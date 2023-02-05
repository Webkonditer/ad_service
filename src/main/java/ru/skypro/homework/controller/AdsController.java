package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.adsDto.AdsDto;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.adsDto.AdsCreateDto;
import ru.skypro.homework.dto.adsDto.*;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {

    @GetMapping()
    public ResponseEntity<AdsAllDto> getAllAds() {
        if (true) {
            return ResponseEntity.ok(new AdsAllDto());
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
            return ResponseEntity.ok(new AdsAllDto());
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
            return ResponseEntity.ok(new AdsDto());
        } else if (false) {
            return ResponseEntity.status(401).build();
        } else if (false) {
            return ResponseEntity.status(403).build();
        } else if (false) {
            return ResponseEntity.status(404).build();
        }
        return null;
    }


    @GetMapping("/{ad_pk}/comments")
    public ResponseEntity<AdsCommentsDto> getAdsComments(@PathVariable Integer ad_pk) {
        if (true) {
            return ResponseEntity.ok(new AdsCommentsDto());
        } else if (false) {
            return ResponseEntity.status(401).build();
        } else if (false) {
            return ResponseEntity.status(403).build();
        }
        return null;
    }

    @GetMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<CommentDto> getComments(@PathVariable String ad_pk, @PathVariable Integer id) {
        if (true) {
                return ResponseEntity.ok(new CommentDto());
            } else if (false) {
                return ResponseEntity.status(404).build();
            }
        return null;
    }

    @DeleteMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<Object> deleteComments(@PathVariable String ad_pk, @PathVariable Integer id) {
        if (true) {
            return ResponseEntity.status(200).build();
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
    public ResponseEntity<AdsByUserIdDto> getAds(@PathVariable Integer id) {
        if (true) {
            return ResponseEntity.ok(new AdsByUserIdDto());
        } else if (false) {
            return ResponseEntity.status(401).build();
        } else if (false) {
            return ResponseEntity.status(403).build();
        } else if (false) {
            return ResponseEntity.status(404).build();
        }
        return null;
    }

    @PatchMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<CommentDto> updateComments(@PathVariable String ad_pk,
                                                     @PathVariable Integer id,
                                                     @RequestBody CommentDto comment) {
        if (true) {
            return ResponseEntity.ok(comment);
        } else if (false) {
            return ResponseEntity.status(401).build();
        } else if (false) {
            return ResponseEntity.status(403).build();
        } else if (false) {
            return ResponseEntity.status(404).build();
        }
        return null;
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<AdsDto> addAds(@RequestPart("properties") AdsCreateDto adsCreateDto,
                                         @RequestPart Byte[] image) {
        if (true) {
            return ResponseEntity.ok(new AdsDto());
        } else if (false) {
            return ResponseEntity.status(401).build();
        } else if (false) {
            return ResponseEntity.status(403).build();
        } else if (false) {
            return ResponseEntity.status(404).build();
        }
        return null;
    }

    @PostMapping("/{ad_pk}/comment")
    public ResponseEntity<CommentDto> addAdsComments(@PathVariable Integer ad_pk,
                                                                @RequestBody CommentDto commentDto) {
        if (true) {
            return ResponseEntity.ok(commentDto);
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
