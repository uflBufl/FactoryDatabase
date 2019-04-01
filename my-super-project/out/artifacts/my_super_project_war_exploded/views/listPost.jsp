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
            /* background-color: red; */
            float: left;
        }
        .point {
            border: 1px solid #adc0ce;
            /* border-radius: 20px; */
            /*color: green;*/
            width: 200px;
            float: left;
        }
        .input {
            /* background-color: red; */
            /* width: 100px; */
            float: left;
            /* height: 200px; */
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
        <%--<span class = "point">email</span>--%>
        <%--<span class = "point">address</span>--%>
        <%--<span class = "point">postId</span>--%>
        <%--<span class = "point">departmentId</span>--%>
        <%--<span class = "point">headId</span>--%>
    </div>
    <%--<ul>--%>
    <%
        ArrayList<Post> posts = (ArrayList<Post>) request.getAttribute("postsData");

        if (posts != null && !posts.isEmpty()) {
            for (Post post : posts) {
    %>
    <%--out.println("<li onclick=\"location.href='/detail?EmployeeId="+employee.getEmployeeId()+"'\">" + employee + "</li>");--%>

    <%--<div>--%>
    <div class = "manyPoint" onclick=location.href='/detailPost?PostId=<%= post.getPostId()%>'>
        <span class = "point"><%= post.getPostId()%></span>
        <span class = "point"><%= post.getName()%></span>
        <%--<span class = "point"><%= employee.getEmail()%></span>--%>
        <%--<span class = "point"><%= employee.getAddress()%></span>--%>
        <%--<span class = "point"><%= employee.getPostId()%></span>--%>
        <%--<span class = "point"><%= employee.getDepartmentId()%></span>--%>
        <%--<span class = "point"><%= employee.getHead()%></span>--%>
        <%--</div>--%>

        <button type="submit" name = "deleteButton" value="<%= post.getPostId()%>" class = "deleteButton">Delete</button>
    </div>

    <%--<li onclick=location.href='/detail?EmployeeId=<%= employee.getEmployeeId()%>'><%= employee.getEmployeeId()%> <%= employee.getName()%> <%= employee.getAddress()%> <%= employee.getEmail()%> </li>--%>
    <%--<%--%>
    <%--out.println("<button type=\"submit\" name=\"deleteButton\" value=" + employee.getEmployeeId() + " >Delete</button>");%>--%>
    <%--</div>--%>
    <% }
    }
    %>
    <%--</ul>--%>
</form>
<%--<br /><br /><br />--%>
<form method="post">

    <div class = "input">
        <label>Работа:  <br /></label>
        <label>Зарплата:  <br /></label>
        <label>Время:  <br /></label>
        <%--<label>Должность работника(id):  <br /></label>--%>
        <%--<label>Отдел работника(id):  <br /></label>--%>
        <%--<label>Начальник работника(id):  <br /></label>--%>
    </div>
    <div class = "input">
        <%--<label><input value="132" />  <br /></label>--%>
        <%--<label><input value="132" />  <br /></label>--%>
        <%--<label><input value="132" />  <br /></label>--%>
        <%--<label><input value="132" />  <br /></label>--%>
        <%--<label><input value="132" />  <br /></label>--%>
        <%--<label><input value="132" />  <br /></label>--%>
        <%--<label><input value="132" />  <br /></label>--%>

        <label>
            <input type="text" name="name"><br />
        </label>
        <%--<label>--%>
            <%--<input type="text" name="email"><br />--%>
        <%--</label>--%>
        <%--<label>--%>
            <%--<input type="text" name="address"><br />--%>
        <%--</label>--%>
        <label>
            <input type="text" name="salary"><br />
        </label>
        <label>
            <input type="text" name="time"><br />
        </label>
        <%--<label>--%>
            <%--<input type="text" name="head"><br />--%>
        <%--</label>--%>
    </div>
    <button type="submit" name="postFilter" class = "filterButton">Filter</button>
</form>


<div>
    <button onclick="location.href='/'"  class = "filterButton">Back to main</button>
</div>
</body>
</html>
