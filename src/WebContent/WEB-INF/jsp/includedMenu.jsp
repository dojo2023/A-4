<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 共通のヘッダー部分 -->
<!-- <div id="sidebar" class="col-lg-2 sidemenu">
    <ul>
        <li><a href="TopPage" class="link-title">ホーム</a></li>
        <li><a href="Search" class="link-title">検索</a></li>
        <li><a href="Ranking" class="link-title">ランキング</a></li>
        <li><a href="UserPage" class="link-title">マイページ</a></li>
        <li><a href="#" class="link-title trigger-post">作成</a></li>
    </ul>
</div> -->

<div id="sidebar" class="menu">
  <div class="logo">NYASTAR </div>
  <!-- <img src="img/nyastar_logo.png" alt="" class="logo"> -->
  <ul>
    <li class="home"><a href="TopPage"><span class="material-symbols-outlined">home</span><span class="text">ホーム</span></a></li>
    <li class="search"><a href="Search"><span class="material-symbols-outlined">search</span><span class="text">検索</span></a></li>
    <li class="ranking"><a href="Ranking"><span class="material-symbols-outlined">leaderboard</span><span class="text">ランキング</span></a></li>
    <li><a href="#" class="link-title trigger-post"><span class="material-symbols-outlined">stylus_note</span><span class="text">作成</span></a></li>
    <li><a href="UserPage"><span class="material-symbols-outlined">person</span><span class="text">マイページ</span></a></li>
  </ul>
  <div class="logout">
    <!-- ログアウト -->
    <form method="POST" action="/NYASTER/TopPage">
      <input type="submit" class="material-symbols-outlined" name="select" value="logout" >
    </form>
  </div>
</div>
