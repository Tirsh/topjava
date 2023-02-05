<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Meal</title>
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
<h3><a href="../index.html">Home</a></h3>
<hr>
<h2>Edit Meal</h2>
<form action="" method="post">
    <jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
    <input type="hidden" name="Id" value="<c:out value="${meal.getMealId()}" />"/> <br/>
    <div class="field">
        <label>DateTime :</label>
        <input type="datetime-local" name="DateTime" value="<c:out value="${meal.getDateTime()}" />"/>
    </div>
    <div class="field">
        <label>Description :</label>
        <input type="text" name="Description" value="<c:out value="${meal.getDescription()}" />"/>
    </div>
    <div class="field">
        <label>Calories :</label>
        <input type="number" name="Calories" value="<c:out value="${meal.getCalories()}" />"/>
    </div>
    <label></label>
    <input type="submit" value="Save"/>
</form>
<button onclick="window.location.href = 'meals?action=mealList';">Cancel</button>

</body>
</html>
