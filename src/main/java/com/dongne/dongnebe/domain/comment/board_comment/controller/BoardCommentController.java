package com.dongne.dongnebe.domain.comment.board_comment.controller;

import com.dongne.dongnebe.domain.board.service.BoardService;
import com.dongne.dongnebe.domain.comment.board_comment.dto.DeleteBoardCommentRequestDto;
import com.dongne.dongnebe.domain.comment.board_comment.dto.UpdateBoardCommentRequestDto;
import com.dongne.dongnebe.domain.comment.board_comment.dto.WriteBoardCommentRequestDto;
import com.dongne.dongnebe.domain.comment.board_comment.service.BoardCommentService;
import com.dongne.dongnebe.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardCommentController {
    private final BoardCommentService boardCommentService;
    @PostMapping("/api/boardComment")
    public ResponseEntity<ResponseDto> writeBoardComment(@RequestBody WriteBoardCommentRequestDto writeBoardCommentRequestDto) {
        ResponseDto result = boardCommentService.writeBoardComment(writeBoardCommentRequestDto);
        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/api/boardComment/{boardCommentId}")
    public ResponseEntity<ResponseDto> updateBoardComment(@PathVariable Long boardCommentId,
                                                          @RequestBody UpdateBoardCommentRequestDto updateBoardCommentRequestDto,
                                                          Authentication authentication) {
        ResponseDto result = boardCommentService.updateBoardComment(boardCommentId, updateBoardCommentRequestDto, authentication);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/api/boardComment/{boardCommentId}")
    public ResponseEntity<ResponseDto> deleteBoardComment(@PathVariable Long boardCommentId,
                                                          @RequestBody DeleteBoardCommentRequestDto deleteBoardCommentRequestDto,
                                                          Authentication authentication) {
        ResponseDto result = boardCommentService.deleteBoardComment(boardCommentId, deleteBoardCommentRequestDto,authentication);
        return ResponseEntity.ok().body(result);
    }

}

