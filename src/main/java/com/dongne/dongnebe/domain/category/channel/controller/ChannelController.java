package com.dongne.dongnebe.domain.category.channel.controller;

import com.dongne.dongnebe.domain.board.dto.request.*;
import com.dongne.dongnebe.domain.board.dto.response.*;
import com.dongne.dongnebe.domain.board.service.BoardService;
import com.dongne.dongnebe.domain.category.channel.dto.FindHotChannelsDto;
import com.dongne.dongnebe.domain.category.channel.dto.request.FindHotChannelsRequestDto;
import com.dongne.dongnebe.domain.category.channel.dto.response.FindHotChannelsResponseDto;
import com.dongne.dongnebe.domain.category.channel.service.ChannelService;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChannelController {
    private final ChannelService channelService;

    @PostMapping("/api/channel/hot")
    public ResponseEntity<ResponseDto> findHotChannels(@RequestBody FindHotChannelsRequestDto findHotChannelsRequestDto) {
        FindHotChannelsResponseDto result = channelService.findHotChannels(findHotChannelsRequestDto);
        return ResponseEntity.ok().body(result);
    }


}


