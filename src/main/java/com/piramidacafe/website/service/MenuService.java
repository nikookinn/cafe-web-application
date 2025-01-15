package com.piramidacafe.website.service;

import com.piramidacafe.website.dto.MenuDto;
import com.piramidacafe.website.model.Menu;
import org.springframework.data.domain.Page;
import java.util.List;

public interface MenuService {
    void saveMenu(MenuDto menuDto);

    MenuDto getActiveMenuById(int id);

    List<Menu> getActiveMenus();

    void updateMenu(MenuDto menuDto);

    Page<Menu> getAllActiveMenus(int page, int size);

    MenuDto getMenuByNameIfIsActive(String menuName);
    Menu findActiveMenuByMenuId(int id);
    void markMenuAsInactive(int id);
}
