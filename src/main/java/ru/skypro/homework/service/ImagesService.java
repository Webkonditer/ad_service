package ru.skypro.homework.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Images;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.ImagesRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

/**
 * Сервис для работы с картинками для объявлений
 */
@Service
@Slf4j
public class ImagesService {
    private final AdsRepository adsRepository;
    private final ImagesRepository imagesRepository;

    public ImagesService(ImagesRepository imagesRepository,
                         AdsRepository adsRepository) {
        this.imagesRepository = imagesRepository;
        this.adsRepository = adsRepository;
    }

    /**
     * Метод для получения картинки
     *
     * @param imageId id картинки
     */
    public Images getById(Integer imageId) {
        log.info("Was invoked method for get image by id");
        Images image = imagesRepository.findById(imageId).orElse(null);
        if (image == null) {
            log.warn("Getting image = null");
        }
        log.debug("Getting image = {}", image);
        return image;
    }


    /**
     * Метод для создания картинки для объявления
     *
     * @param multipartFile файл, содержащий картинку для объявления
     */
    public Images create(MultipartFile multipartFile) {
        log.info("Was invoked method for create image");
        String fileName = /*path + */multipartFile.getOriginalFilename();

        Images image = new Images();
        image.setImage(fileName);
        return imagesRepository.save(image);
    }


    /**
     * Метод обновляет картинку нового объявления
     *
     * @param image картинка из фронта
     * @param id    - id объявления
     * @return true или false
     * @throws IOException
     */
    public Boolean updateAdsImage(MultipartFile image, Integer id) throws IOException {

        Ads ads = adsRepository.findById(id).orElse(null);
        if (ads == null) {
            log.info("Ad not found");
            return false;
        }
        Path filePath = Path.of("/images", ads.getPk() + "_image." +
                getExtensions(Objects.requireNonNull(image.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = image.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Images images = ads.getImage();
        if (images == null) {
            images = new Images();
            images.setAds(ads);
        }

        images.setImage(filePath.toString());
        images.setLinkForFront("/image/" + ads.getPk());
        images.setFileSize(image.getSize());
        images.setMediaType(image.getContentType());
        images = imagesRepository.save(images);
        ads.setImage(images);
        adsRepository.save(ads);
        return true;
    }

    /**
     * Метод возвращает расширение файла
     *
     * @param fileName
     * @return
     */
    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}

