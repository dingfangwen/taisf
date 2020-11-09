<%@page import="com.taisf.services.common.valenum.FinanceCheckTypeEnum" %>
<%@page import="com.taisf.services.common.valenum.EnterpriseTypeEnum" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="customtag" uri="http://minsu.ziroom.com" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <base href="${basePath}">
    <title>企业操作页</title>
    <meta name="keywords" content="新增企业  编辑企业">
    <meta name="description" content="新增企业  编辑企业">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit|ie-comp|ie-stand"/>
    <link rel="${staticIconUrl}/shortcut icon" href="${staticIconUrl}/favicon.ico">
    <link href="${staticResourceUrl}/css/bootstrap.min.css${VERSION}001" rel="stylesheet">
    <link href="${staticResourceUrl}/css/font-awesome.css${VERSION}001" rel="stylesheet">
    <link href="${staticResourceUrl}/css/plugins/bootstrap-table/bootstrap-table.min.css${VERSION}001" rel="stylesheet">
    <link href="${staticResourceUrl}/css/bootstrap.min.css${VERSION}001" rel="stylesheet">
    <link href="${staticResourceUrl}/css/plugins/iCheck/custom.css${VERSION}001" rel="stylesheet">
    <link href="${staticResourceUrl}/css/animate.css${VERSION}001" rel="stylesheet">
    <link href="${staticResourceUrl}/css/style.css${VERSION}001" rel="stylesheet">
    <link href="${staticResourceUrl}/css/custom-z.css${VERSION}" rel="stylesheet">
    <style type=text/css>
        .td-title {
            font-weight: bold;
            font-size: 13px
        }

        .table > tbody > tr > td {
            border: 0px solid white
        }

        .hr-line-dashed {
            margin: 0 0;
        }
    </style>
    <!-- 全局js -->
    <script src="${staticResourceUrl}/js/jquery.min.js${VERSION}"></script>
    <script src="${staticResourceUrl}/js/bootstrap.min.js${VERSION}"></script>
    <!-- 自定义js -->
    <script src="${staticResourceUrl}/js/content.js${VERSION}"></script>
    <script src="${staticResourceUrl}/js/common/custom.js${VERSION}"></script>
    <script src="${staticResourceUrl}/js/common/refresh.js${VERSION}"></script>
    <script src="${staticResourceUrl}/js/common/date.proto.js${VERSION}"></script>
    <!-- iCheck -->
    <script src="${staticResourceUrl}/js/plugins/iCheck/icheck.min.js${VERSION}"></script>
    <!-- Bootstrap table -->
    <script src="${staticResourceUrl}/js/plugins/bootstrap-table/bootstrap-table.min.js${VERSION}"></script>
    <script src="${staticResourceUrl}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js${VERSION}"></script>
    <script src="${staticResourceUrl}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js${VERSION}"></script>
    <script src="${staticResourceUrl}/js/common/geography.cascade.js${VERSION}"></script>
    <script src="${staticResourceUrl}/js/jquery.form.js${VERSION}"></script>
    <script src="${staticResourceUrl}/js/plugins/layer/layer.min.js${VERSION}"></script>
    <script src="${staticResourceUrl}/js/common/commonUtils.js${VERSION}001" type="text/javascript"></script>
    <script src="${staticResourceUrl}/js/plugins/validate/jquery.validate.min.js${VERSION}"></script>
    <script src="${staticResourceUrl}/js/plugins/validate/messages_zh.min.js${VERSION}"></script>
    <script src="${staticResourceUrl}/js/plugins/layer/laydate/laydate.js${VERSION}001"></script>
    <link href="${staticResourceUrl}/css/plugins/blueimp/css/blueimp-gallery.min.css" rel="stylesheet">
    <script src="${staticResourceUrl}/js/plugins/blueimp/jquery.blueimp-gallery.min.js"></script>
