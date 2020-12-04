
CREATE TABLE users(
    id               BIGINT    PRIMARY KEY AUTO_INCREMENT,
    email            VARCHAR(256)   NOT NULL UNIQUE,
    first_name       VARCHAR(256)   NULL,
    last_name        VARCHAR(256)   NULL,
    country          VARCHAR(256)   NULL,
    city             VARCHAR(256)   NULL,
    company          VARCHAR(256)   NULL,
    phone            VARCHAR(16)    NULL,
    enabled          BIT            NOT NULL DEFAULT FALSE ,
    verified         BIT            NOT NULL DEFAULT FALSE ,
    password         VARCHAR(256)   NOT NULL,
    role             ENUM('ROLE_ROOT','ROLE_ADMIN', 'ROLE_USER') NOT NULL
);
