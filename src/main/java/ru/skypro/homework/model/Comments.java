package ru.skypro.homework.model;


import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
public class Comments {


    /**
     * Поле - primary key - идентификатор комментария
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    /**
     * Поле - время создания комментария
     */
    private Instant createdAt;

    /**
     * Поле - комментарий пользователя/отзыв
     */
    private String text;

//    @ManyToOne
//    @JsonIgnore
//    @Column(name = "author")
//    private Users user;
//
//    @ManyToOne
//    @JsonIgnore
//    private Ads ads;

}
