<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <!-- キャッシュの保存を無効化 -->
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta name="viewport" content="width=device-width" />
	<!-- 共通のCSS読み込み -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<jsp:include page="includedCSS.jsp" />

	<title>トップページ｜NYASTAR</title>

</head>
<body>
	<!-- メインメニュー -->
	<jsp:include page="includedMenu.jsp" />

    <style>
        .menu .home .material-symbols-outlined {
          font-variation-settings:
          'FILL' 1,
          'wght' 600,
          'GRAD' 200,
          'opsz' 48
      }
    </style>

    <main class="content">
        <div class="title">NYASTAR</div> <!-- スマホ画面用 -->
        <div class="page_title">ホーム</div>

        <h2>タイムライン</h2>
        <div class="flexContainer">
            <c:forEach var="e" items="${postList}" varStatus="loop">
                <div class="post_card fadeUp" style="animation-delay: ${loop.index*0.2}s;">
                    <div class="flex">
                        <div class="user_icon">
                            <img src="img/human.png" alt="ユーザアイコン">
                        </div>
                        <div class="u-info">
                            <span class="u-name">${e.userName}</span>
                            <%-- <span class="u-id">(@${e.userId})</span> --%>
                            <span class="u-id">(@$userId)</span>
                        </div>
                    </div>
                    <div class="g-Content">
                        <span class="g-tag"><i class="fa-solid fa-book icon" style="color: #203250;"></i>${e.ganbariTag}</span>
                        <span class="g-times">${e.ganbariTimeHours}時間${e.ganbariTimeMins}分</span>
                    </div>
                    <div class="progress-label">
                        目標進捗率: ${Math.floor((e.progress/e.goalTime)*100)}%
                    </div>
                    <progress class="goal_progress" value="${Math.floor((e.progress/e.goalTime)*100)}" max="100"></progress>
                    <textarea name="" id="" class="msg" cols="30" rows="5" readonly>${e.msg}</textarea>
                    <div class="post_time">${e.postTime}</div>
                    <div class="reaction">
                        <i class="fa-regular fa-comment fa-lg .comment-icon trigger-comments-${e.id}"  onclick="asyncComments('${e.id}')"><span class="reaction-num">1</span></i>
                        <i class="fa-regular fa-heart fa-lg"><span class="reaction-num">12</span></i>
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
                    <input type="hidden" name="post_id" value="${e.id}" readonly>
                    <input type="text" name="cmt_msg" min="0" max="50" required>
                    <div> <input type="submit" name="select" title="コメントする" value="add"> </div>
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

        <footer>
            <p>&copy; 2023 NYASTAR. All rights reserved.</p>
        </footer>

        <jsp:include page="includedModal.jsp"/>
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
</body>
</html>
