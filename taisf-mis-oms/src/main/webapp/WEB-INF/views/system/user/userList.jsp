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
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit|ie-comp|ie-stand" />
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
<link
	href="${staticResourceUrl}/css/plugins/iCheck/custom.css${VERSION}"
	rel="stylesheet">

<link href="${staticResourceUrl}/css/bootstrap.min.css?v=3.3.6"
	rel="stylesheet">
<link href="${staticResourceUrl}/css/font-awesome.css?v=4.4.0"
	rel="stylesheet">
<link href="${staticResourceUrl}/css/plugins/iCheck/custom.css"
	rel="stylesheet">
<link href="${staticResourceUrl}/css/animate.css" rel="stylesheet">
<link href="${staticResourceUrl}/css/style.css?v=4.1.0" rel="stylesheet">

<link href="${staticResourceUrl}/css/custom-z.css${VERSION}"
	rel="stylesheet">
<style type=text/css>
.tdfont {
	font-size: 13px
}
</style>
</head>
<body class="gray-bg">

	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox">
			<div class="ibox-content">
				<div class="row">
					<div class="form-group">

						<label class="col-sm-1 control-label mtop">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="empName"
								id="qempName" />
						</div>

						<label class="col-sm-1 control-label mtop">用&nbsp;&nbsp;户&nbsp;&nbsp;名:</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="empMail"
								id="qempMail" />
						</div>

						<label class="col-sm-1 control-label mtop">手&nbsp;&nbsp;机&nbsp;&nbsp;号:</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" name="empMobile"
								id="qempMobile" />
						</div>
						<label class="col-sm-1 control-label"></label>
						<div class="col-sm-1">
							<button class="btn btn-primary" type="button" onclick="query();">
								<i class="fa fa-search"></i>&nbsp;查询
							</button>
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
						<!-- <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#addModal"><i class="fa fa-plus"></i>&nbsp;添加员工</button> -->
						<button onclick="addEmployee();" class="btn btn-primary"
							type="button">
							<i class="fa fa-plus"></i>&nbsp;添加员工
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
									data-url="system/user/userListData">
									<thead>
										<tr>
											<th data-field="userId" data-visible="false" data-width="22%"
												data-align="center"></th>
											<th data-field="empName" data-width="20%" data-align="center"><span
													class="tdfont">姓名</span></th>
											<th data-field="empSex" data-width="10%" data-align="center"
												data-formatter="getSex"><span class="tdfont">性别</span></th>
											<th data-field="empMail" data-width="20%" data-align="center"><span
													class="tdfont">用户名</span></th>
											<th data-field="empMobile" data-width="20%"
												data-align="center"><span class="tdfont">手机</span></th>
											<%--<th data-field="departName" data-width="20%"
												data-align="center"><span class="tdfont">部门</span></th>--%>

											<th data-field="handle" data-width="10%" data-align="center"
												data-formatter="formateOperate"><span class="tdfont">操作</span></th>
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




		<!-- 添加   弹出框 -->
		<div class="modal inmodal" id="addModal" tabindex="-1" role="dialog"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content animated bounceInRight">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
						</button>
						<h4 class="modal-title">添加员工</h4>
					</div>
					<!-- <div class="modal-body">
						<div class="wrapper wrapper-content animated fadeInRight">
							<div class="row"> -->
					<div class="col-sm-14">
						<div class="float-e-margins">
							<div class="ibox-content">
								<form id="addForm" class="form-horizontal m-t">
									<div class="form-group">
										<label class="col-sm-3 control-label">员工姓名:</label>
										<div class="col-sm-8">
											<input class="form-control" id="empName" type="text"
												name="empName">
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label">用&nbsp;&nbsp;户&nbsp;&nbsp;名:</label>
										<div class="col-sm-8">
											<input class="form-control" id="empMail" type="text"
												name="empMail"> <label id="message1"
												class="control-label" style="color: #F00"></label>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
										<div class="col-sm-8">
											<input class="form-control" type="password" id="passWord"
												name="userPwd" placeholder="请输入密码"> <label
												id="message2" class="control-label" style="color: #F00"></label>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label">确认密码:</label>
										<div class="col-sm-8">
											<input class="form-control" type="password"
												id="confirmPassWord" placeholder="请再次输入密码"> <label
												id="message2" class="control-label" style="color: #F00"></label>
										</div>
									</div>


									<div class="form-group">
										<label class="col-sm-3 control-label">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:</label>
										<div class="col-sm-8">
											<select class="form-control" id="empSex" name="empSex">
												<option value="1">男</option>
												<option value="2">女</option>
											</select>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label">手&nbsp;&nbsp;机&nbsp;&nbsp;号:</label>
										<div class="col-sm-8">
											<input class="form-control" id="empMobile" type="text"
												name="empMobile">
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label">部门名称:</label>
										<div class="col-sm-8">
											<input class="form-control" id="departName" type="text"
												name="departName">
										</div>
									</div>

								</form>
							</div>
						</div>
					</div>
					<!-- </div>
						</div>
					</div> -->
					<div class="modal-footer">
						<button type="button" id="addBlack" class="btn btn-primary">保存</button>
						<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>





		<!-- 修改黑名单  弹出框 -->
		<div class="modal inmodal" id="editModal" tabindex="-1" role="dialog"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content animated bounceInRight">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
						</button>
						<h4 class="modal-title">修改密码</h4>
					</div>
					<!-- <div class="modal-body">
						<div class="wrapper wrapper-content animated fadeInRight">
							<div class="row"> -->
					<div class="col-sm-14">
						<div class="float-e-margins">
							<div class="ibox-content">

								<form id="editForm" class="form-horizontal m-t">

									<input class="form-control" id="editUserId" type="hidden"
										name="editUserId">

									<div class="form-group">
										<label class="col-sm-3 control-label">用&nbsp;&nbsp;户&nbsp;&nbsp;名：</label>
										<div class="col-sm-8">
											<input readonly="readonly" class="form-control"
												id="editEmpMail" type="text">
										</div>
									</div>


									<div class="form-group">
										<label class="col-sm-3 control-label">设置密码：</label>
										<div class="col-sm-8">
											<input class="form-control" id="editPassWord" type="password"
												name="editPassWord" placeholder="请输入密码">
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label">确认密码：</label>
										<div class="col-sm-8">
											<input class="form-control" id="confirmEditPassWord"
												type="password" placeholder="请输入密码">
										</div>
									</div>

								</form>

							</div>
						</div>
					</div>
					<!-- </div>
						</div>
					</div> -->
					<div class="modal-footer">
						<button type="button" id="editBlack" class="btn btn-primary">修改</button>
						<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>

					</div>
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
	<script
		src="${staticResourceUrl}/js/plugins/layer/layer.min.js${VERSION}"></script>
	<script
		src="${staticResourceUrl}/js/plugins/layer/laydate/laydate.js${VERSION}001"></script>
	<script src="${staticResourceUrl}/js/common/custom.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/common/refresh.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/common/date.proto.js${VERSION}"></script>

	<script type="text/javascript">
    	function paginationParam(params) {
		    return {
		        limit: params.limit,
		        page: $('#listTable').bootstrapTable('getOptions').pageNumber,
				empName:$.trim($('#qempName').val()),
				empMail:$.trim($('#qempMail').val()),
				empMobile:$.trim($('#qempMobile').val())
	    	};
		}
	    function query(){
	    	$('#listTable').bootstrapTable('selectPage', 1);
	    }
	    
	    function addEmployee(){
	    	$.openNewTab(new Date().getTime(),"system/user/toAddEmployee", "添加员工");
	    }
	    function editEmployee(userId){
	    	$.openNewTab(new Date().getTime(),"system/user/toEditEmployee?userId="+userId, "编辑员工");
	    }
	    
	 	// 操作列
		function formateOperate(value, row, index) {
			var result = "<a><img title='编辑' src='${staticResourceUrl}/btn/icon/btn_eidt@2x.png' onclick='editEmployee(\""+ row.userId + "\");' /></a>";
			return result+"&nbsp;&nbsp;&nbsp;&nbsp;<a><img  data-toggle=\"tooltip\" data-placement=\"top\" title=\"修改密码\" onclick='editPwd(\""+ row.userId + "\",\""+ row.empMail + "\");'  src='${staticResourceUrl}/btn/icon/btn_password@2x.png' /></a>";
		}

		// 修改密码
		function editPwd(userId, empMail) {

			$("#editUserId").val(userId);
			$("#editEmpMail").val(empMail);
			
			$("#editPassWord").val("");
			$("#confirmEditPassWord").val("");
			$('#editModal').modal('show');

		}


		//修改密码
		$("#editBlack").click(function () {
			$("#editBlack").attr("disabled","disabled");
			var editUserId=$("#editUserId").val();

			
			var pwdReg = /^(\w){6,20}$/; //6到20位
			
			var editPassWord=$("#editPassWord").val();
            if(editPassWord == '' || editPassWord == undefined || editPassWord == null){
                layer.alert("请输入密码", {icon: 5,time: 2000, title:'提示'});
                $("#editBlack").removeAttr("disabled");
                return false;
            }
            if(!pwdReg.test(editPassWord)){
            	layer.alert("密码只能是6-20位", {icon: 5,time: 2000, title:'提示'});
                $("#editBlack").removeAttr("disabled");
                return false;
            }
			
			var confirmEditPassWord=$("#confirmEditPassWord").val();
			if(confirmEditPassWord == '' || confirmEditPassWord == undefined || confirmEditPassWord == null){
				layer.alert("确认请输入密码", {icon: 5,time: 2000, title:'提示'});
                $("#editBlack").removeAttr("disabled");
                return false;
			}
			if(!pwdReg.test(confirmEditPassWord)){
            	layer.alert("确认密码只能是6-20位", {icon: 5,time: 2000, title:'提示'});
                $("#editBlack").removeAttr("disabled");
                return false;
            }
			if(confirmEditPassWord != editPassWord){
				layer.alert("两次密码输入不一致", {icon: 5,time: 2000, title:'提示'});
                $("#editBlack").removeAttr("disabled");
                return false;
			}

			$.ajax({
				type: "post",
				url: "system/user/changePwd",
				dataType:"json",
				data: {
					"userId" : editUserId,
					"password" : editPassWord
				},
				success: function (result) {
					if(result.code == 0){
						layer.alert("修改成功", {icon: 6,time: 2000, title:'提示'});
		        		$('#listTable').bootstrapTable('refresh');
						$('#editModal').modal('hide');
						$("#editBlack").removeAttr("disabled");
						//window.location.reload();
					}else{
		        		layer.alert("操作失败:"+result.msg, {icon: 5,time: 2000, title:'提示'});
    					$("#editBlack").removeAttr("disabled");
                	}
				},
				error: function(result) {
					layer.alert("未知错误", {icon: 5,time: 2000, title:'提示'});
   					$("#editBlack").removeAttr("disabled");
				}
			});

		})




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
			
			$.ajax({
				type: "post",
				url: "system/user/saveUser",
				dataType:"json",
				data:
				$("#addForm").serializeArray(),
				success: function (result) {
					if(result.code == 0){
						layer.alert("修改成功", {icon: 6,time: 2000, title:'提示'});
		        		$('#listTable').bootstrapTable('refresh');
						$('#addModal').modal('hide');
						$("#addBlack").removeAttr("disabled");
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


		function getSex(value, row, index){
			if(value==1){
				return "男";
			}else if(value==2){
				return "女";
			}else {
				return "";
			}
		}

    </script>

</body>

</html>
