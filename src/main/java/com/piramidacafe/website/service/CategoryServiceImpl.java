package com.piramidacafe.website.service;

import com.piramidacafe.website.model.Category;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService{
    @Override
    public void save(Category category) {

    }

    @Override
    public Optional<Category> getActiveCategoryById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Category> getActiveCategoriesByMenuId(String menuId) {
        return null;
    }

    @Override
    public void updateItem(Category category) {

    }

    @Override
    public void markCategoryAsInactive(int id) {

    }
}
