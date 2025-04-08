package com.piramidacafe.website.controller;

import com.piramidacafe.website.dto.ContactInformationDto;
import com.piramidacafe.website.service.ContactInformationService;
import com.piramidacafe.website.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/dashboard")
public class ContactDashboardController {

    private final ContactInformationService contactInformationService;
    private final VisitorService visitorService;


    @GetMapping()
    public String showDashboardInfoPage(Model model){
        model.addAttribute("contactInformationDto", contactInformationService.findContactInfo());
        model.addAttribute("dailyVisitors", visitorService.getDailyVisitors());
        model.addAttribute("monthlyVisitors",visitorService.getMonthlyVisitors());
        model.addAttribute("yearlyVisitors",visitorService.getYearlyVisitors());
        return "dashboard/info-dashboard";
    }

    @GetMapping("/contact-information")
    public String showContactInformationForm(Model model) {
        model.addAttribute("contactInformationDto", contactInformationService.findContactInfo());
        return "dashboard/add-info-dashboard";
    }

    @PostMapping("/contact-information/save")
    public String saveContactInformation(@ModelAttribute("contactInformationDto") ContactInformationDto dto) {
        contactInformationService.saveContactInformation(dto);
        return "redirect:/admin/dashboard";
    }
}
