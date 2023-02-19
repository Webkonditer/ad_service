package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.model.Images;
import ru.skypro.homework.model.Users;

public interface ImagesRepository extends CrudRepository<Images, Integer> {

    Images findImagesByAds_Pk(Integer id);

}
