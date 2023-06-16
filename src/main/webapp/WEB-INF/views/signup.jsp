<%@ page import="pack01.domain.type.RoleType" %>
<%@ page import="pack01.domain.type.SocialType" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
    <style>
        body {
            background-image: url('/resources/static/images/background/poscotower.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center center;
        }

        .container {
            max-width: 400px;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .signup-form {
            width: 100%;
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
        }

        .signup-form h2 {
            text-align: center;
        }

        .signup-form .form-group {
            margin-bottom: 15px;
        }

        .signup-form label {
            display: block;
            margin-bottom: 5px;
        }

        .signup-form input[type="text"],
        .signup-form input[type="email"],
        .signup-form input[type="password"],
        .signup-form input[type="date"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .signup-form select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .signup-form button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .signup-form button:hover {
            background-color: #45a049;
        }

        .error-message {
            color: red;
            margin-top: 10px;
        }

        .logo {
            width: 150px;
            height: auto;
            margin-bottom: 20px;
        }

        .login-link {
            text-align: center;
            margin-top: 10px;
        }

        .login-link a {
            color: #000;
        }
    </style>
</head>
<body>
<div class="container">
    <form action="<c:url value="/signup"/>" method="post" class="signup-form">
        <h2>회원가입</h2>
        <div class="form-group">
            <label for="username">이름:</label>
            <input type="text" id="username" name="username" required/>
        </div>
        <div class="form-group">
            <label for="email">이메일:</label>
            <input type="email" id="email" name="email" required/>
        </div>
        <div class="form-group">
            <label for="password">비밀번호:</label>
            <input type="password" id="password" name="password" required/>
        </div>

        <div class="form-group">
            <label for="birthday">생년월일:</label>
            <input type="date" id="birthday" name="birthday" required/>
        </div>

        <div class="form-group">
            <label for="phoneNumber">전화번호:</label>
            <input type="text" id="phoneNumber" name="phoneNumber" required/>
        </div>

        <div class="form-group">
            <label for="address">주소:</label>
            <input type="text" id="address" name="address" required/>
        </div>

        <input type="hidden" id="role" name="role" value=<%=RoleType.APPLICANT%>>

        <hr/>

        <%
            SocialType[] socialTypes = SocialType.values();
            for (int i = 0; i < socialTypes.length; i++) {
        %>
        <div class="form-group">
            <label for="sosical[<%=i%>]"><%=socialTypes[i] + " ID: "%>
            </label>
            <input type="text" id="sosical[<%=i%>]" name="social[]" placeholder="없을 시 공백" required/>
        </div>
        <%
            }
        %>

        <hr/>

        <div class="form-group">
            <button type="submit">가입하기</button>
        </div>
        <p class="error-message">${error}</p>
        <div class="login-link">
            <a href="<c:url value="/login"/>">로그인</a>
        </div>
    </form>
</div>
</body>
</html>