<%@ page import="pack01.domain.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="pack01.domain.Post" %>
<%@ page import="pack01.domain.Department" %>
<%@ page import="pack01.dto.resume.response.ResumeUserResponse" %>
<%@ page import="pack01.domain.Vote" %>
<%@ page import="pack01.dto.resume.response.ResumeVoteResponse" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>지원자 현황</title>
    <style>
        .body {
            max-width: 1440px;
            width: 80%;
            margin: 0 auto 70px auto;
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

        .center {
            text-align: center;
        }
    </style>
</head>
<jsp:include page="../../../header.jsp"/>
<body>
<div class="body">
    <%
        List<ResumeUserResponse> resumes = (List<ResumeUserResponse>) request.getAttribute("resumes");
        Post post = (Post) request.getAttribute("post");
        Department department = (Department) request.getAttribute("department");
        List<ResumeVoteResponse> resumeVoteResponses = (List<ResumeVoteResponse>) request.getAttribute("resumeVoteResponses");
    %>
    <h1 class="center"><%= post.getTitle() %>
    </h1>
    <h5 class="center">[<%= department.getName() %>]</h5>
    <h3>지원자 현황</h3>
    <table>
        <thead>
        <tr>
            <th hidden="hidden">Resume ID</th>
            <th>제목</th>
            <th>지원자 이름</th>
            <th>상태</th>
            <th>찬/반</th>
        </tr>
        </thead>
        <tbody>
        <%-- Iterate over the list of resumes and display each item --%>
        <%
            for (ResumeUserResponse resume : resumes) {
        %>
        <tr>
            <td hidden="hidden"><%= resume.getResumeId() %>
            </td>
            <td>
                <a href="/resume/detail?postId=<%= resume.getPostId() %>&resumeId=<%= resume.getResumeId() %>&userId=<%= resume.getApplicantId() %>"
                   style="display: inline-block; padding: 8px 12px; background-color: #f2f2f2; border-radius: 4px;">
                    <%= resume.getDescription() %>
                </a>
            </td>
            <td><%= resume.getUsername() %>
            </td>
            <td><%= resume.getStatus().getDescription() %>
            </td>
            <td>
                <%
                    int agree = 0;
                    int disagree = 0;
                    for (ResumeVoteResponse resumeVoteResponse : resumeVoteResponses) {
                        if (resume.getResumeId().equals(resumeVoteResponse.getResumeId())) {
                            List<Vote> votes = resumeVoteResponse.getVotes();
                            int voteCount = votes.size();
                            for (int i = 0; i < voteCount; i++) {
                                Vote vote = votes.get(i);
                                if (vote.getVote() == 0) {
                                    disagree++;
                                } else {
                                    agree++;
                                }
                            }
                        }
                    }
                %>
                <%=agree + "/" + disagree%>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>