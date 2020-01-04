<%@page pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<link rel='stylesheet' type='text/css' href='css/reset.css' />
<link rel='stylesheet' type='text/css' href='css/teacherhome.css' />
<link rel='stylesheet' type='text/css' href='bootstrap/css/bootstrap.min.css' />
<link rel='stylesheet' href='https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css'>
<script src='js/jquery-1.8.3.min.js'></script>

<title>教师个人主页</title>
</head>
<body>
	<div class='nav'>
		<div class='nav-left'>
			<span>成绩管理系统</span>
		</div>
		<%@ include file='headbar.jsp' %>
	</div>
	<div class='container1'>
		<div class='left_bar'>
			<ul>
				<p>你好，<a class='user-name'>${teacher.name }老师</a>
				<input type='hidden' id='teacher-id' value='${teacher.teacherId }'>
				</p>
				<li>
					<div>
						<button type='button' class='btn btn-default'>
							<span class='glyphicon glyphicon-briefcase'></span>
						</button> 
						<a class='safe-a' href='/doLogout'>点击退出</a>
					</div>
				</li>
				<li>
					<div>
						<button type='button' class='btn btn-default'>
							<span class='glyphicon glyphicon-star-empty'></span>
						</button> 
						<span class='safe-a' id='update-pwd'>修改密码</span>
					</div>
				</li>
				<li>
					<div>
						<button type='button' class='btn btn-default'>
							<span class='glyphicon glyphicon-star-empty'></span>
						</button> 
						<span class='safe-a' id='teacher-export-excel'>导出成绩为Excel</span>
					</div>
				</li>
				<li>
					<div>
						<button type='button' class='btn btn-default'>
							<span class='glyphicon glyphicon-tags'></span>
						</button> 
						<a class='safe-a' id='edit-status' href='###'>编辑状态</a>
					</div>
				</li>
				<li>
					<div>
						<button type='button' class='btn btn-default'>
							<span class='glyphicon glyphicon-th-large'></span>
						</button>
						<span class='safe-a' >帮&nbsp;助</span>
					</div>		
				</li>
				<li>
					<div>
						<button type='button' class='btn btn-default'>
							<span class='glyphicon glyphicon-tint'></span>
						</button> 
						<a class='safe-a' href='###'>功能反馈</a>
					</div>		
				</li>
			</ul>
		</div>
		<div class='right'>
			<div class='opt'>
				<ul class='row'>
					<li class='col'>
						<div>
							<p class='row1'>班级</p>
							<select class='class_id' name='class_id'>
								<option>-请选择-</option>
								<c:forEach items='${classToTeacherList }' var='classToTeacher'>
									<option value='${classToTeacher.classId }'>${classToTeacher.classId }班</option>
								</c:forEach>
							</select>
						</div>
					</li>
					<li class='col'>
						<div>
							<p class='row1'>科目</p>
							<p class='row2' id='teaching'>${teacher.teaching }</p>
						</div>
					</li class='col'>
					<li>
						<div>
							<p class='row1'>考试id</p>
							<select class='score_id' name='score_id' style='text-indent: 10px;'>
								<c:forEach items='${scoreLList }' var='scoreList'>
									<option value='${scoreList.scoreId }'>${scoreList.scoreId }</option>
								</c:forEach>
							</select>
						</div>
					</li>
					<li class='col'>
						<div>
							<p class='row1' id='search-score'>查询成绩</p>
							<p class='row2' id='save'>
								<span>保存</span>
							</p>
						</div>
					</li>
				</ul>
			</div>
			<div class='score-info '>
				<div id='table' class='container'>
					<div id='score-headline' class='row'>
						<div class='col'>序号</div>
						<div class='col'>姓名</div>
						<div class='col stu-no'>学号</div>
						<div class='col'>语文</div>
						<div class='col'>数学</div>
						<div class='col'>英语</div>
						<div class='col score1'>***</div>
						<div class='col score2'>***</div>
						<div class='col score3'>***</div>
					</div>
				</div>
			</div>
			<!-- 静态包含修改密码的遮罩层 -->
			<%@ include file='updatePwd.jsp' %>
		</div>
		<%@ include file='footer.jsp'%>
	</div>
	
</body>
<script>
	$('.user-name').data('username',$('#teacher-id').val());
	$("#teacher-export-excel").click(doExportScoreExcel);
	function doExportScoreExcel(){
		var classId = $("[name='class_id']").val();
		var scoreId = $("[name='score_id']").val();
		// alert(classId + "," + scoreId);
		if(classId=='-请选择-'){
			alert("请选择班级");
			return ;
		}
		var data = {"classId":classId,"scoreId":scoreId}
		// $.post("/excel/export",data);
		location.href="/excel/export?classId=" + classId + "&scoreId=" + scoreId; 
	}
</script>
<script src='js/teacherhome.js'></script>
</html>