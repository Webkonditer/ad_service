package ru.skypro.homework.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CommentDto;
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

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AdsService {
    private final ImagesRepository imagesRepository;
    private final UserRepository userRepository;
    private final CommentsRepository commentsRepository;
    private final AdsRepository adsRepository;
    private final AdsMapper adsMapper;

    private final ImagesService imagesService;

    public AdsService(AdsRepository adsRepository, AdsMapper adsMapper,
                      CommentsRepository commentsRepository,
                      UserRepository userRepository,
                      ImagesRepository imagesRepository, ImagesService imagesService) {
        this.adsRepository = adsRepository;
        this.adsMapper = adsMapper;
        this.commentsRepository = commentsRepository;
        this.userRepository = userRepository;
        this.imagesRepository = imagesRepository;
        this.imagesService = imagesService;
    }

    public AdsDto getById(Integer adsId) {
        log.info("Was invoked method for get adsDto by id");
        Ads ad = adsRepository.findById(adsId).orElse(null);
        if (ad == null) {
            log.info("Ad not found");
            return null;
        }
        return adsMapper.toAdsDto(ad, ad.getImage(), ad.getUser());
    }

    public AdsAllDto getAllAds() {
        return adsMapper.toAdsAllDto(adsRepository);
    }

    public AdsCommentsDto getAdsComments(Integer adsId) {
        log.info("Was invoked method for get adsComments by adsId");
        Ads ad = adsRepository.findById(adsId).orElse(null);
        if (ad == null) {
            log.info("Ad not found");
            return null;
        }
        return adsMapper.toAdsCommentsDto(ad);
    }

    public AdsDto updateAds(Integer id, AdsCreateDto adsCreateDto) {
        if (!adsRepository.existsById(id)) {
            return null;
        }
        Ads ad = adsRepository.findById(id).orElse(null);
        if (ad == null) {
            log.info("Ad not found");
            return null;
        }
        ad.setDescription(adsCreateDto.getDescription());
        ad.setPrice(adsCreateDto.getPrice());
        ad.setTitle(adsCreateDto.getTitle());
        adsRepository.save(ad);
        return adsMapper.toAdsDto(ad, ad.getImage(), ad.getUser());
    }

    public CommentDto getComment(Integer adPk, Integer id) {
        log.info("Was invoked method for get Comments by adId and authorId");
        Users user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.info("User not found");
            return null;
        }
        Comments comment = commentsRepository.findByAd_PkAndUser(adPk, id);

        return adsMapper.toCommentDto(comment, user);
    }


    public void deleteComment(Integer adPk, Integer id) {
        log.info("Was invoked method for delete Comment by adId and authorId");
        commentsRepository.deleteByAd_PkAndUser(adPk, id);

    }

    public AdsByIdDto getAds(Integer id) {
        log.info("Was invoked method for get Ads by id");
        Ads ad = adsRepository.findById(id).orElse(null);
        if (ad == null) {
            log.info("Ad not found");
            return null;
        }
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

    public AdsCreateDto addAds(AdsCreateDto adsCreateDto, MultipartFile multipartFile) throws IOException {
        log.info("Was invoked method for create ad");


        Images image = imagesService.createImage(multipartFile);

        Ads ad = new Ads();
        ad.setDescription(adsCreateDto.getDescription());
        ad.setPrice(adsCreateDto.getPrice());
        ad.setTitle(adsCreateDto.getTitle());
        ad.setImage(image);
        ad.setCreatedAt(Instant.now());

        Optional<Users> user = userRepository.findById(adsCreateDto.getAuthor());
        if (user.isEmpty()) {
            log.info("User not found");
            return null;
        }
        ad.setUser(user.get());
        adsRepository.save(ad);
        return adsMapper.toAdsCreateDto(ad, user.get());
    }

    public CommentDto addAdsComments(Integer adPk, CommentDto commentDto) {
        log.info("Was invoked method for add comment for ad");

        Optional<Ads> ad = adsRepository.findById(adPk);
        Comments comment = new Comments();
        comment.setUser(userRepository.findById(commentDto.getId()).orElse(null));
        comment.setCreatedAt(Instant.now());
        comment.setText(commentDto.getText());
        comment.setAd(adsRepository.findById(commentDto.getPk()).orElse(null));
        List<Comments> commentsList = ad.get().getComments();
        commentsList.add(comment);

        return adsMapper.toCommentDto(comment, comment.getUser());


    }
}
