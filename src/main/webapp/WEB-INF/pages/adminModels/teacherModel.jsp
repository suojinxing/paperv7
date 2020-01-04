<%@page pageEncoding="UTF-8"%>
<div class="teacher-model">
	<!-- 
	<button id='add-teacher' class='btn'>点击添加/修改用户</button>
	<input type='text' name='teacher-name' placeholder="教师名">
	<input type='text' name='teacher-id' placeholder="教师id">
	<input type='text' name='teacher-age' placeholder="教师年龄">
	<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type='text' name='teacher-salary' placeholder="教师薪资">
	<input type='text' name='teacher-gender' placeholder="教师性别">
	<input type='text' name='teacher-teaching' placeholder="教师教授科目">
	 -->
	<div class='container1'>
		<div id='teacher-show-up-data'>
			<div class='col-sm-12' style='margin-bottom: 20px; margin-top: 20px;'>
				<input type="text" id='teacher-up-name' class='col-sm-12 form-control' placeholder="教师姓名" /> 
				<input type="text" id='teacher-up-id' class='col-sm-12 form-control' placeholder="教师id" /> 
				<input type="text" id='teacher-up-age' class='col-sm-12 form-control' placeholder="教师年龄" /> 
				<input type="text" id='teacher-up-salary' class='col-sm-12 form-control' placeholder="教师薪资" /> 
				<input type="text" id='teacher-up-gender' class='col-sm-12 form-control' placeholder="性别" /> 
				<input type="text" id='teacher-up-teaching' class='col-sm-12 form-control' placeholder="授课科目" /> 
			</div>
			<br>
			<div class='col-sm-8' style='margin-right: 50px;'></div>
			<button id='teacher-model-save-btn' type='button' class='btn btn-info'>保存</button>
			<button id='teacher-model-cancel-btn' type='button' class='btn btn-info'>取消</button>
		</div>
		<div class='container'>
			<div class='row' style='border-bottom:2px solid rgb(234, 236, 239); height: 50px; line-height: 50px; text-align:center;'>
				<div class='col'>序号</div>
				<div class='col'>教师姓名</div>
				<div class='col'>教师id</div>
				<div class='col'>教师年龄</div>
				<div class='col'>教师薪资</div>
				<div class='col'>性别</div>
				<div class='col'>授课科目</div>
				<div class='col'>
					<button id='teacher-model-update-btn' type='button' class='btn btn-warning'>更新数据</button>
				</div>
			</div>
		</div>
		<div class="table">
			<!-- 此处由js生成表格 -->
		</div>
	</div>
</div>
<script>
$(function(){
	$("#teacher-model-update-btn").click(function(){
		$("#teacher-show-up-data").show();
	});
	$("#teacher-model-cancel-btn").click(function(){
		$("#teacher-show-up-data").hide();
	})
})
</script>