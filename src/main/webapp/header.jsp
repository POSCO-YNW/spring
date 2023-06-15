<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
  <title>header</title>
  <style>
    ul {
      list-style: none;
    }

    a {
      text-decoration: none;
      outline: none;
      color: black;
    }

    a:hover {
      transition: 0.3s ease-in;
      color: #0033ff;
      cursor: pointer;
    }

    .logout {
      font-family: "KIMM_Bold", sans-serif;
    }

    .logout:hover {
      color: red;
    }

    .page {
      max-width: 1440px;
      width: 80%;
      margin: 0 auto 70px auto;
      padding: 0 20px;
      border-bottom: 2px solid black;
    }

    header {
      width: 100%;
      height: 80px;
      display: flex;
      align-items: center;
      justify-content: space-between;
    }

    header > h2 {
      margin-left: 20px;
    }

    header > nav {
      display: flex;
      align-items: center;
      justify-content: space-between;
      /*flex-grow: 0.2;*/
      /*width: fit-content;*/
    }

    header ul {
      width: 100%;
      height: 100%;
      display: flex;
      justify-content: space-between;
    }

    header ul > li {
      font-size: 16px;
      height: 100%;
      display: flex;
      align-items: center;
      padding: 10px;
      margin-right: 10px;
    }

    .copy-button {
      padding: 10px;
    }

    .copy-button:hover {
      background-color: #0033ff;
      color: white;
      border: none;
      border-radius: 10px;
      transition: 0.3s ease-in;
      cursor: pointer;
    }

    @media (max-width: 600px) {
      header > h2 {
        font-size: 24px;
      }

      header ul > li {
        font-size: 14px;
      }
    }
  </style>
</head>
<%--<script>--%>
<%--  function updateKeyAndCopyText(serverIp, orgId) {--%>
<%--    let xhr = new XMLHttpRequest();--%>
<%--    xhr.open('GET', '/user/updateKey.jsp?orgId=' + orgId, true);--%>
<%--    xhr.onreadystatechange = function () {--%>
<%--      if (xhr.readyState === 4) {--%>
<%--        if (xhr.status === 200) {--%>
<%--          let orgKey = xhr.responseText;--%>
<%--          copyText(serverIp, orgKey);--%>
<%--        } else {--%>
<%--          console.error('초대 코드를 업데이트하는 중에 오류가 발생했습니다.');--%>
<%--          alert('초대 코드를 업데이트하는 중에 오류가 발생했습니다.');--%>
<%--        }--%>
<%--      }--%>
<%--    };--%>
<%--    xhr.send();--%>
<%--  }--%>

<%--  function copyText(serverIp, orgKey) {--%>
<%--    const copyText = serverIp + '/user/signupUser.jsp?orgKey=' + orgKey;--%>

<%--    const textArea = document.createElement('textarea');--%>
<%--    textArea.value = copyText;--%>
<%--    textArea.setAttribute('readonly', '');--%>
<%--    textArea.style.position = 'absolute';--%>
<%--    textArea.style.left = '-9999px';--%>

<%--    document.body.appendChild(textArea);--%>
<%--    textArea.select();--%>
<%--    document.execCommand('copy');--%>
<%--    document.body.removeChild(textArea);--%>

<%--    alert('초대 코드가 성공적으로 복사되었습니다.');--%>
<%--  }--%>

<%--  // function copyText(serverIp, orgKey) {--%>
<%--  //     window.navigator.clipboard.writeText(serverIp + '/user/signupUser.jsp?orgKey=' + orgKey).then(() => {--%>
<%--  //         alert("초대 코드가 성공적으로 복사되었습니다");--%>
<%--  //     });--%>
<%--  // }--%>
<%--</script>--%>
<body>
<%--<%--%>
<%--  request.setCharacterEncoding("utf-8");--%>
<%--  Status status1 = (Status) session.getAttribute("status");--%>
<%--  Role role1 = (Role) session.getAttribute("role");--%>
<%--  Long orgId1 = (Long) session.getAttribute("orgId");--%>

<%--  if (Status.ACCEPT.equals(status1)) {--%>

<%--%>--%>
<div class="page">
  <header>
    <a href="index.jsp"><h2>PoCruit</h2></a>
    <nav>
      <ul>
        <li><a href="/">채용공고</a></li>
        <li><a href="/" class="logout">나의 지원서</a></li>
        <li><a href="/" class="logout">로그인</a></li>
      </ul>
    </nav>
  </header>
</div>
</body>
</html>
