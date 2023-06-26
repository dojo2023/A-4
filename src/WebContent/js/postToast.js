
/*トーストCSS.JSの読み込み(JSP内に記述)
<link rel="stylesheet" type="text/css" href="./css/iziToast.css" media="screen" />
<script type="text/javascript" src="./js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="./js/jquery-migrate-3.4.1.js"></script>
<script type="text/javascript" src="./js/iziToast.js"></script>
<script type="text/javascript" src="./js/postToast.js"></script>
*/

//トーストJS

//成功(緑)のトースト
  $(function() {
    $(".login-button").on('click', function(event) {
      event.preventDefault();
      iziToast.success({
        title: '成功',
        message: '投稿が完了しました',
      });
    });
  })

/*

//失敗(赤)のトースト
  $(function() {
    $(".ボタンのクラス名").on('click', function(event) {
      event.preventDefault();
      iziToast.error({
        title: '失敗',
        message: '投稿が失敗しました',
      });
    });
  })
//注意勧告(オレンジ)のトースト
  $(function() {
    $(".ボタンのクラス名").on('click', function(event) {
      event.preventDefault();
      iziToast.warning({
        title: '注意',
        message: '投稿が完了しました',
      });
    });
  })
//インフォメーション(青)のトースト
  $(function() {
    $(".ボタンのクラス名").on('click', function(event) {
      event.preventDefault();
      iziToast.info({
        title: '☆',
        message: '投稿が完了しました',
      });
    });
  })
//色なしトースト
  $(function() {
    $(".ボタンのクラス名").on('click', function(event) {
      event.preventDefault();
      iziToast.show({
        title: '☆',
        message: '投稿が完了しました',
      });
    });
  })

*/