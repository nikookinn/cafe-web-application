package com.piramidacafe.website.service;

import com.piramidacafe.website.dto.CampaignDto;

public interface CampaignHelperService {
    void deleteOldImage(CampaignDto dto);
    String saveAndGetPathOfImage(CampaignDto dto);
}
