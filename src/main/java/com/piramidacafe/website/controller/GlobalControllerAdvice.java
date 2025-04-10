package com.piramidacafe.website.controller;

import com.piramidacafe.website.dto.ContactInformationDto;
import com.piramidacafe.website.service.ContactInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    @Value("${app.image.upload.dir}")
    private String imageUploadDir;

    private final ContactInformationService contactInformationService;

    @ModelAttribute("info")
    public ContactInformationDto getContactInformation(Model model){
        String websiteIcon = imageUploadDir + "/app_icon/1736280265356_170084cd-be38-4082-a9ce-e271436e797d.ico";
        model.addAttribute("websiteIcon", websiteIcon);
        return contactInformationService.findContactInfo();
    }
}
