package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.PasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.Avatars;
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

    @PostMapping("/set_password")
    public ResponseEntity<PasswordDto> setPassword(@RequestBody PasswordDto passwordDto) {
        Boolean setPasswordDone = userService.setPassword(passwordDto);
        if (setPasswordDone) {
            return ResponseEntity.ok(passwordDto);
        }
        return ResponseEntity.status(403).build();

    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser() {
        Users user = userService.getAuthorizedUser();
        if (user == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(UserMapper.INSTANCE.toDto(user));
    }

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

    @PostMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUserImage(@RequestParam MultipartFile image) throws IOException {
        Boolean updateUserImageDone = userService.updateUserImage(image);
        if (updateUserImageDone) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(404).build();
    }

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
