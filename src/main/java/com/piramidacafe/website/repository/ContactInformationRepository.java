package com.piramidacafe.website.repository;

import com.piramidacafe.website.model.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactInformationRepository extends JpaRepository<ContactInformation, Long> {
}
