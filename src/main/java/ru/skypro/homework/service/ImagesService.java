package ru.skypro.homework.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Images;
import ru.skypro.homework.repository.ImagesRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Slf4j
public class ImagesService {

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

//    public Images create(Images image) {
//        log.info("Was invoked method for create image");
//        return imagesRepository.save(image);
//    }

    public void delete(Integer imageId) throws RuntimeException {
        log.info("Was invoked method for delete image by id");
        if (getById(imageId) != null) {
            imagesRepository.deleteById(imageId);
        } else {
            throw new RuntimeException("Image not found");
        }
    }

    public byte[] update(Integer imageId, MultipartFile multipartFile) {
        log.info("Was invoked method for update image");
        try {
            FileOutputStream out = new FileOutputStream(getById(imageId).getImage());
            out.write(multipartFile.getBytes());
            out.close();
            return multipartFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Images getAdsImageById(Integer imageId) {
        log.info("Was invoked method for check ads image by id");
        Images image = imagesRepository.findById(imageId).orElse(null);
        if (image == null || image.getAds() == null) {
            log.error("There is not ads image with id = {}",imageId);
        }
        return image;
    }

/** Метод для создания объекта image из поступившего multipartFile
 * */
    public Images createImage (MultipartFile multipartFile) throws IOException {
        log.info("Was invoked method for create image from multipartFile");

        if(multipartFile == null){
            log.info("file not exist");
            return null;
        }

        Path filePath = Path.of("/images", Instant.now().toString() +
                "_image." + getExtensions(Objects.requireNonNull(multipartFile.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = multipartFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
        Images img = new Images();
        img.setImage(filePath.toString());

//??
        img.setLinkForFront("/ads/image/"); // дописать - что пишем, если создаем новое объявление?
        img.setFileSize(multipartFile.getSize());
        img.setMediaType(multipartFile.getContentType());
        return imagesRepository.save(img);

    }

    /**
     * Метод возвращает расширение файла
     * @param fileName - имя входящего файла
     * @return - возвращается расширение файла
     */
    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}

