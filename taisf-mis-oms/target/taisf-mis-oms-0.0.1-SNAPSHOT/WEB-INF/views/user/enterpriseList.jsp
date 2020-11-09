<%@page import="com.taisf.services.common.valenum.EnterpriseTypeEnum"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="customtag" uri="http://minsu.ziroom.com" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<base href="${basePath}">
<title>企业管理</title>
<meta name="keywords" content="企业管理">
<meta name="description" content="企业管理">
<meta http-equiv="X-UA-Compatible" content="IE=edge">  
<meta name="renderer" content="webkit|ie-comp|ie-stand" />
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
		<div class="ibox">
			<div class="ibox-content">
				<div class="row">
					<div class="form-group">
						<input type="hidden" value="${manger}" id="mangerId"/>
						<label class="col-sm-1 control-label mtop">企业名称:</label>
						<div class="col-sm-2">
							<input id="enterpriseName" type="text" value="" class="form-control">
						</div>
						<div class="col-sm-1">
							<button class="btn btn-primary" type="button" onclick="query();">
								<i class="fa fa-search"></i>&nbsp;搜索
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
						<!-- Example Pagination -->
					<div class="col-sm-12">
						<label class="col-sm-3 control-label mtop">员工:${userName}</label>
						<table id="listTable" class="table table-bordered" data-click-to-select="true"
							data-toggle="table" data-side-pagination="server"
							data-pagination="true" data-page-list="[5,10,20,50]"
							data-pagination="true" data-page-size="10"
							data-pagination-first-text="首页" data-pagination-pre-text="上一页"
							data-pagination-next-text="下一页" data-pagination-last-text="末页"
							data-content-type="application/x-www-form-urlencoded"
							data-query-params="paginationParam" data-method="post"
							data-single-select="true"
							data-url="user/enterprisePageList">
							<thead>																																															
								<tr>						
									<th data-field="id" data-visible="false"></th>

									<th data-field="enterpriseCode" data-width="10%"
										data-align="center"><span class="tdfont">企业编号</span></th>
									<th data-field="enterpriseName" data-width="10%"
										data-align="center" ><span class="tdfont">企业名称</span></th>
									<th data-field="enterpriseType" data-width="15%" data-formatter="formatEnterpriseType"
										data-align="center" ><span class="tdfont">企业类型</span></th>
									<th data-field="cityName" data-width="10%"
										data-align="center" ><span class="tdfont">城市</span></th>
									<th data-field="openTime" data-width="10%" data-formatter="formatDate"
										data-align="center" ><span class="tdfont">开户日期</span></th>
									<th data-field="tillTime" data-width="10%" data-formatter="formatDate"
										data-align="center" ><span class="tdfont">截止日期</span></th>
									<th data-field="enterpriseStatus" data-width="10%" data-formatter="formatStatus"
										data-align="center" ><span class="tdfont">状态</span></th>
									<th data-field="handle" data-width="15%" data-align="center"
										data-formatter="formatOperate"><span class="tdfont">操作</span></th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content animated bounceInRight">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
							class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">转让</h4>
				</div>
				<div class="col-sm-14">
					<div class="ibox float-e-margins">
						<div class="ibox-content">
							<form id="form" class="form-horizontal m-t">

								<div class="form-group">
									<label class="col-sm-3 control-label">转让人:</label>
									<div class="col-sm-8">
										<select class="form-control" name="userId"  id="userId">
											<option value="">--请选择--</option>
											<c:forEach items="${userList}" var="z" >
												<option  value="${z.userUid}">${z.userName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<input type="hidden" class="form-control" id="enterpriseId" name="enterpriseId" value=""/>
								<input type="hidden" class="form-control" id="UID" name="UID" value=""/>
								<!-- 用于 将表单缓存清空 -->
								<input id="addReset" type="reset" style="display:none;"/>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" id="saveBtn" type="button" onclick="saveUser();">保存</button>
					<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 全局js -->
	<script src="${staticResourceUrl}/js/jquery.min.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/bootstrap.min.js${VERSION}"></script>

	<!-- Bootstrap table -->
	<script src="${staticResourceUrl}/js/plugins/bootstrap-table/bootstrap-table.min.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/plugins/layer/layer.min.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/plugins/validate/jquery.validate.min.js${VERSION}"></script>
    <script src="${staticResourceUrl}/js/plugins/validate/messages_zh.min.js${VERSION}"></script>
    <script src="${staticResourceUrl}/js/common/commonUtils.js${VERSION}001" type="text/javascript"></script>
	<script src="${staticResourceUrl}/js/common/custom.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/common/refresh.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/common/date.proto.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/plugins/layer/laydate/laydate.js${VERSION}001"></script>


	<!-- Page-Level Scripts -->
	<script>
		function paginationParam(params) {
			return {
				limit : params.limit,
				page : $("#listTable").bootstrapTable("getOptions").pageNumber,
				manger : $("#mangerId").val(),
                enterpriseName : $("#enterpriseName").val()
			};
		}

		// 格式化时间
		function formatDate(value, row, index) {
			if (value != null) {
				var _date = new Date(value);
				return _date.format("yyyy-MM-dd");
			} else {
				return "-";
			}
		}
		function formatEnterpriseType(value, row, index) {
			if (value == 1) {
				return "平台委托";
			} else if(value == 2) {
				return "自主开发";
			}
		}
		function formatStatus(value, row, index) {
			if (value == 0) {
				return "未提交";
			} else if(value == 1) {
				return "正常";
			}else if(value == 1) {
				return "已过期";
			}else if(value == 1) {
				return "停止合作";
			}
		}

		// 操作列
		function formatOperate(value, row, index) {
			var result = "";
			result = result + "<a title='转让' onclick='transfer(\""+row.id+"\")'  data-toggle='modal' data-target='#myModal'>转让</a>&nbsp;&nbsp;&nbsp;&nbsp;";
			return result;
		}
		function transfer(id){
		    $("#enterpriseId").val(id);
		}
		//跳转添加企业页
		function addEnterprise() {
			var url = "base/oms/operate?operate=3";
			$.openNewTab(new Date().getTime(), url, "添加企业");
		}

		function query() {
			$("#listTable").bootstrapTable("selectPage", 1);
		}

        function saveUser() {

            var userId = $("#userId").val();
            if (userId == null || userId == "") {
                layer.alert("请选择转让人", {icon: 5, time: 2000, title: '提示'});
                $("#saveBtn").removeAttr("disabled");
                return false;
            }
            ;
            $("#saveBtn").attr("disabled", "disabled");
            $.ajax({
                beforeSend: function () {
                    var valid = $("#form").valid();
                    if (!valid) {
                        $("#saveBtn").removeAttr("disabled");
                        return false;
                    }
                    return true;
                },
                data: {
                    'manger': $("#userId").val(),
                    'id': $("#enterpriseId").val()
                },
                type: "post",
                dataType: "json",
                url: 'user/transfer',
                success: function (result) {
                    if (result.code === 0) {
                        layer.alert("操作成功", {icon: 6, time: 2000, title: '提示'});
                        $('#listTable').bootstrapTable('refresh');
                        $('#myModal').modal('hide');
                        $("input[type=reset]").trigger("click");
                        $("#saveBtn").removeAttr("disabled");
                    } else {
                        layer.alert(result.msg, {icon: 5, time: 2000, title: '提示'});
                        $("#saveBtn").removeAttr("disabled");
                    }
                },
                error: function (result) {
                    layer.alert("未知错误", {icon: 5, time: 2000, title: '提示'});
                    $("#saveBtn").removeAttr("disabled");
                }
            });
        }
	</script>

</body>
</html>
