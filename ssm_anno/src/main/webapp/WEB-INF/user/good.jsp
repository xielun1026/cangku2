<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach items="${all}" var="all">
        <h1>${all.name}</h1>
        <h1>${all.subject}</h1>
        <h1>${all.score}</h1>
    </c:forEach>
</body>
</html>
