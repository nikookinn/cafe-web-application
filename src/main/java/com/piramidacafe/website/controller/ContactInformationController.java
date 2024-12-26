package com.piramidacafe.website.controller;

import com.piramidacafe.website.Helper.ImageDirectory;
import com.piramidacafe.website.dto.ContactInformationDto;
import com.piramidacafe.website.mapper.ContactInformationMapper;
import com.piramidacafe.website.model.ContactInformation;
import com.piramidacafe.website.service.ContactInformationService;
import com.piramidacafe.website.service.FileStorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/dashboard")
public class ContactInformationController {


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
        ContactInformation contactInformation = contactInformationService.getContactInformation();
        ContactInformationDto contactInformationDto = contactInformationMapper.toDto(contactInformation);
        model.addAttribute("contactInformationDto", contactInformationDto);
        return "dashboard/info-dashboard";
    }

    @GetMapping("/contact-information")
    public String showContactInformationForm(Model model) {
        ContactInformation contactInformation = contactInformationService.getContactInformation();
        ContactInformationDto dto = contactInformationMapper.toDto(contactInformation);

        dto.setExistingImageUrl(contactInformation.getWebsiteImageUrl());

        model.addAttribute("contactInformationDto", dto);
        return "dashboard/add-info-dashboard";
    }

    @PostMapping("/contact-information/save")
    public String saveContactInformation(@ModelAttribute("contactInformationDto") ContactInformationDto dto) {
        ContactInformation eci = contactInformationService.getContactInformation();
        String imageUrl = null;
        if (eci.getId() !=null){

            if (dto.getWebsiteImage() != null && !dto.getWebsiteImage().isEmpty()) {
                fileStorageService.deleteOldImage(eci.getWebsiteImageUrl(), ImageDirectory.APP_IMAGES.getDirectory());
                imageUrl = fileStorageService.storeFile(dto.getWebsiteImage(), ImageDirectory.APP_IMAGES.getDirectory());
            }else {
                imageUrl = eci.getWebsiteImageUrl();
            }
            eci.setWebsiteImageUrl(imageUrl);
            contactInformationService.saveContactInformation(eci);
        }else {
            imageUrl = fileStorageService.storeFile(dto.getWebsiteImage(),ImageDirectory.APP_IMAGES.getDirectory());
            contactInformationService.saveContactInformation(contactInformationMapper.toEntity(dto, imageUrl));
        }


        return "redirect:/admin/dashboard";
    }
}
