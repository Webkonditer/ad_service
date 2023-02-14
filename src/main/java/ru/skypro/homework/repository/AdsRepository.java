package ru.skypro.homework.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.model.Ads;

import java.util.List;

public interface AdsRepository extends CrudRepository<Ads, Integer> {
List<Ads> findAll();
}
