<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: valer
  Date: 04.02.2023
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
    <style>
        table, td, th {
            border: 2px solid;
        }
        table {
            width: 600px;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>Meals</h2>
    <table>
        <tr>
            <th><b>Date</b></th>
            <th><b>Description</b></th>
            <th><b>Calories</b></th>
        </tr>
        <%  ArrayList<MealTo> meals =(ArrayList<MealTo>)request.getAttribute("meals");
            String color;
            for(MealTo meal:meals){
                if(meal.isExcess()) color = "#CD5C5C";
                else color = "#006400";%>
        <tr>
            <td><span style="color:<%=color%>"><%=meal.getDateTime()%></span></td>
            <td><span style="color:<%=color%>"><%=meal.getDescription()%></span></td>
            <td><span style="color:<%=color%>"><%=meal.getCalories()%></span></td>
            <td>Update</td>
            <td>Delete</td>
        </tr>
        <%}%>
    </table>

</body>
</html>
