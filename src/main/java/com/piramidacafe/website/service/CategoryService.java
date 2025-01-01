package com.piramidacafe.website.service;


import com.piramidacafe.website.dto.SimpleCategoryDto;
import com.piramidacafe.website.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface CategoryService {

    void save(Category category);

    Optional<Category> getActiveCategoryById(int id);

    List<Category> getActiveCategoriesByMenuId(String menuId);

    void updateItem(Category category);


    Page<Category> getAllActiveCategories(int page, int size);


    Category getCategoryByIdFromDB(Long id);

    List<SimpleCategoryDto> getCategoriesByMenuId(Long menuId);

    List<SimpleCategoryDto> getAllActiveCategoriesByMenuName(String name);

}
