package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/image")
public class ImageController {

    @PatchMapping("/{id}")
    public ResponseEntity<byte[]> updateAdsImage(@PathVariable Integer id,
                                                 @RequestBody MultipartFile image) throws IOException {
        if (image!=null) {
            return ResponseEntity.ok(image.getBytes());
        } else if (false) {
            return ResponseEntity.status(404).build();
        }
        return null;
    }

}