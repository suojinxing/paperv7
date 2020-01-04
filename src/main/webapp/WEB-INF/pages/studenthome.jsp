<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/reset.css" />
<link rel="stylesheet" type="text/css" href="css/studenthome.css" />
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="js/jquery-1.8.3.min.js"></script>
<title>学生个人主页</title>
</head>
<body>
	<div class='nav'>
		<div class='nav-left'>
			<span>成绩管理系统</span>
		</div>
		<%@ include file="headbar.jsp" %>
	</div>
	<div class="container1">
		<div class="left_bar">
			<ul>
				<p>你好，<a class="user-name">${student.name }同学</a>
				<input id='stu-id' type="hidden" value='${student.stuId }'> 
				</p>
				<li>
					<div>
						<button type='button' class='btn btn-default'>
							<span class='glyphicon glyphicon-briefcase'></span>
						</button> 
						<a class="safe-a" href="exit">点击退出</a>
					</div>	
				</li>
				<li>
					<div>
						<button type='button' class='btn btn-default'>
							<span class='glyphicon glyphicon-star-empty'></span>
						</button> 
						<span class="safe-a" id="update-pwd">修改密码</span>
					</div>		
				</li>
				<li>
					<div>
						<button type='button' class='btn btn-default'>
							<span class='glyphicon glyphicon-tags'></span>
						</button> 
						<span class="safe-a">帮&nbsp;助</span>
					</div>
				</li>
				<li>
					<div>
						<button type='button' class='btn btn-default'>
							<span class='glyphicon glyphicon-th-large'></span>
						</button>
						<a class="safe-a" href="###">功能反馈</a>
					</div>
				</li>
			</ul>
		</div>
		<div class="right">
			<div class="opt row">
				<!-- 功能选择区 -->
				<!-- 功能1.查看成绩 -->
				<div class='col'>考试场次</div>
				<div class='col'>
					<select name='score-id'>
						<option value='all'>全部</option>
						<c:forEach items="${scoreLists }" var="scoreList">
							<option value='${scoreList.scoreId }'>${scoreList.scoreId }</option>
						</c:forEach>
					</select>
				</div>
				<div class='col'>
					<div id='stu-search-btn'>查询成绩</div>
				</div>
			</div>
			<div class="result">
				<div class="table1 container">
					<div class='row'>
						<div class="col">考试场次</div>
						<div class="col">语文</div>
						<div class="col">数学</div>
						<div class="col">英语</div>
						<div class="col">物理</div>
						<div class="col">化学</div>
						<div class="col">生物</div>
						<div class="col">历史</div>
						<div class="col">地理</div>
						<div class="col">政治</div>
						<div class="col">总分</div>
					</div>
				</div>
			</div>
			<div style='width=100%;height:1px; box-shadow: 2px 2px 5px grey;'></div>
		</div>
		<%@ include file='footer.jsp'%>
	</div>
	<!-- 静态包含修改密码的遮罩层 -->
	<%@ include file="updatePwd.jsp"%>
</body>
<script type="text/javascript">
$(function(){
	$(".user-name").data("username",$("#stu-id").val());
	console.log("用户名" + $("#stu-id").val())
	loadHeadImg(); // 加载头像
});
function loadHeadImg(){
	var url = "/user/initHeadImg"; // 初始化头像
	var userId = $("#stu-id").val();
	var data = {
		"userId":userId
	};
	$.getJSON(url,data,function(result){
		console.log("headerbar.jsp==>成功加载头像")
		if(result.state == 1){
			console.log(result.data)
			var headImage = result.data;
			$("#head-img").attr("src","http://image.score/" + headImage);
		}
	})
}
</script>
<script src="js/studenthome.js"></script>
</html>