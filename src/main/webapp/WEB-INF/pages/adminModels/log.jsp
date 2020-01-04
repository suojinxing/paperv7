<%@page pageEncoding="UTF-8"%>
<div class="log-model">
	日志
	<div class='container1'>
		<div class='container'>
			<div class='row' style='border-bottom:2px solid rgb(234, 236, 239); height: 50px; line-height: 50px; text-align:center;'>
				<div class='col-1'>序号</div>
				<div class='col-1'>用户名</div>
				<div class='col-1'>操作</div>
				<div class='col'>方法</div>
				<div class='col-1'>参数</div>
				<div class='col-1'>操作时长</div>
				<div class='col'>ip</div>
				<div class='col-3'>创建时间</div>
			</div>
		</div>
		<div class="table container">
			<!-- 此处由js生成表格 -->
		</div>
		<div id="log-pageId" class="box-footer clearfix">
			<ul class="pagination">
				<li class='col'><a class="first1">首页</a></li>
				<li class='col'><a class="pre1">上一页</a></li>
				<li class='col'><a class="next1">下一页</a></li>
				<li class='col'><a class="last1">尾页</a></li>
				<li class='col'><a class="logRowCount">总记录数(0)</a></li>
				<li class='col'><a class="logPageCount">总页数(0)</a></li>
				<li class='col'><a class="logPageCurrent">当前页(1)</a></li>
			</ul>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			// 初始化页面数据
			doSetLogModelPageInitation();
			$("#log-pageId").on("click", ".first1,.pre1,.next1,.last1", doLogJumpToPage);
			// 更新数据的遮罩层↓↓↓↓↓↓↓↓↓↓↓↓
			// $("#log-model-update-btn").on("click",doShowUpPage);

			// 点击取消时，不展示遮罩层
			// $("#log-model-cancel-btn").click(function(){
			// 	$("#log-show-up-data").hide();
			// });
		});

		// function doShowUpPage(){
		// 	$("#log-show-up-data").show();
		// }

		// 分页处理↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		function doLogJumpToPage() {
			// 获取当前点击按钮的信息，为logPageCount赋值
			var logPageCurrent = $("#log-pageId").data("logPageCurrent"); // 获取当前页
			console.log("当前页: " + logPageCurrent)
			var logPageCount = $("#log-pageId").data("logPageCount"); // 获取总页数
			var cls = $(this).prop("class");
			if (cls == "first1") {
				// 如果点击首页并且
				logPageCurrent = 1;
			} else if (cls == "pre1" && logPageCurrent > 1) {
				// 点击上一页，如果当前页大于1
				logPageCurrent--;
			} else if (cls == "next1" && logPageCurrent < logPageCount) {
				// 点击下一页，且不是尾页时
				logPageCurrent++;
			} else if (cls == "last1") {
				logPageCurrent = logPageCount;
			} else {
				// 点击别处无反应
				return;
			}

			// 重新绑定数据
			$("#log-pageId").data("logPageCurrent", logPageCurrent);
			$("#log-pageId").data("logPageCount", logPageCount);

			// 根据logPageCount的数值进行查询
			doGetLogObjects();
		}
		function doGetLogObjects(){
			var logPageCurrent = $("#log-pageId").data("logPageCurrent");
			$(".logPageCurrent").html("当前页（" + logPageCurrent + "）");
			console.log("userModel.jsp==>当期页==>" + logPageCurrent);
			var params = {"pageCurrent":logPageCurrent};
			$.getJSON("findLog",params,function(result){
				if(result.state==1){
					doHandlerLogResult(result.data.records,result.data.pageCurrent);
				}
			})
		}
		function doHandlerLogResult(logList,logPageCurrent){
			// 1. 先清空列表
			$(".log-model .table").html("");
			// 2. 加表头
			// 3. 填充表体
			$.each(logList,function(index,log){
				console.log("logPageCurrent===" + logPageCurrent)
				var ind = (index + 1 + ((logPageCurrent-1)*14));
				console.log("ind===" + ind)
				$(".log-model .table").append("<div class='row'>"+
						"<div class='col-1'>"+(ind)+"</div>"+
						"<div class='col-1'>"+log.username+"</div>"+
						"<div class='col'>"+log.operation+"</div>"+
						"<div class='col'>"+log.method+"</div>"+
						"<div class='col-1'>"+log.params+"</div>"+
						"<div class='col-1'>"+log.time+"</div>"+
						"<div class='col-1'>"+log.ip+"</div>"+
						"<div class='col-3'>"+log.createdTime+"</div>"+
						// "<div class='col-1'><a href=\"javascript:deleteLog('"+log.scoreId+"');\">删除</a></div>"+
						"</div>");
			});
		}


		function doSetLogModelPageInitation() {
			$.getJSON("findLog", function(result) {
				console.log(result)
				// 初始化总记录数，
				$(".logRowCount").html("总记录数(" + result.data.rowCount + ")");
				// 初始化总页数
				$(".logPageCount").html("总页数（" + result.data.pageCount + "）");
				// 初始化当前页
				$(".logPageCurrent").html("当前页（" + result.data.pageCurrent + "）");

				// 相当于绑定全局数据
				$("#log-pageId").data("logPageCurrent", result.data.pageCurrent);
				$("#log-pageId").data("logPageCount", result.data.pageCount);
			})
		}

	</script>

</div>