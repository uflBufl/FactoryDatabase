<%@ page import="app.entities.Post" %><%--
  Created by IntelliJ IDEA.
  User: gamer
  Date: 01.04.2019
  Time: 4:05
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
    <h1>Your post!</h1>
</div>

<form method="post">
    <%
        Post post = (Post) request.getAttribute("Post");

        if (post != null) {
    %>
    <div class = "manyPoint">
        <span class = "point">id</span>
        <span class = "point">name</span>
        <span class = "point">salary</span>
        <span class = "point">time</span>
    </div>
    <div class = "manyPoint">
        <span class = "point"><%= post.getPostId()%></span>
        <span class = "point"><%= post.getName()%></span>
        <span class = "point"><%= post.getSalary()%></span>
        <span class = "point"><%= post.getTime()%></span>
    </div>

    <div class = "input">
        <label>Работа:  <br /></label>
        <label>Зарплата:  <br /></label>
        <label>Время:  <br /></label>
    </div>
    <div class = "input">
        <label>
            <input type="text" name="name" value="<%= post.getName()%>"><br />
        </label>
        <label>
            <input type="text" name="salary" value="<%= post.getSalary()%>"><br />
        </label>
        <label>
            <input type="text" name="time" value="<%= post.getTime()%>"><br />
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
