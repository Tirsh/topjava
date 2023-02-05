package ru.javawebinar.topjava.CRUD;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MealDataInMemory implements Crud {
    public static final int caloriesPerDay = 2000;
    private static AtomicInteger idCounter;
    public static List<Meal> meals = MealsUtil.meals;

    public static int getIdCounter() {
        return idCounter.getAndIncrement();
    }

    public static void setIdCounter(AtomicInteger idCounter) {
        MealDataInMemory.idCounter = idCounter;
    }

    @Override
    public List<MealTo> getData() {
        return MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }

    @Override
    public Meal getMealById(int id) {
        return meals.stream().filter(m -> m.getMealId() == id).findFirst().orElse(null);
    }

    @Override
    public void delete(int mealId) {
        meals = meals.stream().filter(m -> m.getMealId() != mealId).collect(Collectors.toList());
    }

    @Override
    public void update(int id, LocalDateTime localDateTime, String description, int calories) {
        for (Meal meal : meals) {
            if (meal.getMealId() == id) {
                meal.setDateTime(localDateTime);
                meal.setDescription(description);
                meal.setCalories(calories);
            }
        }
    }

    @Override
    public int add() {
        Meal newMeal = new Meal(LocalDateTime.now().withSecond(0).withNano(0), "", 0);
        meals.add(newMeal);
        return newMeal.getMealId();
    }
}
