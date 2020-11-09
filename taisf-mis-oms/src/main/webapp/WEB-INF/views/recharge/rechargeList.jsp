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


                    <label class="col-xs-1 col-sm-1 control-label mtop">充值类型:</label>
                    <div class="col-xs-2 col-sm-2">
                        <select class="form-control" name="rechargeType" id="rechargeType">
                            <option value="">--请选择--</option>
                            <option value="1">--用户--</option>
                            <option value="2">--企业--</option>
                        </select>
                    </div>

                    <label class="col-sm-1 control-label mtop">手机号:</label>
                    <div class="col-sm-2">
                        <input id="rechargeTel" name="rechargeTel"  class="form-control">
                    </div>

                </div>
            </div>
            <div class="row" style="margin-top: 10px;">
                <div class="form-group">


                    <label class="col-sm-1 control-label mtop">充值流水号:</label>
                    <div class="col-sm-2">
                        <input id="rechargeSn" name="rechargeSn"  class="form-control">
                    </div>

                    <label class="col-sm-1 control-label mtop">企业编号:</label>
                    <div class="col-sm-2">
                        <input id="enterpriseCode" name="enterpriseCode"  class="form-control">
                    </div>
                    <label class="col-sm-1 control-label mtop">企业名称:</label>
                    <div class="col-sm-2">
                        <input id="enterpriseName" name="enterpriseName"  class="form-control">
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
                           data-url="recharge/historyPage">
                        <thead>
                        <tr>
                            <th data-field="rechargeSn" data-width="10%"
                                data-align="center"><span class="tdfont">流水号</span></th>

                            <th data-field="createTime" data-width="20%"
                                data-align="center" data-formatter="formateDate"><p
                                    class="tdfont">充值时间</p></th>

                            <th data-field="rechargeType" data-width="15%" data-formatter="formatRechargeType"
                                data-align="center"><span class="tdfont">充值类型</span></th>
                            <th data-field="enterpriseCode" data-width="10%"
                                data-align="center"><span class="tdfont">企业编号</span></th>
                            <th data-field="enterpriseName" data-width="10%"
                                data-align="center"><span class="tdfont">企业名称</span></th>
                            <th data-field="rechargeTel" data-width="10%"
                                data-align="center"><span class="tdfont">手机号</span></th>
                            <th data-field="totalPrice" data-width="10%" data-formatter="formatAmount"

                                data-align="center"><span class="tdfont">充值金额</span></th>

                            <th data-field="rechargeStatus" data-width="10%" data-formatter="formatRechargeStatus"
                                data-align="center"><span class="tdfont">状态</span></th>

                            <th data-field="createName" data-width="15%" data-align="center"
                                ><span class="tdfont">操作人</span></th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script>

    $(function (){
        //初始化日期
        CommonUtils.datePickerFormat('startTime');
        CommonUtils.datePickerFormat('endTime');
    });

    function formatAmount(value, row, index) {
        return (value / 100).toFixed(2);
    }


    function paginationParam(params) {

        var startTime = $('#startTime').val();
        var endTime = $('#endTime').val();

//

        return {
            startStr: startTime,
            endStr: endTime,
            rechargeSn: $("#rechargeSn").val(),
            rechargeType: $("#rechargeType").find("option:selected").val(),
            enterpriseCode:$("#enterpriseCode").val(),
            enterpriseName:$("#enterpriseName").val(),
            rechargeTel:$("#rechargeTel").val(),
            limit: params.limit,
            page: $("#listTable").bootstrapTable("getOptions").pageNumber
        };
//
//        return {
//            limit: params.limit,
//            page: $("#listTable").bootstrapTable("getOptions").pageNumber,
//            openTime: openTime,
//            tillTime: tillTime,
//            productName: $("#productNameS").val(),
//            productClassify: $("#productClassifyS").val(),
//            productType: $("#productTypeS").val(),
//            productSource: $("#productSourceS").val(),
//        };
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


    function formatRechargeType(value, row, index) {
        if (value == 1) {
            return "企业充值";
        } else if(value == 2){
            return "个人充值";
        }else{
            return "其他";
        }
    }
    function formatRechargeStatus(value, row, index) {
        if (value == 1) {
            return "成功";
        } else if(value == 2){
            return "失败";
        }else {
            return value;
        }
    }

    function query() {
        $("#listTable").bootstrapTable("selectPage", 1);
    }
</script>

</body>
</html>
