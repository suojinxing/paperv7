<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/reset.css" />
<link rel="stylesheet" type="text/css" href="css/admin.css" />
<link rel="stylesheet" type="text/css" href="css/model-class.css" />
<link rel="stylesheet" type="text/css" href="css/model-user.css" />
<link rel="stylesheet" type="text/css" href="css/model-teacher.css" />
<link rel="stylesheet" type="text/css" href="css/model-student.css" />
<link rel="stylesheet" type="text/css" href="css/model-studentScore.css" />
<link rel="stylesheet" type="text/css" href="css/model-scoreList.css" />
<link rel="stylesheet" type="text/css" href="css/model-log.css" />
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="js/jquery-1.8.3.min.js"></script>

<%@ include file="/common/common-js.jsp" %>
<title>管理员个人主页</title>
</head>
<body>
	<div class='nav'>
		<div class='nav-left'>
			<span>成绩管理系统</span>
		</div>
		<%@ include file="headbar.jsp" %>
	</div>
	<div class="container1">
		<!-- 功能菜单区 -->
		<div class="menu-area">
			<ul>
				<p><a class="user-name">${username }</a>，您好</p>
				<li>
					<div>
						<button type="button" class="btn btn-default">
							<span class="glyphicon glyphicon-briefcase"></span>
						</button> 
						<a class="safe-a" href="exit">点击退出</a>
					</div>
				</li>
				<li>
					<div>
						<button type="button" class="btn btn-default">
							<span class="glyphicon glyphicon-star-empty"></span>
						</button> 
						<span class="safe-a " id="update-pwd">修改密码</span>
					</div>
				</li>
				<li>
					<div>
						<button type="button" class="btn btn-default">
							<span class="glyphicon glyphicon-tags"></span>
						</button> 
						<span class="safe-a" id="update-class">管理班级</span>
					</div>
				</li>
				<li>
					<div>
						<button type="button" class="btn btn-default">
							<span class="glyphicon glyphicon-th-large"></span>
						</button>
						<span class="safe-a" id="update-user">管理用户</span>
					</div>
				</li>
				<li>
					<div>
						<button type="button" class="btn btn-default">
							<span class="glyphicon glyphicon-tint"></span>
						</button> 
						<span class="safe-a" id="update-teacher">管理教师</span>
					</div>
				</li>
				<li>
					<div>
						<button type="button" class="btn btn-default">
							<span class="glyphicon glyphicon-leaf"></span>
						</button> 
						<span class="safe-a" id="update-student">管理学生信息</span>
					</div>
				</li>
				<!-- <li><span class="safe-a" id="update-studentScore">管理学生成绩表</span></li> -->
				<li>
					<div>
						<button type="button" class="btn btn-default">
							<span class="glyphicon glyphicon-hd-video"></span>
						</button> 
						<span class="safe-a" id="update-scoreList">管理考试场次</span>
					</div>
				</li>
				<li>
					<div>
						<button type="button" class="btn btn-default">
							<span class="glyphicon glyphicon-hd-video"></span>
						</button> 
						<span class="safe-a" id="update-log">日志信息</span>
					</div>
				</li>

			</ul>
		</div>
		<!-- 功能操作区 -->
		<div class="function-area">
			<!-- 1. 管理班级模块 -->
			<%@ include file="adminModels/classModel.jsp"%>
			<!-- 2. 管理用户模块 -->
			<%@ include file="adminModels/userModel.jsp"%>
			<!-- 3. 管理教师模块 -->
			<%@ include file="adminModels/teacherModel.jsp"%>
			<!-- 4. 管理学生信息模块 -->
			<%@ include file="adminModels/studentModel.jsp"%>
			<!-- 5. 管理学生成绩信息模块 -->
			<%@ include file="adminModels/studentScoreModel.jsp"%>
			<!-- 6. 管理考试场次模块 -->
			<%@ include file="adminModels/scoreListModel.jsp"%>
			<!-- 7. 日志模块 -->
			<%@ include file="adminModels/log.jsp"%>

			<!-- 静态包含修改密码的遮罩层 -->
			<%@ include file="updatePwd.jsp"%>
		</div>
		<%@ include file="footer.jsp"%>
		
	</div>
</body>
<script src="js/admin.js"></script>
<script>
	$(function() {
		var username = $(".user-name").html();
		$(".user-name").data("username", username);
	})
</script>
</html>