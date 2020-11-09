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
                    <label class="col-xs-1 col-sm-1 control-label mtop">档口:</label>
                    <div class="col-xs-2 col-sm-2">
                        <select class="form-control" name="windowCode_s" id="windowCode_s">
                            <option value="">--请选择--</option>
                            <c:forEach items="${supplierWindowEntities}" var="z">
                                <option value="${z.windowCode}">${z.windowName}</option>
                            </c:forEach>
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
        <div class="ibox-content">
            <div class="row row-lg">
                <!-- Example Pagination -->
                <div class="col-sm-12">
                    <button id="addMenuButton" type="button" class="btn btn-primary" onclick="formReset()"
                            data-toggle='modal' data-target='#myModal'>
                        <i class="fa fa-plus"></i>&nbsp;新增
                    </button>
                    <table id="listTable" class="table table-bordered" data-click-to-select="true"
                           data-toggle="table" data-side-pagination="server"
                           data-pagination="true" data-page-list="[5,10,20,50]"
                           data-pagination="true" data-page-size="10"
                           data-pagination-first-text="首页" data-pagination-pre-text="上一页"
                           data-pagination-next-text="下一页" data-pagination-last-text="末页"
                           data-content-type="application/x-www-form-urlencoded"
                           data-query-params="paginationParam" data-method="post"
                           data-single-select="true"
                           data-url="product/pageList">
                        <thead>
                        <tr>
                            <th data-field="id" data-width="10%"
                                data-align="center"><span class="tdfont">ID</span></th>
                            <th data-field="productName" data-width="10%"
                                data-align="center"><span class="tdfont">菜单名称</span></th>
                            <th data-field="productType" data-width="15%" data-formatter="formatProductType"
                                data-align="center"><span class="tdfont">供餐类型</span></th>
                            <th data-field="productSource" data-width="10%" data-formatter="formatProductSource"
                                data-align="center"><span class="tdfont">菜品属性</span></th>
                            <th data-field="productClassify" data-width="10%"
                                data-align="center"><span class="tdfont">分类</span></th>
                             <th data-field="windowCode" data-width="10%"
                                data-align="center"><span class="tdfont">档口</span></th>
                            <th data-field="priceMarket" data-width="10%" data-formatter="formatPrice"
                                data-align="center"><span class="tdfont">销售价</span></th>
                            <th data-field="priceSale" data-width="10%" data-formatter="formatPrice"
                                data-align="center"><span class="tdfont">促销价</span></th>
                            <th data-field="calorie" data-width="10%"
                                data-align="center"><span class="tdfont">卡路里</span></th>
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
<!-- 添加 -->
<div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">

    <div class="modal-dialog">
        <div class="modal-content animated bounceInRight">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">菜单录入</h4>
            </div>
            <div class="col-sm-14">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form id="editForm1" class="form-horizontal m-t">

                            <div class="form-group">
                                <label class="col-sm-3 control-label">菜单名称:</label>
                                <div class="col-sm-8">
                                    <input id="productName" name="productName" type="text"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">选择分类:</label>
                                <div class="col-sm-8">
                                    <select class="form-control" name="productClassify" id="productClassify_a">
                                        <option value="">--请选择--</option>
                                        <c:forEach items="${productClassifyEntities}" var="z">
                                            <option value="${z.classifyCode}">${z.classifyName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">选择档口:</label>
                                <div class="col-sm-8">
                                    <select class="form-control" name="windowCode_a" id="windowCode_a">
                                        <option value="">--请选择--</option>
                                        <c:forEach items="${supplierWindowEntities}" var="z">
                                            <option value="${z.windowCode}">${z.windowName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">供餐类型:</label>
                                <div class="col-sm-8">
                                    <input type="radio" value="1" name="productType"> 全部
                                    <input type="radio" value="2" name="productType"> 老板餐
                                    <input type="radio" value="3" name="productType"> 员工餐(单选)
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">菜品属性:</label>
                                <div class="col-sm-8">
                                    <input type="radio" value="3" name="productSource"> 普通餐
                                    <input type="radio" value="2" name="productSource"> 西餐
                                    <input type="radio" value="1" name="productSource"> 清真(单选)
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">卡路里:</label>
                                <div class="col-sm-8">
                                    <input id="calorie_a" name="calorie_a" type="text"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">销售价(元):</label>
                                <div class="col-sm-8">
                                    <input id="priceMarket" name="priceMarket" type="text"
                                           class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">促销价(元):</label>
                                <div class="col-sm-8">
                                    <input id="priceSale" name="priceSale" type="text"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">描述:</label>
                                <div class="col-sm-8">
                                    <textarea id="productDes" style="width: 100%;height: 80;">描述</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">上传图片:</label>
                                <div class="col-sm-8">
                                    <div>
                                        <a id="showImg-1" href="${headPic }" title="图片" data-gallery="">
                                            <img id='imgSizeImgSrc-1' style="" width="100" height="125"
                                                 src="${headPic }"/></a>
                                        <input id='imgUrl-1' type="hidden" name="headImg" value="${headPic}"/>
                                        <a href="javascript:;" class="file">
                                            <input type="file" onchange="uploadPic('1',this.files)" name="pics"
                                                   multiple="multiple"/>
                                            上传图片
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" class="form-control" id="iduser" name="id" value=""/>
                            <!-- 用于 将表单缓存清空 -->
                            <input id="addReset" type="reset" style="display:none;"/>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" id="saveBtn" type="button" onclick="saveProduct();">保存</button>
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!-- 编辑 -->
<div class="modal inmodal" id="editModal" tabindex="-1" role="dialog" aria-hidden="true">

    <div class="modal-dialog">
        <div class="modal-content animated bounceInRight">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">编辑菜单</h4>
            </div>
            <div class="col-sm-14">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form id="editForm2" class="form-horizontal m-t">

                            <div class="form-group">
                                <label class="col-sm-3 control-label">菜单名称:</label>
                                <div class="col-sm-8">
                                    <input id="productNameE" name="productName" type="text"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">选择分类:</label>
                                <div class="col-sm-8">
                                    <select class="form-control" name="productClassify" id="productClassify_e">
                                        <option value="">--请选择--</option>
                                        <c:forEach items="${productClassifyEntities}" var="z">
                                            <option value="${z.classifyCode}">${z.classifyName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">选择档口:</label>
                                <div class="col-sm-8">
                                    <select class="form-control" name="windowCode_a" id="windowCode_e">
                                        <option value="">--请选择--</option>
                                        <c:forEach items="${supplierWindowEntities}" var="z">
                                            <option value="${z.windowCode}">${z.windowName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">供餐类型:</label>
                                <div class="col-sm-8">
                                    <input type="radio" value="1" name="productTypeE"> 全部
                                    <input type="radio" value="2" name="productTypeE"> 老板餐
                                    <input type="radio" value="3" name="productTypeE"> 员工餐(单选)
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">菜品属性:</label>
                                <div class="col-sm-8">
                                    <input type="radio" value="1" name="productSourceE"> 清真
                                    <input type="radio" value="2" name="productSourceE"> 西餐
                                    <input type="radio" value="3" name="productSourceE">普通餐 (单选)
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">卡路里:</label>
                                <div class="col-sm-8">
                                    <input id="calorie_e" name="calorie_e" type="text"
                                           class="form-control">
                                </div>
                            </div> <div class="form-group">
                                <label class="col-sm-3 control-label">销售价(元):</label>
                                <div class="col-sm-8">
                                    <input id="priceMarketE" name="priceMarket" type="text"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">促销价(元):</label>
                                <div class="col-sm-8">
                                    <input id="priceSaleE" name="priceSale" type="text"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">描述:</label>
                                <div class="col-sm-8">
                                    <textarea id="productDesE" style="width: 100%;height: 80;">描述</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">图片:</label>
                                <div class="col-sm-8">
                                    <div>
                                        <a id="showImg-2" href="${headPic }" title="图片" data-gallery="">
                                            <img id='imgSizeImgSrc-2' style="" width="100" height="125"
                                                 src="${headPic }"/></a>
                                        <input id='imgUrl-2' type="hidden" name="headImg" value="${headPic}"/>
                                        <a href="javascript:;" class="file">
                                            <input type="file" onchange="uploadPic('2',this.files)" name="pics"
                                                   multiple="multiple"/>
                                            上传图片
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" class="form-control" id="productIdE" name="id" value=""/>
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
<!-- 查看详情 -->
<div class="modal inmodal" id="detailModal" tabindex="-1" role="dialog" aria-hidden="true">

    <div class="modal-dialog">
        <div class="modal-content animated bounceInRight">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">菜单详情</h4>
            </div>
            <div class="col-sm-14">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form id="formD" class="form-horizontal m-t">

                            <div class="form-group">
                                <label class="col-sm-3 control-label">菜单名称:</label>
                                <div class="col-sm-8">
                                    <input readonly id="productNameD" name="productName" type="text"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">选择分类:</label>
                                <div class="col-sm-8">
                                    <select class="form-control" name="productClassify" id="productClassify_d" disabled>
                                        <option value="">--请选择--</option>
                                        <c:forEach items="${productClassifyEntities}" var="z">
                                            <option value="${z.classifyCode}">${z.classifyName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">选择档口:</label>
                                <div class="col-sm-8">
                                    <select class="form-control" name="windowCode_a" id="windowCode_d" disabled>
                                        <option value="">--请选择--</option>
                                        <c:forEach items="${supplierWindowEntities}" var="z">
                                            <option value="${z.windowCode}">${z.windowName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">供餐类型:</label>
                                <div class="col-sm-8">
                                    <input disabled="disabled" type="radio" value="1" name="productTypeD"> 全部
                                    <input disabled="disabled" type="radio" value="2" name="productTypeD"> 老板餐
                                    <input disabled="disabled" type="radio" value="3" name="productTypeD"> 员工餐(单选)
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">菜品属性:</label>
                                <div class="col-sm-8">
                                    <input disabled="disabled" type="radio" value="1" name="productSourceD"> 清真餐
                                    <input disabled="disabled" type="radio" value="2" name="productSourceD"> 西餐
                                    <input disabled="disabled" type="radio" value="3" name="productSourceD"> 普通餐(单选)
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">卡路里:</label>
                                <div class="col-sm-8">
                                    <input readonly id="calorie_d" name="priceMarket" type="text"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">销售价(元):</label>
                                <div class="col-sm-8">
                                    <input readonly id="priceMarketD" name="priceMarket" type="text"
                                           class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">促销价(元):</label>
                                <div class="col-sm-8">
                                    <input readonly id="priceSaleD" name="priceSale" type="text"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">描述:</label>
                                <div class="col-sm-8">
                                    <textarea readonly id="productDesD" style="width: 100%;height: 80px;">描述</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">上传图片:</label>
                                <div class="col-sm-8">
                                    <div>
                                        <a id="showImg-3" href="${headPic }" title="图片" data-gallery="">
                                            <img id='imgSizeImgSrc-3' style="" width="100" height="125"
                                                 src="${headPic }"/></a>
                                        <input id='imgUrl-3' type="hidden" name="headImg" value="${headPic}"/>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" class="form-control" id="productIdD" name="id" value=""/>
                            <!-- 用于 将表单缓存清空 -->
                            <input id="editReset" type="reset" style="display:none;"/>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script>
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

        return {
            limit: params.limit,
            page: $("#listTable").bootstrapTable("getOptions").pageNumber,
            openTime: openTime,
            tillTime: tillTime,
            productName: $("#productNameS").val(),
            productClassify: $("#productClassifyS").val(),
            windowCode: $("#windowCode_s").val(),
            productType: $("#productTypeS").val(),
            productSource: $("#productSourceS").val(),
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
        if (value == 1) {
            return "清真";
        } else if (value == 2) {
            return "西餐";
        } else if (value == 3) {
            return "普通";
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
    // 操作列
    function formatOperate(value, row, index) {
        var result = "";
        result = result + "<a title='编辑' onclick='toedit(" + row.id + ")'  data-toggle='modal' data-target='#editModal')>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;";
        result = result + "<a title='查看' onclick='detail(" + row.id + ")'  data-toggle='modal' data-target='#detailModal')>查看</a>";
        return result;
    }
    //编辑菜品
    function toedit(id) {
        $.ajax({
            data: {
                'id': id,
            },
            type: "post",
            dataType: "json",
            url: 'product/toedit',
            success: function (result) {

                if (result.code === 0) {
                    debugger
                    $('#productIdE').val(result.data.id);
                    $('#productNameE').val(result.data.productName);
                    $('#productClassify_e').val(result.data.productClassify);
                    $('#windowCode_e').val(result.data.windowCode);
                    $(":radio[name='productTypeE'][value='" + result.data.productType + "']").prop("checked", "checked");
                    $(":radio[name='productSourceE'][value='" + result.data.productSource + "']").prop("checked", "checked");
                    var price = (result.data.priceSale/100).toFixed(2);
                    $('#priceSaleE').val(price);
                    var priceMarket = (result.data.priceMarket/100).toFixed(2);
                    $('#priceMarketE').val(priceMarket);
                    $('#productDesE').val(result.data.productDes);

                    $('#showImg-2').attr('href', result.data.productPic);
                    $("#imgSizeImgSrc-2").attr("src", result.data.productPic);
                    $("#imgUrl-2").val(result.data.productPic)
                    $("#calorie_e").val(result.data.calorie)
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

    function detail(id) {
        $.ajax({
            data: {
                'id': id,
            },
            type: "post",
            dataType: "json",
            url: 'product/toedit',
            success: function (result) {
                if (result.code === 0) {
                    $('#productIdD').val(result.data.id);
                    $('#productNameD').val(result.data.productName);
                    $('#productClassify_d').val(result.data.productClassify);
                    $('#windowCode_d').val(result.data.windowCode);
                    $(":radio[name='productTypeD'][value='" + result.data.productType + "']").prop("checked", "checked");
                    $(":radio[name='productSourceD'][value='" + result.data.productSource + "']").prop("checked", "checked");
                    var price = (result.data.priceSale/100).toFixed(2);
                    $('#priceSaleD').val(price);

                    var priceMarket = (result.data.priceMarket/100).toFixed(2);
                    $('#priceMarketD').val(priceMarket);

                    $('#productDesD').val(result.data.productDes);

                    $('#showImg-3').attr('href', result.data.productPic);
                    $("#imgSizeImgSrc-3").attr("src", result.data.productPic);
                    $("#imgUrl-3").val(result.data.productPic)
                    $("#calorie_d").val(result.data.calorie)
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


    //新增销售员工
    function saveProduct() {
        $("#saveBtn").attr("disabled", "disabled");
        if ($("#productName").val() == null || $("#productName").val() == "") {
            layer.alert("菜品名称不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;
        if ($("#productClassify_a option:selected").val() == null || $("#productClassify_a option:selected").val() == "") {
            layer.alert("请选择分类", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ; if ($("#windowCode_a option:selected").val() == null || $("#windowCode_a option:selected").val() == "") {
            layer.alert("请选择档口", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;

        var temp = document.getElementsByName("productType");
        var productType;
        for (var i = 0; i < temp.length; i++) {
            if (temp[i].checked)
                productType = temp[i].value;
        }
        if (productType == null || productType == "") {
            layer.alert("请选择供餐类型", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;
        if ($('input[name="productSource"]:checked').val() == null || $('input[name="productSource"]:checked').val() == "") {
            layer.alert("请选择菜品属性", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;

        if ($("#priceMarket").val() == null || $("#priceMarket").val() == "") {
            layer.alert("销售价不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;

        if ($("#priceSale").val() == null || $("#priceSale").val() == "") {
            layer.alert("促销价不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;
        if ($("#productDes").val() == null || $("#productDes").val() == "") {
            layer.alert("描述不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;
        var reg = new RegExp("^[0-9]*$");
        var calorie_a = document.getElementById("calorie_a");
        if (calorie_a.value == null || calorie_a.value == "") {
            layer.alert("卡路里不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        if(!reg.test(calorie_a.value)){
            layer.alert("卡路里输入必须为整数!", {icon: 5,time: 2000, title:'提示'});
            $("#saveBtn").removeAttr("disabled")
            return false;
        }
        debugger
        $.ajax({
            data: {
                'productName': $("#productName").val(),
                'productClassify': $("#productClassify_a option:selected").val(),
                'windowCode': $("#windowCode_a option:selected").val(),
                'productType': productType,
                'productSource': $('input[name="productSource"]:checked').val(),
                'price': $("#priceSale").val(),
                'priceOrg': $("#priceMarket").val(),
                'productDes': $("#productDes").val(),
                'productPic': $("#imgUrl-1").val(),
                'calorie': $("#calorie_a").val(),
            },
            type: "post",
            dataType: "json",
            url: 'product/addProduct',
            success: function (result) {
                if (result.code === 0) {
                    layer.alert("操作成功", {icon: 6, time: 2000, title: '提示'});
                    $('#listTable').bootstrapTable('refresh');
                    $('#myModal').modal('hide');
                    $("input[type=reset]").trigger("click");
                    $("#saveBtn").removeAttr("disabled");
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
    function editSaveProduct() {
        $("#saveBtnE").attr("disabled", "disabled");
        if ($("#productNameE").val() == null || $("#productNameE").val() == "") {
            layer.alert("菜品名称不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtnE").removeAttr("disabled");
            return false;
        }
        ;
        if ($("#productClassify_e option:selected").val() == null || $("#productClassify_e option:selected").val() == "") {
            layer.alert("请选择分类", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtnE").removeAttr("disabled");
            return false;
        }
        ; if ($("#windowCode_e option:selected").val() == null || $("#windowCode_r option:selected").val() == "") {
            layer.alert("请选择档口", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;

        var temp = document.getElementsByName("productTypeE");
        var productType;
        for (var i = 0; i < temp.length; i++) {
            if (temp[i].checked)
                productType = temp[i].value;
        }
        if (productType == null || productType == "") {
            layer.alert("请选择供餐类型", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtnE").removeAttr("disabled");
            return false;
        }
        ;
        if ($('input[name="productSourceE"]:checked').val() == null || $('input[name="productSourceE"]:checked').val() == "") {
            layer.alert("请选择菜品属性", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtnE").removeAttr("disabled");
            return false;
        }
        ;
        if ($("#priceSaleE").val() == null || $("#priceSaleE").val() == "") {
            layer.alert("促销价不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtnE").removeAttr("disabled");
            return false;
        }
        ;

        if ($("#priceMarketE").val() == null || $("#priceMarketE").val() == "") {
            layer.alert("原价不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtnE").removeAttr("disabled");
            return false;
        }
        ;


        if ($("#productDesE").val() == null || $("#productDesE").val() == "") {
            layer.alert("描述不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtnE").removeAttr("disabled");
            return false;
        }
        ;
        var reg = new RegExp("^[0-9]*$");
        var calorie_e = document.getElementById("calorie_e");
        if (calorie_e.value == null || calorie_e.value == "") {
            layer.alert("卡路里不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtnE").removeAttr("disabled");
            return false;
        }
        if(!reg.test(calorie_e.value)){
            layer.alert("卡路里输入必须为整数!", {icon: 5,time: 2000, title:'提示'});
            $("#saveBtnE").removeAttr("disabled")
            return false;
        }
        $.ajax({
            data: {
                'productName': $("#productNameE").val(),
                'productClassify': $("#productClassify_e option:selected").val(),
                'windowCode': $("#windowCode_e option:selected").val(),
                'productType': productType,
                'productSource': $('input[name="productSourceE"]:checked').val(),
                'price': $("#priceSaleE").val(),
                'priceOrg': $("#priceMarketE").val(),
                'productDes': $("#productDesE").val(),
                'id': $("#productIdE").val(),
                'productPic': $("#imgUrl-2").val(),
                'calorie': $("#calorie_e").val(),
            },
            type: "post",
            dataType: "json",
            url: 'product/updateProduct',
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

    function uploadPic(type, files) {
        //上传图片 异步的  	Jquery.form.js
        console.log("上传图片")
        var options = {
            url: "upload/uploadPics",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (type == 1) {
                    $('#showImg-1').attr('href', data[0].fullPath);
                    $("#imgSizeImgSrc-1").attr("src", data[0].fullPath);
                    $("#imgUrl-1").val(data[0].dbPath)
                } else {
                    $('#showImg-2').attr('href', data[0].fullPath);
                    $("#imgSizeImgSrc-2").attr("src", data[0].fullPath);
                    $("#imgUrl-2").val(data[0].dbPath)
                }
                console.log(data[0]);
            }
        }
        $("#editForm"+type).ajaxSubmit(options);
    }

    function formReset(){
        $('#showImg-1').attr('href', "");
        $("#imgSizeImgSrc-1").attr("src", "");
        $("#imgUrl-1").val("")
    }

</script>
</body>
</html>
