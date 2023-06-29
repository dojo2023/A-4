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
	<link rel="stylesheet" href="css/styles_home.css">
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
				<img id="icon" src="icon_img/${pageUserId}.png" alt="ユーザーアイコン">
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
			<ul>
			<c:forEach var="e" items="${goalList}">
				<li>${e.goalName} (${e.goalTimeHours}時間${e.goalTimeMins}分)</li>
			</c:forEach>
			</ul>
			<c:if test="${mypage}">
				<div class="add_goal">
					<div class="material-symbols-outlined trigger-goaladd" style="cursor: pointer;">add_box</div>
				</div>
			</c:if>
		</div>

		<div class="flexbox_chart">
			<h2>がんばり統計</h2>
			<h5>1週間の活動時間</h5>
			<div id="bar-chart"></div>
		</div>

		<div class="flex_mypost">
            <c:forEach var="e" items="${postList}" varStatus="loop">
                <div class="post_card fadeUp" style="animation-delay: ${loop.index*0.2}s;">
                    <div class="flex">
                        <c:set var="directoryPath" value="/icon_img" />
    					<c:set var="fileName" value="${e.userId}.png" />
                        <script>
                            console.log("${fileName}");
                        </script>
                        <div class="user_icon">
								<img class="icon" id="icon" src="icon_img/${e.userId}.png" alt="ユーザアイコン">
                        </div>
                        <div class="u-info">
                            <span class="u-name">${e.userName}</span>
                            <span class="u-id">(@${e.userId})</span>
                        </div>
                    </div>
                    <div class="g-Content">
                    	<span class="g-tag">
                    	<c:choose>
						<c:when test="${e.ganbariTag eq '運動'}">
							<i class="fa-solid fa-person-running" style="color: #203250;"></i>
						</c:when>
						<c:when test="${e.ganbariTag eq '勉強'}">
							<i class="fa-solid fa-pen" style="color: #203250;"></i>
						</c:when>
						<c:when test="${e.ganbariTag eq '読書'}">
							<i class="fa-solid fa-book icon" style="color: #203250;"></i>
						</c:when>
						<c:when test="${e.ganbariTag eq 'その他'}">
							<i class="fa-solid fa-cat" style="color: #203250;"></i>
						</c:when>
						</c:choose>
                        ${e.ganbariTag}</span>
                        <span class="g-times">${e.ganbariTimeHours}時間${e.ganbariTimeMins}分</span>
                    </div>
                    <div class="progress-label">
                        目標進捗率: ${Math.floor((e.progress/e.goalTime)*100)}%
                    </div>
                    <progress class="goal_progress" value="${Math.floor((e.progress/e.goalTime)*100)}" max="100"></progress>
                    <textarea name="" id="" class="msg" cols="30" rows="5" readonly>${e.msg}</textarea>
                    <div class="post_time" id="timestamp_${e.id}">${e.postTimeT}</div>
                    <div class="reaction">
                        <!-- コメント -->
                        <i class="fa-regular fa-comment fa-lg .comment-icon trigger-comments-${e.id}"  onclick="asyncComments('${e.id}')">
                        	<!-- コメント数表示 -->
                        	<span class="reaction-num">${e.commentsCount}</span>
                        </i>
					    <!-- ナイス -->
                        <form>
                            <input type="checkbox" class="checkbox" name="select" id="nice_${e.id}" value="ナイス" onchange= "reactionpost('${e.id}','${useruuid}');"<c:if test="${e.reactionCheck == 1}" >checked</c:if>></input>
                            <label for="nice_${e.id}">
                                <!-- チェックボックスの状態に応じて表示切り替え -->
                                <i  name="icon" id="like_${e.id}"<c:if test="${e.reactionCheck == 1}" >class="fa-solid fa-heart fa-lg"</c:if><c:if test="${e.reactionCheck == 0}" >class="fa-regular fa-heart fa-lg"</c:if>>
                                    <!-- ナイス数表示 -->
                                    <span class="reaction-num" id="rc_${e.id}">${e.reactionCount}</span>
                                </i>
                            </label>
					    </form>
                    </div>
            	</div>

                <!-- コメントモーダル -->

                <div class="iziModal_comments_${e.id} comments_modal" data-izimodal-title="コメント" data-izimodal-subtitle="説明文">
                    <h3>コメント一覧</h3>
                    <div id="comments-area-${e.id}">
                    <!-- ここにコメントを読み込ませる -->
                    </div>
                    <br>
                    <div>コメントする</div>
                    <form method="POST" action="/NYASTER/Comment">
                    <div class="input-comment">
                    <input type="hidden" name="post_id" value="${e.id}" readonly>
                    <input type="text" name="cmt_msg" min="0" max="50" placeholder="コメントを追加..."  required>
                    </div>
                    <div> <input type="submit" class="comment-button" name="select" value="add"> </div>
                    </form>
                </div>

                <script>
                    $(function() {
                    $(".iziModal_comments_${e.id}").iziModal({
                        width: "500px",
                        transitionIn: "fadeInUp",
                        ariahidden: "true",
                        padding: "20px",
                        headerColor: "#768793",
                        top: "230px",
                        // モーダルが閉じられたら取得したコメント要素も破棄する.
                        onClosed: function() {
                            let CommentElements = document.getElementsByClassName("comment-contents");
                            let length = CommentElements.length;
                            for (let i=0; i<length; i++) {
                                CommentElements[0].remove();
                        }}});

                        $(".iziModal_comments .iziModal-header").css("display", "none");

                    $(document).on('click', '.trigger-comments-${e.id}', function(event) {
                        event.preventDefault();
                        $('.iziModal_comments_${e.id}').iziModal('open');
                    });
                    });
                </script>
            </c:forEach>
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
	<script>
	  let image = document.getElementById("icon");
	  image.onerror = function() {
	    image.src = "img/human.png";
	  };
	</script>
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