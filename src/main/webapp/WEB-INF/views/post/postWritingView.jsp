<%@ page import="pack01.domain.Post" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>채용공고 작성</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }
        .form-container {
            width: 400px;
            margin: 0 auto;
        }
        .form-container h1{
            text-align: center;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .form-group input[type="text"],
        .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .form-group textarea {
            height: 150px;
        }
        .form-group button {
            padding: 10px 20px;
            background-color: #4caf50;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<jsp:include page="../../../header.jsp"/>
<%
    Post post = (Post) request.getAttribute("post");
%>
<div class="form-container">
    <% out.println(post != null ? "<h1>채용공고 수정</h1>" : "<h1>채용공고 작성</h1>"); %>

    <jsp:include page="../postForm.jsp"/>
</div>
<jsp:include page="../../../footer.jsp"/>
</body>
</html>
