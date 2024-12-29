package com.piramidacafe.website.repository;

import com.piramidacafe.website.dto.ItemDto;
import com.piramidacafe.website.dto.SimpleMenuDto;
import com.piramidacafe.website.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("""
    SELECT new com.piramidacafe.website.dto.SimpleMenuDto(
        i.itemId, 
        i.name, 
        i.price, 
        i.description, 
        i.imageUrl, 
        m.name, 
        c.name
    )
    FROM Item i
    JOIN i.category c
    JOIN c.menu m
    WHERE i.isActive = true
    """)
    Page<SimpleMenuDto> findAllByIsActiveIsTrue(Pageable pageable);


    Optional<Item> findByItemIdAndIsActiveIsTrue(int id);
}
