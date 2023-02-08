package ru.javawebinar.topjava.crud;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class DataInMemoryMealCrud implements MealCrud {
    private AtomicInteger idCounter;
    private final List<Meal> meals = new CopyOnWriteArrayList<>();

    {
        this.idCounter = new AtomicInteger(0);
        Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        ).forEach(this::add);
    }

    public int getId() {
        return idCounter.getAndIncrement();
    }

    @Override
    public List<Meal> getAll() {
        return meals;
    }

    @Override
    public Meal getById(int id) {
        return meals.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void delete(int mealId) {
        meals.removeIf(meal -> meal.getId() == mealId);
    }

    @Override
    public Meal update(Meal meal) {
        for (Meal m : meals) {
            if (m.getId() == meal.getId()) {
                m.setDateTime(meal.getDateTime());
                m.setDescription(meal.getDescription());
                m.setCalories(meal.getCalories());
            }
        }
        return meal;
    }

    @Override
    public Meal add(Meal meal) {
        meal.setId(this.getId());
        meals.add(meal);
        return meal;
    }
}
