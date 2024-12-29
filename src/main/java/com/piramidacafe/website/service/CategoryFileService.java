package com.piramidacafe.website.service;

import com.piramidacafe.website.dto.CategoryDto;
import com.piramidacafe.website.model.Category;

public interface CategoryFileService {
    void deleteOldImage(CategoryDto dto);

    void getDeleteAndSave(CategoryDto dto);

    String saveAndGetPathOfImage(CategoryDto dto);

    void updateExistingCategory(Category category, CategoryDto dto, String imageUrl);

    void deleteCategoryImageFromStorage(String path);
}
