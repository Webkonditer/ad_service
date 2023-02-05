package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/image")
public class ImageController {

    @PatchMapping("/{id}")
    public ResponseEntity<Byte[]> updateAdsImage(@PathVariable Integer id, @RequestBody Byte[] image) {
        if (image!=null) {
            return ResponseEntity.ok(image);
        } else if (false) {
            return ResponseEntity.status(404).build();
        }
        return null;
    }

}