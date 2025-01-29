package com.piramidacafe.website.controller;

import com.piramidacafe.website.service.CampaignService;
import com.piramidacafe.website.service.ContactInformationService;
import com.piramidacafe.website.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class MainController {

    private final MenuService menuService;
    private final CampaignService campaignService;
    private final ContactInformationService informationService;


    @GetMapping
    public String showHomePage(Model model){
        model.addAttribute("menus",menuService.getActiveMenus());
        model.addAttribute("campaigns",campaignService.findTop10ByIsActiveTrueOrderByCreatedDateDesc());
        return "main/home-page";
    }
    @GetMapping("/main-menu")
    public String showMenuPage(Model model){
        model.addAttribute("menus",menuService.getActiveMenus());
        return "main/menu-page";
    }
    @GetMapping("/campaign-and-event")
    public String showCampaignAndEventPage(Model model){
        model.addAttribute("campaigns",campaignService.findAllActiveCampaigns());
        return "main/campaign-event-page";
    }
    @GetMapping("/about")
    public String showAboutPage(Model model){
        model.addAttribute("cafeInfo",informationService.findContactInfo());
        return "main/about-page";
    }

}
