<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<!-- 目標追加モーダル -->

 <style>
     .iziModal_post {
         display: none;
     }
     .input-cont {

     }

 </style>

<div class="iziModal_goaladd" data-izimodal-title="目標を追加する" data-izimodal-subtitle="がんばりの達成目標を設定しよう！">
   <h2>がんばり投稿</h2>
       	 <form method="POST" action="/NYASTER/UserPage">
            <div>目標名</div>
            <div class="input-cont">
                <input type="text" id="msg" name="goal_name" maxlength="50">
            </div>
            <div class="select_tag">タグ
	            <input type="radio" id="tag_01" name="goal_tag" value="運動">
	  			<label for="tag_01">運動</label>

	  			<input type="radio" id="tag_02" name="goal_tag" value="勉強">
	  			<label for="tag_02">勉強</label>

	  			<input type="radio" id="tag_03" name="goal_tag" value="読書">
	  			<label for="tag_03">読書</label>

				<input type="radio" id="tag_04" name="goal_tag" value="その他">
	  			<label for="tag_04">読書</label>
	  		</div>
<!--             <select id="tag-select" name="goal_tag">
              <option value="運動">運動</option>
              <option value="勉強">勉強</option>
              <option value="読書">読書</option>
              <option value="その他">その他</option>
            </select> -->
            <div>目標時間</div>
            <div class="input-cont"> <input type="number" name="goal_hours" min="0" value="0" required> <label>時間</label>
            </div>
            <div class="input-cont"> <input type="number" name="goal_mins" min="0" max="60" value="0" required> <label>分</label>
            </div>
            <div class="login-button-panel">
                <input type="submit" name="select" class="login-button" title="目標を設定する" value="追加">
            </div>
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
	  $(".iziModal_goaladd").iziModal({
	    width: "600px",
	    transitionIn: "fadeInUp",
	    padding: "20px",
	    headerColor: "#768793",
	    top: "30px"});});

	$(document).on('click', '.trigger-goaladd', function (event) {
	    event.preventDefault();
	    $('.iziModal_goaladd').iziModal('open');
	});

</script>


