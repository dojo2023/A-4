<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width" />
	<meta http-equiv="Cache-Control" content="no-cache">
	<!-- CSS -->
	<jsp:include page="includedCSS.jsp" />
	<link rel="stylesheet" href="css/styles_userpage.css">
	<!-- JS -->
	<script src="https://kit.fontawesome.com/b0a477e877.js" crossorigin="anonymous"></script>
	<title>ユーザーページ｜NYASTAR</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

	<style>
        .menu .userpage .material-symbols-outlined {
            font-variation-settings:
            'FILL' 1,
            'wght' 600,
            'GRAD' 200,
            'opsz' 48
        }

        .menu .userpage li {
            background-color: #FEEFC3;
            font-weight: 700;
        }

        @media screen and (max-width: 769px) {
        	.menu .userpage li {
        		background-color: unset;
	            font-weight: 700;
	    }
    }
	</style>
</head>

<body>
	<!-- メインメニュー -->
	<jsp:include page="includedMenu.jsp" />

	<main class="content">
		<div class="title">NYASTAR</div>
		<div class="page_title"><p>ユーザーページ</p><p class="border" style="width: 180px;"></p></div>
		<div class="flexbox">
			<div class="user_icon">
				<!-- <img src="icon_img/${userid}.png" alt="" id="iconImage"></img> -->
				<img src="img/human.png" alt="ユーザーアイコン">
			</div>
			<div class="user-info">
				<div class="user">
					<span class="name">${pageUserName}</span><span class="id">(@${pageUserId})</span>
				</div>
				<div class="ganbari">
					<div class="time">
						<span class="g-label">総がんばり時間:</span><span class="g-totaltime">${uttHours}時間${uttMins}分</span>
					</div>
					<div class="goal">
						<span class="g-label">目標達成回数:</span><span class="g-totaltime">0</span>
					</div>
				</div>
			</div>
			<!-- ページのユーザとログインユーザが一致していたら表示 -->
			<c:if test="${mypage}">
				<div class="material-symbols-outlined edit_account link-title edit_profile_button">tune</div>
			</c:if>
		</div>

		<div class="flexbox_goallist">
			<div class="goal_title">目標</div>
			<c:forEach var="e" items="${goalList}">
				<li>${e.goalName} (${e.goalTimeHours}時間${e.goalTimeMins}分)</li>
			</c:forEach>
			<c:if test="${mypage}">
				<div class="add_goal">
					<div class="material-symbols-outlined trigger-goaladd">add_box</div>
				</div>
			</c:if>
		</div>

		<div class="flexbox_chart">
			<h2>がんばり統計</h2>
			<h5>1週間の活動時間</h5>
			<div id="bar-chart"></div>
		</div>

		<!-- <a href="UserPage" class="link-title">${username}</a>
		<a href="EditProfile" class="link-title edit_profile_button">プロフィール登録・編集</a> -->

		<!-- <h2>活動合計時間</h2>
		<p>${uttHours}時間${uttMins}分</p> -->

		<footer>
            <p>&copy; 2023 NYASTAR. All rights reserved.</p>
        </footer>

        <jsp:include page="includedModalGoalAdd.jsp"/>
	</main>

	<!-- JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/izimodal/1.5.1/js/iziModal.min.js"></script>
    <script type="text/javascript" src="./js/jquery-migrate-3.4.1.js"></script>
    <script src="js/iziToast.min.js" type="text/javascript"></script>
    <script src="js/iziToast.js" type="text/javascript"></script>
    <script src="js/Toast.js" type="text/javascript"></script>
    <script src="js/script.js"></script>
	<script src="js/asyncReaction.js"></script>
    <script src="js/asyncComments.js"></script>
	<script src="js/asyncUserPageReaction.js"></script>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript" src="js/graph.js"></script>
</body>
</html>