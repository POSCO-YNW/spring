<%--
  Created by IntelliJ IDEA.
  User: nmj37
  Date: 2023-06-15
  Time: 오후 5:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>공고 작성, 지원서작성 완료 페이지</title>
</head>
<body>
<%
    String context = (String) request.getAttribute("context");
%>
<h1><%=context%>></h1>
<a href="/" class="home-button">홈으로 가기</a>
</body>
</html>
