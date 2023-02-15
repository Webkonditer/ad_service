package ru.skypro.homework.service.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.adsDto.*;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comments;
import ru.skypro.homework.model.Images;
import ru.skypro.homework.model.Users;
import ru.skypro.homework.repository.AdsRepository;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AdsMapper {
    CommentDto toCommentDto(Comments comments, Users users);
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
    default AdsAllDto toAdsAllDto(AdsRepository adsRepository) {
        return AdsAllDto.builder()
                .count(adsRepository.findAll().size())
                .allResults(adsRepository.findAll()
                        .stream()
                        .map(a-> toAdsDto(a,a.getImage(),a.getUser()))
                        .collect(Collectors.toList()))
                .build();
    }
}
