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
</form method="POST" action = NYASTER/Search><form>
<label>検索：
 <input type="text" name="seList"placeholder="ユーザ名,ユーザIDで検索" ></label><br>
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
</body>
</html>