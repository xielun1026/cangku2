<%--
  Created by IntelliJ IDEA.
  User: HASEE
  Date: 2019/10/9
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/save" method="post">
        <input type="text" name="name">
        <input type="text" name="subject">
        <input type="text" name="score">
        <input type="submit" value="提交">
    </form>
</body>
</html>
