
CREATE TABLE users(
    id               BIGINT    PRIMARY KEY AUTO_INCREMENT,
    email            VARCHAR(256)   NOT NULL UNIQUE,
    first_name       VARCHAR(256)   NULL,
    last_name        VARCHAR(256)   NULL,
    country          VARCHAR(256)   NULL,
    city             VARCHAR(256)   NULL,
    company          VARCHAR(256)   NULL,
    enabled          BIT            NOT NULL DEFAULT FALSE ,
    verified         BIT            NOT NULL DEFAULT FALSE ,
    role             ENUM('ROLE_ROOT','ROLE_ADMIN', 'ROLE_USER') NOT NULL
);

INSERT INTO users (id, email, first_name, last_name, country, city, company, enabled, verified, role) VALUES
(1, 'test_root@gmail.com', 'Raiz', 'Rodriguez', 'España', 'Madrid', 'Verdecora', true,  true, 'ROLE_ROOT');
