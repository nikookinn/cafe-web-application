package com.piramidacafe.website.service;

import com.piramidacafe.website.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ItemService {

    void save(Item item);

    Optional<Item> getActiveItemById(int id);

    List<Item> getActiveItemsByCategory(String catName);

    void updateItem(Item item);

    void markItemAsInactive(int id);

}
