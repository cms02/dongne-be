package com.dongne.dongnebe.domain.likes.board_comment_likes.controller;

import com.dongne.dongnebe.domain.likes.board_comment_likes.service.BoardCommentLikesService;
import com.dongne.dongnebe.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardCommentLikesController {
    private final BoardCommentLikesService boardCommentLikesService;

    @PostMapping("/api/boardCommentLikes/check/{boardCommentId}")
    public ResponseEntity<ResponseDto> checkBoardCommentLikes(@PathVariable Long boardCommentId,
                                                       Authentication authentication) {
        ResponseDto result = boardCommentLikesService.checkBoardCommentLikes(boardCommentId, authentication);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/boardCommentLikes/cancel/{boardCommentLikesId}")
    public ResponseEntity<ResponseDto> cancelBoardCommentLikes(@PathVariable Long boardCommentLikesId,
                                                        Authentication authentication) {
        ResponseDto result = boardCommentLikesService.cancelBoardCommentLikes(boardCommentLikesId, authentication);
        return ResponseEntity.ok(result);
    }
}
