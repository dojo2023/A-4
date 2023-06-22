<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>トップページ｜NYASTAR</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    
    <!-- 要素内のメタデータ、CSS、JavaScriptのリンクなどを追加します -->
</head>
<body>
    <div id="sidebar" class="col-lg-2 sidemenu">
        <ul>
            <li><a href="#" class="link-title">ホーム</a></li>
            <li><a href="Search" class="link-title">検索</a></li>
            <li><a href="Ranking" class="link-title">ランキング</a></li>
            <li><a href="#" class="link-title">作成</a></li>
        </ul>
    </div>

    <main>
        <p>ログインユーザー： ${username}</p>

        <h2>がんばり投稿</h2>
        <form method="POST" action="/NYASTER/TopPage">
            <div>目標選択</div>
            <select id="goal-select" name="goal">
                <c:forEach var="e" items="${goalList}">
                    <option value="${e.goalId}">${e.goalName}</option>
                </c:forEach>
            </select>

            <div>がんばり時間</div>
            <div class="input-cont"> <input type="number" name="hours" min="0" value="0" required> <label>時間</label>
            </div>
            <div class="input-cont"> <input type="number" name="mins" min="0" max="60" value="0" required> <label>分</label>
            </div>
            <div class="input-cont">  <label>メッセージ</label> <input type="text" id="msg" name="msg" maxlength="50">
            </div>
            <div class="login-button-panel"> <input type="submit" name="select" class="login-button" title="登録する" value="投稿"> </div>
        </form>

		<br>

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
            <div><span>ナイス数</span> <span id="rc">${e.reactionCount}</span></div>
            <form method="POST" action="/NYASTER/TopPage">
                <input type="hidden" name="post_id" id="post_id" value="${e.id}">
                <input type="hidden" name="user_id" id="user_id" value="${useruuid}">
                <input type="checkbox" name="select" id="nice" value="ナイス"onchange="foo();" <c:if test="${e.reactionCheck == 1}" >checked</c:if>></input>
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
    </main>

    <footer>
    <!-- フッターのコンテンツをここに追加します -->
    <p>&copy; 2023 NYASTAR. All rights reserved.</p>
    </footer>
    <script>
  function foo() {
    if (document.getElementById("nice").checked) {
    	let rc = document.getElementById("rc").textContent;
    	document.getElementById("rc").textContent=parseInt(rc)+1;
    } else {
    	let rc = document.getElementById("rc").textContent;
    	document.getElementById("rc").textContent=parseInt(rc)-1;  	
 	 }
  //入力値を取得してくる
	let Post_id = document.getElementById("post_id").value;
	let User_id = document.getElementById("user_id").value;
	let Nice = document.getElementById("nice").value;

	//{変数名：中に入れるもの}みたいに書いて、複数の値をpostData変数に格納
	let reactionData = {postId:Post_id,userId:User_id,nice:Nice,select:"ああ"};
 	$.ajaxSetup({scriptCharset:'utf-8'});
	$.ajax({
		//どのサーブレットに送るか
		//ajaxSampleのところは自分のプロジェクト名に変更する必要あり。
		url: '/NYASTER/TopPage',
		//どのメソッドを使用するか
		type:"POST",
		//受け取るデータのタイプ
		dataType:"json",
		//何をサーブレットに飛ばすか（変数を記述）
		data: reactionData,
		//この下の２行はとりあえず書いてる（書かなくても大丈夫？）
		processDate:false,
		timeStamp: new Date().getTime()
	   //非同期通信が成功したときの処理
	}).done(function() {
	  })
	   //非同期通信が失敗したときの処理
	  .fail(function() {
	  });
}
</script>
</body>
</html>
