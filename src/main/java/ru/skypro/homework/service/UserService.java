package ru.skypro.homework.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.adsDto.AdsDto;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Users;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.mapper.AdsMapper;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    public UserService(AdsRepository adsRepository, UserRepository userRepository, AdsMapper adsMapper) {
        this.userRepository = userRepository;
    }
    public Users getUserByEmail() {
        String email = "user@gmail.com";//Заглушка
        Users user = userRepository.findByEmail(email);
        if(user == null){
            log.info("User not found");
            return null;
        }
        log.info("User found");
        return user;
    }

    public void updateUser(UserDto userDto) {
        Users user = userRepository.findById(userDto.getId()).orElse(null);
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        //user.setRegDate(userDto.getRegDate());
    }
}
