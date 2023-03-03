package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Images;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.ImagesService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/image")
public class ImageController {
    private final ImagesService imagesService;
    private final AdsRepository adsRepository;

    public ImageController(ImagesService imagesService,
                           AdsRepository adsRepository) {
        this.imagesService = imagesService;
        this.adsRepository = adsRepository;
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateAdsImage(@PathVariable Integer id,
                                                 @RequestParam MultipartFile multipartFile)
            throws IOException {
        Boolean updateAdImageDone = imagesService.updateAdsImage(multipartFile, id);
        if (updateAdImageDone) {
            return ResponseEntity.ok().build();
        }
            return ResponseEntity.status(404).build();
    }

    @GetMapping(value = "/{id}")
    public void downloadImage(@PathVariable Integer id, HttpServletResponse response)
            throws IOException {
        Ads ads = adsRepository.findById(id).orElse(null);
        if (ads == null) {
            return;
        }
        Images images = ads.getImage();
        Path path = Path.of(images.getImage());
        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(images.getMediaType());
            response.setContentLength(images.getFileSize().intValue());
            is.transferTo(os);
        }
    }

}