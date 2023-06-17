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
            color: white;
        }

        a {
            text-decoration: none;
            outline: none;
            color: white;
        }

        a:hover {
            transition: 0.3s ease-in;
            color: #9b9b9b;
            cursor: pointer;
        }

        .logout {
            font-family: "KIMM_Bold", sans-serif;
        }

        .logout:hover {
            color: red;
        }

        .header-page {
            width: 100%;
            margin: 0 auto 70px auto;
            background-image: url("/resources/static/images/posco-seoul.jpg");
            background-position: center top; /* 상단 위치에 맞게 조정 */
            background-repeat: no-repeat;
            background-size: cover;
        }

        header {
            width: 90%;
            margin: 0 auto;
            height: 350px;
            /*height: 150px; */
            display: flex;
            /*align-items: center;*/
            justify-content: space-between;
        }

        header > h1 {
            margin-left: 20px;
            font-size: 40px;
        }

        header > nav {
            display: flex;
            /*align-items: center;*/
            /*justify-content: center;*/
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

        header ul > li {
            font-size: 18px;
            height: 100%;
            display: flex;
            /*align-items: center;*/
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

        i {
            color: #05507d;
            font-size: 20px;
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
        function updateKeyAndCopyText(serverIp, deptId) {
            let xhr = new XMLHttpRequest();
            xhr.open('GET', '/department/updateKey?deptId=' + deptId, true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.status === 200) {
                        let dk = xhr.responseText;
                        copyText(serverIp, dk);
                    } else {
                        console.error('초대 코드를 업데이트하는 중에 오류가 발생했습니다.');
                        alert('초대 코드를 업데이트하는 중에 오류가 발생했습니다.');
                    }
                }
            };
            xhr.send();
        }

        function copyText(serverIp, deptKey) {
            const copyText = serverIp + '/signup?deptKey=' + deptKey;

            const textArea = document.createElement('textarea');
            textArea.value = copyText;
            textArea.setAttribute('readonly', '');
            textArea.style.position = 'absolute';
            textArea.style.left = '-9999px';

            document.body.appendChild(textArea);
            textArea.select();
            document.execCommand('copy');
            document.body.removeChild(textArea);

            alert('초대 코드가 성공적으로 복사되었습니다.');
        }
    </script>
</head>
<body>
<%
    User user = (User) session.getAttribute("loginUser");
    String inviteCode = UUID.randomUUID().toString().replace("-", "");
    String inviteUrl = request.getRequestURL().toString() + "?code=" + inviteCode;
%>
<div class="header-page">
    <header>
        <a href="/postlist"><h1>PoCruit</h1></a>
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
                <li><a href="/user/manage">직원 관리</a></li>
                <li>
                    <a onclick="updateKeyAndCopyText(''+/\/\/(.*?):(\d+)/.exec(window.location.href)[1]+':'+/\/\/(.*?):(\d+)/.exec(window.location.href)[2], '<%= user.getDepartmentId() %>')">
                        초대 코드 생성
                    </a></li>
            </ul>
        </nav>
        <nav>
            <ul class="user">
                <% }
                    if (user != null) {
                %>
                <i class="fa-solid"></i>
                <li><%= user.getRole().getDescription() + " " + user.getUsername()%>님 환영합니다.</li>
                <li><a href="/logout" class="logout">로그아웃</a></li>
                <%
                } else {
                %>
                <li><a href="/login">로그인</a></li>
                <%
                    }
                %>
            </ul>
        </nav>
    </header>
</div>
</body>
</html>