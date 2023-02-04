package ru.skypro.homework.dto.adsDto;

import lombok.Data;

import java.util.List;

@Data
public class AdsAllDto {

    /**
     * Поле - счетчик объявлений
     */
    private Integer count;
    private List<AdsAllResultsDto> allResults;
}
