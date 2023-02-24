package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.adsDto.AdsDto;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.adsDto.*;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;


@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {
    private final AdsService adsService;

    public AdsController(AdsService adsService) {
        this.adsService = adsService;
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
    public ResponseEntity<AdsAllDto> getAdsMe(@RequestParam(required = false) Boolean authenticated,
                                              @RequestParam(required = false, name = "authorities[0].authority") String authorities,
                                              @RequestParam(required = false) Object credentials,
                                              @RequestParam(required = false) Object details,
                                              @RequestParam(required = false) Object principal) {
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


    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable Integer id, @RequestBody AdsCreateDto adsCreateDto) {
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
    public ResponseEntity<CommentDto> getComment(@PathVariable Integer adPk, @PathVariable Integer id) {
        if (true) {
            return ResponseEntity.ok(adsService.getComment(adPk, id));
        } else if (false) {
            return ResponseEntity.status(404).build();
        }
        return null;
    }

    @DeleteMapping("/{adPk}/comments/{id}")
    public ResponseEntity<Object> deleteComments(@PathVariable Integer adPk, @PathVariable Integer id) {
        if (true) {
            adsService.deleteComment(adPk, id);
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
                                                     @PathVariable Integer id,
                                                     @RequestBody CommentDto comment) {
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

    @PostMapping("/{adPk}/comment")
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
