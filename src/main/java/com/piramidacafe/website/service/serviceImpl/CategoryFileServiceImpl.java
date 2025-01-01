package com.piramidacafe.website.service.serviceImpl;

import com.piramidacafe.website.Helper.ImageDirectory;
import com.piramidacafe.website.dto.CategoryDto;
import com.piramidacafe.website.model.Category;
import com.piramidacafe.website.service.CategoryFileService;
import com.piramidacafe.website.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryFileServiceImpl implements CategoryFileService {

    private final FileStorageService fileStorageService;
    private final CategoryService categoryService;

    public CategoryFileServiceImpl(FileStorageService fileStorageService, CategoryService categoryService) {
        this.fileStorageService = fileStorageService;
        this.categoryService = categoryService;
    }

    @Override
    public void deleteOldImage(CategoryDto dto){
        if (dto.getCategoryImage() !=null && !dto.getCategoryImage().isEmpty()){
            fileStorageService.deleteOldImage(dto.getImageUrl(), ImageDirectory.CATEGORY_IMAGES.getDirectory());
        }
    }
    @Override
    public void getDeleteAndSave(CategoryDto dto){
        Category category = categoryService.getCategoryByIdFromDB(dto.getCategoryId());
        deleteOldImage(dto);
        String imageUrl = saveAndGetPathOfImage(dto);
        updateExistingCategory(category,dto,imageUrl);
    }
    @Override
    public String saveAndGetPathOfImage(CategoryDto dto){
        if (dto.getCategoryImage() !=null && !dto.getCategoryImage().isEmpty()){
            return fileStorageService.storeFile(dto.getCategoryImage(), ImageDirectory.CATEGORY_IMAGES.getDirectory());
        }
        return dto.getImageUrl();
    }
    @Override
    public void updateExistingCategory(Category category, CategoryDto dto, String imageUrl){
        category.setCategoryId(dto.getCategoryId());
        category.setName(dto.getName());
        category.setMenu(dto.getMenu());
        if (imageUrl !=null){
            category.setImageUrl(imageUrl);
        }
        categoryService.updateItem(category);
    }

    @Override
    public void deleteCategoryImageFromStorage(String path) {
        if (path != null){
            fileStorageService.deleteOldImage(path, ImageDirectory.CATEGORY_IMAGES.getDirectory());
        }

    }
}
