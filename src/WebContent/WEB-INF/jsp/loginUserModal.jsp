<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="loginUserModal.css">
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>


<button id="modalOpen" class="button">ログイン</button>
  <div id="easyModal" class="modal">
    <div class="modal-content">
      <div class="modal-header">
        <h4>目標編集</h4><br>
	<h2>「がんばり」目標</h2>
        <span class="modalClose">×</span>
      </div>


      <div class="modal-body">
        <input type="text">
        <h4>がんばり目標時間</h4>

     <table>
      <tr>
       <td>
        <input type="text" size="5">
       </td>
       <td>
       時間
       </td>
      </tr>
      <tr>
       <td>
        <input type="text" size="5">
       </td>
       <td>
       分
       </td>
      </tr>
     </table>


      <h4>分類タグの選択</h4>
      <table border="1">
       <tr>
        <th>選択してください</th>
        </tr>
        <tr>
       <td><a href="#">読書</a></td>
        </tr>
        <tr>
       <td><a href="#">運動</a></td>
       </tr>
       <tr>
       <td><a href="#">勉強</a></td>
       </tr>
       <tr>
        <td><a href="#">その他</a></td>
       </tr>
     </table>

  <a href="" class="btn">削除</a>
  <a href="" class="btn">編集</a>


      </div>

    </div>

  </div>

  <script src="loginUserModal.js"></script>


</body>
</html>
