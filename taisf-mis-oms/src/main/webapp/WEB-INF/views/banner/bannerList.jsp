<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="customtag" uri="http://minsu.ziroom.com" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<base href="${basePath}">
	<title>互联网医院运营管理后台</title>
	<meta name="keywords" content="互联网医院运营管理后台">
	<meta name="description" content="互联网医院运营管理后台">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="renderer" content="webkit|ie-comp|ie-stand" />
	<link href="${staticResourceUrl}/css/bootstrap.min.css${VERSION}" rel="stylesheet">
	<link href="${staticResourceUrl}/css/font-awesome.css${VERSION}" rel="stylesheet">
	<link href="${staticResourceUrl}/css/plugins/bootstrap-table/bootstrap-table.min.css${VERSION}" rel="stylesheet">
	<link href="${staticResourceUrl}/css/animate.css${VERSION}" rel="stylesheet">
	<link href="${staticResourceUrl}/css/style.css${VERSION}" rel="stylesheet">
	<link href="${staticResourceUrl}/css/custom-z.css${VERSION}" rel="stylesheet">
	<style type=text/css>
		.tdfont{font-size:13px;
		}
	</style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="ibox">
		<div class="ibox-content">
<!-- 			<input id="appCode" type="hidden" value="jkHospital" class="form-control"> -->
			<div class="row">
				<div class="form-group">
					<label class="col-xs-1 col-sm-1 control-label mtop">标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题:</label>
					<div class="col-xs-2 col-sm-2">
						<input id="title" type="text" value=""
							   class="form-control">
					</div>
					<label class="col-xs-1 col-sm-1 control-label mtop">应&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用:</label>
								<div class="col-xs-2 col-sm-2">
									<select id="appCode" name="appCode" class="form-control" onchange="getAppPositionByCode()">
										<option value="">--请选择--</option>
										<c:forEach var="app" items="${bannerAppList}">
											<option value="${app.appCode}">${app.text}</option>
										</c:forEach>
									</select>
								</div>

					<label class="col-xs-1 col-sm-1 control-label mtop">位&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;置:</label>
					<div class="col-xs-2 col-sm-2">
						<select id="appPosition" name="appPosition" class="form-control">
							<option value="">--请选择--</option>
						</select>
					</div>
					<%--<label class="col-sm-1 control-label mtop">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:</label>--%>
					<%--<div class="col-sm-2">--%>
						<%--<select id="state" name="state"--%>
								<%--class="form-control">--%>
							<%--<option value="">--请选择--</option>--%>
							<%--<option value="1">正常</option>--%>
							<%--<option value="2">下架</option>--%>
						<%--</select>--%>
					<%--</div>--%>
					<%--<div class="col-sm-1">--%>
						<%--<button class="btn btn-primary" type="button"  style="margin-top: 10px"--%>
								<%--onclick="query();">--%>
							<%--<i class="fa fa-search"></i>&nbsp;搜索--%>
						<%--</button>--%>
					<%--</div>--%>
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<label class="col-sm-1 control-label mtop">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态:</label>
					<div class="col-sm-2">
						<select id="state" name="state"
								class="form-control">
							<option value="">--请选择--</option>
							<option value="1">正常</option>
							<option value="2">下架</option>
						</select>
					</div>

					<div class="col-xs-1 col-sm-1">
						<button class="btn btn-primary" type="button"
								onclick="query();">
							<i class="fa fa-search"></i>&nbsp;搜索
						</button>
					</div>
					<div class="col-xs-1 col-sm-1">
						<button class="btn btn-primary" type="button"
								onclick="addBanner();">
							<%--<a href="stats/refund/testPoi"> </a>--%>
							新增
						</button>
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- Panel Other -->
	<div class="ibox-content">
		<div class="row row-lg">
			<div class="col-sm-12">
				<%--<button class="btn btn-primary" type="button" style="margin-bottom: 10px"--%>
						<%--onclick="addBanner();">--%>
					<%--&lt;%&ndash;<a href="stats/refund/testPoi"> </a>&ndash;%&gt;--%>
					<%--新增--%>
				<%--</button>--%>
				<div class="example-wrap">
					<div class="example">
						<table id="listTable"
							   class="table table-bordered"
							   style="table-layout:fixed;"
							   data-click-to-select="true"
							   data-toggle="table" data-side-pagination="server"
							   data-pagination="true" data-page-list="[5,10,20,50]"
							   data-pagination="true" data-page-size="10"
							   data-pagination-first-text="首页" data-pagination-pre-text="上一页"
							   data-pagination-next-text="下一页" data-pagination-last-text="末页"
							   data-content-type="application/x-www-form-urlencoded"
							   data-query-params="paginationParam" data-method="post"
							   data-single-select="true"
							   data-url="banner/showBannerList"
							   data->
							<thead>
							<tr>
								<th data-field="id" data-width="5%"
									data-align="center" ><span class="tdfont">ID</span></th>
								<%--<th data-field="position" data-width="10%"--%>
								<%--data-align="center" data-formatter="formatePosition"><span class="tdfont">位置</span></th>--%>
								<th data-field="title" data-width="10%"  data-formatter="formateTitle"
									data-align="center" ><span class="tdfont">标题</span></th>
								<th data-field="appName" data-width="10%"
									data-align="center"><span class="tdfont">应用</span></th>
								<th data-field="positionName" data-width="10%"
									data-align="center"><span class="tdfont">位置</span></th>
								<th data-field="url" data-visible="false"></th>
								<th data-field="imageUrl" data-width="10%"
									data-align="center" data-formatter="formateContent"><span class="tdfont">内容</span></th>
								<th data-field="platform" data-width="10%"
									data-align="center" data-formatter="formatePlatform"><span class="tdfont">平台</span></th>
								<th data-field="createTime" data-width="15%"
									data-align="center"  data-formatter="formateDate"><span class="tdfont">添加时间</span></th>
								<th data-field="state" data-width="10%"
									data-align="center" data-formatter="formateState"><span class="tdfont">状态</span></th>
								<th data-field="" data-width="20%"
									data-align="center" data-formatter="formateOperate"><span class="tdfont">操作</span></th>
							</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 全局js -->
