<%@ page import="pack01.domain.type.RoleType" %>
<%@ page import="pack01.domain.type.SocialType" %>
<%@ page import="java.util.List" %>
<%@ page import="pack01.domain.Department" %>
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
            overflow-y: hidden;
        }

        .container {
            width: 100%;
            height: 100vh;
            overflow-x: hidden;
            overflow-y: auto;
        }

        .signup-form {
            max-width: 400px;
            margin: auto;
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
        }

        .form-group {
            margin: auto;
        }

        .signup-form h2 {
            text-align: center;
        }

        .signup-form .form-group {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            margin-bottom: 15px;
        }

        .signup-form label {
            display: block;
            text-align: left;
            margin-bottom: 5px;
        }

        .signup-form input[type="text"],
        .signup-form input[type="email"],
        .signup-form input[type="password"],
        .signup-form input[type="date"] {
            width: 60%;
            margin: 0 auto;
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

        .signup-form button, .login-link a {
            width: 70px;
            margin: auto;
            text-align: center;
            padding: 10px;
            background-color: #05507d;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
        }

        .signup-form button:hover, .login-link a:hover {
            background-color: #1288d9;
            transition: 0.3s ease-in;
        }

        .error-message {
            color: red;
            margin-top: 10px;
        }

        .login-link a {
            margin-top: 10px;
            text-align: center;
            text-decoration: none;
        }

        .btn-container{
            display: flex;
            flex-direction: column;
            justify-items: center;
            align-items: center;
            margin: 20px auto;
        }
    </style>
</head>
<%
    Department department = (Department) request.getAttribute("department");
    if (department == null)
        department = new Department();
%>
<body>
<div class="container">
    <form action="/signup" method="post" class="signup-form">
        <h2>회원가입</h2>
        <div class="form-group">
            <label for="username">이름</label>
            <input type="text" id="username" name="username" required/>
        </div>
        <div class="form-group">
            <label for="email">이메일</label>
            <input type="email" id="email" name="email" required/>
        </div>
        <div class="form-group">
            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" required/>
        </div>

        <div class="form-group">
            <label for="birthday">생년월일</label>
            <input type="date" id="birthday" name="birthday" required/>
        </div>

        <div class="form-group">
            <label for="phoneNumber">전화번호</label>
            <input type="text" id="phoneNumber" name="phoneNumber" required/>
        </div>

        <div class="form-group">
            <label for="address">주소</label>
            <input type="text" id="address" name="address" required/>
        </div>

        <%
            if (department.getName() != null && !department.getName().equals("무소속")) {
        %>
        <input type="hidden" id="role" name="role" value="<%= RoleType.EMPLOYEE %>">
        <input type="hidden" id="deptId" name="deptId" value=<%=department.getDepartmentId()%>>
        <%
        } else {
        %>
        <input type="hidden" id="role" name="role" value="<%= RoleType.APPLICANT %>">
        <input type="hidden" id="deptId" name="deptId" value="<%= -1 %>">
        <%
            }
        %>

        <hr/>

        <%
            SocialType[] socialTypes = SocialType.values();
            for (int i = 0; i < socialTypes.length; i++) {
        %>
        <div class="form-group">
            <label for="sosical[<%=i%>]"><%=socialTypes[i] + " ID "%>
            </label>
            <input type="text" id="sosical[<%=i%>]" name="social[]" placeholder="없을 시 공백"/>
        </div>
        <%
            }
        %>

        <hr/>

        <div class="btn-container">
            <div class="form-group">
                <button type="submit">가입</button>
            </div>
            <p class="error-message">${error}</p>
            <div class="login-link">
                <a href="/login">로그인</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>