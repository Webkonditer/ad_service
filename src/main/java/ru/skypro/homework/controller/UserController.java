package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.PasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.adsDto.AdsAllDto;
import ru.skypro.homework.dto.adsDto.AdsDto;
import ru.skypro.homework.model.Avatars;
import ru.skypro.homework.model.Images;
import ru.skypro.homework.model.Users;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.mapper.UserMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * Контроллер для работы с сущностью пользователя (User)
 */
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @Operation(
            summary = "Установление пароля пользователю",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пароль пользователю установлен и в зашифрованном виде сохранен в БД",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PasswordDto.class))
                    )
            }, tags = "Users",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PasswordDto.class)
                    )
            )
    )
    @PostMapping("/set_password")
    public ResponseEntity<PasswordDto> setPassword(@RequestBody PasswordDto passwordDto) {
        Boolean setPasswordDone = userService.setPassword(passwordDto);
        if (setPasswordDone) {
            return ResponseEntity.ok(passwordDto);
        }
        return ResponseEntity.status(403).build();

    }


    @Operation(
            summary = "Получение из БД пользователя в зависимости от введенных данных для авторизации",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получена сущность авторизованного пользователя из БД",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Если пользователь не найден"
                    )
            }, tags = "Users"
    )
    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser() {
        Users user = userService.getAuthorizedUser();
        if (user == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(UserMapper.INSTANCE.toDto(user));
    }


    @Operation(
            summary = "Обновление данных пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Данные пользователя обновлены и сохранены в БД",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDto.class))
                    )
            }, tags = "Users",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class))
            )

    )
    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        Users user = userService.updateUser(userDto);
        if (user == null) {
            return ResponseEntity.status(404).build();
        } else if (false) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.ok(UserMapper.INSTANCE.toDto(user));
    }


    @Operation(
            summary = "Установка/обновление аватара пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Аватар пользователя установлен. Путь к нему сохранен в БД",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = String.class))
                    )
            }, tags = "Users",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(implementation = MultipartFile.class))
            )

    )
    @PostMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUserImage(@RequestParam MultipartFile image) throws IOException {
        Boolean updateUserImageDone = userService.updateUserImage(image);
        if (updateUserImageDone) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(404).build();
    }


    @Operation(
            summary = "Получение аватара по id пользователя для фронта",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получен аватар по id пользователя",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Images.class))
                    )
            }, tags = "Users"
    )
    @GetMapping(value = "/avatar/{id}")
    public void downloadAvatar(@PathVariable Integer id,
                               HttpServletResponse response) throws IOException {
        Users user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return;
        }
        Avatars avatar = user.getAvatar();
        Path path = Path.of(avatar.getAvatar());
        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength(avatar.getFileSize().intValue());
            is.transferTo(os);
        }
    }
}
