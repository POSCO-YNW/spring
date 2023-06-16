<%@ page import="pack01.domain.Post" %>
<%@ page import="pack01.dto.post.response.PostDepartmentResponse" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>글 카드 하나</title>
    <style>
        .card {
            border: 1px solid #ccc;
            border-radius: 10px;
            padding: 25px 15px;
            margin-bottom: 10px;
            height: 200px;
        }

        .card-title {
            font-weight: bold;
            font-size: 20px;
            color: black;
        }

        .department {
            margin-top: 10px;
            font-size: 16px;
            color: #888888;
        }

        .department-location {
            font-size: 12px;
            color: #888888;
        }

        .card-footer {
            margin-top: 10px;
            font-size: 12px;
            color: #888;
        }
    </style>
</head>
<body>
<%
    PostDepartmentResponse post = (PostDepartmentResponse) request.getAttribute("post");
    Long postId = post.getPostId();
%>

<a href="/postlist/post?id=<%=postId%>">
    <div class="card">
        <div class="department"><%= post.getName() %> 부서</div>
        <br>
        <div class="card-title"><%= post.getTitle() %>
            <br>
            <p class="department-location">근무지: <%=post.getLocation()%>
            </p>
            <hr/>
        </div>
        <div class="card-footer">
            <%
                java.time.LocalDate currentDate = java.time.LocalDate.now();
                java.time.LocalDate endDate = post.getEndDate().toLocalDate();
                long daysRemaining = java.time.temporal.ChronoUnit.DAYS.between(currentDate, endDate);

                if (daysRemaining > 0) {
            %>
            <h3 style="color: blue">D-<%=daysRemaining%>
            </h3>
            <%
            } else {
            %>
            <h3 style="color: red">채용마감</h3>
            <%
                }
            %>
            <br>
            <span class="<%= (daysRemaining <= 0) ? "expired" : "" %>">
                공고마감: <%= post.getEndDate() %> |
                작성일: <%= post.getStartDate() %>
            </span>
        </div>
    </div>
</a>
</body>
</html>
