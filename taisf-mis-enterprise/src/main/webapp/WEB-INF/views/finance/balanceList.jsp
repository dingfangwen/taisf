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
    <style type=text/css>
        .tdfont {
            font-size: 13px
        }
    </style>

    <style type="text/css">
        .file {
            position: relative;
            display: inline-block;
            background:#1ab394;
            border: 1px solid #99D3F5;
            border-radius: 4px;
            padding: 4px 12px;
            overflow: hidden;
            color: #FFFFFF;
            text-decoration: none;
            text-indent: 0;
            height: 34px;
        }
        .file input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
        }
        .file:hover {
            background: #1ab394;
            border-color: #78C3F3;
            color: #FFFFFF;
            text-decoration: none;
        }
        .content li{ float:left; }
    </style>
</head>

<body class="gray-bg">

<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox">
        <div class="ibox-content">

            <div class="row" style="margin-top: 10px;">
                <div class="form-group">


                    <label class="col-sm-1 control-label mtop">企业编号:</label>
                    <div class="col-sm-2">
                        <input id="enterpriseCode" name="enterpriseCode" class="form-control">
                    </div>
                    <%--<label class="col-sm-1 control-label mtop">企业名称:</label>--%>
                    <%--<div class="col-sm-2">--%>
                    <%--<input id="enterpriseName" name="enterpriseName"  class="form-control">--%>
                    <%--</div>--%>


                    <div class="col-sm-1">
                        <button class="btn btn-primary"  type="button" onclick="query();">
                            <i class="fa fa-search"></i>&nbsp;搜索
                        </button>
                    </div>

                    <div class="col-xs-1 col-sm-1">
                        <button class="btn btn-primary" type="button" onclick="refund()">下载模板</button>
                    </div>
                    <div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
                    <form id="jvForm" action="finance/readExcel" class="form-horizontal" method="post"  >
                        <div class="col-xs-2 col-sm-2">
                            <a href="javascript:;" class="file">
                                <span style="position: inherit;top:3px;">批量分配</span>
                                <input  type="file" onchange="uploadFile()" id="batchExcel" name="file" multiple="multiple"/></a>
                        </div>
                        <input type="reset" style="display:none;" />
                    </form>
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
                           data-query-params="paginationAccount" data-method="post"
                           data-single-select="true"
                           data-url="finance/balanceListPage">
                        <thead>
                        <tr>

                            <th data-field="enterpriseCode" data-width="10%"
                                data-align="center"><span class="tdfont">企业编号</span></th>
                            <th data-field="enterpriseName" data-width="10%"
                                data-align="center"><span class="tdfont">企业名称</span></th>
                            <th data-field="allBalance" data-width="10%" data-formatter="formatPrice"
                                data-align="center"><span class="tdfont">账号总金额</span></th>
                            <th data-field="drawBalance" data-width="10%" data-formatter="formatPrice"
                                data-align="center"><span class="tdfont">可分配金额</span></th>

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


<!-- 弹出用户详情 -->
<div class="modal inmodal" id="detailModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">


            <div class="modal-header" style="padding: 15px 6px;">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">员工明细查询</h4>
            </div>


            <!-- 搜索框panel -->
            <div class="ibox float-e-margins">
                <div class="ibox-content">

                    <!-- 搜索框panel -->

                    <input id="id_enterpriseCode" name="enterpriseCode" type="hidden"
                           class="form-control">

                    <label class="col-sm-1 control-label mtop">电话:</label>
                    <div class="col-sm-2">
                        <input id="userPhone" name="userPhone" type="text"
                               class="form-control">
                    </div>


                    <div class="col-sm-1">
                        <button class="btn btn-primary" type="button" onclick="queryEmp();">
                            <i class="fa fa-search"></i>&nbsp;搜索
                        </button>
                    </div>

                </div>
            </div>


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
                                           data-single-select="true"
                                           data-url="finance/accountPage">
                                        <thead>
                                        <tr>
                                            <th data-field="userCode" data-width="20%">编号</th>
                                            <th data-field="userPhone" data-width="30%" data-align="center">手机号</th>
                                            <th data-field="userName" data-width="30%" data-align="center">姓名</th>
                                            <th data-field="drawBalance" data-width="30%" data-formatter="formatePrice"
                                                data-align="center">账户余额
                                            </th>
                                            <th data-field="userStatus" data-width="20%" data-formatter="formateUserStatus"
                                                data-align="center">状态</th>
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
<!-- end -->






