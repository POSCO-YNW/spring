<%@ page import="java.util.List" %>
<%@ page import="pack01.dto.resume.response.ResumeUserResponse" %>
<%@ page import="pack01.dto.resume.response.ResumeVoteResponse" %>
<%@ page import="pack01.domain.type.ResumeStatusType" %>
<%@ page import="pack01.domain.*" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>지원자 현황</title>
    <style>
        body {
            margin: 0
        }

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
        User user = (User) session.getAttribute("loginUser");
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
            <th>심사</th>
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
                   style="display: inline-block; padding: 8px 12px; background-color: #f2f2f2; color: #05507d; border-radius: 4px;">
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
            <td>
                <%
                    if (Objects.equals(post.getAdminId(), user.getUserId())) {
                        if (resume.getStatus().equals(ResumeStatusType.UNREAD) || resume.getStatus().equals(ResumeStatusType.READ)) {
                %>
                <a href="/resume/judging?postId=<%= resume.getPostId() %>&resumeId=<%= resume.getResumeId() %>"
                   style="display: inline-block; padding: 8px 12px; background-color: #f2f2f2; color: #05507d; border-radius: 4px;">
                    심사 시작
                </a>
                <%
                } else if (resume.getStatus().equals(ResumeStatusType.JUDGING)) {
                %>
                <a style="display: inline-block; padding: 8px 12px; background-color: #f2f2f2; color: #4f4f4f; border-radius: 4px;">
                    심사 중
                </a>
                <%
                } else {
                %>
                <a style="display: inline-block; padding: 8px 12px; background-color: #f2f2f2; color: #4f4f4f; border-radius: 4px;">
                    심사 완료
                </a>
                <%
                        }
                    }
                %>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
<jsp:include page="../../../footer.jsp"/>
</body>
</html>