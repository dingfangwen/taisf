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
							<input id="supplierName" type="text" value="" class="form-control">
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
							data-url="user/supplierPageList">
							<thead>																																															
								<tr>						
									<th data-field="id" data-visible="false"></th>

									<th data-field="supplierCode" data-width="10%"
										data-align="center"><span class="tdfont">编号</span></th>
									<th data-field="cityName" data-width="10%"
										data-align="center" ><span class="tdfont">城市</span></th>
									<th data-field="supplierName" data-width="15%"
										data-align="center" ><span class="tdfont">供餐单位名称</span></th>
									<th data-field="supplierStatus" data-width="10%"
										data-align="center" ><span class="tdfont">状态</span></th>

									<%--<th data-field="handle" data-width="15%" data-align="center"
										data-formatter="formatOperate"><span class="tdfont">操作</span></th>--%>
								</tr>
							</thead>
						</table>
					</div>
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
                supplierName : $("#supplierName").val()
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

		// 操作列
		function formatOperate(value, row, index) {
			var result = "";
			result = result + "<a title='转让' href=javascript:editEnterprise('"
					+ "base/oms/operate?id=" + row.id + "&operate=2"
					+ "')>转让</a>&nbsp;&nbsp;&nbsp;&nbsp;";
			return result;
		}

		function editEnterprise(url) {
			$.openNewTab(new Date().getTime(), url, "编辑企业");
		}

		function viewEnterprise(url) {
			$.openNewTab(new Date().getTime(), url, "查看企业");
		}

		//跳转添加企业页
		function addEnterprise() {
			var url = "base/oms/operate?operate=3";
			$.openNewTab(new Date().getTime(), url, "添加企业");
		}

		function query() {
			$("#listTable").bootstrapTable("selectPage", 1);
		}
	</script>

</body>
</html>
