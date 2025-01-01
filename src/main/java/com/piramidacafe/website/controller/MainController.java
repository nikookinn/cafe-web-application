package com.piramidacafe.website.controller;

import com.piramidacafe.website.service.CampaignService;
import com.piramidacafe.website.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {

    private final MenuService menuService;
    private final CampaignService campaignService;

    public MainController(MenuService menuService, CampaignService campaignService) {
        this.menuService = menuService;
        this.campaignService = campaignService;
    }

    @GetMapping
    public String showHomePage(Model model){
        model.addAttribute("menus",menuService.getActiveMenus());
        model.addAttribute("campaigns",campaignService.findTop5ByIsActiveTrueOrderByCreatedDateDesc());
        return "main/home-page";
    }
    @GetMapping("main-menu")
    public String showMenuPage(Model model){
        model.addAttribute("menus",menuService.getActiveMenus());
        return "main/menu-page";
    }


}
