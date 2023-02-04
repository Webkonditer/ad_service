package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.PasswordDto;
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


    @GetMapping("/{ad_pk}/comments")
    public ResponseEntity<AdsCommentsDto> getAdsComments(@PathVariable Integer ad_pk) {
        if (true) {
            return ResponseEntity.ok(new AdsCommentsDto());
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

    @DeleteMapping("/{id}")
    public ResponseEntity<AdsCommentsResultsDto> removeAds(@PathVariable Integer id) {
        if (true) {
            return ResponseEntity.ok().build();
        } else if (false) {
            return ResponseEntity.status(204).build();
        } else if (false) {
            return ResponseEntity.status(401).build();
        } else if (false) {
            return ResponseEntity.status(403).build();
        }
        return null;
    }

}
