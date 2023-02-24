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

-- changeset alexander:2
alter table images drop column user_id;

-- changeset alexander:3
CREATE TABLE avatars
(
    avatar_id       SERIAL primary key,
    avatar_link     VARCHAR,
    user_id         INT DEFAULT null
);

-- changeset alexander:4
ALTER TABLE users DROP COLUMN image_id ;
ALTER TABLE users ADD COLUMN avatar_id INT;

-- changeset alexander:5
ALTER TABLE avatars ADD COLUMN link_for_front VARCHAR;

-- changeset alexander:6
ALTER TABLE avatars ADD COLUMN file_size INT;
ALTER TABLE avatars ADD COLUMN media_type VARCHAR;

-- changeset alexander:7
ALTER TABLE avatars DROP COLUMN file_size;
ALTER TABLE avatars ADD COLUMN file_size BIGINT;


-- changeset denis:2
ALTER TABLE images DROP COLUMN ad_id;

-- changeset andrey:1
ALTER TABLE images add column link_for_front VARCHAR;
ALTER TABLE images ADD COLUMN file_size BIGINT;
ALTER TABLE images ADD COLUMN media_type VARCHAR;