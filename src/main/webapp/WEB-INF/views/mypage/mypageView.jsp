<%@ page import="pack01.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>마이페이지</title>
</head>
<body>
<% User user = (User) session.getAttribute("loginUser"); %>
<h1>마이페이지</h1>

<div>
    <h3>안녕하세요</h3>
    <h3><%= user.getUsername()%>님! 환영합니다!</h3>
</div>

<h2>내가 지원한 공고</h2>


</body>
</html>
