package ru.skypro.homework.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.PasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.Avatars;
import ru.skypro.homework.model.Users;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.AvatarsRepository;
import ru.skypro.homework.repository.ImagesRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.mapper.AdsMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

import static java.nio.file.StandardOpenOption.CREATE_NEW;


/**
 * Сервис для работы с сущностями пользователей
 */
@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final AvatarsRepository avatarsRepository;
    private final UserDetailsManager manager;

    public UserService(AdsRepository adsRepository, UserRepository userRepository, AdsMapper adsMapper, ImagesRepository imagesRepository, AvatarsRepository avatarsRepository, UserDetailsManager manager) {
        this.userRepository = userRepository;
        this.avatarsRepository = avatarsRepository;
        this.manager = manager;
    }

    /**
     * Метод берет из авторизации username текущего пользователя и достает из бд объект текущего пользователя
     * @return user сущность пользователя
     */
    public Users getAuthorizedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
        // }
        Users user = userRepository.findByEmail(currentUserName);
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
        Users user = getAuthorizedUser();
        if(user == null){return null;}
        if(userDto.getEmail() != null) {user.setEmail(userDto.getEmail());}
        if(userDto.getFirstName() != null) {user.setFirstName(userDto.getFirstName());}
        if(userDto.getLastName() != null) {user.setLastName(userDto.getLastName());}
        if(userDto.getPhone() != null) {user.setPhone(userDto.getPhone());}
        if(userDto.getRegDate() != null) {user.setRegDate(Instant.parse(userDto.getRegDate()));}
        if(userDto.getCity() != null) {user.setCity(userDto.getCity());}
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
        Users user = getAuthorizedUser();

        String encryptedPassword = user.getPassword();
        String encryptedPasswordWithoutEncryptionType = encryptedPassword.substring(8);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);
        if(!encoder.matches(passwordDto.getCurrentPassword(), encryptedPasswordWithoutEncryptionType)) {
            log.info("Invalid password");
            return false;
        }
        String encodedPassword = "{bcrypt}" + encoder.encode(passwordDto.getNewPassword());
        manager.changePassword(passwordDto.getCurrentPassword(), encodedPassword);
        return true;

    }

    /**
     * Метод обновляет иконку текущего пользователя
     * @param image картинка из фронтв
     * @return true или false
     * @throws IOException
     */
    public Boolean updateUserImage(MultipartFile image) throws IOException {
        Users user = getAuthorizedUser();
        if(user == null){
            log.info("User not found");
            return false;
        }
        Path filePath = Path.of("/avatars", user.getId() + "_avatar." + getExtensions(image.getOriginalFilename()));
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
        Avatars avr = user.getAvatar();
        if(avr == null) {
            avr = new Avatars();
            avr.setUser(user);
        }
        avr.setAvatar(filePath.toString());
        avr.setLinkForFront("/users/avatar/" + user.getId());
        avr.setFileSize(image.getSize());
        avr.setMediaType(image.getContentType());
        avatarsRepository.save(avr);
        user.setAvatar(avr);
        userRepository.save(user);
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
