package com.piramidacafe.website.service.serviceImpl;

import com.piramidacafe.website.enums.ImageDirectory;
import com.piramidacafe.website.dto.CategoryDto;
import com.piramidacafe.website.service.CategoryHelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryHelperServiceImpl implements CategoryHelperService {

    private final FileStorageService fileStorageService;


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
