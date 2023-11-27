CREATE TABLE Movie (
    movieId BIGINT AUTO_INCREMENT PRIMARY KEY,
    releaseYear INT,
    title VARCHAR(255),
    studios VARCHAR(255),
    producers VARCHAR(255),
    winner BOOLEAN
);