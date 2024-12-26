CREATE TABLE categories (
                            categoryId BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            menuId BIGINT NOT NULL,
                            imageUrl VARCHAR(255),
                            isActive BOOLEAN NOT NULL DEFAULT TRUE,
                            FOREIGN KEY (menuId) REFERENCES menus(menuId)
);