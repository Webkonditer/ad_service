package ru.skypro.homework.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.model.Comments;

import java.util.List;

public interface CommentsRepository extends CrudRepository<Comments, Integer> {

    Comments findByAd_PkAndUser(Integer adPk, Integer id);

    void deleteByAd_PkAndUser(Integer adPk, Integer id);
}
