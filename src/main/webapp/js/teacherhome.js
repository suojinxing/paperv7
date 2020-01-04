/*功能3.设置编辑状态功能*/
function setEditStatus(){
	// 当成绩改变的时候提示未保存状态
	$("input:text").change(function(){
		$("#edit-status").html("未保存*");
	});
}

/*
 * 允许可编辑的成绩文本框
 * 为教师用户绑定事件,教师用户只能修改自己科目的成绩信息,别的成绩全部只读
 */
 var teaching_;
 function setEditClass(){
	var teaching = $("#teaching").html(); // 获取教书的科目
	teaching_ = teaching;
	console.log("教书的科目===>" + $(".row2").html());
	if(teaching == "语文"){
		$(".chinese").removeAttr("readonly");
		$(".chinese").addClass("canEdit");
	}else if(teaching == "数学"){
		$(".math").removeAttr("readonly");
		$(".math").addClass("canEdit");
	}else if(teaching == "英语"){
		$(".english").removeAttr("readonly");
		$(".english").addClass("canEdit");
	}else if(teaching == "物理"){
		$(".physics").removeAttr("readonly");
		$(".physics").addClass("canEdit");
	}else if(teaching == "化学"){
		$(".chemistry").removeAttr("readonly");
		$(".chemistry").addClass("canEdit");
	}else if(teaching == "生物"){
		$(".biology").removeAttr("readonly");
		$(".biology").addClass("canEdit");
	}else if(teaching == "政治"){
		$(".politics").removeAttr("readonly");
		$(".politics").addClass("canEdit");
	}else if(teaching == "地理"){
		$(".geography").removeAttr("readonly");
		$(".geography").addClass("canEdit");
	}else if(teaching == "历史"){
		$(".history").removeAttr("readonly");
		$(".history").addClass("canEdit");
	}
} 

/*
 * 功能5: 根据文理科添加学生的成绩
 * 1. 先清空当前成绩模块的信息
 * 2. 当选择班级时,会根据班级的id动态从数据库获取班级人员的姓名和成绩展示在模块中
 */
$(".class_id").change(function() {
	doGetStudentsNameAndScoreObjects();
});
$("#search-score").click(doGetStudentsNameAndScoreObjects);

function doGetStudentsNameAndScoreObjects(){
	var classId = $(".class_id").val();
	var scoreId = $(".score_id").val();
	console.log(classId);
	if(classId == '-请选择-'){
		alert("请先选择班级");
		return;
	}
	// 1.获取所选班级的所有学生信息,并将name,学号展示在模块中
	// 2.获取班级的文理科信息
	$.ajax({ 
		type : "POST",
		dateType : "json",
		url : "getStudentsNameAndScore",
		data : {
			"classId" : classId,
			"scoreId" : scoreId
		},
		success : function(infos) {
			// 判断班级文理科性质,,然后为成绩模块赋值
			console.log("班级性质: " + infos.classNature);
			var classNature = infos.classNature;
			if(infos.stuAndScoreInfo.length==0){
				$("#table").empty();
				$("#table").append("<div id='score-headline' class='row'>"+
										"<div class='col'>序号</div>"+
										"<div class='col'>姓名</div>"+
										"<div class='col stu-no'>学号</div>"+
										"<div class='col'>语文</div>"+
										"<div class='col'>数学</div>"+
										"<div class='col'>英语</div>"+
										"<div class='col score1'>***</div>"+
										"<div class='col score2'>***</div>"+
										"<div class='col score3'>***</div>"+
									"</div>");
				$("#table").append("<div id='score-headline' class='row'>"+
										"<div class='col' style='color:grey;'>还没有成绩记录</div>"+
								   "</div>");
				return;
			}
			if(classNature == "理科"){
				// 如果是理科班
				setSciencesScoreInfo(infos.stuAndScoreInfo);
			}else{
				// 如果是文科班
				setLiberalScoreInfo(infos.stuAndScoreInfo)
			}
		}
	});
}


/*
 * 功能5.1,该函数为功能5ajax的调用 //处理理科班信息
 * 去读students对象中的学生信息,遍历,并将信息添加到页面中
 */ 
