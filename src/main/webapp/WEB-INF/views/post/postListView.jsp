<%@ page import="pack01.domain.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="pack01.dto.post.response.PostDepartmentResponse" %>
<%@ page import="pack01.service.PostService" %>
<%@ page import="pack01.repository.PostRepository" %>
<%@ page import="pack01.domain.Department" %>
<%@ page import="pack01.repository.DepartmentRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../font.jsp" %>

<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <title>채용공고</title>
    <style>
        body{
            margin: 0;
        }
        .body {
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
            /*margin-top: 20px;*/
        }

        .card-container h3 {
            text-align: center;
            margin: auto;
        }

        select {
            width: 80px;
            padding: 10px;
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
            padding: 10px;
            border: 1px solid #999;
            border-radius: 3px;
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
        }

        input[type="text"]:focus {
            outline: none;
        }

        .search-button, .sort-button, .map-btn {
            display: flex;
            /*width: 50px;*/
            padding: 10px;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            color: #fff;
            font-size: 16px;
            font-weight: bold;
            text-align: center;
            text-decoration: none;
            cursor: pointer;
            transition: 0.3s ease;
        }
        .map-btn{
            width: fit-content;
        }
        .map-btn-container {
            margin-left: auto;
            display: flex;
            justify-content: flex-end;
        }

        .search-button:hover, .map-btn:hover, .sort-button:hover, .search-button:hover {
            background-color: #0056b3;
            color: white;
        }

        .search-container, .sort-container {
            display: flex;
            justify-content: flex-end;
            gap: 15px;
            align-items: center;
            margin-bottom: 10px;
        }

        .sort-container {
            margin-top: 10px;
        }

        .paging {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .paging span {
            margin: 0 5px;
            padding: 5px;
            text-decoration: none;
            color: #007bff;
            border: 1px solid #007bff;
            border-radius: 3px;
            cursor: pointer; /* 마우스 커서를 포인터(클릭) 모양으로 변경 */
        }

        .paging span:hover {
            background-color: #007bff;
            color: #fff;
            transition: 0.3s ease-in;
        }
    </style>
</head>
<jsp:include page="../../../header.jsp"/>
<body>
<%
    List<PostDepartmentResponse> posts = (List<PostDepartmentResponse>) request.getAttribute("posts");
    String search = (String) request.getAttribute("search");

    int totalPosts = (int) request.getAttribute("postall");

    int postsPerPage = 9; // 페이지당 보여지는 게시글 수

    int totalPages = (int) Math.ceil((double) totalPosts / postsPerPage); // 전체 페이지 수
%>

<div class="body">
    <h1>채용공고</h1>
    <div class="search-form">
        <form id="frm" class="search" method="get" action="/postlist">
            <div class="search-container">
                <select name="searchType" class="search-input">
                    <option value="title"
                            <% if ("title".equals(request.getParameter("searchType"))) { %>selected<% } %>>공고 제목
                    </option>
                    <option value="department"
                            <% if ("department".equals(request.getParameter("searchType"))) { %>selected<% } %>>부서
                    </option>
                </select>
                <input type="text" name="search" class="search-input" placeholder="관심 부서나 공고 제목으로 검색하세요."
                       value="<%=search%>">
                <input type="submit" value="검색" class="search-button">
                <input type="hidden" class="hidPage" name="page" value="">
            </div>
            <div class="sort-container">
                <input type="submit" name="type" value="최신순" class="sort-button" id="latestButton">
                <input type="submit" name="type" value="마감순" class="sort-button" id="deadlineButton">
            </div>
        </form>
        <%
            if(!posts.isEmpty()){
        %>
        <div class="map-btn-container">
            <a href="/postlist/kakaoMap" class="map-btn">지도로 보기</a>
        </div>
        <%}%>
    </div>
    <hr/>
    <div class="card-container">
        <%
            if (posts.isEmpty()) {
        %>
        <h3>!채용중인 공고가 없습니다!</h3>
        <%
        } else {
            for (PostDepartmentResponse post : posts) {
                request.setAttribute("post", post);
        %>
        <jsp:include page="postCard.jsp"/>
        <%
                }
            }
        %>
    </div>

    <div class="card-container">
        <!-- 게시물 목록 표시 -->
    </div>

    <div class="paging">
        <%-- 서버에서 전달받은 총 페이지 수를 반복하여 페이지 링크 생성 --%>
        <% for (int i = 0; i < totalPages; i++) { %>
        <span class="page" data-page="<%= i %>"><%= i + 1 %></span>
        <% } %>
    </div>

    <script>
        document.addEventListener("DOMContentLoaded", () => {
            $('.page').click(function () {
                let i = $(this).data('page');
                $('.hidPage').val(i);
                $('#frm').submit();
            })

        });
    </script>

</div>

<jsp:include page="../../../footer.jsp"/>
</body>
</html>
