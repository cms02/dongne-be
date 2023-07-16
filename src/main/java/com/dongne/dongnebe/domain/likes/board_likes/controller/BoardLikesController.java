package com.dongne.dongnebe.domain.likes.board_likes.controller;

import com.dongne.dongnebe.domain.likes.board_likes.service.BoardLikesService;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardLikesController {
    private final BoardLikesService boardLikesService;

    @PostMapping("/api/boardLikes/check/{boardId}")
    public ResponseEntity<ResponseDto> checkBoardLikes(@PathVariable Long boardId,
                                                       Authentication authentication) {
        ResponseDto result = boardLikesService.checkBoardLikes(boardId, authentication);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/boardLikes/cancel/{boardLikesId}")
    public ResponseEntity<ResponseDto> cancelBoardLikes(@PathVariable Long boardLikesId,
                                                        Authentication authentication) {
        ResponseDto result = boardLikesService.cancelBoardLikes(boardLikesId, authentication);
        return ResponseEntity.ok(result);
    }
}
