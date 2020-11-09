<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <base href="${basePath}">
    <title>平台管理系统</title>
    <meta name="keywords" content="平台管理系统">
    <meta name="description" content="平台管理系统">

    <link rel="${staticResourceUrl}/shortcut icon"
          href="${staticResourceUrl}/favicon.ico">
    <link href="${staticResourceUrl}/css/bootstrap.min.css${VERSION}001"
          rel="stylesheet">
    <link href="${staticResourceUrl}/css/font-awesome.css${VERSION}001"
          rel="stylesheet">
    <link
            href="${staticResourceUrl}/css/plugins/bootstrap-table/bootstrap-table.min.css${VERSION}001"
            rel="stylesheet">
    <link href="${staticResourceUrl}/css/bootstrap.min.css${VERSION}001"
          rel="stylesheet">
    <link
            href="${staticResourceUrl}/css/plugins/iCheck/custom.css${VERSION}001"
            rel="stylesheet">
    <link href="${staticResourceUrl}/css/animate.css${VERSION}001"
          rel="stylesheet">
    <link href="${staticResourceUrl}/css/style.css${VERSION}001"
          rel="stylesheet">
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="float-e-margins">
                <div class="ibox-title">
                    <h5>编辑用户</h5>
                </div>
                <div class="ibox-content">
                    <form id="editForm" class="form-horizontal">
                        <input type="hidden" name="userId" value="${employeeVo.userId }">

                        <div class="form-group">
                            <label class="col-sm-3 control-label">员工姓名:</label>
                            <div class="col-sm-5">
                                <input class="form-control" id="empName" type="text"
                                       name="empName" value="${employeeVo.empName}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">用&nbsp;&nbsp;户&nbsp;&nbsp;名:</label>
                            <div class="col-sm-5">
                                <input class="form-control" id="empMail" type="text"
                                       name="empMail" value="${employeeVo.empMail }"> <label
                                    id="message1" class="control-label" style="color: #F00"></label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="empSex" name="empSex">
                                    <option value="1"
                                            <c:if test="${employeeVo.empSex=='1'}"> selected="selected" </c:if>>男
                                    </option>
                                    <option value="2"
                                            <c:if test="${employeeVo.empSex=='2'}"> selected="selected" </c:if>>女
                                    </option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">手&nbsp;&nbsp;机&nbsp;&nbsp;号:</label>
                            <div class="col-sm-5">
                                <input readonly="readonly" class="form-control" id="empMobile"
                                       type="text" name="empMobile" value="${employeeVo.empMobile}">
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-3 control-label">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色:</label>
                            <div class="col-sm-4" id="roleDiv">
                                <div name="selectedDiv">
                                    <c:forEach items="${employeeVo.roles}" var="role"
                                               varStatus="rowIndex">
                                        <div class="input-group">
                                            <input type="hidden" class="form-control" name="roleFidList"
                                                   value="${role.roleFid }"/> <input readonly="readonly"
                                                                                     type="text" class="form-control"
                                                                                     name="roleName"
                                                                                     value="${role.roleName}"/> <span
                                                class="input-group-btn"><button
                                                type="button" class="btn btn-white"
                                                onclick="deleteRoleDiv(this)">
														<font style="color: red">删除</font>
													</button></span>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <button type="button" class="btn btn-primary"
                                    data-toggle="modal" data-target="#roleModel">角色选择
                            </button>

                        </div>

                        <div class="row">
                            <div class="col-sm-3 col-sm-offset-3">
                                <button id="editBlack" class="btn btn-primary" type="button">保存内容</button>
                            </div>
                            <div class="col-sm-1">
                                <button onclick="toList();" class="btn btn-white" type="button">返回</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 弹出框角色 -->
<div class="modal inmodal fade" id="roleModel" tabindex="-1"
     role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">角色列表</h4>
            </div>
            <div class="ibox-content">
                <div class="row row-lg">
                    <div class="col-sm-12">
                        <div class="example-wrap">
                            <div class="example">
                                <!-- 弹出框列表 -->
                                <table id="roleTable" class="table table-hover table-bordered"
                                       data-click-to-select="true" data-toggle="table"
                                       data-side-pagination="server" data-pagination="true"
                                       data-page-list="[1,20,50]" data-pagination="true"
                                       data-page-size="5" data-show-refresh="true"
                                       data-pagination-first-text="首页" data-pagination-pre-text="上一页"
                                       data-pagination-next-text="下一页" data-pagination-last-text="末页"
                                       data-query-params="rolePaginationParam" data-method="get"
                                       data-classes="table table-hover table-condensed"
                                       data-height="300" data-url="system/ups/showRoles">
                                    <thead>
                                    <tr>
                                        <th data-field="id" data-checkbox="true"></th>
                                        <th data-field="fid" data-visible="false"></th>
                                        <th data-field="roleName" data-align="center">名称</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary"
                        onclick="getSelectRole()" data-dismiss="modal">保存
                </button>
            </div>
        </div>
    </div>
