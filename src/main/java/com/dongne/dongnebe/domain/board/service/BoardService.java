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
import com.dongne.dongnebe.domain.likes.board_likes.repository.BoardLikesQueryRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.dongne.dongnebe.global.service.GlobalService.*;
import static io.micrometer.common.util.StringUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardQueryRepository boardQueryRepository;
    private final BoardLikesQueryRepository boardLikesQueryRepository;
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
    public FindLatestBoardResponseDto findLatestBoards(FindDefaultBoardsRequestDto findDefaultBoardsRequestDto, Pageable pageable) {
        List<Board> boardList = boardQueryRepository.findLatestBoards(findDefaultBoardsRequestDto, pageable);
        int boardSize = boardQueryRepository.findLatestBoardsSize(findDefaultBoardsRequestDto);
        List<FindLatestBoardsDto> findLatestBoardsDtos = boardList.stream().map(FindLatestBoardsDto::new).collect(Collectors.toList());
        int totalPageCount = (boardSize % pageable.getPageSize()) == 0 ?
                (boardSize / pageable.getPageSize()) : (boardSize % pageable.getPageSize()) + 1;
        return new FindLatestBoardResponseDto(findLatestBoardsDtos, totalPageCount);
    }

    @Transactional(readOnly = true)
    public FindOneBoardResponseDto findOneBoard(Long boardId, Authentication authentication) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board Id Not Found"));
        board.plusViewCnt();
        boolean isLiked = boardLikesQueryRepository.findBoardLikesByBoardIdAndUserId(board.getBoardId(), authentication.getName()).isPresent();
        return new FindOneBoardResponseDto(board, isLiked);
    }


    @Transactional(readOnly = true)
    public FindBestBoardsByPeriodResponseDto findBestBoardsByPeriod(FindDefaultBoardsRequestDto findDefaultBoardsRequestDto, Pageable pageable) {
        List<FindBestBoardsByPeriodDto> findBestBoardsByPeriodDtos = boardQueryRepository.findBestBoardsByPeriod(findDefaultBoardsRequestDto, pageable);
        return new FindBestBoardsByPeriodResponseDto(findBestBoardsByPeriodDtos);
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
        List<FindEventBoardsByPeriodDto> findEventBoardsByPeriodDtos = boardQueryRepository.findEventBoardsByPeriod(findDefaultBoardsRequestDto, pageable);
        return new FindEventBoardsByPeriodResponseDto(findEventBoardsByPeriodDtos);
    }

    @Transactional(readOnly = true)
    public FindSearchBoardsResponseDto findSearchBoards(FindSearchBoardsRequestDto findSearchBoardsRequestDto, Pageable pageable) {
        List<FindSearchBoardsDto> findSearchBoardsDtos = boardQueryRepository.findSearchBoards(findSearchBoardsRequestDto, pageable);
        return new FindSearchBoardsResponseDto(findSearchBoardsDtos);
    }
    @Transactional
    public FindUploadBoardImagesResponseDto uploadBoardImages(List<MultipartFile> files, Authentication authentication) {
        String imgFilePath= null;
        if (!files.isEmpty()) {
            files.forEach(GlobalService::uploadFile);

            imgFilePath = files.stream().map(f -> getImgFilePath(f))
                    .collect(Collectors.joining(","));
        }
        return FindUploadBoardImagesResponseDto.builder()
                .responseMessage("Upload Images")
                .statusCode(HttpStatus.OK.value())
                .fileImg(imgFilePath)
                .build();
    }
}
