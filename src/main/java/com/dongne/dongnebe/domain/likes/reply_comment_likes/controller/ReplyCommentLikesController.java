package com.dongne.dongnebe.domain.likes.reply_comment_likes.controller;

import com.dongne.dongnebe.domain.likes.reply_comment_likes.service.ReplyCommentLikesService;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReplyCommentLikesController {
    private final ReplyCommentLikesService replyCommentLikesService;

    @PostMapping("/api/replyCommentLikes/check/{replyCommentId}")
    public ResponseEntity<ResponseDto> checkReplyCommentLikes(@PathVariable Long replyCommentId,
                                                       Authentication authentication) {
        ResponseDto result = replyCommentLikesService.checkReplyCommentLikes(replyCommentId, authentication);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/replyCommentLikes/cancel/{replyCommentLikesId}")
    public ResponseEntity<ResponseDto> cancelReplyCommentLikes(@PathVariable Long replyCommentLikesId,
                                                        Authentication authentication) {
        ResponseDto result = replyCommentLikesService.cancelReplyCommentLikes(replyCommentLikesId, authentication);
        return ResponseEntity.ok(result);
    }
}
