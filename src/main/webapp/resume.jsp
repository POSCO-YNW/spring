<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2023-06-15
  Time: 오전 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>지원서 작성</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 50px;
        }

        div {
            width: 600px;
            margin-bottom: 20px;
        }

        h1, h4 {
            text-align: center;
        }

        h2 {
            text-align: left;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input, textarea {
            width: 100%;
            padding: 5px;
            margin-bottom: 10px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }

        input[type="submit"],
        button {
            width: 120px;
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover,
        button:hover {
            background-color: #45a049;
        }

        .button-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

    </style>
</head>
<body>
<h1>지원서 작성하기</h1>
<h4>[포스코DX 채용] 회사 부서[${departmentId}]</h4>

<div>
    <h1>지원자 정보</h1>
    <label for="username">사용자 이름:</label>
    <input type="text" id="username" name="username" value="<%=user.getUsername%>" required>

    <label for="email">이메일:</label>
    <input type="email" id="email" name="email" required>

    <label for="birthday">생년월일:</label>
    <input type="text" id="birthday" name="birthday" required>

    <label for="address">주소:</label>
    <input type="text" id="address" name="address" required>
</div>

<div>
    <h1>추가 정보</h1>
    <form action="ResumeController" method="POST">
        <div>
            <h2>자격증</h2>
            <label for="certification">자격증:</label>
            <input type="text" id="certification" name="certification" required>

            <label for="cf_level">점수:</label>
            <input type="text" id="cf_level" name="cf_level" required>

            <label for="cf_date">취득일:</label>
            <input type="text" id="cf_date" name="cf_date" required>
        </div>

        <div>
            <h2>기술</h2>
            <label for="skill_stack">기술 스택(언어):</label>
            <input type="text" id="skill_stack" name="skill_stack" required>

            <label for="skill_level">기술 점수(상/중/하):</label>
            <input type="text" id="skill_level" name="skill_level" required>
        </div>

        <div>
            <h2>포트폴리오</h2>
            <label for="social_type">소셜 제목:</label>
            <input type="email" id="social_type" name="social_type" required>

            <label for="link">링크:</label>
            <input type="email" id="link" name="link" required>
        </div>

        <div>
            <h2>최종 학력</h2>
            <label for="edu_type">학력:</label>
            <input type="text" id="edu_type" name="edu_type" required>

            <label for="edu_school">학교:</label>
            <input type="text" id="edu_school" name="edu_school" required>

            <label for="edu_grade">학년:</label>
            <input type="text" id="edu_grade" name="edu_grade" required>
        </div>

        <div>
            <h2>회사 경력</h2>
            <label for="ex_company">회사 이름:</label>
            <input type="text" id="ex_company" name="ex_company" required>

            <label for="ex_period">복무 개월:</label>
            <input type="text" id="ex_period" name="ex_period" required>

            <label for="ex_work">업무:</label>
            <input type="text" id="ex_work" name="ex_work" required>
        </div>
        <h2>${title}</h2>
        <label for="description">설명:</label>
        <textarea id="description" name="description" rows="10" cols="70" style="resize: none;" required></textarea>

        <div class="button-container">
            <input type="submit" value="지원하기">
            <button onclick="location.href='index.jsp'">뒤로 가기</button>
        </div>
    </form>
</div>

</body>
</html>