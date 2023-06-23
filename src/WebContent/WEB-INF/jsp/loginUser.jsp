<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザーページ</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
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
	<!-- 共通のCSS読み込み -->
	<jsp:include page="includedCSS.jsp" />
</head>

<body>

	 <!-- ログアウト -->
        <form method="POST" action="/NYASTER/UserPage">
		<input type="submit"  name="select" value="ログアウト" >
		</form>

	<!-- メインメニュー -->
	<jsp:include page="includedMenu.jsp" />

	<main>

		<h1>マイページ</h1>

		<a href="UserPage" class="link-title">${username}</a>

		<a href="EditProfile" class="link-title">プロフィール登録・編集</a>

		<h2>「がんばり目標」</h2>
		<c:forEach var="e" items="${goalList}">
			<li>${e.goalName} (${e.goalTimeHours}時間${e.goalTimeMins}分)</li>
		</c:forEach>

		<button name="button">目標追加</button>


		<h2>活動合計時間</h2>
		<p>${uttHours}時間${uttMins}分</p>

		<h2>投稿一覧</h2>
		<c:forEach var="e" items="${postList}">
			<hr>
			<!-- <div>投稿UUID： ${e.id}</div> -->
			<div>投稿者名： ${e.userName}</div>
			<div>タグ： ${e.ganbariTag}</div>
			<div>がんばり内容： ${e.msg}</div>
			<div>がんばり時間： ${e.ganbariTimeHours}時間${e.ganbariTimeMins}分</div>
			<div>がんばり目標： ${e.goalName} (${e.goalTimeHours}時間${e.goalTimeMins}分)</div>
			<div>目標進捗率: ${Math.floor((e.progress/e.goalTime)*100)}%</div>
			<div>投稿時間： ${e.postTime}</div>
			<div><span>ナイス数</span> <span id="rc_${e.id}">${e.reactionCount}</span></div>
			<form>
				<input type="checkbox" name="select" id="nice_${e.id}" value="ナイス" onchange= "reactionpost('${e.id}');"<c:if test="${e.reactionCheck == 1}" >checked</c:if>></input>
			</form>
		</c:forEach>
		<hr>
	</main>

    <footer>
    <!-- フッターのコンテンツをここに追加します -->
    <p>&copy; 2023 NYASTAR. All rights reserved.</p>
    </footer>

	<script src="js/asyncUserPageReaction.js"></script>
</body>
</html>