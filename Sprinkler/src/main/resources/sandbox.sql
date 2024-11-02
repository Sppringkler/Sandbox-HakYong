CREATE SCHEMA IF NOT EXISTS sandbox;

CREATE TABLE sandbox.todo (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              content VARCHAR(255) NOT NULL,
                              completed TINYINT NOT NULL
);

CREATE TABLE sandbox.article (
                              id INT PRIMARY KEY,
                              title VARCHAR(255) NOT NULL,
                              created_at TIMESTAMP NOT NULL
);
