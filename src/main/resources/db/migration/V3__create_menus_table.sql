CREATE TABLE menus (
                       menu_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       image_url VARCHAR(255),
                       is_active BOOLEAN NOT NULL DEFAULT TRUE
);