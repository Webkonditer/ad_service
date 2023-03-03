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
     * Поле - ссылка на контроллер аватарок для фронта
     */
    @Column(name="link_for_front")
    private String linkForFront;

    @Column(name="file_size")
    private Long fileSize;

    @Column(name="media_type")
    private String mediaType;
}
