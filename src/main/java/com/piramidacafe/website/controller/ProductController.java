package com.piramidacafe.website.controller;

import com.piramidacafe.website.dto.*;
import com.piramidacafe.website.exception.MenuNotFoundException;
import com.piramidacafe.website.service.CategoryService;
import com.piramidacafe.website.service.ItemService;
import com.piramidacafe.website.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/menu")
public class ProductController {
    private final CategoryService categoryService;
    private final ItemService itemService;
    private final MenuService menuService;

    public ProductController(CategoryService categoryService, ItemService itemService, MenuService menuService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.menuService = menuService;
    }

    @GetMapping("/{menuName}")
    public String getProductPage(@PathVariable String menuName, Model model) {
        try {
            String menu = menuName.replace("-", " ").toLowerCase();
            List<SimpleCategoryDto> categories = categoryService.getAllActiveCategoriesByMenuName(menu);
            model.addAttribute("categories", categories);
            model.addAttribute("menuDto", menuService.getMenuByNameIfIsActive(menu));

        }catch (MenuNotFoundException e){
            System.err.println("Menu not found: " + e.getMessage());
        }
        return "main/menu-details";
    }

    @ResponseBody
    @GetMapping("/api/items")
    public ResponseEntity<List<SimpleItemDto>> getMenuItemsByCategoryId(@RequestParam int id) {
        List<SimpleItemDto> items = itemService.getAllActiveItemsByCategoryId(id);
        return ResponseEntity.ok(items);
    }
}
