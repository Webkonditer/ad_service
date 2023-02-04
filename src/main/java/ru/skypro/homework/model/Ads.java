package ru.skypro.homework.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

/**
 * Класс - сущность объявления
 */
@Data
@Entity
public class Ads {


    /**
     * Поле - счетчик объявлений
     */
    private Integer count;


    /**
     * Поле - primary key - идентификатор объявления
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;


    /**
     * Поле - id автора объявления
     */
    @Column(name = "author")
    private Integer id;


    /**
     * Поле - путь к картинке - визуализация объявления
     */
    private String image;

    /**
     * Поле - описание объявления
     */
    private String description;

    /**
     * Поле - цена товара/услуги из объявления
     */
    private Integer price;

    /**
     * Поле - заголовок объявления
     */
    private String title;

    /**
     * Поле - время создания объявления
     */
    private Instant createdAt;

    /**
     * Поле - комментарий/отзыв пользователя
     */
    private String text;


//    @ManyToOne
//    @JsonIgnore
//    private Comments comments;


}
