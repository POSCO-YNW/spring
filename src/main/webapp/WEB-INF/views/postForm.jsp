<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>글 작성 폼</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 20px;
    }

    form {
      max-width: 500px;
      margin: 0 auto;
    }

    .form-group {
      margin-bottom: 20px;
    }

    label {
      font-weight: bold;
    }

    input[type="text"],
    input[type="date"],
    textarea {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
      font-size: 14px;
    }

    button {
      background-color: #4CAF50;
      color: white;
      padding: 10px 20px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 14px;
    }

    button:hover {
      background-color: #45a049;
    }
  </style>
</head>
<body>
<form action="/post/create" method="post">
  <div class="form-group">
    <label for="title">제목</label>
    <input type="text" id="title" name="title" placeholder="제목을 입력하세요" required>
  </div>
  <div class="form-group">
    <label for="endDate">시작일</label>
    <input type="date" id="startDate" name="startDate" required>
  </div>
  <div class="form-group">
    <label for="endDate">마감일</label>
    <input type="date" id="endDate" name="endDate" required>
  </div>
  <div class="form-group">
    <label for="description">설명</label>
    <textarea id="description" name="description" placeholder="설명을 입력하세요" required></textarea>
  </div>
  <div class="form-group">
    <button type="submit">작성하기</button>
  </div>
</form>
</body>
</html>
