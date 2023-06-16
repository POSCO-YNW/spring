<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>채용공고 작성 폼</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: auto;
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

        .remove-item {
            color: red;
            cursor: pointer;
        }
    </style>
    <script>
        function removeItem(item) {
            let itemContainer = item.parentNode;
            let needItemsContainer = document.getElementById('need-items');
            needItemsContainer.removeChild(itemContainer);

            let items = document.querySelectorAll('.item');
            for (let i = 0; i < items.length; i++) {
                let item = items[i];
                let label = item.querySelector('label');
                label.textContent = '질문' + (i);
            }
        }

        function addItem() {
            let newItemIndex = document.querySelectorAll('.item').length;

            let newItemContainer = document.createElement('div');
            newItemContainer.className = 'form-group item';

            let newItemLabel = document.createElement('label');
            newItemLabel.textContent = '질문' + newItemIndex;

            let newItemInput = document.createElement('input');
            newItemInput.type = 'text';
            newItemInput.name = 'needItems[]';
            newItemInput.placeholder = '지원자에게 궁금한 점을 입력하세요';
            newItemInput.required = true;

            let removeItemSpan = document.createElement('button');
            removeItemSpan.className = 'remove-item';
            removeItemSpan.textContent = '항목 제거';
            removeItemSpan.setAttribute('onclick', 'removeItem(this)');

            newItemContainer.appendChild(newItemLabel);
            newItemContainer.appendChild(newItemInput);
            newItemContainer.appendChild(removeItemSpan);

            let needItemsContainer = document.getElementById('need-items');
            needItemsContainer.appendChild(newItemContainer);
        }
    </script>
</head>
<body>
<form action="/post/create" method="post">
    <div class="form-group">
        <label for="title">제목</label>
        <input type="text" id="title" name="title" placeholder="제목을 입력하세요" required>
    </div>
    <div class="form-group">
        <label for="startDate">시작일</label>
        <input type="date" id="startDate" name="startDate" required>
    </div>
    <div class="form-group">
        <label for="endDate">마감일</label>
        <input type="date" id="endDate" name="endDate" required>
    </div>
    <div class="form-group">
        <label for="description">모집 안내</label>
        <textarea id="description" name="description" placeholder="설명을 입력하세요" required></textarea>
    </div>
    <h3>이력서 항목 작성</h3>
    <div id="need-items">
        <div class="form-group item">
            <%--            <label for="needItems">질문1</label>--%>
            <%--            <input type="text" id="needItems" name="needItems[]" required>--%>
            <%--            <button class="remove-item" onclick="removeItem(this)">항목 제거</button>--%>
        </div>
    </div>
    <div class="form-group">
        <button type="button" onclick="addItem()">항목 추가</button>
    </div>
    <div class="form-group">
        <button type="submit">작성하기</button>
    </div>
</form>
</body>
</html>
