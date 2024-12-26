package com.piramidacafe.website.service;

import com.piramidacafe.website.model.Menu;
import com.piramidacafe.website.repository.MenuRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MenuServiceImpl implements MenuService{

    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
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
}