function setSciencesScoreInfo(stuAndScore) {
	$("#table").empty();
	$("#table").append("<div id='score-headline' class='row'>"+
							"<div class='col'>序号</div>"+
							"<div class='col'>姓名</div>"+
							"<div class='col stu-no'>学号</div>"+
							"<div class='col'>语文</div>"+
							"<div class='col'>数学</div>"+
							"<div class='col'>英语</div>"+
							"<div class='col score1'>***</div>"+
							"<div class='col score2'>***</div>"+
							"<div class='col score3'>***</div>"+
						"</div>");
	$(".score1").html("物理");
	$(".score2").html("化学");
	$(".score3").html("生物");
	$.each(stuAndScore,function(index,stu){
		console.log("index==>"+index)
		$("#table").append(
				"<div class='row'>"+
				"		<div class='col'>"+(index+1)+"</div>"+
				"		<div class='col'>"+stu.name+"</div>"+
				"		<div class='col stu-no stu-no1'>"+stu.stuId+"</div>"+
				"		<div class='col'><input type='text' readonly='readOnly' class='txt-input chinese' name='chineseScore' value='"+stu.chineseScore+"'/></div>"+
				"		<div class='col'><input type='text' readonly='readOnly' class='txt-input math' name='mathScore' value='"+stu.mathScore+"'/></div>"+
				"		<div class='col'><input type='text' readonly='readOnly' class='txt-input english' name='englishScore' value='"+stu.englishScore+"'/></div>"+
				"		<div class='col'><input type='text' readonly='readOnly' class='txt-input physics' name='physicsScore' value='"+stu.physicsScore+"'/></div>"+
				"		<div class='col'><input type='text' readonly='readOnly' class='txt-input chemistry' name='chemistryScore' value='"+stu.chemistryScore+"'/></div>"+
				"		<div class='col'><input type='text' readonly='readOnly' class='txt-input biology' name='biologyScore' value='"+stu.biologyScore+"'/></div>"+
				"		<input type='hidden' name='totalScore' value='"+stu.totalScore+"'/>"+
				"</div>");
	});
	setEditClass(); // 设置只读属性==
	setEditStatus(); // 设置编辑状态==>功能3
}
/*
 * 功能5.2,该函数为功能5ajax的调用 //处文科班信息
 * 去读students对象中的学生信息,遍历,并将信息添加到页面中
 */ 
function setLiberalScoreInfo(stuAndScore){
	$("#table").empty();
	$("#table").append("<div id='score-headline' class='row'>"+
							"<div class='col'>序号</div>"+
							"<div class='col'>姓名</div>"+
							"<div class='col stu-no'>学号</div>"+
							"<div class='col'>语文</div>"+
							"<div class='col'>数学</div>"+
							"<div class='col'>英语</div>"+
							"<div class='col score1'>***</div>"+
							"<div class='col score2'>***</div>"+
							"<div class='col score3'>***</div>"+
						"</div>");
	$(".score1").html("政治");
	$(".score2").html("地理");
	$(".score3").html("历史");
	$.each(stuAndScore,function(index,stu){
		console.log("index==>"+index)
		$("#table").append(
				"<div class='row'>"+
				"	<div class='col'>"+(index+1)+"</div>"+
				"	<div class='col'>"+stu.name+"</div>"+
				"	<div class='col stu-no stu-no1'>"+stu.stuId+"</div>"+
				"	<div class='col'><input type='text' readonly='readOnly' class='txt-input chinese' name='chineseScore' value='"+stu.chineseScore+"'/></div>"+
				"	<div class='col'><input type='text' readonly='readOnly' class='txt-input math' name='mathScore' value='"+stu.mathScore+"'/></div>"+
				"	<div class='col'><input type='text' readonly='readOnly' class='txt-input english' name='englishScore' value='"+stu.englishScore+"'/></div>"+
				"	<div class='col'><input type='text' readonly='readOnly' class='txt-input politics' name='politicsScore' value='"+stu.politicsScore+"'/></div>"+
				"	<div class='col'><input type='text' readonly='readOnly' class='txt-input geography' name='geographyScore' value='"+stu.geographyScore+"'/></div>"+
				"	<div class='col'><input type='text' readonly='readOnly' class='txt-input history' name='historyScore' value='"+stu.historyScore+"'/></div>"+
				"	<input type='hidden' name='totalScore' value='"+stu.totalScore+"'/>"+
				"</div>");
	});
	setEditClass(); // 设置只读属性==
	setEditStatus(); // 设置编辑状态==>功能3
}

