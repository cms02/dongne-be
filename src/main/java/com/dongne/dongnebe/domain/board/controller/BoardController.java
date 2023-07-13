package com.dongne.dongnebe.domain.board.controller;

import com.dongne.dongnebe.domain.board.dto.*;
import com.dongne.dongnebe.domain.board.service.BoardService;
import com.dongne.dongnebe.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseEntity<ResponseDto> writeBoard(@RequestPart MultipartFile file,
                                                  @RequestPart WriteBoardRequestDto writeBoardRequestDto,
                                                  Authentication authentication) {
        ResponseDto result = boardService.writeBoard(file, writeBoardRequestDto, authentication);
        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/api/board/{boardId}")
    public ResponseEntity<ResponseDto> updateBoard(@PathVariable Long boardId,
                                                   @RequestPart MultipartFile file,
                                                   @RequestPart UpdateBoardRequestDto updateBoardRequestDto,
                                                   Authentication authentication) {
        ResponseDto result = boardService.updateBoard(boardId, file, updateBoardRequestDto, authentication);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/api/board/{boardId}")
    public ResponseEntity<ResponseDto> deleteBoard(@PathVariable Long boardId,
                                                   DeleteBoardRequestDto deleteBoardRequestDto,
                                                   Authentication authentication) {
        ResponseDto result = boardService.deleteBoard(boardId, deleteBoardRequestDto, authentication);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/api/board/latest-boards")
    public ResponseEntity<ResponseDto> findLatestBoards(@RequestBody FindMainBoardsRequestDto findMainBoardsRequestDto,
                                                         Pageable pageable) {
        FindLatestBoardResponseDto result = boardService.findLatestBoards(findMainBoardsRequestDto, pageable);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/api/board/hot-boards")
    public ResponseEntity<ResponseDto> findHotBoards(@RequestBody FindMainBoardsRequestDto findMainBoardsRequestDto,
                                                        Pageable pageable) {
        FindLatestBoardResponseDto result = boardService.findLatestBoards(findMainBoardsRequestDto, pageable);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/api/board/{boardId}")
    public ResponseEntity<ResponseDto> findOneBoard(@PathVariable Long boardId) {
        FindOneBoardResponseDto result = boardService.findOneBoard(boardId);
        return ResponseEntity.ok().body(result);
    }
}


