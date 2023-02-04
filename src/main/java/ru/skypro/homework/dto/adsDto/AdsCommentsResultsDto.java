package ru.skypro.homework.dto.adsDto;

import lombok.Data;

import java.time.Instant;

@Data
public class AdsCommentsResultsDto {

    /**
     * Поле - время создания объявления
     */
    private Instant createdAt;

    /**
     * Поле - id пользователя/автора объявления
     */
    private Integer id;

    /**
     * Поле - primary key - идентификатор объявления
     */
    private Integer pk;

    /**
     * Поле - комментарий пользователя/отзыв
     */
    private String text;


}
