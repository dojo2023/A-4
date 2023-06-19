<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="loginUserModal.css">
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>

	<form method="POST" action="/NYASTER/GoalServlet.java">
<button id="modalOpen" class="button">ログイン</button>


  <div id="easyModal" class="modal">
    <div class="modal-content">
      <div class="modal-header">

        <h2>目標の編集</h2>
    	 <form method="POST" action="/NYASTER/TopPage">
            <div>目標名</div>
            <div class="input-cont">
                <input type="text" id="msg" name="goal_name" maxlength="50">
            </div>
            <div>タグ</div>
            <select id="tag-select" name="goal_tag">
              <option value="運動">運動</option>
              <option value="勉強">勉強</option>
              <option value="読書">読書</option>
              <option value="その他">その他</option>
            </select>
            <div>目標時間</div>
            <div class="input-cont"> <input type="number" name="goal_hours" min="0" value="0" required> <label>時間</label>
            </div>
            <div class="input-cont"> <input type="number" name="goal_mins" min="0" max="60" value="0" required> <label>分</label>
            </div>
            <div class="login-button-panel">
                <input type="submit" name="select" class="login-button" title="目標を編集する" value="編集">
                <input type="submit" name="select" class="login-button" title="目標を削除する" value="削除"><br>
            </div>
        </form>





 <!--  <a href="" class="btn">削除</a>
  <a href="" class="btn">編集</a>
 -->

      </div>
    </div>
  </div>

  <script src="loginUserModal.js"></script>

</form>
</body>
</html>
