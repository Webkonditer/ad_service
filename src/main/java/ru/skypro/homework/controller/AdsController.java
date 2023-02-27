package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.adsDto.AdsDto;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.adsDto.*;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comments;
import ru.skypro.homework.model.Users;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentsRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImagesService;
import ru.skypro.homework.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {
    private final AdsService adsService;
    private final ImagesService imagesService;
    private final CommentsRepository commentsRepository;
    private final UserService userService;

    private final AdsRepository adsRepository;

    public AdsController(AdsService adsService,
                         ImagesService imagesService,
                         CommentsRepository commentsRepository,
                         UserService userService,
                         AdsRepository adsRepository) {
        this.adsService = adsService;
        this.imagesService = imagesService;
        this.commentsRepository = commentsRepository;
        this.userService = userService;
        this.adsRepository = adsRepository;
    }

    @GetMapping()
    public ResponseEntity<AdsAllDto> getAllAds() {
        if (true) {
            return ResponseEntity.ok(adsService.getAllAds());
        } else if (false) {
            return ResponseEntity.status(401).build();
        } else if (false) {
            return ResponseEntity.status(403).build();
        } else if (false) {
            return ResponseEntity.status(404).build();
        }
        return null;
    }

    @GetMapping("/me")
    public ResponseEntity<AdsMeDto> getAdsMe(
//            @RequestParam(required = false) Boolean authenticated,
//            @RequestParam(required = false, name = "authorities[0].authority") String authorities,
//            @RequestParam(required = false) Object credentials,
//            @RequestParam(required = false) Object details,
//            @RequestParam(required = false) Object principal
    ) {
        if (true) {
            return ResponseEntity.ok(adsService.getAllAdsMe());
        } else if (false) {
            return ResponseEntity.status(401).build();
        } else if (false) {
            return ResponseEntity.status(403).build();
        } else if (false) {
            return ResponseEntity.status(404).build();
        }
        return null;
    }


    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable Integer id,
                                            @RequestBody AdsCreateDto adsCreateDto) {
        if (true) {
            return ResponseEntity.ok(adsService.updateAds(id, adsCreateDto));
        } else if (false) {
            return ResponseEntity.status(401).build();
        } else if (false) {
            return ResponseEntity.status(403).build();
        } else if (false) {
            return ResponseEntity.status(404).build();
        }
        return null;
    }


    @GetMapping("/{adPk}/comments")
    public ResponseEntity<AdsCommentsDto> getAdsComments(@PathVariable Integer adPk) {
        if (true) {
            return ResponseEntity.ok(adsService.getAdsComments(adPk));
        } else if (false) {
            return ResponseEntity.status(401).build();
        } else if (false) {
            return ResponseEntity.status(403).build();
        }
        return null;
    }

    @GetMapping("/{adPk}/comments/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Integer adPk,
                                                 @PathVariable Integer id) {
        if (true) {
            return ResponseEntity.ok(adsService.getComment(adPk, id));
        } else if (false) {
            return ResponseEntity.status(404).build();
        }
        return null;
    }

    /**
     * Метод для удаления комментария по id объявления и id комментария
     * adPk - id объявления
     * id - id комментария
     */
    @DeleteMapping("/{adPk}/comments/{id}")
    public ResponseEntity<Object> deleteComments(@PathVariable Integer adPk,
                                                 @PathVariable Integer id,
                                                 HttpServletRequest request) {
        //только автор или админ может удалить комментарий
        Comments comment = commentsRepository.findByAd_PkAndPk(adPk, id);
        if (comment == null) {
            return ResponseEntity.status(404).build();
        }
        Users user = userService.getAuthorizedUser();
        if (!request.isUserInRole("ROLE_ADMIN") && !user.getComments().contains(comment)) {
            return ResponseEntity.status(403).build();
        }
        adsService.deleteComment(adPk, id);
        return ResponseEntity.ok().build();
        //===============================================
    }


    /**
     * Метод для удаления объявления по id
     * adPk - id объявления
     * id - id комментария
     */
    @DeleteMapping("/{adPk}")
    public ResponseEntity<Object> deleteAds(@PathVariable Integer adPk,
                                            HttpServletRequest request) {
        //только автор или админ может удалить комментарий
        Ads ad = adsRepository.findAdsByPk(adPk);
        if (ad == null) {
            return ResponseEntity.status(404).build();
        }
        Users user = userService.getAuthorizedUser();
        if (!request.isUserInRole("ROLE_ADMIN") && !user.getAds().contains(ad)) {
            return ResponseEntity.status(403).build();
        }
        adsService.deleteAd(adPk);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdsByIdDto> getAds(@PathVariable Integer id) {
        if (true) {
            return ResponseEntity.ok(adsService.getAds(id));
        } else if (false) {
            return ResponseEntity.status(401).build(); //"Unauthorized"
        } else if (id < 0 || id == null) {
            return ResponseEntity.status(403).build(); //"Forbidden"
        } else if (false) {
            return ResponseEntity.status(404).build(); //"Not Found"
        }
        return null;
    }

    @PatchMapping("/{adPk}/comments/{id}")
    public ResponseEntity<CommentDto> updateComments(@PathVariable Integer adPk,
                                                     @RequestBody CommentDto comment,
                                                     @PathVariable Integer id) {
        if (true) {
            return ResponseEntity.ok(adsService.updateComment(adPk, id, comment));
        } else if (false) {
            return ResponseEntity.status(401).build();
        } else if (false) {
            return ResponseEntity.status(403).build();
        } else if (false) {
            return ResponseEntity.status(404).build();
        }
        return null;
    }

//    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<String> updateAdsImage(@PathVariable Integer id,
//                                                 @RequestParam MultipartFile multipartFile)
//            throws IOException {
//
//        Boolean updateAdImageDone = imagesService.updateAdsImage(multipartFile, id);
//        if (updateAdImageDone) {
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.status(404).build();
//    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<AdsDto> addAds(@RequestPart("properties") AdsCreateDto adsCreateDto,
                                         @RequestPart MultipartFile image) throws IOException {
        if (true) {
            adsService.addAds(adsCreateDto, image);
            return ResponseEntity.ok().build();
        } else if (false) {
            return ResponseEntity.status(401).build();
        } else if (false) {
            return ResponseEntity.status(403).build();
        } else if (false) {
            return ResponseEntity.status(404).build();
        }
        return null;
    }

    @PostMapping("/{adPk}/comments")
    public ResponseEntity<CommentDto> addAdsComments(@PathVariable Integer adPk,
                                                     @RequestBody CommentDto commentDto) {
        if (true) {
            return ResponseEntity.status(HttpStatus.CREATED).body(adsService.addAdsComments(adPk, commentDto));
        } else if (false) {
            return ResponseEntity.status(401).build();
        } else if (false) {
            return ResponseEntity.status(403).build();
        } else if (false) {
            return ResponseEntity.status(404).build();
        }
        return null;
    }


}
