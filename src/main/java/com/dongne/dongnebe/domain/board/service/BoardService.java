package com.dongne.dongnebe.domain.board.service;


import com.dongne.dongnebe.domain.board.dto.WriteBoardRequestDto;
import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.board.repository.BoardRepository;
import com.dongne.dongnebe.domain.category.channel.entity.Channel;
import com.dongne.dongnebe.domain.category.main_category.entity.MainCategory;
import com.dongne.dongnebe.domain.category.sub_category.entity.SubCategory;
import com.dongne.dongnebe.domain.city.dto.CityCodeNameDto;
import com.dongne.dongnebe.domain.city.dto.CityResponseDto;
import com.dongne.dongnebe.domain.city.entity.City;
import com.dongne.dongnebe.domain.city.repository.CityRepository;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.domain.zone.entity.Zone;
import com.dongne.dongnebe.global.dto.ResponseDto;
import com.dongne.dongnebe.global.service.GlobalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static com.dongne.dongnebe.global.service.GlobalService.*;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public ResponseDto writeBoard(MultipartFile file, WriteBoardRequestDto writeBoardRequestDto, Authentication authentication) {
        validatePermission(writeBoardRequestDto.getUserId(), authentication);

        uploadFile(file);
        boardRepository.save(
                Board.builder()
                        .title(writeBoardRequestDto.getTitle())
                        .content(writeBoardRequestDto.getContent())
                        .fileImg(getImgFilePath(file))
                        .type(writeBoardRequestDto.getBoardType())
                        .mainCategory(MainCategory.builder().mainCategoryId(writeBoardRequestDto.getMainCategoryId()).build())
                        .subCategory(SubCategory.builder().subCategoryId(writeBoardRequestDto.getSubCategoryId()).build())
                        .channel(writeBoardRequestDto.getChannelId() == null ?
                                null : Channel.builder().channelId(writeBoardRequestDto.getChannelId()).build())
                        .user(User.builder().userId(writeBoardRequestDto.getUserId()).build())
                        .city(City.builder().cityCode(writeBoardRequestDto.getCityCode()).build())
                        .zone(Zone.builder().zoneCode(writeBoardRequestDto.getZoneCode()).build())
                        .deadline_at(writeBoardRequestDto.getDeadLineAt() == null ?
                                null : LocalDateTime.parse(writeBoardRequestDto.getDeadLineAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .build()
        );


        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Write Board")
                .build();
    }
}
