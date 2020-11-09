<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="customtag" uri="http://minsu.ziroom.com" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<base href="${basePath}">
	<title>互联网医院运营管理后后台</title>
	<meta name="keywords" content="互联网医院运营管理后后台">
	<meta name="description" content="互联网医院运营管理后后台">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="renderer" content="webkit|ie-comp|ie-stand" />
	<link rel="${staticResourceUrl}/shortcut icon" href="${staticIconUrl}/favicon.ico">
	<link href="${staticResourceUrl}/css/bootstrap.min.css${VERSION}" rel="stylesheet">
	<link href="${staticResourceUrl}/css/font-awesome.css${VERSION}" rel="stylesheet">
	<link href="${staticResourceUrl}/css/plugins/bootstrap-table/bootstrap-table.min.css${VERSION}" rel="stylesheet">
	<link href="${staticResourceUrl}/css/animate.css${VERSION}" rel="stylesheet">
	<link href="${staticResourceUrl}/css/style.css${VERSION}" rel="stylesheet">
	<link href="${staticResourceUrl}/css/custom-z.css${VERSION}" rel="stylesheet">
	<style type=text/css>
		.tdfont{font-size:13px}
	</style>
</head>

<body class="gray-bg">

<div class="wrapper wrapper-content animated fadeInRight">


	<div class="ibox float-e-margins">
		<div class="ibox-content">
			<div class="row" style="height:49px;">
				<div class="form-group">

					<label class="col-sm-1 control-label mtop">应&nbsp;&nbsp;用&nbsp;&nbsp;码:</label>
					<div class="col-sm-2">
						<input id="AppCodeS" type="text" value=""
							   class="form-control">
					</div>
					<label class="col-sm-1 control-label mtop">应用名称:</label>
					<div class="col-sm-2">
						<input id="AppNameS" name="phone" type="text"
							   class="form-control">
					</div>
					<label class="col-xs-1 col-sm-1 control-label mtop">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:</label>
					<div class="col-xs-2 col-sm-2">
						<select class="form-control" name=""  id="isDelS">
							<option  value="">---请选择---</option>
							<option  value="0">启用</option>
							<option  value="1">未启用</option>
						</select>
					</div>
					<div class="col-sm-1">
						<button class="btn btn-primary" type="button"
								onclick="query();">
							<i class="fa fa-search"></i>&nbsp;搜索
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>





	<!-- Panel Other -->
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			<div class="row row-lg">
				<div class="col-sm-12">
					<button id="addMenuButton" type="button"  class="btn btn-primary" data-toggle="modal" data-target="#addModal">添加应用</button>
					<div class="example-wrap">
						<div class="example">
							<table id="listTable" class="table table-bordered" data-click-to-select="true"
								   data-toggle="table" data-side-pagination="server"
								   data-pagination="true" data-page-list="[5,10,20,50]"
								   data-pagination="true" data-page-size="10"
								   data-pagination-first-text="首页" data-pagination-pre-text="上一页"
								   data-pagination-next-text="下一页" data-pagination-last-text="末页"
								   data-content-type="application/x-www-form-urlencoded"
								   data-query-params="paginationParam" data-method="post"
								   data-single-select="true"
								   data-height="470"
								   data-url="jkhospital/bannerApp/showBannerAppPageList">
								<thead>

								<tr>
									<th data-field="id" data-visible="false"></th>
									<th data-field="appCode" data-width="5%" data-align="center"><p class="tdfont">应用码</p></th>
									<th data-field="appName" data-width="15%"
										data-align="center"><p class="tdfont">应用名称</p></th>
									<th data-field="isDel" data-width="10%"
										data-align="center" data-formatter="formateIsUsed"><p class="tdfont">状态</p></th>
									<th data-field="handle" data-width="10%" data-align="center"
										data-formatter="formateOperate"><p class="tdfont">操作</p></th>
								</tr>
								</thead>

							</table>
						</div>
					</div>
					<!-- End Example Pagination -->
				</div>
			</div>
		</div>
	</div>
