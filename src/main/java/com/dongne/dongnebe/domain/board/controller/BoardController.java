package com.dongne.dongnebe.domain.board.controller;

import com.dongne.dongnebe.domain.board.service.BoardService;
import com.dongne.dongnebe.domain.city.dto.CityResponseDto;
import com.dongne.dongnebe.domain.city.service.CityService;
import com.dongne.dongnebe.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/api/boards")
    public ResponseEntity<ResponseDto> findAllBoards() {

        return ResponseEntity.ok().body(null);
}}


