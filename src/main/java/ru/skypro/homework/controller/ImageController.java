package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ImagesService;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/image")
public class ImageController {
    private final ImagesService imagesService;

    public ImageController(ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<byte[]> updateAdsImage(@PathVariable Integer id,
                                                 @RequestBody MultipartFile image)  {
        if (imagesService.getAdsImageById(id)!=null) {
            return ResponseEntity.ok(imagesService.update(id,image));
        }
            return ResponseEntity.status(404).build();
    }


}