</div>


<!-- 添加 医药代表  弹出框 -->
<div class="modal inmodal" id="addModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">添加应用</h4>
			</div>
			<!-- <div class="modal-body"> -->
			<!-- <div class="wrapper wrapper-content animated fadeInRight">
                <div class="row"> -->
			<div class="col-sm-14">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<form id="form" class="form-horizontal m-t" >

							<div class="form-group">
								<label class="col-sm-3 control-label">应&nbsp;&nbsp;用&nbsp;&nbsp;码:</label>
								<div class="col-sm-8">
									<input id="appCode" name="phone" type="text" class="form-control">
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">应用名称:</label>
								<div class="col-sm-8">
									<input id="appName" name="realName" type="text" class="form-control">
								</div>
							</div>

							<!-- 用于 将表单缓存清空 -->
							<input id="addReset" type="reset" style="display:none;" />
						</form>
					</div>
				</div>
			</div>
			<!-- </div>
        </div> -->
			<!-- </div> -->
			<div class="modal-footer"  >

				<button class="btn btn-primary" id="saveBtn" type="button" onclick="saveApp();">保存</button>
				<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>


<!-- 编辑 医药代表  弹出框 -->
<div class="modal inmodal" id="updateModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">编辑</h4>
			</div>
			<!-- <div class="modal-body">
                 <div class="wrapper wrapper-content animated fadeInRight">
                     <div class="row"> -->
			<div class="col-sm-14">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<form id="form" class="form-horizontal m-t" >
							<input type="hidden" id="idE" name="id" >

							<div class="form-group">
								<label class="col-sm-3 control-label">应&nbsp;&nbsp;用&nbsp;&nbsp;码:</label>
								<div class="col-sm-8">
									<input id="appCodeE"  readonly="readonly" name="phone" type="text" class="form-control">
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">应用名称:</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="appNameE" name="realName">
								</div>
							</div>



							<div class="form-group">
								<label class="col-sm-3 control-label">
									状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:
								</label>
								<div class="col-sm-8">
									<div class="radio i-checks">
										<label>
											<input type="radio" value="0" id="userStatusOne" name="isDelE" > <i></i>启用</label>
										<label>
											<input type="radio" value="1" id="userStatusTwo" name="isDelE" > <i></i>禁用</label>
									</div>
								</div>
							</div>
							<!-- 用于 将表单缓存清空 -->
							<input id="updateReset" type="reset" style="display:none;" />
						</form>
					</div>
				</div>
			</div>
			<!-- </div>
        </div>
    </div> -->
			<div class="modal-footer"  >
				<button class="btn btn-primary" id="editBtn" type="button" onclick="editMedicalAgent();">保存</button>
				<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>

<!-- 变更医药代表  弹出框 -->
<!-- 全局js -->
<script src="${staticResourceUrl}/js/jquery.min.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/bootstrap.min.js${VERSION}"></script>

<!-- Bootstrap table -->
<script src="${staticResourceUrl}/js/plugins/bootstrap-table/bootstrap-table.min.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/plugins/layer/layer.min.js${VERSION}"></script>

<script src="${staticResourceUrl}/js/common/custom.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/common/refresh.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/common/date.proto.js${VERSION}"></script>


