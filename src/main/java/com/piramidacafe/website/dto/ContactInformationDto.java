package com.piramidacafe.website.dto;

import org.springframework.web.multipart.MultipartFile;


public class ContactInformationDto {
    private String cafeName;
    private String about;
    private String phoneNumber;
    private String address;
    private String workingHours;
    private String existingImageUrl;
    private MultipartFile websiteImage;

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
}
