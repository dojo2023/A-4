function asyncComments(id){
    console.log("asyncComments()呼ばれた. もらったID：");
    console.log(id);

    const commentsAreaId = 'comments-area-' + id;

    let postData = {
        post_id: id,
        select: 'view'
    };

    $.ajaxSetup({scriptCharset:'utf-8'});
    $.ajax({
        url: '/NYASTER/Comment',
        type: "POST",
        dataType: "json",
        data: postData,
        processDate: false,
        timeStamp: new Date().getTime()

    }).done(function(data) {
    let element =  document.getElementById(commentsAreaId);
    let elementText;

    console.log("jsonテスト表示");
    for(let i=0; i<data.length; i++){

        // elementText = data[i].user_name;
        var newElement = document.createElement("div");
        newElement.textContent = data[i].user_name;
        // newElement.setAttribute("class", "comment-box");
        //再表示時に一括削除する為の共通クラスを設定
        newElement.classList.add("comment-contents"); 
        newElement.classList.add("comment-userName");
        element.insertAdjacentElement("beforeend", newElement);

        // elementText = data[i].comment_content;
        var newElement = document.createElement("div");
        newElement.textContent = data[i].comment_content;
        // newElement.setAttribute("class", "comment-box");
        newElement.classList.add("comment-contents");
        newElement.classList.add("comment-msg");
        element.insertAdjacentElement("beforeend", newElement);

        // 投稿時間を変換して表示
        var timestamp = data[i].comment_time; // Javaから受け取ったTimestamp型の変数
        var date = new Date(timestamp);
        var year = date.getFullYear();
        var month = date.getMonth() + 1; // 月は0から始まるため+1
        var day = date.getDate();
        var hours = date.getHours();
        var minutes = date.getMinutes();

        // フォーマットに基づいて日時を表示
        var formattedDateTime = year + "年" + month + "月" + day + "日 " + hours + "時" + minutes + "分";

        // elementText = formattedDateTime;
        var newElement = document.createElement("div");
        newElement.textContent = formattedDateTime;
        // newElement.setAttribute("class", "comment-box");
        newElement.classList.add("comment-contents");
        newElement.classList.add("comment-timestamp");
        element.insertAdjacentElement("beforeend", newElement);
    }
    })
    //非同期通信が失敗したときの処理
    .fail(function() {
    //失敗とアラートを出す
    alert("失敗！");
    });
}