package ru.skypro.homework.dto.adsDto;

import lombok.Data;
import ru.skypro.homework.model.Images;

@Data
public class AdsAllResultsDto {

    private Images images;
    private Integer id;
    private Integer price;
    private Integer pk;
    private String title;
}
