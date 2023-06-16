<%@ page import="pack01.domain.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="pack01.dto.post.response.PostDepartmentResponse" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

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
            margin-top: 20px;
        }
        .card-container h3{
            text-align: center;
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

        .search-button, .sort-button{
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

        .search-button:hover {
            background-color: #0056b3;
        }

        .search-container, .sort-container {
            display: flex;
            justify-content: flex-end;
            gap: 15px;
            align-items: center;
            margin-bottom: 10px;
        }
        .sort-container{
            margin-top: 10px;
        }
    </style>
</head>
<body>
<jsp:include page="../../../header.jsp"/>
<%
    List<PostDepartmentResponse> posts = (List<PostDepartmentResponse>) request.getAttribute("posts");
    String search = (String) request.getAttribute("search");
%>

<div class="body">
    <h1>채용공고</h1>
    <div class="search-form">
        <form id="frm" class="search" method="get" action="/postlist">
            <div class="search-container">
                <select name="searchType" class="search-input">
                    <option value="title">공고 제목</option>
                    <option value="department">부서</option>
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
    <div class="">
        <a class="page" data-page="0">1</a>
        <a class="page" data-page="1">2</a>
    </div>
    <script>
        document.addEventListener("DOMContentLoaded", () => {

            $('.page').click(function (){
               var i =  $(this).data('page');
               $('.hidPage').val(i)
               $('#frm').submit();
            })

        });
    </script>
</div>
<jsp:include page="../../../footer.jsp"/>
</body>
</html>
