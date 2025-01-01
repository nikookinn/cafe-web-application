package com.piramidacafe.website.service.serviceImpl;

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

    public MenuServiceImpl(MenuRepository menuRepository, MenuMapper menuMapper) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }

    @Override
    public void save(Menu menu) {
        menuRepository.save(menu);
    }

    @Override
    public Optional<Menu> getActiveMenuById(int id) {
        return menuRepository.findByMenuIdAndIsActiveIsTrue(id);
    }

    @Override
    public List<Menu> getActiveMenus() {
        return menuRepository.findByIsActiveIsTrue();
    }

    @Override
    public void updateItem(Menu menu) {
        menuRepository.save(menu);
    }

    @Override
    public Page<Menu> getAllActiveMenus(int page, int size) {
        return menuRepository.findAllByIsActiveIsTrue(PageRequest.of(page, size));
    }

    @Override
    public MenuDto getMenuByNameIfIsActive(String menuName) {
        Menu menu = menuRepository.findByNameAndIsActiveIsTrue(menuName).orElseThrow(()-> new MenuNotFoundException("Menu not found with name :"+menuName));
        return menuMapper.toDto(menu);
    }
}
