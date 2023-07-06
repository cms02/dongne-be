package com.dongne.dongnebe.domain.comment.reply_comment.controller;

import com.dongne.dongnebe.domain.comment.board_comment.service.BoardCommentService;
import com.dongne.dongnebe.domain.comment.reply_comment.service.ReplyCommentService;
import com.dongne.dongnebe.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReplycontrollerCommentController {
    private final ReplyCommentService replyCommentService;

    @GetMapping("/api/replyComments")
    public ResponseEntity<ResponseDto> findAllReplyComments() {

        return ResponseEntity.ok().body(null);
    }
}


