package com.piramidacafe.website.dto;

import com.piramidacafe.website.annotations.ValidImageFile;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MenuDto {

    private Long menuId;

    @NotEmpty(message = "This field cannot be empty")
    private String name;

    private String existingImageUrl;

    @ValidImageFile
    private MultipartFile menuImage;

}
