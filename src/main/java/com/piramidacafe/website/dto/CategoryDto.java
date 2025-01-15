package com.piramidacafe.website.dto;

import com.piramidacafe.website.annotations.ValidImageFile;
import com.piramidacafe.website.model.Menu;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CategoryDto {

    private Long categoryId;

    @NotEmpty(message = "This field cannot be empty")
    private String name;

    private Menu menu;

    private String imageUrl;

    @ValidImageFile
    private MultipartFile categoryImage;

    private boolean isActive = true;

}
