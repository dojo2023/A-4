// 名刺管理アプリの流用 まだ変更した箇所なし

$(function () {
	$(".iziModal").iziModal({
		//group: "groupA",
		width: "40%",
		transitionIn: "fadeInUp",
		padding: "20px",
		headerColor: "#768793",
		closeOnEscape: false
	});
});


$(document).on('click', '.trigger-addCard', function (event) {
    event.preventDefault();
    $('.iziModal').iziModal('open');
});