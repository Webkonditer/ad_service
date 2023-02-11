package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * DTO коментария
 * */
@Data
@NoArgsConstructor
public class CommentDto {

    /**
     * Поле - id автора комментария
     * */
    @JsonProperty("author")
    private Integer id;

    /**
     * Поле - время создания
     * */
    private String createdAt;

    /**
     * Поле - идентификатор объявления
     */
    private Integer pk;

    /**
     * Поле - текст комментария
     * */
    private String text;
}