</head>
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
    <div class="float-e-margins">
        <div class="ibox-content">
            <div class="row row-lg">
                <div class="col-sm-12">
                    <div class="row" style="margin-top: 10px;">
                        <div class="form-group">
                            <label class="col-xs-1 col-sm-1 control-label mtop">组合名称:</label>


                            <input id="week" name="week" type="hidden" value="${week}"
                                   class="form-control">

                            <div class="col-xs-2 col-sm-2">
                                <input id="supplierPackageName" name="supplierPackage" type="text"
                                       class="form-control">
                            </div>
                            <label class="col-xs-1 col-sm-1 control-label mtop">大荤:</label>
                            <div class="col-xs-2 col-sm-2">
                                <select class="form-control" id="dahun" name="supplierCode">
                                    <option value="">-请选择-</option>
                                    <c:if test="${ not empty dahunList}">
                                        <c:forEach var="v" items="${dahunList}">
                                            <option value="${v.id}">${v.productName}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                            <label class="col-xs-1 col-sm-1 control-label mtop">小荤:</label>
                            <div class="col-xs-2 col-sm-2">
                                <select class="form-control" id="xiaohun" name="supplierCode">
                                    <option value="">-请选择-</option>
                                    <c:if test="${ not empty xiaohunList}">
                                        <c:forEach var="v" items="${xiaohunList}">
                                            <option value="${v.id}">${v.productName}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                            <label class="col-xs-1 col-sm-1 control-label mtop">素:</label>
                            <div class="col-xs-2 col-sm-2">
                                <select class="form-control" id="su" name="supplierCode">
                                    <option value="">-请选择-</option>
                                    <c:if test="${ not empty suList}">
                                        <c:forEach var="v" items="${suList}">
                                            <option value="${v.id}">${v.productName}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 10px;">
                        <div class="form-group">
                            <label class="col-xs-1 col-sm-1 control-label mtop">汤:</label>
                            <div class="col-xs-2 col-sm-2">
                                <select class="form-control" id="tang" name="supplierCode">
                                    <option value="">-请选择-</option>
                                    <c:if test="${ not empty tangList}">
                                        <c:forEach var="v" items="${tangList}">
                                            <option value="${v.id}">${v.productName}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                            <label class="col-xs-1 col-sm-1 control-label mtop">饮品:</label>
                            <div class="col-xs-2 col-sm-2">
                                <select class="form-control" id="yinpin" name="supplierCode">
                                    <option value="">-请选择-</option>
                                    <c:if test="${ not empty yinpinList}">
                                        <c:forEach var="v" items="${yinpinList}">
                                            <option value="${v.id}">${v.productName}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                            <label class="col-xs-1 col-sm-1 control-label mtop">主食:</label>
                            <div class="col-xs-2 col-sm-2">
                                <select class="form-control" id="zhushi" name="supplierCode">
                                    <option value="">-请选择-</option>
                                    <c:if test="${ not empty zhushiList}">
                                        <c:forEach var="v" items="${zhushiList}">
                                            <option value="${v.id}">${v.productName}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                            <label class="col-xs-1 col-sm-1 control-label mtop">水果:</label>
                            <div class="col-xs-2 col-sm-2">
                                <select class="form-control" id="shuiguo" name="supplierCode">
                                    <option value="">-请选择-</option>
                                    <c:if test="${ not empty shuiguoList}">
                                        <c:forEach var="v" items="${shuiguoList}">
                                            <option value="${v.id}">${v.productName}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 10px;">
                        <div class="form-group">
                            <label class="col-xs-1 col-sm-1 control-label mtop">套餐价格:</label>
                            <div class="col-xs-2 col-sm-2">
                                <input id="packagePrice" name="packagePrice" type="text"
                                       class="form-control">
                            </div>
                            <label class="col-xs-1 col-sm-1 control-label mtop">上传图片:</label>
                            <div class="col-xs-2 col-sm-2">
                                <form id="editForm1" class="form-horizontal m-t">
                                    <div>
                                        <a id="showImg-1" href="${packageEntity.packagePic }" title="图片" data-gallery="">
                                            <img id='imgSizeImgSrc-1' style="" width="100" height="125"
                                                 src="${packageEntity.packagePic }"/></a>
                                        <input id='imgUrl-1' type="hidden" name="headImg" value="${packageEntity.packagePic}"/>
                                        <a href="javascript:;" class="file">
                                            <input type="file" onchange="uploadPic('1',this.files)" name="pics"
                                                   multiple="multiple"/>
                                            上传图片
                                        </a>
                                    </div>
                                </form>
                            </div>
                            <label class="col-xs-1 col-sm-1 control-label mtop">午餐:</label>
                            <div class="col-xs-2 col-sm-2">
                                <input  style="margin-top: 15;" id="forLunch" name="lunch" type="checkbox" value="1" />
                            </div>
                            <label class="col-xs-1 col-sm-1 control-label mtop">晚餐:</label>
                            <div class="col-xs-2 col-sm-2">
                                <input  style="margin-top: 15;" id="forDinner" name="dinner" type="checkbox" value="1" />
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: 10px;">
                        <div class="col-xs-4 col-sm-4"></div>

                        <div class="col-xs-1 col-sm-1">
                            <button class="btn btn-primary" id="saveBtnE" type="button" onclick="saveSupplierPackage();">
                                保存
                            </button>
                        </div>
                        <div class="col-xs-1 col-sm-1">
                            <button type="button" class="btn btn-white" onclick="toList()" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        function saveSupplierPackage() {
            $("#saveBtnE").attr("disabled", "disabled");
            if ($("#supplierPackageName").val() == null || $("#supplierPackageName").val() == "") {
                layer.alert("组合名称不能为空", {icon: 5, time: 2000, title: '提示'});
                $("#saveBtnE").removeAttr("disabled");
                return false;
            };
            /*if ($("#productClassifyE option:selected").val() == null || $("#productClassifyE option:selected").val() == "") {
                layer.alert("请选择分类", {icon: 5, time: 2000, title: '提示'});
                $("#saveBtnE").removeAttr("disabled");
                return false;
            };*/
            var forLunch = 0;
            if($('#forLunch').is(':checked')) {
                forLunch = 1;
            }
            var forDinner = 0;
            if($('#forDinner').is(':checked')) {
                forDinner = 1;
            }
            $.ajax({
                data: {
                    'title': $("#supplierPackageName").val(),
                    'packagePic': $("#pic").val(),
                    'packagePriceDou': $("#packagePrice").val(),
                    'bigCode': $("#dahun option:selected").val(),
                    'smallCode': $("#xiaohun option:selected").val(),
                    'suCode': $("#su option:selected").val(),
                    'tangCode': $("#tang option:selected").val(),
                    'drinkCode': $("#yinpin option:selected").val(),
                    'foodCode': $("#zhushi option:selected").val(),
                    'fruitCode': $("#shuiguo option:selected").val(),
                    'packagePic':  $("#imgUrl-1").val(),
                    'week': $("#week").val(),
                    'forLunch': forLunch,
                    'forDinner': forDinner
                },
                type: "post",
                dataType: "json",
                url: 'supplierProductPackage/addSupplierProductPackage',
                success: function (result) {
                    if (result.code === 0) {
                        layer.alert("操作成功", {icon: 6, time: 2000, title: '提示'});
                        $("#saveBtnE").removeAttr("disabled");
                        toList();
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

        function callBack(parent) {
            parent.refreshData("listTable");
        }
        function toList() {
            $.callBackParent("supplierProductPackage/list", true, callBack);
        }
        function uploadPic(type, files) {
            //上传图片 异步的  	Jquery.form.js
            console.log("上传图片")
            var options = {
                url: "upload/uploadPics",
                type: "post",
                dataType: "json",
                success: function (data) {
                    $('#showImg-1').attr('href', data[0].fullPath);
                    $("#imgSizeImgSrc-1").attr("src", data[0].fullPath);
                    $("#imgUrl-1").val(data[0].dbPath)
                    console.log(data[0]);
                }
            }
            $("#editForm"+type).ajaxSubmit(options);
        }
    </script>
</body>
</html>
