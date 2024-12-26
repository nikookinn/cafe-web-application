package com.piramidacafe.website.service;

import com.piramidacafe.website.model.ContactInformation;

public interface ContactInformationService {

    void saveContactInformation(ContactInformation contactInformation);

    ContactInformation getContactInformation();

    void updateContactInformationById(ContactInformation contactInformation);
}
