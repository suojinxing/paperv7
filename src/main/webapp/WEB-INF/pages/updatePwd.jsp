<%@page pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/updatePwd.css" />
<script src="js/updatePwd.js"></script>
<div class="bg">
	<div class="update-password">
		<form method="post">
			<input type="text" name="username" value="${username }"
				readonly="readonly" /> <input type="password" name="oldPassword"
				placeholder="原密码" /> <input type="password" name="newPassword"
				placeholder="新密码" />
			<div>
				<input type="button" id="cancel-update-pwd" value="取消/关闭" /> <input
					type="button" id="submit" value="点击确认修改密码">
			</div>
		</form>
	</div>
</div>