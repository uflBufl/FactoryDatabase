<%--
  Created by IntelliJ IDEA.
  User: gamer
  Date: 26.02.2019
  Time: 1:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new user</title>

    <style type="text/css">
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
    <h1>New employee!</h1>
</div>

<div>
    <div>
        <div>
            <h2>Add Employee</h2>
        </div>

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
            <button type="submit" name="postFilter" class = "filterButton">Add</button>
        </form>

    </div>
</div>

<div>
    <button onclick="location.href='/'" class = "filterButton">Back to main</button>
</div>
</body>
</html>
