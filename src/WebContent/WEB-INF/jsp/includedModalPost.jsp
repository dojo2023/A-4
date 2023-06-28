<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<!-- 投稿モーダル -->

 <style>
     .iziModal_post {
         display: none;
     }
     .goals-s{
       color: #6091d3;

     }
     .posts-button{
       color: #fff;
       background-color: #6091d3;
       border-radius: 100vh;
     }
    .input-cont {
    width: 150px;
    padding: 0.5em 1em;
    margin: 2em 1px;
    font-weight: bold;
    color: #6091d3;/*文字色*/
    background: #FFF;
    border: solid 3px #6091d3;/*線*/
    border-radius: 10px;/*角の丸み*/
	}
	.input-cont p {
	    margin: 0;
	    padding: 0;
	}
	.input-msg {
    width: 400px;
    height: 100px;
    padding: 0.5em 1em;
    margin: 2em 0;
    font-weight: bold;
    color: #6091d3;/*文字色*/
    background: #FFF;
    border: solid 3px #6091d3;/*線*/
    border-radius: 10px;/*角の丸み*/
	}
	.input-msg p {
	    margin: 0;
	    padding: 0;
	}
	.time-input {
	    width: 100px;
	}
	.IC {
		display: flex;
	    align-items: center;
        justify-content: flex-start;
	}

 </style>

<div class="iziModal_post" data-izimodal-title="投稿" data-izimodal-subtitle="説明文">
   <h2 class="goals-s">がんばり投稿</h2>
   <form method="POST" action="/NYASTER/TopPage">
       <div>目標選択</div>
       <select id="goal-select" name="goal">
           <c:forEach var="e" items="${goalList}">
               <option value="${e.goalId}">${e.goalName}</option>
           </c:forEach>
       </select>

       <div>がんばり時間</div>
       <div class="IC">
       <div class="input-cont"> <input type="number" class="time-input" name="hours" min="0" value="0" required> <label>時間</label>
       </div>
       <div class="input-cont"> <input type="number" class="time-input" name="mins" min="0" max="60" value="0" required> <label>分</label>
       </div></div>
       <div class="input-msg">  <label>メッセージ</label> <input type="text" id="msg" name="msg" maxlength="50" >
       </div>
       <div> <input type="submit" name="select" class="posts-button" title="登録する" value="投稿"> </div>
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
