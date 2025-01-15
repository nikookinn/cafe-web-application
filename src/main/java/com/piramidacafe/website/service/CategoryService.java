package com.piramidacafe.website.service;


import com.piramidacafe.website.dto.CategoryDto;
import com.piramidacafe.website.dto.SimpleCategoryDto;
import com.piramidacafe.website.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CategoryService {

    void saveCategory(CategoryDto dto);
    Category findActiveCategoryById(Long id);
    void updateCategory(CategoryDto dto);
    void markCategoryAsInactive(int id);
    Page<Category> findAllActiveCategories(int page, int size);
    CategoryDto  findExistingCategoryById(Long id);
    List<SimpleCategoryDto> findCategoriesByMenuId(Long menuId);
    List<SimpleCategoryDto> findAllActiveCategoriesByMenuName(String name);

}
