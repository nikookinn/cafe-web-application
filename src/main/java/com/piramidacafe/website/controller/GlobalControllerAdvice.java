package com.piramidacafe.website.controller;

import com.piramidacafe.website.dto.ContactInformationDto;
import com.piramidacafe.website.service.ContactInformationService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {
    private final ContactInformationService contactInformationService;

    public GlobalControllerAdvice(ContactInformationService contactInformationService) {
        this.contactInformationService = contactInformationService;
    }
    @ModelAttribute("info")
    public ContactInformationDto getContactInformation(){
        return contactInformationService.findContactInfo();
    }
}
