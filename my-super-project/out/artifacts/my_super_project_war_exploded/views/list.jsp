<%@ page import="java.util.ArrayList" %>
<%@ page import="app.entities.Employee" %><%--
  Created by IntelliJ IDEA.
  User: gamer
  Date: 26.02.2019
  Time: 1:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users list</title>

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
    <h1>Your employees!</h1>
</div>

<form method="post">
    <div class = "manyPoint">
        <span class = "point">id</span>
        <span class = "point">name</span>
        <span class = "point">postId</span>
        <span class = "point">departmentId</span>
    </div>
<%
    ArrayList<Employee> employees = (ArrayList<Employee>) request.getAttribute("employeesData");

    if (employees != null && !employees.isEmpty()) {
        for (Employee employee : employees) {
            %>

        <div class = "manyPoint" onclick=location.href='/detail?EmployeeId=<%= employee.getEmployeeId()%>'>
            <span class = "point"><%= employee.getEmployeeId()%></span>
            <span class = "point"><%= employee.getName()%></span>
            <span class = "point"><%= employee.getPostId()%></span>
            <span class = "point"><%= employee.getDepartmentId()%></span>
            <span id="fileDownload"><a id="downloadLink" class="hyperLink" href="<%=request.getContextPath()%>/list?employeeId=<%=employee.getEmployeeId()%>">Download</a></span>

        <button type="submit" name = "deleteButton" value="<%= employee.getEmployeeId()%>" class = "deleteButton">Delete</button>
    </div>
       <% }
    }
%>
</form>
<form method="post">

    <div class = "input">
        <label>Имя работника:  <br /></label>
        <label>Email работника:  <br /></label>
        <label>Адрес работника:  <br /></label>
        <label>Должность работника(id):  <br /></label>
        <label>Отдел работника(id):  <br /></label>
        <label>Начальник работника(id):  <br /></label>
    </div>
    <div class = "input">

    <label>
        <input type="text" name="name"><br />
    </label>
    <label>
        <input type="text" name="email"><br />
    </label>
    <label>
        <input type="text" name="address"><br />
    </label>
    <label>
        <input type="text" name="postid"><br />
    </label>
    <label>
        <input type="text" name="departmentid"><br />
    </label>
    <label>
        <input type="text" name="head"><br />
    </label>
    </div>
    <button type="submit" name="postFilter" class = "filterButton">Filter</button>
</form>

<div>
    <button onclick="location.href='/'"  class = "filterButton">Back to main</button>
</div>
</body>
</html>
