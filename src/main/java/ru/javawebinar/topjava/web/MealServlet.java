package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.CRUD.Crud;
import ru.javawebinar.topjava.CRUD.MealDataInMemory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private final Crud memoryCrud = new MealDataInMemory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        log.debug("redirect to meals");
        String action = req.getParameter("action");
        String path;
        int mealId;

        switch (action) {
            case "delete":
                mealId = Integer.parseInt(req.getParameter("mealId"));
                memoryCrud.delete(mealId);
                req.setAttribute("mealToList", memoryCrud.getData());
                path = "/meals.jsp";
                break;
            case "edit":
                mealId = Integer.parseInt(req.getParameter("mealId"));
                req.setAttribute("meal", memoryCrud.getMealById(mealId));
                path = "/updateMeal.jsp";
                break;
            case "add":
                mealId = memoryCrud.add();
                req.setAttribute("meal", memoryCrud.getMealById(mealId));
                path = "/updateMeal.jsp";
                break;
            default:
                req.setAttribute("mealToList", memoryCrud.getData());
                path = "/meals.jsp";
        }
        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String dateTime = req.getParameter("DateTime");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime.replace('T', ' '), formatter);
        String description = req.getParameter("Description");
        int calories = Integer.parseInt(req.getParameter("Calories"));
        int id = Integer.parseInt(req.getParameter("Id"));
        memoryCrud.update(id, localDateTime, description, calories);

        resp.sendRedirect("meals?action=");
    }
}
