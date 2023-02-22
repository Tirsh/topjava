package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_MEAL1_ID = START_SEQ + 3;
    public static final int USER_MEAL2_ID = START_SEQ + 4;
    public static final int USER_MEAL3_ID = START_SEQ + 5;
    public static final int USER_MEAL4_ID = START_SEQ + 6;
    public static final int USER_MEAL5_ID = START_SEQ + 7;
    public static final int USER_MEAL6_ID = START_SEQ + 8;
    public static final int ADMIN_MEAL7_ID = START_SEQ + 9;
    public static final int ADMIN_MEAL8_ID = START_SEQ + 10;
    public static final int ADMIN_MEAL9_ID = START_SEQ + 11;
    public static final int ADMIN_MEAL10_ID = START_SEQ + 12;
    public static final Meal meal1 = new Meal(USER_MEAL1_ID, LocalDateTime.of(2023, 2, 18, 10, 0), "Завтрак", 400);
    public static final Meal meal2 = new Meal(USER_MEAL2_ID, LocalDateTime.of(2023, 2, 18, 14, 0), "Обед", 900);
    public static final Meal meal3 = new Meal(USER_MEAL3_ID, LocalDateTime.of(2023, 2, 18, 19, 0), "Ужин", 500);
    public static final Meal meal4 = new Meal(USER_MEAL4_ID, LocalDateTime.of(2023, 2, 19, 10, 0), "Завтрак", 600);
    public static final Meal meal5 = new Meal(USER_MEAL5_ID, LocalDateTime.of(2023, 2, 19, 15, 0), "Обед", 500);
    public static final Meal meal6 = new Meal(USER_MEAL6_ID, LocalDateTime.of(2023, 2, 19, 19, 30), "Ужин", 510);
    public static final Meal meal7 = new Meal(ADMIN_MEAL7_ID, LocalDateTime.of(2023, 2, 19, 10, 30), "Завтрак", 310);
    public static final Meal meal8 = new Meal(ADMIN_MEAL8_ID, LocalDateTime.of(2023, 2, 19, 13, 30), "Обед", 650);
    public static final Meal meal9 = new Meal(ADMIN_MEAL9_ID, LocalDateTime.of(2023, 2, 19, 19, 30), "Ужин", 1300);
    public static final Meal meal10 = new Meal(ADMIN_MEAL10_ID, LocalDateTime.of(2023, 2, 20, 0, 0), "Граничное значение", 100);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }

    public static Meal getUpdatedMeal() {
        Meal updatedMeal = new Meal(meal9);
        updatedMeal.setId(ADMIN_MEAL9_ID);
        updatedMeal.setDescription("Updated Meal");
        updatedMeal.setCalories(1000);
        return updatedMeal;
    }

    public static Meal getNewMeal() {
        return new Meal(LocalDateTime.of(2023, 2, 21, 18, 30), "ужин", 600);
    }
}