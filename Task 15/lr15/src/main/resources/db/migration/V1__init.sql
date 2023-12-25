CREATE TABLE dish
(
    id            serial PRIMARY KEY,
    name          text          not null,
    ingredients   text          not null,
    proteins      decimal(8, 5) not null,
    fats          decimal(8, 5) not null,
    carbohydrates decimal(8, 5) not null,
    energy_value  int           not null,
    views         int           not null default 0
);

INSERT INTO Dish
VALUES (default, 'Борщ', 'Картофель, капуста, свекла', 4.1, 2.0, 10.4, 68),
       (default, 'Оливье', 'Яйца, картофель, морковь', 3.7, 30.0, 10.4, 298),
       (default, 'Щи', 'Капуста, картофель, лук', 2.8, 2.0, 5.7, 54),
       (default, 'Плов', 'Рис, свинина, морковь', 6.7, 7.0, 49.1, 293),
       (default, 'Гречка с мясом', 'Крупа гречневая, говядина, лук', 12.6, 12.6, 60.1, 342),
       (default, 'Макароны по-флотски', 'Макароны, фарш, лук', 8.2, 13.0, 29.0, 275),
       (default, 'Рассольник', 'Картофель, малосольные огурцы, телятина', 3.1, 2.8, 8.9, 68),
       (default, 'Уха', 'Рыба, картофель, лук', 16.6, 6.7, 0.0, 110),
       (default, 'Салат Цезарь', 'Курица, помидоры черри, салат айсберг', 3.8, 30.0, 2.1, 313),
       (default, 'Сырники', 'Творог, яйца, мука', 14.0, 23.0, 26.0, 341);

CREATE TABLE users
(
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    name     VARCHAR(20)  NOT NULL,
    enabled  boolean      NOT NULL,
    PRIMARY KEY (username)
);

INSERT INTO users
VALUES ('admin', '$2a$10$dYJ9JcdxtCIc6jnJYNTDFOs1tdPt1te25Gf5JKIEc7uRBvJiSk6JO', 'Элечка', true),
       ('user', '$2a$10$zxVS3muLezmSlzipO76OVuUsEPwxBzgYrMMBXu.b383sFiaO.rB5m', 'Гурман', true);

CREATE TABLE authorities
(
    username  varchar(50) NOT NULL,
    authority varchar(50) NOT NULL,

    CONSTRAINT authorities_idx UNIQUE (username, authority),

    CONSTRAINT authorities_ibfk_1
        FOREIGN KEY (username)
            REFERENCES users (username)
);

INSERT INTO authorities
VALUES ('admin', 'ROLE_ADMIN'),
       ('user', 'ROLE_USER');