<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="customtag" uri="http://minsu.ziroom.com"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<base href="${basePath }">
<title>平台管理系统</title>
<meta name="keywords" content="平台管理系统">
<meta name="description" content="平台管理系统">
<link href="${staticResourceUrl}/css/bootstrap.min.css${VERSION}"
	rel="stylesheet">
<link href="${staticResourceUrl}/css/font-awesome.css${VERSION}"
	rel="stylesheet">
<link
	href="${staticResourceUrl}/css/plugins/bootstrap-table/bootstrap-table.min.css${VERSION}"
	rel="stylesheet">
<link href="${staticResourceUrl}/css/animate.css${VERSION}"
	rel="stylesheet">
<link href="${staticResourceUrl}/css/style.css${VERSION}"
	rel="stylesheet">
<link href="${staticResourceUrl}/css/custom-z.css${VERSION}"
	rel="stylesheet">
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row row-lg">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<div class="row">
							<div class="form-group">
								<label class="col-sm-1 control-label mtop">账户名称：</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" name="userName"
										id="userName" />
								</div>
								<label class="col-sm-1 control-label mtop">用户姓名：</label>
								<div class="col-sm-2">
									<input type="text" class="form-control" name="fullName"
										id="fullName" />
								</div>
								<label class="col-sm-1 control-label mtop">账户状态：</label>
								<div class="col-sm-2">
									<select class="form-control" id="accountStatus">
										<option value="0">正常</option>
										<option value="1">停用</option>
									</select>
								</div>
								<label class="col-sm-1 control-label"></label>
								<div class="col-sm-1">
									<button class="btn btn-primary" type="button"
										onclick="query();">
										<i class="fa fa-search"></i>&nbsp;查询
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Panel Other -->
		<div class="float-e-margins">
			<div class="ibox-content">
				<div class="row row-lg">
					<div class="col-sm-12">
						<button class="btn btn-primary" type="button"
							onclick="taddcurrentuser();">
							<i class="fa fa-save"></i>&nbsp;添加
						</button>
						<button class="btn btn-info" type="button"
							onclick="toeditcurrentuser();">
							<i class="fa fa-edit"></i>&nbsp;编辑
						</button>

						<div class="example-wrap">
							<div class="example">
								<table id="listTable" class="table table-bordered"
									data-click-to-select="true" data-toggle="table"
									data-side-pagination="server" data-pagination="true"
									data-striped="true" data-page-list="[10,20,50]"
									data-pagination="true" data-page-size="10"
									data-pagination-first-text="首页" data-pagination-pre-text="上一页"
									data-pagination-next-text="下一页" data-pagination-last-text="末页"
									data-content-type="application/x-www-form-urlencoded"
									data-query-params="paginationParam" data-method="post"
									data-single-select="true"
									data-classes="table table-hover table-condensed"
									data-height="498" data-url="system/ups/showAllUser">
									<thead>
										<tr>
											<th data-field="id" data-width="5%" data-radio="true"></th>
											<th data-field="userAccount" data-width="15%"
												data-align="center">账户</th>
											<th data-field="fullName" data-width="10%"
												data-align="center">姓名</th>
											<th data-field="roleName" data-width="10%"
												data-align="center" data-formatter="formateRoleName">角色</th>
											<th data-field="postName" data-width="15%"
												data-align="center">岗位</th>
											<th data-field="departmentName" data-width="10%"
												data-align="center">部门</th>
											<th data-field="accountStatus" data-width="10%"
												data-align="center" data-formatter="getuserStatus">状态</th>
											<th data-field="cityName" data-width="15%"
												data-align="center" data-formatter="getRegion">城市</th>
											<th data-field="handle" data-width="10%" data-align="center"
												data-formatter="formateOperate">操作</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Panel Other -->

		<div class="modal inmodal" id="myModal" tabindex="-1" role="dialog"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content animated bounceInRight">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
						</button>
						<h4 class="modal-title">同步员工数据</h4>
					</div>
					<div class="modal-body">
						<div class="wrapper wrapper-content animated fadeInRight">
							<div class="row">
								<div class="col-sm-14">
									<div class="float-e-margins">
										<div class="ibox-content">
											<form id="menuEditForm" action="system/ups/addMenuRes"
												class="form-horizontal m-t">
												<div class="form-group">
													<label class="col-sm-3 control-label">员工编号：</label>
													<div class="col-sm-8">
														<input id="empCode" name="empCode" class="form-control"
															type="text" value="">
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
						<button type="button" onclick="saveSysn()" id="saveSyncBtn"
							class="btn btn-primary">保存</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 全局js -->
	<script src="${staticResourceUrl}/js/jquery.min.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/bootstrap.min.js${VERSION}"></script>

	<!-- 自定义js -->
	<script src="${staticResourceUrl}/js/content.js${VERSION}"></script>


	<!-- Bootstrap table -->
	<script
		src="${staticResourceUrl}/js/plugins/bootstrap-table/bootstrap-table.min.js${VERSION}"></script>
	<script
		src="${staticResourceUrl}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js${VERSION}"></script>
	<script
		src="${staticResourceUrl}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js${VERSION}"></script>

	<script src="${staticResourceUrl}/js/common/custom.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/common/refresh.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/common/date.proto.js${VERSION}"></script>

	<script type="text/javascript">
    	function paginationParam(params) {
		    return {
		        limit: params.limit,
		        page: $('#listTable').bootstrapTable('getOptions').pageNumber,
		        userName:$.trim($('#userName').val()),
		        fullName:$.trim($('#fullName').val()),
		        accountStatus:$('#accountStatus').val()
	    	};
		}
	    function query(){
	    	$('#listTable').bootstrapTable('selectPage', 1);
	    }
	    
	 	// 操作列
		function formateOperate(value, row, index) {
			if (row.accountStatus == '0') {
				return "<button type='button'  class='btn btn-primary btn-sm'><span onclick='editUserStatus(\""+ row.fid + "\",\""+ row.accountStatus + "\");'> 停用</span></button>";
			} else {
				return "<button type='button'  class='btn btn-red btn-sm'><span onclick='editUserStatus(\""+ row.fid + "\",\""+ row.accountStatus + "\");'> 启用</span></button>";
			}
		}
	 	
		//启用|禁用角色
		function editUserStatus(uid, operateFlag) {
			var mes = operateFlag == 0 ? '确定要停用吗？' : '确定要启用吗';// 提示信息
			var iconNum = operateFlag == 0 ? 5 : 6; //显示icon层设置 6：笑脸  5：沮丧
			layer.confirm(mes, {
				icon : iconNum,
				title : '提示'
			}, function(index) {
				$.ajax({
					url : "system/ups/editUserStatus",
					data : {
						uid : uid
					},
					type : "post",
					dataType : "json",
					success : function(result) {
						if (result.code === 0) {
							$('#listTable').bootstrapTable('refresh');
						} else {
							layer.alert("操作失败", {
								icon : 5,
								time : 2000,
								title : '提示'
							});
						}
					},
					error : function(result) {
						layer.alert("未知错误", {
							icon : 5,
							time : 2000,
							title : '提示'
						});
					}
				});
				layer.close(index);
			});
		}
	    
	    // 新增用户
		function taddcurrentuser(){
			$.openNewTab(new Date().getTime(),"system/ups/currentuserAdd", "新增用户");
		}
		
	    // 编辑用户
		function toeditcurrentuser(){
			var selectVar=$('#listTable').bootstrapTable('getSelections');
			if(selectVar.length == 0){
				alert("请选择一条记录进行操作");
			}
			$.openNewTab(new Date().getTime(),"system/ups/editCurrentuser?fid="+selectVar[0].fid, "编辑用户");
		}
		function getuserStatus(value, row, index){
			if(value==0){
				return "正常";
			}else{
				return "停用";
			}
		}
		function getRegion(value, row, index){
			var region='';
			if(row.nationName!=null){
				region=region+row.nationName;
			}
			if(row.provinceName!=''&&row.provinceName!=null){
				region=region+","+row.provinceName;
			}
			if(row.cityName!=''&&row.cityName!=null){
				region=region+","+row.cityName;
			}
			if(row.areaName!=''&&row.areaName!=null){
				region=region+","+row.areaName;
			}
			return region;
		}
	    
	 	// 角色名称
		function formateRoleName(value, row, index) {
	 		var roleName = " ";
	 		if(row.roles != undefined && row.roles.length != 0){
	 			$.each(row.roles, function (index, obj){
	 				roleName += obj.roleName + " ";
	 			});
	 		}
	 		return roleName;
		}
	 	
	 	function saveSysn(){
	 		var empCode = $("#empCode").val();
	 		if(empCode.length != 8){
	 			layer.alert("格式错误", {
					icon : 5,
					time : 2000,
					title : '提示'
				});
	 			return;
	 		}
	 		
	 		$.post("system/ups/syncEhrEmp",{empCode:empCode},function(data){
	 			if(data.code == 0){
	 				alert("提交成功");
	 				$("#myModal").modal("toggle");
	 			}else{
	 				alert(data.msg);
	 			}
	 		},'json');
	 	}
    </script>

</body>

</html>
