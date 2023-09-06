package com.dongne.dongnebe.domain.board.service;


import com.dongne.dongnebe.domain.board.dto.*;
import com.dongne.dongnebe.domain.board.dto.request.*;
import com.dongne.dongnebe.domain.board.dto.response.*;
import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.board.repository.BoardQueryRepository;
import com.dongne.dongnebe.domain.board.repository.BoardRepository;
import com.dongne.dongnebe.domain.category.channel.entity.Channel;
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
import java.util.stream.Collectors;

import static com.dongne.dongnebe.global.service.GlobalService.*;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardQueryRepository boardQueryRepository;
    private final BoardLikesQueryRepository boardLikesQueryRepository;
    private final UserRepository userRepository;
    private final GlobalService globalService;


    @Transactional
    public ResponseDto writeBoard(List<MultipartFile> files, WriteBoardRequestDto writeBoardRequestDto, Authentication authentication) {
        String imgFilePath= null;
        if (!files.isEmpty()) {
            files.forEach(GlobalService::uploadFile);

            imgFilePath = files.stream().map(f -> getImgFilePath(f))
                    .collect(Collectors.joining(","));
        }

        boardRepository.save(
                Board.builder()
                        .title(writeBoardRequestDto.getTitle())
                        .content(writeBoardRequestDto.getContent())
                        .fileImg(imgFilePath)
                        .boardType(writeBoardRequestDto.getBoardType())
                        .mainCategory(MainCategory.builder().mainCategoryId(writeBoardRequestDto.getMainCategoryId()).build())
                        .subCategory(SubCategory.builder().subCategoryId(writeBoardRequestDto.getSubCategoryId()).build())
                        .channel(writeBoardRequestDto.getChannelId() == null ?
                                null : Channel.builder().channelId(writeBoardRequestDto.getChannelId()).build())
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

    @Transactional
    public ResponseDto updateBoard(Long boardId, List<MultipartFile> files, UpdateBoardRequestDto updateBoardRequestDto, Authentication authentication) {
        String imgFilePath= null;
        if (!files.isEmpty()) {
            files.forEach(GlobalService::uploadFile);
            imgFilePath = files.stream().map(f -> getImgFilePath(f))
                    .collect(Collectors.joining(","));
        }
        validatePermission(updateBoardRequestDto.getUserId(), authentication);
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new ResourceNotFoundException("BoardId Not Found"));
        board.update(updateBoardRequestDto, imgFilePath);
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
        List<FindLatestBoardsDto> findLatestBoardsDtos = boardList.stream().map(FindLatestBoardsDto::new).collect(Collectors.toList());
        return new FindLatestBoardResponseDto(findLatestBoardsDtos);
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
}
