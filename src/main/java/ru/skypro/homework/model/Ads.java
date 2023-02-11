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
     * Поле - primary key - идентификатор объявления
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;


    /**
     * Поле - связь объявление с сущностью User
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;


    /**
     * Поле - картинка - визуализация объявления
     */
    @OneToOne
    private Images image;

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




}
