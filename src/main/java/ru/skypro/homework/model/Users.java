package ru.skypro.homework.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
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
    private Integer id;

    /**
     * Поле - адрес электронной почты автора объявления
     */
    private String email;

    /**
     * Поле - имя автора объявления/комментария
     */
    private String firstName;

    /**
     * Поле - фамилия автора объявления/комментария
     */
    private String lastName;

    private String phone;

    private String regDate;

    private String city;

    private String image;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Collection<Ads> ads;
}
