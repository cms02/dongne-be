package com.dongne.dongnebe.domain.board.controller;

import com.dongne.dongnebe.domain.board.dto.request.*;
import com.dongne.dongnebe.domain.board.dto.response.*;
import com.dongne.dongnebe.domain.board.service.BoardService;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/api/board/upload")
    public ResponseEntity<ResponseDto> uploadBoardImages(@RequestPart(value = "files", required = false) List<MultipartFile> files) {
        FindUploadBoardImagesResponseDto result = boardService.uploadBoardImages(files);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/api/board")
    public ResponseEntity<ResponseDto> writeBoard(@RequestBody WriteBoardRequestDto writeBoardRequestDto,
                                                  Authentication authentication) {
        ResponseDto result = boardService.writeBoard(writeBoardRequestDto, authentication);
        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/api/board/{boardId}")
    public ResponseEntity<ResponseDto> updateBoard(@PathVariable Long boardId,
                                                   @RequestBody UpdateBoardRequestDto updateBoardRequestDto,
                                                   Authentication authentication) {
        ResponseDto result = boardService.updateBoard(boardId, updateBoardRequestDto, authentication);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/api/board/{boardId}")
    public ResponseEntity<ResponseDto> deleteBoard(@PathVariable Long boardId,
                                                   DeleteBoardRequestDto deleteBoardRequestDto,
                                                   Authentication authentication) {
        ResponseDto result = boardService.deleteBoard(boardId, deleteBoardRequestDto, authentication);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/api/board/hot")
    public ResponseEntity<ResponseDto> findHotBoards(@RequestBody FindHotBoardsRequestDto findHotBoardsRequestDto) {
        FindHotBoardsResponseDto result = boardService.findHotBoards(findHotBoardsRequestDto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/api/board/{boardId}")
    public ResponseEntity<ResponseDto> findOneBoard(@PathVariable Long boardId,
                                                    Authentication authentication) {
        FindOneBoardResponseDto result = boardService.findOneBoard(boardId, authentication);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/api/board/best")
    public ResponseEntity<ResponseDto> findBestBoardsByPeriod(@RequestBody FindBestBoardsRequestDto findBestBoardsRequestDto,
                                                           Pageable pageable) {
        FindBestBoardsByPeriodResponseDto result = boardService.findBestBoardsByPeriod(findBestBoardsRequestDto, pageable);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/api/board/event")
    public ResponseEntity<ResponseDto> findEventBoards(@RequestBody FindDefaultBoardsRequestDto findDefaultBoardsRequestDto,
                                                              Pageable pageable) {
        FindEventBoardsByPeriodResponseDto result = boardService.findEventBoardsByPeriod(findDefaultBoardsRequestDto, pageable);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/api/board/search")
    public ResponseEntity<ResponseDto> findBoardsSearch(@RequestBody FindSearchBoardsRequestDto findSearchBoardsRequestDto
            , Pageable pageable) {
        FindSearchBoardsResponseDto result = boardService.findSearchBoards(findSearchBoardsRequestDto, pageable);
        return ResponseEntity.ok().body(result);
    }

}


