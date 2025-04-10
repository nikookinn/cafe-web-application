package com.piramidacafe.website.controller;

import com.piramidacafe.website.dto.ContactInformationDto;
import com.piramidacafe.website.service.ContactInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final ContactInformationService contactInformationService;

    @ModelAttribute("info")
    public ContactInformationDto getContactInformation(){
        return contactInformationService.findContactInfo();
    }
}
