package ru.skypro.homework.dto.adsDto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("author")
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