<!-- 平均分配 -->
<div class="modal inmodal" id="avgModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">


            <div class="modal-header" style="padding: 15px 6px;">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">金额填写之后会自动平均分配</h4>
            </div>


            <!-- 搜索框panel -->
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <input id="avg_enterpriseCode" name="enterpriseCode" type="hidden"
                           class="form-control">
                    <input id="avg_empNum" name="avg_empNum" type="hidden"
                           class="form-control">
                    <input id="avg_bossNum" name="avg_bossNum" type="hidden"
                           class="form-control">
                    <div class="ibox-content">
                        <label class="col-sm-3 control-label mtop">可分配金额:( <span style="color: red" id="allMoney"></span>
                            )元</label>

                    </div>

                </div>

                <div class="ibox-content">

                    <label class="col-sm-3 control-label mtop">员工( <span style="color: red" id="spanEmp"></span>
                        )人,每人分配金额:</label>
                    <div class="col-sm-2">
                        <input id="empMoney" name="empMoney" type="text" value="0"
                               class="form-control">
                    </div>
                    <label class="col-sm-3 control-label mtop">老板(<span style="color: red" id="spanBoss"></span>
                        )人,每人分配金额:</label>
                    <div class="col-sm-2">
                        <input id="bossMoney" name="bossMoney" type="text" value="0"
                               class="form-control">
                    </div>

                </div>

            </div>


            <div class="modal-footer">
                <button class="btn btn-primary" type="button" id="confirmAvgsaveBtn" onclick="confirmAvg();">
                    <i class="fa fa-search"></i>&nbsp;确认
                </button>
                <button type="button" class="btn btn-white" data-dismiss="modal">返回</button>
            </div>
        </div>
    </div>
</div>
<!-- end -->




<!-- 个人分配 -->
<div class="modal inmodal" id="oneModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">


            <div class="modal-header" style="padding: 15px 6px;">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">金额分配给单一用户</h4>
            </div>


            <!-- 搜索框panel -->
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <input id="one_enterpriseCode" name="enterpriseCode" type="hidden"
                           class="form-control">
                    <div class="ibox-content">
                        <label class="col-sm-3 control-label mtop">可分配金额:( <span style="color: red" id="allMoneyOne"></span>
                            )元</label>

                    </div>

                </div>
                <div class="ibox-content">

                    <label class="col-sm-3 control-label mtop">手机号:</label>
                    <div class="col-sm-2">
                        <input id="one_userPhone" name="userPhone" type="text"
                               class="form-control">
                    </div>
                    <label class="col-sm-3 control-label mtop">分配金额(元):</label>
                    <div class="col-sm-2">
                        <input id="one_moneyYuan" name="moneyYuan" type="text" value="0"
                               class="form-control">
                    </div>

                </div>

            </div>


            <div class="modal-footer">
                <button class="btn btn-primary" type="button" id="saveBtn" onclick="confirmOne();">
                    <i class="fa fa-search"></i>&nbsp;确认
                </button>
                <button type="button" class="btn btn-white" data-dismiss="modal">返回</button>
            </div>
        </div>
    </div>
</div>
<!-- end -->




