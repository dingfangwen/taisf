<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<base href="${basePath}">
<title>平台管理系统</title>
<meta name="keywords" content="平台管理系统">
<meta name="description" content="平台管理系统">
<link rel="${staticResourceUrl}/shortcut icon" href="favicon.ico">
<link href="${staticResourceUrl}/css/bootstrap.min.css${VERSION}001"
	rel="stylesheet">
<link href="${staticResourceUrl}/css/font-awesome.css${VERSION}001"
	rel="stylesheet">
<link
	href="${staticResourceUrl}/css/plugins/bootstrap-table/bootstrap-table.min.css${VERSION}001"
	rel="stylesheet">
<link href="${staticResourceUrl}/css/animate.css${VERSION}001"
	rel="stylesheet">
<link href="${staticResourceUrl}/css/style.css${VERSION}001"
	rel="stylesheet">
<link
	href="${staticResourceUrl}/css/plugins/treeview/bootstrap-treeview.css${VERSION}001"
	rel="stylesheet">
<style type=text/css>
.tdfont {
	font-size: 13px
}
</style>

</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="float-e-margins">
			<div class="ibox-content">
				<!-- 左侧树div -->
				<div class="col-sm-3">
					<button id="addMenuButton" type="button" class="btn btn-primary"
						data-toggle="modal" data-target="#myModal">新增菜单</button>
					<div id="treeview" class="test"
						style="overflow-y: auto; overflow-x: auto; height: 600px;"></div>
				</div>
				<!-- 右侧table div -->
				<div class="col-sm-9">
					<!-- <button id="stopMenuButton" type="button" class="btn btn-primary">停用菜单</button> -->
					<div class="col-sm-12">

						<!-- 菜单列表 -->
						<div class="example-wrap">
							<div class="example">
								<table id="listTable" class="table table-bordered"
									data-click-to-select="true" data-toggle="table"
									data-side-pagination="server" data-pagination="true"
									data-page-list="[10,20,50,100]" data-pagination="true"
									data-page-size="10" data-pagination-first-text="首页"
									data-pagination-pre-text="上一页" data-pagination-next-text="下一页"
									data-pagination-last-text="末页"
									data-content-type="application/x-www-form-urlencoded"
									data-query-params="paginationParam" data-method="post"
									data-row-style="rowStyle"
									data-url="system/ups/menuDatalist">
									<thead>
										<tr>
											<th data-field="resName" data-align="center"
												data-sortable="true"><p class="tdfont">菜单名称</p></th>
											<th data-field="resUrl" data-align="center"
												data-formatter="urlFormatData"><p class="tdfont">URL</p></th>
											<th data-field="orderCode" data-align="center"><p
													class="tdfont">排序</p></th>
											<th data-field="resState" data-align="center"
												data-formatter="stateFormat"><p class="tdfont">状态</p></th>
											<th data-field="test" data-align="center"
												data-formatter="menuOperData"><p class="tdfont">操作</p></th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
						<!-- 菜单列表结束 -->
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<div class="modal inmodal" id="myModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content animated bounceInRight">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">添加菜单</h4>
				</div>
				<!-- <div class="modal-body"> -->
				<!-- <div class="wrapper wrapper-content animated fadeInRight">
			        <div class="row"> -->
				<div class="col-sm-14">
					<div class="float-e-margins">
						<div class="ibox-content">
							<form id="menuEditForm" action="system/ups/addMenuRes"
								class="form-horizontal m-t">
								<input id="selectedMenuId" type="hidden" name="parentFid"
									value="${treeNode.id}">

								<div class="form-group">
									<label class="col-sm-3 control-label">上级目录：</label>
									<div class="col-sm-8">
										<input id="selectedMenuText" class="form-control" type="text"
											value="${treeNode.text}"> <span
											class="help-block m-b-none"></span>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label">是否根节点：</label>
									<div class="col-sm-8">
										<div class="radio i-checks">
											<label> <input type="radio" value="1" id="isRoot"
												name="isRoot"> <i></i>是
											</label> <label> <input type="radio" value="0" id="isRoot"
												name="isRoot" checked> <i></i>否
											</label>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label">名称：</label>
									<div class="col-sm-8">
										<input id="resName" name="resName" class="form-control"
											type="text" value="${menu.resName }"> <span
											class="help-block m-b-none"></span>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label">图标：</label>
									<div class="col-sm-8">
										<input id="className" name="className" class="form-control"
											type="text" value="${menu.className }" aria-required="true"
											aria-invalid="true" class="error">
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label">URL：</label>
									<div class="col-sm-8">
										<input id="resUrl" name="resUrl" class="form-control"
											type="text" value="${menu.resUrl }"> <span
											class="help-block m-b-none"></span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">排序：</label>
									<div class="col-sm-8">
										<input id="orderCode" name="orderCode" class="form-control"
											type="text" value="${menu.orderCode }">
									</div>
								</div>
								<%--  <div class="form-group">
				                     <label class="col-sm-3 control-label">是否启用：</label>
				                     <div class="col-sm-8">
				                         <input id="resState" name="resState" class="form-control" type="text"  value="${menu.resState }" aria-required="true" aria-invalid="false" class="valid">
				                     </div>
				                 </div> --%>

								<div class="form-group">
									<label class="col-sm-3 control-label">菜单类型</label>
									<div class="col-sm-8">
										<input id="resType" name="resType" type="hidden"
											class="form-control" type="text" value="${menu.resType }"
											aria-required="true" aria-invalid="false" class="valid">
										<select id="resTypeSelect" class="form-control m-b">
											<option value="-1">请选择</option>
											<option value="0">非功能点菜单</option>
											<option value="1">功能点菜单</option>
											<option value="2">功能按钮</option>
										</select>
									</div>
								</div>
								<!-- 用于 将表单缓存清空 -->
								<input type="reset" style="display: none;" />
							</form>
						</div>
					</div>
				</div>
				<!-- </div>
	         </div> -->
				<!-- </div> -->
				<div class="modal-footer">
					<button type="button" id="saveMenuRes" class="btn btn-primary">保存</button>
					<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 添加 菜单  弹出框 -->
	<!-- <div class="modal inmodal" id="myModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content animated bounceInRight">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">添加菜单</h4>
				</div>
				<div class="modal-body">
					<div class="wrapper wrapper-content animated fadeInRight">
						<div class="row">
							<div class="col-sm-14">
								<div class="float-e-margins">
									<div class="ibox-content">
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
					<button type="button" id="saveMenuRes" class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div> -->

	<!-- 全局js -->
	<script src="${staticResourceUrl}/js/jquery.min.js${VERSION}001"></script>
	<script src="${staticResourceUrl}/js/bootstrap.min.js${VERSION}001"></script>

	<!-- 自定义js -->
	<script src="${staticResourceUrl}/js/content.js${VERSION}001"></script>


	<!-- Bootstrap table -->
	<script
		src="${staticResourceUrl}/js/plugins/bootstrap-table/bootstrap-table.min.js${VERSION}001"></script>
	<script
		src="${staticResourceUrl}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js${VERSION}001"></script>
	<script
		src="${staticResourceUrl}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js${VERSION}001"></script>

	<!-- Bootstrap-Treeview plugin javascript -->
	<script
		src="${staticResourceUrl}/js/plugins/treeview/bootstrap-treeview.js${VERSION}001"></script>

	<!-- jQuery Validation plugin javascript-->
	<script
		src="${staticResourceUrl}/js/plugins/validate/jquery.validate.min.js${VERSION}001"></script>

	<script
		src="${staticResourceUrl}/js/plugins/validate/messages_zh.min.js${VERSION}001"></script>

	<!-- layer javascript -->
	<script
		src="${staticResourceUrl}/js/plugins/layer/layer.min.js${VERSION}001"></script>


	<script type="text/javascript">
	var refreshTreeUrl = "system/ups/menuDatalist"; //刷新右侧列表路径
	var saveMenuResourceUrl = "system/ups/addMenuRes";//添加菜单路径
	var changeStatusResourceUrl = "system/ups/changeStatus";//更改状态路径
	var parent_id =  "${treeNode.id}";// 作为分页来用，保证分页是所选中菜单的
	
	/* 校验值不为空 */
	function checkNotNull(obj){
   		if(obj == null || obj == "" ||obj == 'undefined'){
   			return false;
   		}else{
   			return true;
   		}
   		
   	}
	 
    /* 页面设置 */
   	function paginationParam(params) {
	  
	   return {
	        limit: params.limit,
	        offset: params.offset,
	        page: $('#listTable').bootstrapTable('getOptions').pageNumber,
	        parent_fid:parent_id
	        
    	};
	}
   	
   	/* 状态 值设置 */
   	function stateFormat(value, row, index){
   		if(value == 0){
   			return'已停用';
   		}else if(value == 1){
   			return '已启用';
   		}else{
   			return '';
   		}
   	}
   	
   	/* 列表 操作 值设置 */
   	function menuOperData(value, row, index){
   		if(row.resState == 0){
   			return "<button type='button'  class='btn btn-red btn-sm' onclick='changeStatus(\""+row.fid+'\",\"'+row.resState+"\");'><span> 启用</span></button>";
   		}else if(row.resState == 1){
   			return "<button type='button'  class='btn btn-primary btn-sm' onclick='changeStatus(\""+row.fid+'\",\"'+row.resState+"\");'><span> 停用</span></button>";
   		}
   		
   	}
   	 /* url 操作 值设置 */
   	function urlFormatData(value, row, index){
   		if(checkNotNull(value)){
   			return value;
   		}else{
   			return "*";
   		}
   	}
   	
   	 /* 左侧树 初始化设置 */
   	function initTreeView(Obj){
   	    //加载左侧树	
   	    $('#treeview').treeview({
   	        color: "inherit",
   	     	selectedBackColor : "#18a689",
   	        data: Obj,
   	        onNodeSelected: function (event, node) {
   	          
   	        //添加菜单时  上层目录  默认设置
   	        var fid = node.id; //默认菜单id
   	        var fidText = node.text;//默认菜单名称
   	        
   	       $("#selectedMenuId").val(fid);
   	       $("#selectedMenuText").val(node.text);
   	       
   	        $.ajax({
   		   	url:refreshTreeUrl,
   			type:"post",
   			dataType:"json",
   			data:{"fid":fid},
   			success:function(data){
   				parent_id = fid;
   				//刷新table
   				$('#listTable').bootstrapTable('load', data)
   			},
   	       });
   	    }
   	  })
   	}
   	
   	/* 改变菜单状态  */
   	function changeStatus(fid,resState){
   		var selectedMenuId = $("#selectedMenuId").val();
   		
   		var mes = '';
   		mes = resState == 0 ? '确认要启用菜单？' : '确认要关闭菜单？';// 提示信息
   		var iconNum = resState == 0 ? 6 : 5; //显示icon层设置 6：笑脸  5：沮丧
   	    //确认是否改变
   		layer.confirm(mes, {icon: iconNum, title:'提示'},function(index){
   			$.ajax({
		           type: "POST",
		           url: changeStatusResourceUrl,
		           dataType:"json",
		           traditional: true,
		           data: {'selectedId':fid,'resStatus':resState},
		           success: function (result) {
		        	   //刷新右侧列表
		        	   $('#listTable').bootstrapTable('refresh', {query: {fid: selectedMenuId}});
		          	},
		           error: function(result) {
		              alert("error:"+result);
		            }
		     });
   		  
   		  layer.close(index);
   		});
   	}
   	
   	/* 校验form表单 */
   	function checkMenuFromData(){
   		var res = true;
   		var resName = $("#resName").val();
   		if(!checkNotNull(resName)){
   			res = false;
   			//自定义 类型弹出框
   			layer.msg("请填写菜单名称！",{icon: 6,time: 1000,offset:'30px',area:['200px', '70px']});  
   			return res;
   		}
   		var resType = $("#resType").val();
   		if(!checkNotNull(resType) || resType == -1){
   			res = false;
   		    //自定义 类型弹出框
   			layer.msg("请选择菜单类型！",{icon: 6,time: 1000,offset:'30px',area:['200px', '70px']});
   			return res;
   		}
   		return res;
   		
   	}
   	
   	$(document).ready(function () {
   		
   	/* 初始化左侧树  */
   	initTreeView('${treeview}');
   	
	/* 选择 菜单类型 */
	$('#resTypeSelect').change(function(){
		var resTypeSelected = $(this).children('option:selected').val();
		$("#resType").val(resTypeSelected);
		
	});
  	
	/*保存菜单*/
	$("#saveMenuRes").click(function(){
		var selectedMenuId = $("#selectedMenuId").val();
		//校验通过  则提交
		if(checkMenuFromData()){
			$.ajax({
	            type: "POST",
	            url: saveMenuResourceUrl,
	            dataType:"json",
	            data: $("#menuEditForm").serialize(),
	            success: function (result) {
	            initTreeView(result.treeview);
	           	$('#listTable').bootstrapTable('refresh', {query: {fid: selectedMenuId}});
	           	$('#myModal').modal('hide')
	           	$("input[type=reset]").trigger("click");
	            },
	            error: function(result) {
	               alert("error:"+result);
	            }
	      });
		}
	})

	/* $("#stopMenuButton").click(function(){
	
	var selectVar=$('#listTable').bootstrapTable('getSelections');
	var selectIdsArray = new Array();
	$(selectVar).each(function(index,obj){
		
		selectIdsArray.push(obj.fid);
		
	});
	
	if(selectIdsArray.length == 0){
		alert("请至少选择一个菜单！");
		return;
	}
	
	 $.ajax({
           type: "POST",
           url: changeStatusResourceUrl,
           dataType:"json",
           traditional: true,
           data: {'selectedIds':selectIdsArray},
           success: function (result) {
          	},
           error: function(result) {
              alert("error:"+result);
            }
     });   
}) */


});
</script>


</body>

</html>