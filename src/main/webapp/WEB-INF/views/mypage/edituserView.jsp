<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2023-06-16
  Time: 오전 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원 정보 수정</title>
    <script>
        let originalPassword = "${userlist.password}"; // 기존 비밀번호를 변수에 저장합니다.

        function togglePasswordInput() {
            let pwInput = document.getElementById("pw");
            let btn = document.getElementById("toggleBtn");

            if (pwInput.readOnly) {
                // 입력란을 활성화 상태로 변경
                pwInput.readOnly = false;
                pwInput.style.backgroundColor = "#ffffff";
                pwInput.style.color = "#000000";
                pwInput.value = ""; // 입력란을 초기화합니다.
                btn.textContent = "비활성화";
            } else {
                // 입력란을 비활성화 상태로 변경
                pwInput.readOnly = true;
                pwInput.style.backgroundColor = "#e3e3e3";
                pwInput.style.color = "#666666";
                pwInput.value = originalPassword; // 기존 비밀번호로 설정합니다.
                btn.textContent = "비밀번호 변경";
            }
        }
    </script>
</head>
<body>
<h1>회원 정보 수정</h1>
<form action="" method="post">
    <label for="name">이름</label>
    <input type="text" id="name" name="name" value="${userlist.username}"
           style="background-color: #e3e3e3; color: #666666" required><br>

    <label for="pw">비밀번호</label>
    <input type="password" id="pw" name="pw" value="${userlist.password}"
           style="background-color: #e3e3e3; color: #666666" readonly required>
    <button type="button" id="toggleBtn" onclick="togglePasswordInput()">비밀번호 변경</button><br>

    <label for="email">이메일</label>
    <input type="email" id="email" name="email" value="${userlist.email}"
           style="background-color: #e3e3e3; color: #666666" required><br>

    <label for="phone">핸드폰</label>
    <input type="tel" id="phone" name="phone" value="${userlist.phoneNumber}"
           style="background-color: #e3e3e3; color: #666666" required><br>

    <label for="birthday">생년월일</label>
    <input type="date" id="birthday" name="birthday" value="${userlist.birthday}"
           style="background-color: #e3e3e3; color: #666666" required><br>

    <label for="address">주소</label>
    <input type="text" id="address" name="address" value="${userlist.address}"
           style="background-color: #e3e3e3; color: #666666" required><br>

    <input type="submit" value="수정 완료">
</form>
</body>
</html>
