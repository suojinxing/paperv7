/* 1........ */ 
//功能1. 点击修改密码=>出现遮罩层
$("#update-pwd").click(function(){
	$(".bg").css("display","block");
});

$(function(){
	selectedMenuHighLight();
})

// 打开当前页面
function doOpenNowPage(modelClassName){
	// 2.1先显示遮罩层,
	$(".class-model").css({
		"display":"none"
	});
	$(".user-model").css({
		"display":"none"
	});
	$(".teacher-model").css({
		"display":"none"
	});
	$(".student-model").css({
		"display":"none"
	});
	$("studentScore-model").css({
		"display":"none"
	});
	$(".scoreList-model").css({
		"display":"none"
	});
	$(".log-model").css({
		"display":"none"
	});
	if(modelClassName == "class-model"){
		$(".class-model").css({
			"display":"block",
		});
	}
	if(modelClassName == "user-model"){
		$(".user-model").css({
			"display":"block"
		});
	}
	if(modelClassName == "teacher-model"){
		$(".teacher-model").css({
			"display":"block"
		});
	}
	if(modelClassName == "student-model"){
		$(".student-model").css({
			"display":"block"
		});
	}
	if(modelClassName == "studentScore-model"){
		$(".studentScore-model").css({
			"display":"block"
		});
	}
	if(modelClassName == "scoreList-model"){
		$(".scoreList-model").css({
			"display":"block"
		});
	}
	if(modelClassName == "log-model"){
		$(".log-model").css({
			"display":"block"
		});
	}
}

// 选中的菜单高亮
function selectedMenuHighLight(){
	var status = 0;
	$(".menu-area ul li").each(function(){
		$(this).click(function(){
			$(".menu-area ul li").css({"background":"#4b545c"});
			$(this).css({"background":"#616469"});
		});
	})
}

// 功能2: 点击管理班级时发送ajax请求
$("#update-class").click(function(){
	//$(this).parent().css({"background":"#616469"})
	
	doOpenNowPage("class-model");
	// 发送ajax请求数据,刷新班级管理模块页面
	doGetClassObjects();
});

// 功能2.2() 的实现,刷新页面
function doGetClassObjects(){
	// 2.2发送ajax请求
	$.ajax({
		type:"post",
		dataType:"json",
		url:"findClass",
		data:{ },
		success:function(result){
			// 2.3 调用函数完成对模块的赋值
			if(result.state==1){
				setClassesInfo(result.data);
			}else{
				alert(result.message);
			}
		}
	});
}

// 功能2.3() 函数2.3的实现
function setClassesInfo(classesList){
	// 1. 先清空列表
	$(".class-model .table1").html("");
	// 2. 加表头
	$(".class-model .table1").append("<div class='container table-striped'>");
	$(".class-model .table1").append("<div class='row'>"+
			"<div class='col-2'>班级id</div>"+
			"<div class='col-2'>班主任姓名</div>"+
			"<div class='col-2'>班主任id</div>"+
			"<div class='col-2'>班级人数</div>"+
			"<div class='col-2'>班级性质</div>"+
			"<div class='col-2'></div>"+
		"</div>");
	// 3. 加表体
	$.each(classesList,function(index,classes){
		$(".class-model .table1").append("<div class='row'>"+
				"<div class='col-2'>"+classes.classId+"</div>"+
				"<div class='col-2'>"+classes.headmasterName+"</div>"+
				"<div class='col-2'>"+classes.headmasterId+"</div>"+
				"<div class='col-2'>"+classes.classSize+"</div>"+
				"<div class='col-2'>"+classes.liberalOrSciences+"</div>"+
				"<div class='col-2'><a href=\"javascript:deleteClassFromClassModel('"+classes.classId+"');\">删除</a></div>"+
			"</div>");
	});
	
	// 加表尾
	$(".class-model .table1").append("</div>");
}

