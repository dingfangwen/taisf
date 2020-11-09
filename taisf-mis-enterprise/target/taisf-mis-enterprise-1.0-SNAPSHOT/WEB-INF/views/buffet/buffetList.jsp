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

                    <label class="col-sm-1 control-label mtop">ID:</label>
                    <div class="col-sm-2">
                        <input id="ID_S" type="text" value="" class="form-control">
                    </div>
                    <label class="col-xs-1 col-sm-1 control-label mtop">餐柜key:</label>
                    <div class="col-sm-2">
                        <input id="buffet_key_S" type="text" value="" class="form-control">
                    </div>
                     <label class="col-xs-1 col-sm-1 control-label mtop">格子数量:</label>
                    <div class="col-sm-2">
                        <input id="cellNum_S" type="text" value="" class="form-control">
                    </div>
                    <label class="col-xs-1 col-sm-1 control-label mtop">状态:</label>
                    <div class="col-xs-2 col-sm-2">
                        <select class="form-control" name="productClassify" id="status_S">
                            <option value="">--全部--</option>
                            <option value="1">--正常--</option>
                            <option value="0">--暂停--</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row" style="margin-top: 10px;">
                <div class="form-group">
                    <label class="col-xs-1 col-sm-1 control-label mtop">省份:</label>
                    <div class="col-xs-2 col-sm-2">
                        <select class="form-control" style="width:150px;float:left;margin-right:8px;" id="provinceCode_S" name="provinceCode_S" onchange="selectCitys(this,'S')">
                            <option value="">-请选择省-</option>
                            <c:forEach var="pv" items="${provinceList}">
                                <option <c:if test="${model.enterpriseEntity.provinceCode==pv.code}" > selected="selected"</c:if>
                                        value="${pv.code}">${pv.name}</option>
                            </c:forEach>
                        </select>
                    </div><label class="col-xs-1 col-sm-1 control-label mtop">市区:</label>
                    <div class="col-xs-2 col-sm-2">
                        <select class="form-control" style="width:150px;float:left;margin-right:8px;" id="cityCode_S" name="cityCode" >
                            <option value="">-请选择市-</option>
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
                           data-url="buffet/pageList">
                        <thead>
                        <tr>
                            <th data-field="provinceCode" data-visible="false"></th>
                            <th data-field="cityCode" data-visible="false"></th>
                            <th data-field="countyCode" data-visible="false"></th>
                            <th data-field="id" data-visible="false"></th>
                            <th data-field="fid" data-width="10%"
                                data-align="center"><span class="tdfont">餐柜ID</span></th>
                            <th data-field="deviceId" data-width="10%"
                                data-align="center"><span class="tdfont">餐柜key值</span></th>
                            <th data-field="cellNum" data-width="15%"
                                data-align="center"><span class="tdfont">格子数</span></th>
                            <th data-field="address" data-width="10%"
                                data-align="center"><span class="tdfont">地点</span></th>
                            <th data-field="provinceName" data-width="10%"
                                data-align="center"><span class="tdfont">省</span></th>
                            <th data-field="cityName" data-width="10%"
                                data-align="center"><span class="tdfont">市</span></th>
                            <th data-field="status" data-width="10%"  data-formatter="formateStatus"
                                data-align="center"><span class="tdfont">状态</span></th>


                            <th data-field="handle" data-width="10%"  data-formatter="formatLinkStatus"
                                data-align="center"><span class="tdfont">链接状态</span></th>


                            <th data-field="handle" data-width="5%" data-align="center"
                                data-formatter="formatOperate"><span class="tdfont">操作</span></th>

                            <th data-field="handle" data-width="5%" data-align="center"
                                data-formatter="formatOperate2"><span class="tdfont">格子状态</span></th>
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
                <h4 class="modal-title">新增餐柜</h4>
            </div>
            <div class="col-sm-14">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form id="editForm1" class="form-horizontal m-t">

                            <div class="form-group">
                                <label class="col-sm-3 control-label">餐柜ID:</label>
                                <div class="col-sm-8">
                                    <input id="fid_a" name="fid_a" type="text"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">餐柜Key值:</label>
                                <div class="col-sm-8">
                                    <input id="deviceId_a" name="device_Id_a" type="text"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">格子数:</label>
                                <div class="col-sm-8">
                                    <input id="cellNum_a" name="cellNum_a" type="text"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                    <label class="col-sm-3 control-label">地区:</label>
                                <div class="col-sm-8">
                                    <select class="form-control" style="width:110px;float:left;margin-right:8px;" id="provinceCode_A" name="provinceCode" onchange="selectCitys(this,'A')">
                                        <option value="">-请选择省-</option>
                                        <c:forEach var="pv" items="${provinceList}">
                                            <option <c:if test="${model.enterpriseEntity.provinceCode==pv.code}" > selected="selected"</c:if>
                                                    value="${pv.code}">${pv.name}</option>
                                        </c:forEach>
                                    </select>
                                    <select class="form-control" style="width:110px;float:left;margin-right:8px;" id="cityCode_A" name="cityCode"  onchange="selectAreas(this,'A')">
                                        <option value="">-请选择市-</option>
                                    </select>
                                    <select class="form-control" style="width:110px;float:left;margin-right:8px;" id="areaCode_A" name="areaCode" >
                                        <option value="">-请选择区-</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">具体地址:</label>
                                <div class="col-sm-8">
                                    <input id="address_a" name="address_a" type="text"
                                           class="form-control">
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
                <h4 class="modal-title">编辑餐柜</h4>
            </div>
            <div class="col-sm-14">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form id="editForm2" class="form-horizontal m-t">

                            <div class="form-group">
                                <label class="col-sm-3 control-label">餐柜ID:</label>
                                <div class="col-sm-8">
                                    <input id="fid_e" name="fid_a" type="text"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">餐柜Key值:</label>
                                <div class="col-sm-8">
                                    <input id="deviceId_e" name="device_Id_a" type="text"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">格子数:</label>
                                <div class="col-sm-8">
                                    <input id="cellNum_e" name="cellNum_a" type="text"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">地区:</label>
                                <div class="col-sm-8">
                                    <select  class="form-control" style="width:110px;float:left;margin-right:8px;" id="provinceCode_E" name="provinceCode" onchange="selectCitys(this,'E')">
                                        <option value="">-请选择省-</option>
                                        <c:forEach var="pv" items="${provinceList}">
                                            <option value="${pv.code}">${pv.name}</option>
                                        </c:forEach>
                                    </select>
                                    <select class="form-control" style="width:110px;float:left;margin-right:8px;" id="cityCode_E" name="cityCode"  onchange="selectAreas(this,'E')">
                                        <option value="">-请选择市-</option>
                                    </select>
                                    <select class="form-control" style="width:110px;float:left;margin-right:8px;" id="areaCode_E" name="areaCode" >
                                        <option value="">-请选择区-</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">具体地址:</label>
                                <div class="col-sm-8">
                                    <input id="address_e" name="address_a" type="text"
                                           class="form-control">
                                </div>
                            </div>
                            <input type="hidden" class="form-control" id="id_e" name="id" value=""/>
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
<div class="modal inmodal" id="buffetStatusModal" tabindex="-1" role="dialog" aria-hidden="true">

    <div class="modal-dialog">
        <div class="modal-content" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">餐柜状态</h4>
            </div>
            <div class="col-sm-14">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <div class="row row-lg">
                            <!-- Example Pagination -->
                            <div class="col-sm-12">
                                <table id="listTable2" class="table table-bordered" data-click-to-select="true"
                                       data-toggle="table" data-side-pagination="server"
                                       data-pagination="true" data-page-list="[100]"
                                       data-pagination="true" data-page-size="100"
                                       data-pagination-first-text="首页" data-pagination-pre-text="上一页"
                                       data-pagination-next-text="下一页" data-pagination-last-text="末页"
                                       data-content-type="application/x-www-form-urlencoded"
                                       data-query-params="paginationParam2" data-method="post"
                                       data-single-select="true"
                                       data-url="buffet/pageListCell">
                                    <thead>
                                    <tr>
                                        <th data-field="cellNo" data-width="10%"
                                            data-align="center"><span class="tdfont">编号</span></th>

                                        <th data-field="frontDoor" data-width="10%" data-formatter="formateStatusDoor"
                                            data-align="center"><span class="tdfont">翻转门状态</span></th>

                                        <th data-field="backLight" data-width="10%" data-formatter="formateStatusLight"
                                            data-align="center"><span class="tdfont">后厨灯状态</span></th>
                                        <th data-field="sterilamp" data-width="10%" data-formatter="formateStatusLight"
                                            data-align="center"><span class="tdfont">消毒灯状态</span></th>

                                        <th data-field="warm" data-width="10%"  data-formatter="formateStatusWarm"
                                            data-align="center"><span class="tdfont">加热状态</span></th>

                                        <th data-field="handle" data-width="5%" data-align="center"
                                            data-formatter="formatOperateCell"><span class="tdfont">操作</span></th>


                                    </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
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
        return {
            limit: params.limit,
            page: $("#listTable").bootstrapTable("getOptions").pageNumber,
            buffetFid: $("#ID_S").val(),
            deviceId: $("#buffet_key_S").val(),
            cellNum: $("#cellNum_S").val(),
            status: $("#status_S").val(),
            provinceCode: $("#provinceCode_S").val(),
            cityCode: $("#cityCode_S").val(),
        };
    }
    var deviceId = "1";
    var configCode = "aaa";
    function paginationParam2(params) {
        return {
            limit: params.limit,
            page: $("#listTable").bootstrapTable("getOptions").pageNumber,
            deviceId: deviceId,
            configCode: configCode,

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

    function formateStatusDoor(value, row, index) {
//        1. 已开门 2.正在开门 3.已关门 4.正在关门

        if (value == 1) {
            return  "<a href='#' style='color: red'>已开门</a>";
        } else if (value == 2) {
            return  "<a href='#' style='color: green'>正在开门</a>";
        } else if (value == 3) {
            return "已关门";
        } else if (value == 4) {
            return "正在关门";
        }else{
            return "-"
        }
    }

    function formateStatusLight(value, row, index) {
//        1.开灯成功 2.开灯失败 3.关灯成功 4.关灯失败
        if (value == 1) {

            return "<a href='#' style='color: red'>开灯成功</a>";
        } else if (value == 2) {
            return "未开灯";
        } else if (value == 3) {
            return "关灯成功";
        } else if (value == 4) {
            return "关灯失败";
        }else{
            return "-"
        }
    }

    function formateStatusWarm(value, row, index) {
//        加热状态（1.正在加热 2.加热失败）
        if (value == 1) {

            var ll = "<a href='#' style='color: red'>正在加热</a>";
            return ll;
        } else if (value == 2) {
            return "未加热";
        } else{
            return "-"
        }
    }







    function formateStatus(value, row, index) {
        if (value == 1) {
            return "正常";
        } else if (value == 0) {
            return "暂停";
        }else{
            return "其他"
        }
    }

    // 操作列
    function formatLinkStatus(value, row, index) {
        var result =  row.linkStatusDes;
        return result;
    }



    //操作按钮
    function formatOperateCell(value, row, index) {
        var result = "<button onclick='openCell(\""+row.cellNo+"\")' >强制开启</button>&nbsp;&nbsp;&nbsp;&nbsp;";
        return result;
    }



    function formatOperate(value, row, index) {
        var result = "";
        result = result + "<a title='编辑' onclick='toedit(\"" + row.id + "\",\"" + row.fid + "\",\""+row.deviceId+"\",\""+row.cellNum+"\",\""+row.address+"\",\""+row.provinceCode+"\",\""+row.cityCode+"\",\""+row.countyCode+"\")'  data-toggle='modal' data-target='#editModal')>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;";
        return result;
    }
    function formatOperate2(value, row, index) {

        var result = "";
        result = result + "<a title='查看' onclick='detail(\"" + row.id + "\",\"" + row.fid + "\",\""+row.deviceId+"\",\""+row.configCode+"\",\""+row.cellNum+"\",\""+row.address+"\",\""+row.provinceCode+"\",\""+row.cityCode+"\",\""+row.countyCode+"\")'  data-toggle='modal' data-target='#buffetStatusModal')>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;";
        return result;
    }
    //编辑菜品
    function toedit(id,fid,deviceId,cellNum,address,provinceCode,cityCode,countyCode) {
        debugger
        $("#id_e").val(id)
        $("#fid_e").val(fid)
        $("#deviceId_e").val(deviceId)
        $("#cellNum_e").val(cellNum)
        $("#address_e").val(address)

        $('#provinceCode_E').find("option").each(function() {
            $(this).removeAttr("selected");
        });
        $("#provinceCode_E option[value='"+provinceCode+"']").prop("selected",true);
        selectCitys(null,"E");
        $("#cityCode_E option[value='"+cityCode+"']").prop("selected",true);
        selectAreas(null,"E");
        $("#areaCode_E option[value='"+countyCode+"']").prop("selected",true);
    }


    function detail(id,fid,device,config,cellNum,address,provinceCode,cityCode,countyCode) {
        deviceId = device;
        configCode = config;
        query2();
    }


    function openCell(cellNo) {
        $.ajax({
            data: {
                'cellNum': cellNo,
                'deviceId': deviceId,
                'configCode': configCode
            },
            type: "post",
            dataType: "json",
            url: 'buffet/forceOpenCell',
            success: function (result) {
                if (result.code === 0) {
                    layer.alert("操作成功", {icon: 6, time: 2000, title: '提示'});

                } else {
                    layer.alert(result.msg, {icon: 5, time: 2000, title: '提示'});
                }
            },
            error: function (result) {
                layer.alert("未知错误", {icon: 5, time: 2000, title: '提示'});
                $("#saveBtn").removeAttr("disabled");
            }
        });


    }

    function saveProduct() {
        $("#saveBtn").attr("disabled", "disabled");
        if ($("#fid_a").val() == null || $("#fid_a").val() == "") {
            layer.alert("餐柜ID不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        if ($("#deviceId_a").val() == null || $("#deviceId_a").val() == "") {
            layer.alert("餐柜Key值不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        if ($("#cellNum_a").val() == null || $("#cellNum_a").val() == "") {
            layer.alert("格子数不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        if ($("#provinceCode_A").val() == null || $("#provinceCode_A").val() == "") {
            layer.alert("请选择省", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        if ($("#cityCode_A").val() == null || $("#cityCode_A").val() == "") {
            layer.alert("请选择市", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        if ($("#areaCode_A").val() == null || $("#areaCode_A").val() == "") {
            layer.alert("请选择区", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }

        if ($("#address_a").val() == null || $("#address_a").val() == "") {
            layer.alert("具体地址不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        debugger
        $.ajax({
            data: {
                'fid': $("#fid_a").val(),
                'deviceId': $("#deviceId_a").val(),
                'cellNum': $("#cellNum_a").val(),
                'provinceCode': $("#provinceCode_A").val(),
                'provinceName': $("#provinceCode_A option:selected").text(),
                'cityCode': $("#cityCode_A").val(),
                'cityName': $("#cityCode_A option:selected").text(),
                'countyCode': $("#areaCode_A").val(),
                'countyName': $("#areaCode_A option:selected").text(),
                'address': $("#address_a").val(),
            },
            type: "post",
            dataType: "json",
            url: 'buffet/addBuffet',
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
        if ($("#fid_e").val() == null || $("#fid_e").val() == "") {
            layer.alert("餐柜ID不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtnE").removeAttr("disabled");
            return false;
        }
        if ($("#deviceId_e").val() == null || $("#deviceId_e").val() == "") {
            layer.alert("餐柜Key值不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtnE").removeAttr("disabled");
            return false;
        }
        if ($("#cellNum_e").val() == null || $("#cellNum_e").val() == "") {
            layer.alert("格子数不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtnE").removeAttr("disabled");
            return false;
        }
        if ($("#provinceCode_E").val() == null || $("#provinceCode_E").val() == "") {
            layer.alert("请选择省", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtnE").removeAttr("disabled");
            return false;
        }
        if ($("#cityCode_E").val() == null || $("#cityCode_E").val() == "") {
            layer.alert("请选择市", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtnE").removeAttr("disabled");
            return false;
        }
        if ($("#areaCode_E").val() == null || $("#areaCode_E").val() == "") {
            layer.alert("请选择区", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtnE").removeAttr("disabled");
            return false;
        }

        if ($("#address_e").val() == null || $("#address_e").val() == "") {
            layer.alert("具体地址不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtnE").removeAttr("disabled");
            return false;
        }
        debugger
        console.log($("#provinceCode_E").text())
        $.ajax({
            data: {
                'fid': $("#fid_e").val(),
                'id': $("#id_e").val(),
                'deviceId': $("#deviceId_e").val(),
                'cellNum': $("#cellNum_e").val(),
                'provinceCode': $("#provinceCode_E").val(),
                'provinceName': $("#provinceCode_E option:selected").text(),
                'cityCode': $("#cityCode_E").val(),
                'cityName': $("#cityCode_E option:selected").text(),
                'countyCode': $("#areaCode_E").val(),
                'countyName': $("#areaCode_E option:selected").text(),
                'address': $("#address_e").val(),
            },
            type: "post",
            dataType: "json",
            url: 'buffet/updateBuffet',
            success: function (result) {
                if (result.code === 0) {
                    layer.alert("操作成功", {icon: 6, time: 2000, title: '提示'});
                    $('#listTable').bootstrapTable('refresh');
                    $('#editModal').modal('hide');
                    $("input[type=reset]").trigger("click");
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
    function query2() {
        $("#listTable2").bootstrapTable("selectPage", 1);
    }


    function formReset(){
        $('#showImg-1').attr('href', "");
        $("#imgSizeImgSrc-1").attr("src", "");
        $("#imgUrl-1").val("")
    }
    function selectCitys(obj,flag) {
        $("#areaCode_"+flag+" option").remove();
        $("#cityCode_"+flag+" option").remove();
        var p= $('#provinceCode_'+flag+' option:selected') .val();
        $.ajax({
            type: "post",
            async:false,
            contentType: "application/json",
            url: "base/region/listByParentCode",
            data: "{pid:'" +  p + "'}",
            success: function (data) {
                $("#cityCode_"+flag).append("<option value=''>--请选择--</option>");
                json = eval(data)
                for (var i = 0; i < json.length; i++) {
                    var a = "<option value='" + json[i].code + "'>" + json[i].name + "</option>";
                    $("#cityCode_"+ flag).append(a);
                }
                $("#areaCode_"+flag).append("<option value=\"\">--请选择--</option>");
            }
        })
    }

    function selectAreas(obj,flag) {
        $("#areaCode_"+flag+" option").remove();
        var p= $('#cityCode_'+flag+' option:selected') .val();
        $.ajax({
            type: "post",
            async:false,
            contentType: "application/json",
            url: "base/region/listByParentCode",
            data: "{pid:'" +  p + "'}",
            success: function (data) {
                $("#areaCode_"+flag).append("<option value=\"\">--请选择--</option>");
                json = eval(data)
                for (var i = 0; i < json.length; i++) {
                    var a = "<option value='" + json[i].code + "'>" + json[i].name + "</option>";
                    $("#areaCode_"+flag).append(a);
                }
            }
        })
    }

</script>
</body>
</html>
