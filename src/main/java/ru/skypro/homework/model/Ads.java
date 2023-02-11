package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;

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
    @Column(name="ad_id")
    private Integer pk;


    /**
     * Поле - связь объявление с сущностью User
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private Users user;


    /**
     * Поле - картинка - визуализация объявления
     */
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "image_id")
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
    @JsonIgnore
    private Instant createdAt;

    /**
     * Поле - комментарий/отзыв пользователя
     */
    @OneToMany(mappedBy = "ad")
    @JsonIgnore
    private Collection<Comments> comments;




}