/*
 * 功能4: 实现保存功能,发送ajax请求,让后台保存前端提交的数据到数据库中
 */
 var scores = Array();
 var stuIds = Array();
 var oldTotalScore = Array();
 var scoreId = $(".score_id").val();
 $("#save").click(saveScore);	
 function saveScore(){
	 var editStatus = $("#edit-status").html();
	 if(editStatus != "编辑状态"){
		 // 1. 遍历input获取成绩的数据封装给变量
		 console.log("全局变量 ===> " + teaching_);
		 if(teaching_ == "语文"){
			 initData('chineseScore');
		 }else if(teaching_ == "数学"){
			 initData('mathScore');
		 }else if(teaching_ == "英语"){
			 initData('englishScore');
		 }else if(teaching_ == "物理"){
			 initData('physicsScore');
		 }else if(teaching_ == "化学"){
			 initData('chemistryScore');
		 }else if(teaching_ == "生物"){
			 initData('biologyScore');
		 }else if(teaching_ == "政治"){
			 initData('politicsScore');
		 }else if(teaching_ == "地理"){
			 initData('geographyScore');
		 }else if(teaching_ == "历史"){
			 initData('historyScore');
		 }
	  	 // 2. 发送ajax请求
		 $.ajax({ // 获取所选班级的所有学生信息,并将name,学号展示在模块中
			type : "GET",
			dateType : "json",
			url : "saveStuScore",
			data : {
				"scoreId":scoreId,
				"scores":scores,
				"stuIds":stuIds,
				"teaching":teaching_
			},
			success : function(result) {
				$("#edit-status").html("编辑状态");
				// 清空数组
				scores = Array();
				stuIds = Array();
				// 保存成功
				alert(result.message);
			}
		});
	 }else{
		 alert("没有更改成绩,无法执行保存操作!!");
	 }
 }
 
//功能4-1,功能4内调用的函数,用来初始化ajax发送的数据
 function initData(teaching){
	 console.log("成功调用!!!!!!!!");
	// scores = $("[name='biologyScore']").val();
	 // 有很多成绩,遍历成绩封装成数组
	 $("[name="+teaching+"]").each(function(index){
		  scores[index] = $(this).val();
	 });
	 // 封装学号
	 $(".stu-no1").each(function(index){
		 stuIds[index] = $(this).html();
	 });
	 scores = "scores:" + scores;
	 stuIds = "stuIds:" + stuIds;
	 oldTotalScore = "oldTotalScore:" + oldTotalScore;
	 console.log("scores ===>" + scores);
	 console.log("stuIds ===>" + stuIds);
 }
 
 /*
 // 功能4-1,功能4内调用的函数,用来初始化ajax发送的数据
 function initData(teaching){
	 console.log("成功调用!!!!!!!!");
	// scores = $("[name='biologyScore']").val();
	 // 有很多成绩,遍历成绩封装成数组
	 $("[name="+teaching+"]").each(function(index){
		 scores[index] = "" + $(this).val();
	 });
	 // 封装学号
	 $(".stu-no1").each(function(index){
		 stuIds[index] = "" + $(this).html();
	 });
	 // 封装总成绩
	 $("[name='totalScore']").each(function(index){
		 console.log("oldTotalScore+==> " + oldTotalScore)
		 console.log("oldTotalScore+==> " + $(this).val())
		 
		 oldTotalScore[index] = "" + $(this).val();
	 });
	 scores = "scores:" + scores;
	 stuIds = "stuIds:" + stuIds;
	 oldTotalScore = "oldTotalScore:" + oldTotalScore;
	 console.log("scores ===>" + scores);
	 console.log("stuIds ===>" + stuIds);
 }
 */
 // 功能5: 点击修改密码按钮,展示遮罩层
 $("#update-pwd").click(function(){
	 $(".bg").css("display","block");
 });