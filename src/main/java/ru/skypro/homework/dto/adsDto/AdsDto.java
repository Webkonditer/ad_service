package ru.skypro.homework.dto.adsDto;

import lombok.*;

/**
 * DTO результата создания и изменения объявления
 * */
@Data
@NoArgsConstructor
public class AdsDto {
    /**
     * Поле - id автора объявления
     * */
    private Integer author;

    /**
     * Поле - ссылка на картинку объявления
     */
    private String image;

    /**
     * Поле - идентификатор объявления
     */
    private Integer pk;

    /**
     * Поле - цена товара
     * */
    private Integer price;

    /**
     * Поле - заголовок объявления
     * */
    private String title;
}
