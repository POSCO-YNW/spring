<%@ page import="pack01.domain.SocialAccount" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원 정보 수정</title>
    <style>
        body {margin: 0}

        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        form {
            display: grid;
            grid-gap: 10px;
        }

        label {
            font-weight: bold;
        }

        input[type="text"],
        input[type="password"],
        input[type="email"],
        input[type="tel"],
        input[type="date"] {
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .button-container {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .button-container button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .button-container button:first-child {
            margin-right: 10px;
        }
    </style>
</head>
<body>
<jsp:include page="../../../header.jsp"/>
<div class="container">
    <h1>회원 정보 수정</h1>
    <form action="/mypage/edituser" method="post">
        <label for="name">이름</label>
        <input type="text" id="name" name="name" value="${userlist.username}" required>

        <label for="pw">비밀번호</label>
        <input type="password" id="pw" name="pw" value="${userlist.password}" required>

        <label for="email">이메일</label>
        <input type="email" id="email" name="email" value="${userlist.email}" readonly hidden="hidden" required>

        <label for="phone">핸드폰</label>
        <input type="tel" id="phone" name="phone" value="${userlist.phoneNumber}" required>

        <label for="birthday">생년월일</label>
        <input type="date" id="birthday" name="birthday" value="${userlist.birthday}" required>

        <label for="address">주소</label>
        <input type="text" id="address" name="address" value="${userlist.address}" required>

        <hr>

        <%
            List<SocialAccount> socialAccounts = (List<SocialAccount>) request.getAttribute("socialAccounts");
            String github = "";
            String tistory = "";
            String boj = "";
            for (SocialAccount socialAccount : socialAccounts) {
                switch (socialAccount.getType()) {
                    case GITHUB:
                        github = socialAccount.getAccountId();
                        break;
                    case TISTORY:
                        tistory = socialAccount.getAccountId();
                        break;
                    case BOJ:
                        boj = socialAccount.getAccountId();
                        break;
                }
            }
        %>

        <label for="github">GITHUB ID</label>
        <input type="text" id="github" name="github" value="<%= github %>">
        <label for="tistory">TISTORY ID</label>
        <input type="text" id="tistory" name="tistory" value="<%= tistory %>">
        <label for="boj">BOJ ID</label>
        <input type="text" id="boj" name="boj" value="<%= boj %>">

        <div class="button-container">
            <button type="submit">수정 완료</button>
            <button type="button" onclick="history.back()">뒤로가기</button>
        </div>
    </form>
</div>
<jsp:include page="../../../footer.jsp"/>
</body>
</html>