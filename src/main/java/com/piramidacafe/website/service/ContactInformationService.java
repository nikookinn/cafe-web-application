package com.piramidacafe.website.service;

import com.piramidacafe.website.model.ContactInformation;

import java.util.List;

public interface ContactInformationService {

    void saveContactInformation(ContactInformation contactInformation);

    List<ContactInformation> getContactInformation();

    void updateContactInformationById(ContactInformation contactInformation);
}
