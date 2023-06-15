<%@ page import="pack01.domain.Post" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>글 카드 하나</title>
    <style>
        .card {
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 25px 15px;
            margin-bottom: 10px;
            height: 200px;
        }

        .card-title {
            font-weight: bold;
            font-size: 20px;
        }

        .department {
            margin-top: 10px;
            font-size: 16px;
            color: #888888;
        }

        .card-footer {
            margin-top: 10px;
            font-size: 12px;
            color: #888;
        }
    </style>
</head>
<body>
<%
    Post post = (Post) request.getAttribute("post");
    Long postId = post.getPostId();
%>

<a href="/postlist/post?id=<%=postId%>">
    <div class="card">
        <div class="department"><%= post.getDepartmentId() %> 부서</div>
        <div class="card-title"><%= post.getTitle() %></div>
        <div class="card-footer">
            공고마감: <%= post.getEndDate() %> |
            작성일: <%= post.getStartDate() %>
        </div>
    </div>
</a>
</body>
</html>