<script src="${staticResourceUrl}/js/jquery.min.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/bootstrap.min.js${VERSION}"></script>
<!-- Bootstrap table -->
<script src="${staticResourceUrl}/js/plugins/bootstrap-table/bootstrap-table.min.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/plugins/layer/layer.min.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/plugins/layer/laydate/laydate.js${VERSION}001"></script>
<script src="${staticResourceUrl}/js/common/custom.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/common/refresh.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/common/date.proto.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/common/commonUtils.js${VERSION}001" type="text/javascript"></script>
<!-- Page-Level Scripts -->
<script>
    function paginationParam(params) {
        return {
            limit : params.limit,
            page : $('#listTable').bootstrapTable('getOptions').pageNumber,
            state : $('#state').val(),
            title : $.trim($('#title').val()),
            appCode :$.trim($("#appCode option:selected").val()),
            positionCode:$("#appPosition").val()
        };
    }
    function query() {
        $('#listTable').bootstrapTable('selectPage', 1);
    }
    // 格式化时间
    function formateContent(value, row, index) {
        var result = "";
        result += "</nobr><a target=\"_blank\"  href='"+row.imageUrl+"'>";
        result += "<img style='width:100px;height:100px;' src=\""+ value + "\" /></br>";
        result += "</a><nobr>";
        return result;
    }
    function formateTitle(value, row, index) {
        var result = "";
        result += "</nobr>";
        result += "<p>"+value+"</p>"
        result += "<nobr>";
        return result;
    }
    // 格式化时间
    function formateDate(value, row, index) {
        if (value != null) {
            var vDate = new Date(value);
            return vDate.format("yyyy-MM-dd HH:mm");
        } else {
            return "";
        }
    }
    // 格式化状态
    function formateState(value, row, index) {
        if (value == 1) {
            return "<span><font >正常</font></span>";
        } else if(value==2){
            return "<span><font >下架</font></span>";
        }
    }
    // 应用化状态
    function formateAppCode(value, row, index) {
        if (value === 'jkCloudClinic') {
            return "<span><font >健客诊所</font></span>";
        } else if(value==='jkHospital'){
            return "<span><font >健客医院</font></span>";
        }
    }
    // 格式化状态
    function formatePlatform(value, row, index) {
        if (value == 1) {
            return "<span><font >iOS</font></span>";
        } else if(value==2){
            return "<span><font >Android</font></span>";
        } else if(value==3){
            return "<span><font >m站</font></span>";
        }else if(value==4){
            return "<span><font >微信</font></span>";
        }else if(value==5){
            return "<span><font >微信小程序</font></span>";
        }

    }
    // 操作列
    function formateOperate(value, row, index) {
        var upOrDown = "";
        var edit = "";
        if(row.state == 1){
            upOrDown = "<button type='button'  class='btn btn-primary btn-sm' onclick='changeStatus(\""+ row.id + "\",\""+ row.state + "\",\""+ 2 + "\");'>下架</button>";
            edit = "<button type='button'  class='btn btn-primary btn-sm' onclick='editBanner(\""+ row.id + "\");'>编辑</button>";
        }else if(row.state == 2){
            upOrDown = "<button type='button'  class='btn btn-primary btn-sm' onclick='changeStatus(\""+ row.id + "\",\""+ row.state + "\",\""+ 1 + "\");'>上架</button>";
            edit = "<button type='button'  class='btn btn-primary btn-sm' onclick='editBanner(\""+ row.id + "\");'>编辑</button>";
        }
        return upOrDown + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + edit;
    }

    //新增banner
    function addBanner(id,state) {
        $.openNewTab(new Date().getTime(),"banner/addBanner", "新增banner");
    }

    //編輯banner
    function editBanner(id) {
        $.openNewTab(new Date().getTime(),"banner/editBanner?id=" + id, "编辑banner");
    }
	/* 上架下架  */
    function changeStatus(id, oldStatus, newStatus){
        var mes = '';
        mes = oldStatus == 1 ? '确认下架吗？' : '确认上架吗？';// 提示信息
        var iconNum = 6; //显示icon层设置 6：笑脸  5：沮丧
        //确认是否改变
        layer.confirm(mes, {icon: iconNum, title:'提示'},function(index){
            $.ajax({
                type: "POST",
                url: 'banner/changeStatus',
                dataType:"json",
                traditional: true,
                data: {'id':id,'oldStatus':oldStatus,'newStatus':newStatus},
                success: function (result) {
                    layer.alert("成功", {icon: 6, time: 2000, title: '提示'});
                    //刷新右侧列表
                    $('#listTable').bootstrapTable('refresh');
                },
                error: function(result) {
                    layer.alert("未知错误", {icon: 5, time: 2000, title: '提示'});
                }
            });
            layer.close(index);
        });
    }

    function getAppPositionByCode() {

        $.ajax({
            type:"POST",
            url:"banner/getBannerPositionEntityList",
            data:{'appCode':$("#appCode option:selected").val()},
            success:function (result) {

                $("#appPosition").html("<option value=''>--请选择--</option>")

                var positionList=result.data;

                $(positionList).each(function(i,e){

                    $("#appPosition").append("<option value='"+e.positionCode+"'>"+e.postionName+"</option>")

                })

            }

        })


    }
</script>

</body>
</html>