// 功能5: 点击修改数据按钮,根据id更新当前数据,修改或增加
$("#class-model-save-btn").click(function(){
	// 5.1 获取修改域中文本框的值
	var id = $("#up-id").val();
	var headmasterId = $("#up-headmasterId").val();
	var headmasterName = $("#up-headmasterName").val();
	var classSize = $("#up-classSize").val();
	var liberalOrSciences = $("#up-liberalOrSciences").val();
	
	// 5.2 发送ajax请求
	$.ajax({
		type:"post",
		dataType:"json",
		url:"updateClassModel",
		data:{
			"id": id,
			"headmasterId": headmasterId,
			"headmasterName": headmasterName,
			"classSize": classSize,
			"liberalOrSciences": liberalOrSciences
		},
		success:function(result){
			if(result.state==1){
				doGetClassObjects();
				$("#show-up-data").hide();
			}else{
				alert(result.message);
			}
		}
	});
});
$("#update-class-model").click(function(){
	// 5.1 获取修改域中文本框的值
	var id = $("#up-id").val();
	var headmasterId = $("#up-headmasterId").val();
	var headmasterName = $("#up-headmasterName").val();
	var classSize = $("#up-classSize").val();
	var liberalOrSciences = $("#up-liberalOrSciences").val();
	
	// 5.2 发送ajax请求
	$.ajax({
		type:"post",
		dataType:"json",
		url:"updateClassModel",
		data:{
			"id": id,
			"headmasterId": headmasterId,
			"headmasterName": headmasterName,
			"classSize": classSize,
			"liberalOrSciences": liberalOrSciences
		},
		success:function(result){
			if(result.state==1){
				doGetClassObjects();
			}else{
				alert(result.message);
			}
		}
	});
});

function deleteClassFromClassModel(classId){
	var d = confirm("确定删除吗?")
	if(d){
		$.ajax({
			type:'post',
			dataType:'json',
			url:'deleteClassById',
			data:{
				"classId":classId
			},
			success:function(result){
				alert(result.message);
				if(result.state==1){
					doGetClassObjects();
				}
			}
		});
	}
}

/**-------------2. 管理用户模块---------------**/
// 功能1 : 点击管理用户按钮显示功能区
$("#update-user").click(function(){
	doOpenNowPage("user-model");
	// 加载信息,依赖于功能2
	doGetUserObjects();
});

// 功能2 : 查询数据点击用户按钮的时候加载用户信息并展示
function doGetUserObjects(){
	var pageCurrent = $("#pageId").data("pageCurrent");
	console.log("admin.js==doGetUserObjects==>" + pageCurrent);
	if(!pageCurrent){
		pageCurrent = 1;
		//$(".pageCurrent").html("当前页（1）");
		//$("#pageId").data("pageCurrent", 1);
	}
	var params = {"pageCurrent":pageCurrent}
	$.getJSON("findUser",params,function(result){
		if(result.state == 1){
			setUserInfo(result.data.records,result.data.pageCurrent);
		}else{
			alert(result.message);
		}
	})
}

// 2.1 填充数据
function setUserInfo(userList,pageCurrent){
	// 1. 先清空列表
	$(".user-model .table").html("");
	// 2. 加表头
	// 3. 填充表体
	$.each(userList,function(index,user){
		var username_ = user.username;
		var ind = (index + 1 + ((pageCurrent-1)*14));
		$(".user-model .table").append("<div class='row'>"+
								"<div class='col'>"+ind+"</div>"+
								"<div class='col'>"+user.username+"</div>"+
								"<div class='col'>"+user.password+"</div>"+
								"<div class='col'>"+user.status+"</div>"+
								"<div class='col'><a href=\"javascript:deleteUserFromUserModel('"+username_+"');\">删除</a></div>"+
						   "</div>");
	});
	
}

// 功能2.1 中的函数
function deleteUserFromUserModel(username){
	// ind为序号，判断是不是某一页的最后一条数据，并判断总数是不是/pageSize余1
	var d = confirm("确定删除该用户吗?不可恢复");
	if(d){
		var params = {"username":username};
		$.getJSON("deleteUserFromUserModel",params,function(result){
			if(result.state==1){
				doGetUserObjects();
			}
		})
	}
}

// 功能3: 点击添加用户按钮,发送请求,添加用户信息
$(".user-model #user-model-save-btn").click(function(){
	// 1. 获取文本框中用户的数据
	var username = $("#user-up-username").val();
	var password = $("#user-up-password").val();
	var status = $("#user-up-status").val();
	console.log(username);
	console.log(password);
	console.log(status);
	$.ajax({
		type:'post',
		dataType:'json',
		url:'updateUserForUserModel',
		data:{
			"username":username,
			"password":password,
			"status":status
		},
		success:function(result){
			if(result.state==1){
				// ok  更新数据
				doGetUserObjects();
				$("#user-show-up-data").hide();
			}
			alert(result.message);
		}
	});
});

