<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<!-- 投稿モーダル -->

 <style>
     .iziModal_post {
         display: none;
     }

    /* ヘッダー非表示 */
/*     .iziModal-header {
        display: none;
    } */

	input[type="number"]::-webkit-inner-spin-button,
	input[type="number"]::-webkit-outer-spin-button {
	  -webkit-appearance: none;
	  margin: 0;
	  display: none;
	}

 </style>

<div class="iziModal_post" data-izimodal-title="がんばり投稿" data-izimodal-subtitle="今日のがんばりを共有しよう！">
<!-- 	<h2 class="goals-s">がんばり投稿</h2> -->
	<div class="modal_content">
	<form method="POST" action="/NYASTER/TopPage">

		<div class="goal">
			<div class="goal_label">目標を選ぶ</div>
				<select class="select_goal" id="goal-select" name="goal">
					<option hidden disabled selected>目標を選択してください...</option>
				<c:forEach var="e" items="${goalList}">
					<option value="${e.goalId}">${e.goalName}</option>
				</c:forEach>
			</select>
		</div>

		<div class="posts-s">がんばり時間</div>
		<div class="ic">
			<div class="input-cont"> <input type="number" class="time-input" name="hours" min="0" value="0" required onmousewheel="return false;"> <label>時間</label>
			</div>
			<div class="input-cont">
				<input type="number" class="time-input" name="mins" min="0" max="60" value="0" required onmousewheel="return false;"><label>分</label>
			</div>
		</div>
		<div class="msg-label">一言コメント</div>
       	<div>
			<textarea class="input-msg" id="msg" name="msg" maxlength="50" ></textarea>
       	</div>
       	<div>
		<input type="submit" name="select" class="posts-button" title="登録する" value="投稿">
		</div>
	</form>
	</div>
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
	    width: "300px",
	    transitionIn: "fadeInUp",
	    padding: "15px",
	    headerColor: "#FED600",
	    top: "5%"
	  });});

	$(document).on('click', '.trigger-post', function (event) {
	    event.preventDefault();
	    $('.iziModal_post').iziModal('open');
	});

</script>
