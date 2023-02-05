package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateAds;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {

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