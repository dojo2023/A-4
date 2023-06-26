
/*トーストCSS.JSの読み込み(JSP内に記述)
<link rel="stylesheet" type="text/css" href="./css/iziToast.css" media="screen" />
<script type="text/javascript" src="./js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="./js/jquery-migrate-3.0.1.js"></script>
<script type="text/javascript" src="./js/iziToast.js"></script>
<script type="text/javascript" src="./js/postToast.js"></script>
*/

//トーストJS

  $(function() {
    $(".login-button").on('click', function(event) {
      event.preventDefault();
      iziToast.show({
        title: '☆',
        message: '投稿が完了しました',
      });
    });
  })


