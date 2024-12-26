package com.piramidacafe.website.model;

import jakarta.persistence.*;


@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCafeName() {
        return cafeName;
    }

    public void setCafeName(String cafeName) {
        this.cafeName = cafeName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getWebsiteImageUrl() {
        return websiteImageUrl;
    }

    public void setWebsiteImageUrl(String websiteImageUrl) {
        this.websiteImageUrl = websiteImageUrl;
    }
}
