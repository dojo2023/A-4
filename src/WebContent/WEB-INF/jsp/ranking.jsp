<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ランキングページ｜NYASTAR</title>
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
    <!--ランキングをここに追加します -->
    <form method="POST" action="/NYASTER/Ranking">
     <input type="submit" name="tag" value="ALL">
	  <input type="submit" name="tag" value="運動">
	  <input type="submit" name="tag" value="読書">
	  <input type="submit" name="tag" value="勉強">
	  <input type="submit" name="tag" value="その他">
	</form>
    <h2>週間ランキング</h2>
<div id="ranking">
  <table id="list">
    <tr>
      <th>ユーザー名</th><th>がんばりタイム</th>
    </tr>
    <c:forEach var="e" items="${rankingList}" >
      <tr><td>${e.userName}</td><td>${e.ganbariTime}</td>
    </c:forEach>
  </table>
</div>
    </main>

    <footer>
    <!-- フッターのコンテンツをここに追加します -->
    <p>&copy; 2023 NYASTAR. All rights reserved.</p>
    </footer>
</body>
</html>
