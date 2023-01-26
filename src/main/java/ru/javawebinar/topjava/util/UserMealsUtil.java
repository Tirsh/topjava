package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesPerDayMap = new HashMap<>();
        List<UserMeal> userMealInGates = new ArrayList<>();
        for (UserMeal userMeal : meals) {
            LocalDate tmpLocalDate = userMeal.getDateTime().toLocalDate();
            if (caloriesPerDayMap.containsKey(tmpLocalDate)) {
                caloriesPerDayMap.put(tmpLocalDate, caloriesPerDayMap.get(tmpLocalDate) + userMeal.getCalories());
            } else {
                caloriesPerDayMap.put(tmpLocalDate, userMeal.getCalories());
            }

            if (userMeal.getDateTime().toLocalTime().compareTo(startTime) > 0 && userMeal.getDateTime().toLocalTime().compareTo(endTime) < 0) {
                userMealInGates.add(userMeal);
            }
        }
        List<UserMealWithExcess> userMealWithExcesses = new ArrayList<>();
        for (UserMeal userMealSorted : userMealInGates) {
            userMealWithExcesses.add(new UserMealWithExcess(
                    userMealSorted.getDateTime(),
                    userMealSorted.getDescription(),
                    userMealSorted.getCalories(),
                    caloriesPerDayMap.get(userMealSorted.getDateTime().toLocalDate()) > caloriesPerDay
            ));
        }
        return userMealWithExcesses;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesPerDayMap = meals.stream()
                .collect(Collectors.toMap(
                        m -> m.getDateTime().toLocalDate(),
                        UserMeal::getCalories,
                        Integer::sum
                ));
        return meals.stream()
                .filter(m -> m.getDateTime().toLocalTime().compareTo(startTime) > 0 && m.getDateTime().toLocalTime().compareTo(endTime) < 0)
                .map(m -> new UserMealWithExcess(
                        m.getDateTime(),
                        m.getDescription(),
                        m.getCalories(),
                        caloriesPerDayMap.get(m.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
