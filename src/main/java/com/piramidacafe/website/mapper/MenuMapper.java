package com.piramidacafe.website.mapper;

import com.piramidacafe.website.dto.MenuDto;
import com.piramidacafe.website.model.Menu;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper {

    public MenuDto toDto(Menu menu){
        MenuDto menuDto = new MenuDto();
        menuDto.setMenuId(menu.getMenuId());
        menuDto.setName(menu.getName());
        menuDto.setExistingImageUrl(menu.getImageUrl());
        return menuDto;
    }

    public Menu toEntity(MenuDto menuDto, String imageUrl){
        Menu entity = new Menu();
        entity.setName(menuDto.getName());
        if (menuDto.getMenuImage() !=null && !menuDto.getMenuImage().isEmpty()){
            entity.setImageUrl(imageUrl);
        }else {
            entity.setImageUrl(entity.getImageUrl());
        }
        return entity;
    }
    public Menu toEntity(MenuDto menuDto,Menu entity, String imageUrl){
        entity.setMenuId(menuDto.getMenuId());
        entity.setName(menuDto.getName());
        if (menuDto.getMenuImage() !=null && !menuDto.getMenuImage().isEmpty()){
            entity.setImageUrl(imageUrl);
        }else {
            entity.setImageUrl(entity.getImageUrl());
        }
        return entity;
    }


}
