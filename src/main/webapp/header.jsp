<%@ page import="pack01.domain.User" %>
<%@ page import="pack01.domain.type.RoleType" %>
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
            color: #0033ff;
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
            border-bottom: 2px solid black;
        }

        header {
            width: 100%;
            height: 80px;
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
            justify-content: space-between;
            /*flex-grow: 0.2;*/
            /*width: fit-content;*/
        }

        header ul {
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: space-between;
        }

        header ul > li {
            font-size: 16px;
            height: 100%;
            display: flex;
            align-items: center;
            padding: 10px;
            margin-right: 10px;
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

        @media (max-width: 600px) {
            header > h2 {
                font-size: 24px;
            }

            header ul > li {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>
<div class="page">
    <header>
        <a href="/postlist"><h2>PoCruit</h2></a>
        <nav>
            <ul>
                <li><a href="/postlist">채용공고</a></li>
                <% User user = (User) session.getAttribute("loginUser");
                    if (user != null && RoleType.APPLICANT.equals(user.getRole())) { %>
                <li><a href="/">나의 지원서</a></li>
                <%
                    }
                    if (user != null && RoleType.ADMIN.equals(user.getRole())) {
                %>
                <li><a href="/post/write">채용공고 작성</a></li>
                <li><a href="/post/write">지원자 현황</a></li>
                <% }
                    if (user != null) {
                %>
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
