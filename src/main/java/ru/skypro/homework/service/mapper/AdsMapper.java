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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AdsMapper {
    CommentDto toCommentDto(Comments comments, Users users);

    @Mapping(source = "images.image", target = "image")
    @Mapping(source = "users.id", target = "author")

     AdsDto toAdsDto(Ads ads, Users users,Images images);
//     {

//        // read entire line as string
//            String line = bf.readLine();
//
//
//            // list that holds strings of a file
//            List<String> listOfStrings
//                    = new ArrayList<String>();
//            try {
//                // load data from file
//                BufferedReader bf = new BufferedReader(
//                        new FileReader(ads.getImage().getImage()));
//
//                // checking for end of file
//            while (line != null) {
//                listOfStrings.add(line);
//                line = bf.readLine();
//            }
//
//            // closing bufferreader object
//            bf.close();
//        } catch (
//                IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//        // storing the data in arraylist to array
//        String[] array
//                = listOfStrings.toArray(new String[0]);
//
//
//        AdsDto adsDto = new AdsDto();
//        adsDto.setImage(array);
//        adsDto.setAuthor(ads.getUser().getId());
//        adsDto.setPrice(ads.getPrice());
//        adsDto.setTitle(ads.getTitle());
//        adsDto.setPk(ads.getPk());
//
//        return adsDto;
//
//    }



    @Mapping(source = "users.id", target = "author")
    AdsCreateDto toAdsCreateDto(Ads ads, Users users);


    Ads toAds(AdsCreateDto dto);

    @Mapping(source = "images.image", target = "image")
    AdsByIdDto toDtoByUserId(Ads ads, Images images, Users users);

    default AdsCommentsDto toAdsCommentsDto(Ads ads) {
        return AdsCommentsDto.builder()
                .count(ads.getComments().size())
                .allResults(ads.getComments()
                        .stream()
                        .map(c -> toCommentDto(c, ads.getUser()))
                        .collect(Collectors.toList()))
                .build();
    }

    default AdsAllDto toAdsAllDto(AdsRepository adsRepository) {
        return AdsAllDto.builder()
                .count(adsRepository.findAll().size())
                .allResults(adsRepository.findAll()
                        .stream()
                        .map(a -> toAdsDto(a,a.getUser(),a.getImage()))
                        .collect(Collectors.toList()))
                .build();
    }


}
