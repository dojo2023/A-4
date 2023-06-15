<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>トップページ｜NYASTAR</title>
    <!-- 要素内のメタデータ、CSS、JavaScriptのリンクなどを追加します -->
</head>
<body>
    <div id="sidebar" class="col-lg-2 sidemenu">
        <ul>
            <li><a href="#" class="link-title">ホーム</a></li>
            <li><a href="#" class="link-title">検索</a></li>
            <li><a href="#" class="link-title">ランキング</a></li>
            <li><a href="#" class="link-title">作成</a></li>
        </ul>
    </div>

    <main>
        <form method="POST" action="/NYASTER/TopPage">
            <label for="tag">タグ</label>
            <select id="tag-select" name="tag">
              <option value="option1">運動</option>
              <option value="option2">勉強</option>
              <option value="option3">読書</option>
              <option value="option4">その他</option>
            </select>
            <select id="goal-select" name="goal">
              <option value="option1">目標（テスト）</option>
            </select>
              <div class="input-cont"> <input type="number" name="hours" min="0" placeholder="時間"> <label>時間</label>
              </div>
              <div class="input-cont"> <input type="number" name="mins" min="0" max="60" placeholder="分"> <label>分</label>
              </div>
              <div class="input-cont">  <label>メッセージ</label> <input type="text" id="msg" name="msg" maxlength="50">
              </div>
              <div class="login-button-panel"> <input type="submit" name="REGIST" class="login-button" title="登録する" value="登録"> </div>
        </form>

		<br>

    	<c:forEach var="e" items="${postList}">
            <div>投稿UUID： ${e.id}</div>
            <div>投稿者名： ${e.userName}</div>
            <div>タグ： ${e.ganbariTag}</div>
            <div>投稿メッセージ： ${e.msg}</div>
            <div>がんばり時間： ${e.ganbariTime}分</div>
            <div>がんばり目標： ${e.goalName}</div>
            <div>投稿時間： ${e.postTime}</div>
            <hr>
    	</c:forEach>

    </main>

    <footer>
    <!-- フッターのコンテンツをここに追加します -->
    <p>&copy; 2023 NYASTAR. All rights reserved.</p>
    </footer>
</body>
</html>
