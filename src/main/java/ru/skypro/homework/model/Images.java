package ru.skypro.homework.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
public class Images {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="image_id")
    private Integer id;

    /**
     * Поле - ссылка на картинку (аватар пользователя или визуализация объявления)
     */
    @Column(name="image_link")
    private String image;

    @OneToOne(mappedBy = "image")
    @ToString.Exclude
    private Ads ads;

    /**
     * Поле - ссылка на контроллер кортинок для фронта
     */
    @Column(name="link_for_front")
    private String linkForFront;

    @Column(name="file_size")
    private Long fileSize;

    @Column(name="media_type")
    private String mediaType;


}
