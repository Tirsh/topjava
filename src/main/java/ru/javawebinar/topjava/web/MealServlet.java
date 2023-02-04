package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.model.ProjectData;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private List<MealTo> getStaticData(){
        return MealsUtil.filteredByStreams(ProjectData.meals, LocalTime.MIN, LocalTime.MAX, ProjectData.caloriesPerDay);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        log.debug("redirect to meals");
        req.setAttribute("meals",  getStaticData());
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
