// 名刺管理アプリの流用 まだ変更した箇所なし

$(function() {
    $(".trigger-success").on('click', function(event) {
      event.preventDefault();
      iziToast.settings({
          timeout: 1500,
          resetOnHover: true,
          transitionIn: 'flipInX',
          transitionOut: 'flipOutX',
          onOpening: function(){
              console.log('callback abriu!');
          },
          onClosing: function(){
              console.log("callback fechou!");
          }
      });
      // success
      iziToast.success({
        message: 'アドレスをコピーしました',
        position: 'bottomRight',
        transitionIn: 'bounceInLeft',
      });
      
    });
  })