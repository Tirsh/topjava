package ru.javawebinar.topjava.data;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ProjectData {
    private List<Meal> meals = new CopyOnWriteArrayList<>();
    {
        this.setIdCounter(new AtomicInteger());
        meals.add(new Meal(this.getId(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        meals.add(new Meal(this.getId(),LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        meals.add(new Meal(this.getId(),LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        meals.add(new Meal(this.getId(),LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        meals.add(new Meal(this.getId(),LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        meals.add(new Meal(this.getId(),LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        meals.add(new Meal(this.getId(),LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }
    private AtomicInteger idCounter;

    public int getId() {
        return idCounter.getAndIncrement();
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setIdCounter(AtomicInteger idCounter) {
        this.idCounter = idCounter;
    }
}
