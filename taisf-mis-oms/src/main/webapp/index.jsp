<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE >
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<base href="${basePath }>">
<title>平台管理系统</title>
<meta name="keywords" content="平台管理系统">
<meta name="description" content="平台管理系统">
<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min.css?v=3.3.9" rel="stylesheet">
<link href="css/plugins/bootstrap-table/bootstrap-table.min.css?v=3.3.9"
	rel="stylesheet">
</head>
<html>
<body>

	<div class="ibox float-e-margins">
		<div class="ibox-title">
			<h3 align="center">开发人员</h3>
			<div class="ibox-tools">
				<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
				</a> <a class="dropdown-toggle" data-toggle="dropdown"
					href="table_basic.html#"> <i class="fa fa-wrench"></i>
				</a> <a class="close-link"> <i class="fa fa-times"></i>
				</a>
			</div>
		</div>
		${basePath }
		<div class="ibox-content">

			<table class="table table-hover table-bordered">
				<thead>
					<tr>
						<th style="text-align: center;">花名</th>
						<th style="text-align: center;">姓名</th>
						<th style="text-align: center;">邮箱</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center" colspan="3"><font style="font-size: 14px">版权所有@北京研发中心</font></td>
					</tr>
				</tbody>
			</table>

		</div>
	</div>
</body>
</html>