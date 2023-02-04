package ru.skypro.homework.dto.adsDto;

import lombok.Data;
import ru.skypro.homework.model.Images;

@Data
public class AdsAddDto {

    /**
     * Поле - картинка из объявления (изображение товара/услуги)
     */
    private Images images;

    /**
     * Поле - цена товара/услуги из объявления
     */
    private Integer price;

    /**
     * Поле - описание объявления
     */
    private String description;

    /**
     * Поле - primary key - идентификатор объявления
     */
    private Integer pk;

    /**
     * Поле - заголовок объявления
     */
    private String title;

}
