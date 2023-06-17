<%@ page import="java.util.List" %>
<%@ page import="pack01.dto.needitem.response.NeedItemResumeItem" %>
<%@ page import="pack01.domain.*" %>
<%@ page import="pack01.dto.resume.response.ResumeUserResponse" %>
<%@ page import="pack01.domain.type.SocialType" %>
<%@ page import="pack01.domain.type.RoleType" %>
<%@ page import="pack01.domain.type.ResumeStatusType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>지원서 상세</title>
    <style>
        .body {
            max-width: 1080px;
            width: 60%;
            margin: 0 auto 70px auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        ul {
            padding-left: 20px;
        }

        .back-button {
            display: inline-block;
            width: 120px;
            padding: 5px;
            background-color: dimgrey;
            border: none;
            border-radius: 5px;
            color: #fff;
            font-size: 16px;
            font-weight: bold;
            text-align: center;
            text-decoration: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .back-button:hover {
            background-color: darkslategrey;
        }

        .pass-button {
            display: inline-block;
            width: 120px;
            padding: 5px;
            background-color: dodgerblue;
            border: none;
            border-radius: 5px;
            color: #fff;
            font-size: 16px;
            font-weight: bold;
            text-align: center;
            text-decoration: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .pass-button:hover {
            background-color: blue;
        }

        .fail-button {
            display: inline-block;
            width: 120px;
            padding: 5px;
            background-color: orangered;
            border: none;
            border-radius: 5px;
            color: #fff;
            font-size: 16px;
            font-weight: bold;
            text-align: center;
            text-decoration: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .fail-button:hover {
            background-color: red;
        }

        .button-container {
            display: flex;
            justify-content: center;
            gap: 50px;
            margin-top: 20px;
        }

        .back-button,
        .pass-button,
        .fail-button {
            padding: 10px 20px;
            font-size: 16px;
        }
    </style>
</head>
<jsp:include page="../../../header.jsp"/>
<body>
<%
    ResumeUserResponse resume = (ResumeUserResponse) request.getAttribute("resume");
    List<SocialAccount> socials = (List<SocialAccount>) request.getAttribute("socials");
    List<NeedItemResumeItem> needItems = (List<NeedItemResumeItem>) request.getAttribute("needItems");
    List<Experience> experiences = (List<Experience>) request.getAttribute("experiences");
    List<Certification> certifications = (List<Certification>) request.getAttribute("certifications");
    List<Skill> skills = (List<Skill>) request.getAttribute("skills");
    List<Education> educations = (List<Education>) request.getAttribute("educations");
    List<Vote> votes = (List<Vote>) request.getAttribute("votes");

    User user = (User) session.getAttribute("loginUser");

    boolean voted = false;

    for (Vote vote : votes) {
        if (vote.getUserId().equals(user.getUserId())) {
            voted = true;
            break;
        }
    }
%>

<div class="body">

    <h1>지원서 상세</h1>
    <hr>

    <h2>[<%=resume.getDescription()%>]
    </h2>
    <br>

    <h2>기본 정보</h2>
    <table>
        <tr>
            <th>이름</th>
            <td><%= resume.getUsername() %>
            </td>
        </tr>
        <tr>
            <th>생년월일</th>
            <td><%= resume.getBirthday() %>
            </td>
        </tr>
        <tr>
            <th>이메일</th>
            <td><%= resume.getEmail() %>
            </td>
        </tr>
        <tr>
            <th>전화번호</th>
            <td><%= resume.getPhoneNumber() %>
            </td>
        </tr>
        <tr>
            <th>주소</th>
            <td><%= resume.getAddress() %>
            </td>
        </tr>
    </table>

    <h2>자기소개서</h2>
    <% for (NeedItemResumeItem item : needItems) { %>
    <%= item.getTitle() %>:<br>
    <label>
        <textarea readonly cols="80" rows="10"><%= item.getDescription() %></textarea>
        <br> <br>
    </label>
    <% } %>

    <br>
    <hr>
    <br>

    <h2>소셜 계정</h2>
    <% for (SocialAccount social : socials) { %>
    <h3><%= social.getType().getDescription() %>: </h3>
    <%
        String url = "";
        switch (social.getType()) {
            case GITHUB:
                url = "https://github.com/" + social.getAccountId();
                break;
            case BOJ:
                url = "https://www.acmicpc.net/user/" + social.getAccountId();
                break;
            case TISTORY:
                url = "https://" + social.getAccountId() + ".tistory.com/";
        }
    %>
    <a style="color: blue" href="<%=url%>"><%= social.getAccountId() %>(바로가기)
    </a>
    <%
        if (SocialType.GITHUB.equals(social.getType())) {
    %>
    <div>
        <img src="https://github-readme-stats.vercel.app/api/top-langs/?username=<%=social.getAccountId()%>&layout=compact&theme=tokyonight"
             alt="Top Langs">
        <img src="https://github-readme-stats.vercel.app/api?username=<%=social.getAccountId()%>&theme=vue&show_icons=true"
             alt="stats">
    </div>
    <%
    } else if (SocialType.BOJ.equals(social.getType())) {
    %>
    <div>
        <a href="https://solved.ac/jh0902">
            <img src="http://mazassumnida.wtf/api/generate_badge?boj=<%=social.getAccountId()%>"
                 alt="Solved.ac Profile">
        </a>
    </div>
    <%
            }
        }
    %>

    <br>
    <hr>
    <br>

    <h2>경력</h2>
    <table>
        <tr>
            <th>회사</th>
            <th>기간</th>
            <th>담당 업무</th>
        </tr>
        <% for (Experience experience : experiences) { %>
        <tr>
            <td><%= experience.getCompany() %>
            </td>
            <td><%= experience.getPeriod() %>
            </td>
            <td><%= experience.getWork() %>
            </td>
        </tr>
        <% } %>
    </table>

    <br>
    <hr>
    <br>

    <h2>자격증</h2>
    <table>
        <tr>
            <th>이름</th>
            <th>점수</th>
            <th>취득일</th>
        </tr>
        <% for (Certification certification : certifications) { %>
        <tr>
            <td><%= certification.getName() %>
            </td>
            <td><%= certification.getLevel() %>
            </td>
            <td><%= certification.getDate() %>
            </td>
        </tr>
        <% } %>
    </table>

    <br>
    <hr>
    <br>

    <h2>기술 스택</h2>
    <table>
        <tr>
            <th>이름</th>
            <th>수준</th>
        </tr>
        <% for (Skill skill : skills) { %>
        <tr>
            <td><%= skill.getStack() %>
            </td>
            <td><%= skill.getLevel().getDescription() %>
            </td>
        </tr>
        <% } %>
    </table>

    <br>
    <hr>
    <br>

    <h2>최종 학력</h2>
    <table>
        <tr>
            <th>유형</th>
            <th>학교</th>
            <th>학점</th>
        </tr>
        <% for (Education education : educations) { %>
        <tr>
            <td><%= education.getType().getDescription() %>
            </td>
            <td><%= education.getSchool() %>
            </td>
            <td><%= education.getGrade() %>
            </td>
        </tr>
        <% } %>
    </table>

    <br>
    <hr>
    <br>

    <div class="button-container">
        <form method="post" action="javascript:history.back()">
            <input type="submit" value="뒤로가기" class="back-button">
        </form>

        <%
            if (user.getRole().equals(RoleType.ADMIN) && (resume.getStatus().equals(ResumeStatusType.UNREAD) || resume.getStatus().equals(ResumeStatusType.READ))) {
        %>
        <form method="post" action="/resume/pass">
            <label>
                <input type="text" name="resumeId" hidden="hidden" value="<%=resume.getResumeId()%>">
            </label>
            <label>
                <input type="text" name="postId" hidden="hidden" value="<%=resume.getPostId()%>">
            </label>
            <input type="submit" value="합격" class="pass-button">
        </form>

        <form method="post" action="/resume/fail">
            <label>
                <input type="text" name="resumeId" hidden="hidden" value="<%=resume.getResumeId()%>">
            </label>
            <label>
                <input type="text" name="postId" hidden="hidden" value="<%=resume.getPostId()%>">
            </label>
            <input type="submit" value="불합격" class="fail-button">
        </form>
        <%
        } else if (user.getRole().equals(RoleType.EMPLOYEE) && !voted && (resume.getStatus().equals(ResumeStatusType.UNREAD) || resume.getStatus().equals(ResumeStatusType.READ))) {
        %>
        <form method="post" action="/resume/agree">
            <label>
                <input type="text" name="resumeId" hidden="hidden" value="<%=resume.getResumeId()%>">
            </label>
            <label>
                <input type="text" name="postId" hidden="hidden" value="<%=resume.getPostId()%>">
            </label>
            <input type="submit" value="찬성" class="pass-button">
        </form>

        <form method="post" action="/resume/disagree">
            <label>
                <input type="text" name="resumeId" hidden="hidden" value="<%=resume.getResumeId()%>">
            </label>
            <label>
                <input type="text" name="postId" hidden="hidden" value="<%=resume.getPostId()%>">
            </label>
            <input type="submit" value="반대" class="fail-button">
        </form>
        <%
            }
        %>
    </div>
</div>
</body>
</html>