package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.adsDto.AdsByIdDto;
import ru.skypro.homework.dto.adsDto.AdsDto;
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

/**
 * Контроллер для работы с изображениями для объявлений
 */
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


    @Operation(
            summary = "Обновление картинки в объявлении по id объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Картинка у объявления обновлена, ссылка на нее сохранена в БД",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = String.class))
                    )
            }, tags = "Images",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(implementation = MultipartFile.class))
            )

    )
    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateAdsImage(@Parameter(description = "id объявления", example = "8")
                                                 @PathVariable Integer id,
                                                 @RequestParam MultipartFile multipartFile)
            throws IOException {
        Boolean updateAdImageDone = imagesService.updateAdsImage(multipartFile, id);
        if (updateAdImageDone) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(404).build();
    }


    @Operation(
            summary = "Получение картинки по id объявления для фронта",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получена картинка по id объявления",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Images.class))
                    )
            }, tags = "Images"
    )
    @GetMapping(value = "/{id}")
    public void downloadImage(@Parameter(description = "id объявления", example = "8")
                              @PathVariable Integer id,
                              HttpServletResponse response)
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