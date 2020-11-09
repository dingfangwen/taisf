<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<base href="${basePath}">
<title>平台管理系统</title>
<meta name="keywords" content="平台管理系统">
<meta name="description" content="平台管理系统">

<link rel="${staticResourceUrl}/shortcut icon"
	href="${staticResourceUrl}/favicon.ico">
<link href="${staticResourceUrl}/css/bootstrap.min.css?v=3.3.6"
	rel="stylesheet">
<link href="${staticResourceUrl}/css/font-awesome.css${VERSION}001"
	rel="stylesheet">
<link
	href="${staticResourceUrl}/css/plugins/bootstrap-table/bootstrap-table.min.css${VERSION}001"
	rel="stylesheet">
<link href="${staticResourceUrl}/css/bootstrap.min.css${VERSION}001"
	rel="stylesheet">
<link
	href="${staticResourceUrl}/css/plugins/iCheck/custom.css${VERSION}001"
	rel="stylesheet">
<link href="${staticResourceUrl}/css/animate.css${VERSION}001"
	rel="stylesheet">
<link href="${staticResourceUrl}/css/style.css${VERSION}001"
	rel="stylesheet">
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="float-e-margins">
					<div class="ibox-title">
						<h5>添加用户</h5>
						<!-- <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </div> -->
					</div>

					<div class="ibox-content">

						<form id="addForm" class="form-horizontal m-t">
							<div class="form-group">
								<label class="col-sm-3 control-label">员工姓名:</label>
								<div class="col-sm-5">
									<input class="form-control" id="empName" type="text"
										name="empName">
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">用&nbsp;&nbsp;户&nbsp;&nbsp;名:</label>
								<div class="col-sm-5">
									<input class="form-control" id="empMail" type="text"
										name="empMail"> <label id="message1"
										class="control-label" style="color: #F00"></label>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
								<div class="col-sm-5">
									<input class="form-control" type="password" id="passWord"
										name="userPwd" placeholder="请输入密码"> <label
										id="message2" class="control-label" style="color: #F00"></label>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">确认密码:</label>
								<div class="col-sm-5">
									<input class="form-control" type="password"
										id="confirmPassWord" placeholder="请再次输入密码"> <label
										id="message2" class="control-label" style="color: #F00"></label>
								</div>
							</div>


							<div class="form-group">
								<label class="col-sm-3 control-label">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:</label>
								<div class="col-sm-5">
									<select class="form-control" id="empSex" name="empSex">
										<option value="1">男</option>
										<option value="2">女</option>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">手&nbsp;&nbsp;机&nbsp;&nbsp;号:</label>
								<div class="col-sm-5">
									<input class="form-control" id="empMobile" type="text"
										name="empMobile">
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">部门名称:</label>
								<div class="col-sm-5">
									<input class="form-control" id="departName" type="text"
										name="departName">
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色:</label>
								<div class="col-sm-4" id="roleDiv"></div>
								<button type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#roleModel">选择角色</button>

							</div>
							<div class="row">
								<div class="col-sm-3 col-sm-offset-3">
									<button id="addBlack" class="btn btn-primary" type="button">保存内容</button>
								</div>
								<div class="col-sm-1">
									<button onclick="toList();" class="btn btn-white" type="button">返回</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- 弹出框角色 -->
	<div class="modal inmodal fade" id="roleModel" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">角色列表</h4>
				</div>
				<div class="ibox-content">
					<div class="row">
						<div class="col-sm-12">
							<div class="example-wrap">
								<div class="example">
									<!-- 弹出框列表 -->
									<table id="roleTable" class="table table-hover table-bordered"
										data-click-to-select="true" data-toggle="table"
										data-side-pagination="server" data-pagination="true"
										data-page-list="[1,20,50]" data-pagination="true"
										data-page-size="10" data-show-refresh="true"
										data-pagination-first-text="首页" data-pagination-pre-text="上一页"
										data-pagination-next-text="下一页" data-pagination-last-text="末页"
										data-query-params="rolePaginationParam" data-method="get"
										data-height="300" data-url="system/ups/showRoles">
										<thead>
											<tr>
												<th data-field="id" data-checkbox="true"></th>
												<th data-field="fid" data-visible="false"></th>
												<th data-field="roleName" data-align="center">名称</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" onclick="getSelectRole()"
						class="btn btn-primary" data-dismiss="modal">保存</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 全局js -->
	<script src="${staticResourceUrl}/js/jquery.min.js${VERSION}001"></script>
	<script src="${staticResourceUrl}/js/bootstrap.min.js${VERSION}001"></script>

	<!-- 自定义js -->
	<script src="${staticResourceUrl}/js/content.js${VERSION}001"></script>

	<!-- iCheck -->
	<script
		src="${staticResourceUrl}/js/plugins/iCheck/icheck.min.js${VERSION}001"></script>

	<!-- Bootstrap table -->
	<script
		src="${staticResourceUrl}/js/plugins/bootstrap-table/bootstrap-table.min.js${VERSION}001"></script>
	<script
		src="${staticResourceUrl}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js${VERSION}001"></script>
	<script
		src="${staticResourceUrl}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js${VERSION}001"></script>
	<script
		src="${staticResourceUrl}/js/plugins/validate/jquery.validate.min.js${VERSION}001"></script>
	<script
		src="${staticResourceUrl}/js/plugins/validate/messages_zh.min.js${VERSION}001"></script>

	<script
		src="${staticResourceUrl}/js/common/geography.cascade.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/common/refresh.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/common/custom.js${VERSION}"></script>
	<script
		src="${staticResourceUrl}/js/plugins/layer/layer.min.js${VERSION}"></script>

	<script type="text/javascript">
    	 /* $(document).ready(function () {
            
            //选择用户角色
            $("#selectRole").click(function(){
            	var html = '<div name="selectedDiv" class="col-sm-2">';
    			var selectVar=$('#roleTable').bootstrapTable('getSelections');
    			var selectedIdArray = $("#roleDiv").find("input[name='roleFidList']");
    			var selectedIdStr = $(selectedIdArray).map(function(){return $(this).val();}).get().join(", "); 
    			
    			$(selectVar).each(function(index,obj){
    				if(!selectedIdStr || (!!selectedIdStr && selectedIdStr.indexOf(obj.fid) == -1)){
    					html += '<div class="input-group"><input type="hidden" class="form-control" name="roleFidList" value="'+obj.fid+'" />';
    					html += '<input type="text" class="form-control" name="empName" value="'+obj.roleName+'" />';
    					html += '<span class="input-group-btn"><button type="button" class="btn btn-white" onclick="deleteRoleDiv(this)" ><font style="color:red">删除</font></button></span>'
    					html += '</div>'
    				}
    			});
    			
    			html += '</div>';
    			
    			$("#roleDiv").append(html);
            });
        });  */
    	 
    	//添加用户
 		$("#addBlack").click(function(){
 			$("#addBlack").attr("disabled","disabled");
 			
 			var empMail=$("#empMail").val();

 			if(empMail == '' || empMail == undefined || empMail == null){
 				layer.alert("请填写用户名！", {icon: 5,time: 2000, title:'提示'});
 				$("#addBlack").removeAttr("disabled");
 				return false;
 			}
 			if(!/^[A-Za-z0-9]{2,20}$/.test(empMail)){
 				layer.alert("用户名必须为2-20位英文或者数字", {icon: 5,time: 2000, title:'提示'});
                 $("#addBlack").removeAttr("disabled");
                 return false;
 			}
 			
 			var pwdReg = /^(\w){6,20}$/; //6到20位
 			
 			var passWord=$("#passWord").val();
             if(passWord == '' || passWord == undefined || passWord == null){
                 layer.alert("请输入密码", {icon: 5,time: 2000, title:'提示'});
                 $("#addBlack").removeAttr("disabled");
                 return false;
             }
             if(!pwdReg.test(passWord)){
             	layer.alert("密码只能是6-20位", {icon: 5,time: 2000, title:'提示'});
                 $("#addBlack").removeAttr("disabled");
                 return false;
             }
 			
 			var confirmPassWord=$("#confirmPassWord").val();
 			if(confirmPassWord == '' || confirmPassWord == undefined || confirmPassWord == null){
 				layer.alert("请输入密码", {icon: 5,time: 2000, title:'提示'});
                 $("#addBlack").removeAttr("disabled");
                 return false;
 			}
 			if(!pwdReg.test(confirmPassWord)){
             	layer.alert("确认密码只能是6-20位", {icon: 5,time: 2000, title:'提示'});
                 $("#addBlack").removeAttr("disabled");
                 return false;
             }
 			if(confirmPassWord != passWord){
 				layer.alert("两次密码输入不一致", {icon: 5,time: 2000, title:'提示'});
                 $("#addBlack").removeAttr("disabled");
                 return false;
 			}
 			//校验
        	var phone=$("#empMobile").val();
            if (phone == ""){
                layer.alert("请输入电话", {icon: 5,time: 2000, title:'提示'});
                $("#addBlack").removeAttr("disabled");
                return false;
            }
            if(!(/^1[3|5|7|8][0-9]\d{8}$/.test(phone))){ 
            	layer.alert("手机格式不正确", {icon: 5,time: 2000, title:'提示'});                
                $("#addBlack").removeAttr("disabled"); 
                return false; 
            } 
 			
 			$.ajax({
 				type: "post",
 				url: "system/user/saveUser",
 				dataType:"json",
 				data:
 				$("#addForm").serializeArray(),
 				success: function (result) {
 					if(result.code == 0){
 						layer.alert("修改成功", {icon: 6,time: 2000, title:'提示'});
 						$.callBackParent("system/user/userList",true,callBack);
 					}else{
 		        		layer.alert("操作失败:"+result.msg, {icon: 5,time: 2000, title:'提示'});
     					$("#addBlack").removeAttr("disabled");
                 	}
 				},
 				error: function(result) {
 					layer.alert("未知错误", {icon: 5,time: 2000, title:'提示'});
    					$("#addBlack").removeAttr("disabled");
 				}
 			});
 		})	 
    	 
		function callBack(parent){
    		parent.refreshData("listTable");
    	}
        function toList() {
            $.callBackParent("system/user/userList",true,callBack);
        }
        
		/*角色列表参数*/
	   function rolePaginationParam(params) {
		    return {
		        limit: params.limit,
		        page: $('#roleTable').bootstrapTable('getOptions').pageNumber,
		        roleName:$('#roleName').val(),
		        createrName:$('#createrName').val(),
		        isVail:0
	    	};
		}
		/*选择角色*/
		function getSelectRole(){
			
			var html = '<div name="selectedDiv" >';
			var selectVar=$('#roleTable').bootstrapTable('getSelections');
			var selectedIdArray = $("#roleDiv").find("input[name='roleFidList']");
			var selectedIdStr = $(selectedIdArray).map(function(){return $(this).val();}).get().join(", "); 
			
			$(selectVar).each(function(index,obj){
				if(!selectedIdStr || (!!selectedIdStr && selectedIdStr.indexOf(obj.fid) == -1)){
					html += '<div style="float:left;" class="input-group"><input type="hidden" class="form-control" name="roleFidList" value="'+obj.fid+'" />';
					html += '<input readonly="readonly" type="text" class="form-control" name="roleName" value="'+obj.roleName+'" />';
					html += '<span class="input-group-btn"><button type="button" class="btn btn-white" onclick="deleteRoleDiv(this)" ><font style="color:red">删除</font></button></span>'
					html += '</div>'
				}
			});
			
			html += '</div>';
			
			/* console.log(html); */
			$("#roleDiv").append(html);
			
		}
		/*删除选择角色*/
		function deleteRoleDiv(obj){
			//删除选中人的div
			$(obj).parent().parent().remove();
			//删除 <div name="selectedDiv" class="col-sm-2">子节点为空的div
			var divArry = $("#roleDiv").find("div[name='selectedDiv']");
			$(divArry).each(function(index,obj){
				if($(obj).children().length<=0){
					$(obj).remove();
				}
			})
			
		}
    </script>
</body>
</html>
