<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ランキングページ｜NYASTAR</title>
    <!-- 共通のCSS読み込み -->
	<jsp:include page="includedCSS.jsp" />
</head>
<body>
	<!-- メインメニュー -->
	<jsp:include page="includedMenu.jsp" />

<main>
    <!--ランキングのタグを選択追加します -->
  <form method="POST" action="/NYASTER/Ranking">
    <input type="submit" name="tag" value="累計">
    <input type="submit" name="tag" value="運動">
    <input type="submit" name="tag" value="読書">
    <input type="submit" name="tag" value="勉強">
    <input type="submit" name="tag" value="その他">
	</form>
  <h2>週間ランキング</h2>
  <div id="ranking">
    <table id="list">
      <thead>
        <tr>
          <th>順位</th>
          <th>ユーザー名</th>
          <th>がんばりタイム</th>
        </tr>
      </thead>
		<tbody>
		  <c:forEach var="e" items="${rankingList}" varStatus="status">
		    <tr>
		      <td>${status.index + 1}位：</td>
		      <td>${e.userName}</td>
		      <td> ${e.ganbariTimeHours}時間${e.ganbariTimeMins}分</td>
		    </tr>
		  </c:forEach>
		</tbody>
    </table>
  </div>
</main>

    <footer>
    <!-- フッターのコンテンツをここに追加します -->
    <p>&copy; 2023 NYASTAR. All rights reserved.</p>
    </footer>
</body>
</html>
