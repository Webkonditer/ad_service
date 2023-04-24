package ru.skypro.homework.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.model.Avatars;

import java.util.Optional;

public interface AvatarsRepository extends CrudRepository<Avatars, Integer> {
}
