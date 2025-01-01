package com.piramidacafe.website.repository;

import com.piramidacafe.website.dto.SimpleItemDto;
import com.piramidacafe.website.dto.SimpleMenuDto;
import com.piramidacafe.website.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    JOIN Category c on i.category.categoryId=c.categoryId
    JOIN Menu m on c.menu.menuId=m.menuId
    WHERE i.isActive = true
    """)
    Page<SimpleMenuDto> findAllByIsActiveIsTrue(Pageable pageable);


    Optional<Item> findByItemIdAndIsActiveIsTrue(int id);

    @Query("""
    SELECT new com.piramidacafe.website.dto.SimpleItemDto(
        i.itemId,
        i.name,
        i.price,
        i.description,
        i.imageUrl
    )
    FROM Item i
    WHERE i.category.name=:categoryName AND i.isActive = true
    """)
    List<SimpleItemDto> findAllItemsByCategoryNameAndIsActiveIsTrue(String categoryName);
    @Query("""
    SELECT new com.piramidacafe.website.dto.SimpleItemDto(
        i.itemId,
        i.name,
        i.price,
        i.description,
        i.imageUrl
    )
    FROM Item i
    WHERE i.category.categoryId=:id AND i.isActive = true
    """)
    List<SimpleItemDto> findAllItemsByCategoryIdAndIsActiveIsTrue(int id);
}
