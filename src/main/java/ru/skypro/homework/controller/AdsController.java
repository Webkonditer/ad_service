package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateAds;
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
    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable Integer id, @RequestBody CreateAds createAds) {
        if (createAds!=null) {
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
    @GetMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<Comment> getComments(@PathVariable String adPk, @PathVariable Integer id) {
        if (adPk!=null) {
            return ResponseEntity.ok(new Comment());
                } else if (false) {
                    return ResponseEntity.status(404).build();
                }
                return null;
            }

    @DeleteMapping("/{ad_pk}/comments/{id}")
    public ResponseEntity<Integer> deleteComments(@PathVariable String adPk, @PathVariable Integer id) {
        if (adPk!=null) {
            return ResponseEntity.status(200).build();

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
    public ResponseEntity<Comment> updateComments(@PathVariable String adPk,
                                                  @PathVariable Integer id, @RequestBody Comment comment) {
        if (comment!=null) {
            return ResponseEntity.ok(comment);
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
