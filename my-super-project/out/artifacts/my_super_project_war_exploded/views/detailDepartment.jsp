<%@ page import="app.entities.Department" %><%--
  Created by IntelliJ IDEA.
  User: gamer
  Date: 01.04.2019
  Time: 5:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Detail</title>

    <style type="text/css">
        .manyPoint {
            width: 100%;
            float: left;
        }
        .point {
            border: 1px solid #adc0ce;
            width: 200px;
            float: left;
        }
        .input {
            float: left;
            font-size: 18px;
            margin-top: 30px;
        }
        .filterButton{
            margin-left: 10px;
            margin-top: 30px;
        }
    </style>

</head>
<body>

<div>
    <h1>Your department!</h1>
</div>

<form method="post">
    <%
        Department department = (Department) request.getAttribute("Department");

        if (department != null) {
    %>
    <div class = "manyPoint">
        <span class = "point">id</span>
        <span class = "point">name</span>
        <span class = "point">address</span>
    </div>
    <div class = "manyPoint">
        <span class = "point"><%= department.getDepartmentId()%></span>
        <span class = "point"><%= department.getName()%></span>
        <span class = "point"><%= department.getAddress()%></span>
    </div>

    <div class = "input">
        <label>Отдел:  <br /></label>
        <label>Адрес отдела:  <br /></label>
    </div>
    <div class = "input">
        <label>
            <input type="text" name="name" value="<%= department.getName()%>"><br />
        </label>
        <label>
            <input type="text" name="address" value="<%= department.getAddress()%>"><br />
        </label>
    </div>
    <%
        }
    %>
    <button type="submit" class = "filterButton">Edit</button>
</form>
<div>
    <button onclick="location.href='/'" class = "filterButton">Back to main</button>
</div>
</body>
</html>
