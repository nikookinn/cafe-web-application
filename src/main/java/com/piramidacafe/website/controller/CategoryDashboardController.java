package com.piramidacafe.website.controller;

import com.piramidacafe.website.dto.CategoryDto;
import com.piramidacafe.website.mapper.CategoryMapper;
import com.piramidacafe.website.model.Category;
import com.piramidacafe.website.model.Menu;
import com.piramidacafe.website.service.serviceImpl.CategoryFileServiceImpl;
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
    private final CategoryMapper categoryMapper;
    private final CategoryFileServiceImpl fileService;

    public CategoryDashboardController(CategoryService categoryService, MenuService menuService, CategoryMapper categoryMapper, CategoryFileServiceImpl fileService) {
        this.categoryService = categoryService;
        this.menuService = menuService;
        this.categoryMapper = categoryMapper;
        this.fileService = fileService;
    }

    @GetMapping
    public String showCategoryPage(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   Model model){
        model.addAttribute("categoryPage",categoryService.getAllActiveCategories(page, size));
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
        String imageUrl = fileService.saveAndGetPathOfImage(categoryDto);
        Category category = categoryMapper.toEntity(categoryDto, imageUrl);
        categoryService.save(category);
        return "redirect:/admin/dashboard/category";
    }
    @GetMapping("/update/{id}")
    public String showCategoryUpdatePage(@PathVariable("id") Long id,Model model){
        Category category = categoryService.getCategoryByIdFromDB(id);
        model.addAttribute("categoryDto",categoryMapper.toDto(category));
        model.addAttribute("menuList",menuService.getActiveMenus());
        return "dashboard/update-category-dashboard";
    }
    @PostMapping("/process-update")
    public String updateCategory(@Valid @ModelAttribute("categoryDto") CategoryDto categoryDto, BindingResult result){
        if (result.hasErrors()){
            return "dashboard/update-category-dashboard";
        }
        fileService.getDeleteAndSave(categoryDto);
        return "redirect:/admin/dashboard/category";
    }

    @GetMapping("/delete/{id}")
    public String deleteMenu(@PathVariable("id") Long id){
        Category category = categoryService.getCategoryByIdFromDB(id);
        fileService.deleteCategoryImageFromStorage(category.getImageUrl());
        category.setActive(false);
        categoryService.save(category);
        return "redirect:/admin/dashboard/category";

    }


}
