package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);
    private int userId;

    {
        MealsUtil.meals.forEach(meal -> {
            this.userId = meal.getUserId();
            this.save(meal);
        });
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.getUserId() != null && !isUserMeal(meal))
            return null;
        meal.setUserId(userId);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id) {
        return isUserMeal(repository.get(id)) && repository.remove(id) != null;
    }

    @Override
    public Meal get(int id) {
        return isUserMeal(repository.get(id)) ? repository.get(id) : null;
    }

    @Override
    public List<Meal> getAll(Integer id) {
        return repository.values().stream()
                .filter(meal -> meal.getUserId().equals(id))
                .sorted(Comparator.comparing(Meal::getDate).thenComparing(Meal::getTime).reversed())
                .collect(Collectors.toList());
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    private boolean isUserMeal(Meal meal) {
        return meal.getUserId() == userId;
    }
}

