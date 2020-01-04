$(function() {
	
	// 功能1. 点击取消==>隐藏遮罩层,取消修改密码的操作
	$("#cancel-update-pwd").click(function() {
		console.log("取消修改密码")
		$(".bg").css({
			"display" : "none"
		});
	});

	/*
	 * 功能2. 点击确认修改密码 ----取消遮罩层 ----发送ajax数据
	 */
	$("#submit").click(function() {
		// 获取数据
		var username = $(".user-name").data("username");
		console.log("用户名:" + username)
		var oldPassword = $("[name='oldPassword']").val();
		var newPassword = $("[name='newPassword']").val();
		console.log("账号密码是" + username + "," + oldPassword + "," + newPassword);
		$.ajax({
			type:'post',
			dataType:'json',
			url:'updatePassword',
			data:{
				'username':username,
				'oldPassword':oldPassword,
				'newPassword':newPassword
			},
			success: function(info){
				alert(info.msg);
			}
		});
	});
});