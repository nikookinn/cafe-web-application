package com.piramidacafe.website.service;

import com.piramidacafe.website.dto.ContactInformationDto;
import com.piramidacafe.website.model.ContactInformation;

import java.util.List;

public interface ContactInformationService {

    void saveContactInformation(ContactInformationDto dto);
    ContactInformationDto findContactInfo();
    ContactInformation findById(Long id);
}
