CREATE TABLE IF NOT EXISTS Users(
    id          INT         AUTO_INCREMENT PRIMARY KEY,
    Login       VARCHAR(100) NOT NULL,
    Password    VARCHAR(50) NOT NULL
);