package com.dongne.dongnebe.domain.comment.reply_comment.service;


import com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment;
import com.dongne.dongnebe.domain.comment.reply_comment.dto.DeleteReplyCommentRequestDto;
import com.dongne.dongnebe.domain.comment.reply_comment.dto.UpdateReplyCommentRequestDto;
import com.dongne.dongnebe.domain.comment.reply_comment.dto.WriteReplyCommentRequestDto;
import com.dongne.dongnebe.domain.comment.reply_comment.entity.ReplyComment;
import com.dongne.dongnebe.domain.comment.reply_comment.repository.ReplyCommentRepository;
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
public class ReplyCommentService {
    private final ReplyCommentRepository replyCommentRepository;

    @Transactional
    public ResponseDto writeReplyComment(WriteReplyCommentRequestDto writeReplyCommentRequestDto) {
        replyCommentRepository.save(ReplyComment.builder()
                .content(writeReplyCommentRequestDto.getContent())
                .user(User.builder().userId(writeReplyCommentRequestDto.getUserId()).build())
                .boardComment(BoardComment.builder().boardCommentId(writeReplyCommentRequestDto.getReplyCommentId()).build())
                .build());
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Write Reply Comment")
                .build();
    }

    @Transactional
    public ResponseDto updateReplyComment(Long replyCommentId, UpdateReplyCommentRequestDto updateReplyCommentRequestDto, Authentication authentication) {
        validatePermission(updateReplyCommentRequestDto.getUserId(), authentication);
        ReplyComment replyComment = replyCommentRepository.findById(replyCommentId)
                .orElseThrow(() -> new ResourceNotFoundException("BoardCommentId Not Found"));
        replyComment.update(updateReplyCommentRequestDto);
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Update Reply Comment")
                .build();
    }

    @Transactional
    public ResponseDto deleteReplyComment(Long replyCommentId, DeleteReplyCommentRequestDto deleteReplyCommentRequestDto, Authentication authentication) {
        validatePermission(deleteReplyCommentRequestDto.getUserId(), authentication);
        ReplyComment replyComment = replyCommentRepository.findById(replyCommentId)
                .orElseThrow(() -> new ResourceNotFoundException("BoardCommentId Not Found"));
        replyComment.delete();
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Delete Reply Comment")
                .build();
    }


}
