package com.piramidacafe.website.repository;

import com.piramidacafe.website.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
