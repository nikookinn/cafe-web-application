package com.piramidacafe.website.dto;

import java.math.BigDecimal;

public class SimpleItemDto {
    private Long itemId;
    private String name;
    private BigDecimal price;
    private String description;
    private String imageUrl;

    public SimpleItemDto(Long itemId, String name, BigDecimal price, String description, String imageUrl) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "SimpleItemDto{" +
               "itemId=" + itemId +
               ", name='" + name + '\'' +
               ", price=" + price +
               ", description='" + description + '\'' +
               ", imageUrl='" + imageUrl + '\'' +
               '}';
    }
}
