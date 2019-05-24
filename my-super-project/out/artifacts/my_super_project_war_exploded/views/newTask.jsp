<%@ page import="app.entities.Employee" %>
<%@ page import="app.entities.Post" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: gamer
  Date: 17.04.2019
  Time: 1:02
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
    <h1>Your posts!</h1>
</div>

<%--<div class = "manyPoint">--%>
    <%--<span class = "point">id</span>--%>
    <%--<span class = "point">name</span>--%>
    <%--<span class = "point">postId</span>--%>
    <%--<span class = "point">departmentId</span>--%>
<%--</div>--%>
<%
    ArrayList<Employee> employees = (ArrayList<Employee>) request.getAttribute("employeesData");
    ArrayList<Post> posts = (ArrayList<Post>) request.getAttribute("postsData");

    if (posts != null && !posts.isEmpty()) {
        for (Post post : posts) {
%>

<div class = "manyPoint" >
    <span class = "point"><%= post.getPostId()%></span>
    <span class = "point"><%= post.getName()%></span>
    <span class = "point"><%= post.getTime()%></span>
    <span class = "point"><%= post.getSalary()%></span>
    <span id="fileDownload"><a id="downloadLink" class="hyperLink" href="<%=request.getContextPath()%>/newTask?postId=<%=post.getPostId()%>">Download</a></span>
    <%--<button type="submit" name = "deleteButton" value="<%= post.getPostId()%>" class = "deleteButton">Delete</button>--%>


    <div class = "point">
        <%
            if (employees != null && !employees.isEmpty()) {
                for (Employee employee: employees) {
if(employee.getPostId() == post.getPostId()) {%><span><%= employee.getName()%></span> <br>
        <%
                    }
                }
        }
        %>
    </div>





    <%--<button type="submit" name = "deleteButton" value="<%= employee.getEmployeeId()%>" class = "deleteButton">Delete</button>--%>
</div>
<% }
}
%>

<div>
    <button onclick="location.href='/'"  class = "filterButton">Back to main</button>
</div>

</body>
</html>
