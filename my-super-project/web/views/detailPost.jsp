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
        <%--<span class = "point">email</span>--%>
        <%--<span class = "point">address</span>--%>
        <span class = "point">salary</span>
        <span class = "point">time</span>
        <%--<span class = "point">headId</span>--%>
    </div>
    <div class = "manyPoint">
        <span class = "point"><%= post.getPostId()%></span>
        <span class = "point"><%= post.getName()%></span>
        <%--<span class = "point"><%= employee.getEmail()%></span>--%>
        <%--<span class = "point"><%= employee.getAddress()%></span>--%>
        <span class = "point"><%= post.getSalary()%></span>
        <span class = "point"><%= post.getTime()%></span>
        <%--<span class = "point"><%= employee.getHead()%></span>--%>
    </div>

    <%--out.println("<li>" + employee + "</li>");--%>
    <%--%>--%>





    <%--<form method="post">--%>

    <div class = "input">
        <label>Работа:  <br /></label>
        <%--<label>Email работника:  <br /></label>--%>
        <%--<label>Адрес работника:  <br /></label>--%>
        <label>Зарплата:  <br /></label>
        <label>Время:  <br /></label>
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
            <input type="text" name="name" value="<%= post.getName()%>"><br />
        </label>
        <%--<label>--%>
            <%--<input type="text" name="email" value="<%= employee.getEmail()%>"><br />--%>
        <%--</label>--%>
        <%--<label>--%>
            <%--<input type="text" name="address" value="<%= employee.getAddress()%>"><br />--%>
        <%--</label>--%>
        <label>
            <input type="text" name="salary" value="<%= post.getSalary()%>"><br />
        </label>
        <label>
            <input type="text" name="time" value="<%= post.getTime()%>"><br />
        </label>
        <%--<label>--%>
            <%--<input type="text" name="head" value="<%= employee.getHead()%>"><br />--%>
        <%--</label>--%>
    </div>
    <%--<button type="submit" name="postFilter" class = "filterButton">Filter</button>--%>
    <%--</form>--%>





    <%--<label>Name:--%>
    <%--<input type="text" name="name" value="<%= employee.getName()%>"><br />--%>
    <%--</label>--%>
    <%--<label>Email:--%>
    <%--<input type="text" name="email" value="<%= employee.getEmail()%>"><br />--%>
    <%--</label>--%>
    <%--<label>Address:--%>
    <%--<input type="text" name="address" value="<%= employee.getAddress()%>"><br />--%>
    <%--</label>--%>
    <%--<label>PostId:--%>
    <%--<input type="text" name="postid" value="<%= employee.getPostId()%>"><br />--%>
    <%--</label>--%>
    <%--<label>DepartmentId:--%>
    <%--<input type="text" name="departmentid" value="<%= employee.getDepartmentId()%>"><br />--%>
    <%--</label>--%>
    <%--<label>Head:--%>
    <%--<input type="text" name="head" value="<%= employee.getHead()%>"><br />--%>
    <%--</label>--%>
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
