<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width" />
  <!-- CSS -->
	<jsp:include page="includedCSS.jsp" />
	<link rel="stylesheet" href="css/styles_ranking.css">
  <!-- JS -->
    <script src="https://kit.fontawesome.com/b0a477e877.js" crossorigin="anonymous"></script>
  <title>トップページ｜NYASTAR</title>
</head>
<body>
  	<!-- メインメニュー -->
	<jsp:include page="includedMenu.jsp" />

  <style>
       .menu .ranking .material-symbols-outlined {
           font-variation-settings:
           'FILL' 1,
           'wght' 600,
           'GRAD' 200,
           'opsz' 48
       }

       .menu .ranking li {
           background-color: #FEEFC3;
           font-weight: 700;
       }

       @media screen and (max-width: 769px) {
       	.menu .ranking li {
       		background-color: unset;
            font-weight: 700;
    }
   }
  </style>

  <main class="content">
    <div class="title">NYASTAR</div>
    <div class="page_title"><p>ランキング</p><p class="border"></p></div>


    <div class="ranking-tag">
      <form class="flexbox" method="POST" action="/NYASTER/Ranking">
        <input class="tag-button"  type="submit" name="tag" value="累計">
        <input class="tag-button"  type="submit" name="tag" value="運動">
        <input class="tag-button"  type="submit" name="tag" value="読書">
        <input class="tag-button"  type="submit" name="tag" value="勉強">
        <input class="tag-button"  type="submit" name="tag" value="その他">
      </form>
    </div>

    <ul class="ranking_result">
       <c:forEach var="e" items="${rankingList}" varStatus="status">
        <c:choose>
        <c:when test="${status.index == 0}">
          <li class="user_info">
            <div class="flexbox">
                <div class="rank">
                  <img src="img/f5-1.png" alt="#1">
                </div>
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

                <div class="user">
                    <div class="id">@${e.userId}</div>
                    <div class="name">${e.userName}</div>
                </div>
                <div class="g-time">
                    <div class="g-time-label">がんばり時間:</div>
                    <div class="g-time-record">${e.ganbariTimeHours}時間${e.ganbariTimeMins}分</div>
                </div>
            </div>
          </li>
        </c:when>
        <c:when test="${status.index == 1}">
          <li class="user_info">
            <div class="flexbox">
                <div class="rank">
                  <img src="img/f5-2.png" alt="#2">
                </div>
                <img class="icon" src="icon_img/${e.userId}.png" alt="ユーザアイコン">
                <div class="user">
                    <div class="id">@${e.userId}</div>
                    <div class="name">${e.userName}</div>
                </div>
                <div class="g-time">
                    <div class="g-time-label">がんばり時間:</div>
                    <div class="g-time-record">${e.ganbariTimeHours}時間${e.ganbariTimeMins}分</div>
                </div>
            </div>
          </li>
        </c:when>
        <c:when test="${status.index == 2}">
          <li class="user_info">
            <div class="flexbox">
                <div class="rank">
                  <img src="img/f5-3.png" alt="#2">
                </div>
                <img class="icon" src="icon_img/${e.userId}.png" alt="ユーザアイコン">
                <div class="user">
                    <div class="id">@${e.userId}</div>
                    <div class="name">${e.userName}</div>
                </div>
                <div class="g-time">
                    <div class="g-time-label">がんばり時間:</div>
                    <div class="g-time-record">${e.ganbariTimeHours}時間${e.ganbariTimeMins}分</div>
                </div>
            </div>
          </li>
        </c:when>
        <c:otherwise>
          <li class="user_info">
            <div class="flexbox">
              <div class="rank">
                #${status.index+1}
              </div>
              <img class="icon" src="icon_img/${e.userId}.png" alt="ユーザアイコン">
              <div class="user">
                  <div class="id">@${e.userId}</div>
                  <div class="name">${e.userName}</div>
              </div>
              <div class="g-time">
                  <div class="g-time-label">がんばり時間:</div>
                  <div class="g-time-record">0時間20分</div>
              </div>
            </div>
          </li>
        </c:otherwise>
        </c:choose>
      </c:forEach>
    </ul>

    <footer>
      <p>&copy; 2023 NYASTAR. All rights reserved.</p>
    </footer>
</main>

<script>

</script>
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
