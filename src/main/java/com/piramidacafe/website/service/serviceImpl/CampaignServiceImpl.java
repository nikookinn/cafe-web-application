package com.piramidacafe.website.service.serviceImpl;

import com.piramidacafe.website.dto.CampaignDto;
import com.piramidacafe.website.exception.CampaignNotFoundException;
import com.piramidacafe.website.mapper.CampaignMapper;
import com.piramidacafe.website.model.Campaign;
import com.piramidacafe.website.repository.CampaignRepository;
import com.piramidacafe.website.service.CampaignHelperService;
import com.piramidacafe.website.service.CampaignService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository repository;
    private final CampaignMapper mapper;
    private final CampaignHelperService helperService;

    public CampaignServiceImpl(CampaignRepository repository, CampaignMapper mapper, CampaignHelperService helperService) {
        this.repository = repository;
        this.mapper = mapper;
        this.helperService = helperService;
    }

    @Override
    public void mapAndUpdate(CampaignDto campaignDto) {
        helperService.deleteOldImage(campaignDto);
        String path = helperService.saveAndGetPathOfImage(campaignDto);
        Campaign campaign = getActiveCampaignById(campaignDto.getCampaignId().intValue());
        campaign.setName(campaignDto.getName());
        if (campaignDto.getCampaignImage() !=null && !campaignDto.getCampaignImage().isEmpty()){
            campaign.setImageUrl(path);
        }
        update(campaign);
    }


    @Override
    public void mapAndSave(CampaignDto campaignDto) {
        String path = helperService.saveAndGetPathOfImage(campaignDto);
        Campaign campaign = mapper.toEntity(campaignDto,path);
        save(campaign);
    }

    @Override
    public CampaignDto getActiveCampaignDtoById(int id) {
        Campaign campaign = repository.findCampaignByCampaignIdAndIsActiveIsTrue(id).orElseThrow(()->new CampaignNotFoundException("Campaign with " + id + " is not found"));
        return mapper.toDto(campaign);
    }

    @Override
    public Campaign getActiveCampaignById(int id) {
        return repository.findCampaignByCampaignIdAndIsActiveIsTrue(id).orElseThrow(()->new CampaignNotFoundException("Campaign with " + id + " is not found"));
    }

    @Override
    public List<Campaign>  findTop5ByIsActiveTrueOrderByCreatedDateDesc() {
        Pageable pageable = PageRequest.of(0, 5);
        return repository.findTop5ByIsActiveTrueOrderByCreatedDateDesc(pageable);
    }
    @Override
    public void save(Campaign campaign) {
        repository.save(campaign);
    }
    @Override
    public void update(Campaign campaign) {
        repository.save(campaign);
    }

    @Override
    public Page<Campaign> getAllActiveCampaigns(int page, int size) {
        return repository.findAllByIsActiveIsTrue(PageRequest.of(page, size));
    }
}
