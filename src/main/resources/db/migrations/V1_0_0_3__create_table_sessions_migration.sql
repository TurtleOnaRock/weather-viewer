CREATE TABLE IF NOT EXISTS Sessions(
    id          INT         AUTO_INCREMENT PRIMARY KEY,
    UserId      INT         NOT NULL,
    ExpiresAt   DATETIME    NOT NULL
);