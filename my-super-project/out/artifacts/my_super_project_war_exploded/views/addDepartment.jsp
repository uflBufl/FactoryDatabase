<%--
  Created by IntelliJ IDEA.
  User: gamer
  Date: 01.04.2019
  Time: 5:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new Department</title>

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
    <h1>New department!</h1>
</div>

<div>
    <%--<%--%>
    <%--if (request.getAttribute("userName") != null) {--%>
    <%--out.println("<p>User '" + request.getAttribute("userName") + "' added!</p>");--%>
    <%--}--%>
    <%--%>--%>
    <div>
        <div>
            <h2>Add Department</h2>
        </div>



        <form method="post">

            <div class = "input">
                <label>Отдел:  <br /></label>
                <%--<label>Email работника:  <br /></label>--%>
                <label>Адрес отдела:  <br /></label>
                <%--<label>Должность работника(id):  <br /></label>--%>
                <%--<label>Отдел работника(id):  <br /></label>--%>
                <%--<label>Начальник работника(id):  <br /></label>--%>
            </div>
            <div class = "input">
                <label>
                    <input type="text" name="name"><br />
                </label>
                <%--<label>--%>
                    <%--<input type="text" name="email"><br />--%>
                <%--</label>--%>
                <label>
                    <input type="text" name="address"><br />
                </label>
                <%--<label>--%>
                    <%--<input type="text" name="postid"><br />--%>
                <%--</label>--%>
                <%--<label>--%>
                    <%--<input type="text" name="departmentid"><br />--%>
                <%--</label>--%>
                <%--<label>--%>
                    <%--<input type="text" name="head"><br />--%>
                <%--</label>--%>
            </div>
            <button type="submit" name="postFilter" class = "filterButton">Add</button>
        </form>



        <%--<form method="post">--%>
        <%--<label>Name:--%>
        <%--<input type="text" name="name"><br />--%>
        <%--</label>--%>
        <%--<label>Email:--%>
        <%--<input type="text" name="email"><br />--%>
        <%--</label>--%>
        <%--<label>Address:--%>
        <%--<input type="text" name="address"><br />--%>
        <%--</label>--%>
        <%--<label>PostId:--%>
        <%--<input type="text" name="postid"><br />--%>
        <%--</label>--%>
        <%--<label>DepartmentId:--%>
        <%--<input type="text" name="departmentid"><br />--%>
        <%--</label>--%>
        <%--<label>Head:--%>
        <%--<input type="text" name="head"><br />--%>
        <%--</label>--%>
        <%--&lt;%&ndash;<label>Password:&ndash;%&gt;--%>
        <%--&lt;%&ndash;<input type="password" name="pass"><br />&ndash;%&gt;--%>
        <%--&lt;%&ndash;</label>&ndash;%&gt;--%>
        <%--<button type="submit">Submit</button>--%>
        <%--</form>--%>
    </div>
</div>

<div>
    <button onclick="location.href='/'" class = "filterButton">Back to main</button>
</div>
</body>
</html>

