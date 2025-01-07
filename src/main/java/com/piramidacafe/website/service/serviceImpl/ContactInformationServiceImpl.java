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

    @Override
    public void saveContactInformation(ContactInformationDto dto) {
        ContactInformation ci = findById(dto.getId());
        String imageUrl = ci.getWebsiteImageUrl();
        String iconUrl = ci.getWebsiteIcon();
        if (dto.getWebsiteImage() != null && !dto.getWebsiteImage().isEmpty()) {
            fileStorageService.deleteOldImage(imageUrl, ImageDirectory.APP_IMAGES.getDirectory());
            imageUrl = fileStorageService.storeFile(dto.getWebsiteImage(), ImageDirectory.APP_IMAGES.getDirectory());
        }
        if (dto.getWebsiteIconFile() != null && !dto.getWebsiteIconFile().isEmpty()){
            fileStorageService.deleteOldImage(iconUrl, ImageDirectory.APP_ICON.getDirectory());
            iconUrl = fileStorageService.saveIcoFile(dto.getWebsiteIconFile(), ImageDirectory.APP_ICON.getDirectory());
        }
        contactInformationRepository.save(mapper.toEntity(dto, ci, imageUrl,iconUrl));
    }

    @Override
    public ContactInformationDto findContactInfo() {
        ContactInformation ci = contactInformationRepository.findFirstRecord();
        return mapper.toDto(ci);
    }

    @Override
    public ContactInformation findById(Long id) {
        return contactInformationRepository.findById(id).orElseThrow(()->new ContactInfoNotFoundException("There is no contact info with id "+id));
    }
}