<script>

    function formatPrice(value, row, index) {
        if (value != null) {
            return (value/100).toFixed(2);
        } else {
            return "-";
        }
    }
    
    $(function () {
        //初始化日期
        CommonUtils.datePickerFormat('startTime');
        CommonUtils.datePickerFormat('endTime');
    });


    //确认平均分配
    function confirmOne() {

        $("#saveBtn").attr("disabled", "disabled");
        var enterpriseCode = $("#one_enterpriseCode").val();
        var userPhone = $("#one_userPhone").val();
        var moneyYuan = $("#one_moneyYuan").val();

        if (userPhone == null || userPhone == "") {
            layer.alert("手机号不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        if (moneyYuan == null || moneyYuan == "") {
            layer.alert("金额不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        $("#one_userPhone").val("");
        $("#one_moneyYuan").val("");
        debugger
        $.ajax({
            data: {

                'enterpriseCode': enterpriseCode,
                'userPhone': userPhone,
                'moneyYuan': moneyYuan
            },
            type: "post",
            dataType: "json",
            url: 'finance/balanceMoneyOne',
            success: function (result) {
                if (result.code === 0) {
                    layer.alert("充值成功", {icon: 6, time: 2000, title: '提示'});
                    $("#saveBtn").removeAttr("disabled");
                    $('#oneModal').modal('hide');
                    query();
                } else {
                    $("#saveBtn").removeAttr("disabled");
                    layer.alert(result.msg, {icon: 5, time: 2000, title: '提示'});
                }
            },
            error: function (result) {
                $("#saveBtn").removeAttr("disabled");
                layer.alert("未知错误", {icon: 5, time: 2000, title: '提示'});
            }
        });

    }



    //确认平均分配
    function confirmAvg() {
        $("#confirmAvgsaveBtn").attr("disabled", "disabled");

        var empMoney = $("#empMoney").val();
        var bossMoney = $("#bossMoney").val();
        if (empMoney == null || empMoney == "") {
            layer.alert("员工没人分配金额不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#confirmAvgsaveBtn").removeAttr("disabled");
            return false;
        }
        if (bossMoney == null || bossMoney == "") {
            layer.alert("老板每人分配金额不能为空", {icon: 5, time: 2000, title: '提示'});
            $("#confirmAvgsaveBtn").removeAttr("disabled");
            return false;
        }
        var enterpriseCode = $("#avg_enterpriseCode").val();
        var bossNum = $("#avg_bossNum").val();
        var empNum = $("#avg_empNum").val();
        $("#empMoney").val("");
        $("#bossMoney").val("");
        debugger
        $.ajax({
            data: {
                'bossMoneyYuan': bossMoney,
                'empMoneyYuan': empMoney,
                'enterpriseCode': enterpriseCode,
                'bossNum': bossNum,
                'empNum': empNum
            },
            type: "post",
            dataType: "json",
            url: 'finance/balanceMoneyAvg',
            success: function (result) {
                $("#confirmAvgsaveBtn").removeAttr("disabled");
                if (result.code === 0) {
                    layer.alert("充值成功", {icon: 6, time: 2000, title: '提示'});
                    $('#avgModal').modal('hide');
                    query();
                } else {
                    layer.alert(result.msg, {icon: 5, time: 2000, title: '提示'});
                }
            },
            error: function (result) {
                $("#confirmAvgsaveBtn").removeAttr("disabled");
                layer.alert("未知错误", {icon: 5, time: 2000, title: '提示'});
            }
        });

    }

    function formatePrice(value, row, index) {
        return (value/100).toFixed(2);
    }


    function paginationAccount(params) {
        return {
            enterpriseCode: $("#enterpriseCode").val(),
            /*userPhone: $("#userPhone").val(),*/
            limit: params.limit,
            page: $("#listTable").bootstrapTable("getOptions").pageNumber
        };

    }

    function paginationAccountS(params) {
        return {
            limit: params.limit,
            page: $("#listTableEmp").bootstrapTable("getOptions").pageNumber,
            enterpriseCode: $("#id_enterpriseCode").val(),
            userPhone: $("#userPhone").val(),
        };

    }

    function paginationParam(params) {
        return {
            enterpriseCode: $("#enterpriseCode").val(),
            limit: params.limit,
            page: $("#listTable").bootstrapTable("getOptions").pageNumber
        };

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
    
    function formateUserStatus(value, row, index) {
        if (value == 1) {
            return "未激活";
        } else if (value == 2) {
            return "可用";
        }else if (value == 3) {
            return "注销";
        }else if (value == 4) {
            return "冻结";
        } else {
            return value;
        }
    }

    function formatRechargeType(value, row, index) {
        if (value == 1) {
            return "企业充值";
        } else if (value == 2) {
            return "个人充值";
        } else {
            return "其他";
        }
    }
    function formatRechargeStatus(value, row, index) {
        if (value == 1) {
            return "成功";
        } else if (value == 2) {
            return "失败";
        } else {
            return value;
        }
    }

    function query() {
        $("#listTable").bootstrapTable("selectPage", 1);
    }

    function queryEmp() {
        $("#listTableEmp").bootstrapTable("selectPage", 1);
    }

    function avgMoney(enterpriseCode) {

        $("#avg_enterpriseCode").val(enterpriseCode)
        $.ajax({
            data: {},
            type: "post",
            dataType: "json",
            url: 'finance/enterpriseStats?enterpriseCode='+enterpriseCode,
            success: function (result) {
                console.log(result);
                if (result.code === 0) {
                    $("#allMoney").html(result.data.drawBalance/100);
                    $("#spanBoss").html(result.data.bossNum);
                    $("#spanEmp").html(result.data.empNum);
                    $("#avg_bossNum").val(result.data.bossNum);
                    $("#avg_empNum").val(result.data.empNum);
                    $('#avgModal').modal('show');

                } else {
                    layer.alert(result.msg, {icon: 5, time: 2000, title: '提示'});
                }
            },
            error: function (result) {
                layer.alert("未知错误", {icon: 5, time: 2000, title: '提示'});
            }
        });
    }

    function detail(enterpriseCode) {
        $("#id_enterpriseCode").val(enterpriseCode)
        $('#detailModal').modal('show');
        queryEmp();
    }


    function oneMoney(enterpriseCode) {
        $("#one_enterpriseCode").val(enterpriseCode)
        $("#one_userPhone").val("")
        $("#one_moneyYuan").val("")


        $.ajax({
            data: {},
            type: "post",
            dataType: "json",
            url: 'finance/enterpriseStats?enterpriseCode='+enterpriseCode,
            success: function (result) {
                if (result.code === 0) {
                    $("#allMoneyOne").html(result.data.drawBalance/100);
                    $('#oneModal').modal('show');

                } else {
                    layer.alert(result.msg, {icon: 5, time: 2000, title: '提示'});
                }
            },
            error: function (result) {
                layer.alert("未知错误", {icon: 5, time: 2000, title: '提示'});
            }
        });


    }


    // 操作列
    function formatOperate(value, row, index) {
        var result = "";
        result = result + "<a title='查看' onclick='detail(\"" + row.enterpriseCode + "\")' >查看</a>&nbsp;&nbsp;&nbsp;&nbsp;";
        result = result + "<a title='平均分配' onclick='avgMoney(\"" + row.enterpriseCode + "\")'  >平均分配</a>&nbsp;&nbsp;&nbsp;&nbsp;";
        result = result + "<a title='个人分配' onclick='oneMoney(\"" + row.enterpriseCode + "\")'  >个人分配</a>&nbsp;&nbsp;&nbsp;&nbsp;";
        result = result + "<a title='批量分配' onclick='batchMoney(\"" + row.enterpriseCode + "\")'  >批量分配</a>";
        return result;
    }
    var batchMoney_enterpriseCode = "";
    function batchMoney(enterpriseCode){
        batchMoney_enterpriseCode = enterpriseCode
      $("#batchExcel").click()
    }

    function refund(){
        window.location.href="${staticResourceUrl}/finance/downloadTemplate";
    }

    //上传Excel
    function uploadFile(){
        var filename = document.getElementById('batchExcel').value;
        var modelCode = $("#modelCode").val();
        var flag = false; //状态
        var arr = ["xls"];
        //取出上传文件的扩展名
        var index = filename.lastIndexOf(".");
        var ext = filename.substr(index+1);
        for(var i=0;i<arr.length;i++)
        {
            if(ext == arr[i])
            {
                flag = true; //一旦找到合适的，立即退出循环
                break;
            }
        }
        //循环比较
        //条件判断
        if(flag)
        {
            //文件合法
        }else
        {
            layer.alert("请上传正确的文件", {icon: 5,time: 3000, title:'提示'});
            return false;
        }
        var options = {
            url : "finance/readExcel?enterpriseCode="+batchMoney_enterpriseCode,
            type : "post",
            dataType : "json",

            success : function(result){
                if(result.code === 0){
                    layer.alert("批量导入成功", {icon: 6,time: 2000, title:'提示'});
                    $("input[type=reset]").trigger("click");
                    setTimeout(function(){ $('#listTable').bootstrapTable('refresh'); }, 2000);
                }else{
                    layer.alert(result.msg, {icon: 5,time: 3000, title:'提示'});
                    //setTimeout(function(){ document.location.reload(); }, 2000);
                    $("input[type=reset]").trigger("click");

                }
            }
        }
        $("#jvForm").ajaxSubmit(options);
    }

</script>

</body>
</html>
