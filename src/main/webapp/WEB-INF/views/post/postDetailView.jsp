<%@ page import="pack01.domain.Post" %>
<%@ page import="java.time.LocalDate" %>
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
        .title-container{
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
        .date-container{
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
        .d-day > span{
            color: #0033ff;
            font-size: 20px;
        }
        .date {
            font-size: 14px;
            color: #605e5e;
            margin-bottom: 10px;
        }
        .date > span{
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

        .apply-button:hover {
            background-color: #0056b3;
        }
    </style>
    <script>
        const onClick = () => {
            console.log("지원하기 버튼 눌림");
            window.location.href = "/index.jsp"; // 지원서 작성 페이지로 이동하기
        }
    </script>
</head>
<body>
<jsp:include page="../../../header.jsp" />

<%
    Post post = (Post) request.getAttribute("post");
    LocalDate currentDate = LocalDate.now();
    LocalDate endDate = post.getEndDate().toLocalDate();
    Long dDay = endDate.toEpochDay() - currentDate.toEpochDay();
%>

<div class="card">
    <div class="title-container">
        <div class="department"><%= post.getDepartmentId() %> 부서</div>
        <h1 class="title"><%= post.getTitle() %></h1>
        <p>[경력]</p>
    </div>

    <div class="date-container">
        <div class="d-day">마감일까지 <span>D-<%= dDay %></span></div>
        <div class="date"><span>게시</span> <%= post.getStartDate() %>  | <span>공고 마감</span> <%= post.getEndDate() %></div>
    </div>

    <div class="description">
        <h3>직무 정보</h3>
        <p><%= post.getDescription() %></p>
    </div>

    <div class="image-file">

    </div>
</div>

<button class="apply-button" onclick="onClick()">지원하기</button>
</body>
</html>
