package com.piramidacafe.website.controller;

import com.piramidacafe.website.dto.ItemDto;
import com.piramidacafe.website.dto.ItemUpdateDto;
import com.piramidacafe.website.dto.SimpleCategoryDto;
import com.piramidacafe.website.model.Menu;
import com.piramidacafe.website.service.CategoryService;
import com.piramidacafe.website.service.ItemService;
import com.piramidacafe.website.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/dashboard/item")
public class ItemDashboardController {

    private final ItemService itemService;
    private final MenuService menuService;
    private final CategoryService categoryService;


    @GetMapping
    public String showItemPage(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               Model model) {
        model.addAttribute("itemPage", itemService.getAllActiveItems(page, size));
        return "dashboard/item-dashboard";
    }

    @GetMapping("/add")
    public String showItemAddPage(Model model) {
        model.addAttribute("menuList", menuService.getActiveMenus());
        model.addAttribute("itemDto", new ItemDto());
        return "dashboard/add-item-dashboard";
    }

    @PostMapping("/save")
    public String saveItem(@Valid @ModelAttribute("itemDto") ItemDto itemDto,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("menuList", menuService.getActiveMenus());
            return "dashboard/add-item-dashboard";
        }
        itemService.saveItem(itemDto);
        return "redirect:/admin/dashboard/item";
    }

    @GetMapping("/update/{id}")
    public String showItemUpdatePage(@PathVariable("id") int id, Model model) {
        model.addAttribute("menuList", menuService.getActiveMenus());
        model.addAttribute("itemDto", itemService.getActiveItemById(id));
        return "dashboard/update-item-dashboard";
    }

    @PostMapping("/process-update")
    public String updateItem(@Valid @ModelAttribute("updateDto") ItemUpdateDto updateDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Menu> menuList = menuService.getActiveMenus();
            model.addAttribute("menuList", menuList);
            return "dashboard/update-item-dashboard";
        }
        itemService.updateItem(updateDto);
        return "redirect:/admin/dashboard/item";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") int id) {
        itemService.markItemAsInactive(id);
        return "redirect:/admin/dashboard/item";
    }

    @GetMapping("/categories")
    @ResponseBody
    public List<SimpleCategoryDto> getCategoriesByMenu(@RequestParam Long menuId) {
        return categoryService.findCategoriesByMenuId(menuId);
    }


}
