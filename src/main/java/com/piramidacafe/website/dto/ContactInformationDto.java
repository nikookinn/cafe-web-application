package com.piramidacafe.website.dto;

import com.piramidacafe.website.annotations.ValidImageFile;
import org.springframework.web.multipart.MultipartFile;


public class ContactInformationDto {
    private Long id;
    private String cafeName;
    private String about;
    private String phoneNumber;
    private String address;
    private String workingHours;
    private String existingImageUrl;
    @ValidImageFile
    private MultipartFile websiteImage;
    private String email;
    private String instagramUrl;
    private String facebookUrl;
    private String twitterUrl;
    private String websiteIcon;
    private MultipartFile websiteIconFile;

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

    public String getExistingImageUrl() {
        return existingImageUrl;
    }

    public void setExistingImageUrl(String existingImageUrl) {
        this.existingImageUrl = existingImageUrl;
    }

    public MultipartFile getWebsiteImage() {
        return websiteImage;
    }

    public void setWebsiteImage(MultipartFile websiteImage) {
        this.websiteImage = websiteImage;
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

    public MultipartFile getWebsiteIconFile() {
        return websiteIconFile;
    }

    public void setWebsiteIconFile(MultipartFile websiteIconFile) {
        this.websiteIconFile = websiteIconFile;
    }
}
