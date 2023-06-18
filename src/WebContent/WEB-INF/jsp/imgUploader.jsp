<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        #figure {
            /* サイズを調整 */
            width: 200px;
            height: 200px;

            overflow: hidden;
            border-radius: 50%;
            position: relative;
            border: 3px solid #eeeeee;
        }

        #figure img {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            max-width: 100%;
            max-height: 100%;
            width: auto;
            height: auto;
            margin: auto;
            object-fit: contain;
        }
    </style>
    <title>画像ファイルのプレビュー</title>
  </head>
  <body>
    <p>画像ファイルを選択するとプレビューが表示されます。</p>
    <form method="POST" enctype="multipart/form-data" action="/NYASTER/ImgUpload">
      <div>
        <label for="input">画像ファイル</label>
        <!-- 画像ファイルをアップロード -->
        <input type="file" name="file" id="input" accept="image/*">
        <figure id="figure" style="display: none">
          <!-- アップロード画像を表示 -->
          <img src="" alt="" id="figureImage">
        </figure>
        <button type="submit">送信</button>
      </div>
    </form>
    <script src="js/imgPreview.js"></script>
  </body>
</html>