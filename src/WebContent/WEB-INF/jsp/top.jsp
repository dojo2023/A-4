<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>
</head>

<body>
<div class="content">
<div class="modal js-modal">
<div class="modal__bg js-modal-close"></div>
<div class="modal__content">

<a class="closeBttom" href="TopPage">閉じる</a>
<span class="closeBttom"></span>
<p>送信先：<p/>
<p>（今までのコメント）</p>
 <p>コメントを追加</p><div class="comment_area" ></div>

<form method="POST" action = NYASTER/Comment>
<!-- 入力フォーム -->
<textarea class="sample" name="comment_content" placeholder="20文字以内で入力してください"rows="5" cols="40"></textarea>
<!-- 文字数制限表示 -->
<p class="error">エラー表示はここだよ</p>
<!-- 追加ボタン -->
<input type="button" class="btn" value="送信" >

</form><br>

</div>
</div>
</div>

</body>
</html>