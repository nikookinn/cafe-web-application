package com.piramidacafe.website.repository;

import com.piramidacafe.website.model.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    Optional<Campaign> findCampaignByCampaignIdAndIsActiveIsTrue(int id);

    Page<Campaign> findAllByIsActiveIsTrue(Pageable pageable);
    @Query("SELECT c FROM Campaign c WHERE c.isActive = true ORDER BY c.creationDate DESC")
    List<Campaign> findAllByIsActiveIsTrue();

    @Query("SELECT c FROM Campaign c WHERE c.isActive = true ORDER BY c.creationDate DESC")
    List<Campaign> findTop5ByIsActiveTrueOrderByCreatedDateDesc(Pageable pageable);
}
