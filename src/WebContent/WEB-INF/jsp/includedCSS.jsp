<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 共通のCSS読み込み -->
  <!-- メイン -->
  <link rel="stylesheet" href="css/styles.css">

  <!-- リセットCSS -->
<!--   <link rel="stylesheet" href="css/ress.min.css"> -->

  <!-- モーダル -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izimodal/1.5.1/css/iziModal.min.css">

  <!-- トースト -->
  <link rel="stylesheet" href="css/iziToast.min.css">

  <!-- アイコン -->
  <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
  <!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css"> -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
  <script src="https://kit.fontawesome.com/b0a477e877.js" crossorigin="anonymous"></script>

 <style>
     .comments_modal {
         display: none;
     }
 </style>

 <script>
     window.addEventListener('DOMContentLoaded', function() {
     var modalElement = document.querySelectorAll('.comments_modal');
     setTimeout(function() {
         modalElement.style.display = 'block';
     }, 3000); // 3秒後に要素を表示する
     });

 </script>