/*-----------3. 管理教师模块----------------*/
// 功能1. 点击管理教师按钮,显示其遮罩层,隐藏其他遮罩层
$("#update-teacher").click(function(){
	doOpenNowPage("teacher-model");
	// 加载信息,依赖于功能2
	doGetTeacherObjects();
});
// 功能2: 刷新信息
function doGetTeacherObjects(){
	$.ajax({
		type:'post',
		dataType:'json',
		url:'findTeacher',
		data:{},
		success:function(result){
			if(result.state==1){
				setTeacherInfo(result.data);
			}else{
				alert(result.message);
			}
		}
	});
}
// 
function setTeacherInfo(teacherList){
	console.log("教师模块的数据加载成功!")
	// 1. 先清空列表
	$(".teacher-model .table").html("");
	// 2. 加表头
	// 3. 填充表体
	$.each(teacherList,function(index,teacher){
		var teacherId = teacher.teacherId;
		$(".teacher-model .table").append("<div class='row'>"+
								"<div class='col'>"+(index + 1)+"</div>"+
								"<div class='col'>"+teacher.name+"</div>"+
								"<div class='col'>"+teacher.teacherId+"</div>"+
								"<div class='col'>"+teacher.age+"</div>"+
								"<div class='col'>"+teacher.salary+"</div>"+
								"<div class='col'>"+teacher.gender+"</div>"+
								"<div class='col'>"+teacher.teaching+"</div>"+
								"<div class='col'><a href=\"javascript:deleteTeacherFromTeacherModel('"+teacherId+"');\">删除</a></div>"+
						   "</div>");
	});
}
function deleteTeacherFromTeacherModel(teacherId){
	var d = confirm("确认删除该教师信息吗?不可恢复");
	if(d){
		$.ajax({
			type:'post',
			dataType:'json',
			url:'deleteTeacherFromTeacherModel',
			data:{
				"teacherId":teacherId
			},
			success:function(result){
				if(result.state==1){
					doGetTeacherObjects();
				}
				alert(result.message);
			}
		});
	}
}

// 功能3: 添加或修改教师信息
$(".teacher-model #teacher-model-save-btn").click(function(){
	var teacherName = $("#teacher-up-name").val();
	var teacherId = $("#teacher-up-id").val();
	var teacherAge = $("#teacher-up-age").val();
	var teacherSalary = $("#teacher-up-salary").val();
	var teacherGender = $("#teacher-up-gender").val();
	var teacherTeaching = $("#teacher-up-teaching").val();
	$.ajax({
		type:'post',
		dataType:'json',
		url:'updateTeacherInfo',
		data:{
			"teacherName":teacherName,
			"teacherId":teacherId,
			"teacherAge":teacherAge,
			"teacherSalary":teacherSalary,
			"teacherGender":teacherGender,
			"teacherTeaching":teacherTeaching
		},
		success:function(result){
			alert(result.message);
			if(result.state==1){
				doGetTeacherObjects();
				$("#teacher-show-up-data").hide();
			}
		}
	});
});

/*-------------------4. 管理学生信息模块-----------------------*/
// 功能1. 显示该模块的遮罩层
$("#update-student").click(function(){
	doOpenNowPage("student-model");
	// 加载信息,依赖于功能2
	doGetStudentObjects();
});
function doGetStudentObjects(){
	$.getJSON("findStudent",function(result){
		if(result.state==1){
			setStudentInfo(result.data);
		}else{
			alert("1发生未知错误" + result.message);
		}
	})
}
function setStudentInfo(studentList){
	// 1. 先清空列表
	$(".student-model .table").html("");
	// 2. 加表头
	// 3. 填充表体
	$.each(studentList,function(index,student){
		$(".student-model .table").append("<div class='row'>"+
								"<div class='col'>"+(index + 1)+"</div>"+
								"<div class='col'>"+student.stuId+"</div>"+
								"<div class='col'>"+student.name+"</div>"+
								"<div class='col'>"+student.classId+"</div>"+
								"<div class='col'>"+student.age+"</div>"+
								"<div class='col'>"+student.gender+"</div>"+
								"<div class='col'><a href=\"javascript:deleteStudentFromStudentModel('"+student.stuId+"');\">删除</a></div>"+
						   "</div>");
	});
}

// 功能2. 添加或修改学生的信息
$(".student-model #student-model-save-btn").click(function(){
	var stuId = $("#student-up-stuId").val();
	var name = $("#student-up-name").val();
	var classId = $("#student-up-class").val();
	var age = $("#student-up-age").val();
	var gender = $("#student-up-gender").val();
	$.ajax({
		type: 'post',
		dataType : 'json',
		url:'updateStudentInfo',
		data:{
			"stuId":stuId,
			"name":name,
			"classId":classId,
			"age":age,
			"gender":gender
		},
		success:function(result){
			if(result.state==1){
				doGetStudentObjects(); // 更新数据
				$("#student-show-up-data").hide();
			}
				alert(result.message);
		}
	});
});

