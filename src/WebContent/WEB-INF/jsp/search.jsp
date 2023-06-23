<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>検索｜NYASTAR </title>
	<!-- 共通のCSS読み込み -->
	<jsp:include page="includedMenu.jsp" />
</head>
<body>

	<h1>ユーザー検索</h1>
		<!-- メインメニュー -->
		<jsp:include page="includedMenu.jsp" />

	<form method="POST" action = "/NYASTER/Search">
	<label>検索：
	 <!--ユーザ名,ユーザIDのどちらかで検索　searchword -->
	 <input type="text" name="search_query" placeholder="ユーザ名,ユーザIDで検索" >
	 <input type="submit" value="検索"></label>
	</form>

	<h2>検索結果</h2>

	<ul>
		<c:forEach var= "e" items= "${seList}" varStatus="status">
			<li><div>${status.index + 1}. ${e.user_name} (@${e.user_id})</div></li>
			<form method="GET" action="/NYASTER/UserPage">
				<input type="hidden" name="u" value="${e.user_id}">
				<input type="submit" value="ユーザページへ">
			</form>
		</c:forEach>
	</ul>

</body>
</html>