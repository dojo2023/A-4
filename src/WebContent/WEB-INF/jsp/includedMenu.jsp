<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 共通のヘッダー部分 -->
<div id="sidebar" class="col-lg-2 sidemenu">
    <ul>
        <li><a href="TopPage" class="link-title">ホーム</a></li>
        <li><a href="Search" class="link-title">検索</a></li>
        <li><a href="Ranking" class="link-title">ランキング</a></li>
        <li><a href="UserPage" class="link-title">マイページ</a></li>
        <li class="trigger-post"><a href="#" class="link-title">作成</a></li>
    </ul>
</div>

<!-- 名刺追加モーダル -->
<div class="iziModal_post" data-izimodal-title="投稿" data-izimodal-subtitle="説明文">
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
</div>


<script>
	$(function () {
	  $(".iziModal_post").iziModal({
	    width: "600px",
	    transitionIn: "fadeInUp",
	    padding: "20px",
	    headerColor: "#768793",
	    top: "30px"});});

	$(document).on('click', '.trigger-post', function (event) {
	    event.preventDefault();
	    $('.iziModal_post').iziModal('open');
	});
</script>