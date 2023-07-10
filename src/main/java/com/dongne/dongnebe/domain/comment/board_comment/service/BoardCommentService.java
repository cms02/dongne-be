package com.dongne.dongnebe.domain.comment.board_comment.service;


import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.comment.board_comment.dto.DeleteBoardCommentRequestDto;
import com.dongne.dongnebe.domain.comment.board_comment.dto.UpdateBoardCommentRequestDto;
import com.dongne.dongnebe.domain.comment.board_comment.dto.WriteBoardCommentRequestDto;
import com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment;
import com.dongne.dongnebe.domain.comment.board_comment.repository.BoardCommentRepository;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.global.dto.ResponseDto;
import com.dongne.dongnebe.global.exception.user.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dongne.dongnebe.global.service.GlobalService.validatePermission;


@Service
@RequiredArgsConstructor
public class BoardCommentService {
    private final BoardCommentRepository boardCommentRepository;

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
                .orElseThrow(() -> new ResourceNotFoundException("BoardCommentId Not Found"));
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
                .orElseThrow(() -> new ResourceNotFoundException("BoardCommentId Not Found"));
        boardComment.delete();
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Delete Board Comment")
                .build();
    }
}
