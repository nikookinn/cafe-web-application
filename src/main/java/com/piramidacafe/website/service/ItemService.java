package com.piramidacafe.website.service;

import com.piramidacafe.website.dto.ItemDto;
import com.piramidacafe.website.dto.ItemUpdateDto;
import com.piramidacafe.website.dto.SimpleMenuDto;
import com.piramidacafe.website.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ItemService {

    void save(Item item);

    ItemUpdateDto getActiveItemById(int id);
    Item findActiveItemById(int id);

    List<Item> getActiveItemsByCategory(String catName);

    void updateItem(Item item);

    void markItemAsInactive(int id);

    Page<SimpleMenuDto> getAllActiveItems(int page, int size);
}
