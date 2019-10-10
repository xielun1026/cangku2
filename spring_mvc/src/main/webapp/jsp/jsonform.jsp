<%--
  Created by IntelliJ IDEA.
  User: HASEE
  Date: 2019/9/25
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
</head>
<body>
     <script>
         var array = new Array();
         array.push({name:"zhangsan",age:"20"});
         array.push({name:"lisi",age:"21"});

         $.ajax({
             type:"POST",
             url:"${pageContext.request.contextPath}/quick16",
             data:JSON.stringify(array),
             contentType:"application/json;charset=utf-8"
         });
     </script>
</body>
</html>
