package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Users;

import java.util.List;

public interface UserRepository extends CrudRepository<Users, Integer> {

    @Query(value = "SELECT * from users where email = ?1", nativeQuery = true)
    Users findByEmail(String email);

}
