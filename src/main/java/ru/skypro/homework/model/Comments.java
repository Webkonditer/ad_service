package ru.skypro.homework.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name="comment_id")
    private Integer pk;

    /**
     * Поле - время создания комментария
     */
    @JsonIgnore
    private Instant createdAt;

    /**
     * Поле - комментарий пользователя/отзыв
     */
    @Column(name="comment_text")
    private String text;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ad_id")
    private Ads ad;

}
