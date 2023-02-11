package ru.skypro.homework.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Images {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Поле - ссылка на картинку (аватар пользователя или визуализация объявления)
     */
    private String image;

//    @OneToOne
//    @JsonIgnore
//    private Users user;
//
//    @OneToOne
//    @JsonIgnore
//    private Ads ads;

}
