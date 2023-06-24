function reactionpost(Post_id) {
    let rcid ="rc_"+Post_id;
    let niceid = "nice_"+Post_id;

    if (document.getElementById(niceid).checked) {
        let rc = document.getElementById(rcid).textContent;
        document.getElementById(rcid).textContent=parseInt(rc)+1;
    } else {
        let rc = document.getElementById(rcid).textContent;
        document.getElementById(rcid).textContent=parseInt(rc)-1;
        }

    let reactionData = {postId:Post_id,select:"ナイス"};
    $.ajaxSetup({scriptCharset:'utf-8'});
    $.ajax({
        url: '/NYASTER/TopPage',
        type:"POST",
        dataType:"json",
        data: reactionData,
        processDate:false,
        timeStamp: new Date().getTime()
        //通信が成功したときの処理
    }).done(function() {
        })
        //通信が失敗したときの処理
        .fail(function() {
        });
}