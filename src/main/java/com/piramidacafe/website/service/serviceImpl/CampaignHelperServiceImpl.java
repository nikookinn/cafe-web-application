package com.piramidacafe.website.service.serviceImpl;

import com.piramidacafe.website.enums.ImageDirectory;
import com.piramidacafe.website.dto.CampaignDto;
import com.piramidacafe.website.service.CampaignHelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CampaignHelperServiceImpl implements CampaignHelperService {

    private final FileStorageService storageService;

    @Override
    public void deleteOldImage(CampaignDto dto) {
        if (dto.getCampaignImage() != null && !dto.getCampaignImage().isEmpty()) {
            storageService.deleteOldImage(dto.getImageUrl(), ImageDirectory.CATEGORY_IMAGES.getDirectory());
        }
    }

    @Override
    public String saveAndGetPathOfImage(CampaignDto dto) {
        if (dto.getCampaignImage() != null && !dto.getCampaignImage().isEmpty()) {
            return storageService.storeFile(dto.getCampaignImage(), ImageDirectory.CAMPAIGN_IMAGES.getDirectory());
        }
        return dto.getImageUrl();
    }
    @Override
    public void deleteCategoryImageFromStorage(String path) {
        if (path != null){
            storageService.deleteOldImage(path, ImageDirectory.CAMPAIGN_IMAGES.getDirectory());
        }
    }
}
