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
				<div class="row" style="margin-top:10px">
                	<div class="form-group">

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

						<table id="listTable" class="table table-bordered" data-click-to-select="true"
							data-toggle="table" data-side-pagination="server"
							data-pagination="true" data-page-list="[5,10,20,50]"
							data-pagination="true" data-page-size="10"
							data-pagination-first-text="首页" data-pagination-pre-text="上一页"
							data-pagination-next-text="下一页" data-pagination-last-text="末页"
							data-content-type="application/x-www-form-urlencoded"
							data-query-params="paginationParam" data-method="post"
							data-single-select="true"
							data-url="finance/enterprisePageListPage">
							<thead>																																															
								<tr>						
									<th data-field="id" data-visible="false"></th>

									<th data-field="enterpriseCode" data-width="10%"
										data-align="center"><span class="tdfont">企业编号</span></th>
									<th data-field="enterpriseName" data-width="10%"
										data-align="center" ><span class="tdfont">企业名称</span></th>
									<th data-field="enterpriseType" data-width="15%"
										data-align="center" ><span class="tdfont">企业类型</span></th>
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



	<!-- 充值 -->
	<div class="modal inmodal" id="chargeModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">


				<div class="modal-header" style="padding: 15px 6px;">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
							class="sr-only">Close</span></button>
					<h4 class="modal-title">企业账户充值</h4>
				</div>


				<!-- 搜索框panel -->
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<input id="charge_enterpriseCode" name="enterpriseCode" type="hidden"
							   class="form-control">

						<div class="ibox-content">
							<label class="col-sm-2 control-label mtop">企业编号:</label>

							<label class="col-sm-4 control-label mtop"><span style="color: red" id="charge_code"></span></label>


							<label class="col-sm-2 control-label mtop">企业名称:</label>

							<label class="col-sm-4 control-label mtop"><span style="color: red" id="charge_name"></span></label>


						</div>


					</div>

					<div class="ibox-content">

						<label class="col-sm-2 control-label mtop">充值金额:</label>
						<div class="col-sm-4">
							<input id="moneyYuan" name="moneyYuan" type="text" value="0"
								   class="form-control">
						</div>

					</div>

				</div>


				<div class="modal-footer">
					<button class="btn btn-primary" type="button" onclick="confirmCharge();">
						<i class="fa fa-search"></i>&nbsp;确认
					</button>
					<button type="button" class="btn btn-white" data-dismiss="modal">返回</button>
				</div>
			</div>
		</div>
	</div>
	<!-- end -->


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
				enterpriseName : $("#enterpriseName").val(),
				enterpriseCode : $("#enterpriseCode").val()
			};
		}

		//确认充值
		function confirmCharge() {
		    var enterpriseCode =  $("#charge_enterpriseCode").val();
		    var moneyYuan =  $("#moneyYuan").val();

            $.ajax({
                data: {
                    'enterpriseCode':enterpriseCode,
                    'moneyYuan':moneyYuan
				},
                type: "post",
                dataType: "json",
                url: 'finance/chargeMoney',
                success: function (result) {
                    if (result.code === 0) {
                        layer.alert("充值成功", {icon: 6, time: 2000, title: '提示'});
                        $('#chargeModal').modal('hide');
                    } else {
                        layer.alert(result.msg, {icon: 5, time: 2000, title: '提示'});
                    }
                },
                error: function (result) {
                    layer.alert("未知错误", {icon: 5, time: 2000, title: '提示'});
                }
            });
        }
		
		function gocharge(enterpriseCode,enterpriseName) {
            $("#charge_enterpriseCode").val(enterpriseCode)
            $("#charge_code").html(enterpriseCode);
            $("#charge_name").html(enterpriseName);
            $('#chargeModal').modal('show');
        }


        // 操作列
        function formatOperate(value, row, index) {
            var result = "";
            if(row.enterpriseStatus == 1){
                result = result + "<a title='去充值' onclick='gocharge(\"" + row.enterpriseCode + "\",\"" + row.enterpriseName + "\")'  >去充值</a>&nbsp;&nbsp;&nbsp;&nbsp;";
            }
            return result;
        }

		function formatStatus(value, row, index) {
            if (value == 1) {
                return "正常";
            } else if (value == 2) {
                return "该企业合作已过期";
            }else if (value == 3) {
                return "该企业已停止合作";
            } else if (value == 0) {
                return "该企业状态未审核通过";
            }else{
                return value;
            }
        }

		function query() {
			$("#listTable").bootstrapTable("selectPage", 1);
		}
	</script>

</body>
</html>
