CREATE TABLE menus (
                       menuId BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       imageUrl VARCHAR(255),
                       isActive BOOLEAN NOT NULL DEFAULT TRUE
);