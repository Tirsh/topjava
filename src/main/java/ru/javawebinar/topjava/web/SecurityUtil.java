package ru.javawebinar.topjava.web;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class SecurityUtil {
    private static int id = 0;

    public static int setAuthUserId() {
        return SecurityUtil.id;
    }

    public static void setId(int userId) {
        id = userId;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}