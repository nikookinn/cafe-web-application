CREATE TABLE categories (
                            category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            menu_id BIGINT NOT NULL,
                            image_url VARCHAR(255),
                            is_active BOOLEAN NOT NULL DEFAULT TRUE,
                            FOREIGN KEY (menu_id) REFERENCES menus(menu_id)
);