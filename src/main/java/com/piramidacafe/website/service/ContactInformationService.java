package com.piramidacafe.website.service;

import com.piramidacafe.website.dto.ContactInformationDto;

public interface ContactInformationService {

    void saveContactInformation(ContactInformationDto dto);
    ContactInformationDto findContactInfo();
}
