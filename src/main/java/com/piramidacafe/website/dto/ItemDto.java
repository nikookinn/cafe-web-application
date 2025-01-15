package com.piramidacafe.website.dto;

import com.piramidacafe.website.annotations.ValidImageFile;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
@Data
public class ItemDto {

    private Long itemId;

    @NotEmpty(message = "field cannot be empty")
    private String name;

    @NotNull(message = "field cannot be empty")
    private BigDecimal price;

    @NotEmpty(message = "field cannot be empty")
    private String description;

    private String imageUrl;
    @NotNull(message = "field cannot be empty")

    private Long menuId;
    @NotNull(message = "field cannot be empty")
    private Long categoryId;

    @ValidImageFile
    private MultipartFile itemImage;

    private boolean isActive = true;
}
