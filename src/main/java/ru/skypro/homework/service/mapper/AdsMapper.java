package ru.skypro.homework.service.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;;
import ru.skypro.homework.dto.adsDto.AdsCreateDto;
import ru.skypro.homework.dto.adsDto.AdsDto;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Images;
import ru.skypro.homework.model.Users;

import java.time.Instant;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AdsMapper {

    @Mapping(source = "images.image", target = "image")
    @Mapping(source = "users.id", target = "author")
    AdsDto toDto(Ads ads, Images images, Users users);

    Ads toModel(AdsCreateDto dto);
}
