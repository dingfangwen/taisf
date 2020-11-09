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
                    <label class="col-sm-1 control-label mtop">手机号:</label>
                    <div class="col-sm-2">
                        <input id="userPhoneS" type="text" value="" class="form-control">
                    </div>
                    <label class="col-xs-1 col-sm-1 control-label mtop">餐食标准:</label>
                    <div class="col-xs-2 col-sm-2">
                        <select class="form-control" name="userRole" id="userRole">
                            <option value="">--请选择--</option>
                            <option value="1">--普通餐--</option>
                            <option value="2">--老板餐--</option>
                        </select>
                    </div>
                    <label class="col-sm-1 control-label mtop">企业名称:</label>
                    <div class="col-sm-2">
                        <input id="enterpriseName" type="text" value="" class="form-control">
                    </div>
                    <label class="col-xs-1 col-sm-1 control-label mtop">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:</label>
                    <div class="col-xs-2 col-sm-2">
                        <select class="form-control" name="status" id="status">
                            <option value="">--请选择--</option>
                            <option value="1">--正常--</option>
                            <option value="2">--冻结--</option>
                            <option value="3">--已过期--</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row" style="margin-top: 10px;">
                <div class="form-group">
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
                    <button id="addMenuButton" type="button" class="btn btn-primary" onclick="toAddUser()"
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
                           data-url="user/pageListCompanyUser">
                        <thead>
                        <tr>
                            <%--<th data-field="id" data-visible="false"></th>--%>
                                <th data-field="userUid" data-visible="false"></th>
                            <th data-field="id" data-width="10%"
                                data-align="center"><span class="tdfont">ID</span></th>
                            <th data-field="userCode" data-width="10%"
                                data-align="center"><span class="tdfont">编号</span></th>
                            <th data-field="userName" data-width="10%"
                                data-align="center"><span class="tdfont">姓名</span></th>

                            <th data-field="createTime" data-width="10%" data-formatter="formatDate"
                                data-align="center"><span class="tdfont">注册时间</span></th>
                            <th data-field="userPhone" data-width="10%"
                                data-align="center"><span class="tdfont">手机号</span></th>
                            <th data-field="enterpriseName" data-width="15%"
                                data-align="center"><span class="tdfont">企业</span></th>
                            <th data-field="userRole" data-width="15%" data-formatter="formatUserRole"
                                data-align="center"><span class="tdfont">餐食标准</span></th>
                                <th data-field="amount" data-width="15%" data-formatter="formatAmount"
                                data-align="center"><span class="tdfont">账户金额(元)</span></th>
                            <th data-field="userStatus" data-width="10%" data-formatter="formatAccountStatus"
                                data-align="center"><span class="tdfont">状态</span></th>
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
                <h4 class="modal-title">新增企业员工</h4>
            </div>
            <div class="col-sm-14">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form id="form" class="form-horizontal m-t">

                            <div class="form-group">
                                <label class="col-sm-3 control-label">员工编号:</label>
                                <div class="col-sm-8">
                                    <input id="userCode" name="userCode" type="text"
                                           value="${backstageUser.relationName }" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>
                                <div class="col-sm-8">
                                    <input id="userName" name="userName" type="text"
                                           value="${backstageUser.userName }" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">手&nbsp;&nbsp;&nbsp;机&nbsp;&nbsp;&nbsp;号:</label>
                                <div class="col-sm-8">
                                    <input id="userPhoneA" name="userPhoneA" type="text"
                                           value="${backstageUser.relationName }" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">企业编号:</label>
                                <div class="col-sm-8">
                                        <select class="form-control m-b" id="enterpriseCodeA" name="enterpriseCodeA"  ></select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">类型选择:</label>
                                <div class="col-sm-8">
                                    <input type="radio" value="3"  name="productSource" > 普通餐
                                    <input type="radio" value="2"  name="productSource" > 西餐
                                    <input type="radio" value="1"  name="productSource" > 清真(单选)
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">餐费标准:</label>
                                <div class="col-sm-8">
                                    <select class="form-control" name="userRoleA" id="userRoleA">
                                        <option value="">--请选择--</option>
                                        <option value="1">--普通餐--</option>
                                        <option value="2">--老板餐--</option>
                                    </select>
                                </div>
                            </div>
                            <input type="hidden" class="form-control" id="iduser" name="id" value=""/>
                            <input type="hidden" class="form-control" id="UID" name="UID" value=""/>
                            <!-- 用于 将表单缓存清空 -->
                            <input id="addReset" type="reset" style="display:none;"/>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" id="saveBtn" type="button" onclick="saveUser();">保存</button>
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
                <h4 class="modal-title">编辑企业员工</h4>
            </div>
            <div class="col-sm-14">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form id="form" class="form-horizontal m-t">

                            <div class="form-group">
                                <label class="col-sm-3 control-label">员工编号:</label>
                                <div class="col-sm-8">
                                    <input id="userCodeE" name="userCode" type="text"
                                           value="${user.userCode }" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>
                                <div class="col-sm-8">
                                    <input id="userName-E" name="userNameE" type="text"
                                           value="${backstageUser.userName }" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">手&nbsp;&nbsp;&nbsp;机&nbsp;&nbsp;&nbsp;号:</label>
                                <div class="col-sm-8">
                                    <input id="userPhoneU" name="userPhoneA" type="text"
                                           value="${backstageUser.relationName }" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">企业编号:</label>
                                <div class="col-sm-8">
                                    <select class="form-control m-b" id="enterpriseCodeE" name="enterpriseCodeE"  ></select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">类型选择:</label>
                                <div class="col-sm-8">
                                    <input type="radio" value="3"  name="productSourceE" > 普通餐
                                    <input type="radio" value="2"  name="productSourceE" > 西餐
                                    <input type="radio" value="1"  name="productSourceE" > 清真(单选)
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">餐费标准:</label>
                                <div class="col-sm-8">
                                    <select class="form-control" name="userRoleE" id="userRoleE">
                                        <option value="">--请选择--</option>
                                        <option value="1">--普通餐--</option>
                                        <option value="2">--老板餐--</option>
                                    </select>
                                </div>
                            </div>
                            <input type="hidden" class="form-control" id="UIDE" name="UID" value=""/>
                            <!-- 用于 将表单缓存清空 -->
                            <input id="addReset" type="reset" style="display:none;"/>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" id="saveBtn" type="button" onclick="editUser();">保存</button>
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<div class="modal inmodal" id="detailModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content animated bounceInRight">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">编辑销售员工</h4>
            </div>
            <div class="col-sm-14">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form id="formE" class="form-horizontal m-t">
                            <input type="hidden" id="UserUidE" name="UserUidE" value="">

                            <div class="form-group">
                                <label class="col-sm-3 control-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>
                                <div class="col-sm-8">
                                    <input id="userNameE" name="userNameE" type="text"
                                           value="" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">手&nbsp;&nbsp;&nbsp;机&nbsp;&nbsp;&nbsp;号:</label>
                                <div class="col-sm-8">
                                    <input id="userPhoneE" name="userPhone" type="text"
                                           value="" class="form-control">
                                </div>
                            </div>
                            <input type="hidden" class="form-control" id="iduser_eidt" name="id" value=""/>
                            <!-- 用于 将表单缓存清空 -->
                            <input id="addReset_edit" type="reset" style="display:none;"/>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" id="saveBtn_edit" type="button" onclick="editUser();">保存</button>
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script>
    var Listenterprise = null;
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
            userPhone: $("#userPhoneS").val(),
            userRole: $("#userRole").val(),
            enterpriseName: $("#enterpriseName").val(),
        };
    }
    function paginationParamC(params) {
        return {
            limit: params.limit,
            page: $("#listTableC").bootstrapTable("getOptions").pageNumber,
            manger: $("#UID").val(),
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
    function formatUserRole(value, row, index) {
        if (value == 1) {
            return "普通餐";
        } else {
            return "老板餐";
        }
    }

    function formatAmount(value, row, index) {
            return (value/100).toFixed(2);
    }
    function formatAccountStatus(value, row, index) {
        if(value == 1){
            return "可用";
        }else if(value == 2){
            return "激活";
        }else if(value == 3){
            return "注销";
        }else if(value == 4){
            return "冻结";
        }
    }

    function formatCompany(value, row, index) {
       return "<button  onclick='showEnterprise(\""+row.userUid+"\",\""+row.userName+"\")'   style=\"height:30px;\" type=\"button\" class=\"btn btn-primary\" >查看 </button>"
    }
    function formatSupplier(value, row, index) {
       return "<button  onclick='showSupplier(\""+row.userUid+"\",\""+row.userName+"\")'   style=\"height:30px;\" type=\"button\" class=\"btn btn-primary\" >查看 </button>"
    }

    function showEnterprise(userUid,userName) {
        $.openNewTab(new Date().getTime(),"user/toEnterprisePageList?manger="+userUid+"&userName="+userName, "企业列表");

    }
    function showSupplier(userUid,userName) {
        $.openNewTab(new Date().getTime(),"user/toSupplierPageList?manger="+userUid+"&userName="+userName, "供餐商列表");

    }
    // 操作列
    function formatOperate(value, row, index) {
        var result = "";
        result = result + "<a title='编辑' onclick='toedit("+row.id+")'  data-toggle='modal' data-target='#editModal')>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;";
        if(row.userStatus == 1 || row.userStatus == 2){
            result = result + "<a title='禁用' onclick='updateUserStatus(\""+row.userUid+"\",\"3\")'  >注销</a>&nbsp;&nbsp;&nbsp;&nbsp;";
            result = result + "<a title='冻结' onclick='updateUserStatus(\""+row.userUid+"\",\"4\")' >冻结</a>";
        }else if(row.userStatus ==3 || row.userStatus == 4){
            result = result + "<a title='激活' onclick='updateUserStatus(\""+row.userUid+"\",\"2\")' >激活</a>";
        }
        result = result + "&nbsp;&nbsp;&nbsp;&nbsp;";
        if(row.isAdmin == 1){
            result = result + "<a title='激活' onclick='updateAdminStatus(\""+row.userUid+"\",\"0\")' >取消管理员</a>";
        }else {
            result = result + "<a title='激活' onclick='updateAdminStatus(\""+row.userUid+"\",\"1\")' >设置管理员</a>";
        }
        return result;
    }
    //编辑员工
    function toedit(id) {
        toAddUser();
        $.ajax({
            data: {
                'id': id,
            },
            type: "post",
            dataType: "json",
            url: 'user/toedit',
            success: function (result) {
                if (result.code === 0) {
                    $('#UserUidE').val(result.data.userUid);
                    $('#userName-E').val(result.data.userName);
                    $('#userCodeE').val(result.data.userCode);
                    $('#userPhoneU').val(result.data.userPhone);
                    $('#enterpriseCodeE').val(result.data.enterpriseCode);
                    $('#enterpriseNameE').val(result.data.enterpriseName);
                    $('#userRoleE').val(result.data.userRole);
                    $(":radio[name='productSourceE'][value='" + result.data.productSource + "']").prop("checked", "checked");
                    //console.log(Listenterprise.toString())
                    option(Listenterprise,"enterpriseCodeE",result.data.enterpriseCode);
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



    function updateAdminStatus(id,status) {
        $.ajax({
            data: {
                'userUid': id,
                'isAdmin': status,
            },
            type: "post",
            dataType: "json",
            url: 'user/updateAdminUser',
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


    function updateUserStatus(id,status) {
        $.ajax({
            data: {
                'userUid': id,
                'userStatus': status,
            },
            type: "post",
            dataType: "json",
            url: 'user/updateAccountUser',
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

    //编辑保存销售员工
    function editUser() {
        $("#saveBtn").attr("disabled", "disabled");
        if ($("#userCodeE").val() == null || $("#userCodeE").val() == "") {
            layer.alert("员工编号不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;if ($("#userName-E").val() == null || $("#userName-E").val() == "") {
            layer.alert("员工姓名不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;
        if ($("#enterpriseCodeE").val() == "-1") {
            layer.alert("请选择企业", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;
        var userPhone = $("#userPhoneU").val();
        if (userPhone == null || userPhone == "") {
            layer.alert("手机号不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;
        var userPhoneSn = /^(1[34578][0-9])\d{8}$/;
        if (userPhone != null && userPhone != '' && userPhone != undefined) {
            if (!userPhoneSn.test(userPhone)) {
                layer.alert("请输入正确的手机号", {icon: 5, time: 5000, title: '提示'});
                $("#saveBtn").removeAttr("disabled");
                return false;
            }
        }
        $.ajax({
            data: {
                'userUid': $("#UserUidE").val(),
                'userName': $("#userName-E").val(),
                'userCode': $("#userCodeE").val(),
                'userPhone': $("#userPhoneU").val(),
                'enterpriseCode': $("#enterpriseCodeE option:selected").val(),
                'enterpriseName': $("#enterpriseCodeE option:selected").text(),
                'productSource': $('input[name="productSourceE"]:checked').val(),
                'userRole': $("#userRoleE").val(),
            },
            type: "post",
            dataType: "json",
            url: 'user/editCompanyUser',
            success: function (result) {
                if (result.code === 0) {
                    layer.alert("操作成功", {icon: 6, time: 2000, title: '提示'});
                    $('#listTable').bootstrapTable('refresh');
                    $('#editModal').modal('hide');
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

    //新增企业员工
    function saveUser() {
        $("#saveBtn").attr("disabled", "disabled");
        if ($("#userCode").val() == null || $("#userCode").val() == "") {
            layer.alert("员工编号不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;if ($("#userName").val() == null || $("#userName").val() == "") {
            layer.alert("员工姓名不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;
        if ($('input[name="productSource"]:checked').val() == null || $('input[name="productSource"]:checked').val() == "") {
            layer.alert("请选择类型", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;
        var userPhone = $("#userPhoneA").val();
        if (userPhone == null || userPhone == "") {
            layer.alert("手机号不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;
        var userPhoneSn = /^(1[34578][0-9])\d{8}$/;
        if (userPhone != null && userPhone != '' && userPhone != undefined) {
            if (!userPhoneSn.test(userPhone)) {
                layer.alert("请输入正确的手机号", {icon: 5, time: 5000, title: '提示'});
                $("#saveBtn").removeAttr("disabled");
                return false;
            }
        }
        if($("#enterpriseCodeA").val() == "-1"){
            layer.alert("请选择企业", {icon: 5, time: 3000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        $.ajax({
            beforeSend: function () {
                var valid = $("#form").valid();
                if (!valid) {
                    $("#saveBtn").removeAttr("disabled");
                    return false;
                }
                return true;
            },
            data: {
                'userCode': $("#userCode").val(),
                'userName': $("#userName").val(),
                'userPhone': $("#userPhoneA").val(),
                'enterpriseCode': $("#enterpriseCodeA  option:selected").val(),
                'enterpriseName': $("#enterpriseCodeA  option:selected").text(),
                'productSource': $('input[name="productSource"]:checked').val(),
                'userRole': $("#userRoleA").val(),
            },
            type: "post",
            dataType: "json",
            url: 'user/addCompanyUser',
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
    function query() {
        $("#listTable").bootstrapTable("selectPage", 1);
    }

    function toAddUser() {
        $.ajax({
            type : "post",
            dataType : "json",
            url : 'base/oms/findAll',
            success : function(result) {
                if(result.code === 0){
                    $("#addSaveReset").trigger("click");
                    Listenterprise = result.data;
                    console.log(Listenterprise[0])
                    option(Listenterprise,"enterpriseCodeA",null)
                }else{
                    layer.alert("操作失败:"+result.msg, {icon: 5,time: 2000, title:'提示'});
                }
            },
            error : function(result) {
                layer.alert("未知错误", {icon: 5,time: 2000, title:'提示'});
            }
        });
    }
    function option(list,id,selected){
        var str = "<option value='-1'>请选择</option>";
        for(var i=0;i<list.length;i++) {
            if(selected == list[i].enterpriseCode){
                str = str + "<option  selected value='"+list[i].enterpriseCode+"'>"+list[i].enterpriseName+"</option>";
            }else {
                str = str + "<option  value='"+list[i].enterpriseCode+"'>"+list[i].enterpriseName+"</option>";
            }

        }
        $("#"+id).html(str);
        console.log(str)
    }
</script>

</body>
</html>
