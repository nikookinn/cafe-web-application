package com.piramidacafe.website.controller;

import com.piramidacafe.website.Helper.ImageDirectory;
import com.piramidacafe.website.dto.ItemDto;
import com.piramidacafe.website.dto.ItemUpdateDto;
import com.piramidacafe.website.dto.MenuDto;
import com.piramidacafe.website.dto.SimpleCategoryDto;
import com.piramidacafe.website.exeption.ItemNotFoundException;
import com.piramidacafe.website.exeption.MenuNotFoundException;
import com.piramidacafe.website.mapper.ItemMapper;
import com.piramidacafe.website.model.Category;
import com.piramidacafe.website.model.Item;
import com.piramidacafe.website.model.Menu;
import com.piramidacafe.website.service.CategoryService;
import com.piramidacafe.website.service.FileStorageService;
import com.piramidacafe.website.service.ItemService;
import com.piramidacafe.website.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/dashboard/item")
public class ItemController {

    private final ItemService itemService;
    private final MenuService menuService;
    private final CategoryService categoryService;
    private final FileStorageService fileStorageService;
    private final ItemMapper itemMapper;

    public ItemController(ItemService itemService, MenuService menuService, CategoryService categoryService, FileStorageService fileStorageService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.menuService = menuService;
        this.categoryService = categoryService;
        this.fileStorageService = fileStorageService;
        this.itemMapper = itemMapper;
    }

    @GetMapping
    public String showItemPage(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   Model model){
        model.addAttribute("itemPage",itemService.getAllActiveItems(page, size));
        return "dashboard/item-dashboard";
    }

    @GetMapping("/add")
    public String showItemAddPage(Model model){
        model.addAttribute("menuList",menuService.getActiveMenus());
        model.addAttribute("itemDto",new ItemDto());
        return "dashboard/add-item-dashboard";
    }
    @PostMapping("/save")
    public String saveItem(@Valid @ModelAttribute("itemDto") ItemDto itemDto,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Menu> menuList = menuService.getActiveMenus();
            model.addAttribute("menuList", menuList);
            return "dashboard/add-item-dashboard";
        }

        String imageUrl = null;
        if (itemDto.getItemImage() != null && !itemDto.getItemImage().isEmpty()) {
            imageUrl = fileStorageService.storeFile(itemDto.getItemImage(), ImageDirectory.ITEM_IMAGES.getDirectory());
        }
        Category category = categoryService.getCategoryByIdFromDB(itemDto.getCategoryId());
        Item item = itemMapper.toEntity(itemDto,category,imageUrl);
        itemService.save(item);
        return "redirect:/admin/dashboard/item";
    }
    @GetMapping("/update/{id}")
    public String showItemUpdatePage(@PathVariable("id") int id,Model model){
        model.addAttribute("menuList",menuService.getActiveMenus());
        model.addAttribute("itemDto",itemService.getActiveItemById(id));
        return "dashboard/update-item-dashboard";
    }
    @PostMapping("/process-update")
    public String updateItem(@Valid @ModelAttribute("menuDto")ItemUpdateDto updateDto, BindingResult result,Model model){
        if (result.hasErrors()){
            List<Menu> menuList = menuService.getActiveMenus();
            model.addAttribute("menuList", menuList);
            return "dashboard/update-menu-dashboard";
        }
        Item item = itemService.findActiveItemById(updateDto.getItemId().intValue());

        String imageUrl = item.getImageUrl();
        if (updateDto.getItemImage() !=null && !updateDto.getItemImage().isEmpty()){
            fileStorageService.deleteOldImage(imageUrl,ImageDirectory.ITEM_IMAGES.getDirectory());
            imageUrl = fileStorageService.storeFile(updateDto.getItemImage(), ImageDirectory.ITEM_IMAGES.getDirectory());
        }
        item.setItemId(updateDto.getItemId());
        item.setName(updateDto.getName());
        item.setPrice(updateDto.getPrice());
        item.setDescription(updateDto.getDescription());
        item.setCategory(updateDto.getCategory());
        if (updateDto.getItemImage() !=null && !updateDto.getItemImage().isEmpty())
        {
            item.setImageUrl(imageUrl);
        }else {
            item.setImageUrl(null);
        }

        itemService.updateItem(item);

        return "redirect:/admin/dashboard/item";
    }






    @GetMapping("/categories")
    @ResponseBody
    public List<SimpleCategoryDto> getCategoriesByMenu(@RequestParam Long menuId) {
        List<SimpleCategoryDto> dto = categoryService.getCategoriesByMenuId(menuId);
        dto.forEach(System.out::println);
        return dto;
    }



}
