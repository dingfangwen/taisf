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
    <link href="${staticResourceUrl}/css/plugins/blueimp/css/blueimp-gallery.min.css" rel="stylesheet">
    <script src="${staticResourceUrl}/js/plugins/blueimp/jquery.blueimp-gallery.min.js"></script>
    <style type=text/css>
        .tdfont {
            font-size: 13px
        }
    </style>
    <style>
        .lightBoxGallery img {
            margin: 5px;
            width: 160px;
        }

        .room-pic {
            float: left;
        }

        .room-pic p {
            text-align: center;
        }

        .blueimp-gallery > .title {
            left: 0;
            bottom: 45px;
            top: auto;
            width: 100%;
            text-align: center;
        }

        .picline {
            display: inline-block;
        }

        .picjz {
            display: inline-block;
            vertical-align: middle;
        }
    </style>
</head>

<body class="gray-bg">
<div id="blueimp-gallery" class="blueimp-gallery">
    <div class="slides"></div>
    <h3 class="title"></h3>
    <a class="prev">‹</a>
    <a class="next">›</a>
    <a class="close">×</a>
    <a class="play-pause"></a>
    <ol class="indicator"></ol>
</div>
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
                            <option value="1">--大荤--</option>
                            <option value="2">--小荤--</option>
                            <option value="3">--素--</option>
                            <option value="4">--汤--</option>
                            <option value="5">--饮品--</option>
                            <option value="6">--主食--</option>
                            <option value="7">--水果--</option>
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
                    <div class="col-sm-3">
                        <button class="btn btn-primary" type="button" onclick="query();">
                            <i class="fa fa-search"></i>&nbsp;搜索
                        </button>

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
                    <div class="col-sm-3">


                        <button id="addMenuButton" type="button" class="btn btn-primary"
                                onclick="addSupplierPackage();">
                            <i class="fa fa-plus"></i>&nbsp;新增
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
                           data-cache="false"
                           data-url="supplierProductPackage/pageList">
                        <thead>
                        <tr>
                            <th data-field="forDinner" data-visible="false"></th>
                            <th data-field="id" data-width="5%"
                                data-align="center"><span class="tdfont">ID</span></th>
                            <th data-field="title" data-width="10%"
                                data-align="center"><span class="tdfont">组合名称</span></th>
                            <th data-field="forLunch" data-width="10%" data-formatter="formatForLunch"
                                data-align="center"><span class="tdfont">午餐/晚餐</span></th>
                            <th data-field="bigName" data-width="10%"
                                data-align="center"><span class="tdfont">大荤</span></th>
                            <th data-field="smallName" data-width="10%"
                                data-align="center"><span class="tdfont">小荤</span></th>
                            <th data-field="suName" data-width="10%"
                                data-align="center"><span class="tdfont">素</span></th>
                            <th data-field="tangName" data-width="10%"
                                data-align="center"><span class="tdfont">汤</span></th>
                            <th data-field="drinkName" data-width="10%"
                                data-align="center"><span class="tdfont">饮品</span></th>
                            <th data-field="foodName" data-width="10%"
                                data-align="center"><span class="tdfont">主食</span></th>
                            <th data-field="fruitName" data-width="10%"
                                data-align="center"><span class="tdfont">水果</span></th>
                            <th data-field="packagePic" data-width="10%" data-formatter="formatePackagePic"
                                data-align="center"><span class="tdfont">图片</span></th>
                            <th data-field="packagePrice" data-width="10%" data-formatter="formatPrice"
                                data-align="center"><span class="tdfont">价格</span></th>
                            <th data-field="handle" data-width="5%" data-align="center"
                                data-formatter="formatOperate"><span class="tdfont">操作</span></th>
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

    function getWeekName() {
        var name = "";
        if (week == 1){
            name = "周日"
        }else if (week == 2){
            name = "周一"
        }else if (week == 3){
            name = "周二"
        }else if (week == 4){
            name = "周三"
        }else if (week == 5){
            name = "周四"
        }else if (week == 6){
            name = "周五"
        }else if (week == 7){
            name = "周六"
        }
        return name;
    }

    function paginationParam(params) {
        return {
            limit: params.limit,
            page: $("#listTable").bootstrapTable("getOptions").pageNumber,
            productName: $("#productNameS").val(),
            productClassify: $("#productClassifyS").val(),
            productType: $("#productTypeS").val(),
            productSource: $("#productSourceS").val(),
            week:week
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
    // 操作列
    function formatOperate(value, row, index) {
        var result = "";
        result = result + "<a title='编辑' onclick='toeditSupplierPackage(" + row.id + ")')>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;";
        result = result + "<a title='删除' onclick='deleteSupplierPackage(" + row.id + ")')>删除</a>&nbsp;&nbsp;&nbsp;&nbsp;";
        return result;
    }

    function formatePackagePic(value, row, index) {
        var result = "";
        result += "</nobr><a  title=\"图片\"  href='"+value+"'  data-gallery=\"\">";
        result += "<img style='width:100px;height:100px;' src=\""+ value + "\" /></br>";
        result += "</a><nobr>";
        return result;
    }
    function query() {
        $("#listTable").bootstrapTable('destroy');

        $("#listTable").bootstrapTable({
            dataType: "json",
            method: 'post',
            contentType: "application/x-www-form-urlencoded",
            cache: false,
            url:"supplierProductPackage/pageList",
            queryParams: paginationParam,
            columns:[],
            pagination:true,
            sidePagination:'server',
            pageNumber:1,
            pageSize:10,
            pageList:[5,10,20,50]
        })


        $("#listTable").bootstrapTable("selectPage", 1);
    }

    //跳转添加组合套餐页面
    function addSupplierPackage() {
        var $table = $('#listTable');
        var len = $table.bootstrapTable('getData').length;
//        if (len >= 5) {
//            layer.alert("最多添加五个套餐", {icon: 5, time: 2000, title: '提示'});
//            return false;
//        }
        var url = "supplierProductPackage/toAdd?week="+week+"&weekName="+getWeekName();

        $.openNewTab(new Date().getTime(), url, "添加组合套餐");
    }

    //跳转编辑组合套餐页面
    function toeditSupplierPackage(id) {
        var url = "supplierProductPackage/toedit?id="+id+"&week=" +week;
        $.openNewTab(new Date().getTime(), url, "编辑组合套餐");
    }

    //删除
    function deleteSupplierPackage(id) {
        $.ajax({
            data: {
                'id': id,
                'week':week
            },
            type: "post",
            dataType: "json",
            url: 'supplierProductPackage/deleteSupplierPackage',
            success: function (result) {
                if (result.code === 0) {
                    layer.alert("操作成功", {icon: 6, time: 2000, title: '提示'});
                    $('#listTable').bootstrapTable('refresh');
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
    function formatPrice(value, row, index) {
        if (value != null) {
            return (value/100).toFixed(2);
        } else {
            return "-";
        }
    }

</script>

</body>
</html>
