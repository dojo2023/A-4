<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--    <link rel="stylesheet" href="css/ress.min.css"> -->
    <link rel="stylesheet" href="css/Login.css">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
<title>ログイン｜NYASTAR</title>
<meta charset="UTF-8">

<title>新規登録｜NYASTAR</title>
</head>
<body>
	<div class="log">
	<h1>がんばりシェアアプリ</h1>
	<hr>

	<h2>新規登録</h2>
	<hr>

	<form method="POST" action="/NYASTER/Register">
		IDの設定は半角英数字と記号(_)のみ使用できます<br>
		<div class="input-cont">
		ユーザーID<input type="text" id="userid" pattern="^[A-Za-z0-9_]+$" placeholder="16文字以内で入力してください" maxlength="16" name="id"><br>
		<div class="border2"></div></div>
		${errorMsg}<br>

	    <div class="input-cont">
		ユーザー名<input type="text" placeholder="16文字以内で入力してください" maxlength="16" name="name"><br>
		<div class="border2"></div>
		</div>
		${errorMsg}<br>

		<div class="input-cont">
		パスワードの設定は半角英数字と記号(_)のみ使用できます<br>
		パスワード<input type="password" id="password" pattern="^[A-Za-z0-9_]+$" placeholder="8文字以上で入力してください" minlength="8" required  name="pw">
		<i id="eye" class="fa-solid fa-eye"></i><br>
		<div class="border2"></div>
		</div>
		${errorMsg}<br>

		<div class="loginform-footer">
		<input type="submit" name="REGISTER" value="登録">
		</div>
	</form>

	<div class="loginform-footer">
	<a href="Login">ログインはこちら</a>
	</div>

	<script>
	// 目のアイコン
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