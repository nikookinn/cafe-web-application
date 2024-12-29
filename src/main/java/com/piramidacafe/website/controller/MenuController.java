package com.piramidacafe.website.controller;

import com.piramidacafe.website.Helper.ImageDirectory;
import com.piramidacafe.website.dto.MenuDto;
import com.piramidacafe.website.exeption.MenuNotFoundException;
import com.piramidacafe.website.mapper.MenuMapper;
import com.piramidacafe.website.model.Menu;
import com.piramidacafe.website.service.FileStorageService;
import com.piramidacafe.website.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("admin/dashboard/menu")
public class MenuController {

    private final MenuService menuService;
    private final MenuMapper menuMapper;
    private final FileStorageService fileStorageService;

    public MenuController(MenuService menuService, MenuMapper menuMapper, FileStorageService fileStorageService) {
        this.menuService = menuService;
        this.menuMapper = menuMapper;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping
    public String showMenuInfoPage(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   Model model){
        Page<Menu> menuPage = menuService.getAllActiveMenus(page, size);
        model.addAttribute("menuPage",menuPage);
        return "dashboard/menu-dashboard";
    }
    @GetMapping("/add")
    public String showMenuAddPage(@ModelAttribute("menuDto") MenuDto menuDto){
        return "dashboard/add-menu-dashboard";
    }


    @PostMapping("/save")
    public String saveMenu(@Valid @ModelAttribute("menuDto") MenuDto menuDto, BindingResult result){
        if (result.hasErrors()){
            return "dashboard/add-menu-dashboard";
        }
        String imageUrl = null;
        if (menuDto.getMenuImage() != null && !menuDto.getMenuImage().isEmpty()){
            imageUrl = fileStorageService.storeFile(menuDto.getMenuImage(), ImageDirectory.MENU_IMAGES.getDirectory());
        }

        Menu menu = menuMapper.toEntity(menuDto,imageUrl);
        menuService.save(menu);
        return "redirect:/admin/dashboard/menu";
    }
    @GetMapping("/update/{id}")
    public String showUpdatePage(@PathVariable("id") int id,Model model){
        Optional<Menu> menu = menuService.getActiveMenuById(id);
        if (menu.isEmpty()){
          throw new MenuNotFoundException("Menu with ID "+ id + " not found");
        }
        MenuDto menuDto = menuMapper.toDto(menu.get());
        model.addAttribute("menuDto",menuDto);
        return "dashboard/update-menu-dashboard";
    }
    @PostMapping("/process-update")
    public String updateMenu(@Valid @ModelAttribute("menuDto") MenuDto menuDto, BindingResult result){
        if (result.hasErrors()){
            return "dashboard/update-menu-dashboard";
        }
        Optional<Menu> existingMenu = menuService.getActiveMenuById(menuDto.getMenuId().intValue());
        if (existingMenu.isEmpty()){
            throw new MenuNotFoundException("Menu with ID " + menuDto.getMenuId() + " not found");
        }
        Menu menu = existingMenu.get();
        String imageUrl = menu.getImageUrl();
        if (menuDto.getMenuImage() !=null && !menuDto.getMenuImage().isEmpty()){
            fileStorageService.deleteOldImage(imageUrl,ImageDirectory.MENU_IMAGES.getDirectory());
            imageUrl = fileStorageService.storeFile(menuDto.getMenuImage(), ImageDirectory.MENU_IMAGES.getDirectory());
        }
        menu.setMenuId(menuDto.getMenuId());
        menu.setName(menuDto.getName());
        menu.setImageUrl(imageUrl);
        menuService.updateItem(menu);

        return "redirect:/admin/dashboard/menu";
    }

    @GetMapping("/delete/{id}")
    public String deleteMenu(@PathVariable("id") int id){
        Optional<Menu> existingMenu = menuService.getActiveMenuById(id);
        if (existingMenu.isEmpty()){
            throw new MenuNotFoundException("Menu with ID " + id + " not found");
        }
        fileStorageService.deleteOldImage(existingMenu.get().getImageUrl(),ImageDirectory.MENU_IMAGES.getDirectory());
        existingMenu.get().setActive(false);
        menuService.save(existingMenu.get());
        return "redirect:/admin/dashboard/menu";

    }


}
