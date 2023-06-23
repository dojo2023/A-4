<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="loginUserModal.css">
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>




	<form method="POST" action="/NYASTER/GoalServlet">
    <!-- <button id="modalOpen" class="button">ログイン</button>


  <div id="easyModal" class="modal"></div>
    <div class="modal-content">
      <div class="modal-header">
      <h2>目標追加</h2>
      <span class="modalClose">×</span>
      </div>

      <div class="modal-body">
 -->
        <h2>目標の追加</h2>

    	 <form method="GET" action="/NYASTER/GoalServlet">
            <div>目標名</div>
            <div class="input-cont">
                <input type="text" id="msg" name="goal_name" maxlength="50">
            </div>
            <div>タグ</div>
            <select id="tag-select" name="goal_tag">
              <option value="運動" name="exercise">運動</option>
              <option value="勉強" name="study">勉強</option>
              <option value="読書" name="reading">読書</option>
              <option value="その他" name="others">その他</option>
            </select>
            <div>目標時間</div>
            <div class="input-cont"> <input type="number" name="goal_hours" min="0" value="0" required> <label>時間</label>
            </div>
            <div class="input-cont"> <input type="number" name="goal_mins" min="0" max="60" value="0" required> <label>分</label>
            </div>
            <div class="login-button-panel">
                <input type="submit" name="select" class="login-button" title="目標を追加する" value="追加">
			</div>
			</form>
<!-- ここから削除・編集-->

        <h2>目標リスト</h2>
        <ul>
            <c:forEach var="e" items="${goalList}">
                <li>${e.goalName}</li>
                <li>${e.genreTag}</li>
                <li>${e.goalTime}</li>
                <form method="POST" action="/NYASTER/GoalServlet">
	        		<input type ="hidden" name="goal_id" value="${e.goalId}" readonly>
	                <input type="submit" name="select" value="削除"><br>
		    	</form>
		    </c:forEach>
        </ul>

<!-- <c:forEach var="e" items="${cardList}" >
	<form method="POST" action="/simpleBC/UpdateDeleteServlet">
	番号<input type="text" name="NUMBER" value="${e.number}"><br>
	氏名<input type="text" name="NAME" value="${e.name}"><br>
	住所<input type="text" name="ADDRESS" value="${e.address}"><br>
	<input type="submit" name="SUBMIT" value="更新">
	<input type="submit" name="SUBMIT" value="削除"><br>
	</form>
	<hr>
</c:forEach> -->

  <!--<a href="" class="btn">削除</a>
  <a href="" class="btn">編集</a>
 -->
 </div>
 </div>
 </div>
 </form>

  <script src="loginUserModal.js"></script>

</form>
</body>
</html>
