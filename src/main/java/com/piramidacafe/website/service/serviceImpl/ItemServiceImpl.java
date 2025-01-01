package com.piramidacafe.website.service.serviceImpl;

import com.piramidacafe.website.dto.ItemUpdateDto;
import com.piramidacafe.website.dto.SimpleItemDto;
import com.piramidacafe.website.dto.SimpleMenuDto;
import com.piramidacafe.website.exception.ItemNotFoundException;
import com.piramidacafe.website.mapper.ItemMapper;
import com.piramidacafe.website.model.Item;
import com.piramidacafe.website.repository.ItemRepository;
import com.piramidacafe.website.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Override
    public ItemUpdateDto getActiveItemById(int id) {
        Item item = itemRepository.findByItemIdAndIsActiveIsTrue(id).orElseThrow(() -> new ItemNotFoundException("There is no yitem with id "+id));
        return itemMapper.toDto(item);
    }

    @Override
    public Item findActiveItemById(int id) {
        return itemRepository.findByItemIdAndIsActiveIsTrue(id).orElseThrow(()-> new ItemNotFoundException("There is no yitem with id "+id));
    }

    @Override
    public List<Item> getActiveItemsByCategory(String catName) {
        return null;
    }

    @Override
    public List<SimpleItemDto> getAllActiveItemsByCategoryName(String catName) {
        return itemRepository.findAllItemsByCategoryNameAndIsActiveIsTrue(catName);
    }

    @Override
    public List<SimpleItemDto> getAllActiveItemsByCategoryId(int id) {
        return itemRepository.findAllItemsByCategoryIdAndIsActiveIsTrue(id);
    }

    @Override
    public void updateItem(Item item) {
        itemRepository.save(item);

    }

    @Override
    public void markItemAsInactive(int id) {

    }

    @Override
    public Page<SimpleMenuDto> getAllActiveItems(int page, int size) {
        return itemRepository.findAllByIsActiveIsTrue(PageRequest.of(page, size));
    }
}
