package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.PasswordDto;
import ru.skypro.homework.dto.UserDto;

@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping("/set_password")
    public ResponseEntity<PasswordDto> setPassword(@RequestBody PasswordDto passwordDto){
        if(true){
            return ResponseEntity.ok(passwordDto);
        } else if(false){
            return ResponseEntity.status(401).build();
        }else if(false){
            return ResponseEntity.status(403).build();
        }else if(false){
            return ResponseEntity.status(404).build();
        }
        return null;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser(){
        if(true){
            return ResponseEntity.ok(new UserDto());
        } else if(false){
            return ResponseEntity.status(401).build();
        }else if(false){
            return ResponseEntity.status(403).build();
        }else if(false){
            return ResponseEntity.status(404).build();
        }
        return null;
    }

    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto){
        if(true){
            return ResponseEntity.ok(userDto);
        } else if(false){
            return ResponseEntity.status(204).build();
        } else if(false){
            return ResponseEntity.status(401).build();
        }else if(false){
            return ResponseEntity.status(403).build();
        }else if(false){
            return ResponseEntity.status(404).build();
        }
        return null;
    }

}
