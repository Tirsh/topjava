<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
    <style>
        .green {
            color: green;
        }

        .red {
            color: red;
        }

        table, td, th {
            border: 2px solid black;
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
        <th colspan=2>Action</th>
    </tr>
    <jsp:useBean id="mealToList" scope="request" type="java.util.List"/>
    <c:forEach var="mealTo" items="${mealToList}">
        <jsp:useBean id="mealTo" type="ru.javawebinar.topjava.model.MealTo"/>
        <tr class="${mealTo.excess ? "red": "green"}">
            <td>${mealTo.date} ${mealTo.time}</td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
            <td><a href="meals?action=edit&mealId=${mealTo.id}">Edit</a></td>
            <td><a href="meals?action=delete&mealId=${mealTo.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<br>
<hr>
<button onclick="window.location.href = 'meals?action=add';">Add</button>
</body>
</html>
