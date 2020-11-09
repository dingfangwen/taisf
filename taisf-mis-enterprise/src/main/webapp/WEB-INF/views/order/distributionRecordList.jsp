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
    <script src="${staticResourceUrl}/js/jquery.form.js${VERSION}"></script>
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
    <style type="text/css">
        .file {
            position: relative;
            display: inline-block;
            background: #1ab394;
            border: 1px solid #99D3F5;
            border-radius: 4px;
            padding: 4px 12px;
            overflow: hidden;
            color: #FFFFFF;
            text-decoration: none;
            text-indent: 0;
            line-height: 20px;
            margin: 20px 0px 12px 0px;
        }

        .file input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
        }

        .file:hover {
            background: #AADFFD;
            border-color: #78C3F3;
            color: #004974;
            text-decoration: none;
        }

        .content li {
            float: left;
        }
    </style>
</head>

<body class="gray-bg">
<!-- 图片预览continer -->
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
            <div class="row" style="margin-top: 10px;">
                <div class="form-group">
                    <label class="col-xs-1 col-sm-1 control-label mtop">企业:</label>
                    <div class="col-xs-2 col-sm-2">
                        <select class="form-control" name="enterpriseCodeS" id="enterpriseCodeS">
                            <option value="">--全部--</option>
                            <c:forEach var="v" items="${entityList}">
                                <option value="${v.enterpriseCode}">--${v.enterpriseName}--</option>
                            </c:forEach>
                        </select>
                    </div>
                    <label class="col-sm-1 control-label mtop">日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期:</label>
                    <div class="col-sm-2">
                        <input id="openTime" name="openTime" value="" class="laydate-icon form-control layer-date">
                    </div>
                    <div class="col-sm-2">
                        <input id="tillTime" name="tillTime" value="" class="laydate-icon form-control layer-date">
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
                           data-single-select="false"
                           data-url="order/distributionRecord">
                        <thead>
                        <tr>
                            <th data-field="enterpriseCode" data-visible="false"></th>
                            <th data-field="enterpriseName" data-width="10%"
                                data-align="center"><span class="tdfont">企业名称</span></th>
                            <th data-field="orderType" data-width="10%" data-formatter="formatOrderType"
                                data-align="center"><span class="tdfont">供餐信息</span></th>
                            <th data-field="countNum" data-width="10%"
                                data-align="center"><span class="tdfont">订单数量</span></th>
                            <th data-field="successNum" data-width="10%"
                                data-align="center"><span class="tdfont">已完成数量</span></th>
                            <th data-field="dinnerExt" data-width="10%"
                                data-align="center"><span class="tdfont">联系人</span></th>
                            <th data-field="userName" data-width="10%"
                                data-align="center"><span class="tdfont">联系人</span></th>
                            <th data-field="userTel" data-width="10%"
                                data-align="center"><span class="tdfont">联系电话</span></th>
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
<div class="modal inmodal" id="detailModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">


            <div class="modal-header" style="padding: 15px 6px;">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">企业订单列表</h4>
            </div>


            <!-- 搜索框panel -->
            <div class="ibox float-e-margins">
                <input id="id_enterpriseCode" name="enterpriseCode" type="hidden"
                       class="form-control">
            </div>

            <span style="color: red"></span>

            <!-- 列表 -->
            <div class="ibox float-e-margins" style="margin-bottom:0px">
                <div class="ibox-content">
                    <div class="row row-lg">
                        <div class="col-sm-12">
                            <div class="example-wrap">
                                <div class="example">
                                    <!-- 弹出框列表 -->
                                    <table id="listTableEmp" class="table table-bordered" data-click-to-select="true"
                                           data-toggle="table" data-side-pagination="server"
                                           data-pagination="true" data-page-list="[5,10,20,50]"
                                           data-pagination="true" data-page-size="10"
                                           data-pagination-first-text="首页" data-pagination-pre-text="上一页"
                                           data-pagination-next-text="下一页" data-pagination-last-text="末页"
                                           data-content-type="application/x-www-form-urlencoded"
                                           data-query-params="paginationAccountS" data-method="post"
                                           data-single-select="false"
                                           data-url="everydayTask/findListByEnterpriseCode">
                                        <thead>
                                        <tr>
                                            <th data-field="userName" data-width="10%">预订人</th>
                                            <th data-field="userCode" data-width="10%">工号</th>
                                            <th data-field="title" data-width="40%" data-align="center">详情</th>
                                            <th data-field="orderStatus" data-width="10%" data-formatter="formatAccountStatus"
                                                data-align="center"><span class="tdfont">状态</span></th>
                                            <th data-field="sendTime" data-width="10%"
                                                data-align="center" data-formatter="formateDate"><span class="tdfont">配送时间</span></th>

                                            <th data-field="userTel" data-width="10%" data-align="center">电话</th>
                                            <th data-field="address" data-width="20%"
                                                data-align="center">送餐地址
                                            </th>
                                        </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">返回</button>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        //初始化日期
        CommonUtils.datePickerFormat("openTime");
        CommonUtils.datePickerFormat("tillTime");
    });
    function paginationParam(params) {
        var openTime = $("#openTime").val();
        var tillTime = $("#tillTime").val();

        if (openTime == "") {
            openTime = undefined;
        } else {
            openTime += " 00:00:00";
            openTime = openTime.replace(/\-/g,"/");
        }
        if (tillTime == "") {
            tillTime = undefined;
        } else{
            tillTime += " 23:59:59";
            tillTime = tillTime.replace(/\-/g,"/");
        }
        console.log(openTime)
        console.log(tillTime)
        return {
            limit: params.limit,
            openTime: openTime,
            tillTime: tillTime,
            page: $("#listTable").bootstrapTable("getOptions").pageNumber,
            enterpriseCode: $("#enterpriseCodeS").val(),
        };
    }
    function formatOrderType(value, row, index) {
        if (value == 1) {
            return "午餐";
        } else if (value == 2) {
            return "晚餐";
        }
    }

    // 操作列
    function formatOperate(value, row, index) {
        var result = "";
        result = result + "<a title='查看' onclick='detail(\"" + row.enterpriseCode + "\")'>详情</a>";
        return result;
    }

    function toedit(enterpriseCode) {
        layer.confirm("确认配送吗", {
            //icon : iconNum,
            title: '开始配送'
        }, function (index) {
            $.ajax({
                data: {
                    'enterpriseCode': enterpriseCode,
                },
                type: "post",
                dataType: "json",
                url: 'everydayTask/distribution',
                success: function (result) {
                    if (result.code === 0) {
                        layer.alert("配送成功", {icon: 6, time: 2000, title: '提示'});
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
            layer.close(index);
        });
    }

    function paginationAccountS(params) {
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
            tillTime += " 23:59:59";
        return {
            limit: params.limit,
            openTime: openTime,
            tillTime: tillTime,
            page: $("#listTableEmp").bootstrapTable("getOptions").pageNumber,
            enterpriseCode: $("#id_enterpriseCode").val(),
        };

    }

    function detail(enterpriseCode) {
        $("#id_enterpriseCode").val(enterpriseCode)
        $('#detailModal').modal('show');
        queryEmp();
    }

    function query() {
        $("#listTable").bootstrapTable("selectPage", 1);
    }
    function queryEmp() {
        $("#listTableEmp").bootstrapTable("selectPage", 1);
    }

    function formatAccountStatus(value, row, index) {

        var start = ' <span style="color: ';
        var clor = '#1ab394">';
        var name = '-';
        var end = '</span>';

        if (value == 10) {
            name = "待支付";
        } else if (value == 11) {
            name = "部分支付";
        } else if (value == 30) {
            name = "取消";
        } else if (value == 40) {
            name = "已经退款";
        } else if (value == 50) {
            name = "已支付";
        } else if (value == 60) {
            name = "配送中";
        } else if (value == 70) {
            clor = 'red">';
            name = "已签收";
        }
        return start + clor + name + end;
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

</script>
</body>
</html>
