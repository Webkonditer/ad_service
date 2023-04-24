package ru.skypro.homework.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.model.Comments;

public interface CommentsRepository extends CrudRepository<Comments, Integer> {

    Comments findByAd_PkAndUser(Integer adPk, Integer id);

    Comments findByPk(Integer id);
    Comments findByAd_PkAndPk(Integer adPk, Integer id);


}
