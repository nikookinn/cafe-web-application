
package com.piramidacafe.website.dto;

import jakarta.validation.constraints.NotNull;


public class SimpleCategoryDto {
    @NotNull(message = "Cannot be empty!")
    private Long categoryId;
    private String name;

    public SimpleCategoryDto() {
    }

    public SimpleCategoryDto(Long categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "SimpleCategoryDto{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                '}';
    }
}
