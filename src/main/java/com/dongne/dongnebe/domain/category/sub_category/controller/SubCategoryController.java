package com.dongne.dongnebe.domain.category.sub_category.controller;

import com.dongne.dongnebe.domain.board.dto.request.FindDefaultBoardsRequestDto;
import com.dongne.dongnebe.domain.category.sub_category.dto.response.SubCategoryResponseDto;
import com.dongne.dongnebe.domain.category.sub_category.service.SubCategoryService;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SubCategoryController {
    private final SubCategoryService subCategoryService;
    @GetMapping("/api/sub-categories/{mainCategoryId}")
    public ResponseEntity<ResponseDto> findAllSubCategoriesByMainCategoryId(@PathVariable Long mainCategoryId) {
        SubCategoryResponseDto result = subCategoryService.findAllSubCategoriesByMainCategoryIdOrderBySubCategoryId(mainCategoryId);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/api/sub-categories")
    public ResponseEntity<ResponseDto> findAllSubCategories(@RequestBody FindDefaultBoardsRequestDto findDefaultBoardsRequestDto, Pageable pageable) {
        SubCategoryResponseDto result = subCategoryService.findAllSubCategories(findDefaultBoardsRequestDto, pageable);
        return ResponseEntity.ok().body(result);
    }



}


