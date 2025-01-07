package com.piramidacafe.website.service.serviceImpl;

import com.piramidacafe.website.Helper.ImageDirectory;
import com.piramidacafe.website.dto.CategoryDto;
import com.piramidacafe.website.service.CategoryHelperService;
import org.springframework.stereotype.Service;

@Service
public class CategoryHelperServiceImpl implements CategoryHelperService {

    private final FileStorageService fileStorageService;


    public CategoryHelperServiceImpl(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @Override
    public void deleteOldImage(CategoryDto dto){
        if (dto.getCategoryImage() !=null && !dto.getCategoryImage().isEmpty()){
            fileStorageService.deleteOldImage(dto.getImageUrl(), ImageDirectory.CATEGORY_IMAGES.getDirectory());
        }
    }
    @Override
    public String saveAndGetPathOfImage(CategoryDto dto){
        if (dto.getCategoryImage() !=null && !dto.getCategoryImage().isEmpty()){
            return fileStorageService.storeFile(dto.getCategoryImage(), ImageDirectory.CATEGORY_IMAGES.getDirectory());
        }
        return dto.getImageUrl();
    }

    @Override
    public void deleteCategoryImageFromStorage(String path) {
        if (path != null){
            fileStorageService.deleteOldImage(path, ImageDirectory.CATEGORY_IMAGES.getDirectory());
        }
    }
}
