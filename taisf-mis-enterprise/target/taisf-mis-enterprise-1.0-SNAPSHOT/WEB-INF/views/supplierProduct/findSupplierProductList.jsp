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

                    <label class="col-sm-1 control-label mtop">菜&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>
                    <div class="col-sm-2">
                        <input id="productNameS" type="text" value="" class="form-control">
                    </div>
                    <label class="col-xs-1 col-sm-1 control-label mtop">分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类:</label>
                    <div class="col-xs-2 col-sm-2">
                        <select class="form-control" name="productClassify" id="productClassifyS">
                            <option value="">--请选择--</option>
                            <c:forEach items="${productClassifyEntities}" var="z">
                                <option value="${z.classifyCode}">${z.classifyName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <label class="col-xs-1 col-sm-1 control-label mtop">供餐类型:</label>
                    <div class="col-xs-2 col-sm-2">
                        <select class="form-control" name="productClassify" id="productTypeS">
                            <option value="">--请选择--</option>
                            <option value="1">--全部--</option>
                            <option value="2">--老板餐--</option>
                            <option value="3">--员工餐--</option>
                        </select>
                    </div>
                    <label class="col-xs-1 col-sm-1 control-label mtop">午餐/晚餐:</label>
                    <div class="col-xs-2 col-sm-2">
                        <select class="form-control" name="lunchDinner" id="lunchDinner">
                            <option value="">--请选择--</option>
                            <option value="1">--午餐--</option>
                            <option value="2">--晚餐--</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row" style="margin-top: 10px;">
                <div class="form-group">
                    <label class="col-xs-1 col-sm-1 control-label mtop">菜品属性:</label>
                    <div class="col-xs-2 col-sm-2">
                        <select class="form-control" name="productClassify" id="productSourceS">
                            <option value="">--全部--</option>
                            <option value="3">--普通餐--</option>
                            <option value="2">--西餐--</option>
                            <option value="1">--清真--</option>
                        </select>
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


        <div class="float-e-margins">
            <div class="ibox-content">
                <div class="panel-heading">

                    <div class="panel-options">

                        <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" onclick="change(2)">周一</a>
                            </li>
                            <li class=""><a data-toggle="tab" onclick="change(3)">周二</a>

                            </li>
                            <li class=""><a data-toggle="tab" onclick="change(4)">周三</a>

                            </li>
                            <li class=""><a data-toggle="tab" onclick="change(5)">周四</a>
                            </li>
                            <li class=""><a data-toggle="tab" onclick="change(6)">周五</a>
                            </li>
                            <li class=""><a data-toggle="tab" onclick="change(7)">周六</a>
                            </li>
                            <li class=""><a data-toggle="tab" onclick="change(1)">周日</a>
                            </li>
                        </ul>

                    </div>
                </div>
            </div>
        </div>


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
                           data-url="supplierProduct/findPageList">
                        <thead>
                        <tr>
                            <th data-field="for" data-visible="false"></th>
                            <th data-field="productName" data-width="10%"
                                data-align="center"><span class="tdfont">菜单名称</span></th>
                            <th data-field="productType" data-width="15%" data-formatter="formatProductType"
                                data-align="center"><span class="tdfont">供餐类型</span></th>
                            <th data-field="productSource" data-width="10%" data-formatter="formatProductSource"
                                data-align="center"><span class="tdfont">菜品属性</span></th>
                            <th data-field="classifyName" data-width="10%"
                                data-align="center"><span class="tdfont">分类</span></th>
                            <th data-field="forLunch" data-width="10%" data-formatter="formatForLunch"
                                data-align="center"><span class="tdfont">午餐/晚餐</span></th>
                            <th data-field="priceSale" data-width="10%"  data-formatter="formatPrice"
                                data-align="center"><span class="tdfont">单价</span></th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

    var week = 2;
    function change(current) {
        week = current;
        query();
    }

    function paginationParam(params) {
        var openTime = $("#openTime").val();
        var tillTime = $("#tillTime").val();
        if (openTime == "") {
            openTime = undefined;
        } else {
            openTime += " 00:00:00";
        }
        if (tillTime == "") {
            tillTime = undefined;
        } else
            tillTime += " 00:00:00";
        var forLunch = "";
        var forDinner = "";
        if($("#lunchDinner").val() == 1){
            forLunch = 1;
        }
        if($("#lunchDinner").val() == 2){
            forDinner = 1;
        }

        return {
            limit: params.limit,
            page: $("#listTable").bootstrapTable("getOptions").pageNumber,
            openTime: openTime,
            tillTime: tillTime,
            productName: $("#productNameS").val(),
            productClassify: $("#productClassifyS").val(),
            productType: $("#productTypeS").val(),
            productSource: $("#productSourceS").val(),
            week:week,
            forLunch:forLunch,
            forDinner:forDinner,
        };
    }
    function formatForLunch(value, row, index) {
        if(row.forLunch == undefined && row.forDinner == undefined){
            return "";
        }
        var result = "";
        if (row.forLunch == 1) {
            result += "午餐";
        } else{
            result += "";
        }

        if(row.forDinner ==1){
            if(row.forLunch == 1){
                result += "/晚餐";
            }else{
                result += "晚餐";
            }
        }else{
            result += "";
        }
        return result;
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
    function formatProductType(value, row, index) {
        if (value == 1) {
            return "全部";
        } else if (value == 2) {
            return "老板餐";
        } else if (value == 3) {
            return "员工餐";
        }
    }
    function formatProductSource(value, row, index) {
        if (value == 3) {
            return "普通餐";
        } else if (value == 2) {
            return "西餐";
        } else if (value == 1) {
            return "清真";
        }
    }
    function formatProductClassify(value, row, index) {
        if (value == 1) {
            return "大荤";
        } else if (value == 2) {
            return "小荤";
        } else if (value == 3) {
            return "素";
        }else if (value == 4) {
            return "汤";
        }else if (value == 5) {
            return "饮品";
        }else if (value == 6) {
            return "主食";
        }else if (value == 7) {
            return "水果";
        }else if (value == 8) {
            return "超市";
        }
    }


    function formatPrice(value, row, index) {
        if (value != null) {
            return (value/100).toFixed(2);
        } else {
            return "-";
        }
    }


    function query() {
        $("#listTable").bootstrapTable("selectPage", 1);
    }
</script>

</body>
</html>
