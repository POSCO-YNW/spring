<%@ page import="pack01.domain.Post" %>
<%@ page import="pack01.domain.NeedItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
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
            margin: 50px auto;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
        }
        h3{
            text-align: center;
            margin: 50px 0 10px 0;
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
        textarea{
            min-height: 250px;
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
        .need-item-input{
            margin-bottom: 10px;
        }
        .remove-item {
            color: red;
            cursor: pointer;
            margin-top: 10px;
        }
        .cancel-btn{
            background-color: red;
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
            newItemInput.className = 'need-item-input'

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
        const onCancel = ()=>{
            window.location.href = "/postlist";
        }
    </script>
</head>
<body>
<%
    Post post = (Post) request.getAttribute("post");
    Boolean exist = (post!=null)? true : false;
    List<NeedItem> needItems = (List<NeedItem>) request.getAttribute("needItems");
    String[] descLines = new String[6];
    if (exist){ descLines = post.getDescription().split("\\$\\$"); }
%>
<form action="<%= exist ? "/postlist/post/edit?id=" + post.getPostId() : "/postlist/post/create" %>" method="post">
    <div class="form-group">
        <label for="title">제목</label>
        <input type="text" id="title" name="title" placeholder="제목을 입력하세요" value="<%= exist ? post.getTitle() : "" %>" required>
    </div>
    <div class="form-group">
        <label for="startDate">시작일</label>
        <input type="date" id="startDate" name="startDate" value="<%= exist ? post.getStartDate() : "" %>" required>
    </div>
    <div class="form-group">
        <label for="endDate">마감일</label>
        <input type="date" id="endDate" name="endDate" value="<%= exist ? post.getEndDate() : "" %>" required>
    </div>
    <div class="form-group">
        <label for="select">채용 구분</label>
        <select id="select" name="description[]" required>
            <option value="신입" <%=exist && descLines[0].equals("신입") ? "selected":""%>>신입</option>
            <option value="경력" <%=exist && descLines[0].equals("경력") ? "selected":""%>>경력</option>
        </select>
    </div>
    <div class="form-group">
        <label for="requirements">자격 요건</label>
            <%
                if(exist){
            %>
            <textarea id="requirements" name="description[]" placeholder="지원자격 요건을 입력하세요(우대사항 포함)" required><%=descLines[1]%></textarea>
            <%}else{%>
            <textarea id="requirements" name="description[]" placeholder="지원자격 요건을 입력하세요(우대사항 포함)" required></textarea>
        <%}%>
    </div>
    <div class="form-group">
        <label for="job">수행 업무</label>
        <%
            if(exist){
        %>
        <textarea id="job" name="description[]" placeholder="입사 후 수행 업무를 입력하세요" required><%=descLines[2]%></textarea>
        <%}else{%>
        <textarea id="job" name="description[]" placeholder="입사 후 수행 업무를 입력하세요" required></textarea>
        <%}%>
    </div>
    <div class="form-group">
        <label for="person">인원</label>
        <input type="text" id="person" name="description[]" placeholder="채용 인원을 입력하세요" value="<%=exist? descLines[3]:"" %>" required/>
    </div>
    <div class="form-group">
        <label for="salary">급여</label>
        <input type="text" id="salary" name="description[]" placeholder="급여를 입력하세요" value="<%=exist? descLines[4]:"" %>" required/>
    </div>
    <div class="form-group">
        <label for="jobMerit">근무 조건 및 복리 후생</label>
        <%
            if(exist){
        %>
        <textarea id="jobMerit" name="description[]" placeholder="근무 시간 등 근무 조건과 복리 후생 등을 입력하세요" required><%=descLines[5]%></textarea>
        <%}else{%>
        <textarea id="jobMerit" name="description[]" placeholder="근무 시간 등 근무 조건과 복리 후생 등을 입력하세요" required></textarea>
        <%}%>
    </div>

    <h3>이력서 항목 작성</h3>
    <div id="need-items">
        <% if (exist) { %>
        <% for (NeedItem item : needItems) { %>
        <div class="form-group item">
            <label for="needItems">질문</label>
            <input type="hidden" id="needItemsId" name="needItemsId[]" value="<%=item.getNeedItemId()%>"/>
            <input type="text" id="needItems" name="needItems[]" value="<%= item.getTitle() %>" required>
            <button class="remove-item" onclick="removeItem(this)">항목 제거</button>
        </div>
        <% } %>
        <% } else { %>
        <div class="form-group item"></div>
        <% } %>
    </div>
    <div class="form-group">
        <button type="button" onclick="addItem()">항목 추가</button>
    </div>
    <div class="form-group">
        <button type="submit"><%= exist ? "수정하기" : "작성하기" %></button>
    </div>
    <div class="form-group">
        <button type="button" class="cancel-btn" onclick="onCancel()">취소하기</button>
    </div>
</form>
</body>
</html>
