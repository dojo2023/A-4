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
