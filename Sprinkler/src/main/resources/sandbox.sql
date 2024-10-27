CREATE SCHEMA IF NOT EXISTS sandbox;

CREATE TABLE sandbox.todo (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              content VARCHAR(255) NOT NULL,
                              completed TINYINT NOT NULL
);
