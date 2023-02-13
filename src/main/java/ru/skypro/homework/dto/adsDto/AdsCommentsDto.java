package ru.skypro.homework.dto.adsDto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.skypro.homework.dto.CommentDto;

import java.util.List;

@Data
public class AdsCommentsDto {


    /**
     * Поле - счетчик комментариев
     */
    private Integer count;

    /**
     * Поле - массив размещенных объявлений
     */
    @JsonProperty("results")
    private List<CommentDto> allResults;

}
