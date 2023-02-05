package ru.skypro.homework.dto;

import com.sun.istack.NotNull;
import lombok.*;

/**
 * DTO для создания и изменения объявления
 * */
@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CreateAds {
    /**
     * Поле - описание объявления
     * */
    @NotNull
    private String description;

    /**
     * Поле - цена товара
     * */
    @NotNull
    private Integer price;

    /**
     * Поле - заголовок объявления
     * */
    @NotNull
    private String title;
}
