<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編集・削除｜NYASTAR</title>
</head>
<body>
<p>プロフィール登録・編集</p>

<a id="return" href="("/WEB-INF/jsp/loginUser.jsp")" target="_blank" >←


<div id="sidebar" class="col-lg-2 sidemenu">
        <ul>
            <li><a href="#" class="link-title">ホーム</a></li>
            <li><a href="#" class="link-title">検索</a></li>
            <li><a href="#" class="link-title">ランキング</a></li>
            <li><a href="#" class="link-title">作成</a></li><br>
            <oi><a href="#" class="link-title">ユーザ名</a></oi>
        </ul>
</div>
<label>ユーザID<br>
 <input type="text" name="USER_ID"placeholder="１６文字以上で入力してください" required><br>
 <label>ユーザ名<br>
<input type="text" name="USER_NAME"placeholder="１６文字以上で入力してください" required><br>
<label>変更前パスワード<br>
<input type="text" name="B_PASSWORD"placeholder="８文字以上で入力してください" required><br>
<label>変更後パスワード<br>
<input type="text" name="A_PASSWOR"placeholder="８文字以上で入力してください"><br>

<!-- 変更ボタン -->
<input type="button" class="btn" value="変更" ><br><br>


<!--削除ボタン -->
<input type="button" class="acbtn" value="アカウント削除" >


</body>
</html>