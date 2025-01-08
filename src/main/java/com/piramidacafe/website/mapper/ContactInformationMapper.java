package com.piramidacafe.website.mapper;

import com.piramidacafe.website.dto.ContactInformationDto;
import com.piramidacafe.website.model.ContactInformation;
import org.springframework.stereotype.Component;

@Component
public class ContactInformationMapper {

    public ContactInformationDto toDto(ContactInformation entity) {
        ContactInformationDto dto = new ContactInformationDto();
        dto.setId(entity.getId());
        dto.setCafeName(entity.getCafeName());
        dto.setAbout(entity.getAbout());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAddress(entity.getAddress());
        dto.setWorkingHours(entity.getWorkingHours());
        dto.setExistingImageUrl(entity.getWebsiteImageUrl());
        dto.setEmail(entity.getEmail());
        dto.setInstagramUrl(entity.getInstagramUrl());
        dto.setFacebookUrl(entity.getFacebookUrl());
        dto.setTwitterUrl(entity.getTwitterUrl());
        dto.setWebsiteIcon(entity.getWebsiteIcon());
        return dto;
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
