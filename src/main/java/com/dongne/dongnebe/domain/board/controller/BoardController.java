package com.dongne.dongnebe.domain.board.controller;

import com.dongne.dongnebe.domain.board.dto.request.*;
import com.dongne.dongnebe.domain.board.dto.response.*;
import com.dongne.dongnebe.domain.board.service.BoardService;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseEntity<ResponseDto> writeBoard(@RequestPart(value = "files", required = false) List<MultipartFile> files,
                                                  @RequestPart(value = "writeBoardRequestDto") WriteBoardRequestDto writeBoardRequestDto,
                                                  Authentication authentication) {
        ResponseDto result = boardService.writeBoard(files, writeBoardRequestDto, authentication);
        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/api/board/{boardId}")
    public ResponseEntity<ResponseDto> updateBoard(@PathVariable Long boardId,
                                                   @RequestPart(value = "files", required = false) List<MultipartFile> files,
                                                   @RequestPart UpdateBoardRequestDto updateBoardRequestDto,
                                                   Authentication authentication) {
        ResponseDto result = boardService.updateBoard(boardId, files, updateBoardRequestDto, authentication);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/api/board/{boardId}")
    public ResponseEntity<ResponseDto> deleteBoard(@PathVariable Long boardId,
                                                   DeleteBoardRequestDto deleteBoardRequestDto,
                                                   Authentication authentication) {
        ResponseDto result = boardService.deleteBoard(boardId, deleteBoardRequestDto, authentication);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/api/board/latest")
    public ResponseEntity<ResponseDto> findLatestBoards(@RequestBody FindDefaultBoardsRequestDto findDefaultBoardsRequestDto,
                                                         Pageable pageable) {
        FindLatestBoardResponseDto result = boardService.findLatestBoards(findDefaultBoardsRequestDto, pageable);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/api/board/hot")
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

    @GetMapping("/api/board/best")
    public ResponseEntity<ResponseDto> findBestBoardsByPeriod(@RequestBody FindDefaultBoardsRequestDto findDefaultBoardsRequestDto,
                                                           Pageable pageable) {
        FindBestBoardsByPeriodResponseDto result = boardService.findBestBoardsByPeriod(findDefaultBoardsRequestDto, pageable);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/api/action-test")
    public ResponseEntity<ResponseDto> actionTest() {
        return ResponseEntity.ok().body(ResponseDto.builder().responseMessage("성공적").statusCode(HttpStatus.OK.value()).build());
    }

    @GetMapping("/api/board/event")
    public ResponseEntity<ResponseDto> findEventBoards(@RequestBody FindDefaultBoardsRequestDto findDefaultBoardsRequestDto,
                                                              Pageable pageable) {
        FindEventBoardsByPeriodResponseDto result = boardService.findEventBoardsByPeriod(findDefaultBoardsRequestDto, pageable);
        return ResponseEntity.ok().body(result);
    }

}


