package com.piramidacafe.website.controller;

import com.piramidacafe.website.Helper.ImageDirectory;
import com.piramidacafe.website.dto.ContactInformationDto;
import com.piramidacafe.website.exeption.ContactInfoNotFoundException;
import com.piramidacafe.website.mapper.ContactInformationMapper;
import com.piramidacafe.website.model.ContactInformation;
import com.piramidacafe.website.service.ContactInformationService;
import com.piramidacafe.website.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/dashboard")
public class ContactInformationController {

    Logger logger = LoggerFactory.getLogger(ContactInformation.class);


    private final FileStorageService fileStorageService;
    private final ContactInformationMapper contactInformationMapper;
    private final ContactInformationService contactInformationService;

    public ContactInformationController(FileStorageService fileStorageService, ContactInformationMapper contactInformationMapper, ContactInformationService contactInformationService) {
        this.fileStorageService = fileStorageService;
        this.contactInformationMapper = contactInformationMapper;
        this.contactInformationService = contactInformationService;
    }

    @GetMapping()
    public String showDashboardInfoPage(Model model){
        List<ContactInformation> contactInfos = contactInformationService.getContactInformation();
        Optional<ContactInformation> information = contactInfos.stream().findFirst();
        if (information.isEmpty()){
            throw new ContactInfoNotFoundException("There is no contact information");
        }
        ContactInformationDto contactInformationDto = contactInformationMapper.toDto(information.get());
        model.addAttribute("contactInformationDto", contactInformationDto);
        return "dashboard/info-dashboard";
    }

    @GetMapping("/contact-information")
    public String showContactInformationForm(Model model) {
        List<ContactInformation> contactInfos = contactInformationService.getContactInformation();
        Optional<ContactInformation> information = contactInfos.stream().findFirst();
        if (information.isEmpty()){
            throw new ContactInfoNotFoundException("There is no contact information");
        }
        ContactInformationDto dto = contactInformationMapper.toDto(information.get());

        dto.setExistingImageUrl(information.get().getWebsiteImageUrl());

        model.addAttribute("contactInformationDto", dto);
        return "dashboard/add-info-dashboard";
    }

    @PostMapping("/contact-information/save")
    public String saveContactInformation(@ModelAttribute("contactInformationDto") ContactInformationDto dto) {
        List<ContactInformation> contactInfos = contactInformationService.getContactInformation();
        Optional<ContactInformation> eci = contactInfos.stream().findFirst();
        if (eci.isEmpty()){
            throw new ContactInfoNotFoundException("There is no contact information");
        }
        String imageUrl = null;
        if (eci.get().getId() !=null){

            if (dto.getWebsiteImage() != null && !dto.getWebsiteImage().isEmpty()) {
                fileStorageService.deleteOldImage(eci.get().getWebsiteImageUrl(), ImageDirectory.APP_IMAGES.getDirectory());
                imageUrl = fileStorageService.storeFile(dto.getWebsiteImage(), ImageDirectory.APP_IMAGES.getDirectory());
            }else {
                imageUrl = eci.get().getWebsiteImageUrl();
            }
            eci.get().setWebsiteImageUrl(imageUrl);
            contactInformationService.saveContactInformation(eci.get());
        }else {
            imageUrl = fileStorageService.storeFile(dto.getWebsiteImage(),ImageDirectory.APP_IMAGES.getDirectory());
            contactInformationService.saveContactInformation(contactInformationMapper.toEntity(dto, imageUrl));
        }


        return "redirect:/admin/dashboard";
    }
}
