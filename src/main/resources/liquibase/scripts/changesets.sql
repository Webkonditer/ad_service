-- liquibase formatted sql

-- changeset denis:1
CREATE TABLE ads
(
    ad_id           SERIAL primary key,
    user_id         INT,
    image_id        INT,
    description     VARCHAR,
    price           INT,
    title           VARCHAR,
    created_at      TIMESTAMP
);

CREATE TABLE users
(
    user_id         SERIAL primary key,
    email           VARCHAR,
    first_name      VARCHAR,
    last_name       VARCHAR,
    phone           VARCHAR,
    reg_date        TIMESTAMP,
    city            VARCHAR,
    image_id        INT
);

CREATE TABLE comments
(
    comment_id      SERIAL primary key,
    created_at      TIMESTAMP,
    comment_text    VARCHAR,
    user_id         INT,
    ad_id           INT
);

CREATE TABLE images
(
    image_id        SERIAL primary key,
    image_link      VARCHAR,
    ad_id           INT DEFAULT null,
    user_id         INT DEFAULT null
);

-- changeset alexander:1
ALTER TABLE users ADD COLUMN password VARCHAR;