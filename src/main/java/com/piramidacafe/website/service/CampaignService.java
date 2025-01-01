package com.piramidacafe.website.service;

import com.piramidacafe.website.dto.CampaignDto;
import com.piramidacafe.website.model.Campaign;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CampaignService {

    void mapAndSave(CampaignDto campaignDto);
    void mapAndUpdate(CampaignDto campaignDto);

    void save(Campaign campaign);

    CampaignDto getActiveCampaignDtoById(int id);
    Campaign getActiveCampaignById(int id);

    List<Campaign>  findTop5ByIsActiveTrueOrderByCreatedDateDesc();

    void update(Campaign campaign);


    Page<Campaign> getAllActiveCampaigns(int page, int size);
}
