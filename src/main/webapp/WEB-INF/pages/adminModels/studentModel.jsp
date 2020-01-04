<%@page pageEncoding="UTF-8"%>
<div class="student-model">
	<!-- 
	<button id='add-student' class='btn'>点击添加/修改用户</button>
	<input type='text' name='stuId' placeholder="学号">
	<input type='text' name='name' placeholder="学生姓名">
	<input type='text' name='classId' placeholder="班级">
	<input type='text' name='age' placeholder="年龄">
	<input type='text' name='gender' placeholder="性别">
	 -->
	<div class='container1'>
		<div id='student-show-up-data'>
			<br>
			<div class='col-sm-12' style='margin-bottom: 20px; margin-top: 20px;'>
				<input type="text" id='student-up-stuId' class='col-sm-12 form-control' placeholder="学号" /> 
				<input type="text" id='student-up-name' class='col-sm-12 form-control' placeholder="学生姓名" /> 
				<input type="text" id='student-up-class' class='col-sm-12 form-control' placeholder="班级" /> 
				<input type="text" id='student-up-age' class='col-sm-12 form-control' placeholder="年龄" /> 
				<input type="text" id='student-up-gender' class='col-sm-12 form-control' placeholder="性别" /> 
			</div>
			<br>
			<div class='col-sm-8' style='margin-right: 50px;'></div>
			<button id='student-model-save-btn' type='button' class='btn btn-info'>保存</button>
			<button id='student-model-cancel-btn' type='button' class='btn btn-info'>取消</button>
		</div>
		<div class='container'>
			<div class='row' style='border-bottom:2px solid rgb(234, 236, 239); height: 50px; line-height: 50px; text-align:center;'>
				<div class='col'>序号</div>
				<div class='col'>学号</div>
				<div class='col'>姓名</div>
				<div class='col'>班级</div>
				<div class='col'>年龄</div>
				<div class='col'>性别</div>
				<div class='col'>
					<button id='student-model-update-btn' type='button' class='btn btn-warning'>更新数据</button>
				</div>
			</div>
		</div>
		<div class="table container">
			<!-- 此处由js生成数据 -->
		</div>
	</div>
</div>
<script>
$(function(){
	$("#student-model-update-btn").click(function(){
		$("#student-show-up-data").show();
	});
	$("#student-model-cancel-btn").click(function(){
		$("#student-show-up-data").hide();
	})
})
</script>