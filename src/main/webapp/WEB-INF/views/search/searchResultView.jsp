<%@ page import="java.util.List" %>
<%@ page import="pack01.domain.Post" %><%--
  Created by IntelliJ IDEA.
  User: nmj37
  Date: 2023-06-15
  Time: 오후 2:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>검색 결과 페이지</title>
    <style>
        h1 {
            padding-bottom: 10px;
        }

        .card-container {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            grid-gap: 20px;
        }

        button {
            cursor: pointer;
        }

        .back-btn {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            text-align: center;
        }

        .cancel-btn {
            border: 1px solid wheat;
            border-radius: 1rem;
            background-color: #cccccc;
            padding: 10px 20px;
        }
    </style>
    <script>
        const onClick = () => {
            window.history.back();
        }
        const onCancel = () => {
            window.location.href = "postListView.jsp";
        }
    </script>
</head>
<body>
<jsp:include page="../../../header.jsp"/>

<%
    List<Post> posts = (List<Post>) request.getAttribute("posts");
//    String search = request.getParameter("search");
%>
<h1>채용공고</h1>
<hr/>
<div class="search-category">
    <button class="cancel-btn" onclick="onCancel"><%=request.getParameter("search")%> X</button>
</div>
<div class="card-container">
    <%
        if (posts.isEmpty()) {
    %>
    <h3>!검색된 공고가 없습니다!</h3>
    <%
    } else {
        for (Post post : posts) {
            request.setAttribute("post", post);
    %>
    <jsp:include page="../post/postCard.jsp"/>
    <%
            }
        }
    %>
</div>
<button class="back-btn" onclick="onClick()">뒤로 가기</button>
</body>
</html>
