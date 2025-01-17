package com.piramidacafe.website.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String authority;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username", nullable = false)
    private User user;


}
