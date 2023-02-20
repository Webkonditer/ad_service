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

}
