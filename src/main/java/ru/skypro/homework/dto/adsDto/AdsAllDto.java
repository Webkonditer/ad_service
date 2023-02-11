package ru.skypro.homework.dto.adsDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AdsAllDto {

    /**
     * Поле - счетчик объявлений
     */
    private Integer count;

    @JsonProperty("results")
    private List<AdsDto> allResults;
}
