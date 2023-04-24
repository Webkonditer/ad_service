package ru.skypro.homework.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.skypro.homework.Role;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;
import java.util.Set;

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
    @Column(name="username")
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

    /**
     * Телефон
     */
    private String phone;

    /**
     * Дата регистрации
     */
    @JsonIgnore
    private Instant regDate;

    /**
     * Город
     */
    private String city;

    /**
     * Поле - аватарка
     */
    @OneToOne
    @JoinColumn(name = "avatar_id")
    private Avatars avatar;

    /**
     * Объявления пользователя
     */
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @ToString.Exclude
    private Collection<Ads> ads;

    /**
     * Комментарии пользователя
     */
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Collection<Comments> comments;

}
