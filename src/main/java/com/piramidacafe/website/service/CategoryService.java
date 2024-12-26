package com.piramidacafe.website.service;

import com.piramidacafe.website.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface CategoryService {

    void save(Category category);

    Optional<Category> getActiveCategoryById(int id);

    List<Category> getActiveCategoriesByMenuId(String menuId);

    void updateItem(Category category);

    void markCategoryAsInactive(int id);
}
