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
<title>新規登録｜NYASTAR</title>
</head>
<body>
	<h1>がんばりシェアアプリ</h1>
	<hr>

	<h2>新規登録</h2>
	<hr>

	<form method="POST" action="/NYASTER/Register">
		IDの設定は半角英数字と記号(_)のみ使用できます<br>
		ユーザーID<input type="text" placeholder="16文字以内で入力してください" maxlength="16" name="ID"><br>
		${errorMsg}<br>
		ユーザー名<input type="text" placeholder="16文字以内で入力してください" maxlength="16" name="NAME"><br>
		${errorMsg}<br>
		パスワードの設定は半角英数字と記号(_)のみ使用できます<br>
		パスワード<input type="password" placeholder="8文字以上で入力してください" minlength="8" required  name="PW">
		<i id="eye" class="fa-solid fa-eye"></i><br>
		${errorMsg}<br>
		<input type="submit" name="REGISTER" value="登録">
	</form>

	<a href="Login">ログインはこちら</a>

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