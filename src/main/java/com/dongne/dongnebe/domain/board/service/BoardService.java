package com.dongne.dongnebe.domain.board.service;


import com.dongne.dongnebe.domain.board.dto.*;
import com.dongne.dongnebe.domain.board.dto.request.*;
import com.dongne.dongnebe.domain.board.dto.response.*;
import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.board.repository.BoardQueryRepository;
import com.dongne.dongnebe.domain.board.repository.BoardRepository;
import com.dongne.dongnebe.domain.category.channel.entity.Channel;
import com.dongne.dongnebe.domain.category.channel.repository.ChannelQueryRepository;
import com.dongne.dongnebe.domain.category.channel.repository.ChannelRepository;
import com.dongne.dongnebe.domain.category.main_category.entity.MainCategory;
import com.dongne.dongnebe.domain.category.sub_category.dto.SubCategoryDto;
import com.dongne.dongnebe.domain.category.sub_category.entity.SubCategory;
import com.dongne.dongnebe.domain.city.entity.City;
import com.dongne.dongnebe.domain.comment.board_comment.repository.BoardCommentQueryRepository;
import com.dongne.dongnebe.domain.likes.board_likes.entity.BoardLikes;
import com.dongne.dongnebe.domain.likes.board_likes.repository.BoardLikesQueryRepository;
import com.dongne.dongnebe.domain.user.dto.response.FindUserReactionResponseDto;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.domain.user.repository.UserRepository;
import com.dongne.dongnebe.domain.zone.entity.Zone;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import com.dongne.dongnebe.global.exception.common.ResourceNotFoundException;
import com.dongne.dongnebe.global.service.GlobalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.dongne.dongnebe.global.service.GlobalService.*;
import static io.micrometer.common.util.StringUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardQueryRepository boardQueryRepository;
    private final BoardLikesQueryRepository boardLikesQueryRepository;
    private final BoardCommentQueryRepository boardCommentQueryRepository;
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;
    private final ChannelQueryRepository channelQueryRepository;


    @Transactional
    public ResponseDto writeBoard(WriteBoardRequestDto writeBoardRequestDto, Authentication authentication) {
        Channel channel = null;

        if (!isEmpty(writeBoardRequestDto.getChannelName())){
            channel = channelQueryRepository.findChannelBySubCategoryIdAndName(writeBoardRequestDto.getSubCategoryId(), writeBoardRequestDto.getChannelName());
            if (channel == null) {
                channel = makeChannel(writeBoardRequestDto.getSubCategoryId(), writeBoardRequestDto.getChannelName());
            }
        }

        boardRepository.save(
                        Board.builder()
                                .title(writeBoardRequestDto.getTitle())
                                .content(writeBoardRequestDto.getContent())
                                .fileImg(writeBoardRequestDto.getFileImg())
                                .boardType(writeBoardRequestDto.getBoardType())
                                .mainCategory(MainCategory.builder().mainCategoryId(writeBoardRequestDto.getMainCategoryId()).build())
                                .subCategory(SubCategory.builder().subCategoryId(writeBoardRequestDto.getSubCategoryId()).build())
                                .channel(channel)
                                .user(User.builder().userId(authentication.getName()).build())
                                .city(City.builder().cityCode(writeBoardRequestDto.getCityCode()).build())
                                .zone(Zone.builder().zoneCode(writeBoardRequestDto.getZoneCode()).build())
                                .deadlineAt(writeBoardRequestDto.getDeadlineAt() == null ?
                                        null : LocalDateTime.parse(writeBoardRequestDto.getDeadlineAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                .build()
                );
        User user = userRepository.findByUserId(authentication.getName()).orElseThrow(
                () -> new ResourceNotFoundException("User Not Found")
        );
        user.plusPointByBoard();

        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Write Board")
                .build();
    }

    private Channel makeChannel(Long subCategoryId, String channelName) {
        return channelRepository.save(
                Channel.builder()
                        .subCategory(SubCategory.builder().subCategoryId(subCategoryId).build())
                        .name(channelName)
                        .build()
        );
    }

    @Transactional
    public ResponseDto updateBoard(Long boardId, UpdateBoardRequestDto updateBoardRequestDto, Authentication authentication) {
        validatePermission(updateBoardRequestDto.getUserId(), authentication);
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new ResourceNotFoundException("BoardId Not Found"));
        board.update(updateBoardRequestDto);
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Update Board")
                .build();
    }

    @Transactional
    public ResponseDto deleteBoard(Long boardId, DeleteBoardRequestDto deleteBoardRequestDto, Authentication authentication) {
        validatePermission(deleteBoardRequestDto.getUserId(),authentication);
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new ResourceNotFoundException("Board Id Not Found")
        );
        board.delete();

        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Delete Board")
                .build();
    }

    @Transactional(readOnly = true)
    public FindOneBoardResponseDto findOneBoard(Long boardId, Authentication authentication) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board Id Not Found"));
        board.plusViewCnt();

        Optional<BoardLikes> boardLikesByBoardIdAndUserId = boardLikesQueryRepository.findBoardLikesByBoardIdAndUserId(board.getBoardId(), authentication.getName());
        Long boardLikesId;
        if (boardLikesByBoardIdAndUserId.isPresent()) {
            boardLikesId = boardLikesByBoardIdAndUserId.get().getBoardLikesId();
        } else {
            boardLikesId = null;
        }

        Long subCategoryId = board.getSubCategory() == null ? null : board.getSubCategory().getSubCategoryId();
        Board preBoard;
        Board nextBoard;
        if (subCategoryId == null) {
            preBoard = boardQueryRepository.findPreEventBoardByBoardId(boardId);
            nextBoard = boardQueryRepository.findNextEventBoardByBoardId(boardId);
        } else {
            preBoard = boardQueryRepository.findPreBoardByBoardId(subCategoryId, boardId);
            nextBoard = boardQueryRepository.findNextBoardByBoardId(subCategoryId, boardId);
        }

        return new FindOneBoardResponseDto(board, boardLikesId, preBoard, nextBoard);
    }


    @Transactional(readOnly = true)
    public FindBestBoardsByPeriodResponseDto findBestBoardsByPeriod(FindBestBoardsRequestDto findBestBoardsRequestDto, Pageable pageable) {
        List<Board> findBestBoards = boardQueryRepository.findBestBoardsByPeriod(findBestBoardsRequestDto, pageable);
        return new FindBestBoardsByPeriodResponseDto(findBestBoards);
    }

    @Transactional(readOnly = true)
    public FindHotBoardsResponseDto findHotBoards(FindHotBoardsRequestDto findHotBoardsRequestDto) {
        List<SubCategoryDto> topNSubCategoryDtos = boardQueryRepository.findTopNSubCategoryIds(findHotBoardsRequestDto);
        List<FindHotBoardsByCategoriesDto> findHotBoardsByCategoriesDtos = new ArrayList<>();

        for (SubCategoryDto topNSubCategoryDto : topNSubCategoryDtos) {
            List<FindHotBoardsDto> findHotBoardsDtos = boardQueryRepository.findHotBoardsBySubCategoryId(topNSubCategoryDto.getSubCategoryId(), findHotBoardsRequestDto);

            findHotBoardsByCategoriesDtos.add(FindHotBoardsByCategoriesDto.builder()
                    .subCategoryId(topNSubCategoryDto.getSubCategoryId())
                    .subCategoryName(topNSubCategoryDto.getName())
                    .findHotBoardsDtos(findHotBoardsDtos)
                    .build());
        }
        return new FindHotBoardsResponseDto(findHotBoardsByCategoriesDtos);
    }

    @Transactional(readOnly = true)
    public FindEventBoardsByPeriodResponseDto findEventBoardsByPeriod(FindDefaultBoardsRequestDto findDefaultBoardsRequestDto, Pageable pageable) {
        List<Board> findEventBoardsByPeriod = boardQueryRepository.findEventBoardsByPeriod(findDefaultBoardsRequestDto, pageable);
        return new FindEventBoardsByPeriodResponseDto(findEventBoardsByPeriod);
    }

    @Transactional(readOnly = true)
    public FindSearchBoardsResponseDto findSearchBoards(FindSearchBoardsRequestDto findSearchBoardsRequestDto, Pageable pageable) {
        List<Board> findSearchBoardsDtos = boardQueryRepository.findSearchBoards(findSearchBoardsRequestDto, pageable);
        int boardSize = boardQueryRepository.findSearchBoardsSize(findSearchBoardsRequestDto);
        int totalPageCount = getTotalPageCount(boardSize, pageable);
        return new FindSearchBoardsResponseDto(findSearchBoardsDtos, totalPageCount);
    }
    @Transactional
    public FindUploadBoardImagesResponseDto uploadBoardImages(List<MultipartFile> files) {
        String imgFilePath= null;
        List<String> imgFilePaths = new ArrayList<>();
        if (!files.isEmpty()) {
            for (MultipartFile file : files) {
                uploadFile(imgFilePaths, file);
            }

            imgFilePath = imgFilePaths.stream()
                    .collect(Collectors.joining(","));
        }
        return FindUploadBoardImagesResponseDto.builder()
                .responseMessage("Upload Images")
                .statusCode(HttpStatus.OK.value())
                .fileImg(imgFilePath)
                .build();
    }

    public FindUserReactionResponseDto findBoardCommentReaction(Pageable pageable, Authentication authentication) {
        List<Long> myBoardIds = boardQueryRepository.findMyBoardIds(authentication.getName());
        List<Board> reactionCommentBoards = boardCommentQueryRepository.findBoardCommentsByBoardIds(myBoardIds, pageable);
        int boardSize = boardCommentQueryRepository.findBoardCommentsSize(myBoardIds);
        int totalPageCount = getTotalPageCount(boardSize, pageable);
        return new FindUserReactionResponseDto(reactionCommentBoards, totalPageCount);
    }

    public FindUserReactionResponseDto findBoardLikesReaction(Pageable pageable, Authentication authentication) {
        List<Long> myBoardIds = boardQueryRepository.findMyBoardIds(authentication.getName());
        List<Board> reactionLikesBoards = boardLikesQueryRepository.findBoardLikesByBoardIds(myBoardIds, pageable);
        int boardSize = boardLikesQueryRepository.findBoardLikesSize(myBoardIds);
        int totalPageCount = getTotalPageCount(boardSize, pageable);
        return new FindUserReactionResponseDto(reactionLikesBoards, totalPageCount);
    }
}
