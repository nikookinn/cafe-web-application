package com.piramidacafe.website.service.serviceImpl;

import com.piramidacafe.website.dto.CategoryDto;
import com.piramidacafe.website.dto.SimpleCategoryDto;
import com.piramidacafe.website.exception.CategoryNotFoundException;
import com.piramidacafe.website.mapper.CategoryMapper;
import com.piramidacafe.website.model.Category;
import com.piramidacafe.website.repository.CategoryRepository;
import com.piramidacafe.website.service.CategoryHelperService;
import com.piramidacafe.website.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryHelperService helperService;
    private final CategoryMapper categoryMapper;

    @Override
    public void saveCategory(CategoryDto dto) {
        String imageUrl = helperService.saveAndGetPathOfImage(dto);
        Category category = categoryMapper.toEntity(dto, imageUrl);
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(CategoryDto dto) {
        Category category = findActiveCategoryById(dto.getCategoryId());
        helperService.deleteOldImage(dto);
        String url = helperService.saveAndGetPathOfImage(dto);
        category = categoryMapper.mapExistingCatToCategory(category, dto, url);
        categoryRepository.save(category);
    }

    @Override
    public void markCategoryAsInactive(int id) {
        Category category = findActiveCategoryById((long) id);
        helperService.deleteCategoryImageFromStorage(category.getImageUrl());
        category.setActive(false);
        categoryRepository.save(category);
    }

    @Override
    public Page<Category> findAllActiveCategories(int page, int size) {
        return categoryRepository.findAllByIsActiveIsTrue(PageRequest.of(page, size));
    }

    @Override
    public Category findActiveCategoryById(Long id) {
        Optional<Category> existingCategory = categoryRepository.findByCategoryIdAndIsActiveIsTrue(id.intValue());
        if (existingCategory.isEmpty()) {
            throw new CategoryNotFoundException("Category with ID " + id + " not found");
        }
        return existingCategory.get();
    }

    @Override
    public CategoryDto findExistingCategoryById(Long id) {
        return categoryMapper.toDto(findActiveCategoryById(id));
    }

    @Override
    public List<SimpleCategoryDto> findCategoriesByMenuId(Long menuId) {
        return categoryRepository.findCategoriesByMenuIdAndIsActiveTrue(menuId);
    }

    @Override
    public List<SimpleCategoryDto> findAllActiveCategoriesByMenuName(String name) {
        List<Category> categories = categoryRepository.getAllActiveCategoriesByMenuName(name);
        return categories.stream()
                .map(category -> new SimpleCategoryDto(category.getCategoryId(), category.getName()))
                .collect(Collectors.toList());
    }


}
