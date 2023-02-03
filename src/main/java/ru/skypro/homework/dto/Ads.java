package ru.skypro.homework.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

/**
 * Класс - сущность объявления
 * */
@Data
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Ads {


    /**
     * Поле - primary key - идентификатор объявления
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    /**
     * Поле - счетчик объявлений
     */
    private Integer count;

    /**
     * Поле - id автора объявления
     * */
    private Long author;

    /**
     * Поле - имя автора объявления
     * */
    private String authorFirstName;

    /**
     * Поле - фамилия автора объявления
     * */
    private String authorLastName;

    /**
     * Поле - описание объявления
     * */
    private String description;

    /**
     * Поле - адрес электронной почты автора объявления
     * */
    private String email;

    /**
     * Поле - картинка - визуализация объявления
     * */
    private byte[] image;

    /**
     * Поле - цена товара/услуги из объявления
     * */
    private Integer price;

    /**
     * Поле - заголовок объявления
     * */
    private String title;

    /**
     * Поле - время создания объявления
     * */
    private Instant createdAt;

    /**
     * Поле - комментарий/отзыв пользователя
     * */
    private String text;



}
