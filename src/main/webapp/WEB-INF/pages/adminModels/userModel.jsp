<%@page pageEncoding="UTF-8"%>
<div class="user-model">
	<!--
	<button id='add-user' class='btn'>点击添加/修改用户</button>
	<input type='text' name='username' placeholder="用户名"> <input
		type='text' name='password' placeholder="密码"> <input
		type='text' name='status' placeholder="身份"> <br />
	 -->
	<div class='container1'>
		<div id='user-show-up-data'>
			<br>
			<div class='col-sm-12' style='margin-bottom: 20px; margin-top: 20px;'>
				<input type="text" id='user-up-username' class='col-sm-12 form-control' placeholder="用户名" />
				<input type="text" id='user-up-password' class='col-sm-12 form-control' placeholder="密码" />
				<input type="text" id='user-up-status' class='col-sm-12 form-control' placeholder="身份" />
			</div>
			<br>
			<div class='col-sm-8' style='margin-right: 50px;'></div>
			<button id='user-model-save-btn' type='button' class='btn btn-info'>保存</button>
			<button id='user-model-cancel-btn' type='button' class='btn btn-info'>取消</button>
		</div>
		<div class='container'>
			<div class='row' style='border-bottom:2px solid rgb(234, 236, 239); height: 50px; line-height: 50px; text-align:center;'>
				<div class='col'>序号</div>
				<div class='col'>用户名</div>
				<div class='col'>密码</div>
				<div class='col'>身份</div>
				<div class='col'>
					<button id='user-model-update-btn' type='button' class='btn btn-warning'>更新数据</button>
				</div>
			</div>
		</div>
		<div class="table container">
			<!-- 此处由js生成表格 -->
		</div>
		<div id="pageId" class="box-footer clearfix">
			<ul class="pagination">
				<li class='col'><a class="first">首页</a></li>
				<li class='col'><a class="pre">上一页</a></li>
				<li class='col'><a class="next">下一页</a></li>
				<li class='col'><a class="last">尾页</a></li>
				<li class='col'><a class="rowCount">总记录数(0)</a></li>
				<li class='col'><a class="pageCount">总页数(0)</a></li>
				<li class='col'><a class="pageCurrent">当前页(1)</a></li>
			</ul>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			// 初始化页面数据
			doSetPageInitation();
			$("#pageId").on("click", ".first,.pre,.next,.last", doJumpToPage);
			// 更新数据的遮罩层↓↓↓↓↓↓↓↓↓↓↓↓
			$("#user-model-update-btn").on("click",doShowUpPage);

			// 点击取消时，不展示遮罩层
			$("#user-model-cancel-btn").click(function(){
				$("#user-show-up-data").hide();
			});
		});

		function doShowUpPage(){
			$("#user-show-up-data").show();
		}

		// 分页处理↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		function doJumpToPage() {
			// 获取当前点击按钮的信息，为pageCount赋值
			var pageCurrent = $("#pageId").data("pageCurrent"); // 获取当前页
			console.log("当前页: " + pageCurrent)
			var pageCount = $("#pageId").data("pageCount"); // 获取总页数
			var cls = $(this).prop("class");
			if (cls == "first") {
				// 如果点击首页并且
				pageCurrent = 1;
			} else if (cls == "pre" && pageCurrent > 1) {
				// 点击上一页，如果当前页大于1
				pageCurrent--;
			} else if (cls == "next" && pageCurrent < pageCount) {
				// 点击下一页，且不是尾页时
				pageCurrent++;
			} else if (cls == "last") {
				pageCurrent = pageCount;
			} else {
				// 点击别处无反应
				return;
			}

			// 重新绑定数据
			$("#pageId").data("pageCurrent", pageCurrent);
			$("#pageId").data("pageCount", pageCount);

			// 根据pageCount的数值进行查询
			console.log(1)
			doGetObjects();
		}
		function doGetObjects(){
			var pageCurrent = $("#pageId").data("pageCurrent");
			$(".pageCurrent").html("当前页（" + pageCurrent + "）");
			console.log("userModel.jsp==>当期页==>" + pageCurrent);
			var params = {"pageCurrent":pageCurrent};

			$.getJSON("findUser",params,function(result){
				if(result.state==1){
					console.log(result)
					doHandlerResult(result.data.records,result.data.pageCurrent);
				}
			})
		}
		function doHandlerResult(userList,pageCurrent){
			// 1. 先清空列表
			$(".user-model .table").html("");
			// 3. 填充表体
			$.each(userList,function(index,user){
				var username_ = user.username;
				var ind = (index + 1 + ((pageCurrent-1)*14));
				$(".user-model .table").append("<div class='row'>"+
										"<div class='col'>"+(index + 1 + ((pageCurrent-1)*14))+"</div>"+
										"<div class='col'>"+user.username+"</div>"+
										"<div class='col'>"+user.password+"</div>"+
										"<div class='col'>"+user.status+"</div>"+
										"<div class='col'><a href=\"javascript:deleteUserFromUserModel('"+username_+"');\">删除</a></div>"+
								   "</div>");
			});
		}


		function doSetPageInitation() {
			$.getJSON("findUser", function(result) {
				// 初始化总记录数，
				$(".rowCount").html("总记录数(" + result.data.rowCount + ")");
				// 初始化总页数
				$(".pageCount").html("总页数（" + result.data.pageCount + "）");
				// 初始化当前页
				$(".pageCurrent").html("当前页（" + result.data.pageCurrent + "）");

				// 相当于绑定全局数据
				$("#pageId").data("pageCurrent", result.data.pageCurrent);
				$("#pageId").data("pageCount", result.data.pageCount);
			})
		}
	</script>
</div>