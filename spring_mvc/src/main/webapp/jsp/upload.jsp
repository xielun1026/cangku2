<%--
  Created by IntelliJ IDEA.
  User: HASEE
  Date: 2019/9/26
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/quick21" method="post" enctype="multipart/form-data">
        <input type="text" name="username"><br/>
        <input type="file" name="upload"><br/>
        <input type="file" name="upload"><br/>
        <input type="submit" value="上传">
    </form>
</body>
</html>
