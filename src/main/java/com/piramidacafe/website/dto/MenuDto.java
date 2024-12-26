package com.piramidacafe.website.dto;

import com.piramidacafe.website.annotations.ValidImageFile;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;


public class MenuDto {

    private Long menuId;

    @NotEmpty(message = "This field cannot be empty")
    private String name;

    private String existingImageUrl;

    @ValidImageFile
    private MultipartFile menuImage;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExistingImageUrl() {
        return existingImageUrl;
    }

    public void setExistingImageUrl(String existingImageUrl) {
        this.existingImageUrl = existingImageUrl;
    }

    public MultipartFile getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(MultipartFile menuImage) {
        this.menuImage = menuImage;
    }
}
