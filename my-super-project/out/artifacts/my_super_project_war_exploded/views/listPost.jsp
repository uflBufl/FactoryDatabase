<%@ page import="app.entities.Post" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: gamer
  Date: 01.04.2019
  Time: 4:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Posts list</title>

    <style type="text/css">
        .deleteButton{
            width: 100px;
            float: right;
            margin-left: 10px;
        }
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
    <h1>Posts!</h1>
</div>

<form method="post">
    <div class = "manyPoint">
        <span class = "point">id</span>
        <span class = "point">name</span>
    </div>
    <%
        ArrayList<Post> posts = (ArrayList<Post>) request.getAttribute("postsData");

        if (posts != null && !posts.isEmpty()) {
            for (Post post : posts) {
    %>

    <div class = "manyPoint" onclick=location.href='/detailPost?PostId=<%= post.getPostId()%>'>
        <span class = "point"><%= post.getPostId()%></span>
        <span class = "point"><%= post.getName()%></span>

        <button type="submit" name = "deleteButton" value="<%= post.getPostId()%>" class = "deleteButton">Delete</button>
    </div>

    <% }
    }
    %>
</form>
<form method="post">

    <div class = "input">
        <label>Работа:  <br /></label>
        <label>Зарплата:  <br /></label>
        <label>Время:  <br /></label>
    </div>
    <div class = "input">

        <label>
            <input type="text" name="name"><br />
        </label>
        <label>
            <input type="text" name="salary"><br />
        </label>
        <label>
            <input type="text" name="time"><br />
        </label>
    </div>
    <button type="submit" name="postFilter" class = "filterButton">Filter</button>
</form>

<div>
    <button onclick="location.href='/'"  class = "filterButton">Back to main</button>
</div>
</body>
</html>
