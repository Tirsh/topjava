package ru.javawebinar.topjava.crud;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealCrud {
    List<Meal> getAll();

    Meal getById(int id);

    void delete(int id);

    Meal update(Meal meal);

    Meal add(Meal meal);
}
