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
                    <label class="col-sm-1 control-label mtop">日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期:</label>
                    <div class="col-sm-2">
                        <input id="openTime" name="openTime" value="" class="laydate-icon form-control layer-date">
                    </div>
                    <div class="col-sm-2">
                        <input id="tillTime" name="tillTime" value="" class="laydate-icon form-control layer-date">
                    </div>
                    <label class="col-sm-1 control-label mtop">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>
                    <div class="col-sm-2">
                        <input id="name" type="text" value="" class="form-control">
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
                    <button id="addMenuButton" type="button" class="btn btn-primary"
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
                           data-url="user/pageKnightList">
                        <thead>
                        <tr>
                            <th data-field="id" data-visible="false"></th>
                            <th data-field="userUid" data-width="10%"
                                data-align="center"><span class="tdfont">编号</span></th>
                            <th data-field="createTime" data-width="10%" data-formatter="formatDate"
                                data-align="center"><span class="tdfont">日期</span></th>
                            <th data-field="userName" data-width="15%"
                                data-align="center"><span class="tdfont">姓名</span></th>
                            <th data-field="userPhone" data-width="10%"
                                data-align="center"><span class="tdfont">手机号</span></th>
                            <th data-field="2" data-width="10%" data-formatter="formatCompany"
                                data-align="center"><span class="tdfont">维护企业</span></th>
                            <th data-field="1" data-width="10%" data-formatter="formatSupplier"
                                data-align="center"><span class="tdfont">维护供餐商</span></th>
                            <th data-field="userStatus" data-width="10%" data-formatter="formatUserStatus"
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
                <h4 class="modal-title">新增销售员工</h4>
            </div>
            <div class="col-sm-14">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form id="form" class="form-horizontal m-t">

                            <div class="form-group">
                                <label class="col-sm-3 control-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>
                                <div class="col-sm-8">
                                    <input id="userName" name="userName" type="text"
                                           value="${backstageUser.relationName }" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">手&nbsp;&nbsp;&nbsp;机&nbsp;&nbsp;&nbsp;号:</label>
                                <div class="col-sm-8">
                                    <input id="userPhone" name="userPhone" type="text"
                                           value="${backstageUser.relationName }" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">员工编号:</label>
                                <div class="col-sm-8">
                                    <input id="userCode" name="userCode" type="text"
                                           value="" class="form-control">
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-3 control-label">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱:</label>
                                <div class="col-sm-8">
                                    <input id="empMail" name="empMail" type="text"
                                           value="" class="form-control">
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
<div class="modal inmodal" id="detailModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content animated bounceInRight">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">关闭</span>
                </button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="col-sm-14">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form id="formD" class="form-horizontal m-t">
                            <input type="hidden" id="UserUidD" name="UserUidE" value="">

                            <div class="form-group">
                                <label class="col-sm-3 control-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>
                                <div class="col-sm-8">
                                    <input readonly id="userNameD" name="userNameE" type="text"
                                           value="" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
                                <div class="col-sm-8">
                                    <input  id="password-E" name="password-E" type="password"
                                           value="" class="form-control">
                                </div>
                            </div>
                           <%-- <div class="form-group">
                                <label class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
                                <div class="col-sm-8">
                                    <input readonly id="password" name="password" type="text"
                                           value="" class="form-control">
                                </div>
                            </div>--%>
                            <input type="hidden" class="form-control" id="iduserD" name="id" value=""/>
                            <!-- 用于 将表单缓存清空 -->
                            <input id="addResetD" type="reset" style="display:none;"/>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" id="saveBtnD" type="button" onclick="editUserPasword();">保存</button>
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div><div class="modal inmodal" id="editModal" tabindex="-1" role="dialog" aria-hidden="true">
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
            userName: $("#name").val(),
            userStatus: $("#status").val(),
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

    function formatCompany(value, row, index) {
       return "<button  onclick='showEnterprise(\""+row.userUid+"\",\""+row.userName+"\")'   style=\"height:30px;\" type=\"button\" class=\"btn btn-primary\" >查看 </button>"
    }
    function formatSupplier(value, row, index) {
       return "<button  onclick='showSupplier(\""+row.userUid+"\",\""+row.userName+"\")'   style=\"height:30px;\" type=\"button\" class=\"btn btn-primary\" >查看 </button>"
    }
    function formatUserStatus(value, row, index) {
        if(row.userStatus == 1 || row.userStatus == 2){
            return "正常";
        }else{
            return "离职";
        }
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
        result = result + "<a title='查看' onclick='detail(\""+row.id+"\")'  data-toggle='modal' data-target='#detailModal')>修改密码</a>&nbsp;&nbsp;&nbsp;&nbsp;";
        if(row.userStatus == 1 || row.userStatus == 2){
            result = result + "<a title='离职' onclick='updateUserStatus(\""+row.userUid+"\",\"3\")'  >离职</a>&nbsp;&nbsp;&nbsp;&nbsp;";
        }else{
            result = result + "<a title='正常' onclick='updateUserStatus(\""+row.userUid+"\",\"2\")' >可用</a>";
        }
        return result;
    }

    function updateUserStatus(id,status) {
        $.ajax({
            data: {
                'userUid': id,
                'userStatus': status,
            },
            type: "post",
            dataType: "json",
            url: 'user/updateUserStatus',
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
    //编辑员工
    function toedit(id) {
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
                    $('#userNameE').val(result.data.userName);
                    $('#userPhoneE').val(result.data.userPhone);
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
            url: 'user/toedit',
            success: function (result) {
                if (result.code === 0) {
                    $('#UserUidD').val(result.data.userUid);
                    $('#userNameD').val(result.data.userName);
                    $('#userPhoneD').val(result.data.userPhone);
                    $('#userPhoneDD').val(result.data.userPhone);
                    $('#password').val(result.data.userPassword);
                    if(result.data.userStatus == 3){
                        $('#userStatusE').html("离职");
                    }else{
                        $('#userStatusE').html("在职");
                    }
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
        if ($("#userNameE").val() == null || $("#userNameE").val() == "") {
            layer.alert("姓名不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;
        var userPhone = $("#userPhoneE").val();
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
                'userPhone': $("#userPhoneE").val(),
                'userName': $("#userNameE").val(),
            },
            type: "post",
            dataType: "json",
            url: 'user/editUser',
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
    //离职
    function dimission() {
        $.ajax({
            data: {
                'userUid': $("#UserUidD").val(),
            },
            type: "post",
            dataType: "json",
            url: 'user/dimission',
            success: function (result) {
                if (result.code === 0) {
                    layer.alert("操作成功", {icon: 6, time: 2000, title: '提示'});
                    $('#listTable').bootstrapTable('refresh');
                    $('#detailModal').modal('hide');
                    $("#addResetD").trigger("click");
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
    //新增销售员工
    function saveUser() {
        $("#saveBtn").attr("disabled", "disabled");
        if ($("#userName").val() == null || $("#userName").val() == "") {
            layer.alert("姓名不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;
        var userPhone = $("#userPhone").val();
        if (userPhone == null || userPhone == "") {
            layer.alert("手机号不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        ;
        var empMail = $("#empMail").val();
        if (empMail == null || empMail == "") {
            layer.alert("邮箱不能为空", {icon: 5, time: 2000, title: '提示'});
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
            beforeSend: function () {
                var valid = $("#form").valid();
                if (!valid) {
                    $("#saveBtn").removeAttr("disabled");
                    return false;
                }
                return true;
            },
            data: {
                'userPhone': $("#userPhone").val(),
                'userName': $("#userName").val(),
                'empMail': $("#empMail").val(),
                'userCode': $("#userCode").val(),
            },
            type: "post",
            dataType: "json",
            url: 'user/addUser',
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

    function editUserPasword() {
        if ($("#password-E").val() == null || $("#password-E").val() == "") {
            layer.alert("请输入密码", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        $.ajax({
            data: {
                'userUid': $("#UserUidD").val(),
                'userPassword': $("#password-E").val(),
            },
            type: "post",
            dataType: "json",
            url: 'user/editUser',
            success: function (result) {
                if (result.code === 0) {
                    layer.alert("操作成功", {icon: 6, time: 2000, title: '提示'});
                    $('#listTable').bootstrapTable('refresh');
                    $('#detailModal').modal('hide');
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
</script>

</body>
</html>
