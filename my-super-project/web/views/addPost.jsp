<%--
  Created by IntelliJ IDEA.
  User: gamer
  Date: 01.04.2019
  Time: 3:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new post</title>

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
    <h1>New post!</h1>
</div>

<div>
    <div>
        <div>
            <h2>Add Post</h2>
        </div>



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
            <button type="submit" name="postFilter" class = "filterButton">Add</button>
        </form>

    </div>
</div>

<div>
    <button onclick="location.href='/'" class = "filterButton">Back to main</button>
</div>
</body>
</html>
