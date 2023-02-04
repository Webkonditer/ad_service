package ru.skypro.homework.dto.adsDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.skypro.homework.model.Images;

@Data
public class AdsAllResultsDto {

    /**
     * Поле - картинка из объявления (изображение товара/услуги)
     */
    private Images images;

    /**
     * Поле - id пользователя/автора объявления
     */
    @JsonProperty("author")
    private Integer id;

    /**
     * Поле - цена товара/услуги из объявления
     */
    private Integer price;

    /**
     * Поле - primary key - идентификатор объявления
     */
    private Integer pk;

    /**
     * Поле - заголовок объявления
     */
    private String title;
}
