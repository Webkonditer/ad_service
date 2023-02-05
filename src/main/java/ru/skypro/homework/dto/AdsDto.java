package ru.skypro.homework.dto;

import lombok.*;

/**
 * DTO результата создания и изменения объявления
 * */
@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AdsDto {
    /**
     * Поле - id автора объявления
     * */
    private Integer author;

    /**
     * Поле - фото объявления
     */
    private Byte[] image;

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
