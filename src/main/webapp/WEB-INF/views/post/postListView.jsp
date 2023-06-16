<%@ page import="pack01.domain.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>채용공고</title>
    <style>
        .body {
            max-width: 1440px;
            width: 80%;
            margin: 0 auto 70px auto;
        }

        h1 {
            padding-bottom: 10px;
        }

        .card-container {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            grid-gap: 20px;
        }

        select {
            width: 80px;
            padding: 5px;
            border: 1px solid #999;
            border-radius: 3px;
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
        }

        select::-ms-expand {
            display: none;
        }

        input[type="text"] {
            width: 250px;
            padding: 5px;
            border: 1px solid #999;
            border-radius: 3px;
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
        }

        input[type="text"]:focus {
            outline: none;
        }

        .search-button {
            display: inline-block;
            width: 50px;
            padding: 5px;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            color: #fff;
            font-size: 16px;
            font-weight: bold;
            text-align: center;
            text-decoration: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .search-button:hover {
            background-color: #0056b3;
        }

        .search-button:focus {
            outline: none;
            box-shadow: 0 0 5px #007bff;
        }
    </style>
</head>
<body>
<jsp:include page="../../../header.jsp"/>
<%
    List<Post> posts = (List<Post>) request.getAttribute("posts");
%>
<div class="body">
    <h1>채용공고</h1>
    <div class="search-form">
        <form class="search" method="get" action="/postlist/search">
            <select name="searchType" class="search-input">
                <option value="title">공고 제목</option>
                <option value="department">부서</option>
            </select>
            <input type="text" name="search" class="search-input" placeholder="관심 부서나 공고 제목으로 검색하세요.">
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
            if (posts.isEmpty()) {
        %>
        <h3>!채용중인 공고가 없습니다!</h3>
        <%
        } else {
            for (Post post : posts) {
                request.setAttribute("post", post);
        %>
        <jsp:include page="postCard.jsp"/>
        <%
                }
            }
        %>
    </div>
</div>
</body>
</html>
