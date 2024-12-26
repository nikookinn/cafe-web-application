CREATE TABLE menuItems (
    itemId BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2),
    description TEXT,
    imageUrl VARCHAR(255),
    categoryId BIGINT NOT NULL,
    isActive BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (categoryId) REFERENCES categories(categoryId)
);