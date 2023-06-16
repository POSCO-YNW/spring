<%@ page import="pack01.domain.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="pack01.domain.Post" %>
<%@ page import="pack01.domain.Department" %>
<%@ page import="pack01.dto.resume.response.ResumeUserResponse" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>지원자 현황</title>
</head>
<style>
    .body {
        max-width: 1440px;
        width: 80%;
        margin: 0 auto 70px auto;
    }
</style>
<jsp:include page="../../../header.jsp"/>
<body>
<div class="body">
    <%
        List<ResumeUserResponse> resumes = (List<ResumeUserResponse>) request.getAttribute("resumes");
        Post post = (Post) request.getAttribute("post");
        Department department = (Department) request.getAttribute("department");
    %>
    <h1 style="text-align: center;"><%=post.getTitle()%>
    </h1>
    <h5 style="text-align: center;">[<%=department.getName()%>]
    </h5>
    <h3>지원자 현황</h3>
    <table>
        <thead>
        <tr>
            <th hidden="hidden">Resume ID</th>
            <th>제목</th>
            <th>지원자 이름</th>
            <th>상태</th>
        </tr>
        </thead>
        <tbody>
        <%-- Iterate over the list of resumes and display each item --%>
        <%
            for (ResumeUserResponse resume : resumes) { %>
        <tr>
            <td hidden="hidden"><%= resume.getResumeId() %>
            <td>
                <a href="/resume/detail?postId=<%=resume.getPostId()%>&resumeId=<%= resume.getDescription() %>&userId=<%=resume.getApplicantId()%>">
                    <%= resume.getDescription() %>
                </a>
            </td>
            <td><%= resume.getUsername() %>
            </td>
            <td><%= resume.getStatus() %>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>