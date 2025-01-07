package com.piramidacafe.website.service;

import com.piramidacafe.website.dto.CategoryDto;

public interface CategoryHelperService {
    void deleteOldImage(CategoryDto dto);
    String saveAndGetPathOfImage(CategoryDto dto);
    void deleteCategoryImageFromStorage(String path);
}
