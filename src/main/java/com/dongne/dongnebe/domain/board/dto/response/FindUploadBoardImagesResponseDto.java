package com.dongne.dongnebe.domain.board.dto.response;

import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import static com.dongne.dongnebe.global.service.GlobalService.formatLocalDateTimeToString;

@Getter
@SuperBuilder
public class FindUploadBoardImagesResponseDto extends ResponseDto {

    private String fileImg;

}
