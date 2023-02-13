package ru.skypro.homework.service.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.adsDto.*;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Images;
import ru.skypro.homework.model.Users;

import java.time.Instant;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {CommentsMapper.class})
public interface AdsMapper {

    @Mapping(source = "images.image", target = "image")
    @Mapping(source = "users.id", target = "author")
    AdsDto toDto(Ads ads, Images images, Users users);

    Ads toModel(AdsCreateDto dto);


    @Mapping(source = "images.image", target = "image")
//    @Mapping(source = "users.firstName", target = "firstName")
//    @Mapping(source = "users.lastName", target = "lastName")
//    @Mapping(source = "users.phone", target = "phone")
//    @Mapping(source = "users.email", target = "email")
    AdsByUserIdDto toDtoByUserId(Ads ads, Images images, Users users);

//    Ads toModel(AdsByUserIdDto dto);

// тут пока я не знаю, как сделать
//    default void toDtoAdsComments(Ads ads) {
//        AdsCommentsDto.count = ads.getComments().size();
//    }

    ;


}
