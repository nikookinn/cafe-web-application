package com.piramidacafe.website.service.serviceImpl;

import com.piramidacafe.website.dto.SimpleCategoryDto;
import com.piramidacafe.website.exception.CategoryNotFoundException;
import com.piramidacafe.website.model.Category;
import com.piramidacafe.website.repository.CategoryRepository;
import com.piramidacafe.website.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Optional<Category> getActiveCategoryById(int id) {
        return categoryRepository.findByCategoryIdAndIsActiveIsTrue(id);
    }

    @Override
    public List<Category> getActiveCategoriesByMenuId(String menuId) {
        return null;
    }

    @Override
    public void updateItem(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Page<Category> getAllActiveCategories(int page, int size) {
        return categoryRepository.findAllByIsActiveIsTrue(PageRequest.of(page, size));
    }

    @Override
    public Category getCategoryByIdFromDB(Long id) {
        Optional<Category> existingCategory = getActiveCategoryById(id.intValue());
        if (existingCategory.isEmpty()){
            throw new CategoryNotFoundException("Category with ID " + id + " not found");
        }
        return existingCategory.get();
    }

    @Override
    public List<SimpleCategoryDto> getCategoriesByMenuId(Long menuId) {
        return categoryRepository.findCategoriesByMenuIdAndIsActiveTrue(menuId);
    }

    @Override
    public List<SimpleCategoryDto> getAllActiveCategoriesByMenuName(String name) {
        List<Category> categories = categoryRepository.getAllActiveCategoriesByMenuName(name);
        return categories.stream()
                .map(category -> new SimpleCategoryDto(category.getCategoryId(), category.getName()))
                .collect(Collectors.toList());
    }


}
