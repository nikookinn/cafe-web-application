package com.piramidacafe.website.dto;

import com.piramidacafe.website.annotations.ValidImageFile;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class CampaignDto {
    private Long campaignId;
    @NotEmpty(message = "This field cannot be empty")
    private String name;
    private String imageUrl;
    @ValidImageFile
    private MultipartFile campaignImage;

    public CampaignDto() {
    }

    public CampaignDto(Long campaignId, String name, String imageUrl) {
        this.campaignId = campaignId;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public MultipartFile getCampaignImage() {
        return campaignImage;
    }

    public void setCampaignImage(MultipartFile campaignImage) {
        this.campaignImage = campaignImage;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "CampaignDto{" +
               "campaignId=" + campaignId +
               ", name='" + name + '\'' +
               ", imageUrl='" + imageUrl + '\'' +
               ", campaignImage=" + campaignImage +
               '}';
    }
}
