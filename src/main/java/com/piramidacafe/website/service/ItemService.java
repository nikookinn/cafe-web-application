package com.piramidacafe.website.service;

import com.piramidacafe.website.dto.ItemUpdateDto;
import com.piramidacafe.website.dto.SimpleItemDto;
import com.piramidacafe.website.dto.SimpleMenuDto;
import com.piramidacafe.website.model.Item;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ItemService {

    void save(Item item);

    ItemUpdateDto getActiveItemById(int id);
    Item findActiveItemById(int id);

    List<Item> getActiveItemsByCategory(String catName);
    List<SimpleItemDto> getAllActiveItemsByCategoryName(String catName);
    List<SimpleItemDto> getAllActiveItemsByCategoryId(int id);

    void updateItem(Item item);

    void markItemAsInactive(int id);

    Page<SimpleMenuDto> getAllActiveItems(int page, int size);
}
