package ru.skypro.homework.service.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comments;
import ru.skypro.homework.model.Users;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CommentsMapper {

//    @Mapping(source = "users.id", target = "id")
//    @Mapping(source = "ads.pk", target = "pk")
//    CommentDto toDto(Comments comments, Users users, Ads ads);
//
//    Comments toModel (CommentDto dto);


}
