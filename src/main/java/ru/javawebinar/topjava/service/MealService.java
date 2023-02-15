package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.ValidationUtil;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    @Autowired
    private MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    public Meal get(int id) {
        return ValidationUtil.checkNotFoundWithId(repository.get(id), id);
    }

    public List<Meal> getAll(Integer id) {
        return ValidationUtil.checkNotFoundWithId(repository.getAll(id), id);
    }

    public void update(Meal meal) {
        checkNotFoundWithId(repository.save(meal), meal.getId());
    }

    public void getUserId(int id) {
        repository.setUserId(id);
    }
}