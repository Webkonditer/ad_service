package ru.skypro.homework.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.adsDto.*;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comments;
import ru.skypro.homework.model.Images;
import ru.skypro.homework.model.Users;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentsRepository;
import ru.skypro.homework.repository.ImagesRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.mapper.AdsMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AdsService {
    private final UserService userService;
    private final CommentsRepository commentsRepository;
    private final AdsRepository adsRepository;
    private final AdsMapper adsMapper;
    private final ImagesService imageService;

    public AdsService(AdsRepository adsRepository, AdsMapper adsMapper,
                      CommentsRepository commentsRepository,
                      UserService userService,
                      ImagesService imageService) {
        this.adsRepository = adsRepository;
        this.adsMapper = adsMapper;
        this.commentsRepository = commentsRepository;
        this.userService = userService;
        this.imageService = imageService;
    }

    public AdsDto getById(Integer adsId) {
        log.info("Was invoked method for get adsDto by id");
        Ads ad = adsRepository.findById(adsId).get();
        return adsMapper.toAdsDto(ad);
    }

    public AdsAllDto getAllAds() {
        return adsMapper.toAdsAllDto(adsRepository);
    }

    public AdsCommentsDto getAdsComments(Integer adsId) {
        log.info("Was invoked method for get adsComments by adsId");
        Ads ad = adsRepository.findById(adsId).get();
        return adsMapper.toAdsCommentsDto(ad);
    }

    public AdsDto updateAds(Integer id, AdsCreateDto adsCreateDto) {
        if (!adsRepository.existsById(id)) {
            return null;
        }
        Ads ad = adsRepository.findById(id).get();
        ad.setDescription(adsCreateDto.getDescription());
        ad.setPrice(adsCreateDto.getPrice());
        ad.setTitle(adsCreateDto.getTitle());
        adsRepository.save(ad);
        return adsMapper.toAdsDto(ad);
    }

    public CommentDto getComment(Integer adPk, Integer id) {
        log.info("Was invoked method for get Comments by adId and authorId");
        Users user = userService.getUserByEmail();
        Comments comment = commentsRepository.findByAd_PkAndUser(adPk, id);

        return adsMapper.toCommentDto(comment, user);
    }


    public void deleteComment(Integer adPk, Integer id) {
        log.info("Was invoked method for delete Comment by adId and authorId");
        commentsRepository.deleteByAd_PkAndUser(adPk, id);

    }

    public AdsByIdDto getAds(Integer id) {
        log.info("Was invoked method for get Ads by id");
        Ads ad = adsRepository.findById(id).get();
        return adsMapper.toDtoByUserId(ad, ad.getImage(), ad.getUser());
    }


    public CommentDto updateComment(Integer adPk, Integer id, CommentDto commentDto) {
        log.info("Was invoked method for update Comment");
        if (!commentsRepository.existsById(id)) {
            return null;
        }
        Comments comment = commentsRepository.findByAd_PkAndUser(adPk, id);
        comment.setText(commentDto.getText());
        comment.setCreatedAt(Instant.parse(commentDto.getCreatedAt()));
        commentsRepository.save(comment);
        return adsMapper.toCommentDto(comment, comment.getUser());
    }

    public AdsDto addAds(AdsCreateDto adsCreateDto, MultipartFile image) {
        log.info("Was invoked method for create ad");

        Ads ad = new Ads();
        ad.setDescription(adsCreateDto.getDescription());
        ad.setPrice(adsCreateDto.getPrice());
        ad.setTitle(adsCreateDto.getTitle());

        ad.setCreatedAt(Instant.now());
        ad.setUser(userService.getUserByEmail());

        log.warn("Начало сохранения картинки");
        ad.setImage(imageService.create(image));

        log.warn("Картинка сохранена");
        adsRepository.save(ad);






//        URL website = new URL("95.31.175.90/information.asp");
//        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
//        FileOutputStream fos = new FileOutputStream("information.html");
//        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);



        return adsMapper.toAdsDto(ad);
    }

    public CommentDto addAdsComments(Integer adPk, CommentDto commentDto) {
        log.info("Was invoked method for add comment for ad");

        Optional<Ads> ad = adsRepository.findById(adPk);
        Comments comment = new Comments();
        comment.setUser(userService.getUserByEmail());
        comment.setCreatedAt(Instant.now());
        comment.setText(commentDto.getText());
        comment.setAd(adsRepository.findById(commentDto.getPk()).get());
        List<Comments> commentsList =  ad.get().getComments();
        commentsList.add(comment);

        return adsMapper.toCommentDto(comment, comment.getUser());
    }
}
