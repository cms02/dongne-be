package com.dongne.dongnebe.domain.board.dto.response;

import com.dongne.dongnebe.domain.board.dto.FindTodayBestBoardsDto;
import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

import static com.dongne.dongnebe.global.service.GlobalService.formatLocalDateTimeToString;

@Getter
@SuperBuilder
public class FindTodayBestBoardsResponseDto extends ResponseDto {
    List<FindTodayBestBoardsDto> findTodayBestBoardsDtos;

    public FindTodayBestBoardsResponseDto(List<FindTodayBestBoardsDto> findTodayBestBoardsDtos) {
        super(HttpStatus.OK.value(), "Find Today Best Boards");
        this.findTodayBestBoardsDtos = findTodayBestBoardsDtos;
    }
}
