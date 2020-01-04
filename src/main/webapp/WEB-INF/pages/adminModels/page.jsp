<ul class="pagination pagination-sm no-margin pull-right">
	<li><a class="first">首页</a></li>
	<li><a class="pre">上一页</a></li>
	<li><a class="next">下一页</a></li>
	<li><a class="last">尾页</a></li>
	<li><a class="rowCount">总记录数(0)</a></li>
	<li><a class="pageCount">总页数(0)</a></li>
	<li><a class="pageCurrent">当前页(1)</a></li>
</ul>
<script type="text/javascript">
	$(function(){
		// 为上一页，下一页，首页，尾页绑定点击事件
		//$("#pageId").on("click",".frist,.pre,.next,.last",doJumpToPage());
		  $(".pagination").on("click",".first,.pre,.next,.last",doJumpToPage);
	});
	function doJumpToPage(){
		// 获取当前点击按钮的信息，为pageCount赋值
		var pageCurrent = $("#pageId").data("pageCurrent"); // 获取当前页
		var pageCount = $("#pageId").data("pageCount"); // 获取总页数
		var cls = $(this).prop("class");
		if(cls == "first"){
			// 如果点击首页并且
			pageCurrent = 1;
		}else if(cls == "pre" && pageCurrent > 1){
			// 点击上一页，如果当前页大于1
			pageCurrent--;
		}else if(cls == "next" && pageCurrent < pageCount){
			// 点击下一页，且不是尾页时
			pageCurrent++;
		}else if(cls == "last"){
			pageCurrent = pageCount;
		}else{
			// 点击别处无反应
			return;
		}
		
		// 重新绑定数据
		$("#pageId").data("pageCurrent",pageCurrent);
		$("#pageId").data("pageCount",pageCount);
		// 根据pageCount的数值进行查询
		doGetObjects();
	}
	
	function doSetPageInitation(pageObject) {
		// 初始化总记录数，
		$(".rowCount").html("总记录数（"+pageObject.rowCount+"）");
		// 初始化总页数
		$(".pageCount").html("总页数（"+pageObject.pageCount+"）");
		// 初始化当前页
		$(".pageCurrent").html("当前页（"+pageObject.pageCurrent+"）");
		
		// 相当于绑定全局数据
		$("#pageId").data("pageCurrent",pageObject.pageCurrent);
		$("#pageId").data("pageCount",pageObject.pageCount);
	}
</script>






