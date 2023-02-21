package ru.javawebinar.topjava.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void testGet() {
        Meal meal = service.get(MEAL1_ID, USER_ID);
        assertMatch(meal, meal1);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() {
        service.get(MEAL7_ID, USER_ID);
    }

    @Test
    public void testDelete() {
        service.delete(MEAL1_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(MEAL1_ID, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() {
        service.delete(MEAL2_ID, ADMIN_ID);
    }

    @Test
    public void testGetBetweenInclusive() {
        List<Meal> mealList = service.getBetweenInclusive(LocalDate.of(2023, 2, 19),
                LocalDate.of(2023, 2, 19), USER_ID);
        assertMatch(mealList, meal6, meal5, meal4);
    }

    @Test
    public void testGetAll() {
        List<Meal> mealList = service.getAll(ADMIN_ID);
        assertMatch(mealList, meal9, meal8, meal7);
    }

    @Test
    public void testUpdate() {
        Meal updatedMeal = new Meal(meal9);
        updatedMeal.setId(MEAL9_ID);
        updatedMeal.setDescription("Updated Meal");
        updatedMeal.setCalories(1000);
        service.update(updatedMeal, ADMIN_ID);
        assertMatch(updatedMeal, service.get(MEAL9_ID, ADMIN_ID));
    }

    @Test
    public void testCreate() {
        Meal newMeal = new Meal(LocalDateTime.of(2023, 2, 21, 18, 30), "ужин", 600);
        Meal created = service.create(newMeal, GUEST_ID);
        Integer newId = created.getId();
        newMeal.setId(newId);
        assertMatch(Collections.singletonList(newMeal), service.getAll(GUEST_ID));
        assertMatch(service.get(newId, GUEST_ID), newMeal);
    }
}