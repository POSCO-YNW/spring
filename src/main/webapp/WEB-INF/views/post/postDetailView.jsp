<%@ page import="pack01.domain.Post" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="pack01.domain.User" %>
<%@ page import="pack01.domain.type.RoleType" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>글 상세보기</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }

        .card {
            /*border: 1px solid #ccc;*/
            /*border-radius: 5px;*/
            padding: 20px;
            width: 80%;
            margin: auto;
            margin-bottom: 20px;
        }

        .title-container {
            padding: 20px;
        }

        .title {
            margin-bottom: 10px;
        }

        .department {
            font-size: 15px;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .date-container {
            border: 2px solid black;
            border-radius: 5px;
            padding: 20px;
            display: flex;
            align-content: space-around;
            justify-content: center;
        }

        .d-day {
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .d-day > span {
            color: #0033ff;
            font-size: 20px;
        }

        .date {
            font-size: 14px;
            color: #605e5e;
            margin-bottom: 10px;
        }

        .date > span {
            color: #888888;
        }

        .description {
            margin-bottom: 20px;
        }

        .apply-button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
        }

        .end-button {
            background-color: red;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
        }

        .apply-button:hover {
            background-color: #0056b3;
        }
    </style>
    <script>
        const onClick = () => {
            console.log("지원하기 버튼 눌림");
            window.location.href = "/resume/post"; // /resume/post?postId=1&departmentId=2
        }
    </script>
</head>
<body>
<jsp:include page="../../../header.jsp"/>

<%
    Post post = (Post) request.getAttribute("post");
    LocalDate currentDate = LocalDate.now();
    LocalDate endDate = post.getEndDate().toLocalDate();
    Long dDay = endDate.toEpochDay() - currentDate.toEpochDay();
%>

<div class="card">
    <div class="title-container">
        <div class="department"><%= post.getDepartmentId() %> 부서</div>
        <h1 class="title"><%= post.getTitle() %>
        </h1>
        <p>[경력]</p>
    </div>

    <div class="date-container">
        <div class="d-day">마감일까지 <span>D-<%= dDay %></span></div>
        <div class="date"><span>게시</span> <%= post.getStartDate() %>  | <span>공고 마감</span> <%= post.getEndDate() %>
        </div>
    </div>

    <div class="description">
        <h3>직무 정보</h3>
        <p><%= post.getDescription() %>
        </p>
    </div>

    <div class="image-file">

    </div>
    <hr>
    <%
        User user = (User) session.getAttribute("loginUser");

        if (endDate.isAfter(currentDate)) {
            if (user == null || user.getRole().equals(RoleType.APPLICANT)) {
    %>
    <a href="/resume/post?postId=<%=post.getPostId()%>&departmentId=<%=post.getDepartmentId()%>"
       class="apply-button">지원하기</a>
    <%
    } else if (Objects.equals(user.getUserId(), post.getAdminId())) {
    %>
    <a href="/postlist/deadline?id=<%=post.getPostId()%>"
       class="end-button">마감하기</a>
    <a href="/postlist/post/edit?id=<%=post.getPostId()%>" class="apply-button">수정하기</a>
    <a href="/postlist/post/delete?id=<%=post.getPostId()%>" class="apply-button">삭제하기</a>
    <%
        }
    } else {
    %>
    <h4>채용이 마감되었습니다.</h4>
    <br>
    <%
        }
        if (user != null && (user.getRole().equals(RoleType.ADMIN) || user.getRole().equals(RoleType.EMPLOYEE))) {
    %>
    <a href="/resume/list?postId=<%=post.getPostId()%>"
       class="apply-button">지원자 현황</a>
    <%
        }
    %>
</div>
<<<<<<< HEAD
=======
</div>
>>>>>>> 32cb8de71d28d097c28894bf76076502edd28e54
</body>
</html>
