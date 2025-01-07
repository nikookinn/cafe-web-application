package com.piramidacafe.website.controller;

import com.piramidacafe.website.dto.CategoryDto;
import com.piramidacafe.website.model.Menu;
import com.piramidacafe.website.service.CategoryService;
import com.piramidacafe.website.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/dashboard/category")
public class CategoryDashboardController {

    private final CategoryService categoryService;
    private final MenuService menuService;

    public CategoryDashboardController(CategoryService categoryService, MenuService menuService) {
        this.categoryService = categoryService;
        this.menuService = menuService;
    }

    @GetMapping
    public String showCategoryPage(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   Model model){
        model.addAttribute("categoryPage",categoryService.findAllActiveCategories(page, size));
        return "dashboard/category-dashboard";
    }
    @GetMapping("/add")
    public String showCategoryAddPage(Model model){
        model.addAttribute("categoryDto",new CategoryDto());
        model.addAttribute("menuList",menuService.getActiveMenus());
        return "dashboard/add-category-dashboard";
    }
    @PostMapping("/save")
    public String saveCategory(@Valid @ModelAttribute("categoryDto") CategoryDto categoryDto,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Menu> menuList = menuService.getActiveMenus();
            model.addAttribute("menuList", menuList);
            return "dashboard/add-category-dashboard";
        }
        categoryService.saveCategory(categoryDto);
        return "redirect:/admin/dashboard/category";
    }
    @GetMapping("/update/{id}")
    public String showCategoryUpdatePage(@PathVariable("id") Long id,Model model){
        model.addAttribute("categoryDto",categoryService.findExistingCategoryById(id));
        model.addAttribute("menuList",menuService.getActiveMenus());
        return "dashboard/update-category-dashboard";
    }
    @PostMapping("/process-update")
    public String updateCategory(@Valid @ModelAttribute("categoryDto") CategoryDto categoryDto, BindingResult result){
        if (result.hasErrors()){
            return "dashboard/update-category-dashboard";
        }
        categoryService.updateCategory(categoryDto);
        return "redirect:/admin/dashboard/category";
    }

    @GetMapping("/delete/{id}")
    public String deleteMenu(@PathVariable("id") int id){
        categoryService.markCategoryAsInactive(id);
        return "redirect:/admin/dashboard/category";
    }


}
