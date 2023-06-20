<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索｜NYASTAR </title>
</head>
<body>

<p>プロフィール登録・編集</p>
<div>
	<ul>
	    <li><a href="TopPage" class="link-title">ホーム</a></li>
	    <li><a href="Search" class="link-title">検索</a></li>
	    <li><a href="Ranking" class="link-title">ランキング</a></li>
	    <li><a href="#" class="link-title">作成</a></li>
	</ul>
</div>

<form method="POST" action = "/NYASTER/Search">
<label>検索：
 <!--ユーザ名,ユーザIDのどちらかで検索　searchword -->
 <input type="text" name="searchword" placeholder="ユーザ名,ユーザIDで検索" >
 <input type="submit" name="REGIST" value="検索"></label>
</form>

<h1>検索結果</h1>

<ul>
	<c:forEach var= "e" items= "${seList}" varStatus="status">
		<li><div>${status.index + 1}. ${e.user_name} (@${e.user_id})</div></li>
	</c:forEach>
</ul>

</body>
</html>