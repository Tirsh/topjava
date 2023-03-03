package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Meal m SET m.dateTime=:dateTime," +
            "m.description=:description, m.calories=:calories WHERE m.id=:id")
    void update(@Param("id") int id, @Param("dateTime") LocalDateTime dateTime,
                @Param("description") String description, @Param("calories") int calories);
    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
    List<Meal> getAll(@Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId AND m.dateTime >= :startDateTime AND m.dateTime < :endDateTime" +
            " ORDER BY m.dateTime DESC")
    List<Meal> getBetween(@Param("userId") int userId, @Param("startDateTime") LocalDateTime startDateTime,
                          @Param("endDateTime") LocalDateTime endDateTime);

}
