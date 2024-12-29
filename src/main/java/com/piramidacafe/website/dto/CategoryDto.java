package com.piramidacafe.website.dto;

import com.piramidacafe.website.annotations.ValidImageFile;
import com.piramidacafe.website.model.Menu;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class CategoryDto {
    private Long categoryId;
    @NotEmpty(message = "This field cannot be empty")
    private String name;

    private Menu menu;

    private String imageUrl;
    @ValidImageFile
    private MultipartFile categoryImage;


    private boolean isActive = true;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public MultipartFile getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(MultipartFile categoryImage) {
        this.categoryImage = categoryImage;
    }
}
