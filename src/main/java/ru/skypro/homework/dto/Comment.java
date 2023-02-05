package ru.skypro.homework.dto;

import lombok.*;

/**
 * DTO коментария
 * */
@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    /**
     * Поле - id автора комментария
     * */
    private Integer author;

    /**
     * Поле -
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
