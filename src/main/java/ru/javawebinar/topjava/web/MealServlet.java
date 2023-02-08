package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.crud.MealCrud;
import ru.javawebinar.topjava.crud.DataInMemoryMealCrud;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    public static final int CALORIES_PER_DAY = 2000;
    private static final Logger log = getLogger(MealServlet.class);
    private MealCrud mealCrud;

    @Override
    public void init() throws ServletException {
        mealCrud = new DataInMemoryMealCrud();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "show";
        }
        String path = "/meals.jsp";

        switch (action) {
            case "show":
                req.setAttribute("mealToList", MealsUtil.filteredByStreams(
                        mealCrud.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY));
                log.debug("redirect to meals");
                break;
            case "delete":
                mealCrud.delete(getMealId(req));
                resp.sendRedirect("meals");
                log.debug("delete meal");
                return;
            case "edit":
                req.setAttribute("meal", mealCrud.getById(getMealId(req)));
                req.setAttribute("newMeal", Boolean.FALSE);
                path = "/updateMeal.jsp";
                break;
            case "add":
                req.setAttribute("meal", createNewMeal());
                req.setAttribute("newMeal", Boolean.TRUE);
                path = "/updateMeal.jsp";
                break;
            default:
                resp.sendRedirect("meals");
                return;
        }
        req.getRequestDispatcher(path).forward(req, resp);
    }

    private int getMealId(HttpServletRequest req) {
        return Integer.parseInt(req.getParameter("mealId"));
    }

    private Meal createNewMeal() {
        return new Meal(LocalDateTime.now().withSecond(0).withNano(0), "", 0);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String dateTime = req.getParameter("dateTime");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        boolean isNew = Boolean.parseBoolean(req.getParameter("new"));
        Meal inputtedMeal = new Meal(localDateTime, description, calories);
        if (isNew) {
            mealCrud.add(inputtedMeal);
            log.debug("add new meal");
        } else {
            int id = Integer.parseInt(req.getParameter("id"));
            inputtedMeal.setId(id);
            mealCrud.update(inputtedMeal);
            log.debug("update meal");
        }
        resp.sendRedirect("meals");
    }
}
