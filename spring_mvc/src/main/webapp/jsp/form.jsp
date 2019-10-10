<%--
  Created by IntelliJ IDEA.
  User: HASEE
  Date: 2019/9/25
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/quick15" method="post">
          <input type="text" name="list[0].name" >
          <input type="text" name="list[0].age" >
          <input type="text" name="list[1].name" >
          <input type="text" name="list[1].age" >
          <input type="submit" value="提交">
    </form>
</body>
</html>
