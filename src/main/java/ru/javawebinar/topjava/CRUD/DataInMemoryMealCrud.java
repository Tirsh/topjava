package ru.javawebinar.topjava.CRUD;

import ru.javawebinar.topjava.data.ProjectData;
import ru.javawebinar.topjava.model.Meal;
import java.util.List;

public class DataInMemoryMealCrud implements MealCrud {
    ProjectData projectData = new ProjectData();

    @Override
    public int getNextId() {
        return projectData.getId();
    }

    @Override
    public List<Meal> getAll() {
        return projectData.getMeals();
    }

    @Override
    public Meal getById(int id) {
        return projectData.getMeals().stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void delete(int mealId) {
        projectData.getMeals().removeIf(meal -> meal.getId()==mealId);
    }

    @Override
    public Meal update(Meal meal) {
        for (Meal m : projectData.getMeals()) {
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
        projectData.getMeals().add(meal);
        return meal;
    }
}
