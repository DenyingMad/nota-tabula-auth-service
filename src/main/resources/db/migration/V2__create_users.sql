CREATE TABLE USERS
(
    id       bigserial    not null,
    login    varchar(255) not null,
    email    varchar(255) not null,
    password varchar(255) not null,
    PRIMARY KEY (id)
);

CREATE TABLE ROLE
(
    id   bigserial   not null,
    name varchar(30) not null,
    PRIMARY KEY (id)
);

CREATE TABLE USER_ROLE
(
    user_id bigint not null,
    role_id bigint not null,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES USERS (id),
    FOREIGN KEY (role_id) REFERENCES ROLE (id)
);

INSERT INTO ROLE(name)
VALUES ('ROLE_USER');