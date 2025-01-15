package com.piramidacafe.website.dto;

import com.piramidacafe.website.annotations.ValidImageFile;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
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

}
