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
    <input type="hidden" id="enterpriseCode" value="${enterpriseCode}">
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
                           data-url="base/enterpriseAddress/pageList">
                        <thead>
                        <tr>
                            <th data-field="fid" data-width="10%"
                                data-align="center"><span class="tdfont">ID</span></th>
                            <th data-field="isSelf" data-width="10%"  data-formatter="formatSelf"
                                data-align="center"><span class="tdfont">配送方式</span></th>
                            送餐地址:       <th data-field="address" data-width="15%"
                                data-align="center"><span class="tdfont">地址</span></th>
                            <th data-field="conTel" data-width="10%"
                                data-align="center"><span class="tdfont">电话</span></th>
                            <th data-field="conName" data-width="10%"
                                data-align="center"><span class="tdfont">姓名</span></th>
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
                <h4 class="modal-title">新增地址</h4>
            </div>
            <div class="col-sm-14">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form id="editForm1" class="form-horizontal m-t">

                            <div class="form-group">
                                <label class="col-sm-3 control-label">配送方式:</label>
                                <div class="col-sm-8">
                                    <input checked type="radio" value="0" name="isSelf"> 送餐上门
                                    <input type="radio" value="1" name="isSelf"> 到店自取
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">地址:</label>
                                <div class="col-sm-8">
                                    <input id="address" name="address" type="text"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">电话:</label>
                                <div class="col-sm-8">
                                    <input id="conTel" name="conTel" type="text"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">联系人:</label>
                                <div class="col-sm-8">
                                    <input id="conName" name="conName" type="text"
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
                <h4 class="modal-title">编辑地址</h4>
            </div>
            <div class="col-sm-14">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form id="editForm2" class="form-horizontal m-t">

                            <div class="form-group">
                                <label class="col-sm-3 control-label">配送方式:</label>
                                <div class="col-sm-8">
                                    <input checked type="radio" value="0" name="isSelfE"> 送餐上门
                                    <input type="radio" value="1" name="isSelfE"> 到店自取
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">地址:</label>
                                <div class="col-sm-8">
                                    <input id="addressE" name="address" type="text"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">电话:</label>
                                <div class="col-sm-8">
                                    <input id="conTelE" name="conTel" type="text"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">联系人:</label>
                                <div class="col-sm-8">
                                    <input id="conNameE" name="conName" type="text"
                                           class="form-control">
                                </div>
                            </div>
                            <input type="hidden" class="form-control" id="IDE" name="id" value=""/>
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
<script>
    function paginationParam(params) {
        var openTime = $("#openTime").val();
        var tillTime = $("#tillTime").val();


        return {
            limit: params.limit,
            page: $("#listTable").bootstrapTable("getOptions").pageNumber,
            enterpriseCode: $("#enterpriseCode").val(),
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
    function formatPrice(value, row, index) {
        if (value != null) {
            return (value/100).toFixed(2);
        } else {
            return "-";
        }
    }
    function formatSelf(value, row, index) {
        if (value == 1){
            return "到店自取";
        } else {
            return " 送餐上门";
        }
    }

    // 操作列
    function formatOperate(value, row, index) {
        var result = "";
        result = result + "<a title='编辑' onclick='toedit(\"" + row.fid + "\")'  data-toggle='modal' data-target='#editModal')>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;";
        result = result + "<a title='查看' onclick='detail(\"" + row.fid + "\")'  data-toggle='modal' data-target='#detailModal')>删除</a>";
        return result;
    }
    //编辑菜品
    function toedit(fid) {
        console.log(fid)
        $.ajax({
            data: {
                'fid': fid,
            },
            type: "post",
            dataType: "json",
            url: 'base/enterpriseAddress/toedit',
            success: function (result) {
                if (result.code === 0) {
                    $('#IDE').val(result.data.fid);
                    $(":radio[name='isSelfE'][value='" + result.data.isSelf + "']").prop("checked", "checked");
                    $('#addressE').val(result.data.address);
                    $('#conTelE').val(result.data.conTel);
                    $('#conNameE').val(result.data.conName);
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

    function detail(fid) {
        $.ajax({
            data: {
                'fid': fid,
            },
            type: "post",
            dataType: "json",
            url: 'base/enterpriseAddress/deleteByFid',
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

    function saveProduct() {
        $("#saveBtn").attr("disabled", "disabled");
        if ($("#address").val() == null || $("#address").val() == "") {
            layer.alert("地址不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;if ($("#conName").val() == null || $("#conName").val() == "") {
            layer.alert("姓名不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;if ($("#conTel").val() == null || $("#conTel").val() == "") {
            layer.alert("电话不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;

        $.ajax({
            data: {
                'address': $("#address").val(),
                'conName': $("#conName").val(),
                'conTel': $("#conTel").val(),
                'enterpriseCode': $("#enterpriseCode").val(),
                'isSelf': $('input[name="isSelf"]:checked').val(),
            },
            type: "post",
            dataType: "json",
            url: 'base/enterpriseAddress/saveEnterpriseAddress',
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


        if ($("#addressE").val() == null || $("#addressE").val() == "") {
            layer.alert("地址不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtnE").removeAttr("disabled");
            return false;
        }
        ;if ($("#conNameE").val() == null || $("#conNameE").val() == "") {
            layer.alert("姓名不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtnE").removeAttr("disabled");
            return false;
        }
        ;if ($("#conTelE").val() == null || $("#conTelE").val() == "") {
            layer.alert("电话不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtnE").removeAttr("disabled");
            return false;
        }
        ;

        $.ajax({
            data: {
                'fid': $("#IDE").val(),
                'address': $("#addressE").val(),
                'conName': $("#conNameE").val(),
                'conTel': $("#conTelE").val(),
                'isSelf': $('input[name="isSelfE"]:checked').val(),
            },
            type: "post",
            dataType: "json",
            url: 'base/enterpriseAddress/updateEnterpriseAddress',
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
