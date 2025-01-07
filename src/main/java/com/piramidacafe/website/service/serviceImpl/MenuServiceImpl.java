package com.piramidacafe.website.service.serviceImpl;

import com.piramidacafe.website.Helper.ImageDirectory;
import com.piramidacafe.website.dto.MenuDto;
import com.piramidacafe.website.exception.MenuNotFoundException;
import com.piramidacafe.website.mapper.MenuMapper;
import com.piramidacafe.website.model.Menu;
import com.piramidacafe.website.repository.MenuRepository;
import com.piramidacafe.website.service.MenuService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;
    private final FileStorageService fileStorageService;

    public MenuServiceImpl(MenuRepository menuRepository, MenuMapper menuMapper, FileStorageService fileStorageService) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public void saveMenu(MenuDto menuDto) {
        String imageUrl = null;
        if (menuDto.getMenuImage() != null && !menuDto.getMenuImage().isEmpty()){
            imageUrl = fileStorageService.storeFile(menuDto.getMenuImage(), ImageDirectory.MENU_IMAGES.getDirectory());
        }
        Menu menu = menuMapper.toEntity(menuDto,imageUrl);
        menuRepository.save(menu);
    }

    @Override
    public MenuDto getActiveMenuById(int id) {
        Menu menu = menuRepository.findByMenuIdAndIsActiveIsTrue(id)
                .orElseThrow(()->new MenuNotFoundException("Menu with ID "+ id + " not found"));
        return menuMapper.toDto(menu);
    }

    @Override
    public List<Menu> getActiveMenus() {
        return menuRepository.findByIsActiveIsTrue();
    }

    @Override
    public void updateMenu(MenuDto menuDto) {
        Menu menu = findActiveMenuByMenuId(menuDto.getMenuId().intValue());
        String imageUrl = menu.getImageUrl();
        if (menuDto.getMenuImage() !=null && !menuDto.getMenuImage().isEmpty()){
            fileStorageService.deleteOldImage(imageUrl,ImageDirectory.MENU_IMAGES.getDirectory());
            imageUrl = fileStorageService.storeFile(menuDto.getMenuImage(), ImageDirectory.MENU_IMAGES.getDirectory());
        }
        menuRepository.save(menuMapper.toEntity(menuDto,menu,imageUrl));
    }

    @Override
    public Page<Menu> getAllActiveMenus(int page, int size) {
        return menuRepository.findAllByIsActiveIsTrue(PageRequest.of(page, size));
    }

    @Override
    public MenuDto getMenuByNameIfIsActive(String menuName) {
        Menu menu = menuRepository.findByNameAndIsActiveIsTrue(menuName)
                .orElseThrow(()-> new MenuNotFoundException("Menu not found with name :"+menuName));
        return menuMapper.toDto(menu);
    }

    @Override
    public Menu findActiveMenuByMenuId(int id) {
        return menuRepository.findByMenuIdAndIsActiveIsTrue(id)
                .orElseThrow(()->new MenuNotFoundException("Menu with ID "+ id + " not found"));
    }

    @Override
    public void markMenuAsInactive(int id) {
        Menu existingMenu = findActiveMenuByMenuId(id);
        fileStorageService.deleteOldImage(existingMenu.getImageUrl(),ImageDirectory.MENU_IMAGES.getDirectory());
        existingMenu.setActive(false);
        menuRepository.save(existingMenu);
    }
}
