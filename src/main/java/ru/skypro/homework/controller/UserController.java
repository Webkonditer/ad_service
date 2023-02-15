package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.PasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.Users;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.mapper.UserMapper;

import java.io.IOException;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/set_password")
    public ResponseEntity<PasswordDto> setPassword(@RequestBody PasswordDto passwordDto){
        Boolean setPasswordDone = userService.setPassword(passwordDto);
        if(setPasswordDone){
            return ResponseEntity.ok(passwordDto);
        } else if(false){
            return ResponseEntity.status(401).build();
        }else if(false){
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.status(404).build();

    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser(){
        Users user = userService.getUserByEmail();
        if(user == null){
            return ResponseEntity.status(404).build();
        } else if(false){
            return ResponseEntity.status(401).build();
        }else if(false){
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(UserMapper.INSTANCE.toDto(user));
    }

    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto){
        Users user = userService.updateUser(userDto);
        if(user == null){
            return ResponseEntity.status(404).build();
        } else if(false){
            return ResponseEntity.status(204).build();
        } else if(false){
            return ResponseEntity.status(401).build();
        }else if(false){
            return ResponseEntity.status(403).build();
        }else
            return ResponseEntity.ok(UserMapper.INSTANCE.toDto(user));
    }

    @PostMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUserImage(@RequestParam MultipartFile image) throws IOException {
        Boolean updateUserImageDone = userService.updateUserImage(image);
        if(updateUserImageDone){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(404).build();
    }

}
