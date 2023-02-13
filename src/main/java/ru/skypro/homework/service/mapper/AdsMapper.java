package ru.skypro.homework.service.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.adsDto.AdsByIdDto;
import ru.skypro.homework.dto.adsDto.AdsCommentsDto;
import ru.skypro.homework.dto.adsDto.AdsCreateDto;
import ru.skypro.homework.dto.adsDto.AdsDto;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Images;
import ru.skypro.homework.model.Users;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AdsMapper extends CommentsMapper{

    @Mapping(source = "images.image", target = "image")
    @Mapping(source = "users.id", target = "author")
    AdsDto toAdsDto(Ads ads, Images images, Users users);

    Ads toAds(AdsCreateDto dto);

    @Mapping(source = "images.image", target = "image")
    AdsByIdDto toDtoByUserId(Ads ads, Images images, Users users);

    default AdsCommentsDto toAdsCommentsDto(Ads ads) {
        return AdsCommentsDto.builder()
                .count(ads.getComments().size())
                .allResults(ads.getComments()
                        .stream()
                        .map(c-> toCommentDto(c,ads.getUser()))
                        .collect(Collectors.toList()))
                .build();
    }
}
