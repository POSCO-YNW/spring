<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
<h1>User List</h1>
<table>
    <thead>
    <tr>
        <th>User ID</th>
        <th>Username</th>
        <!-- Add more columns as needed -->
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.userId}</td>
            <td>${user.username}</td>
            <!-- Add more columns as needed -->
            <td>
                <a href="/users/${user.userId}" class="btn btn-primary">Details</a>
                <a href="/users/${user.userId}/edit" class="btn btn-secondary">Edit</a>
                <a href="/users/${user.userId}/delete" class="btn btn-danger">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/users/new" class="btn btn-primary">Create User</a>
</body>
</html>