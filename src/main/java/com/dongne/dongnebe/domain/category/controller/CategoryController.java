package com.dongne.dongnebe.domain.category.controller;

import com.dongne.dongnebe.domain.category.dto.CategoryResponseDto;
import com.dongne.dongnebe.domain.category.service.CategoryService;
import com.dongne.dongnebe.domain.city.dto.CityResponseDto;
import com.dongne.dongnebe.domain.city.service.CityService;
import com.dongne.dongnebe.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/api/categories")
    public ResponseEntity<ResponseDto> findAllCategories() {
        CategoryResponseDto result = categoryService.findAllByOrderByCategoryIdAsc();
        return ResponseEntity.ok().body(result);
}}


