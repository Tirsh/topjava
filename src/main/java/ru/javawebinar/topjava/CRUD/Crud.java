package ru.javawebinar.topjava.CRUD;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.util.List;

public interface Crud {
    List<MealTo> getData();

    Meal getMealById(int id);

    void delete(int mealId);

    void update(int id, LocalDateTime localDateTime, String description, int calories);

    int add();
}
