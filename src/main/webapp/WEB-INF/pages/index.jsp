<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 重置样式表 -->
<link rel="stylesheet" href="css/reset.css"/>
<!-- 首页样式表,背景图片和头部容器的样式 -->
<link rel="stylesheet" href="css/index.css">
<!-- 只包含登录框的样式 -->
<link rel="stylesheet" href="css/login.css">
<script src="js/jquery-1.8.3.min.js"></script>
<title>成绩管理系统-首页</title>
</head>
<body>
	<div id="container">
		<div class="head">
			<img alt="bar" src="img/nav.png" width="1080px" height="150px">
		</div>
		<div class="login">
			<div class='form'>
				<table>
					<tr>
						<td><input id='username' type="text" name="username" placeholder="用户名" /></td>
					</tr>
					<tr>
						<!-- JQuery动态验证或者Java后端验证 -->
						<td class="err-msg">${errorMsg }</td>
					</tr>
					<tr>
						<td><input id='password' type="password" name="password" placeholder="密码" /></td>
					</tr>
					<tr>
						<td class="err-msg alert">${errorMsg }</td>
					</tr>
					<tr>
						<!-- 用户协议手册创建一份 -->
						<td class="protocol">我已阅读并同意 <a href="###">《用户协议手册》</a></td>
					</tr>
					<tr>
						<td><input type="button" id='login-btn'
							value="登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录" /></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<footer>
		<!-- 页脚链接 -->
	</footer>
</body>
<script>
$(function(){
	$("#login-btn").click(function(){
		var username = $("#username").val();
		var password = $("#password").val();
		console.log("u" + username)
		console.log('p' + password)
		var data = {
			"username":username,
			"password":password
		};
		var url = 'doLogin';
		$.post(url,data,function(result){
			if(result.state==0){
				// 登录失败
				alert(result.message);
			}else{
				// 登录成功，根据身份不同跳转页面
				doJumpUserHome(result.data)
			}
		})
	});

	// 根据用户状态判断用户身份并跳转到不同的页面
	function doJumpUserHome(user){
		var username = $("#username").val();
		var password = $("#password").val();
		var status = user.status; // 用户身份，1==>学生，2==>教师，3==>管理员
		if(status==1){
			// 学生
			var url = '/toStudentHome';
			location.href=url+"?username="+username+"&t="+Math.random();
		}else if(status==2){
			// 教师
			var url = '/toTeacherHome';
			location.href=url+"?username="+username+"&t="+Math.random();
		}else if(status==3){
			// 管理员
			var url = '/toAdminHome';
			location.href=url+"?t="+Math.random();
			
		}else{
			alert("系统异常，无法登录")
		}
	}


	
})
</script>
</html>