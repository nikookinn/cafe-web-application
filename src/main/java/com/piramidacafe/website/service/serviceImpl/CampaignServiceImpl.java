package com.piramidacafe.website.service.serviceImpl;

import com.piramidacafe.website.dto.CampaignDto;
import com.piramidacafe.website.exception.CampaignNotFoundException;
import com.piramidacafe.website.mapper.CampaignMapper;
import com.piramidacafe.website.model.Campaign;
import com.piramidacafe.website.repository.CampaignRepository;
import com.piramidacafe.website.service.CampaignHelperService;
import com.piramidacafe.website.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository repository;
    private final CampaignMapper mapper;
    private final CampaignHelperService helperService;


    @Override
    public void saveCampaign(CampaignDto campaignDto) {
        String path = helperService.saveAndGetPathOfImage(campaignDto);
        Campaign campaign = mapper.toEntity(campaignDto, path);
        save(campaign);
    }

    @Override
    public void updateCampaign(CampaignDto campaignDto) {
        helperService.deleteOldImage(campaignDto);
        String path = helperService.saveAndGetPathOfImage(campaignDto);
        Campaign campaign = findActiveCampaignById(campaignDto.getCampaignId().intValue());
        campaign.setName(campaignDto.getName());
        if (campaignDto.getCampaignImage() != null && !campaignDto.getCampaignImage().isEmpty()) {
            campaign.setImageUrl(path);
        }
        update(campaign);
    }

    @Override
    public CampaignDto findActiveCampaignDtoById(int id) {
        Campaign campaign = repository.findCampaignByCampaignIdAndIsActiveIsTrue(id).orElseThrow(() -> new CampaignNotFoundException("Campaign with " + id + " is not found"));
        return mapper.toDto(campaign);
    }

    @Override
    public Campaign findActiveCampaignById(int id) {
        return repository.findCampaignByCampaignIdAndIsActiveIsTrue(id).orElseThrow(() -> new CampaignNotFoundException("Campaign with " + id + " is not found"));
    }

    @Override
    public List<Campaign> findTop10ByIsActiveTrueOrderByCreatedDateDesc() {
        Pageable pageable = PageRequest.of(0, 10);
        return repository.findTop5ByIsActiveTrueOrderByCreatedDateDesc(pageable);
    }

    @Override
    public Page<Campaign> findAllActiveCampaigns(int page, int size) {
        return repository.findAllByIsActiveIsTrue(PageRequest.of(page, size));
    }

    @Override
    public List<CampaignDto> findAllActiveCampaigns() {
        List<Campaign> campaignList = repository.findAllByIsActiveIsTrue();
        return campaignList.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void save(Campaign campaign) {
        repository.save(campaign);
    }

    @Override
    public void markCampaignAsInactive(int id) {
        Campaign campaign = findActiveCampaignById(id);
        helperService.deleteCategoryImageFromStorage(campaign.getImageUrl());
        campaign.setActive(false);
        repository.save(campaign);
    }
    @Override
    public void update(Campaign campaign) {
        repository.save(campaign);
    }
}
