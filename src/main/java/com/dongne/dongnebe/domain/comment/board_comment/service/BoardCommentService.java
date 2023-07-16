package com.dongne.dongnebe.domain.comment.board_comment.service;


import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.board.repository.BoardRepository;
import com.dongne.dongnebe.domain.comment.board_comment.dto.*;
import com.dongne.dongnebe.domain.comment.board_comment.dto.request.DeleteBoardCommentRequestDto;
import com.dongne.dongnebe.domain.comment.board_comment.dto.request.UpdateBoardCommentRequestDto;
import com.dongne.dongnebe.domain.comment.board_comment.dto.request.WriteBoardCommentRequestDto;
import com.dongne.dongnebe.domain.comment.board_comment.dto.response.FindBoardCommentsResponseDto;
import com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment;
import com.dongne.dongnebe.domain.comment.board_comment.repository.BoardCommentQueryRepository;
import com.dongne.dongnebe.domain.comment.board_comment.repository.BoardCommentRepository;
import com.dongne.dongnebe.domain.likes.board_comment_likes.repository.BoardCommentLikesQueryRepository;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import com.dongne.dongnebe.global.exception.common.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.dongne.dongnebe.global.service.GlobalService.validatePermission;


@Service
@RequiredArgsConstructor
public class BoardCommentService {
    private final BoardCommentRepository boardCommentRepository;
    private final BoardCommentQueryRepository boardCommentQueryRepository;

    private final BoardRepository boardRepository;
    private final BoardCommentLikesQueryRepository boardCommentLikesQueryRepository;


    @Transactional

    public ResponseDto writeBoardComment(WriteBoardCommentRequestDto writeBoardCommentRequestDto,
                                         Authentication authentication) {
        boardCommentRepository.save(BoardComment.builder()
                .content(writeBoardCommentRequestDto.getContent())
                .user(User.builder().userId(authentication.getName()).build())
                .board(Board.builder().boardId(writeBoardCommentRequestDto.getBoardId()).build())
                .build());
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Write Board Comment")
                .build();
    }

    @Transactional
    public ResponseDto updateBoardComment(Long boardCommentId, UpdateBoardCommentRequestDto updateBoardCommentRequestDto, Authentication authentication) {
        validatePermission(updateBoardCommentRequestDto.getUserId(), authentication);
        BoardComment boardComment = boardCommentRepository.findById(boardCommentId)
                .orElseThrow(() -> new ResourceNotFoundException("Board Comment Id Not Found"));
        boardComment.update(updateBoardCommentRequestDto);
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Update Board Comment")
                .build();
    }

    @Transactional
    public ResponseDto deleteBoardComment(Long boardCommentId, DeleteBoardCommentRequestDto deleteBoardCommentRequestDto, Authentication authentication) {
        validatePermission(deleteBoardCommentRequestDto.getUserId(), authentication);
        BoardComment boardComment = boardCommentRepository.findById(boardCommentId)
                .orElseThrow(() -> new ResourceNotFoundException("Board Comment Id Not Found"));
        boardComment.delete();
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Delete Board Comment")
                .build();
    }

    @Transactional(readOnly = true)
    public FindBoardCommentsResponseDto findBoardComments(Long boardId, Pageable pageable, Authentication authentication) {
        List<BoardComment> boardComments = boardCommentQueryRepository.findBoardComments(boardId, pageable);
        List<FindBoardCommentDto> findBoardCommentDtos = boardComments.stream()
                .map(c -> FindBoardCommentDto.builder()
                        .boardComment(c)
                        .isLiked(checkUserBoardCommentLikes(authentication, c))
                        .build())
                .collect(Collectors.toList());
        return new FindBoardCommentsResponseDto(findBoardCommentDtos);
    }

    private boolean checkUserBoardCommentLikes(Authentication authentication, BoardComment boardComment) {
        return boardCommentLikesQueryRepository
                .findBoardCommentLikesByBoardCommentIdAndUserId(boardComment.getBoardCommentId(), authentication.getName()).isPresent();
    }
}
