<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン｜NYASTAR</title>
</head>
<body>
	<h1>がんばりシェアアプリ</h1>
	<hr>

	<h2>ログイン</h2>
	<hr>

	<form method="POST" action="/NYASTAR/Login">
		ユーザーID<input type="text" placeholder="16文字以内で入力してください" maxlength="16" name="ID"><br>
		パスワード<input type="password" placeholder="8文字以上で入力してください" minlength="8" required name="PW"><br>
		${errorMsg}<br>
		<input type="reset" name="RESET" value="リセット">
		<input type="submit" name="LOGIN" value="ログイン">
	</form>

		<a href="Register">新規登録はこちら</a>


</body>
</html>