package com.dongne.dongnebe.domain.comment.reply_comment.controller;

import com.dongne.dongnebe.domain.comment.reply_comment.dto.DeleteReplyCommentRequestDto;
import com.dongne.dongnebe.domain.comment.reply_comment.dto.UpdateReplyCommentRequestDto;
import com.dongne.dongnebe.domain.comment.reply_comment.dto.WriteReplyCommentRequestDto;
import com.dongne.dongnebe.domain.comment.reply_comment.service.ReplyCommentService;
import com.dongne.dongnebe.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReplyCommentController {
    private final ReplyCommentService replyCommentService;

    @PostMapping("/api/replyComment")
    public ResponseEntity<ResponseDto> writeReplyComment(@RequestBody WriteReplyCommentRequestDto writeReplyCommentRequestDto,
                                                         Authentication authentication) {
        ResponseDto result = replyCommentService.writeReplyComment(writeReplyCommentRequestDto, authentication);
        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/api/replyComment/{replyCommentId}")
    public ResponseEntity<ResponseDto> updateBoardComment(@PathVariable Long replyCommentId,
                                                          @RequestBody UpdateReplyCommentRequestDto updateReplyCommentRequestDto,
                                                          Authentication authentication) {
        ResponseDto result = replyCommentService.updateReplyComment(replyCommentId, updateReplyCommentRequestDto, authentication);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/api/replyComment/{replyCommentId}")
    public ResponseEntity<ResponseDto> deleteBoardComment(@PathVariable Long replyCommentId,
                                                          @RequestBody DeleteReplyCommentRequestDto deleteReplyCommentRequestDto,
                                                          Authentication authentication) {
        ResponseDto result = replyCommentService.deleteReplyComment(replyCommentId, deleteReplyCommentRequestDto,authentication);
        return ResponseEntity.ok().body(result);
    }
}


