<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>직원 관리</title>
    <style>
        body {
            /*font-family: Arial, sans-serif;*/
        }

        .container {
            width: 80%;
            margin: auto;
            margin: 100px;
        }

        .container > h1 {
            color: #333;
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        .delete-button {
            background-color: #f44336;
            border: none;
            color: white;
            padding: 8px 16px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
            margin-left: 5px;
            cursor: pointer;
        }

        .delete-button:hover {
            background-color: #d32f2f;
        }

        .empty-users-message {
            color: #888;
            font-style: italic;
        }
    </style>
</head>
<jsp:include page="../../../header.jsp"/>
<body>
<div class="container">
    <h1>직원 목록</h1>
    <table>
        <thead>
        <tr>
            <th>이름</th>
            <th>이메일</th>
            <th>전화번호</th>
            <th>부서 이름</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${empty users}">
                <tr>
                    <td colspan="5" class="empty-users-message">직원이 없습니다.</td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${user.phoneNumber}</td>
                        <td>${user.name}</td>
                        <td>
                            <button class="delete-button" onclick="confirmDelete(${user.userId})">삭제</button>
                        </td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>
<script>
    function confirmDelete(userId) {
        if (confirm("정말로 이 직원을 삭제하시겠습니까?")) {
            window.location.href = "/user/manage/delete?userId=" + userId;
        }
    }
</script>
<jsp:include page="../../../footer.jsp"/>
</body>
</html>
