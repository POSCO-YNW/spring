<%@ page import="pack01.domain.Post" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="pack01.domain.User" %>
<%@ page import="pack01.domain.type.RoleType" %>
<%@ page import="java.util.Objects" %>
<%@ page import="pack01.dto.post.response.PostDepartmentResponse" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>글 상세보기</title>
    <style>
        body {
            margin: 0;
        }

        .card {
            /*border: 1px solid #ccc;*/
            /*border-radius: 5px;*/
            padding: 20px;
            width: 60%;
            margin: auto;
            margin-bottom: 20px;
        }

        .title-container {
            padding-bottom: 20px;
        }

        .title {
            margin-bottom: 10px;
        }

        .department {
            font-size: 15px;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .date-container {
            border: 2px solid black;
            border-radius: 5px;
            padding: 20px;
            display: flex;
            justify-items: center;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 50px;
        }

        .d-day {
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .d-day > span {
            color: #0033ff;
            font-size: 20px;
        }

        .date {
            font-size: 14px;
            color: #605e5e;
            margin-bottom: 10px;
        }

        .date > span {
            color: #888888;
        }

        .description {
            width: 80%;
            margin: auto auto 20px auto;
        }
        .description h1{
            margin-bottom: 30px;
        }

        .apply-button, .end-button{
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
            display: block;
            width: fit-content;
            margin: auto;
        }

        .end-button {
            background-color: red;
            color: white;
        }

        .apply-button:hover {
            background-color: #0056b3;
            color: white;
        }
        textarea[readonly] {
            border: none;
            resize: none;
            background-color: transparent;
            height: auto;
            overflow: hidden;
            padding: 0;
            line-height: 2em; /* 줄 간격 설정 */
            pointer-events: none;
        }
        .btn-container{
            display: flex;
            gap: 10px;
            margin-bottom: 20px;
        }
        h4{
           text-align: center;
        }
    </style>
    <script>
        const onClick = () => {
            console.log("지원하기 버튼 눌림");
            window.location.href = "/resume/post"; // /resume/post?postId=1&departmentId=2
        }
        window.addEventListener("DOMContentLoaded", () => {
            const textareas = document.querySelectorAll("textarea[readonly]");
            textareas.forEach((textarea) => {
                textarea.style.height = "auto";
                textarea.style.height = textarea.scrollHeight + "px";
            });
        });
    </script>
</head>
<body>
<jsp:include page="../../../header.jsp"/>

<%
//    Post post = (Post) request.getAttribute("post");
    PostDepartmentResponse post = (PostDepartmentResponse) request.getAttribute("post");

    LocalDate currentDate = LocalDate.now();
    LocalDate endDate = post.getEndDate().toLocalDate();
    Long dDay = endDate.toEpochDay() - currentDate.toEpochDay();
    String[] descLines = post.getDescription().split("\\$\\$");
%>

<div class="card">
    <div class="title-container">
        <div class="department"><%= post.getName() %> 부서</div>
        <h1 class="title"><%= post.getTitle()%></h1>
    </div>

    <div class="date-container">
        <div class="d-day">마감일까지 <span>D-<%= dDay %></span></div>
        <div class="date"><span>게시</span> <%= post.getStartDate() %>  | <span>공고 마감</span> <%= post.getEndDate() %>
        </div>
    </div>

    <div class="description">
        <h1>모집 안내</h1>

        <h2>채용 구분</h2>
        <p><%=descLines[0]%></p>
        <hr/>

        <h2>자격 요건</h2>
        <textarea readonly><%=descLines[1]%></textarea>
        <hr/>

        <h2>수행 업무</h2>
        <textarea readonly><%=descLines[2]%></textarea>
        <hr/>

        <h2>채용 인원</h2>
        <p><%=descLines[3]%></p>
        <hr/>

        <h2>예상 급여</h2>
        <p><%=descLines[4]%></p>
        <hr/>

        <h2>근무 조건 및 복리 후생</h2>
        <textarea readonly><%=descLines[5]%></textarea>

    </div>

    <div class="image-file"></div>
    <%
        User user = (User) session.getAttribute("loginUser");

        if (endDate.isAfter(currentDate)) {
            if (user == null || user.getRole().equals(RoleType.APPLICANT)) {
    %>
    <a href="/resume/post?postId=<%=post.getPostId()%>&departmentId=<%=post.getDepartmentId()%>"
       class="apply-button">지원하기</a>
    <%
    } else if (Objects.equals(user.getUserId(), post.getAdminId())) {
    %>
    <div class="btn-container">
        <a href="/postlist/deadline?id=<%=post.getPostId()%>"
           class="end-button">마감하기</a>
        <a href="/postlist/post/edit?id=<%=post.getPostId()%>" class="apply-button">수정하기</a>
        <a href="/postlist/post/delete?id=<%=post.getPostId()%>" class="end-button">삭제하기</a>
    </div>
    <%
        }
    } else {
    %>
    <h2>채용이 마감되었습니다.</h2>
    <br>
    <%
        }
        if (user != null && (user.getRole().equals(RoleType.ADMIN) || user.getRole().equals(RoleType.EMPLOYEE))) {
    %>
    <a href="/resume/list?postId=<%=post.getPostId()%>"
       class="apply-button">지원자 현황</a>
    <%
        }
    %>
</div>
<jsp:include page="../../../footer.jsp"/>
</body>
</html>
