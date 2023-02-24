package ru.skypro.homework.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Images;
import ru.skypro.homework.repository.ImagesRepository;
import ru.skypro.homework.service.mapper.AdsMapper;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ImagesService {
//    @Value("${images.location}")
//    private String path;
    private final ImagesRepository imagesRepository;
    public ImagesService(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    public Images getById(Integer imageId) {
        log.info("Was invoked method for get image by id");
        Images image = imagesRepository.findById(imageId).orElse(null);
        if (image == null) {
            log.warn("Getting image = null");
        }
        log.debug("Getting image = {}",image);
        return image;
    }

    public Images create(MultipartFile multipartFile) {
        log.info("Was invoked method for create image");
        String fileName = /*path + */multipartFile.getOriginalFilename();
//
//        try {
////            multipartFile.transferTo(new File(fileName));
////            FileOutputStream out = new FileOutputStream(fileName);
////            out.write(multipartFile.getBytes());
////            out.close();
//            Images image = new Images();
//            image.setImage(fileName);
//            return imagesRepository.save(image);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }

        Images image = new Images();
        image.setImage(fileName);
        return imagesRepository.save(image);
    }

    public void delete(Integer imageId) throws RuntimeException {
        log.info("Was invoked method for delete image by id");
        if (getById(imageId) != null) {
            imagesRepository.deleteById(imageId);
        } else {
            throw new RuntimeException("Image not found");
        }
    }

    public Images update(Integer imageId, String image) {
        log.info("Was invoked method for update image");
        return imagesRepository.update(imageId, image);
//        try {
//            FileOutputStream out = new FileOutputStream(getById(imageId).getImage());
//            out.write(multipartFile.getBytes());
//            out.close();
//            return multipartFile.getBytes();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
    }

    public Images getAdsImageById(Integer imageId) {
        log.info("Was invoked method for check ads image by id");
        Images image = imagesRepository.findById(imageId).orElse(null);
        if (image == null || image.getAds() == null) {
            log.error("There is not ads image with id = {}",imageId);
        }
        return image;
    }

}
