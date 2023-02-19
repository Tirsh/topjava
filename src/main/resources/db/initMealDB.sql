DROP TABLE IF EXISTS meals;
DROP SEQUENCE IF EXISTS meal_seq;

CREATE SEQUENCE meal_seq START WITH 100000;

CREATE TABLE meals
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('meal_seq'),
    date_time   TIMESTAMP                         NOT NULL,
    description VARCHAR                           NOT NULL,
    calories    INTEGER                           NOT NULL,
    user_id     INTEGER                           NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);