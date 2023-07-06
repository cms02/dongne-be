package com.dongne.dongnebe.domain.board.controller;

import com.dongne.dongnebe.domain.board.dto.WriteBoardRequestDto;
import com.dongne.dongnebe.domain.board.service.BoardService;
import com.dongne.dongnebe.domain.city.dto.CityResponseDto;
import com.dongne.dongnebe.domain.city.service.CityService;
import com.dongne.dongnebe.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
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
//        @valid 추가해야함

        ResponseDto result = boardService.writeBoard(file, writeBoardRequestDto, authentication);
        return ResponseEntity.ok().body(result);
    }
}


