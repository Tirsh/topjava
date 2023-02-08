package ru.javawebinar.topjava.crud;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DataInMemoryMealCrud implements MealCrud {
    private final AtomicInteger idCounter;
    private final Map<Integer, Meal> meals = new ConcurrentHashMap<>();

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
        return new ArrayList<>(meals.values());
    }

    @Override
    public Meal getById(int id) {
        return meals.get(id);
    }

    @Override
    public void delete(int id) {
        meals.remove(id);
    }

    @Override
    public Meal update(int id, Meal meal) {
        if (meals.containsKey(id)) {
            meals.get(id).setDateTime(meal.getDateTime());
            meals.get(id).setDescription(meal.getDescription());
            meals.get(id).setCalories(meal.getCalories());
            return meal;
        }
        else {
            return null;
        }
    }

    @Override
    public Meal add(Meal meal) {
        int id = this.getId();
        meal.setId(id);
        meals.put(id, meal);
        return meal;
    }
}
