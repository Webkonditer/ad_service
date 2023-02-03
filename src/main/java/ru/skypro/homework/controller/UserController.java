package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.PasswordDto;

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

}
