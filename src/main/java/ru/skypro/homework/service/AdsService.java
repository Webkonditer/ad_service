package ru.skypro.homework.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.adsDto.AdsAllDto;
import ru.skypro.homework.dto.adsDto.AdsCommentsDto;
import ru.skypro.homework.dto.adsDto.AdsCreateDto;
import ru.skypro.homework.dto.adsDto.AdsDto;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.service.mapper.AdsMapper;

@Service
@Slf4j
public class AdsService {
    private final AdsRepository adsRepository;
    private final AdsMapper adsMapper;
    public AdsService(AdsRepository adsRepository, AdsMapper adsMapper) {
        this.adsRepository = adsRepository;
        this.adsMapper = adsMapper;
    }
    public AdsDto getById(Integer adsId) {
        log.info("Was invoked method for get adsDto by id");
        Ads ad = adsRepository.findById(adsId).get();
        return adsMapper.toAdsDto(ad,ad.getImage(),ad.getUser());
    }

    public AdsAllDto getAllAds() {
        return adsMapper.toAdsAllDto(adsRepository);
    }

    public AdsCommentsDto getAdsComments(Integer adsId) {
        log.info("Was invoked method for get adsComments by adsId");
        Ads ad = adsRepository.findById(adsId).get();
        return adsMapper.toAdsCommentsDto(ad);
    }

    public AdsDto updateAds(Integer id, AdsCreateDto adsCreateDto) {
        if (!adsRepository.existsById(id)) {
            return null;
        }
        Ads ad = adsRepository.findById(id).get();
        ad.setDescription(adsCreateDto.getDescription());
        ad.setPrice(adsCreateDto.getPrice());
        ad.setTitle(adsCreateDto.getTitle());
        adsRepository.save(ad);
        return adsMapper.toAdsDto(ad,ad.getImage(),ad.getUser());
    }
}
