package ru.skypro.homework.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

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
//    @JsonIgnore
    @JoinColumn(name = "user_id")
    private Users user;


    /**
     * Поле - картинка - визуализация объявления
     */
    @OneToOne
//    @JsonIgnore
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
    private Instant createdAt;

    /**
     * Поле - список комментариев/отзывов пользователя
     */
    @OneToMany(mappedBy = "ad", cascade = CascadeType.REMOVE)
    private List<Comments> comments;

}
