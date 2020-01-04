/*1. 为考试场次绑定change事件*/
$(".container1 .right .opt select").change(function() {
	doGetScoreObjects();
});
$("#stu-search-btn").click(function(){
	doGetScoreObjects();
});

function doGetScoreObjects(){
	var scoreId = $("[name='score-id']").val();
	// 获取数据
	// var scoreId = $(this).val();
	var stuId = $("#stu-id").val();
	console.log("成绩id===>" + scoreId);
	if (scoreId == "all") {
		console.log("查询所有成绩信息");
		// 查询全部信息
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : 'queryAllScore',
			data : {
				"stuId":stuId,
			},
			success : function(scoreList) {
				setResult2(scoreList);
			}
		});
	} else {
		// 查询某个场次的成绩信息
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : 'queryScore',
			data : {
				"stuId":stuId,
				"scoreId" : scoreId
			},
			success : function(score) {
				console.log("学生已成功查询成绩" + score.chineseScore);
				setResult(score);
			}
		});
	}
}
// 功能1的函数
function setResult(score){
	// 1. 先清空列表
	$(".table1").html("");
	// 2. 加表头
	$(".table1").append("<div class='row'>"+
							"<div class='col'>考试场次</div>"+
							"<div class='col'>语文</div>"+
							"<div class='col'>数学</div>"+
							"<div class='col'>英语</div>"+
							"<div class='col'>物理</div>"+
							"<div class='col'>化学</div>"+
							"<div class='col'>生物</div>"+
							"<div class='col'>历史</div>"+
							"<div class='col'>地理</div>"+
							"<div class='col'>政治</div>"+
							"<div class='col'>总分</div>"+
					  "</div>");
	// 3. 填充表体
	var total = score.mathScore+score.englishScore+score.physicsScore+score.physicsScore+score.chemistryScore+
	score.biologyScore+score.historyScore+score.geographyScore+score.politicsScore;
	$(".table1").append("<div class='row'>"+
							"<div class='col'>"+score.scoreId+"</div>"+
							"<div class='col'>"+score.chineseScore+"</div>"+
							"<div class='col'>"+score.mathScore+"</div>"+
							"<div class='col'>"+score.englishScore+"</div>"+
							"<div class='col'>"+score.physicsScore+"</div>"+
							"<div class='col'>"+score.chemistryScore+"</div>"+
							"<div class='col'>"+score.biologyScore+"</div>"+
							"<div class='col'>"+score.historyScore+"</div>"+
							"<div class='col'>"+score.geographyScore+"</div>"+
							"<div class='col'>"+score.politicsScore+"</div>"+
							"<div class='col'>"+total+"</div>"+
					   "</div>");
	
}

function setResult2(scoreList){
	// 1. 先清空列表
	$(".table1").html("");
	// 2. 加表头
	$(".table1").append("<div class='row'>"+
							"<div class='col'>考试场次</div>"+
							"<div class='col'>语文</div>"+
							"<div class='col'>数学</div>"+
							"<div class='col'>英语</div>"+
							"<div class='col'>物理</div>"+
							"<div class='col'>化学</div>"+
							"<div class='col'>生物</div>"+
							"<div class='col'>历史</div>"+
							"<div class='col'>地理</div>"+
							"<div class='col'>政治</div>"+
							"<div class='col'>总分</div>"+
					  "</div>");
	// 3. 填充表体
	$.each(scoreList,function(index,score){
		var total = score.mathScore+score.englishScore+score.physicsScore+score.physicsScore+score.chemistryScore+
		score.biologyScore+score.historyScore+score.geographyScore+score.politicsScore;
		$(".table1").append("<div class='row'>"+
							"<div class='col'>"+score.scoreId+"</div>"+
							"<div class='col'>"+score.chineseScore+"</div>"+
							"<div class='col'>"+score.mathScore+"</div>"+
							"<div class='col'>"+score.englishScore+"</div>"+
							"<div class='col'>"+score.physicsScore+"</div>"+
							"<div class='col'>"+score.chemistryScore+"</div>"+
							"<div class='col'>"+score.biologyScore+"</div>"+
							"<div class='col'>"+score.historyScore+"</div>"+
							"<div class='col'>"+score.geographyScore+"</div>"+
							"<div class='col'>"+score.politicsScore+"</div>"+
							"<div class='col'>"+total+"</div>"+
					   "</div>");
	});
}

// 修改密码
$("#update-pwd").click(function(){
	$(".bg").css("display","block");
});
