package com.piramidacafe.website.repository;

import com.piramidacafe.website.dto.SimpleCategoryDto;
import com.piramidacafe.website.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Page<Category> findAllByIsActiveIsTrue(Pageable pageable);

    Optional<Category> findByCategoryIdAndIsActiveIsTrue(int id);
    @Query("SELECT new com.piramidacafe.website.dto.SimpleCategoryDto(c.categoryId, c.name) FROM Category c WHERE c.menu.menuId = :menuId AND c.isActive = true")
    List<SimpleCategoryDto> findCategoriesByMenuIdAndIsActiveTrue(@Param("menuId") Long menuId);

    @Query("SELECT c FROM Category c WHERE LOWER(c.menu.name) = LOWER(:menuName) AND c.isActive = true")
    List<Category> getAllActiveCategoriesByMenuName(String menuName);
}
