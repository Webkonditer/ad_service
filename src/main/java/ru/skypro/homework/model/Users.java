package ru.skypro.homework.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;

/**
 * Класс - сущность пользователя
 */
@Data
@Entity
public class Users {

    /**
     * Поле - id пользователя/автора объявления
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer id;

    /**
     * Поле - адрес электронной почты автора объявления
     */
    private String email;

    /**
     * Поле - пароль пользователя
     */
    private String password;

    /**
     * Поле - имя автора объявления/комментария
     */
    private String firstName;

    /**
     * Поле - фамилия автора объявления/комментария
     */
    private String lastName;

    private String phone;

    @JsonIgnore
    private Instant regDate;

    private String city;

    /**
     * Поле - аватарка
     */
    @OneToOne
    @JoinColumn(name = "image_id")
    private Images image;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Collection<Ads> ads;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Collection<Comments> comments;
}
