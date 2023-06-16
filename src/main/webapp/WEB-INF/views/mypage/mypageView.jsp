<%@ page import="pack01.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="pack01.domain.type.RoleType" %>

<html>
<head>
    <title>마이페이지</title>
    <style>
        /* 스타일을 추가하여 원하는 레이아웃을 구성하세요 */
        .container {
            max-width: 1440px;
            width: 80%;
            margin: 0 auto;
            padding: 20px;
        }

        h1, h2 {
            text-align: center;
        }

        .welcome {
            text-align: right;
            margin-bottom: 20px;
        }

        .post-container {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            grid-gap: 20px;
        }

        .post-card {
            padding: 10px;
            background-color: #f1f1f1;
            border-radius: 5px;
        }

        .user-info {
            display: flex;
            align-items: center;
        }

        .user-info h3 {
            margin-right: 20px;
        }
    </style>
</head>
<body>
<jsp:include page="../../../header.jsp"/>
<% User user = (User) session.getAttribute("loginUser"); %>
<div class="container">
    <h1>마이페이지</h1>
    <h2>내가 지원한 공고</h2>

    <div class="post-container">
        <c:forEach var="post" items="${myresume}">
            <div class="post-card">
                <h3>${post.title}</h3>
                <p>시작일: ${post.startDate}</p>
                <p>종료일: ${post.endDate}</p>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>