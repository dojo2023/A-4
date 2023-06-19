<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索｜NYASTAR </title>
</head>
<body>

<p>プロフィール登録・編集</p>
<form method="POST" action = NYASTER/Search></form>
<label>検索：

 <!--ユーザ名,ユーザIDのどちらかで検索　searchword -->
 <input type="text" name="searchword" placeholder="ユーザ名,ユーザIDで検索" ></label>
<input type="submit" name="REGIST" value="検索">
${errorMsg}
<div>

        <ul>
            <li><a href="/NYASTER/TopPage"  class="link-title">ホーム</a></li>
            <li><a href="/NYASTER/Search" class="link-title">検索</a></li>
            <li><a href="/NYASTER/Ranking" class="link-title">ランキング</a></li>
            <li><a href="#" class="link-title">作成</a></li>
                <a href="/NYASTER/EditProfile" class="link-title">ユーザ名</a>
        </ul>
</div>


<h1>検索結果</h1>

<c:forEach var= "e" items="${seList}" >
<form method="POST" action="/NYASTER/Search">

ユーザID<input type="text" name="USER_ID" value="${e.user_id}">
ユーザ名<input type="text" name="USER_NAME" value="${e.user_name}">

</form>
</c:forEach>




</body>
</html>