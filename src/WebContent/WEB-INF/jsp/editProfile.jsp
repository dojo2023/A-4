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
<div>
<a id="return" href="/NYASTER/UserPage" target="_blank" >←</a>


        <ul>
            <li><a href="/NYASTER/TopPage"  class="link-title">ホーム</a></li>
            <li><a href="/NYASTER/Search" class="link-title">検索</a></li>
            <li><a href="/NYASTER/Ranking" class="link-title">ランキング</a></li>
            <li><a href="#" class="link-title">作成</a></li>
                <a href="/NYASTER/EditProfile" class="link-title">ユーザ名</a>
        </ul>
</div>



<label>ユーザID<br>
 <input type="text" name="user_id"placeholder="１６文字以内で入力してください" maxlength ="16"value= "${e.user_id}" ></label><br>

 <label>ユーザ名<br>
<input type="text" name="user_name"placeholder="１６文字以内で入力してください" maxlength ="16"value= "${e.user_name}" ></label><br>

<label>変更前パスワード<br>
<input type="text" name="password"placeholder="８文字以上で入力してください" minlength ="8"value= "${e.Passwprd}"></label><br>
<br>
<label>変更後パスワード<br>
<input type="text" name="password"placeholder="８文字以上で入力してください" minlength ="8" ></label><br>
<br>


<form method="POST" action = NYASTER/EditProfile>
<!-- 変更ボタン -->
<input type="button" class="btn"  value="変更" ><br><br>

</form>


<form method="POST" action = NYASTER/AccountsDao>
<!--削除ボタン -->
<input type="button" id="acbtn" value="アカウント削除" >
</form>
<!--
<script>
	var acbtn =document.getElementById('acbtn');
	acbtn.addEventListener('click',function(){
	window.confirm('本当に削除しますか');
	})
</script>
 -->

</body>
</html>