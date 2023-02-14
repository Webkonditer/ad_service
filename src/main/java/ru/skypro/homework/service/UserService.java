package ru.skypro.homework.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.PasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.Images;
import ru.skypro.homework.model.Users;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.ImagesRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.mapper.AdsMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final ImagesRepository imagesRepository;
    public UserService(AdsRepository adsRepository, UserRepository userRepository, AdsMapper adsMapper, ImagesRepository imagesRepository) {
        this.userRepository = userRepository;
        this.imagesRepository = imagesRepository;
    }

    /**
     * Метод берет из авторизации емейл текущего пользователя и достает из бд объект текущего пользователя
     * @return user сущность пользователя
     */
    public Users getUserByEmail() {
        String email = "user1@gmail.com";//Заглушка
        Users user = userRepository.findByEmail(email);
        if(user == null){
            log.info("User not found");
            return null;
        }
        log.info("User found");
        return user;
    }

    /**
     * Метод вносит изменения в объект текущего пользователя на основании поступивших данных UserDto
     * @param userDto поступивший из фронта объект Dto
     * @return user сущность пользователя
     */
    public Users updateUser(UserDto userDto) {
        Users user = userRepository.findById(userDto.getId()).orElse(null);
        if(user == null){
            log.info("User not found");
            return null;
        }
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user.setRegDate(Instant.parse(userDto.getRegDate()));
        user.setCity(userDto.getCity());
        userRepository.save(user);
        return user;
    }

    /**
     * Метод проверяет, совпадает ли поступивший текущий пароль с паролем пользователя. Если совпадает, меняет пароль
     * пользователя на поступивший новый пароль и возвращает true.
     * @param passwordDto поступивший из фронта объект с текущим и новым паролем.
     * @return true или false
     */
    public Boolean setPassword(PasswordDto passwordDto) {
        Users user = getUserByEmail();
        if(user == null){
            log.info("User not found");
            return false;
        }
        if(user.getPassword().equals(passwordDto.getCurrentPassword())) {
            user.setPassword(passwordDto.getNewPassword());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    /**
     * Метод обновляет иконку текущего пользователя
     * @param image картинка из фронтв
     * @return true или false
     * @throws IOException
     */
    public Boolean updateUserImage(MultipartFile image) throws IOException {
        Users user = getUserByEmail();
        if(user == null){
            log.info("User not found");
            return false;
        }
        Path filePath = Path.of("/images", user.getId() + "_avatar." + getExtensions(image.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = image.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Images img = user.getImage();
        img.setImage(filePath.toString());
        imagesRepository.save(img);
        return true;
    }

    /**
     * Метод возвращает расширение файла
     * @param fileName
     * @return
     */
    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
