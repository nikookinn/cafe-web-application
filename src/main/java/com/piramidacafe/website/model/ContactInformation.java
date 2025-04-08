package com.piramidacafe.website.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cafeName;

    @Column(columnDefinition = "TEXT")
    private String about;

    private String phoneNumber;

    private String address;

    private String workingHours;

    private String websiteImageUrl;

    private String email;

    private String instagramUrl;

    private String facebookUrl;

    private String twitterUrl;

    private String websiteIcon;

}
