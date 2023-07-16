package com.dongne.dongnebe.domain.comment.board_comment.controller;

import com.dongne.dongnebe.domain.comment.board_comment.dto.request.DeleteBoardCommentRequestDto;
import com.dongne.dongnebe.domain.comment.board_comment.dto.response.FindBoardCommentsResponseDto;
import com.dongne.dongnebe.domain.comment.board_comment.dto.request.UpdateBoardCommentRequestDto;
import com.dongne.dongnebe.domain.comment.board_comment.dto.request.WriteBoardCommentRequestDto;
import com.dongne.dongnebe.domain.comment.board_comment.service.BoardCommentService;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardCommentController {
    private final BoardCommentService boardCommentService;

    @PostMapping("/api/boardComment")
    public ResponseEntity<ResponseDto> writeBoardComment(@RequestBody WriteBoardCommentRequestDto writeBoardCommentRequestDto,
                                                         Authentication authentication) {
        ResponseDto result = boardCommentService.writeBoardComment(writeBoardCommentRequestDto, authentication);
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

    @GetMapping("/api/boardComment/{boardId}")
    public ResponseEntity<ResponseDto> findBoardComments(@PathVariable Long boardId,
                                                    Pageable pageable,
                                                    Authentication authentication) {
        FindBoardCommentsResponseDto result = boardCommentService.findBoardComments(boardId, pageable, authentication);
        return ResponseEntity.ok().body(result);
    }



}


