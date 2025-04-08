package com.piramidacafe.website.mapper;

import com.piramidacafe.website.dto.ContactInformationDto;
import com.piramidacafe.website.model.ContactInformation;
import org.springframework.stereotype.Component;

@Component
public class ContactInformationMapper {

    public ContactInformationDto toDto(ContactInformation entity) {
        return ContactInformationDto.builder()
                .id(entity.getId())
                .cafeName(entity.getCafeName())
                .about(entity.getAbout())
                .phoneNumber(entity.getPhoneNumber())
                .address(entity.getAddress())
                .workingHours(entity.getWorkingHours())
                .existingImageUrl(entity.getWebsiteImageUrl())
                .email(entity.getEmail())
                .instagramUrl(entity.getInstagramUrl())
                .facebookUrl(entity.getFacebookUrl())
                .twitterUrl(entity.getTwitterUrl())
                .websiteIcon(entity.getWebsiteIcon())
                .build();
    }

    public ContactInformation toEntity(ContactInformationDto dto, ContactInformation ci, String imageUrl,String iconUrl) {
        ci.setCafeName(dto.getCafeName());
        ci.setAbout(dto.getAbout());
        ci.setPhoneNumber(dto.getPhoneNumber());
        ci.setAddress(dto.getAddress());
        ci.setWorkingHours(dto.getWorkingHours());
        ci.setEmail(dto.getEmail());
        ci.setInstagramUrl(dto.getInstagramUrl());
        ci.setFacebookUrl(dto.getFacebookUrl());
        ci.setTwitterUrl(dto.getTwitterUrl());
        if (dto.getWebsiteImage() != null && !dto.getWebsiteImage().isEmpty()) {
            ci.setWebsiteImageUrl(imageUrl);
        }
        if (dto.getWebsiteIconFile() != null && !dto.getWebsiteIconFile().isEmpty()) {
            ci.setWebsiteIcon(iconUrl);
        }
        return ci;
    }
}
