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


<script>


</script>
>
<body class="gray-bg">

<div class="wrapper wrapper-content animated fadeInRight">


    <div class="ibox">
        <div class="ibox-content">
            <div class="row">
                <div class="form-group">

                    <label class="col-sm-1 control-label mtop">菜名:</label>
                    <div class="col-sm-2">
                        <input id="productNameS" type="text" value="" class="form-control">
                    </div>
                    <label class="col-sm-1 control-label mtop">选择分类:</label>
                    <div class="col-sm-2">
                        <select class="form-control" name="productClassifyS" id="productClassifyS">
                            <option value="">--请选择--</option>
                            <c:forEach items="${productClassifyEntities}" var="z">
                                <option value="${z.classifyCode}">${z.classifyName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <label class="col-xs-1 col-sm-1 control-label mtop">午/晚餐:</label>
                    <div class="col-xs-2 col-sm-2">
                        <select class="form-control" name="lunchDinnerS" id="lunchDinnerS">
                            <option value="">--请选择--</option>
                            <option value="1">--午餐--</option>
                            <option value="2">--晚餐--</option>
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


    <div class="col-sm-9" style="width:100%; padding-left: 0px;">
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
                           data-url="stock/pageList">
                        <thead>
                        <tr>
                            <th data-field="lunchStockId" data-visible="false"></th>
                            <th data-field="lunchProductNum" data-visible="false"></th>

                            <th data-field="dinnerStockId" data-visible="false"></th>
                            <th data-field="dinnerProductNum" data-visible="false"></th>


                            <th data-field="id" data-visible="false"></th>
                            <th data-field="productName" data-width="10%"
                                data-align="center"><span class="tdfont">菜单名称</span></th>
                            <th data-field="classifyName" data-width="10%"
                                data-align="center"><span class="tdfont">分类</span></th>

                            <th data-field="supplierProductType" data-width="10%" data-formatter="formatSupplierProductType"
                                data-align="center"><span class="tdfont">类型</span></th>


                            <th data-field="priceSale" data-width="10%" data-formatter="formatPrice"
                                data-align="center"><span class="tdfont">价格</span></th>
                            <th data-field="forLunch" data-width="10%" data-formatter="formatForLunch"
                                data-align="center"><span class="tdfont">午餐</span></th>
                            <th data-field="lunchProductLimit" data-width="10%" data-formatter="formatLunchStock"
                                data-align="center"><span class="tdfont">库存(剩余库数)</span></th>
                            <th data-field="forDinner" data-width="10%" data-formatter="formatForDinner"
                                data-align="center"><span class="tdfont">晚餐</span></th>
                            <th data-field="dinnerProductLimit" data-width="10%" data-formatter="dinnerProductLimit"
                                data-align="center"><span class="tdfont">库存(剩余库数)</span></th>
                            <th data-field="handle" data-width="15%" data-align="center"
                                data-formatter="formatOperate"><span class="tdfont">操作</span></th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- 编辑 -->
    <div class="modal inmodal" id="editModal" tabindex="-1" role="dialog" aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content animated bounceInRight">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span
                            class="sr-only">关闭</span>
                    </button>
                    <h4 class="modal-title">修改库存</h4>
                </div>
                <div class="col-sm-14">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content">
                            <form id="editForm2" class="form-horizontal m-t">



                                <div class="form-group">
                                    <label class="col-sm-3 control-label">菜单名称:</label>
                                    <div class="col-sm-8">
                                        <input readonly id="productNameE" name="productName" type="text"
                                               class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">午餐:</label>
                                    <div class="col-sm-5">
                                        <input id="lunchProductLimitE" name="lunchProductLimit" type="text"
                                               class="form-control">
                                    </div>
                                    <div class="col-sm-2">
                                        <label class="control-label">库存数</label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">晚餐:</label>
                                    <div class="col-sm-5">
                                        <input id="dinnerProductLimitE" name="dinnerProductLimit" type="text"
                                               class="form-control">
                                    </div>
                                    <div class="col-sm-2">
                                        <label class="control-label">库存数</label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-1 control-label"></label>
                                    <label class="col-sm-5 control-label">(修改库存数时,自动更新剩余库存)</label>
                                </div>

                                <input type="hidden" class="form-control" id="supplierProductTypeE" name="supplierProductType" value=""/>
                                <input type="hidden" class="form-control" id="idE" name="id" value=""/>
                                <input type="hidden" class="form-control" id="lunchStockIdE" name="id" value=""/>
                                <input type="hidden" class="form-control" id="dinnerStockIdE" name="id" value=""/>
                                <!-- 用于 将表单缓存清空 -->
                                <input id="editReset" type="reset" style="display:none;"/>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" id="saveBtnE" type="button" onclick="editSaveProduct();">保存</button>
                    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
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
        var forLunch = "";
        if( $("#lunchDinnerS").val() == 1 ){
            forLunch = 1
        }
        var forDinner = "";
        if( $("#lunchDinnerS").val() == 2 ){
            forDinner = 1
        }

        return {
            limit: params.limit,
            page: $("#listTable").bootstrapTable("getOptions").pageNumber,
            productName: $("#productNameS").val(),
            productClassify: $("#productClassifyS").val(),
            forLunch:forLunch,
            forDinner: forDinner,
            week: week,
        };
    }
    function formatPrice(value, row, index) {
        if (value != null) {
            return (value / 100).toFixed(2);
        } else {
            return "-";
        }
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


    function formatSupplierProductType(value, row, index) {
        if (value == 1){
            return "商品";
        }else if (value == 2){
            return "套餐";
        }
        return value;
    }


    function formatLunchStock(value, row, index) {
        if (row.lunchProductLimit == null || row.lunchProductNum == null) {
            return "0" + "(0)";
        }
        var num = row.lunchProductLimit - row.lunchProductNum;
        var str = '(<span style="color: red;">' + num + '</span>)';
        return row.lunchProductLimit + str;
    }

    function dinnerProductLimit(value, row, index) {
        if (row.dinnerProductLimit == null || row.dinnerProductNum == null) {
            return "0" + "(0)";
        }
        var num = row.dinnerProductLimit - row.dinnerProductNum;
        var str = '(<span style="color: red;">' + num + '</span>)';
        return row.dinnerProductLimit + str;
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
        } else if (value == 4) {
            return "汤";
        } else if (value == 5) {
            return "饮品";
        } else if (value == 6) {
            return "主食";
        } else if (value == 7) {
            return "水果";
        } else if (value == 8) {
            return "超市";
        }
    }
    function formatStatus(value, row, index) {
        if (value == 1) {
            return "已添加";
        } else {
            return "未添加";
        }
    }
    function formatForLunch(value, row, index) {
        var result = "";
        if (row.forLunch == 1) {
            result += "是";
        } else {
            result += "否";
        }

        return result;
    }
    function formatForDinner(value, row, index) {
        var result = "";
        if (row.forDinner == 1) {
            result += "是";
        } else {
            result += "否";
        }

        return result;
    }
    // 操作列
    function formatOperate(value, row, index) {
        var result = "";
        result = result + "<a title='编辑' onclick='toedit(\"" + row.forLunch + "\",\"" + row.forDinner + "\",\"" + row.id + "\",\"" + row.supplierProductType + "\",\"" + row.lunchStockId + "\",\"" + row.lunchProductLimit + "\",\"" + row.dinnerStockId + "\",\"" + row.dinnerProductLimit + "\",\"" + row.productName + "\")'  data-toggle='modal' data-target='#editModal')>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;";
        return result;
    }
    function toedit(forLunch,forDinner,id,supplierProductType,lunchStockId, lunchProductLimit, dinnerStockId, dinnerProductLimit,productName) {
        $("#editReset").trigger("click");

        $("#idE").val(id)

        $("#supplierProductTypeE").val(supplierProductType)
        $("#productNameE").val(productName)
        if(lunchStockId > 0){
            $("#lunchStockIdE").val(lunchStockId)
        }else{
            $("#lunchStockIdE").val("")
        }
        if(lunchProductLimit >= 0){
            $("#lunchProductLimitE").val(lunchProductLimit)
        }
        if(dinnerStockId > 0){
            $("#dinnerStockIdE").val(dinnerStockId)
        }else{
            $("#dinnerStockIdE").val("")
        }
        debugger
        if(dinnerProductLimit >= 0){
            $("#dinnerProductLimitE").val(dinnerProductLimit)
        }
        if(forLunch == 0){
            $('#lunchProductLimitE').attr("readonly","readonly");
            $('#lunchProductLimitE').val("")
            $('#lunchProductLimitE').attr("placeholder","暂不提供午餐");
        }else{
            $('#lunchProductLimitE').removeAttr("readonly");
            $('#lunchProductLimitE').removeAttr("placeholder");
        }

        if(forDinner == 0){
            $('#dinnerProductLimitE').attr("readonly","readonly");
            $('#dinnerProductLimitE').val("")
            $('#dinnerProductLimitE').attr("placeholder","暂不提供晚餐");
        }else{
            $('#dinnerProductLimitE').removeAttr("readonly");
            $('#dinnerProductLimitE').removeAttr("placeholder");
        }
    }


    function editSaveProduct() {
        $("#saveBtnE").attr("disabled", "disabled");
        var r = /^[0-9]*[1-9][0-9]*$/;
        if ($("#lunchProductLimitE").val() == null || $("#lunchProductLimitE").val() == "") {
            if(!document.getElementById("lunchProductLimitE").readOnly){
                layer.alert("午餐库存数量不能为空", {icon: 5, time: 2000, title: '提示'});
                $("#saveBtnE").removeAttr("disabled");
                return false;
            }
        }else{
            if (!r.test($("#lunchProductLimitE").val())) {
                layer.alert("午餐库存数量请输入正整数", {icon: 5, time: 2000, title: '提示'});
                $("#saveBtnE").removeAttr("disabled");
                return false;
            }
        }
        if ($("#dinnerProductLimitE").val() == null || $("#dinnerProductLimitE").val() == "") {
            if(!document.getElementById("dinnerProductLimitE").readOnly){
                layer.alert("晚餐库存数量不能为空", {icon: 5, time: 2000, title: '提示'});
                $("#saveBtnE").removeAttr("disabled");
                return false;
            }
        }else{
            if (!r.test($("#dinnerProductLimitE").val())) {
                layer.alert("晚餐库存数量请输入正整数", {icon: 5, time: 2000, title: '提示'});
                $("#saveBtnE").removeAttr("disabled");
                return false;
            }
        }


        console.log($("#idE").val())
        console.log(week)
        console.log($("#lunchStockIdE").val())
        console.log($("#lunchProductLimitE").val())
        console.log($("#dinnerStockIdE").val())
        console.log($("#dinnerProductLimitE").val())
        $.ajax({
            data: {
                'id': $("#idE").val(),
                'supplierProductType': $("#supplierProductTypeE").val(),
                'week': week,
                'lunchStockId':  $("#lunchStockIdE").val(),
                'lunchProductLimit': $("#lunchProductLimitE").val(),
                'dinnerStockId': $("#dinnerStockIdE").val(),
                'dinnerProductLimit': $("#dinnerProductLimitE").val()
            },
            type: "post",
            dataType: "json",
            url: 'stock/updateStock',
            success: function (result) {
                if (result.code === 0) {
                    layer.alert("操作成功", {icon: 6, time: 2000, title: '提示'});
                    $('#listTable').bootstrapTable('refresh');
                    $('#editModal').modal('hide');
                    $("#editReset").trigger("click");
                    $("#saveBtnE").removeAttr("disabled");
                } else {
                    layer.alert(result.msg, {icon: 5, time: 2000, title: '提示'});
                    $("#saveBtnE").removeAttr("disabled");
                }
            },
            error: function (result) {
                layer.alert("未知错误", {icon: 5, time: 2000, title: '提示'});
                $("#saveBtn").removeAttr("disabled");
            }
        });
    }


    function query() {
        $("#listTable").bootstrapTable("selectPage", 1);
    }
</script>

</body>
</html>
