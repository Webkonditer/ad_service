package ru.skypro.homework.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Avatars {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="avatar_id")
    private Integer id;

    /**
     * Поле - ссылка на картинку (аватар пользователя или визуализация объявления)
     */
    @Column(name="avatar_link")
    private String avatar;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    /**
     * Поле - ссылка на контроллер для получения аватарок для фронта
     */
    @Column(name="link_for_front")
    private String linkForFront;


    /**
     * Поле - ссылка на контроллер для получения размера аватарок для фронта
     */
    @Column(name="file_size")
    private Long fileSize;

    /**
     * Поле - ссылка на контроллер для получения типа файла аватарки для фронта
     */
    @Column(name="media_type")
    private String mediaType;
}
