<%@page pageEncoding="UTF-8"%>
<div class="scoreList-model">
	<!-- 
	<button id='add-scoreList' class='btn'>点击添加/修改用户</button>
	<input type='text' name='scoreId' placeholder="13151101">
	 -->
	 <div class='container1'>
		<div id='scoreList-show-up-data'>
			<div class='col-sm-12' style='margin-bottom: 20px; margin-top: 20px;'>
				<input type="text" id='scoreList-up-id' class='col-sm-12 form-control' placeholder="考试编号" /> 
			</div>
			<br>
			<div class='col-sm-8' style='margin-right: 50px;'></div>
			<button id='scoreList-model-save-btn' type='button' class='btn btn-info'>保存</button>
			<button id='scoreList-model-cancel-btn' type='button' class='btn btn-info'>取消</button>
		</div>
		<div class='container'>
			<div class='row' style='border-bottom:2px solid rgb(234, 236, 239); height: 50px; line-height: 50px; text-align:center;'>
				<div class='col'>序号</div>
				<div class='col'>考试编号</div>
				<div class='col'>
					<button id='scoreList-model-update-btn' type='button' class='btn btn-warning'>更新数据</button>
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
	$("#scoreList-model-update-btn").click(function(){
		$("#scoreList-show-up-data").show();
	});
	$("#scoreList-model-cancel-btn").click(function(){
		$("#scoreList-show-up-data").hide();
	})
})
</script>