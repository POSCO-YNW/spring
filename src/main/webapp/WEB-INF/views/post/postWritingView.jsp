<%@ page import="pack01.domain.Post" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>채용공고 작성</title>
    <style>
        body {
            margin: 0;
        }
        .form-container {
            width: 400px;
            margin: 0 auto;
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
