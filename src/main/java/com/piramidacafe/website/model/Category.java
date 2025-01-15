package com.piramidacafe.website.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false)
    private String name;

    @ManyToOne()
    @JoinColumn(name = "menuId", nullable = false)
    private Menu menu;

    private String imageUrl;


    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Item> menuItems = new ArrayList<>();

    @Column(nullable = false)
    private boolean isActive = true;
}
