package com.piramidacafe.website.service.serviceImpl;

import com.piramidacafe.website.Helper.ImageDirectory;
import com.piramidacafe.website.dto.ContactInformationDto;
import com.piramidacafe.website.exception.ContactInfoNotFoundException;
import com.piramidacafe.website.mapper.ContactInformationMapper;
import com.piramidacafe.website.model.ContactInformation;
import com.piramidacafe.website.model.Item;
import com.piramidacafe.website.repository.ContactInformationRepository;
import com.piramidacafe.website.service.ContactInformationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactInformationServiceImpl implements ContactInformationService {
    private final ContactInformationMapper mapper;
    private final ContactInformationRepository contactInformationRepository;
    private final FileStorageService fileStorageService;

    public ContactInformationServiceImpl(ContactInformationMapper mapper, ContactInformationRepository contactInformationRepository, FileStorageService fileStorageService) {
        this.mapper = mapper;
        this.contactInformationRepository = contactInformationRepository;
        this.fileStorageService = fileStorageService;
    }

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
