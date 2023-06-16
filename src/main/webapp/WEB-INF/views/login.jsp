<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
    <style>
        /*@font-face {*/
        /*    font-family: 'KBO-Dia-Gothic_bold';*/
        /*    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-2@1.0/KBO-Dia-Gothic_bold.woff') format('woff');*/
        /*    font-weight: 300;*/
        /*    font-style: normal;*/
        /*}*/
        body {
            background-image: url('/resources/static/images/background/poscotower.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center center;
            overflow-y: hidden;
            /*font-family: "KBO-Dia-Gothic_bold", sans-serif;*/
        }

        .container {
            width: 100%;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .login-form {
            width: 400px;
            max-width: 400px;
            margin: auto;
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
        }

        .login-form h2 {
            text-align: center;
        }

        .login-form > .form-group {
            width: 80%;
            margin: auto;
            margin-bottom: 15px;

        }

        .login-form label {
            display: block;
            margin-bottom: 5px;
        }

        .login-form input[type="email"],
        .login-form input[type="password"] {
            width: 90%;
            margin: auto;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .login-form button, .signup-link a {
            width: 60%;
            margin: auto;
            text-align: center;
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

        .login-link a{
            margin-top: 10px;
        }

        .signup-link a {
            margin-top: 10px;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">
    <form action="/login" method="post" class="login-form">
        <h2>로그인</h2>
        <div class="form-group">
            <label for="email">이메일 </label>
            <input type="email" id="email" name="email" required/>
        </div>
        <div class="form-group">
            <label for="password">비밀번호 </label>
            <input type="password" id="password" name="password" required/>
        </div>
        <div class="form-group">
            <button type="submit">로그인</button>
        </div>
        <p class="error-message">${error}</p>
        <div class="signup-link">
            <a href="/signup">회원가입</a>
        </div>
    </form>
</div>
</body>
</html>