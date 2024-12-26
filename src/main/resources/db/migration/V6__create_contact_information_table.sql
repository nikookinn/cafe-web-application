CREATE TABLE contact_information (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     cafe_name VARCHAR(255),
                                     about TEXT,
                                     phone_number VARCHAR(20),
                                     address VARCHAR(255),
                                     working_hours VARCHAR(255),
                                     website_image_url VARCHAR(255)
);