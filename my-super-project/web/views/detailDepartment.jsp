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
        <%--<span class = "point">email</span>--%>
        <span class = "point">address</span>
        <%--<span class = "point">postId</span>--%>
        <%--<span class = "point">departmentId</span>--%>
        <%--<span class = "point">headId</span>--%>
    </div>
    <div class = "manyPoint">
        <span class = "point"><%= department.getDepartmentId()%></span>
        <span class = "point"><%= department.getName()%></span>
        <%--<span class = "point"><%= department.getEmail()%></span>--%>
        <span class = "point"><%= department.getAddress()%></span>
        <%--<span class = "point"><%= department.getPostId()%></span>--%>
        <%--<span class = "point"><%= department.getDepartmentId()%></span>--%>
        <%--<span class = "point"><%= department.getHead()%></span>--%>
    </div>

    <%--out.println("<li>" + employee + "</li>");--%>
    <%--%>--%>





    <%--<form method="post">--%>

    <div class = "input">
        <label>Отдел:  <br /></label>
        <%--<label>Email работника:  <br /></label>--%>
        <label>Адрес отдела:  <br /></label>
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
            <input type="text" name="name" value="<%= department.getName()%>"><br />
        </label>
        <%--<label>--%>
            <%--<input type="text" name="email" value="<%= department.getEmail()%>"><br />--%>
        <%--</label>--%>
        <label>
            <input type="text" name="address" value="<%= department.getAddress()%>"><br />
        </label>
        <%--<label>--%>
            <%--<input type="text" name="postid" value="<%= department.getPostId()%>"><br />--%>
        <%--</label>--%>
        <%--<label>--%>
            <%--<input type="text" name="departmentid" value="<%= department.getDepartmentId()%>"><br />--%>
        <%--</label>--%>
        <%--<label>--%>
            <%--<input type="text" name="head" value="<%= department.getHead()%>"><br />--%>
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
