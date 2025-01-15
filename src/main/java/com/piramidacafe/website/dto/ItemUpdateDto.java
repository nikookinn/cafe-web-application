package com.piramidacafe.website.dto;

import com.piramidacafe.website.model.Category;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
@Data
public class ItemUpdateDto {

    private Long itemId;

    private String name;

    private BigDecimal price;

    private String description;

    private String imageUrl;

    private Category category;

    private boolean isActive = true;

    private MultipartFile itemImage;

}
