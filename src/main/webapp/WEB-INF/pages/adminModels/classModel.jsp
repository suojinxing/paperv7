<%@page pageEncoding="UTF-8"%>
<!-- 引入该模块的css -->

<!-- 引入该模块的js -->
<!-- 班级管理的模块 -->
<div class="class-model">
	<br>
	<button id='class-model-update-btn' type='button' class='btn btn-warning right'>更新数据</button>
	<div id='show-up-data'>
		<br>
		<div class='col-sm-12' style='margin-bottom: 20px; margin-top: 20px;'>
			<input type="text" id='up-id' class='col-sm-12 form-control' placeholder="班级id" /> 
			<input type="text" id='up-headmasterId' class='col-sm-12 form-control' placeholder="班主任id" /> 
			<input type="text" id='up-headmasterName' class='col-sm-12 form-control' placeholder="班主任姓名" /> 
			<input type="text" id='up-classSize' class='col-sm-12 form-control' placeholder="班级人数" /> 
			<input type="text" id='up-liberalOrSciences' class='col-sm-12 form-control' placeholder="班级性质" />
		</div>
		<br>
		<div class='col-sm-10' style='margin-right: 50px;'></div>
		<button id='class-model-save-btn' type='button' class='btn btn-info'>保存</button>
		<button id='class-model-cancel-btn' type='button' class='btn btn-info'>取消</button>
	</div>


	<!-- <button id='refresh-class-model' class='class-model-btn'>刷新页面</button>
	<div id='class-model-div' ></div> -->
	<!-- 显示一张表格,有无列 -->
	<!-- classId,headMasterId,headMasterName,classSize,liberalOrSciences -->
	<div class="table1">
		<div class='container'>
			<div class='row'>
				<div class='col-2'></div>
				<div class='col-2'></div>
				<div class='col-2'></div>
				<div class='col-2'></div>
				<div class='col-2'></div>
				<div class='col-2'></div>
			</div>
		</div>
		<!-- 此处由js生成表格 -->
	</div>
</div>
<script>
	$(function() {
		$("#show-up-data").css({
			"display":"none"
		})
		
		// 点击按钮时显示更新数据层
		$("#class-model-update-btn").click(function() {
			$("#show-up-data").css({
				"display":"block"
			})
		});

		// 点击取消时关闭更新数据层
		$("#class-model-cancel-btn").click(function() {
			$("#show-up-data").css({
				"display":"none"
			})
		});
	});
</script>