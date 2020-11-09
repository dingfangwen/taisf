<%@page import="com.taisf.services.common.valenum.EnterpriseTypeEnum" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <meta name="renderer" content="webkit|ie-comp|ie-stand"/>
    <link href="${staticResourceUrl}/css/bootstrap.min.css${VERSION}" rel="stylesheet">
    <link href="${staticResourceUrl}/css/font-awesome.css${VERSION}" rel="stylesheet">
    <link href="${staticResourceUrl}/css/plugins/bootstrap-table/bootstrap-table.min.css${VERSION}" rel="stylesheet">
    <link href="${staticResourceUrl}/css/animate.css${VERSION}" rel="stylesheet">
    <link href="${staticResourceUrl}/css/style.css${VERSION}" rel="stylesheet">
    <link href="${staticResourceUrl}/css/custom-z.css${VERSION}" rel="stylesheet">
    <script src="${staticResourceUrl}/js/jquery.min.js${VERSION}"></script>
    <script src="${staticResourceUrl}/js/bootstrap.min.js${VERSION}"></script>
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

                    <label class="col-sm-1 control-label mtop">开始日期:</label>
                    <div class="col-sm-2">
                        <input id="startTime" name="startTime"  class="laydate-icon form-control layer-date">
                    </div>
                    <label class="col-sm-1 control-label mtop">结束日期:</label>
                    <div class="col-sm-2">
                        <input id="endTime" name="endTime"  class="laydate-icon form-control layer-date">
                    </div>

                    <label class="col-sm-1 control-label mtop">手机号:</label>
                    <div class="col-sm-2">
                        <input id="userPhone" name="userPhone"  class="form-control">
                    </div>




                </div>
            </div>


            <div class="row" style="margin-top: 10px;">
                <div class="form-group">
                    <label class="col-sm-1 control-label mtop">企业名称:</label>
                    <div class="col-sm-2">
                        <select class="form-control" name="enterpriseCode" id="enterpriseCode">
                            <option value="">--请选择--</option>
                            <c:forEach items="${enterpriseList}" var="z">
                                <option value="${z.enterpriseCode}">${z.enterpriseName}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-sm-1">
                        <button class="btn btn-primary"  type="button" onclick="query();">
                            <i class="fa fa-search"></i>&nbsp;搜索
                        </button>
                    </div>
                    <div class="col-sm-1">
                        <button class="btn btn-primary"  type="button" onclick="excelMoney();">
                            <i class="fa fa-search"></i>&nbsp;导出
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
                           data-url="finance/moneyListPage">
                        <thead>
                        <tr>

                            <th data-field="bizSn" data-width="10%"
                                data-align="center"><span class="tdfont">流水号</span></th>

                            <th data-field="createTime" data-width="10%" data-formatter="formateDate"
                                data-align="center"><span class="tdfont">分配时间</span></th>
                            <th data-field="title" data-width="10%"
                                data-align="center"><span class="tdfont">操作备注</span></th>

                            <th data-field="bizMoney" data-width="10%" data-formatter="formatPrice"
                                data-align="center"><span class="tdfont">分配金额</span></th>
                            <th data-field="userPhone" data-width="10%"
                                data-align="center"><span class="tdfont">手机号</span></th>
                            <th data-field="userName" data-width="10%"
                                data-align="center"><span class="tdfont">姓名</span></th>
                            <th data-field="enterpriseName" data-width="10%"
                                data-align="center"><span class="tdfont">企业名称</span></th>

                            <th data-field="bizStatus" data-width="10%" data-formatter="formateRechargeStatus"
                                data-align="center"><span class="tdfont">状态</span></th>

                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>




<script>


    // 导出excel
    function excelMoney(){
        var startStr = $("#startTime").val();
        var endStr = $("#endTime").val();

        if (startStr != "") {
            startStr += " 00:00:00";
        }
        if (endStr != "") {
            endStr += " 23:59:59";
        }


        var userPhone =  $("#userPhone").val()



        window.location.href="finance/moneyListExcel?startStr="+startStr + "&endStr=" + endStr + "&userPhone=" + userPhone;
    }



    function formateRechargeStatus(value, row, index) {
        if (value == 1) {
            return "成功";
        } else {
            return "处理中";
        }
    }



    function formatPrice(value, row, index) {
        if (value != null) {
            return (value/100).toFixed(2);
        } else {
            return "-";
        }
    }
    
    $(function () {
        //初始化日期
        CommonUtils.datePickerFormat('startTime');
        CommonUtils.datePickerFormat('endTime');
    });


    function paginationParam(params) {
        var startStr = $("#startTime").val();
        var endStr = $("#endTime").val();


        if (startStr != "") {
            startStr += " 00:00:00";
        }
        if (endStr != "") {
            endStr += " 23:59:59";
        }


//        if (startStr == "") {
//            startStr = undefined;
//        } else {
//            startStr += " 00:00:00";
//        }
//        if (endStr == "") {
//            endStr = undefined;
//        } else{
//            endStr += " 23:59:59";
//        }

        return {
            limit: params.limit,
            page: $("#listTable").bootstrapTable("getOptions").pageNumber,
            startStr: startStr,
            endStr: endStr,
            userPhone: $("#userPhone").val(),
            enterpriseCode: $("#enterpriseCode").val()
        };


    }



    // 格式化时间
    function formateDate(value, row, index) {
        if (value != null) {
            var vDate = new Date(value);
            return vDate.format("yyyy-MM-dd HH:mm:ss");
        } else {
            return "";
        }
    }


    function query() {
        $("#listTable").bootstrapTable("selectPage", 1);
    }


</script>

</body>
</html>
