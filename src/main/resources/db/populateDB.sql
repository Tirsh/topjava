DELETE
FROM user_role;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2023-02-18 10:00', 'Завтрак', 400, 100000),
       ('2023-02-18 14:00', 'Обед', 900, 100000),
       ('2023-02-18 19:00', 'Ужин', 500, 100000),
       ('2023-02-19 10:00', 'Завтрак', 600, 100000),
       ('2023-02-19 15:00', 'Обед', 500, 100000),
       ('2023-02-19 19:30', 'Ужин', 510, 100000),
       ('2023-02-19 10:30', 'Завтрак', 310, 100001),
       ('2023-02-19 13:30', 'Обед', 650, 100001),
       ('2023-02-19 19:30', 'Ужин', 1300, 100001);
