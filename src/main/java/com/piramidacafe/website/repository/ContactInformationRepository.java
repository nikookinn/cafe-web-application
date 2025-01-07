package com.piramidacafe.website.repository;

import com.piramidacafe.website.model.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContactInformationRepository extends JpaRepository<ContactInformation, Long> {
    @Query(value = "SELECT c FROM ContactInformation c")
    ContactInformation findFirstRecord();
}
