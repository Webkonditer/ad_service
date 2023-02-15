package ru.skypro.homework.dto.adsDto;

import com.sun.istack.NotNull;
import lombok.*;

/**
 * DTO для создания и изменения объявления
 * */
@Data
@NoArgsConstructor
public class AdsCreateDto {
    /**
     * Поле - описание объявления
     * */
    @NotNull
    private String description;

    /**
     * Поле - цена товара
     * */
    @NotNull
    private Integer price;

    /**
     * Поле - заголовок объявления
     * */
    @NotNull
    private String title;

    /**
     * Поле - id автора объявления
     * */
    private Integer author;
}
