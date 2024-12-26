CREATE TABLE authorities (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             authority VARCHAR(50) NOT NULL,
                             username VARCHAR(50) NOT NULL,
                             FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE
);
;