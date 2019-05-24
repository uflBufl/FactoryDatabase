<%--
  Created by IntelliJ IDEA.
  User: gamer
  Date: 22.05.2019
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Import</title>

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
    <h1>Import!</h1>
</div>


<form method="post" action="${pageContext.request.contextPath}/import"
      enctype="multipart/form-data">

    Select file to upload:
    <br />
    <input type="file" name="file"  />
    <br />
    <%--<input type="file" name="file" />--%>
    <br />
    <br />
    <input type="submit" value="Upload" />
</form>


<div>
    <button onclick="location.href='/'"  class = "filterButton">Back to main</button>
</div>

</body>
</html>
