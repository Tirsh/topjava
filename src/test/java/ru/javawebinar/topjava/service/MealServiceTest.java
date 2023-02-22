package ru.javawebinar.topjava.service;

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
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app-jdbc-rep.xml",
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(USER_MEAL1_ID, USER_ID);
        assertMatch(meal, userMeal1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(ADMIN_MEAL7_ID, USER_ID));
    }

    @Test
    public void delete() {
        service.delete(USER_MEAL1_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(USER_MEAL1_ID, USER_ID));
    }

    @Test
    public void deleteNotFound() {
        service.delete(USER_MEAL2_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.delete(USER_MEAL2_ID, USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> mealList = service.getBetweenInclusive(LocalDate.of(2023, 2, 19),
                LocalDate.of(2023, 2, 19), USER_ID);
        assertMatch(mealList, userMeal6, userMeal5, userMeal4);
    }

    @Test
    public void getAll() {
        List<Meal> mealList = service.getAll(ADMIN_ID);
        assertMatch(mealList, adminMeal10, adminMeal9, adminMeal8, adminMeal7);
    }

    @Test
    public void update() {
        Meal updatedMeal = getUpdatedMeal();
        service.update(updatedMeal, ADMIN_ID);
        assertMatch(service.get(ADMIN_MEAL9_ID, ADMIN_ID), getUpdatedMeal());
    }

    @Test
    public void create() {
        Meal created = service.create(getNewMeal(), GUEST_ID);
        Integer newId = created.getId();
        Meal newMeal = getNewMeal();
        newMeal.setId(newId);
        assertMatch(service.getAll(GUEST_ID), Collections.singletonList(newMeal));
        assertMatch(service.get(newId, GUEST_ID), newMeal);
    }

    @Test
    public void updateAlienMeal() {
        Meal updatedMeal = getUpdatedMeal();
        assertThrows(NotFoundException.class, () -> service.update(updatedMeal, USER_ID));
    }

    @Test
    public void deleteAlienMeal() {
        assertThrows(NotFoundException.class, () -> service.delete(USER_MEAL4_ID, ADMIN_ID));
    }

    @Test
    public void createDuplicate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(userMeal4.getDateTime(), "Duplicate", 500), USER_ID));
    }

    @Test
    public void notExistedMeal() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_EXISTED_MEAL_ID, USER_ID));
    }

    @Test
    public void filterWithNullBoards() {
        List<Meal> mealList = service.getBetweenInclusive(null, null, USER_ID);
        assertMatch(mealList, userMeal6, userMeal5, userMeal4, userMeal3, userMeal2, userMeal1);
    }

}