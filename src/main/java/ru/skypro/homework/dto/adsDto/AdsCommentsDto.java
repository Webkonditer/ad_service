package ru.skypro.homework.dto.adsDto;


import lombok.Data;

import java.util.List;

@Data
public class AdsCommentsDto {

    /**
     * Поле - счетчик объявлений
     */
    private Integer count;

    /**
     * Поле - массив размещенных объявлений
     */
    private List<AdsCommentsResultsDto> allResults;

}
