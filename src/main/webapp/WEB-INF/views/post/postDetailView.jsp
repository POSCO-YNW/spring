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
            background-color: #f5f5f5;
        }
        .card {
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 20px;
            margin-bottom: 20px;
        }

        .title {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .subtitle {
            font-size: 16px;
            color: #888;
            margin-bottom: 10px;
        }

        .d-day {
            font-size: 36px;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .date {
            font-size: 14px;
            color: #888;
            margin-bottom: 10px;
        }

        .description {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <%
        Post post = (Post) request.getAttribute("post");
        LocalDate currentDate = LocalDate.now();
        LocalDate endDate = post.getEndDate().toLocalDate();
        Long dDay = endDate.toEpochDay() - currentDate.toEpochDay();
    %>
    <div>
        <h6>부서: <%= post.getDepartmentId() %> 부서</h6>
        <h1><%= post.getTitle() %></h1>
        <p>[경력]</p>
    </div>
    <div>
        <h3>D-<%=dDay%></h3>
        <p>게시: <%= post.getStartDate() %></p>
        <p>공고 마감: <%= post.getEndDate() %></p>
    </div>
    <div>
        <h3>직무 정보</h3>
        <p><%=post.getDescription()%></p>
    </div>
</body>
</html>
