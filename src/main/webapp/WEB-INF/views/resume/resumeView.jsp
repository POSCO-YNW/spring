<%@ page import="pack01.domain.NeedItem" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>지원서 작성</title>
    <style>
        body {
            margin: 0
        }

        .body {
            max-width: 1080px;
            width: 60%;
            margin: 0 auto 70px auto;
        }

        form {
            width: 600px;
            background-color: rgba(255, 255, 255, 0.7);
            padding: 20px;
            margin: auto;
            border-radius: 8px;
        }

        div {
            width: 600px;
            margin-bottom: 20px;
        }

        h1, h4 {
            text-align: center;
        }

        h2 {
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 5px;
            margin-bottom: 10px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        textarea {
            width: 100%;
            height: 100px;
            padding: 5px;
            margin-bottom: 10px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        input[type="submit"],
        button {
            width: 60px;
            padding: 10px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"], .button-container a {
            background-color: #05507d;
            color: white;
            margin-top: 12px;
        }

        button {
            background-color: #008CBA;
            color: white;
        }

        .button-container {
            display: flex;
            /*justify-content: space-between;*/
            gap: 20px;
            align-items: center;
        }


        .add-button,
        .remove-button {
            font-size: 14px;
            padding: 6px 10px;
        }

        .submit-button {
            background-color: #05507d;
            color: white;
        }
    </style>
</head>

<script>
    let certifications = 0;
    let skills = 0;
    let experiences = 0;
    const maxFields = 5;

    function addField(fieldType) {
        if (fieldType === 'certification' && certifications < maxFields) {
            let container = document.getElementById('certifications-container');
            let newField = document.createElement('div');
            newField.innerHTML = `
                <label for="certification_${certifications}">자격증</label>
                <input type="text" id="certification_${certifications}" name="certification[]" required>

                <label for="cf_level_${certifications}">점수</label>
                <input type="text" id="cf_level_${certifications}" name="cf_level[]" required>

                <label for="cf_date_${certifications}">취득일</label>
                <input type="date" id="cf_date_${certifications}" name="cf_date[]" required>

                <button class="remove-button" type="button" onclick="removeField(this.parentNode, 'certification')" style="width: 30px; height: 30px; flex-shrink: 0; margin-left: auto;">-</button>
            `;
            container.appendChild(newField);
            certifications++;
        } else if (fieldType === 'skill' && skills < maxFields) {
            let container = document.getElementById('skills-container');
            let newField = document.createElement('div');
            newField.innerHTML = `
                <label for="skill_stack_${skills}">기술 스택(언어)</label>
                <input type="text" id="skill_stack_${skills}" name="skill_stack[]" required>

                <label for="skill_level_${skills}">기술 수준</label>
                <select id="skill_level_${skills}" name="skill_level[]" required>
                    <option value="HIGH">상</option>
                    <option value="MIDDLE">중</option>
                    <option value="LOW">하</option>
                </select>
                <br><br>

                <button class="remove-button" type="button" onclick="removeField(this.parentNode, 'skill')" style="width: 30px; height: 30px; flex-shrink: 0; margin-left: auto;">-</button>
            `;
            container.appendChild(newField);
            skills++;
        } else if (fieldType === 'experience' && experiences < maxFields) {
            let container = document.getElementById('experiences-container');
            let newField = document.createElement('div');
            newField.innerHTML = `
                <label for="ex_company_${experiences}">회사 이름</label>
                <input type="text" id="ex_company_${experiences}" name="ex_company[]" required>

                <label for="ex_period_${experiences}">복무 개월</label>
                <input type="number" id="ex_period_${experiences}" name="ex_period[]" required>

                <label for="ex_work_${experiences}">업무</label>
                <input type="text" id="ex_work_${experiences}" name="ex_work[]" required>

                <button class="remove-button" type="button" onclick="removeField(this.parentNode, 'experience')" style="width: 30px; height: 30px; flex-shrink: 0; margin-left: auto;">-</button>
            `;
            container.appendChild(newField);
            experiences++;
        }
    }

    function removeField(field, fieldType) {
        field.parentNode.removeChild(field);
        if (fieldType === 'certification') {
            certifications--;
        } else if (fieldType === 'skill') {
            skills--;
        } else if (fieldType === 'experience') {
            experiences--;
        }
    }
</script>
<jsp:include page="../../../header.jsp"/>
<body>
<div class="body">
    <h1>[${department.getName()}]</h1>
    <h2>${post.getTitle()}</h2>

    <form action="/resume/post?postId=${post.getPostId()}&departmentId=${department.getDepartmentId()}" method="POST">
        <div>
            <h1>한 줄 소개</h1>
            <label for="desc"></label>
            <input type="text" id="desc" name="desc" required>
        </div>

        <hr>

        <div>
            <h1>지원자 정보</h1>
            <label for="username">사용자 이름</label>
            <input type="text" id="username" name="username" value="${userInfo.getUsername()}" readonly required>

            <label for="email">이메일</label>
            <input type="email" id="email" name="email" value="${userInfo.getEmail()}" readonly required>

            <label for="birthday">생년월일</label>
            <input type="text" id="birthday" name="birthday" value="${userInfo.getBirthday()}" readonly required>

            <label for="phoneNumber">전화번호</label>
            <input type="text" id="phoneNumber" name="phoneNumber" value="${userInfo.getPhoneNumber()}" readonly
                   required>

            <label for="address">주소</label>
            <input type="text" id="address" name="address" value="${userInfo.getAddress()}" readonly required>
        </div>

        <hr>
        <div>
            <h1>최종 학력</h1>
            <div class="form-group">
                <label for="educationType">학력</label>
                <select id="educationType" name="educationType" required>
                    <option value="">선택하세요</option>
                    <option value="HIGHSCHOOL">고등학교</option>
                    <option value="UNIVERSITY">대학교</option>
                    <option value="GRADUATESCHOOL">대학원</option>
                </select>
            </div>
            <label for="school">학교명</label>
            <input type="text" id="school" name="school" required>

            <label for="grade">학년</label>
            <input type="number" id="grade" name="grade" required>
        </div>
        <hr>

        <div>
            <h1>추가 정보</h1>
            <div style="display: flex; align-items: center;">
                <h2 style="margin-right: 10px;">자격증</h2>
                <button class="add-button" type="button" onclick="addField('certification')"
                        style="width: 30px; height: 30px; flex-shrink: 0; margin-left: auto;">+
                </button>
            </div>
            <div id="certifications-container"></div>

            <hr>

            <div style="display: flex; align-items: center;">
                <h2 style="margin-right: 10px;">기술</h2>
                <button class="add-button" type="button" onclick="addField('skill')"
                        style="width: 30px; height: 30px; flex-shrink: 0; margin-left: auto;">+
                </button>
            </div>

            <div id="skills-container"></div>

            <hr>

            <div style="display: flex; align-items: center;">
                <h2 style="margin-right: 10px;">회사 경력</h2>
                <button class="add-button" type="button" onclick="addField('experience')"
                        style="width: 30px; height: 30px; flex-shrink: 0; margin-left: auto;">+
                </button>
            </div>

            <div id="experiences-container"></div>

            <hr>
            <%
                List<NeedItem> needItems = (List<NeedItem>) request.getAttribute("needItems");
                for (int i = 0; i < needItems.size(); i++) {
            %>
            <h2>[질문 <%= i + 1 %>] <%= needItems.get(i).getTitle() %>
            </h2>
            <input type="hidden" id="title[<%=i%>]" name="title[]" value="<%= needItems.get(i).getNeedItemId() %>">
            <label for="description[<%=i%>]"></label><textarea id="description[<%=i%>]" name="description[]"
                                                               required></textarea><br>
            <%
                }
            %>

            <div class="button-container">
                <button type="button" class="submit-button" onclick="location.href='/postlist'">취소</button>
                <input type="submit" class="submit-button" value="제출">
            </div>
        </div>
    </form>
</div>
<jsp:include page="../../../footer.jsp"/>
</body>
</html>

