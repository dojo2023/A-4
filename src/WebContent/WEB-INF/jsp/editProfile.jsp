<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />

<title>プロフィールの編集・削除｜NYASTAR</title>
</head>
<body>
	<!-- メインメニュー -->
	<jsp:include page="includedMenu.jsp" />

	<h1>プロフィール登録・編集</h1>

	<a href="UserPage">戻る</a>


	<form method="POST" action = "/NYASTER/EditProfile">
		<label>ユーザID<br>
		 <input type="text" name="USER_ID" maxlength ="16"value= "${userid}" ></label><br>
			${errorMsg}<br>

		 <label>ユーザ名<br>
		<input type="text" name="USER_NAME" maxlength ="16"value= "${username}" ></label><br>
			${errorMsg}<br>

		<label>変更前パスワード<br>
		<input type="password" name="pw_be"placeholder="８文字以上で入力してください" minlength ="8"></label>
				<i id="eye" class="fa-solid fa-eye"></i><br>
				${errorMsg}<br>

		<label>変更後パスワード<br>
		<input type="password" name="pw-af"placeholder="８文字以上で入力してください" minlength ="8" ></label>
				<i id="eye" class="fa-solid fa-eye"></i><br>
				${errorMsg}<br>

		<!-- 変更ボタン -->
		<input type="submit" class="btn" name="select" value="変更" ><br><br>

		<!--削除ボタン -->
		<input type="hidden" name="UUID" value="${e.id}" readonly>
		<input type="submit"  name="select" value="アカウント削除" >
	</form>
	<!--
	<script>
		var acbtn =document.getElementById('acbtn');
		acbtn.addEventListener('click',function(){
		window.confirm('本当に削除しますか');
		})
	</script>
	 -->
	<script>
		let eye = document.getElementById("eye");
		eye.addEventListener('click', function () {
		     if (this.previousElementSibling.getAttribute('type') == 'password') {
		          this.previousElementSibling.setAttribute('type', 'text');
		          this.classList.toggle('fa-eye');
		          this.classList.toggle('fa-eye-slash');
		     } else {
		          this.previousElementSibling.setAttribute('type', 'password');
		          this.classList.toggle('fa-eye');
		          this.classList.toggle('fa-eye-slash');
		     }
		})
	</script>
</body>
</html>