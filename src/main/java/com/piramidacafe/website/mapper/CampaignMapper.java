package com.piramidacafe.website.mapper;

import com.piramidacafe.website.dto.CampaignDto;
import com.piramidacafe.website.model.Campaign;
import org.springframework.stereotype.Component;

@Component
public class CampaignMapper {
    public CampaignDto toDto(Campaign campaign){
        return new CampaignDto( campaign.getCampaignId(),
                                campaign.getName(),
                                campaign.getImageUrl());

    }

    public Campaign toEntity(CampaignDto campaignDto, String imageUrl) {
        Campaign campaign = new Campaign();
        campaign.setCampaignId(campaignDto.getCampaignId());
        campaign.setName(campaignDto.getName());
        if (campaignDto.getCampaignImage() !=null && !campaignDto.getCampaignImage().isEmpty()){
            campaign.setImageUrl(imageUrl);
        }else {
            campaign.setImageUrl(null);
        }
        return campaign;
    }
}
