<%@ page import="pack01.domain.User" %>
<%@ page import="pack01.domain.type.RoleType" %>
<%@ page import="java.util.UUID" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>header</title>
    <style>
        ul {
            list-style: none;
        }

        a {
            text-decoration: none;
            outline: none;
            color: black;
        }

        a:hover {
            transition: 0.3s ease-in;
            color: #05507d;
            cursor: pointer;
        }

        .logout {
            font-family: "KIMM_Bold", sans-serif;
        }

        .logout:hover {
            color: red;
        }

        .page {
            max-width: 1440px;
            width: 80%;
            margin: 0 auto 70px auto;
            padding: 0 20px;
            border-bottom: 3px solid black;
        }

        header {
            width: 100%;
            height: 150px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        header > h2 {
            margin-left: 20px;
        }

        header > nav {
            display: flex;
            align-items: center;
            justify-content: center;
            border: 1px solid red;
        }

        header ul {
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: space-between;
        }
        header ul:first-child {
            margin-right: auto;
        }

        header ul:last-child {
            margin-left: auto;
        }
        .user{

        }
        header ul > li {
            font-size: 16px;
            height: 100%;
            display: flex;
            align-items: center;
            padding: 20px;
            /*margin-right: 10px;*/
        }

        .copy-button {
            padding: 10px;
        }

        .copy-button:hover {
            background-color: #0033ff;
            color: white;
            border: none;
            border-radius: 10px;
            transition: 0.3s ease-in;
            cursor: pointer;
        }
        i{
            color: #05507d;
            font-size: 20px;
            border: 1px solid blue;
            padding: 30px 10px 0px 10px;
        }
        @media (max-width: 600px) {
            header > h2 {
                font-size: 24px;
            }

            header ul > li {
                font-size: 14px;
            }
        }
    </style>
    <script src="https://kit.fontawesome.com/e3a7d25f3f.js" crossorigin="anonymous"></script>
    <script>
        const copyCode = () =>{
            var inviteUrl = document.getElementById('inviteUrl');
            inviteUrl.select();
            document.execCommand('copy');
            alert('초대 URL이 클립보드에 복사되었습니다.');
        }
    </script>
</head>
<body>
<%
    User user = (User) session.getAttribute("loginUser");
    String inviteCode = UUID.randomUUID().toString().replace("-", "");
    String inviteUrl = request.getRequestURL().toString() + "?code=" + inviteCode);
%>
<div class="page">
    <header>
        <a href="/postlist"><h2>PoCruit</h2></a>
        <nav>
            <ul>
                <li><a href="/postlist">채용공고</a></li>
                <%
                    if (user != null && RoleType.APPLICANT.equals(user.getRole())) { %>
                <li><a href="/mypage/get">마이페이지</a></li>
                <%
                    }
                    if (user != null && RoleType.ADMIN.equals(user.getRole())) {
                %>
                <li><a href="/postlist/post/write">채용공고 작성</a></li>
                <li><a href="/">지원자 현황</a></li>
                <li><button onclick="copyCode()">초대코드 생성</button></li>
            </ul>
        </nav>
        <nav>
            <ul class="user">
                <% }
                    if (user != null) {
                %>
                        <i class="fa-solid fa-user"></i>
                        <li><%= user.getUsername()%>님 환영합니다</li>
                        <li><a href="/logout" class="logout">로그아웃</a></li>
                <%
                } else {
                %>
                        <li><a href="/login" class="logout">로그인</a></li>
                <%
                    }
                %>
            </ul>
        </nav>
    </header>
</div>
</body>
</html>