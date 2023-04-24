package ru.skypro.homework.dto.adsDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AdsByIdDto {

    /**
     * Поле - ссылка на картинку объявления
     */
    private String image;

    /**
     * Поле - фамилия автора объявления
     */
    @JsonProperty("authorLastName")
    private String lastName;

    /**
     * Поле - имя автора объявления
     */
    @JsonProperty("authorFirstName")
    private String firstName;

    /**
     * Поле - телефон автора объявления
     */
    private String phone;

    /**
     * Поле - цена товара/услуги из объявления
     */
    private Integer price;

    /**
     * Поле - описание объявления
     */
    private String description;

    /**
     * Поле - primary key - идентификатор объявления
     */
    private Integer pk;

    /**
     * Поле - заголовок объявления
     */
    private String title;

    /**
     * Поле - адрес эл.почты автора объявления
     */
    private String email;

}
