package ru.skypro.homework.dto.adsDto;


import lombok.Data;

import java.util.List;

@Data
public class AdsCommentsDto {

    private Integer count;
    private List<AdsCommentsResultsDto> allResults;

}
