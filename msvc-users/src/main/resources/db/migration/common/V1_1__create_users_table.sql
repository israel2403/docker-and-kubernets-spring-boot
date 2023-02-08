CREATE TABLE IF NOT EXISTS users (
    id BINARY(16) NOT NULL,
    name VARCHAR(100),
    password VARCHAR(100),
    PRIMARY KEY (id)
);