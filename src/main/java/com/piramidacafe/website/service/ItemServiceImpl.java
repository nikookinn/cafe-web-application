package com.piramidacafe.website.service;

import com.piramidacafe.website.model.Item;

import java.util.List;
import java.util.Optional;

public class ItemServiceImpl implements ItemService{
    @Override
    public void save(Item item) {

    }

    @Override
    public Optional<Item> getActiveItemById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Item> getActiveItemsByCategory(String catName) {
        return null;
    }

    @Override
    public void updateItem(Item item) {

    }

    @Override
    public void markItemAsInactive(int id) {

    }
}
