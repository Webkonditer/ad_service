package ru.skypro.homework.controller;

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
    public ResponseEntity<AdsAllDto> getAdsMe(@RequestParam Boolean authenticated,
                                              @RequestParam(name = "authorities[0].authority") String authorities,
                                              @RequestParam Object credentials,
                                              @RequestParam Object details,
                                              @RequestParam Object principal) {
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
    public ResponseEntity<AdsDto> updateAds(@PathVariable Integer id, @RequestBody AdsCreateDto createAds) {
        if (createAds != null) {
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
    public ResponseEntity<CommentDto> getComments(@PathVariable String adPk, @PathVariable Integer id) {
        if (adPk != null) {
            return ResponseEntity.ok(new CommentDto());
        } else if (false) {
            return ResponseEntity.status(404).build();
        }
        return null;
    }

    @DeleteMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<Integer> deleteComments(@PathVariable String ad_pk, @PathVariable Integer id) {
//        if (adPk != null) {
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
    public ResponseEntity<CommentDto> updateComments(@PathVariable String adPk,
                                                     @PathVariable Integer id,
                                                     @RequestBody CommentDto comment) {
        if (comment != null) {
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

    @PostMapping()
    public ResponseEntity<AdsAddDto> addAds(@RequestBody AdsAddDto adsAddDto) {
        if (true) {
            return ResponseEntity.ok(adsAddDto);
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
    public ResponseEntity<AdsCommentsResultsDto> addAdsComments(@PathVariable Integer ad_pk,
                                                                @RequestBody AdsCommentsResultsDto adsCommentsResultsDto) {
        if (true) {
            return ResponseEntity.ok(adsCommentsResultsDto);
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
