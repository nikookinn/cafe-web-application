package com.piramidacafe.website.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleMenuDto {

    private Long itemId;

    private String name;

    private BigDecimal price;

    private String description;

    private String imageUrl;

    private String menuName;

    private String categoryName;

}
