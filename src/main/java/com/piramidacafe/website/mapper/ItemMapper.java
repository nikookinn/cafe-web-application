package com.piramidacafe.website.mapper;

import com.piramidacafe.website.dto.ItemDto;
import com.piramidacafe.website.dto.ItemUpdateDto;
import com.piramidacafe.website.model.Category;
import com.piramidacafe.website.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public Item toEntity(ItemDto itemDto, Category category, String imageUrl){
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        item.setDescription(itemDto.getDescription());
        item.setCategory(category);

        if (itemDto.getItemImage() !=null && !itemDto.getItemImage().isEmpty()){
            item.setImageUrl(imageUrl);
        }else {
            item.setImageUrl(null);
        }
        return item;
    }
    public Item toEntity(ItemUpdateDto updateDto, String imageUrl){
        Item item = new Item();
        item.setItemId(updateDto.getItemId());
        item.setName(updateDto.getName());
        item.setPrice(updateDto.getPrice());
        item.setDescription(updateDto.getDescription());
        item.setCategory(updateDto.getCategory());
        if (updateDto.getItemImage() !=null && !updateDto.getItemImage().isEmpty()){
            item.setImageUrl(imageUrl);
        }else {
            item.setImageUrl(null);
        }
        return item;
    }

    public ItemUpdateDto toDto(Item item) {
        ItemUpdateDto itemDto = new ItemUpdateDto();
        itemDto.setItemId(item.getItemId());
        itemDto.setName(item.getName());
        itemDto.setPrice(item.getPrice());
        itemDto.setDescription(item.getDescription());
        itemDto.setImageUrl(item.getImageUrl());
        itemDto.setCategory(item.getCategory());
        return itemDto;

    }
}
