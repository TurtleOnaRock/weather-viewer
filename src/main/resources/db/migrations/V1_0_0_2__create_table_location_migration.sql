CREATE TABLE IF NOT EXISTS Location(
    id          INT         AUTO_INCREMENT PRIMARY KEY,
    Name        VARCHAR(100) NOT NULL,
    UserId      INT         NOT NULL,
    Latitude    DECIMAL     NOT NULL,
    Longitude   DECIMAL     NOT NULL
);