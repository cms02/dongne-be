package com.dongne.dongnebe.domain.city.dto.response;

import com.dongne.dongnebe.domain.category.channel.dto.ChannelDto;
import com.dongne.dongnebe.domain.city.dto.CityCodeNameDto;
import com.dongne.dongnebe.domain.city.dto.CityNameCountDto;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@SuperBuilder
public class TopNCityResponseDto extends ResponseDto {
    private List<CityNameCountDto> cityNameCountDtos;

    public TopNCityResponseDto(List<CityNameCountDto> cityNameCountDtos) {
        super(HttpStatus.OK.value(), "Find Top N City");
        this.cityNameCountDtos = cityNameCountDtos;
    }
}
