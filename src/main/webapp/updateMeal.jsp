<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:useBean id="newMeal" scope="request" type="java.lang.Boolean"/>
    <title>${newMeal ? "Add": "Edit"} meal</title>
    <style>
        div.field {
            padding-bottom: 5px;
        }

        div.field label {
            display: block;
            float: left;
            width: 150px;
            height: 15px;
        }

        div.field input {
            width: 250px;
            box-sizing: border-box;
            margin: 0;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>${newMeal ? "Add": "Edit"} Meal</h2>
<form action="" method="post">
    <jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
    <input type="hidden" name="new" value="${newMeal}" /><br/>
    <c:if test="${!newMeal}">
        <input type="hidden" name="id" value="${meal.id}" /> <br/>
    </c:if>
    <div class="field">
        <label>DateTime :</label>
        <input type="datetime-local" name="dateTime" value="${meal.dateTime}" />
    </div>
    <div class="field">
        <label>Description :</label>
        <input type="text" name="description" value="${meal.description}" />
    </div>
    <div class="field">
        <label>Calories :</label>
        <input type="number" name="calories" value="${meal.calories}" />
    </div>
    <label></label>
    <input type="submit" value="Save"/>
</form>
<hr>
<button onclick="window.location.href = 'meals';">Cancel</button>


</body>
</html>
