<%@ page import="pack01.domain.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
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
    <div class="search-form">
        <form class="search" method="get" action="/postlist/search">
            <select name="searchType" class="search-input">
                <option value="title">공고 제목</option>
                <option value="department">부서</option>
            </select>
            <input type="text" name="search" class="search-input" placeholder="관심 부서나 공고 제목으로 검색하세요">
<%--            <input type="hidden" name="page" class="search-input" value="1">--%>
            <input type="submit" value="검색" class="search-button">
        </form>
    </div>
    <hr/>
    <div class="sort-buttons">
        <form action="/postlist/sort" method="get">
            <input type="hidden" name="type" value="latest">
            <input type="submit" value="최신순" class="sort-button">
        </form>
        <form action="/postlist/sort" method="get">
            <input type="hidden" name="type" value="deadline">
            <input type="submit" value="마감순" class="sort-button">
        </form>
    </div>
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
