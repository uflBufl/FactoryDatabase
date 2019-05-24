<%@ page import="app.entities.Department" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: gamer
  Date: 01.04.2019
  Time: 5:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Departments list</title>

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
    <h1>Your departments!</h1>
</div>

<form method="post">
    <div class = "manyPoint">
        <span class = "point">id</span>
        <span class = "point">name</span>
    </div>
    <%
        ArrayList<Department> departments = (ArrayList<Department>) request.getAttribute("departmentsData");

        if (departments != null && !departments.isEmpty()) {
            for (Department department : departments) {
    %>

    <div class = "manyPoint" onclick=location.href='/detailDepartment?DepartmentId=<%= department.getDepartmentId()%>'>
        <span class = "point"><%= department.getDepartmentId()%></span>
        <span class = "point"><%= department.getName()%></span>
        <span id="fileDownload"><a id="downloadLink" class="hyperLink" href="<%=request.getContextPath()%>/listDepartment?departmentId=<%=department.getDepartmentId()%>">Download</a></span>

        <button type="submit" name = "deleteButton" value="<%= department.getDepartmentId()%>" class = "deleteButton">Delete</button>
    </div>

    <% }
    }
    %>
</form>
<form method="post">

    <div class = "input">
        <label>Отдел:  <br /></label>
        <label>Адрес отдела:  <br /></label>
    </div>
    <div class = "input">

        <label>
            <input type="text" name="name"><br />
        </label>
        <label>
            <input type="text" name="address"><br />
        </label>
    </div>
    <button type="submit" name="postFilter" class = "filterButton">Filter</button>
</form>

<div>
    <button onclick="location.href='/'"  class = "filterButton">Back to main</button>
</div>
</body>
</html>
