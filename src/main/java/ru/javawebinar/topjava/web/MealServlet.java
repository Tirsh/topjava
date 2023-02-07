package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.CRUD.MealCrud;
import ru.javawebinar.topjava.CRUD.DataInMemoryMealCrud;
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
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");
        if(action == null){
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
        }
        req.getRequestDispatcher(path).forward(req, resp);
    }
    private int getMealId(HttpServletRequest req){
        return Integer.parseInt(req.getParameter("mealId"));
    }
    private Meal createNewMeal() {
        return new Meal(mealCrud.getNextId(), LocalDateTime.now().withSecond(0).withNano(0), "", 0);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String dateTime = req.getParameter("dateTime");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        int id = Integer.parseInt(req.getParameter("id"));
        boolean isNew = Boolean.parseBoolean(req.getParameter("new"));
        if (isNew){
            mealCrud.add(new Meal(id, localDateTime, description, calories));
            log.debug("add new meal");
        }
        else {
            mealCrud.update(new Meal(id, localDateTime, description, calories));
            log.debug("update meal");
        }
        resp.sendRedirect("meals");
    }
}
