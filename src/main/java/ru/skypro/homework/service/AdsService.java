package ru.skypro.homework.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.adsDto.*;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comments;
import ru.skypro.homework.model.Users;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentsRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.mapper.AdsMapper;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

/**
 * Сервис для работы с объявлениями и комментариями/отзывами к ним
 */
@Service
@Slf4j
public class AdsService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final CommentsRepository commentsRepository;
    private final AdsRepository adsRepository;
    private final AdsMapper adsMapper;
    private final ImagesService imageService;


    public AdsService(AdsRepository adsRepository, AdsMapper adsMapper,
                      CommentsRepository commentsRepository,
                      UserService userService,
                      ImagesService imageService,
                      UserRepository userRepository) {
        this.adsRepository = adsRepository;
        this.adsMapper = adsMapper;
        this.commentsRepository = commentsRepository;
        this.userService = userService;
        this.imageService = imageService;
        this.userRepository = userRepository;
    }


//    Метод не используется. Нужен?
    public AdsDto getById(Integer adsId) {
        log.info("Was invoked method for get adsDto by id");
        Ads ad = adsRepository.findById(adsId).get();
        return adsMapper.toAdsDto(ad, ad.getUser(), ad.getImage());
    }

    /**
     * Метод для получения всех объявлений всех пользователей
     *
     */
    public AdsAllDto getAllAds() {
        return adsMapper.toAdsAllDto(adsRepository);
    }

    /**
     * Метод для получения всех объявлений пользователя-автора объявлений
     *
     */
    public AdsMeDto getAllAdsMe() {
        Integer id = userService.getAuthorizedUser().getId();
        return adsMapper.toAdsAllDtoMe(adsRepository, id);
    }

    /**
     * Метод для получения всех комментариев объявления
     *
     * @param adsId идентификатор объявления
     */
    public AdsCommentsDto getAdsComments(Integer adsId) {
        log.info("Was invoked method for get adsComments by adsId");
        Ads ad = adsRepository.findById(adsId).get();
        return adsMapper.toAdsCommentsDto(ad);
    }

    /**
     * Метод для редактирования объявлению
     *
     * @param id           идентификатор объявления
     * @param adsCreateDto ДТО, содержащий информацию для создания/обновления объявления
     */
    public AdsDto updateAds(Integer id, AdsCreateDto adsCreateDto) {
        if (!adsRepository.existsById(id)) {
            return null;
        }
        Ads ad = adsRepository.findById(id).get();
        ad.setDescription(adsCreateDto.getDescription());
        ad.setPrice(adsCreateDto.getPrice());
        ad.setTitle(adsCreateDto.getTitle());
        adsRepository.save(ad);
        return adsMapper.toAdsDto(ad, ad.getUser(), ad.getImage());
    }

    /**
     * Метод для получения комментария к объявлению
     *
     * @param id   идентификатор комментария
     * @param adPk идентификатор объявления
     */
    public CommentDto getComment(Integer adPk, Integer id) {
        log.info("Was invoked method for get Comments by adId and commentId");
        Users user = userService.getAuthorizedUser();
//        Users user = userRepository.findById(id).orElse(null);
        Comments comment = commentsRepository.findByAd_PkAndPk(adPk, id);


        return adsMapper.toCommentDto(comment, user);
    }

    /**
     * Метод для удаления комментария
     *
     * @param adPk id объявления
     * @param id   id комментария
     */
    public void deleteComment(Integer adPk, Integer id) {
        log.info("Was invoked method for delete Comment by adId and commentId");
        Comments comment = commentsRepository.findByAd_PkAndPk(adPk, id);
        commentsRepository.delete(comment);

    }

    /**
     * Метод для получения объявления по его id
     *
     * @param id id объявления
     */
    public AdsByIdDto getAds(Integer id) {
        log.info("Was invoked method for get Ads by id");
        Ads ad = adsRepository.findById(id).get();
        return adsMapper.toDtoByUserId(ad, ad.getImage(), ad.getUser());
    }

    /**
     * Метод для редактирования ранее размещенного комментария к объявлению
     *
     * @param id         идентификатор комментария
     * @param adPk       идентификатор объявления
     * @param commentDto ДТО, содержащий информацию для создания/обновления комментария
     */
    public CommentDto updateComment(Integer adPk, Integer id, CommentDto commentDto) {
        log.info("Was invoked method for update Comment");
        if (commentsRepository.findByPk(id) == null) {
            return null;
        }
        Comments comment = commentsRepository.findByPk(id);
        Users user = userRepository.findById(commentDto.getId()).orElse(null);
        Ads ad = adsRepository.findById(adPk).orElse(null);

        if (user == null || ad == null) {
            return null;
        }

        if (!user.getComments().contains(comment) || !ad.getComments().contains(comment)) {
            return null;
        }

        comment.setText(commentDto.getText());
        comment.setCreatedAt(Instant.parse(commentDto.getCreatedAt()));
        commentsRepository.save(comment);
        return adsMapper.toCommentDto(comment, comment.getUser());
    }


    /**
     * Метод для создания нового объявления
     *
     * @param image        файл, содержащий картинку для объявления
     * @param adsCreateDto ДТО, содержащий информацию для создания объявления
     */
    public AdsDto addAds(AdsCreateDto adsCreateDto, MultipartFile image) throws IOException {
        log.info("Was invoked method for create ad");

        Ads ad = new Ads();
        ad.setDescription(adsCreateDto.getDescription());
        ad.setPrice(adsCreateDto.getPrice());
        ad.setTitle(adsCreateDto.getTitle());

        ad.setCreatedAt(Instant.now());
        ad.setUser(userService.getAuthorizedUser());

        log.warn("Начало сохранения картинки");
        ad.setImage(imageService.create(image));

        log.warn("Картинка сохранена");
        adsRepository.save(ad);

        imageService.updateAdsImage(image, ad.getPk());

        return adsMapper.toAdsDto(ad, ad.getUser(), ad.getImage());
    }

    /**
     * Метод для добавления комментария к объявлению
     *
     * @param adPk       id объявления
     * @param commentDto ДТО, содержащий информацию для создания/обновления комментария
     */
    public CommentDto addAdsComments(Integer adPk, CommentDto commentDto) {
        log.info("Was invoked method for add comment for ad");

        Ads ad = adsRepository.findById(adPk).orElse(null);
        if (ad == null) {
            log.warn("Ad not found from id = " + adPk);
            return null;
        }
        Comments comment = new Comments();
        comment.setUser(userService.getAuthorizedUser());
        comment.setCreatedAt(Instant.now());
        comment.setText(commentDto.getText());
        comment.setAd(adsRepository.findById(adPk).orElse(null));
        commentsRepository.save(comment);

        List<Comments> commentsList = ad.getComments();
        commentsList.add(comment);

        return adsMapper.toCommentDto(comment, comment.getUser());
    }

    /**
     * Метод для удаления объявления
     *
     * @param adPk id объявления
     */
    public void deleteAd(Integer adPk) {
        log.info("Was invoked method for delete Ad by adId");
        Ads ad = adsRepository.findAdsByPk(adPk);
        adsRepository.delete(ad);
    }

    /**
     * Метод проверяет полномочия на внесение изиенений или удаление объявления
     * @param id - ид объявления
     */
    public boolean checkGrantesForAds(Integer id) {
        Ads ad = adsRepository.findAdsByPk(id);
        if (ad == null) {
            return false;
        }
        Users user = userService.getAuthorizedUser();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return user.getAds().contains(ad) ||
                (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
    }

    /**
     * Метод проверяет полномочия на внесение изиенений или удаление комментария
     * @param comment - сущность комментария
     */
    public boolean checkGrantesForComments(Comments comment) {

        Users user = userService.getAuthorizedUser();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return user.getComments().stream().anyMatch(x -> x.getText().equals(comment.getText())) ||
                (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
    }
}
