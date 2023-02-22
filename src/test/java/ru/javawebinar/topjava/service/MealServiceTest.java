package ru.javawebinar.topjava.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest extends TestCase {
    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(USER_MEAL1_ID, USER_ID);
        assertMatch(meal, meal1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(ADMIN_MEAL7_ID, USER_ID);
    }

    @Test
    public void delete() {
        service.delete(USER_MEAL1_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(USER_MEAL1_ID, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(USER_MEAL2_ID, USER_ID);
        service.delete(USER_MEAL2_ID, USER_ID);
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> mealList = service.getBetweenInclusive(LocalDate.of(2023, 2, 19),
                LocalDate.of(2023, 2, 19), USER_ID);
        assertMatch(mealList, meal6, meal5, meal4);
    }

    @Test
    public void getAll() {
        List<Meal> mealList = service.getAll(ADMIN_ID);
        assertMatch(mealList, meal10, meal9, meal8, meal7);
    }

    @Test
    public void update() {
        Meal updatedMeal = getUpdatedMeal();
        service.update(updatedMeal, ADMIN_ID);
        assertMatch(getUpdatedMeal(), service.get(ADMIN_MEAL9_ID, ADMIN_ID));
    }

    @Test
    public void create() {
        Meal created = service.create(getNewMeal(), GUEST_ID);
        Integer newId = created.getId();
        Meal newMeal = getNewMeal();
        newMeal.setId(newId);
        assertMatch(Collections.singletonList(newMeal), service.getAll(GUEST_ID));
        assertMatch(service.get(newId, GUEST_ID), newMeal);
    }

    @Test(expected = NotFoundException.class)
    public void updateAlienMeal() {
        Meal updatedMeal = getUpdatedMeal();
        service.update(updatedMeal, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteAlienMeal() {
        service.delete(USER_MEAL4_ID, ADMIN_ID);
    }

    @Test
    public void createDuplicate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(LocalDateTime.of(2023, 2, 19, 19, 30), "Duplicate", 500), USER_ID));
    }

}