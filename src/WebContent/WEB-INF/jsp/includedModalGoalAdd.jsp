<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<!-- 目標追加モーダル -->

 <style>
     .goal-button{
		color: #fafafa;
		background-color: #fed800e0;
		border-radius: 50px;
		padding: 4px 12px;
		margin-top: 15px;
		font-size: 15px;
		font-weight: 800;
     }
	.input-cont {
	    width: 150px;
	    padding: 0.5em 1em;
	    margin: 8px 3px;
	    color: #333;/*文字色*/
	    background: #FFF;
	    border: solid 1px #b9b9b9;/*線*/
	    border-radius: 10px;/*角の丸み*/
	}
	.input-cont-goalname {
	    width: 200px;
		height: 20px;
	    padding: 3px 10px 12px 10px;
	    margin: 8px 3px;
	    color: #333;/*文字色*/
	    background: #FFF;
	    border: solid 1px #b9b9b9;/*線*/
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

	.goals-s {
		margin-top: 20px;
	}

	.select_tag {
		margin: 0px 5px 8px 0px;
	}

	.goal_tag {
		padding: 5px 20px;
		border: 1px solid #b9b9b9;
		border-radius: 10px;
	}
 </style>

<div class="iziModal_goaladd" data-izimodal-title="目標を追加する" data-izimodal-subtitle="がんばりの達成目標を設定しよう！">
       	<form method="POST" action="/NYASTER/GoalServlet">
            <div class="goals-s">目標名
	            <div class="input-cont-goalname">
	                <input type="text" id="msg" name="goal_name" maxlength="50">
	            </div>
	            <div class="select_tag">目標タグ</div>
		         <select id="tag-select" name="goal_tag" class="goal_tag">
					<option hidden disabled selected>タグを選択してください...</option>
					<option value="運動">運動</option>
					<option value="勉強">勉強</option>
					<option value="読書">読書</option>
					<option value="その他">その他</option>
	            </select>
	  		</div>

            <div class="goals-s">
				<div>目標時間</div>
				<div class="IC">
					<div class="input-cont"> <input type="number" class="time-input" name="goal_hours" min="0" value="0" required>
						<label>時間</label>
					</div>
					<div class="input-cont"> <input type="number" class="time-input" name="goal_mins" min="0" max="60" value="0" required>
						<label>分</label>
					</div>
				</div>
				<div class="goal-button-panel">
					<input type="submit" name="select" class="goal-button" title="目標を設定する" value="追加">
				</div>
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
	    width: "430px",
	    transitionIn: "fadeInUp",
	    padding: "20px",
	    headerColor: "#FED600",
	    top: "5%"});});

	$(document).on('click', '.trigger-goaladd', function (event) {
	    event.preventDefault();
	    $('.iziModal_goaladd').iziModal('open');
	});

</script>


