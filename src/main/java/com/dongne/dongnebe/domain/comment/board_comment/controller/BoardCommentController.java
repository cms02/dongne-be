package com.dongne.dongnebe.domain.comment.board_comment.controller;

import com.dongne.dongnebe.domain.board.service.BoardService;
import com.dongne.dongnebe.domain.comment.board_comment.service.BoardCommentService;
import com.dongne.dongnebe.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardCommentController {
    private final BoardCommentService boardCommentService;
    @GetMapping("/api/boardComments")
    public ResponseEntity<ResponseDto> findAllBoardComments() {
        return ResponseEntity.ok().body(null);
    }
}


