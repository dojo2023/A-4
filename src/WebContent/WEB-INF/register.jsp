<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録｜NYASTAR</title>
</head>
<body>
<h1>がんばりシェアアプリ</h1>
<hr>
<h2>新規登録</h2>
<hr>
<form method="POST" action="/NYASTAR/Login">
IDの設定は半角英数字と記号(_)のみ使用できます<br>
ユーザーID<input type="text" placeholder="16文字以内で入力してください" maxlength="16" name="ID"><br>
${errorMsg}<br>
ユーザー名<input type="text" placeholder="16文字以内で入力してください" maxlength="16" name="NAME"><br>
${errorMsg}<br>
パスワードの設定は半角英数字と記号(_)のみ使用できます<br>
パスワード<input type="password" placeholder="8文字以上で入力してください" minlength="8" required name="PW"><br>
${errorMsg}<br>
<input type="submit" name="REGISTER" value="登録">
</form>
<a href="Login">ログインはこちら</a>
</body>
</html>