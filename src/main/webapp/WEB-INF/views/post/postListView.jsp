<%@ page import="pack01.domain.Post" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>채용공고 리스트 뷰</title>
    <style>
        h1{
            padding-bottom: 10px;
        }
        .card-container {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            grid-gap: 20px;
        }
    </style>
</head>
<body>
    <jsp:include page="../../../header.jsp"/>
<%
    List<Post> posts = (List<Post>) request.getAttribute("posts");
%>
    <h1>채용공고</h1>
    <form class="search" method="post" action="/">
        <input type="text" placeholder="부서, 제목으로 검색해보세요">
    </form>
    <hr/>
    <div class="card-container">
        <%
            if(posts.isEmpty()){
        %>
                <h3>!채용중인 공고가 없습니다!</h3>
        <%
            } else{
                for (Post post: posts) {
                    request.setAttribute("post", post);
        %>
                    <jsp:include page="postCard.jsp"/>
        <%
                }
            }
        %>
    </div>
</body>
</html>
