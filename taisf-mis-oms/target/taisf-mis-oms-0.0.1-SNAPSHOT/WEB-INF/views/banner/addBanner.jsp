<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<base href="${basePath}">
	<title>互联网医院运营管理后台</title>
	<meta name="keywords" content="互联网医院运营管理后台">
	<meta name="description" content="互联网医院运营管理后台">
	<link rel="${staticIconUrl}/shortcut icon" href="${staticIconUrl}/favicon.ico">
	<link href="${staticResourceUrl}/css/bootstrap.min.css${VERSION}001" rel="stylesheet">
	<link href="${staticResourceUrl}/css/font-awesome.css${VERSION}001" rel="stylesheet">
	<link href="${staticResourceUrl}/css/plugins/bootstrap-table/bootstrap-table.min.css${VERSION}001" rel="stylesheet">
	<link href="${staticResourceUrl}/css/animate.css${VERSION}001" rel="stylesheet">
	<link href="${staticResourceUrl}/css/style.css${VERSION}001" rel="stylesheet">
	<link href="${staticResourceUrl}/css/custom-z.css${VERSION}" rel="stylesheet">
	<link href="${staticResourceUrl}/css/plugins/blueimp/css/blueimp-gallery.min.css" rel="stylesheet">
	<link href="${staticResourceUrl}/css/font-awesome.min.css${VERSION}" rel="stylesheet">
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
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>新增/编辑banner</h5>
				</div>
				<div class="ibox-content">
<!-- 					<input id="appCode" type="hidden" value="jkHospital" class="form-control"> -->
					<form id="form" class="form-horizontal m-t">
						<div class="form-group">
							<label class="col-sm-3 control-label"><font>*&nbsp;</font>标题:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control disabled" id="title" name="title">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label"><font>*&nbsp;</font>应用:</label>
							<div class="col-sm-8">
									<select id="appCode" name="appCode" class="form-control" onchange="getAppPositionByCode()">
										<option value="">--请选择--</option>
										<c:forEach var="app" items="${bannerAppList}">
										<option value="${app.appCode}">${app.text}</option>
										</c:forEach>
									</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label"><font>*&nbsp;</font>位置:</label>
							<div class="col-sm-8">
								<select id="appPosition" name="appPosition" class="form-control">
									<option value="">--请选择--</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label"><font>*&nbsp;</font>平台:</label>
							<div class="col-sm-8">
								<div class="radio i-checks">
									<label>
										<input type="radio" value="1" name="platform"> <i></i>iOS</label>
									<label>
										<input checked type="radio" value="2" name="platform"> <i></i>Android</label>
									<label>
										<input type="radio" value="3" name="platform"> <i></i>m站</label>
									<label>
										<input type="radio" value="4" name="platform"> <i></i>微信</label>
									<label>
										<input type="radio" value="5" name="platform" checked="checked"> <i></i>微信小程序</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label"><font>*&nbsp;</font>排序:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control disabled" id="rank" name="rank">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label"><font>*&nbsp;</font>跳转类型:</label>
							<div class="col-sm-8">
								<div class="radio i-checks">
									<label>
										<input type="radio" value="1" name="jumpType"> <i></i>原生</label>
									<label>
										<input checked type="radio" value="2" name="jumpType"> <i></i>h5</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label"><font>*&nbsp;</font>开始时间:</label>
							<div class="col-xs-2 col-sm-2">
								<input id="startTime" name="startTime" value="" class="laydate-icon form-control layer-date">
							</div>
							<label class="col-sm-2 control-label"><font>*&nbsp;</font>结束时间:</label>
							<div class="col-xs-2 col-sm-2">
								<input id="endTime" name="endTime" value="" class="laydate-icon form-control layer-date">
							</div>
						</div>
						<%--<div class="form-group">--%>
							<%--<label class="col-sm-3 control-label">结束时间:</label>--%>
							<%--<div class="col-xs-2 col-sm-2">--%>
								<%--<input id="endTime" name="endTime" value="" class="laydate-icon form-control layer-date">--%>
							<%--</div>--%>
						<%--</div>--%>
						<div class="form-group">
							<label class="col-sm-3 control-label">URL:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control disabled" id="url" name="url">
							</div>
						</div>

						<div class="form-group">
						<label class="col-sm-3 control-label"><font>*&nbsp;</font>图片:</label>
						<div class="col-sm-8">
							<a id="image11-1" href="${imageUrl }" title="图片" data-gallery="">
								<img id='imgSizeImgSrc1' style="margin-left:50px" width="400" height="400"
									 src="${imageUrl }"/></a>
							<input id='imgUrl1' type="hidden" name="imageUrl" value="${imageUrl}"/>
							<a href="javascript:;" style="top:70" class="file">
								<input type="file" onchange="uploadPic(this)" name="pics"
									   multiple="multiple"/>
								上传图片
							</a>
						</div>
					</div>
					</form>
					<div class="row">
						<div class="col-sm-5"></div>
						<div class="col-sm-2">
							<button class="btn btn-primary" id="saveBtn" type="button" onclick="saveBanner();">保存
							</button>
						</div>
						<div class="col-sm-1">
							<button class="btn btn-primary" id="saveBannerAndUpBtn" type="button"
									onclick="saveBannerAndUp();">保存并上架
							</button>
						</div>
					</div>
				</div>
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
<script src="${staticResourceUrl}/js/plugins/iCheck/icheck.min.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/plugins/bootstrap-table/bootstrap-table.min.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/common/geography.cascade.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/common/refresh.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/common/custom.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/jquery.form.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/plugins/layer/laydate/laydate.js${VERSION}001"></script>
<script src="${staticResourceUrl}/js/plugins/layer/layer.min.js${VERSION}"></script>
<script src="${staticResourceUrl}/js/common/commonUtils.js${VERSION}001" type="text/javascript"></script>
<script src="${staticResourceUrl}/js/plugins/blueimp/jquery.blueimp-gallery.min.js"></script>
<script src="${staticResourceUrl}/js/plugins/layer/laydate/laydate.js${VERSION}001"></script>
<script src="${staticResourceUrl}/js/common/UploadPicTool.js${VERSION}" type="text/javascript"></script>
<script>
    $(function (){
        //初始化日期
        CommonUtils.datePickerFormat('startTime');
        CommonUtils.datePickerFormat('endTime');
        $("#rank").on("input", function () {
            var rank = $(this).val();
            var patrn = /^([1-9]\d*|0)(\.\d*[1-9])?$/;
            if (!patrn.exec(rank)) {
                layer.alert("请输入正确排序", {icon: 5, time: 2000, title: '提示'});
            }

        })
    });
