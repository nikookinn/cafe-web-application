package com.piramidacafe.website.mapper;

import com.piramidacafe.website.dto.ContactInformationDto;
import com.piramidacafe.website.model.ContactInformation;
import org.springframework.stereotype.Component;

@Component
public class ContactInformationMapper {

    public ContactInformationDto toDto(ContactInformation entity) {
        ContactInformationDto dto = new ContactInformationDto();
        dto.setCafeName(entity.getCafeName());
        dto.setAbout(entity.getAbout());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAddress(entity.getAddress());
        dto.setWorkingHours(entity.getWorkingHours());
        dto.setExistingImageUrl(entity.getWebsiteImageUrl());
        return dto;
    }

    public ContactInformation toEntity(ContactInformationDto dto, String imageUrl) {
        ContactInformation entity = new ContactInformation();
        entity.setCafeName(dto.getCafeName());
        entity.setAbout(dto.getAbout());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAddress(dto.getAddress());
        entity.setWorkingHours(dto.getWorkingHours());

        if (dto.getWebsiteImage() != null && !dto.getWebsiteImage().isEmpty()) {
            entity.setWebsiteImageUrl(imageUrl);
        } else {
            entity.setWebsiteImageUrl(null);
        }
        return entity;
    }
}
