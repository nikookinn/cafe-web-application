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
    private String email;
    private String instagramUrl;
    private String facebookUrl;
    private String twitterUrl;
    private String websiteIcon;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstagramUrl() {
        return instagramUrl;
    }

    public void setInstagramUrl(String instagramUrl) {
        this.instagramUrl = instagramUrl;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public String getWebsiteIcon() {
        return websiteIcon;
    }

    public void setWebsiteIcon(String websiteIcon) {
        this.websiteIcon = websiteIcon;
    }

    @Override
    public String toString() {
        return "ContactInformation{" +
                "id=" + id +
                ", cafeName='" + cafeName + '\'' +
                ", about='" + about + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", workingHours='" + workingHours + '\'' +
                ", websiteImageUrl='" + websiteImageUrl + '\'' +
                '}';
    }
}
