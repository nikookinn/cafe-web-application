package com.piramidacafe.website.controller;

import com.piramidacafe.website.dto.CampaignDto;
import com.piramidacafe.website.service.CampaignService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/dashboard/campaign")
public class CampaignDashboardController {

    private final CampaignService campaignService;

    @GetMapping()
    public String showCampaignPage(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   Model model) {
        model.addAttribute("campaignPage", campaignService.findAllActiveCampaigns(page, size));
        return "dashboard/campaign-dashboard";
    }

    @GetMapping("/add")
    public String showCampaignAddPage(@ModelAttribute("campaignDto") CampaignDto campaignDto) {
        return "dashboard/add-campaign-dashboard";
    }

    @PostMapping("/save")
    public String saveItem(@Valid @ModelAttribute("campaignDto") CampaignDto campaignDto, BindingResult result) {
        if (result.hasErrors()) {
            return "dashboard/add-campaign-dashboard";
        }
        campaignService.saveCampaign(campaignDto);
        return "redirect:/admin/dashboard/campaign";
    }

    @GetMapping("/update/{id}")
    public String showUpdatePage(@PathVariable("id") int id, Model model) {
        model.addAttribute("campaignDto", campaignService.findActiveCampaignDtoById(id));
        return "dashboard/update-campaign-dashboard";
    }

    @PostMapping("/process-update")
    public String updateMenu(@Valid @ModelAttribute("campaignDto") CampaignDto campaignDto, BindingResult result) {
        if (result.hasErrors()) {
            return "dashboard/update-campaign-dashboard";
        }
        campaignService.updateCampaign(campaignDto);
        return "redirect:/admin/dashboard/campaign";
    }
    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") int id) {
        campaignService.markCampaignAsInactive(id);
        return "redirect:/admin/dashboard/campaign";
    }
}
