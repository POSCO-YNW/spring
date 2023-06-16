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
            border-radius: 10px;
        }

        .user-info {
            display: flex;
            align-items: center;
        }

        .user-info h3 {
            margin-right: 20px;
        }

        /* 버튼 스타일 추가 */
        .edit-button {
            text-align: center;
            margin-bottom: 20px;
        }

        .edit-button input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
        }

        .edit-button input[type="submit"]:hover {
            background-color: #45a049;
        }

        .resume-button {
            text-align: center;
            margin-bottom: 20px;
        }

        .resume-button input[type="submit"] {
            background-color: deepskyblue;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 12px;
            border-radius: 5px;
            cursor: pointer;
        }

        .resume-button input[type="submit"]:hover {
            background-color: dodgerblue;
        }
    </style>
</head>
<body>
<jsp:include page="../../../header.jsp"/>
<% User user = (User) session.getAttribute("loginUser"); %>
<div class="container">
    <h1>마이페이지</h1>
    <div class="edit-button">
        <form action="/mypage/edituser">
            <input class="button" type="submit" value="개인정보 수정">
        </form>
    </div>
    <hr>

    <h2>내가 지원한 공고</h2>

    <div class="post-container">
        <c:forEach var="resume" items="${ResumePostResponses}">
            <div class="post-card">
                <h3>${resume.title}</h3>
                <p>상태: ${resume.status.description}</p>
                <p>마감일: ${resume.endDate}</p>
                <div class="resume-button">
                    <form id="resumeForm" action="/resume/detail" method="get">
                        <input type="hidden" name="postId" value="${resume.postId}">
                        <input type="hidden" name="resumeId" value="${resume.resumeId}">
                        <input type="hidden" name="userId" value="${resume.applicantId}">
                        <input class="button" type="submit" value="이력서 보기">
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>

</div>
</body>
</html>
