package com.piramidacafe.website.service;

import com.piramidacafe.website.model.ContactInformation;
import com.piramidacafe.website.repository.ContactInformationRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactInformationServiceImpl implements ContactInformationService{

    private final ContactInformationRepository contactInformationRepository;

    public ContactInformationServiceImpl(ContactInformationRepository contactInformationRepository) {
        this.contactInformationRepository = contactInformationRepository;
    }

    @Override
    public void saveContactInformation(ContactInformation contactInformation) {
        contactInformationRepository.save(contactInformation);
    }

    @Override
    public ContactInformation getContactInformation() {
        return contactInformationRepository.findById(17L).orElse(new ContactInformation());
    }

    @Override
    public void updateContactInformationById(ContactInformation contactInformation) {

    }
}
