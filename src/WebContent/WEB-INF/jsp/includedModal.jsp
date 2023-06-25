<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<!-- 投稿モーダル -->

 <style>
     .iziModal_post {
         display: none;
     }
 </style>

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
       <div> <input type="submit" name="select" class="login-button" title="登録する" value="投稿"> </div>
   </form>
</div>

<script>
	// モーダルに要素が配置される前に表示されないため, ロード後2秒経過した後表示する
	window.addEventListener('DOMContentLoaded', function() {
	    var postModalElement = document.querySelectorAll('.iziModal_post');
	    setTimeout(function() {
	        postModalElement.style.display = 'block';
	    }, 2000); // 2秒後に要素を表示
    });

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