<!-- Page-Level Scripts -->
<script>

    //初始化查询参数
    function paginationParam(params) {
        return {
            limit : params.limit,
            page : $('#listTable').bootstrapTable('getOptions').pageNumber,
            isDel : $.trim($('#isDelS').val()),
            appCode : $.trim($('#AppCodeS').val()),
            appName : $.trim($('#AppNameS').val()),
        };
    }
    function query() {
        $('#listTable').bootstrapTable('selectPage', 1);
    }

    //到编辑医药代表框 前 请求药企列表数据 填充数据
    function goUpdateMedicalAgent(id,appCode,appName,isDel){
        $('#updateReset').trigger("click");
        $('#idE').val(id);
        $('#appCodeE').val(appCode);
        $('#appNameE').val(appName);
        if(isDel == 0) {
            $('#userStatusOne').prop("checked",true);
            $('#userStatusTwo').removeAttr("checked");
        }else if(isDel== 1 ){
            $('#userStatusTwo').prop("checked",true);
            $('#userStatusOne').removeAttr("checked");
        }
    }

    //编辑保存医药代表
    function editMedicalAgent() {

        $("#editBtn").attr("disabled","disabled");

        var appNameE=$("#appNameE").val();
        if (appNameE == ""){
            layer.alert("请输入appName", {icon: 5,time: 2000, title:'提示'});
            $("#editBtn").removeAttr("disabled");
            return false;
        }

        $.ajax({
            data : {
                "id" : $("#idE").val(),
                "appName" : appNameE,
                "isDel" : $("input[name='isDelE']:checked").val(),
            },
            type : "post",
            dataType : "json",
            url : 'bannerApp/updateBannerApp',
            success : function(result) {
                if(result.code === 0){
                    layer.alert("编辑成功", {icon: 6,time: 2000, title:'提示'});
                    $('#updateModal').modal('hide');
                    $('#updateReset').trigger("click");
                    $("#editBtn").removeAttr("disabled");
                    $('#listTable').bootstrapTable('refresh');
                }else{
                    layer.alert("操作失败:"+result.msg, {icon: 5,time: 2000, title:'提示'});
                    $("#editBtn").removeAttr("disabled");
                }
            },
            error : function(result) {
                layer.alert("未知错误", {icon: 5,time: 2000, title:'提示'});
                $("#editBtn").removeAttr("disabled");
            }
        });
    }

    //关联医院超链接
    function formateCount(value, row, index) {
        if (value != '0') {
            return "<a onclick='toListAgentHospital(\""+row.realName+"\",\""+row.userId+"\");'><span><font color='#1bb394'>"+value+"</font></span></a>";
        } else {
            return value;
        }
    }
    function toListAgentHospital(realName,userId) {
        $.openNewTab(new Date().getTime(),"medicalagent/listAgentDoctor?realName="+realName+"&userId="+userId, "关联医生明细");
    }

    function goBannerPositionList(appCode,appName) {
        $.openNewTab(new Date().getTime(),"bannerApp/listBannerPosition?appCode="+appCode, appName+"位置管理");
    }



    //增加保存医药代表
    function saveApp() {
        $("#saveBtn").attr("disabled","disabled");
        //校验
        if ($("#appCode").val() == ""){
            layer.alert("请输入appCode", {icon: 5,time: 2000, title:'提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        if ($("#appName").val() == ""){
            layer.alert("请输入appName", {icon: 5,time: 2000, title:'提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        $.ajax({
            data : {
                'appCode' : $("#appCode").val(),
                'appName' : $("#appName").val(),
            },
            type : "post",
            dataType : "json",
            url : 'bannerApp/addBannerApp',
            success : function(result) {
                if(result.code === 0){
                    layer.alert("操作成功"+result.msg, {icon: 6,time: 5000, title:'提示'});
                    $('#listTable').bootstrapTable('refresh');
                    $('#addModal').modal('hide');
                    $("input[type=reset]").trigger("click");
                    $("#saveBtn").removeAttr("disabled");
                }else{
                    layer.alert("操作失败:"+result.msg, {icon: 5,time: 3000, title:'提示'});
                    $("#saveBtn").removeAttr("disabled");
                }
            },
            error : function(result) {
                layer.alert("未知错误", {icon: 5,time: 2000, title:'提示'});
                $("#saveBtn").removeAttr("disabled");
            }
        });
    }

    // 状态
    function formateIsUsed(value, row, index) {
        if (value == '0') {
            return "启用";
        } else if(value == "1"){
            return "未启用";
        }
    }

</script>
</body>
</html>
