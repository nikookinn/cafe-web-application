package com.piramidacafe.website.repository;

import com.piramidacafe.website.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
