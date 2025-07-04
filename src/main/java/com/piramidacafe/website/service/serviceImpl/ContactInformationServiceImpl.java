package com.piramidacafe.website.service.serviceImpl;

import com.piramidacafe.website.enums.ImageDirectory;
import com.piramidacafe.website.dto.ContactInformationDto;
import com.piramidacafe.website.mapper.ContactInformationMapper;
import com.piramidacafe.website.model.ContactInformation;
import com.piramidacafe.website.repository.ContactInformationRepository;
import com.piramidacafe.website.service.ContactInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContactInformationServiceImpl implements ContactInformationService {
    private final ContactInformationMapper mapper;
    private final ContactInformationRepository contactInformationRepository;
    private final FileStorageService fileStorageService;

    // this method is for saving and updating the contact information,
    // when there is no record on database we are creating new ContactInformation object
    // otherwise we will find the only record on database and update
    @Override
    public void saveContactInformation(ContactInformationDto dto) {
        ContactInformation ci = null;
        if (dto.getId() != null) {
            ci = contactInformationRepository.findById(dto.getId()).orElse(null);
        }
        if (ci == null) {
            ci = new ContactInformation();
        }

        String imageUrl = ci.getWebsiteImageUrl();
        String iconUrl = ci.getWebsiteIcon();

        if (dto.getWebsiteImage() != null && !dto.getWebsiteImage().isEmpty()) {
            if (imageUrl != null) {
                fileStorageService.deleteOldImage(imageUrl, ImageDirectory.APP_IMAGES.getDirectory());
            }
            imageUrl = fileStorageService.storeFile(dto.getWebsiteImage(), ImageDirectory.APP_IMAGES.getDirectory());
        }

        if (dto.getWebsiteIconFile() != null && !dto.getWebsiteIconFile().isEmpty()) {
            if (iconUrl != null) {
                fileStorageService.deleteOldImage(iconUrl, ImageDirectory.APP_ICON.getDirectory());
            }
            iconUrl = fileStorageService.saveIcoFile(dto.getWebsiteIconFile(), ImageDirectory.APP_ICON.getDirectory());
        }
        contactInformationRepository.save(mapper.toEntity(dto, ci, imageUrl, iconUrl));
        log.info("contact information is updated successfully");
    }


    @Override
    public ContactInformationDto findContactInfo() {
        ContactInformation ci = contactInformationRepository.findFirstRecord();
        if (ci ==null){
            return new ContactInformationDto();
        }
        return mapper.toDto(ci);
    }
}
