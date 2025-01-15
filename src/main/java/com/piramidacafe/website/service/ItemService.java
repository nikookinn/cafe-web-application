package com.piramidacafe.website.service;

import com.piramidacafe.website.dto.ItemDto;
import com.piramidacafe.website.dto.ItemUpdateDto;
import com.piramidacafe.website.dto.SimpleItemDto;
import com.piramidacafe.website.dto.SimpleMenuDto;
import com.piramidacafe.website.model.Item;
import org.springframework.data.domain.Page;
import java.util.List;

public interface ItemService {

    void saveItem(ItemDto itemDto);

    ItemUpdateDto getActiveItemById(int id);

    Item findActiveItemById(int id);

    List<SimpleItemDto> getAllActiveItemsByCategoryId(int id);

    void updateItem(ItemUpdateDto updateDto);

    void markItemAsInactive(int id);

    Page<SimpleMenuDto> getAllActiveItems(int page, int size);
}
