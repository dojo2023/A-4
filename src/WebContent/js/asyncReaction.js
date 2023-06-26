
function reactionpost(Post_id) {
    let rcid = "rc_"+Post_id;
    let niceid = "nice_"+Post_id;
    let likeid = "like_"+Post_id;
    let label = document.getElementById(likeid);

    if (document.getElementById(niceid).checked) {
        let rc = document.getElementById(rcid).textContent;
        document.getElementById(rcid).textContent=parseInt(rc)+1;
        label.classList.remove('fa-regular');
		label.classList.add('fa-solid');
        
    } else {
        let rc = document.getElementById(rcid).textContent;
        document.getElementById(rcid).textContent=parseInt(rc)-1;
        label.classList.remove('fa-solid');
		label.classList.add('fa-regular');
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