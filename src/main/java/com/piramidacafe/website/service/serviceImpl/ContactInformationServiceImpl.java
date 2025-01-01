package com.piramidacafe.website.service.serviceImpl;

import com.piramidacafe.website.model.ContactInformation;
import com.piramidacafe.website.repository.ContactInformationRepository;
import com.piramidacafe.website.service.ContactInformationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactInformationServiceImpl implements ContactInformationService {

    private final ContactInformationRepository contactInformationRepository;

    public ContactInformationServiceImpl(ContactInformationRepository contactInformationRepository) {
        this.contactInformationRepository = contactInformationRepository;
    }

    @Override
    public void saveContactInformation(ContactInformation contactInformation) {
        contactInformationRepository.save(contactInformation);
    }

    @Override
    public List<ContactInformation> getContactInformation() {
        return contactInformationRepository.findAll();
    }

    @Override
    public void updateContactInformationById(ContactInformation contactInformation) {

    }
}
