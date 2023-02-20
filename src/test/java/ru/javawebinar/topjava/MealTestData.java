package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL1_ID = START_SEQ + 3;
    public static final int MEAL2_ID = START_SEQ + 4;
    public static final int MEAL3_ID = START_SEQ + 5;
    public static final int MEAL4_ID = START_SEQ + 6;
    public static final int MEAL5_ID = START_SEQ + 7;
    public static final int MEAL6_ID = START_SEQ + 8;
    public static final int MEAL7_ID = START_SEQ + 9;
    public static final int MEAL8_ID = START_SEQ + 10;
    public static final int MEAL9_ID = START_SEQ + 11;
    public static final Meal meal1 = new Meal(MEAL1_ID, LocalDateTime.of(2023, 2, 18, 10, 0), "Завтрак", 400);
    public static final Meal meal2 = new Meal(MEAL2_ID, LocalDateTime.of(2023, 2, 18, 14, 0), "Обед", 900);
    public static final Meal meal3 = new Meal(MEAL3_ID, LocalDateTime.of(2023, 2, 18, 19, 0), "Ужин", 500);
    public static final Meal meal4 = new Meal(MEAL4_ID, LocalDateTime.of(2023, 2, 19, 10, 0), "Завтрак", 600);
    public static final Meal meal5 = new Meal(MEAL5_ID, LocalDateTime.of(2023, 2, 19, 15, 0), "Обед", 500);
    public static final Meal meal6 = new Meal(MEAL6_ID, LocalDateTime.of(2023, 2, 19, 19, 30), "Ужин", 510);
    public static final Meal meal7 = new Meal(MEAL7_ID, LocalDateTime.of(2023, 2, 19, 10, 30), "Завтрак", 310);
    public static final Meal meal8 = new Meal(MEAL8_ID, LocalDateTime.of(2023, 2, 19, 13, 30), "Обед", 650);
    public static final Meal meal9 = new Meal(MEAL9_ID, LocalDateTime.of(2023, 2, 19, 19, 30), "Ужин", 1300);
}