</script>
<script type="text/javascript">
    function query() {
        $('#listTable').bootstrapTable('selectPage', 1);
    }
    //上传图片
    function uploadPic(obj) {
        var fileData = obj.files[0];

        UploadPicTool.uploadPics(fileData, 5, null, function () {
            var options = {
                url: "common/picture/uploadPics",
                type: "post",
                data: {
                    "path": "banner.path"
                },
                dataType: "json",
                success: function (data) {
                    $("#imgSizeImgSrc1").attr("src", data[0]);
                    $("#imgUrl1").val(data[0]);
                    $('#image11-1').attr('href', data[0]);
                }
            }
            var str = "form";
            $("#" + str).ajaxSubmit(options);
        })
    }
    //保存banner
    function saveBanner() {
        $("#saveBtn").attr("disabled", "disabled");
        if ($("#title").val() == "" || $("#title").val() == null) {
            layer.alert("请填写标题", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        if ($("#appCode").val() == "" || $("#appCode").val() == null) {
            layer.alert("请填写应用", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        if ($("#appPosition").val() == "" || $("#appPosition").val() == null) {
            layer.alert("请填写位置", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }

        if ($('input[name="platform"]:checked').val() == "" || $('input[name="platform"]:checked').val() == null) {
            layer.alert("请选择平台", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        if ($('input[name="jumpType"]:checked').val() == "" || $('input[name="jumpType"]:checked').val() == null) {
            layer.alert("请选择跳转类型", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        if ($("#rank").val() == "" || $("#rank").val() == null) {
            layer.alert("请选择排序", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }


        var patrn = /^([1-9]\d*|0)(\.\d*[1-9])?$/;
        if (!patrn.exec($("#rank").val())) {
            layer.alert("请输入正确排序", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }

        var startTime=$("#startTime").val();

        if(startTime == null ||startTime == "" ){

            layer.alert("请选择开始时间", {icon: 5, time: 2000, title: '提示'});

            $("#saveBtn").removeAttr("disabled");

            return false;

        }

        var endTime=$("#endTime").val();

        if(endTime == null ||endTime == "" ){

            layer.alert("请选择结束时间", {icon: 5, time: 2000, title: '提示'});

            $("#saveBtn").removeAttr("disabled");

            return false;

        }

        var imag11 = $("#imgUrl1").val();
        if (imag11 == null || imag11 == "") {
            layer.alert("请上传封面图", {icon: 5, time: 2000, title: '提示'});
            $("#saveBtn").removeAttr("disabled");
            return false;
        }
        var temp = document.getElementsByName("platform");
        var platform = "${platform}";
        for (var i = 0; i < temp.length; i++) {
            if (temp[i].checked)
                platform = temp[i].value;
        }
        var temp1 = document.getElementsByName("jumpType");
        var jumpType = "${jumpType}";
        for (var i = 0; i < temp1.length; i++) {
            if (temp1[i].checked)
                jumpType = temp1[i].value;
        }

        CommonUtils.ajaxPostSubmit("banner/saveBanner", {
                "appCode":$("#appCode").val(),
                "title": $("#title").val(),
                "position": $("#appPosition").val(),
                "platform": platform,
                "jumpType": jumpType,
                "rank": $("#rank").val(),
                "url": $("#url").val(),
                "state": 2, //点保存默认下架
                "imageUrl": imag11,
				"startTime":new Date(startTime),
				"endTime":new Date(endTime)
            }, function (data) {
                if (data) {
                    if (data.code == 0) {
                        layer.alert("保存成功", {icon: 6, time: 3000, title: '提示'});
                        setTimeout(function () {
                            $.callBackParent("banner/bannerList", true, callBack);
                        }, 3000);
                    } else {
                        layer.alert(data.msg, {icon: 5, time: 2000, title: '提示'});
                        $("#saveBtn").removeAttr("disabled");
                    }
                }
            }
        );
    }
    //保存banner
    function saveBannerAndUp() {
        $("#saveBannerAndUpBtn").attr("disabled", "disabled");
        if ($("#title").val() == "" || $("#title").val() == null) {
            layer.alert("请填写标题", {icon: 5, time: 2000, title: '提示'});
            $("#saveBannerAndUpBtn").removeAttr("disabled");
            return false;
        }
        if ($("#appCode").val() == "" || $("#appCode").val() == null) {
            layer.alert("请填写应用", {icon: 5, time: 2000, title: '提示'});
            $("#saveBannerAndUpBtn").removeAttr("disabled");
            return false;
        }
        if ($("#appPosition").val() == "" || $("#appPosition").val() == null) {
            layer.alert("请填写位置", {icon: 5, time: 2000, title: '提示'});
            $("#saveBannerAndUpBtn").removeAttr("disabled");
            return false;
        }

        if ($('input[name="platform"]:checked').val() == "" || $('input[name="platform"]:checked').val() == null) {
            layer.alert("请选择平台", {icon: 5, time: 2000, title: '提示'});
            $("#saveBannerAndUpBtn").removeAttr("disabled");
            return false;
        }
        if ($('input[name="jumpType"]:checked').val() == "" || $('input[name="jumpType"]:checked').val() == null) {
            layer.alert("请选择跳转类型", {icon: 5, time: 2000, title: '提示'});
            $("#saveBannerAndUpBtn").removeAttr("disabled");
            return false;
        }
        if ($("#rank").val() == "" || $("#rank").val() == null) {
            layer.alert("请选择排序", {icon: 5, time: 2000, title: '提示'});
            $("#saveBannerAndUpBtn").removeAttr("disabled");
            return false;
        }

        var patrn = /^([1-9]\d*|0)(\.\d*[1-9])?$/;
        if (!patrn.exec($("#rank").val())) {
            layer.alert("请输入正确排序", {icon: 5, time: 2000, title: '提示'});
            $("#saveBannerAndUpBtn").removeAttr("disabled");
            return false;
        }

        var startTime=$("#startTime").val();

        if(startTime == null ||startTime == "" ){

            layer.alert("请选择开始时间", {icon: 5, time: 2000, title: '提示'});

            $("#saveBannerAndUpBtn").removeAttr("disabled");

            return false;

        }

        var endTime=$("#endTime").val();

        if(endTime == null ||endTime == "" ){

            layer.alert("请选择结束时间", {icon: 5, time: 2000, title: '提示'});

            $("#saveBannerAndUpBtn").removeAttr("disabled");

            return false;

        }


        var imag11 = $("#imgUrl1").val();
        if (imag11 == null || imag11 == "") {
            layer.alert("请上传封面图", {icon: 5, time: 2000, title: '提示'});
            $("#saveBannerAndUpBtn").removeAttr("disabled");
            return false;
        }
        var temp = document.getElementsByName("platform");
        var platform = "${platform}";
        for (var i = 0; i < temp.length; i++) {
            if (temp[i].checked)
                platform = temp[i].value;
        }
        var temp1 = document.getElementsByName("jumpType");
        var jumpType = "${jumpType}";
        for (var i = 0; i < temp1.length; i++) {
            if (temp1[i].checked)
                jumpType = temp1[i].value;
        }

        CommonUtils.ajaxPostSubmit("banner/saveBanner", {
                "appCode":$("#appCode").val(),
                "title": $("#title").val(),
                "position": $("#appPosition").val(),
                "platform": platform,
                "jumpType": jumpType,
                "rank": $("#rank").val(),
                "url": $("#url").val(),
                "state": 1, //点保存并上架
                "imageUrl": imag11,
		"startTime":new Date(startTime),
		"endTime":new Date(endTime)
            }, function (data) {
                if (data) {
                    if (data.code == 0) {
                        layer.alert("保存成功", {icon: 6, time: 3000, title: '提示'});
                        setTimeout(function () {
                            $.callBackParent("banner/bannerList", true, callBack);
                        }, 3000);
                    } else {
                        layer.alert(data.msg, {icon: 5, time: 2000, title: '提示'});
                        $("#saveBannerAndUpBtn").removeAttr("disabled");
                    }
                }
            }
        );
    }
    function callBack(parent) {
        parent.refreshData("listTable");
    }
    
	function getAppPositionByCode() {

        var appCode= $("#appCode option:selected").val();

      console.log("appcode>>>>>"+appCode);

        $.ajax({
			type:"POST",
			url:"banner/getBannerPositionEntityList",
			data:{'appCode':appCode},
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