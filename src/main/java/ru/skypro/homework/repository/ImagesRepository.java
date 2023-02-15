package ru.skypro.homework.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.model.Images;

public interface ImagesRepository extends CrudRepository<Images, Integer> {

    Images findImagesByAds_Pk(Integer id);

}
