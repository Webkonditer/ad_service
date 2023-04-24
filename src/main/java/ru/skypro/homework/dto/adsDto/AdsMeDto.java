package ru.skypro.homework.dto.adsDto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Builder;

import java.util.List;

@Data
@Builder
public class AdsMeDto {

    /**
     * Поле - id автора объявления
     * */
    private Integer author;

    /**
     * Поле - список всех объявлений
     */
    @JsonProperty("results")
    private List<AdsDto> allResults;
}
