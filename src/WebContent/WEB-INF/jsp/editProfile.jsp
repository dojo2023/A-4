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


<form method="POST" action = NYASTER/EditProfile>
<label>ユーザID<br>
 <input type="text" name="USER_ID"placeholder="１６文字以内で入力してください" maxlength ="16"value= "${USER_ID}" ></label><br>
	${errorMsg}<br>

 <label>ユーザ名<br>
<input type="text" name="USER_NAME"placeholder="１６文字以内で入力してください" maxlength ="16"value= "${USER_NAME}" ></label><br>
	${errorMsg}<br>

<label>変更前パスワード<br>
<input type="password" name="password"placeholder="８文字以上で入力してください" minlength ="8"value= "${PASSWORD}"></label>
		<i id="eye" class="fa-solid fa-eye"></i><br>
		${errorMsg}<br>

<label>変更後パスワード<br>
<input type="password" name="password"placeholder="８文字以上で入力してください" minlength ="8" ></label>
		<i id="eye" class="fa-solid fa-eye"></i><br>
		${errorMsg}<br>
</form>

<form method="POST" action = NYASTER/EditProfile>
<!-- 変更ボタン -->
<input type="button" class="btn"  value="変更" ><br><br>

</form>


<form method="POST" action = EditProfile>
<!--削除ボタン -->
<input type="hidden" name="UUID" value="${e.id}" readonly>
<input type="submit"  name="SELECT" value="アカウント削除" >
</form>
 <script>
                // アラートを表示
                function showAlert() {
                  alert("名刺を削除してもよろしいですか？");
                  return true;
                }
              </script>
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