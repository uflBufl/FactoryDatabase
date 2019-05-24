<%--
  Created by IntelliJ IDEA.
  User: gamer
  Date: 24.05.2019
  Time: 2:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
Элемент с таким id уже есть в базе данных. Заменить объект?

<form method="post" action="${pageContext.request.contextPath}/error">
    <button type="submit">OK</button>
</form>

<div>
    <button onclick="location.href='/'"  class = "filterButton">No</button>
</div>

</body>
</html>
