<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<!-- 目標追加モーダル -->

 <style>
     .iziModal_post {
         display: none;
     }
      .goals-s{
       color: #6091d3;

     }
     .input-cont {

     }
     .goal-button{
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
	.time-input {
	    width: 100px;
	}
	.IC {
		display: flex;
	    align-items: center;
        justify-content: flex-start;
	}
 </style>

<div class="iziModal_goaladd" data-izimodal-title="目標を追加する" data-izimodal-subtitle="がんばりの達成目標を設定しよう！">
   <h2 class="goals-s">がんばり投稿</h2>
       	 <form method="POST" action="/NYASTER/UserPage">
            <div class="goals-s">目標名</div>
            <div class="input-cont">
                <input type="text" id="msg" name="goal_name" maxlength="50">
            </div>
            <div class="select_tag">タグ
	         <select id="tag-select" name="goal_tag">
              <option value="運動">運動</option>
              <option value="勉強">勉強</option>
              <option value="読書">読書</option>
              <option value="その他">その他</option>
            </select> 
	  		</div>

            <div class="goals-s">目標時間</div>
            <div class="IC">
            <div class="input-cont"> <input type="number" class="time-input" name="goal_hours" min="0" value="0" required> <label>時間</label>
            </div>
            <div class="input-cont"> <input type="number" class="time-input" name="goal_mins" min="0" max="60" value="0" required> <label>分</label>
            </div></div>
            <div class="goal-button-panel">
                <input type="submit" name="select" class="goal-button" title="目標を設定する" value="追加">
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


