package com.piramidacafe.website.service;

import com.piramidacafe.website.dto.ItemDto;
import com.piramidacafe.website.dto.ItemUpdateDto;
import com.piramidacafe.website.dto.SimpleMenuDto;
import com.piramidacafe.website.exeption.ItemNotFoundException;
import com.piramidacafe.website.mapper.ItemMapper;
import com.piramidacafe.website.model.Item;
import com.piramidacafe.website.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ItemServiceImpl implements ItemService{

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
