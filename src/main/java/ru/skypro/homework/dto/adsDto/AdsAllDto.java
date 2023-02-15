package ru.skypro.homework.dto.adsDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.DoubleStream;

@Data
@Builder
public class AdsAllDto {

    /**
     * Поле - счетчик объявлений
     */
    private Integer count;

    @JsonProperty("results")
    private List<AdsDto> allResults;

}
