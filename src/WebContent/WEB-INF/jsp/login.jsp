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
</head>
<body>

	<div class="log">
	<h1>がんばりシェアアプリ</h1>
	<hr>

	<h2>ログイン</h2>
	<hr>

	<form method="POST" action="/NYASTER/Login">
	    <div class="input-cont">
		ユーザーID<input type="text" placeholder="16文字以内で入力してください" maxlength="16" name="id"><br>
	    <div class="border2"></div>
	    </div>
	    <div class="input-cont">
		パスワード<input type="password" placeholder="8文字以上で入力してください" minlength="8" required name="pw">
		<div class="border2"></div>
		</div>
		<i id="eye" class="fa-solid fa-eye"></i><br>
		${errorMsg}<br>
		 <div class="login-button-panel">
		<input type="reset" name="RESET" value="リセット">
		<input type="submit" name="LOGIN" value="ログイン">
		</div>
	</form>
	</div>

		<div class="loginform-footer">
		<a href="Register">新規登録はこちら</a>
		</div>

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