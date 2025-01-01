package com.piramidacafe.website.service;

import com.piramidacafe.website.dto.MenuDto;
import com.piramidacafe.website.model.Menu;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface MenuService {
    void save(Menu menu);

    Optional<Menu> getActiveMenuById(int id);

    List<Menu> getActiveMenus();

    void updateItem(Menu menu);


    Page<Menu> getAllActiveMenus(int page, int size);

    MenuDto getMenuByNameIfIsActive(String menuName);
}
