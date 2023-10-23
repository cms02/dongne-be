package com.dongne.dongnebe.domain.category.channel.service;


import com.dongne.dongnebe.domain.board.dto.*;
import com.dongne.dongnebe.domain.board.dto.request.*;
import com.dongne.dongnebe.domain.board.dto.response.*;
import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.board.repository.BoardQueryRepository;
import com.dongne.dongnebe.domain.board.repository.BoardRepository;
import com.dongne.dongnebe.domain.category.channel.dto.ChannelDto;
import com.dongne.dongnebe.domain.category.channel.dto.FindHotChannelsDto;
import com.dongne.dongnebe.domain.category.channel.dto.request.FindHotChannelsRequestDto;
import com.dongne.dongnebe.domain.category.channel.dto.response.ChannelResponseDto;
import com.dongne.dongnebe.domain.category.channel.dto.response.FindHotChannelsResponseDto;
import com.dongne.dongnebe.domain.category.channel.entity.Channel;
import com.dongne.dongnebe.domain.category.channel.repository.ChannelQueryRepository;
import com.dongne.dongnebe.domain.category.channel.repository.ChannelRepository;
import com.dongne.dongnebe.domain.category.main_category.entity.MainCategory;
import com.dongne.dongnebe.domain.category.sub_category.dto.SubCategoryDto;
import com.dongne.dongnebe.domain.category.sub_category.entity.SubCategory;
import com.dongne.dongnebe.domain.city.entity.City;
import com.dongne.dongnebe.domain.likes.board_likes.repository.BoardLikesQueryRepository;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.domain.user.repository.UserRepository;
import com.dongne.dongnebe.domain.zone.entity.Zone;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import com.dongne.dongnebe.global.exception.common.ResourceNotFoundException;
import com.dongne.dongnebe.global.service.GlobalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.dongne.dongnebe.global.service.GlobalService.getImgFilePath;
import static com.dongne.dongnebe.global.service.GlobalService.validatePermission;
import static io.micrometer.common.util.StringUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class ChannelService {
    private final ChannelQueryRepository channelQueryRepository;

    @Transactional(readOnly = true)
    public FindHotChannelsResponseDto findHotChannels(FindHotChannelsRequestDto findHotChannelsRequestDto) {

        List<FindHotChannelsDto> topNChannelsDtos = channelQueryRepository.findHotChannels(findHotChannelsRequestDto);
        return new FindHotChannelsResponseDto(topNChannelsDtos);
    }

    @Transactional(readOnly = true)
    public ChannelResponseDto findAllChannels(FindDefaultBoardsRequestDto findDefaultBoardsRequestDto, Long subCategoryId) {
        List<ChannelDto> channelDtos = channelQueryRepository.findAllChannels(findDefaultBoardsRequestDto, subCategoryId);
        return new ChannelResponseDto(channelDtos);
    }
}
