<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<!-- 共通のCSS読み込み -->
	<jsp:include page="includedCSS.jsp" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<title>トップページ｜NYASTAR</title>

</head>
<body>
	<!-- メインメニュー -->
	<jsp:include page="includedMenu.jsp" />

    <main>
        <p>ログインユーザー： ${username}</p>

        <h2>タイムライン</h2>
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

        <h2>目標の追加</h2>
    	 <form method="POST" action="/NYASTER/TopPage">
            <div>目標名</div>
            <div class="input-cont">
                <input type="text" id="msg" name="goal_name" maxlength="50">
            </div>
            <div>タグ</div>
            <select id="tag-select" name="goal_tag">
              <option value="運動">運動</option>
              <option value="勉強">勉強</option>
              <option value="読書">読書</option>
              <option value="その他">その他</option>
            </select>
            <div>目標時間</div>
            <div class="input-cont"> <input type="number" name="goal_hours" min="0" value="0" required> <label>時間</label>
            </div>
            <div class="input-cont"> <input type="number" name="goal_mins" min="0" max="60" value="0" required> <label>分</label>
            </div>
            <div class="login-button-panel">
                <input type="submit" name="select" class="login-button" title="目標を設定する" value="追加">
            </div>
        </form>

        <h2>目標リスト</h2>
        <ul>
            <c:forEach var="e" items="${goalList}">
                <li>${e.goalName}</li>
		    </c:forEach>
        </ul>

        <!-- ログアウト -->
        <form method="POST" action="/NYASTER/TopPage">
		<input type="submit"  name="select" value="ログアウト" >
		</form>
    </main>

    <footer>
    <!-- フッターのコンテンツをここに追加します -->
    <p>&copy; 2023 NYASTAR. All rights reserved.</p>
    </footer>

	<!-- JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/izimodal/1.5.1/js/iziModal.min.js"></script>
    <script type="text/javascript" src="./js/jquery-migrate-3.4.1.js"></script>
    <script src="js/iziToast.min.js" type="text/javascript"></script>
    <script src="js/iziToast.js" type="text/javascript"></script>

    <script src="js/Toast.js" type="text/javascript"></script>
    <script src="js/script.js"></script>
	<script src="js/asyncReaction.js"></script>
</body>
</html>
