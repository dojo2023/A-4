<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
<title>ログイン｜NYASTAR</title>
</head>
<body>
	<h1>がんばりシェアアプリ</h1>
	<hr>

	<h2>ログイン</h2>
	<hr>

	<form method="POST" action="/NYASTER/Login">
		ユーザーID<input type="text" placeholder="16文字以内で入力してください" maxlength="16" name="id"><br>
		パスワード<input type="password" placeholder="8文字以上で入力してください" minlength="8" required name="pw">
		<i id="eye" class="fa-solid fa-eye"></i><br>
		${errorMsg}<br>
		<input type="reset" name="RESET" value="リセット">
		<input type="submit" name="LOGIN" value="ログイン">
	</form>

		<a href="Register">新規登録はこちら</a>

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