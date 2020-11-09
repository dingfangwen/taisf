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
            <input type="hidden" id="orderSnP">
            <div class="row">
                <div class="form-group">
                    <label class="col-sm-1 control-label mtop">手机号:</label>
                    <div class="col-sm-2">
                        <input id="userPhoneS" type="text" value="" class="form-control">
                    </div>
                    <label class="col-xs-1 col-sm-1 control-label mtop">配送方式:</label>
                    <div class="col-xs-2 col-sm-2">
                        <select class="form-control" name="isSelfS" id="isSelfS">
                            <option value="">--请选择--</option>
                            <option value="1">--到店自取--</option>
                            <option value="0">--送餐上门--</option>
                        </select>
                    </div>
                    <label class="col-sm-1 control-label mtop">企业名称:</label>
                    <div class="col-sm-2">
                        <select class="form-control" name="enterpriseCode" id="enterpriseCode">
                            <option value="">--请选择--</option>
                            <c:forEach items="${enterpriseList}" var="z">
                                <option value="${z.enterpriseCode}">${z.enterpriseName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <label class="col-xs-1 col-sm-1 control-label mtop">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:</label>
                    <div class="col-xs-2 col-sm-2">
                        <select class="form-control" name="status" id="status">
                            <option value="">--请选择--</option>
                            <option value="10">--待支付--</option>
                            <option value="11">--部分支付--</option>
                            <option value="30">--取消--</option>
                            <option value="40">--已经退款--</option>
                            <option value="41">--退款成功--</option>
                            <option value="42">--退款失败--</option>
                            <option value="50">--已支付--</option>
                            <option value="60">--配送中--</option>
                            <option value="61">--放入餐格--</option>
                            <option value="70">--已签收--</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row" style="margin-top: 10px;">
                <div class="form-group">
                    <label class="col-xs-1 col-sm-1 control-label mtop">订单类型:</label>
                    <div class="col-xs-2 col-sm-2">
                        <select class="form-control" name="orderTypeS"  id="orderTypeS" >
                            <option value="">--请选择--</option>
                            <c:forEach items="${list}" var="z" >
                                <option  value="${z.key}">${z.value}</option>
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

                    <div class="col-sm-1">


                        <button class="btn btn-primary" type="button" onclick="fenxi();">
                            <i class="fa fa-search"></i>&nbsp;下载分析
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
                    <button class="btn btn-primary" type="button"
                            onclick="listOrderExcel();">
                        <!-- <a href="stats/refund/testPoi"> </a>-->
                        导出Excel
                    </button>
                    <table id="listTable" class="table table-bordered" data-click-to-select="true"
                           data-toggle="table" data-side-pagination="server"
                           data-pagination="true" data-page-list="[5,10,20,50]"
                           data-pagination="true" data-page-size="10"
                           data-pagination-first-text="首页" data-pagination-pre-text="上一页"
                           data-pagination-next-text="下一页" data-pagination-last-text="末页"
                           data-content-type="application/x-www-form-urlencoded"
                           data-query-params="paginationParam" data-method="post"
                           data-url="order/pageList">
                        <thead>
                        <tr>
                            <%--<th data-field="id" data-visible="false"></th>--%>
                            <th data-field="orderSn" data-width="10%"
                                data-align="center"><span class="tdfont">订单编号</span></th>
                            <th data-field="createTime" data-width="10%" data-formatter="formatDate"
                                data-align="center"><span class="tdfont">下单时间</span></th>
                            <th data-field="userName" data-width="10%"
                                    data-align="center"><span class="tdfont">用户姓名</span></th>
                            <th data-field="userCode" data-width="10%"
                                    data-align="center"><span class="tdfont">工号</span></th>
                            <th data-field="userTel" data-width="10%"
                                data-align="center"><span class="tdfont">用户电话</span></th>
                            <%--<th data-field="enterpriseCode" data-width="15%"
                                data-align="center"><span class="tdfont">企业编号</span></th>--%>
                            <th data-field="enterpriseName" data-width="15%"
                                data-align="center"><span class="tdfont">企业名称</span></th>
                            <th data-field="sumMoney" data-width="15%" data-formatter="formatAmount"
                                data-align="center"><span class="tdfont">订单金额(元)</span></th>
                            <th data-field="orderTypeStr" data-width="15%"
                                data-align="center"><span class="tdfont">订单类型</span></th>

                                <th data-field="payTypeStr" data-width="15%"
                                data-align="center"><span class="tdfont">支付方式</span></th>
                            <th data-field="isSelf" data-width="15%" data-formatter="formaIsSelf"
                                data-align="center"><span class="tdfont">配送方式</span></th>

                            <th data-field="buffetFid" data-width="5%"
                                data-align="center"><span class="tdfont">餐柜</span></th>

                            <th data-field="cellSn" data-width="5%"
                                    data-align="center"><span class="tdfont">格子</span></th>

                            <th data-field="orderStatus" data-width="5%" data-formatter="formatAccountStatus"
                                data-align="center"><span class="tdfont">状态</span></th>


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
<div class="modal inmodal" id="detailModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="padding: 15px 6px;">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">订单详情</h4>
            </div>
            <div class="ibox-content">
                <div class="row">

                    <div class="form-group">
                        <label class="col-sm-2 control-label mtop">订单编号:</label>
                        <div class="col-sm-4">
                            <input disabled id="orderSnD" type="text" value="" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label mtop">姓名:</label>
                        <div class="col-sm-4">
                            <input disabled id="userNameD" type="text" value="" class="form-control">
                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-sm-2 control-label mtop">工号:</label>
                        <div class="col-sm-4">
                            <input disabled id="userCodeD" type="text" value="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label mtop">配送方式:</label>
                        <div class="col-sm-2">
                            <input disabled id="isSelfD" type="text" value="" class="form-control">
                        </div>
                        <div id="signIn" class="col-sm-2">
                            <button class="btn btn-primary" type="button"
                                    onclick="signIn();">签收
                            </button>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group">
                        <label class="col-sm-2 control-label mtop">创建时间:</label>
                        <div class="col-sm-4">
                            <input disabled id="createTimeD" type="text" value="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label mtop">手机号:</label>
                        <div class="col-sm-4">
                            <input disabled id="userTelDD" type="text" value="" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="row">

                    <div class="form-group">
                        <label class="col-sm-2 control-label mtop">支付方式:</label>
                        <div class="col-sm-4">
                            <input disabled id="payTypeD" type="text" value="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label mtop">订单金额:</label>
                        <div class="col-sm-4">
                            <input disabled id="sumMoneyD" type="text" value="" class="form-control">
                        </div>
                    </div>

                </div>

                <div class="row">

                    <div class="form-group">
                        <label class="col-sm-2 control-label mtop">餐柜:</label>
                        <div class="col-sm-4">
                            <input disabled id="buffetFid" type="text" value="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label mtop">格子编号:</label>
                        <div class="col-sm-4">
                            <input disabled id="cellSnD" type="text" value="" class="form-control">
                        </div>
                    </div>

                </div>


                <div class="row">
                    <div class="form-group">
                        <label class="col-sm-2 control-label mtop">地址:</label>
                        <div class="col-sm-6">
                            <input disabled id="addressD" type="text" value="" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">

                    </div>
                </div>

            </div>

            <!-- 搜索框panel -->
            <div class="ibox float-e-margins">
                <input id="id_enterpriseCode" name="enterpriseCode" type="hidden"
                       class="form-control">
            </div>
            <!-- 列表 -->
            <div class="float-e-margins">
                <div class="ibox-content">
                    <div class="row row-lg">
                        <div class="col-sm-12">
                            <table id="listTableP" class="table table-bordered" data-click-to-select="true"
                                   data-toggle="table" data-side-pagination="server"
                                   data-pagination="true" data-page-list="[5,10,20,50]"
                                   data-pagination="true" data-page-size="10"
                                   data-pagination-first-text="首页" data-pagination-pre-text="上一页"
                                   data-pagination-next-text="下一页" data-pagination-last-text="末页"
                                   data-content-type="application/x-www-form-urlencoded"
                                   data-query-params="paginationParamP" data-method="post"
                                   data-single-select="false"
                                   data-url="order/orderProductPageList">
                                <thead>
                                <tr>
                                    <th data-field="classifyName" data-width="10%"
                                        data-align="center"><span class="tdfont">分类</span></th>
                                    <th data-field="productName" data-width="10%"
                                        data-align="center"><span class="tdfont">菜单名称</span></th>
                                    <th data-field="productNum" data-width="10%"
                                        data-align="center"><span class="tdfont">数量</span></th>
                                    <th data-field="productPrice" data-width="10%" data-formatter="formatAmount"
                                        data-align="center"><span class="tdfont">单价</span></th>
                                </tr>
                                </thead>
                            </table>
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
        $("#signIn").hide();
    });
    var num = 0;
    function initTime() {
        num += 1;
        //昨天的时间
        var day1 = new Date();
        day1.setTime(day1.getTime() - 24 * 60 * 60 * 1000);
        var s1 = day1.getFullYear() + "-" + (day1.getMonth() + 1) + "-" + day1.getDate();
        $("#openTime").val(s1);
    }
    function paginationParam(params) {
        /*if (num == 0) {
            initTime();
        }*/


        var startDate = $("#openTime").val();
        var endDate = $("#tillTime").val();

        var threeDay = 1000 * 60 * 60 * 24 * 30;

        if((endDate==''||endDate==undefined) && (startDate=='' || startDate==undefined)){
            var now = new Date();

            startDate = new Date(now-threeDay).format("yyyy-MM-dd");
            endDate = now.format("yyyy-MM-dd");
            $("#openTime").val(startDate);
            $("#tillTime").val(endDate);
        }
        openTime = $("#openTime").val();
        tillTime = $("#tillTime").val();

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
            page: $("#listTable").bootstrapTable("getOptions").pageNumber,
            openTime: openTime,
            tillTime: tillTime,
            userTel: $("#userPhoneS").val().trim(),
            orderStatus: $("#status").val(),
            isSelf: $("#isSelfS").val(),
            enterpriseCode: $("#enterpriseCode").val().trim(),
            orderType: $("#orderTypeS").val(),
        };
    }
    function paginationParamC(params) {
        return {
            limit: params.limit,
            page: $("#listTableC").bootstrapTable("getOptions").pageNumber,
            manger: $("#UID").val(),
        };
    }
    function formatAmount(value, row, index) {
        return (value / 100).toFixed(2);
    }


    function formaIsSelf(value, row, index) {
        if (value == 1) {
            return "到店自取";
        } else {
            return "送餐上门";
        }
    }

    // 格式化时间
    function formatDate(value, row, index) {
        if (value != null) {
            var _date = new Date(value);
            return _date.format("yyyy-MM-dd HH:mm:ss");
        } else {
            return "-";
        }
    }
    function formatUserRole(value, row, index) {
        if (value == 1) {
            return "普通餐";
        } else {
            return "老板餐";
        }
    }

    function formatOrderType(value, row, index) {
        if (value == 20 || value == 21) {
            return "午餐"
        } else if (value == 30 || value == 31) {
            return "午餐"
        }
    }


    function formatAccountStatus(value, row, index) {
        if (value == 10) {
            return "待支付";
        } else if (value == 11) {
            return "部分支付";
        } else if (value == 30) {
            return "取消";
        } else if (value == 40) {
            return "已经退款";
        } else if (value == 41) {
            return "退款成功";
        } else if (value == 42) {
            return "退款失败";
        } else if (value == 50) {
            return "已支付";
        } else if (value == 60) {
            return "配送中";
        } else if (value == 61) {
            return "已放餐柜";
        } else if (value == 70) {
            return "已签收";
        }
    }
    function showOrderProduct(orderSn) {
        debugger
        $("#orderSnP").val(orderSn)
        $('#detailModal').modal('show');
        queryP();
        //查询订单详情
        $.ajax({
            data: {
                'orderSn': orderSn,
            },
            type: "post",
            dataType: "json",
            url: 'order/getOrderBaseBySn',
            success: function (result) {
                //订单编号,预订人,工号 电话,送餐地址,订单金额,是否自提(是自提并且已经支付,显示签收按钮,异步签收),创建时间
                $('#orderSnD').val(result.orderSn);
                $('#userNameD').val(result.userName);
                $('#userCodeD').val(result.userCode);
                $('#userTelDD').val(result.userTel);
                $('#addressD').val(result.address);
                $('#buffetFidD').val(result.buffetFid);
                $('#cellSnD').val(result.cellSn);



                if (result.sumMoney != null && result.sumMoney != "" && result.sumMoney != undefined) {
                    $('#sumMoneyD').val(((result.sumMoney) / 100).toFixed(2));
                }
                $('#payTypeD').val(result.payTypeStr);

                if (result.isSelf == 1) {
                    $('#isSelfD').val("到店自取");
                    if (result.orderStatus == 50) {
                        //显示签收按钮
                        $("#signIn").show();
                    }
                } else {
                    $('#isSelfD').val("送餐上门");
                }

                if (result.createTime != null) {
                    var _date = new Date(result.createTime);
                    $('#createTimeD').val(_date.format("yyyy-MM-dd HH:mm:ss"));
                }
            },
            error: function (result) {
                layer.alert("未知错误", {icon: 5, time: 2000, title: '提示'});
                $("#saveBtn").removeAttr("disabled");
            }
        });
    }
    function updateOrderStatus(orderSn) {
        //查询订单详情
        $.ajax({
            data: {
                'orderSn': orderSn,
            },
            type: "post",
            dataType: "json",
            url: 'order/updateOrderStatus',
            success: function (result) {
                if (result.code === 0) {
                    layer.alert("操作成功", {icon: 6, time: 3000, title: '提示'});
                    $('#listTable').bootstrapTable('refresh');
                } else {
                    layer.alert("操作失败:" + result.msg, {icon: 5, time: 3000, title: '提示'});
                }
            },
            error: function (result) {
                layer.alert("未知错误", {icon: 5, time: 2000, title: '提示'});
                $("#saveBtn").removeAttr("disabled");
            }
        });
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
        } else if (value == 4) {
            return "汤";
        } else if (value == 5) {
            return "饮品";
        } else if (value == 6) {
            return "主食";
        } else if (value == 7) {
            return "水果";
        }else if (value == 8) {
            return "超市";
        }
    }
    // 操作列
    function formatOperate(value, row, index) {
        var result = "";
        result = result + "<a title='查看' onclick='showOrderProduct(\"" + row.orderSn + "\")'  >查看</a>&nbsp;&nbsp;&nbsp;&nbsp;";
        if(row.orderStatus == 61){
            result = result + "<a title='签收' onclick='updateOrderStatus(\"" + row.orderSn + "\")'  >人工签收</a>&nbsp;&nbsp;&nbsp;&nbsp;";
        }
        return result;
    }
    function paginationParamP(params) {
        return {
            limit: params.limit,
            page: $("#listTableP").bootstrapTable("getOptions").pageNumber,
            orderSn: $("#orderSnP").val(),
        };
    }
    function query() {
        $("#listTable").bootstrapTable("selectPage", 1);
    }
    function queryP() {
        $("#listTableP").bootstrapTable("selectPage", 1);
    }
    function signIn() {
        layer.confirm("确定签收吗", {icon: 6, title: '提示'}, function (index) {
            $.ajax({
                type: "POST",
                url: 'order/signIn',
                dataType: "json",
                traditional: true,
                data: {'orderSn': $("#orderSnP").val()},
                success: function (result) {
                    if (result.code == 0) {
                        layer.alert("操作成功", {icon: 6, time: 2000, title: '提示'});
                        $("#signIn").hide();
                    } else {
                        layer.alert("操作失败", {icon: 6, time: 2000, title: '提示'});
                    }
                },
                error: function (result) {
                    layer.alert("未知错误", {icon: 5, time: 2000, title: '提示'});
                }
            });
            layer.close(index);
        });
    }



    function fenxi(){
        window.location.href="order/listOrderExcelALL";
    }


    function listOrderExcel(){

        var openTime = $("#openTime").val();
        var tillTime = $("#tillTime").val();

        if (openTime == "") {
            openTime = "";
        } else {
            openTime += " 00:00:00";
        }
        if (tillTime == "") {
            tillTime = "";
        } else{
            tillTime += " 23:59:59";
        }
        window.location.href="order/listOrderExcel?"
            +"openTime="+openTime
            +"&tillTime="+tillTime
            +"&userTel="+$("#userPhoneS").val().trim()
            +"&orderStatus="+$("#status").val()
            +"&isSelf="+$("#isSelfS").val()
            + "&enterpriseCode=" + $("#enterpriseCode").val().trim()
//            +"&supplierName="+$("#supplierName").val().trim()
            +"&orderType="+$("#orderTypeS").val();
    }
</script>

</body>
</html>
