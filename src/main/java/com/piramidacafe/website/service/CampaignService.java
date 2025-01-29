package com.piramidacafe.website.service;

import com.piramidacafe.website.dto.CampaignDto;
import com.piramidacafe.website.model.Campaign;
import org.springframework.data.domain.Page;
import java.util.List;

public interface CampaignService {

    void saveCampaign(CampaignDto campaignDto);

    void updateCampaign(CampaignDto campaignDto);

    CampaignDto findActiveCampaignDtoById(int id);

    Campaign findActiveCampaignById(int id);

    List<Campaign> findTop10ByIsActiveTrueOrderByCreatedDateDesc();

    Page<Campaign> findAllActiveCampaigns(int page, int size);
    List<CampaignDto> findAllActiveCampaigns();

    void update(Campaign campaign);

    void save(Campaign campaign);
    void markCampaignAsInactive(int id);

}
