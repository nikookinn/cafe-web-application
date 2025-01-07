package com.piramidacafe.website.mapper;

import com.piramidacafe.website.dto.CategoryDto;
import com.piramidacafe.website.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryDto categoryDto, String imageUrl) {
        Category entity = new Category();
        entity.setCategoryId(categoryDto.getCategoryId());
        entity.setName(categoryDto.getName());
        entity.setMenu(categoryDto.getMenu());
        entity.setActive(categoryDto.isActive());

        if (categoryDto.getCategoryImage() != null && !categoryDto.getCategoryImage().isEmpty()) {
            entity.setImageUrl(imageUrl);
        } else {
            entity.setImageUrl(null);
        }
        return entity;
    }

    public CategoryDto toDto(Category category){
        CategoryDto dto = new CategoryDto();
        dto.setCategoryId(category.getCategoryId());
        dto.setName(category.getName());
        dto.setMenu(category.getMenu());
        dto.setImageUrl(category.getImageUrl());
        return dto;
    }

    public Category mapExistingCatToCategory(Category category, CategoryDto dto,String url) {
        category.setCategoryId(dto.getCategoryId());
        category.setName(dto.getName());
        category.setMenu(dto.getMenu());
        if (url !=null){
            category.setImageUrl(url);
        }
        return category;
    }
}
