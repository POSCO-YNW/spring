<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
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

        .login-form {
            width: 100%;
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
        }

        .login-form h2 {
            text-align: center;
        }

        .login-form .form-group {
            margin-bottom: 15px;
        }

        .login-form label {
            display: block;
            margin-bottom: 5px;
        }

        .login-form input[type="email"],
        .login-form input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .login-form button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .login-form button:hover {
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

        .signup-link {
            text-align: center;
            margin-top: 10px;
        }

        .signup-link a {
            color: #000;
        }
    </style>
</head>
<body>
<div class="container">
    <form action="<c:url value="/login"/>" method="post" class="login-form">
        <h2>로그인</h2>
        <div class="form-group">
            <label for="email">이메일: </label>
            <input type="email" id="email" name="email" required/>
        </div>
        <div class="form-group">
            <label for="password">비밀번호: </label>
            <input type="password" id="password" name="password" required/>
        </div>
        <div class="form-group">
            <button type="submit">로그인</button>
        </div>
        <p class="error-message">${error}</p>
        <div class="signup-link">
            <a href="<c:url value="/signup"/>">회원가입</a>
        </div>
    </form>
</div>
</body>
</html>