</div>


<!-- 全局js -->
<script src="${staticResourceUrl}/js/jquery.min.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/bootstrap.min.js${VERSION}"></script>

<!-- 自定义js -->
<script src="${staticResourceUrl}/js/content.js${VERSION}"></script>

<!-- iCheck -->
<script
        src="${staticResourceUrl}/js/plugins/iCheck/icheck.min.js${VERSION}"></script>

<!-- Bootstrap table -->

<script
        src="${staticResourceUrl}/js/plugins/bootstrap-table/bootstrap-table.min.js${VERSION}"></script>
<script
        src="${staticResourceUrl}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js${VERSION}"></script>
<script
        src="${staticResourceUrl}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js${VERSION}"></script>
<script
        src="${staticResourceUrl}/js/common/geography.cascade.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/common/refresh.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/common/custom.js${VERSION}"></script>
<script
        src="${staticResourceUrl}/js/plugins/layer/layer.min.js${VERSION}"></script>

<script type="text/javascript">

    //添加用户
    $("#editBlack").click(function () {
        $("#editBlack").attr("disabled", "disabled");

        var empMail = $("#empMail").val();

        if (empMail == '' || empMail == undefined || empMail == null) {
            layer.alert("请填写用户名！", {icon: 5, time: 2000, title: '提示'});
            $("#editBlack").removeAttr("disabled");
            return false;
        }
        if (!/^[A-Za-z0-9]{2,20}$/.test(empMail)) {
            layer.alert("用户名必须为2-20位英文或者数字", {icon: 5, time: 2000, title: '提示'});
            $("#editBlack").removeAttr("disabled");
            return false;
        }
        $.ajax({
            type: "post",
            url: "system/user/editEmployee",
            dataType: "json",
            data: $("#editForm").serializeArray(),
            success: function (result) {
                if (result.code == 0) {
                    layer.alert("修改成功", {icon: 6, time: 2000, title: '提示'});
                    $.callBackParent("system/user/userList", true, callBack);
                } else {
                    layer.alert("操作失败:" + result.msg, {icon: 5, time: 2000, title: '提示'});
                    $("#editBlack").removeAttr("disabled");
                }
            },
            error: function (result) {
                layer.alert("未知错误", {icon: 5, time: 2000, title: '提示'});
                $("#editBlack").removeAttr("disabled");
            }
        });
    })

    function callBack(parent) {
        parent.refreshData("listTable");
    }
    function toList() {
        $.callBackParent("system/user/userList", true, callBack);
    }
    /*员工列表参数*/
    function paginationParam(params) {
        return {
            limit: params.limit,
            offset: params.offset,
            page: $('#listTable').bootstrapTable('getOptions').pageNumber
        };
    }
    /*选择员工*/
    function getSelectEmployee() {
        var selectVar = $('#listTable').bootstrapTable('getSelections');
        $("#empName").val(selectVar[0].empName);
        $("#empId").val(selectVar[0].fid);
    }
    /*角色列表参数*/
    function rolePaginationParam(params) {
        return {
            limit: params.limit,
            page: $('#roleTable').bootstrapTable('getOptions').pageNumber,
            roleName: $('#roleName').val(),
            createrName: $('#createrName').val()
        };
    }
    /*选择角色*/
    function getSelectRole() {
        var html = '<div name="selectedDiv">';
        var selectVar = $('#roleTable').bootstrapTable('getSelections');
        var selectedIdArray = $("#roleDiv").find("input[name='roleFidList']");
        var selectedIdStr = $(selectedIdArray).map(function () {
            return $(this).val();
        }).get().join(", ");

        $(selectVar).each(function (index, obj) {
            if (!selectedIdStr || (!!selectedIdStr && selectedIdStr.indexOf(obj.fid) == -1)) {
                html += '<div class="input-group"><input type="hidden" class="form-control" name="roleFidList" value="' + obj.fid + '" />';
                html += '<input readonly="readonly" type="text" class="form-control" name="roleName" value="' + obj.roleName + '" />';
                html += '<span class="input-group-btn"><button type="button" class="btn btn-white" onclick="deleteRoleDiv(this)" ><font style="color:red">删除</font></button></span>'
                html += '</div>'
            }
        });

        html += '</div>';
        $("#roleDiv").append(html);
    }
    /*删除选择角色*/
    function deleteRoleDiv(obj) {
        //删除选中人的div
        $(obj).parent().parent().remove();
        //删除 <div name="selectedDiv" class="col-sm-2">子节点为空的div
        var divArry = $("#roleDiv").find("div[name='selectedDiv']");
        $(divArry).each(function (index, obj) {
            if ($(obj).children().length <= 0) {
                $(obj).remove();
            }
        })
    }
</script>
</body>
</html>
