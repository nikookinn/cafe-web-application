package com.piramidacafe.website.dto;

import com.piramidacafe.website.annotations.ValidImageFile;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@NoArgsConstructor
public class CampaignDto {
    private Long campaignId;

    @NotEmpty(message = "This field cannot be empty")
    private String name;

    private String imageUrl;

    @ValidImageFile
    private MultipartFile campaignImage;

    public CampaignDto(Long campaignId, String name, String imageUrl) {
        this.campaignId = campaignId;
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
