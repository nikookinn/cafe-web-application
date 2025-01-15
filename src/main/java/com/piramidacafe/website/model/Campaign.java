package com.piramidacafe.website.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "campaigns")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long campaignId;

    private String name;

    private String imageUrl;

    private boolean isActive = true;

    @CreationTimestamp
    private LocalDate creationDate;

}
