<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>平台管理系统</title>
<meta name="keywords" content="平台管理系统">
<meta name="description" content="平台管理系统">

<link rel="${staticResourceUrl}/shortcut icon" href="favicon.ico">
<link href="${staticResourceUrl}/css/bootstrap.min.css${VERSION}001"
	rel="stylesheet">
<link href="${staticResourceUrl}/css/font-awesome.css${VERSION}001"
	rel="stylesheet">

<link href="${staticResourceUrl}/css/animate.css${VERSION}001"
	rel="stylesheet">
<link href="${staticResourceUrl}/css/style.css${VERSION}001"
	rel="stylesheet">
<link
	href="${staticResourceUrl}/css/plugins/treeview/bootstrap-treeview.css${VERSION}001"
	rel="stylesheet">
</head>

<body class="gray-bg">
	<div class="row wrapper wrapper-content animated fadeInRight">
		<div class="col-sm-8">
			<div class="float-e-margins">
				<div class="ibox-title">
					<h5>事件</h5>
					<div class="ibox-tools">
						<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
						</a> <a class="dropdown-toggle" data-toggle="dropdown"
							href="buttons.html#"> <i class="fa fa-wrench"></i>
						</a>
						<ul class="dropdown-menu dropdown-user">
							<li><a href="buttons.html#">选项1</a></li>
							<li><a href="buttons.html#">选项2</a></li>
						</ul>
						<a class="close-link"> <i class="fa fa-times"></i>
						</a>
					</div>
				</div>
				<div class="ibox-content">
					<div class="col-sm-6">
						<div id="treeview11" class="test"></div>
					</div>
					<div class="col-sm-6">
						<h5>事件输出：</h5>
						<hr>
						<div id="event_output"></div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="float-e-margins">
				<div class="ibox-title">
					<h5>JSON数据</h5>
					<div class="ibox-tools">
						<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
						</a> <a class="dropdown-toggle" data-toggle="dropdown"
							href="buttons.html#"> <i class="fa fa-wrench"></i>
						</a>
						<ul class="dropdown-menu dropdown-user">
							<li><a href="buttons.html#">选项1</a></li>
							<li><a href="buttons.html#">选项2</a></li>
						</ul>
						<a class="close-link"> <i class="fa fa-times"></i>
						</a>
					</div>
				</div>
				<div class="ibox-content">
					<div id="treeview12" class="test"></div>
				</div>
			</div>
		</div>
	</div>

	<!-- 全局js -->
	<script src="${staticResourceUrl}/js/jquery.min.js${VERSION}001"></script>
	<script src="${staticResourceUrl}/js/bootstrap.min.js${VERSION}001"></script>

	<!-- 自定义js -->
	<script src="${staticResourceUrl}/js/content.js${VERSION}001"></script>


	<!-- Bootstrap-Treeview plugin javascript -->
	<script
		src="${staticResourceUrl}/js/plugins/treeview/bootstrap-treeview.js${VERSION}001"></script>

	<script type="text/javascript">
	    $('#treeview11').treeview({
	        color: "#428bca",
	        data: '${treeview}',
	        showCheckbox:true,
	        uncheckedIcon:"glyphicon glyphicon-unchecked",
	        checkedIcon:"glyphicon glyphicon-check",
	        showIcon:true,
	        onNodeSelected: function (event, node) {
	            $('#event_output').prepend('<p>您单击了 ' + node.text + '</p>');
	        }
	    });
    </script>

</body>
</html>
