package ru.skypro.homework.dto.adsDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AdsByIdDto {

    /**
     * Поле - ссылка на фото объявления
     */
    private String image;

    @JsonProperty("authorLastName")
    private String lastName;

    @JsonProperty("authorFirstName")
    private String firstName;

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


    private String email;

}
