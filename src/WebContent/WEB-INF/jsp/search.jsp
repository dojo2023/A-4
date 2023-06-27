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
	<link rel="stylesheet" href="css/styles_search.css">
  <!-- JS -->
    <script src="https://kit.fontawesome.com/b0a477e877.js" crossorigin="anonymous"></script>
  <title>ユーザ検索｜NYASTAR</title>
</head>
<body>
	<!-- メインメニュー -->
	<jsp:include page="includedMenu.jsp" />

    <style>
        .menu .search .material-symbols-outlined {
            font-variation-settings:
            'FILL' 1,
            'wght' 600,
            'GRAD' 200,
            'opsz' 48
        }

        .menu .search li {
            background-color: #FEEFC3;
            font-weight: 700;
        }

        @media screen and (max-width: 769px) {
        	.menu .search li {
        		background-color: unset;
	            font-weight: 700;
	    }
    }
    </style>

	<main class="content">
		<div class="title">NYASTAR</div>
		<!-- <div class="page_title">検索</div> -->

		<form method="post" class="search-form" action = "/NYASTER/Search">
			<label>
				<input type="text" name="search_query" placeholder="ID,名前でユーザーを検索">
			</label>
			<button type="submit" aria-label="検索"></button>
		</form>

		<c:choose>
			<c:when test="${search_result == 0}">
				<div class="search_result_label_none">キーワードに一致するユーザーが見つかりませんでした。</div>
			</c:when>
			<c:when test="${search_result > 0}">
				<div class="search_result_label">検索結果</div>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>


		<ul class="search_result">
		<c:forEach var= "e" items= "${seList}" varStatus="status">
			<li class="user_info">
				<div class="flexbox">
					<!-- img class="icon" src="C:/pleiades/workspace/data/icon_img/human.png" alt="ユーザアイコン"> -->

					<c:set var="directoryPath" value="icon_img" />
    				<c:set var="fileName" value="${userId}.png" />
​
					<c:if test="${fn:contains(directoryFiles, fileName)}">
						<!-- ファイルが存在したとき処理 -->
						<img class="icon" src="icon_img/${e.user_id}.png" alt="ユーザアイコン">
					</c:if>

					<c:if test="${!fn:contains(directoryFiles, fileName)}">
						<!-- ファイルが存在しなかったときの処理 -->
						<img class="icon" src="icon_img/human.png" alt="ユーザアイコン">
					</c:if>


					<div class="user" id="get_${e.user_id}">
						<div class="id">@${e.user_id}</div>
						<div class="name">${e.user_name}</div>
					</div>
					<div class="g-time">
						<div class="g-time-label">がんばり時間:</div>
						<div class="g-time-record">${e.ganbariTimeHours}時間${e.ganbariTimeMins}分</div>
					</div>
					<div class="linked_user">
						<form id="get_to_userpage" method="GET" action="/NYASTER/UserPage">
							<input type="hidden" name="u" value="${e.user_id}">
							<input class="material-symbols-outlined" type="submit" value="link">
						</form>
					</div>
				</div>
			</li>
			<script>
			const id = get_ + '${e.user_id}'
			document.getElementById(id).addEventListener("click", function() {
				document.getElementById("get_to_userpage").submit();
			});
			</script>
		</c:forEach>
		</ul>

		<footer>
		<p>&copy; 2023 NYASTAR. All rights reserved.</p>
		</footer>
	</main>

</body>
<!-- JavaScript -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/izimodal/1.5.1/js/iziModal.min.js"></script>
  <script type="text/javascript" src="./js/jquery-migrate-3.4.1.js"></script>
  <script src="js/iziToast.min.js" type="text/javascript"></script>
  <script src="js/iziToast.js" type="text/javascript"></script>
  <script src="js/Modal.js" type="text/javascript"></script>
  <!-- <script src="js/Toast.js" type="text/javascript"></script> -->
  <script src="js/script.js"></script>
</html>
