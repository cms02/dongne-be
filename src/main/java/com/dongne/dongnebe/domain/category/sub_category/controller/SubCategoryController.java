package com.dongne.dongnebe.domain.category.sub_category.controller;

import com.dongne.dongnebe.domain.category.sub_category.dto.SubCategoryResponseDto;
import com.dongne.dongnebe.domain.category.sub_category.service.SubCategoryService;
import com.dongne.dongnebe.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubCategoryController {
    private final SubCategoryService subCategoryService;
    @GetMapping("/api/sub-categories/{mainCategoryId}")
    public ResponseEntity<ResponseDto> findAllSubCategoriesByMainCategoryId(@PathVariable Long mainCategoryId) {
        SubCategoryResponseDto result = subCategoryService.findAllSubCategoriesByMainCategoryIdOrderBySubCategoryId(mainCategoryId);
        return ResponseEntity.ok().body(result);
}}


