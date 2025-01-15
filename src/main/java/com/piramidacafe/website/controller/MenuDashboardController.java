package com.piramidacafe.website.controller;

import com.piramidacafe.website.Helper.ImageDirectory;
import com.piramidacafe.website.dto.MenuDto;
import com.piramidacafe.website.exception.MenuNotFoundException;
import com.piramidacafe.website.mapper.MenuMapper;
import com.piramidacafe.website.model.Menu;
import com.piramidacafe.website.service.serviceImpl.FileStorageService;
import com.piramidacafe.website.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/dashboard/menu")
public class MenuDashboardController {

    private final MenuService menuService;


    @GetMapping
    public String showMenuInfoPage(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   Model model) {
        model.addAttribute("menuPage", menuService.getAllActiveMenus(page, size));
        return "dashboard/menu-dashboard";
    }

    @GetMapping("/add")
    public String showMenuAddPage(@ModelAttribute("menuDto") MenuDto menuDto) {
        return "dashboard/add-menu-dashboard";
    }

    @PostMapping("/save")
    public String saveMenu(@Valid @ModelAttribute("menuDto") MenuDto menuDto, BindingResult result) {
        if (result.hasErrors()) {
            return "dashboard/add-menu-dashboard";
        }
        menuService.saveMenu(menuDto);
        return "redirect:/admin/dashboard/menu";
    }

    @GetMapping("/update/{id}")
    public String showUpdatePage(@PathVariable("id") int id, Model model) {
        model.addAttribute("menuDto", menuService.getActiveMenuById(id));
        return "dashboard/update-menu-dashboard";
    }

    @PostMapping("/process-update")
    public String updateMenu(@Valid @ModelAttribute("menuDto") MenuDto menuDto, BindingResult result) {
        if (result.hasErrors()) {
            return "dashboard/update-menu-dashboard";
        }
        menuService.updateMenu(menuDto);
        return "redirect:/admin/dashboard/menu";
    }

    @GetMapping("/delete/{id}")
    public String deleteMenu(@PathVariable("id") int id) {
        menuService.markMenuAsInactive(id);
        return "redirect:/admin/dashboard/menu";
    }


}
