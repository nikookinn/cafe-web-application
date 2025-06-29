package com.piramidacafe.website.service.serviceImpl;

import com.piramidacafe.website.enums.ImageDirectory;
import com.piramidacafe.website.dto.ItemDto;
import com.piramidacafe.website.dto.ItemUpdateDto;
import com.piramidacafe.website.dto.SimpleItemDto;
import com.piramidacafe.website.dto.SimpleMenuDto;
import com.piramidacafe.website.exception.ItemNotFoundException;
import com.piramidacafe.website.mapper.ItemMapper;
import com.piramidacafe.website.model.Category;
import com.piramidacafe.website.model.Item;
import com.piramidacafe.website.repository.ItemRepository;
import com.piramidacafe.website.service.CategoryService;
import com.piramidacafe.website.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
@Slf4j
@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final FileStorageService fileStorageService;
    private final CategoryService categoryService;
    private final ItemMapper itemMapper;

    @Override
    public void saveItem(ItemDto itemDto) {
        String imageUrl = null;
        if (itemDto.getItemImage() != null && !itemDto.getItemImage().isEmpty()) {
            imageUrl = fileStorageService.storeFile(itemDto.getItemImage(), ImageDirectory.ITEM_IMAGES.getDirectory());
        }
        Category category = categoryService.findActiveCategoryById(itemDto.getCategoryId());
        Item item = itemMapper.toEntity(itemDto, category, imageUrl);
        itemRepository.save(item);
        log.info("new menu item added successfully to db with name : "+itemDto.getName());
    }

    @Override
    public ItemUpdateDto getActiveItemById(int id) {
        Item item = itemRepository.findByItemIdAndIsActiveIsTrue(id).orElseThrow(() -> new ItemNotFoundException("There is no yitem with id " + id));
        return itemMapper.toDto(item);
    }

    @Override
    public Item findActiveItemById(int id) {
        return itemRepository.findByItemIdAndIsActiveIsTrue(id).orElseThrow(() -> new ItemNotFoundException("There is no yitem with id " + id));
    }


    @Override
    public List<SimpleItemDto> getAllActiveItemsByCategoryId(int id) {
        return itemRepository.findAllItemsByCategoryIdAndIsActiveIsTrue(id);
    }

    @Override
    public void updateItem(ItemUpdateDto updateDto) {
        Item item = findActiveItemById(updateDto.getItemId().intValue());
        String imageUrl = item.getImageUrl();
        if (updateDto.getItemImage() != null && !updateDto.getItemImage().isEmpty()) {
            fileStorageService.deleteOldImage(imageUrl, ImageDirectory.ITEM_IMAGES.getDirectory());
            imageUrl = fileStorageService.storeFile(updateDto.getItemImage(), ImageDirectory.ITEM_IMAGES.getDirectory());
        }
        itemRepository.save(itemMapper.toEntity(updateDto, item, imageUrl));
        log.info("menu item updated successfully with name : "+updateDto.getName());
    }

    @Override
    public void markItemAsInactive(int id) {
        Item item = findActiveItemById(id);
        fileStorageService.deleteOldImage(item.getImageUrl(), ImageDirectory.ITEM_IMAGES.getDirectory());
        item.setActive(false);
        itemRepository.save(item);
        log.info("menu item removed successfully with name : "+item.getName());
    }

    @Override
    public Page<SimpleMenuDto> getAllActiveItems(int page, int size) {
        return itemRepository.findAllByIsActiveIsTrue(PageRequest.of(page, size));
    }
}
