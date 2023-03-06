package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

/**
 * Контроллер для работы с объявлениями и комментариями/отзывами к ним
 */
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {
    private final AdsService adsService;
    private final CommentsRepository commentsRepository;
    private final UserService userService;

    private final AdsRepository adsRepository;

    public AdsController(AdsService adsService,
                         CommentsRepository commentsRepository,
                         UserService userService,
                         AdsRepository adsRepository) {
        this.adsService = adsService;
        this.commentsRepository = commentsRepository;
        this.userService = userService;
        this.adsRepository = adsRepository;
    }


    @Operation(
            summary = "Отображение всех объявлений от всех пользователей",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получение списка всех объявлений",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = AdsAllDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Если объявления не найдены"
                    )
            }, tags = "Ads"
    )
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

    @Operation(
            summary = "Отображение собственных объявлений пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получение всех объявлений пользователя-автора",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = AdsAllDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Если объявления не найдены"
                    )
            }, tags = "Ads"
    )
    @GetMapping("/me")
    public ResponseEntity<AdsMeDto> getAdsMe() {
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

    @Operation(
            summary = "Редактирование объявлений по id объявления (доступно только для админа и автора объявления)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Редактируемое объявление",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Если объявление не найдено"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Если пользователь - не админ и не автор объявления"
                    )
            }, tags = "Ads",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Редактируемое объявление",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdsDto.class)
                    )
            )
    )
    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAds(@Parameter(description = "id объявления", example = "8") @PathVariable Integer id,
                                            @RequestBody AdsCreateDto adsCreateDto,
                                            HttpServletRequest request) {
        //только автор или админ может удалить комментарий
        Ads ad = adsRepository.findAdsByPk(id);
        if (ad == null) {
            return ResponseEntity.status(404).build();
        }
        Users user = userService.getAuthorizedUser();
        if (!request.isUserInRole("ROLE_ADMIN") && !user.getAds().contains(ad)) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(adsService.updateAds(id, adsCreateDto));

    }


    @Operation(
            summary = "Отображение всех комментариев объявления по id объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получение списка всех комментариев конкретного объявления",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = AdsCommentsDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Если объявления не найдены"
                    )
            }, tags = "Comments"
    )
    @GetMapping("/{adPk}/comments")
    public ResponseEntity<AdsCommentsDto> getAdsComments(@Parameter(description = "id объявления", example = "8")
                                                         @PathVariable Integer adPk) {
        if (true) {
            return ResponseEntity.ok(adsService.getAdsComments(adPk));
        } else if (false) {
            return ResponseEntity.status(401).build();
        } else if (false) {
            return ResponseEntity.status(403).build();
        }
        return null;
    }


    @Operation(
            summary = "Получение комментария по id комментария и по id объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получение комментария по его id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Если комментарий не найден"
                    )
            }, tags = "Comments"
    )
    @GetMapping("/{adPk}/comments/{id}")
    public ResponseEntity<CommentDto> getComment(@Parameter(description = "id объявления", example = "8")
                                                 @PathVariable Integer adPk,
                                                 @Parameter(description = "id комментария", example = "2")
                                                 @PathVariable Integer id) {
        if (true) {
            return ResponseEntity.ok(adsService.getComment(adPk, id));
        } else if (false) {
            return ResponseEntity.status(404).build();
        }
        return null;
    }


    @Operation(
            summary = "Удаление комментария по id комментария и по id объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Удаление комментария по его id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Comments.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Если комментарий не найден"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Если пользователь - не админ и не автор объявления"
                    )
            }, tags = "Comments"
    )
    @DeleteMapping("/{adPk}/comments/{id}")
    public ResponseEntity<Object> deleteComments(@Parameter(description = "id объявления", example = "8")
                                                 @PathVariable Integer adPk,
                                                 @Parameter(description = "id комментария", example = "2")
                                                 @PathVariable Integer id,
                                                 HttpServletRequest request) {
        //только автор или админ может удалить комментарий
        Comments comment = commentsRepository.findByAd_PkAndPk(adPk, id);
        if (comment == null) {
            return ResponseEntity.status(404).build();
        }
        Users user = userService.getAuthorizedUser();
        if (!request.isUserInRole("ROLE_ADMIN") &&
                !user.getComments().stream()
                        .anyMatch(x -> x.getText().equals(comment.getText())))
//                        contains(comment.getText()))
        {
            return ResponseEntity.status(403).build();
        }
        adsService.deleteComment(adPk, id);
        return ResponseEntity.ok().build();
        //===============================================
    }


    @Operation(
            summary = "Удаление объявления по id объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Удаление объявления по его id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Ads.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Если объявление не найдено"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Если пользователь - не админ и не автор объявления"
                    )
            }, tags = "Ads"
    )
    @DeleteMapping("/{adPk}")
    public ResponseEntity<Object> deleteAds(@Parameter(description = "id объявления", example = "8")
                                            @PathVariable Integer adPk,
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


    @Operation(
            summary = "Получение объявления по его id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получено объявление по его id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsByIdDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Если объявление не найдено"
                    )
            }, tags = "Ads"
    )
    @GetMapping("/{id}")
    public ResponseEntity<AdsByIdDto> getAds(@Parameter(description = "id объявления", example = "8")
                                             @PathVariable Integer id) {
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


    @Operation(
            summary = "Редактирование комментария по его id (доступно только для админа и автора комментария)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Редактируемый комментарий",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Если комментарий не найден"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Если пользователь - не админ и не автор комментария"
                    )
            }, tags = "Comments",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Редактируемый комментарий",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CommentDto.class)
                    )
            )
    )
    @PatchMapping("/{adPk}/comments/{id}")
    public ResponseEntity<CommentDto> updateComments(@Parameter(description = "id объявления", example = "8")
                                                     @PathVariable Integer adPk,
                                                     @RequestBody CommentDto comment,
                                                     @Parameter(description = "id комментария", example = "2")
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


    @Operation(
            summary = "Размещение нового объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Объявление размещено и сохранено в БД",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDto.class))
                    )
            }, tags = "Ads",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(implementation = MultipartFile.class))
            )

    )
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


    @Operation(
            summary = "Размещение комментария к объявлению по id объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Комментарий размещен и сохранен в БД",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class))
                    )
            }, tags = "Comments",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CommentDto.class)
                    )

            )
    )
    @PostMapping("/{adPk}/comments")
    public ResponseEntity<CommentDto> addAdsComments(@Parameter(description = "id объявления", example = "8")
                                                     @PathVariable Integer adPk,
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
