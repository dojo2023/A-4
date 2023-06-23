function reactionpost(Post_id) {
    let rcid ="rc_"+Post_id;
    let niceid = "nice_"+Post_id;
    console.log(rcid);
    console.log(niceid);

    if (document.getElementById(niceid).checked) {
        let rc = document.getElementById(rcid).textContent;
        document.getElementById(rcid).textContent=parseInt(rc)+1;
    } else {
        let rc = document.getElementById(rcid).textContent;
        document.getElementById(rcid).textContent=parseInt(rc)-1;
        }


    //{変数名：中に入れるもの}みたいに書いて、複数の値をpostData変数に格納
    let reactionData = {postId:Post_id,select:"ナイス"};
    $.ajaxSetup({scriptCharset:'utf-8'});
    $.ajax({
        //どのサーブレットに送るか
        //ajaxSampleのところは自分のプロジェクト名に変更する必要あり。
        url: '/NYASTER/UserPage',
        //どのメソッドを使用するか
        type:"POST",
        //受け取るデータのタイプ
        dataType:"json",
        //何をサーブレットに飛ばすか（変数を記述）
        data: reactionData,
        //この下の２行はとりあえず書いてる（書かなくても大丈夫？）
        processDate:false,
        timeStamp: new Date().getTime()
        //非同期通信が成功したときの処理
    }).done(function() {
        })
        //非同期通信が失敗したときの処理
        .fail(function() {
        });
}