<%@page pageEncoding="UTF-8"%>
<style>
.nav-right {
	width: 83%;
	height: 56px;
	float: right;
	/*
	overflow: hidden;
	*/
}

.nav-right #self-info {
	height: 100%;
	line-height: 56px;
	cursor: pointer;
	border-radius: 10px;
}

.nav-right #self-info:hover {
	background: rgb(244, 246, 249);
}

.nav-right #self-info #head-img {
	width: 44px;
	width: 44px;
	border-radius: 22px;
}

.nav-right #messages {
	height: 100%;
	line-height: 56px;
	cursor: pointer;
	border-radius: 10px;
}

.nav-right #messages:hover {
	background: rgb(244, 246, 249);
}

.nav-right #msg {
	width: 35px;
	height: 35px;
}
/*上传头像*/
#modify-head-img {
	width: 500px;
	height: 309px;
	background: rgba(240,240,240,0.8);
	position: absolute;
	top: 50px;
	left: -250px;
	z-index: 4;
	box-shadow: -3px 3px 5px grey;
	border-radius: 5px;
}
#modify-head-img #img-btn{
	width: 100%;
	height: 40px;
	line-height: 40px;
	border: none;
}
</style>
<div class="nav-right row">
	<div class="col-8"></div>
	<div id='self-info' class="col-2">
		<img id='head-img' src='/img/headimg.png' />
		<div id='modify-head-img'>
			<form action="/file/pic/uploadHeadImg" method="post" enctype="multipart/form-data">
				<input name="fileImage" type="file" />
				<input type="hidden" value="${username }" />
				<input type="submit" value="提交"/>
			</form>
		</div>
		<span>${username }</span>
	</div>
	<div id='messages' class="col-1">
		消息 <img id='msg' src='/img/messages.png'>
	</div>
</div>
<script>
	$(function() {
		$("#modify-head-img").hide();
		//$("#head-img").attr("src","http://image.score/xusong.jpg");
		$("#self-info").mousemove(function() {
			$("#modify-head-img").show();
		});
		$("#self-info").mouseleave(function() {
			// $("#modify-head-img").hide();
		});

	});
</script>