package com.dongne.dongnebe.domain.category.main_category.controller;

import com.dongne.dongnebe.domain.category.main_category.dto.MainCategoryResponseDto;
import com.dongne.dongnebe.domain.category.main_category.service.MainCategoryService;
import com.dongne.dongnebe.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainCategoryController {
    private final MainCategoryService mainCategoryService;
    @GetMapping("/api/main-categories")
    public ResponseEntity<ResponseDto> findAllMainCategories() {
        MainCategoryResponseDto result = mainCategoryService.findAllByOrderByMainCategoryIdAsc();
        return ResponseEntity.ok().body(result);
}}