// 功能3: 删除学生信息
function deleteStudentFromStudentModel(stuId){
	var d = confirm("确认删除吗?删除后联动该学生的成绩信息也会删除.不可恢复");
	if(d){
		$.ajax({
			type:'post',
			dataType:'json',
			url:'deleteStudentFromStudentModel',
			data:{
				"stuId":stuId
			},
			success:function(result){
				if(result.state==1){
					if(result.state==1){
						doGetStudentObjects(); // 更新数据
					}else{
						alert(result.message);
					}
				}
			}
		});
	}
}

/**-------------6.管理考试场次模块----------------------**/
//功能1. 点击管理学生成绩按钮,显示遮罩层
//功能1. 显示该模块的遮罩层
$("#update-scoreList").click(function(){
	doOpenNowPage("scoreList-model");
	// 加载信息,依赖于功能2
	doGetScoreListObjects();
});
function doGetScoreListObjects(){
	$.ajax({
		type:'post',
		dataType:'json',
		url:'findScoreList',
		data:{},
		success:function(result){
			if(result.state==1){
				setScoreListInfo(result.data);
			}else{
				alert(result.message);
			}
		}
	});
}
function setScoreListInfo(ScoreList){
	// 1. 先清空列表
	$(".scoreList-model .table").html("");
	// 2. 加表头
	// 3. 填充表体
	$.each(ScoreList,function(index,scoreList){
		$(".scoreList-model .table").append("<div class='row'>"+
								"<div class='col'>"+(index + 1)+"</div>"+
								"<div class='col'>"+scoreList.scoreId+"</div>"+
								"<div class='col'><a href=\"javascript:deleteScoreId('"+scoreList.scoreId+"');\">删除</a></div>"+
						   "</div>");
	});
}

// 功能2. 添加考试场次
$(".scoreList-model #scoreList-model-save-btn").click(function(){
	var scoreId = $("#scoreList-up-id").val();
	console.log("添加的考试id为: " + scoreId);
	$.ajax({
		type:'post',
		dataType:'json',
		url:'addScoreList',
		data:{
			"scoreId":scoreId
		},
		success:function(result){
			if(result.state==1){
				doGetScoreListObjects();
				$("#scoreList-show-up-data").hide();
			}
			alert(result.message);
		}
	});
});
// 功能3: 删除scoreId
function deleteScoreId(scoreId){
	var d = confirm("确认删除该考试编号吗?删除后该场次的所有考试成绩也将删除.不可恢复")
	if(d){
		$.ajax({
			type:'post',
			dataType:'json',
			url:'deleteScoreId',
			data:{
				"scoreId":scoreId
			},
			success:function(result){
				alert(result.message);
				if(result.state==1){
					doGetScoreListObjects();
				}
			}
		});
	}
}
/**-------------7.管理日志模块----------------------**/
$("#update-log").click(function(){
	doOpenNowPage("log-model");
	// 加载信息,依赖于功能2
	doGetLogObjects2();
});

function doGetLogObjects2(){
	$.ajax({
		type:'post',
		dataType:'json',
		url:'findLog',
		data:{},
		success:function(result){
			if(result.state==1){
				console.log(result)
				setLogInfo(result.data.records);
			}else{
				alert(result.message);
			}
		}
	});
}
function setLogInfo(logList){
	// 1. 先清空列表
	$(".log-model .table").html("");
	// 2. 加表头
	// 3. 填充表体
	$.each(logList,function(index,log){
		$(".log-model .table").append("<div class='row'>"+
								"<div class='col-1'>"+(index + 1)+"</div>"+
								"<div class='col-1'>"+log.username+"</div>"+
								"<div class='col'>"+log.operation+"</div>"+
								"<div class='col'>"+log.method+"</div>"+
								"<div class='col-1'>"+log.params+"</div>"+
								"<div class='col-1'>"+log.time+"</div>"+
								"<div class='col-1'>"+log.ip+"</div>"+
								"<div class='col-3'>"+log.createdTime+"</div>"+
								"<div class='col-1'><a href=\"javascript:deleteLog('"+log.scoreId+"');\">删除</a></div>"+
						   "</div>");
	});
}
