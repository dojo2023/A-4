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


</form method="POST" action = NYASTER/EditProfile><form>
<label>ユーザID<br>
 <input type="text" name="USER_ID"placeholder="１６文字以内で入力してください" maxlength ="16"value= ${e.userid} ></label><br>
${errorMsg}
 <label>ユーザ名<br>
<input type="text" name="USER_NAME"placeholder="１６文字以内で入力してください"maxlength ="16"value= ${e.username} ></label><br>
${errorMsg}
<label>変更前パスワード<br>
<input type="text" name="PASSWORD"placeholder="８文字以上で入力してください"minlength ="8"></label><br>
<br>
<label>変更後パスワード<br>
<input type="text" name="PASSWORD"placeholder="８文字以上で入力してください"minlength ="8" ></label><br>
<br>
<!-- 変更ボタン -->
<input type="button" class="btn"  value="変更" ><br><br>


<!--削除ボタン -->
<input type="button" id="acbtn" value="アカウント削除" >
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