<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザーページ</title>

<style>
    #wrapper {
      height: 200px;
      width: 300px;
      overflow-y: scroll;
    }

    #contents {
      background-color: cadetblue;
    }
</style>
</head>

<body>

	<input type="button" onclick="window.location='%_myname_%'" value="ログアウト">

	<!-- メインメニュー -->
	<jsp:include page="includedMenu.jsp" />

<main>

	<h1>マイページ</h1>

	<a href="UserPage" class="link-title">${username}</a>

	<a href="EditProfile" class="link-title">プロフィール登録・編集</a>

	<h2>「がんばり目標」</h2><br>
	<c:forEach var="e" items="${goalList}">
    	<li>${e.goalName} (${e.goalTimeHours}時間${e.goalTimeMins}分)</li>
	</c:forEach>


	<button name="button">編集</button>

	<button name="button">目標追加</button>


	<h2>活動合計時間</h2><br>
	<h2>${userTotalTime}</h2>

	<h2>投稿一覧</h2>
</main>

    <footer>
    <!-- フッターのコンテンツをここに追加します -->
    <p>&copy; 2023 NYASTAR. All rights reserved.</p>
    </footer>

</body>
</html>