<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
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

        input[type="submit"],
        button {
            width: 100px;
            padding: 10px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
        }

        button {
            background-color: #008CBA;
            color: white;
        }

        .button-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .add-button,
        .remove-button {
            font-size: 14px;
            padding: 6px 10px;
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
                <h2>자격증</h2>
                <label for="certification_${certifications}">자격증:</label>
                <input type="text" id="certification_${certifications}" name="certification[]" required>

                <label for="cf_level_${certifications}">점수:</label>
                <input type="text" id="cf_level_${certifications}" name="cf_level[]" required>

                <label for="cf_date_${certifications}">취득일:</label>
                <input type="date" id="cf_date_${certifications}" name="cf_date[]" required>

                <button class="remove-button" type="button" onclick="removeField(this.parentNode, 'certification')">-</button>
            `;
            container.appendChild(newField);
            certifications++;
        } else if (fieldType === 'skill' && skills < maxFields) {
            let container = document.getElementById('skills-container');
            let newField = document.createElement('div');
            newField.innerHTML = `
                <h2>기술</h2>
                <label for="skill_stack_${skills}">기술 스택(언어):</label>
                <input type="text" id="skill_stack_${skills}" name="skill_stack[]" required>

                <label for="skill_level_${skills}">기술 점수(상/중/하):</label>
                <select id="skill_level_${skills}" name="skill_level[]" required>
                    <option value="상">상</option>
                    <option value="중">중</option>
                    <option value="하">하</option>
                </select>

                <button class="remove-button" type="button" onclick="removeField(this.parentNode, 'skill')">-</button>
            `;
            container.appendChild(newField);
            skills++;
        } else if (fieldType === 'experience' && experiences < maxFields) {
            let container = document.getElementById('experiences-container');
            let newField = document.createElement('div');
            newField.innerHTML = `
                <h2>회사 경력</h2>
                <label for="ex_company_${experiences}">회사 이름:</label>
                <input type="text" id="ex_company_${experiences}" name="ex_company[]" required>

                <label for="ex_period_${experiences}">복무 개월:</label>
                <input type="number" id="ex_period_${experiences}" name="ex_period[]" required>

                <label for="ex_work_${experiences}">업무:</label>
                <input type="text" id="ex_work_${experiences}" name="ex_work[]" required>

                <button class="remove-button" type="button" onclick="removeField(this.parentNode, 'experience')">-</button>
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

<body>
<h1>[${department.getName()}]</h1>
<h3>${post.getTitle()}</h3>

<div>
    <h1>지원자 정보</h1>
    <label for="username">사용자 이름:</label>
    <input type="text" id="username" name="username" value="${userInfo.getUsername()}" readonly required>

    <label for="email">이메일:</label>
    <input type="email" id="email" name="email" value="${userInfo.getEmail()}" readonly required>

    <label for="birthday">생년월일:</label>
    <input type="text" id="birthday" name="birthday" value="${userInfo.getBirthday()}" readonly required>

    <label for="address">주소:</label>
    <input type="text" id="address" name="address" value="${userInfo.getAddress()}" readonly required>
</div>

<div>
    <h1>추가 정보</h1>
    <form action="/resume/post?postId=${post.getPostId()}&departmentId=${department.getDepartmentId()}" method="POST">
        <div id="certifications-container">
            <div>
                <h2>자격증</h2>
                <label for="certification_0">자격증:</label>
                <input type="text" id="certification_0" name="certification[]" required>

                <label for="cf_level_0">점수:</label>
                <input type="text" id="cf_level_0" name="cf_level[]" required>

                <label for="cf_date_0">취득일:</label>
                <input type="date" id="cf_date_0" name="cf_date[]" required>

                <button class="remove-button" type="button" onclick="removeField(this.parentNode, 'certification')">-
                </button>
            </div>
        </div>
        <button class="add-button" type="button" onclick="addField('certification')">+ 자격증 추가</button>

        <div id="skills-container">
            <div>
                <h2>기술</h2>
                <label for="skill_stack_0">기술 스택(언어):</label>
                <input type="text" id="skill_stack_0" name="skill_stack[]" required>

                <label for="skill_level_0">기술 점수(상/중/하):</label>
                <select id="skill_level_0" name="skill_level[]" required>
                    <option value="상">상</option>
                    <option value="중">중</option>
                    <option value="하">하</option>
                </select>

                <button class="remove-button" type="button" onclick="removeField(this.parentNode, 'skill')">-</button>
            </div>
        </div>
        <button class="add-button" type="button" onclick="addField('skill')">+ 기술 추가</button>

        <div id="experiences-container">
            <div>
                <h2>회사 경력</h2>
                <label for="ex_company_0">회사 이름:</label>
                <input type="text" id="ex_company_0" name="ex_company[]" required>

                <label for="ex_period_0">복무 개월:</label>
                <input type="number" id="ex_period_0" name="ex_period[]" required>

                <label for="ex_work_0">업무:</label>
                <input type="text" id="ex_work_0" name="ex_work[]" required>

                <button class="remove-button" type="button" onclick="removeField(this.parentNode, 'experience')">-
                </button>
            </div>
        </div>
        <button class="add-button" type="button" onclick="addField('experience')">+ 회사 경력 추가</button>

        <h2>${title}</h2>
        <label for="description">설명:</label>
        <textarea id="description" name="description" required></textarea><br>

        <div class="button-container">
            <input type="submit" value="제출">
            <a href="/resume">취소</a>
        </div>
    </form>
</div>
</body>
</html>