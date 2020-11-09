<%@page import="com.taisf.services.common.valenum.FinanceCheckTypeEnum"%>
<%@page import="com.taisf.services.common.valenum.EnterpriseTypeEnum"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="customtag" uri="http://minsu.ziroom.com" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<base href="${basePath}">
	<title>企业操作页</title>
	<meta name="keywords" content="新增企业  编辑企业">
	<meta name="description" content="新增企业  编辑企业">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="renderer" content="webkit|ie-comp|ie-stand" />
	<link rel="${staticIconUrl}/shortcut icon" href="${staticIconUrl}/favicon.ico">
	<link href="${staticResourceUrl}/css/bootstrap.min.css${VERSION}001" rel="stylesheet">
	<link href="${staticResourceUrl}/css/font-awesome.css${VERSION}001" rel="stylesheet">
	<link href="${staticResourceUrl}/css/plugins/bootstrap-table/bootstrap-table.min.css${VERSION}001" rel="stylesheet">
	<link href="${staticResourceUrl}/css/bootstrap.min.css${VERSION}001" rel="stylesheet">
	<link href="${staticResourceUrl}/css/plugins/iCheck/custom.css${VERSION}001" rel="stylesheet">
	<link href="${staticResourceUrl}/css/animate.css${VERSION}001" rel="stylesheet">
	<link href="${staticResourceUrl}/css/style.css${VERSION}001" rel="stylesheet">
	<link href="${staticResourceUrl}/css/custom-z.css${VERSION}" rel="stylesheet">
	<style type=text/css>
		.td-title{font-weight:bold;font-size:13px}
		.inner-title{float:left; width:60px; font-size:13px;}
		.inner-input{float:left; width:80px; margin:0px 10px}
		.table>tbody>tr>td {
			border: 0px solid white
		}
		.hr-line-dashed {
			margin: 0 0;
		}
	</style>
	<!-- 全局js -->
	<script src="${staticResourceUrl}/js/jquery.min.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/bootstrap.min.js${VERSION}"></script>
	<!-- 自定义js -->
	<script src="${staticResourceUrl}/js/content.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/common/custom.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/common/refresh.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/common/date.proto.js${VERSION}"></script>
	<!-- iCheck -->
	<script src="${staticResourceUrl}/js/plugins/iCheck/icheck.min.js${VERSION}"></script>
	<!-- Bootstrap table -->
	<script src="${staticResourceUrl}/js/plugins/bootstrap-table/bootstrap-table.min.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/common/geography.cascade.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/jquery.form.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/plugins/layer/layer.min.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/common/commonUtils.js${VERSION}001" type="text/javascript"></script>
	<script src="${staticResourceUrl}/js/plugins/validate/jquery.validate.min.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/plugins/validate/messages_zh.min.js${VERSION}"></script>
	<script src="${staticResourceUrl}/js/plugins/layer/laydate/laydate.js${VERSION}001"></script>
</head>

<body class="gray-bg">

	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="float-e-margins">
			<div class="ibox-title">
				<c:if test="${operate==3}">
					<h5>添加企业</h5>
				</c:if>
				<c:if test="${operate==2}">
					<h5>编辑企业</h5>
				</c:if>
				<c:if test="${operate==1}">
					<h5>查看企业</h5>
				</c:if>
			</div>
			<div class="ibox-content">
				<div class="row row-lg">
					<div class="col-sm-12">
						<table class="table">
							<tr><td colspan="6" style="font-size:13px;color:#1bb394;font-weight:bold;">基本信息
								<input type="hidden" id="id" name="id" value="${model.enterpriseEntity.id}"></td></tr>
							
							<tr>
								<td align="right" class="td-title"><font color="red">*&nbsp;</font>供餐单位:</td>
								<td><select class="form-control" id="supplierCode" name="supplierCode"  readonly="true">
				                        <option value="">-请选择-</option>  
				                    	<c:if test="${ not empty suppliers}" > 
				                        <c:forEach var="su" items="${suppliers}">  
				                            <option <c:if test="${model.enterpriseEntity.supplierCode==su.supplierCode}" > selected="selected"</c:if>
				                            value="${su.supplierCode}">${su.supplierName}</option>
				                        </c:forEach>  
				                        </c:if>
				                    </select></td>
							</tr>
							<tr>
								<td align="right" class="td-title"><font color="red">*&nbsp;</font>企业编号:</td>
								<td align="center"><input type="text" id="enterpriseCode" name="enterpriseCode" class="form-control"
									<c:if test="${operate!=3}">readonly="true"</c:if> value="${model.enterpriseEntity.enterpriseCode}"></td>
								<td align="right" class="td-title"><font color="red">*&nbsp;</font>企业名称:</td>
								<td align="center"><input type="text" id="enterpriseName" name="enterpriseName" class="form-control"
									<c:if test="${operate==1}">readonly="true"</c:if> value="${model.enterpriseEntity.enterpriseName}"></td>
								<td align="right" style="font-weight:bold;">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话:</td>
								<td align="center"><input type="text" id="enterpriseTel" name="enterpriseTel" class="form-control"
									<c:if test="${operate==1}">readonly="true"</c:if> value="${model.enterpriseEntity.enterpriseTel}"></td>
							</tr>
							<tr>
								<td align="right" class="td-title">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址:</td>
								<td colspan="5">
									<input type="hidden" id="provinceName" name="provinceName" value="${model.enterpriseEntity.provinceName}"/>
									<select class="form-control" style="width:150px;float:left;margin-right:8px;" id="provinceCode" name="provinceCode" onchange="selectCitys(this)" >  
				                        <option value="">-请选择省-</option>  
				                        <c:forEach var="pv" items="${provinceList}">  
				                            <option <c:if test="${model.enterpriseEntity.provinceCode==pv.code}" > selected="selected"</c:if>
				                            value="${pv.code}">${pv.name}</option>
				                        </c:forEach>  
				                    </select>
				                    <input type="hidden" id="cityName" name="cityName" value="${model.enterpriseEntity.cityName}"/>
				                    <select class="form-control" style="width:150px;float:left;margin-right:8px;" id="cityCode" name="cityCode" onchange="selectAreas(this)">  
				                        <option value="">-请选择市-</option>
				                        <c:if test="${ not empty citylist}" > 
				                        <c:forEach var="pv" items="${citylist}">  
				                            <option <c:if test="${model.enterpriseEntity.cityCode==pv.code}" > selected="selected"</c:if>
				                            value="${pv.code}">${pv.name}</option>
				                        </c:forEach> 
				                         </c:if>
				                    </select>
				                    <input type="hidden" id="areaName" name="areaName" value="${model.enterpriseEntity.areaName}"/>
				                    <select class="form-control" style="width:150px;float:left;margin-right:8px;" id="areaCode" name="areaCode"> 
				                    	<option value="">-请选择区\县-</option>
				                        <c:if test="${ not empty countylist}" > 
				                        <c:forEach var="pv" items="${countylist}">  
				                            <option <c:if test="${model.enterpriseEntity.areaCode==pv.code}" > selected="selected"</c:if>
				                            value="${pv.code}">${pv.name}</option>
				                        </c:forEach>  
				                        </c:if>
				                    </select>
				                    <input type="text" id="street" name="street" class="form-control" style="width:300px;"
				                    	<c:if test="${operate==1}">readonly="true"</c:if> value="${model.enterpriseEntity.street}"/>
								</td>
							</tr>
							
							<tr><td colspan="6"><hr class="hr-line-dashed"/></td></tr>
							
							<tr>
								<td align="right" class="td-title">联&nbsp;&nbsp;络&nbsp;人:</td>
								<td align="center"><input type="text" id="contactName" name="contactName" class="form-control"
									<c:if test="${operate==1}">readonly="true"</c:if> value="${model.enterpriseEntity.contactName}"></td>
								<td align="right" class="td-title">公司邮箱:</td>
								<td align="center"><input type="text" id="contactMail" name="contactMail" class="form-control"
									<c:if test="${operate==1}">readonly="true"</c:if> value="${model.enterpriseEntity.contactMail}"></td>
								<td align="right" style="font-weight:bold;">手&nbsp;&nbsp;机&nbsp;&nbsp;号:</td>
								<td align="center"><input type="text" id="contactMobile" name="contactMobile" class="form-control"
									<c:if test="${operate==1}">readonly="true"</c:if> value="${model.enterpriseEntity.contactMobile}"></td>
							</tr>
							<tr>
								<td align="right" class="td-title">微信/QQ:</td>
								<td align="center"><input type="text" id="contactTencent" name="contactTencent" class="form-control"
									<c:if test="${operate==1}">readonly="true"</c:if> value="${model.enterpriseEntity.contactTencent}"></td>
								<td align="right" style="font-weight:bold;">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话:</td>
								<td align="center"><input type="text" id="contactTel" name="contactTel" class="form-control"
									<c:if test="${operate==1}">readonly="true"</c:if> value="${model.enterpriseEntity.contactTel}"></td>
							</tr>
							
							<tr><td colspan="6"><hr class="hr-line-dashed"/></td></tr>
							
							<tr>
								<td align="right" class="td-title"><font color="red">*&nbsp;</font>企业类型:</td>
								<td align="center">
									<select class="form-control" name="enterpriseType" id="enterpriseType">
										<option value="">--请选择--</option>
										<c:forEach items="<%=EnterpriseTypeEnum.values()%>" var="e" >
											<option <c:if test="${model.enterpriseEntity.enterpriseType==e.code}" > selected="selected"</c:if>
											value="${e.code}">${e.name}</option>
										</c:forEach>
	                        		</select>
                        		</td>
								<td align="right" class="td-title"><font color="red">*&nbsp;</font>平台经理:</td>
								<td align="center">
									<select class="form-control" name="manger" id="manger">
			                            <option value="">--请选择--</option>
			                            <c:forEach items="${users}" var="user" >
			                                <option <c:if test="${model.enterpriseEntity.manger==user.userUid}" > selected="selected"</c:if>
			                                value="${user.userUid}">${user.userName}</option>
			                            </c:forEach>
			                        </select>
								</td>
							</tr>
							<tr>
								<td align="right" class="td-title"><font color="red">*&nbsp;</font>开户日期:</td>
								<td align="center"><input type="text" id="openTime" name="openTime" class="form-control"
									<c:if test="${operate==1}">readonly="true"</c:if> 
									value="<fmt:formatDate value="${model.enterpriseEntity.openTime}" pattern="yyyy-MM-dd"/>"></td>
								<td align="right" style="font-weight:bold;"><font color="red">*&nbsp;</font>截止日期:</td>
								<td align="center"><input type="text" id="tillTime" name="tillTime" class="form-control"
									<c:if test="${operate==1}">readonly="true"</c:if> 
									value="<fmt:formatDate value="${model.enterpriseEntity.tillTime}" pattern="yyyy-MM-dd"/>"></td>
							</tr>
							
							<tr><td colspan="6" style="font-size:13px;color:#1bb394;font-weight:bold;">供餐信息</td></tr>
							<tr>
								<td align="right" class="td-title">餐费标准:</td>
								<td colspan="5">
									<font class="mtop inner-title" style="text-align:right;"><font color="red">*&nbsp;</font>老板餐</font>
								 	<input type="text" id="bossPrice" name="bossPrice" class="form-control inner-input"
				                    	<c:if test="${operate==1}">readonly="true"</c:if> value="${model.configEntity.bossPrice}"/>
				                    <font class="mtop inner-title">元</font>
				                    <font class="mtop inner-title" style="text-align:right;"><font color="red">*&nbsp;</font>员工餐</font>
								 	<input type="text" id="empPrice" name="empPrice" class="form-control inner-input"
				                    	<c:if test="${operate==1}">readonly="true"</c:if> value="${model.configEntity.empPrice}"/>
				                    <font class="mtop inner-title">元</font>
				                </td>
							</tr>
							<tr><td colspan="6"><hr class="hr-line-dashed"/></td></tr>
							<tr>
								<td align="right" class="td-title">配送午餐:</td>
								<td align="center">
									<select class="form-control" name="forLunch" id="forLunch">
										<option <c:if test="${model.configEntity.forLunch=='1'}" > selected="selected" </c:if> value="1">是</option>
										<option <c:if test="${model.configEntity.forLunch=='0'}" > selected="selected" </c:if> value="0">否</option>
	                        		</select>
                        		</td>
								<td align="right" class="td-title">开始时间:</td>
								<td align="center"><input type="text" id="lunchStart" name="lunchStart" class="form-control" placeholder="时:分(24小时制 半角冒号)"
									<c:if test="${operate==1}">readonly="true"</c:if> 
									value="${fn:substring(model.configEntity.lunchStart, 0, fn:length(model.configEntity.lunchStart)-3)}"></td>
								<td align="right" style="font-weight:bold;">截止时间:</td>
								<td align="center"><input type="text" id="lunchEnd" name="lunchEnd" class="form-control" placeholder="时:分(24小时制 半角冒号)"
									<c:if test="${operate==1}">readonly="true"</c:if> 
									value="${fn:substring(model.configEntity.lunchEnd, 0, fn:length(model.configEntity.lunchEnd)-3)}"></td>
							</tr>
							<tr><td colspan="6"><hr class="hr-line-dashed"/></td></tr>
							<tr>
								<td align="right" class="td-title">配送晚餐:</td>
								<td align="center">
									<select class="form-control" name="forDinner" id="forDinner">
										<option <c:if test="${model.configEntity.forDinner=='1'}" > selected="selected" </c:if> value="1">是</option>
										<option <c:if test="${model.configEntity.forDinner=='0'}" > selected="selected" </c:if> value="0">否</option>
	                        		</select>
                        		</td>
								<td align="right" class="td-title">开始时间:</td>
								<td align="center"><input type="text" id="dinnerStart" name="dinnerStart" class="form-control" placeholder="时:分(24小时制 半角冒号)"
									<c:if test="${operate==1}">readonly="true"</c:if> 
									value="${fn:substring(model.configEntity.dinnerStart, 0, fn:length(model.configEntity.dinnerStart)-3)}"></td>
								<td align="right" style="font-weight:bold;">截止时间:</td>
								<td align="center"><input type="text" id="dinnerEnd" name="dinnerEnd" class="form-control" placeholder="时:分(24小时制 半角冒号)"
									<c:if test="${operate==1}">readonly="true"</c:if> 
									value="${fn:substring(model.configEntity.dinnerEnd, 0, fn:length(model.configEntity.dinnerEnd)-3)}"></td>
							</tr>

							
							<tr><td colspan="6" style="font-size:13px;color:#1bb394;font-weight:bold;">财务信息</td></tr>
							<tr>
								<td align="right" class="td-title">发票抬头:</td>
								<td align="center"><input type="text" id="invoiceTitle" name="invoiceTitle" class="form-control"
									<c:if test="${operate==1}">readonly="true"</c:if> value="${model.financeEntity.invoiceTitle}"></td>
								<td align="right" class="td-title">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</td>
								<td align="center"><input type="text" id="enterpriseAccount" name="enterpriseAccount" class="form-control"
									<c:if test="${operate==1}">readonly="true"</c:if> value="${model.financeEntity.enterpriseAccount}"></td>
								<td align="right" class="td-title">税&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</td>
								<td align="center"><input type="text" id="enterpriseTax" name="enterpriseTax" class="form-control"
									<c:if test="${operate==1}">readonly="true"</c:if> value="${model.financeEntity.enterpriseTax}"></td>
							</tr>
							<tr>
								<td align="right" class="td-title">结算周期:</td>
								<td align="center">
									<select class="form-control" name="checkType" id="checkType">
										<option value="">--请选择--</option>
										<c:forEach items="<%=FinanceCheckTypeEnum.values()%>" var="e" >
											<option <c:if test="${model.financeEntity.checkType==e.code}" > selected="selected"</c:if>
											value="${e.code}">${e.name}</option>
										</c:forEach>
	                        		</select>
                        		</td>
								<td align="right" class="td-title">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期:</td>
								<td align="center">
									<label class="td-title mtop" style="width:10%;float:left;">T+</label>
									<div style="width:90%;float:left;"><input type="text" id="feeDay" name="feeDay" class="form-control"
									<c:if test="${operate==1}">readonly="true"</c:if> value="${model.financeEntity.feeDay}"></div>
								</td>
							</tr>
						</table>
						
						<div class="row">
							<div class="col-sm-5"></div>
							
							<c:if test="${operate==1}">
								<div class="col-sm-1"><button class="btn btn-white" type="button" onclick="toList();">返回</button></div>
							</c:if>
							<c:if test="${operate==2}">
								<div class="col-sm-1">
									<button class="btn btn-primary" id="saveBtn" type="button" onclick="operateEnterprise(0,0);">保存</button>
								</div>
								<div class="col-sm-1">
									<button class="btn btn-primary" id="submitBtn" type="button" onclick="operateEnterprise(0,1);">提交</button>
								</div>
							</c:if>
							<c:if test="${operate==3}">
								<div class="col-sm-1">
									<button class="btn btn-primary" id="saveBtn" type="button" onclick="operateEnterprise(1,0);">保存</button>
								</div>
								<div class="col-sm-1">
									<button class="btn btn-primary" id="submitBtn" type="button" onclick="operateEnterprise(1,1);">提交</button>
								</div>
							</c:if>
							<div class="col-sm-1">
								<button class="btn btn-white" type="button" onclick="toList();">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
	$(function (){
		//初始化日期
		CommonUtils.datePickerFormat("openTime");
		CommonUtils.datePickerFormat("tillTime");
	});
	
    function selectCitys() {
		$("#cityCode option").remove();
	    $("#areaCode option").remove();
	    var p= $('#provinceCode option:selected') .val(); 
	     $.ajax({
	        type: "post",
	        contentType: "application/json",
	        url: "base/region/listByParentCode",
	        data: "{pid:'" +  p + "'}",
	        success: function (data) {
	        	$("#cityCode").append("<option value=''>--请选择--</option>");
	        	json = eval(data)
	            for (var i = 0; i < json.length; i++) {
	                var a = "<option value='" + json[i].code + "'>" + json[i].name + "</option>";
	                $("#cityCode").append(a);
	            }
	        	$("#areaCode").append("<option value=\"\">--请选择--</option>");
	        }
	    }) 
	}
	function selectAreas() {
	    $("#areaCode option").remove();
	    var p= $('#cityCode option:selected') .val(); 
	     $.ajax({
	        type: "post",
	        contentType: "application/json",
	        url: "base/region/listByParentCode",
	        data: "{pid:'" +  p + "'}",
	        success: function (data) {
	        	$("#areaCode").append("<option value=\"\">--请选择--</option>");
	        	json = eval(data)
	            for (var i = 0; i < json.length; i++) {
	                var a = "<option value='" + json[i].code + "'>" + json[i].name + "</option>";
	                $("#areaCode").append(a);
	            }
	        }
	    }) 
	}
	
	function callBack(parent){
        parent.refreshData("listTable");
    }
	function toList() {
	    $.callBackParent("base/enterprise/list",true,callBack);
	}
	
	//添加企业
    function operateEnterprise(operateType,status) {
        $("#saveBtn").attr("disabled","disabled");
        
       var checkMsg = checkParam();
        if(!isNullOrBlank(checkMsg)){
            layer.alert(checkMsg, {icon: 5,time: 2000, title:'提示'});
            $("#saveBtn").removeAttr("disabled");
            return;
        }
        
        var openTime = $("#openTime").val();
		var tillTime = $("#tillTime").val();

		if (openTime == "") {
			openTime = undefined;
		} else {
			openTime = openTime.replace(/-/g,"/");
			openTime += " 00:00:00";
		}
		if (tillTime == "") {
			tillTime = undefined;
		} else
			tillTime = tillTime.replace(/-/g,"/");
			tillTime += " 23:59:59";
		
        CommonUtils.ajaxPostSubmit("base/enterprise/operate", {
                "operateType" : operateType,
                "enterpriseStatus" : status,
                "id" :  $("#id").val(),
                "supplierCode" :  $("#supplierCode").val(),
                "enterpriseCode" :  $("#enterpriseCode").val(),
                "enterpriseName" : $("#enterpriseName").val(),
                "enterpriseTel" : $("#enterpriseTel").val(),
                "enterpriseType" : $("#enterpriseType").val(),
                "manger" : $("#manger").val(),
                "provinceCode" : $("#provinceCode").val(),
                "cityCode" : $("#cityCode").val(),
                "areaCode" : $("#areaCode").val(),
                "street" : $("#street").val(),
                "openTime" : openTime,
				"tillTime" : tillTime,
                "contactName" : $("#contactName").val(),
                "contactMail" : $("#contactMail").val(),
                "contactMobile" : $("#contactMobile").val(),
                "contactTel" : $("#contactTel").val(),
                "contactTencent" : $("#contactTencent").val(),
                "empPrice" : $("#empPrice").val(),
                "bossPrice" : $("#bossPrice").val(),
                "forLunch" : $("#forLunch").val(),
                "lunchStart" : handleTime($("#lunchStart").val()),
                "lunchEnd" : handleTime($("#lunchEnd").val()),
                "forDinner" : $("#forDinner").val(),
                "dinnerStart" : handleTime($("#dinnerStart").val()),
                "dinnerEnd" : handleTime($("#dinnerEnd").val()),
                "otherAddress" : $("#otherAddress").val(),
                "invoiceTitle" : $("#invoiceTitle").val(),
                "enterpriseAccount" : $("#enterpriseAccount").val(),
                "enterpriseTax" : $("#enterpriseTax").val(),
                "checkType" : $("#checkType").val(),
                "feeDay" : $("#feeDay").val()
            }, function(data){
                if(data){
                    if(data.code == 0){
                        layer.alert("添加成功", {icon: 6,time: 3000, title:'提示'});
                        toList();

                    }else {
                        layer.alert(data.msg, {icon: 5,time: 2000, title:'提示'});
                        $("#saveBtn").removeAttr("disabled");
                    }
                }
            }
        );
    }

  	//参数校验
    function checkParam() {
        if(isNullOrBlank($("#supplierCode").val())){
            return "供餐单位为必填项"
        }
        
        if(isNullOrBlank($("#enterpriseCode").val())){
            return "企业编号为必填项"
        }
        if($("#enterpriseCode").val().length > 50) {
        	return "企业编号长度不能超过50";
        }
        
        if(isNullOrBlank($("#enterpriseName").val())){
            return "企业名称为必填项"
        }
        if($("#enterpriseName").val().length > 50) {
        	return "企业名称长度不能超过50";
        }
        
        if(isNullOrBlank($("#enterpriseType").val())){
            return "企业类型为必填项"
        }
        
        if(isNullOrBlank($("#manger").val())){
            return "平台经理为必填项"
        }
        
        if(isNullOrBlank($("#openTime").val())){
            return "开户时期为必填项"
        }
        
        if(isNullOrBlank($("#tillTime").val())){
            return "截止日期为必填项"
        }
        
        var bossPrice = $("#bossPrice").val();
        if(isNullOrBlank(bossPrice)){
            return "老板餐费为必填项"
        }
        if(!isNumber(bossPrice)) {
        	return "老板餐费只能是正整数";
        }
        
        var empPrice = $("#empPrice").val();
        if(isNullOrBlank(empPrice)){
            return "员工餐费为必填项"
        }
        if(!isNumber(empPrice)) {
        	return "员工餐费只能是正整数";
        }
        
        if(!isMallCheck($("#contactMail").val())){
            return "公司邮箱输入不合法";
        }
        
        if(!isPhoneNumber($("#contactMobile").val())){
            return "手机号输入不合法";
        }
        
        if(!hourMinuteCheck($("#lunchStart").val())){
            return "午餐配送开始时间为HH:mm格式";
        }
        
        if(!hourMinuteCheck($("#lunchEnd").val())){
            return "午餐配送截止时间为HH:mm格式";
        }
        
        if(!hourMinuteCheck($("#dinnerStart").val())){
            return "晚餐配送开始时间为HH:mm格式";
        }
        
        if(!hourMinuteCheck($("#dinnerEnd").val())){
            return "晚餐配送截止时间为HH:mm格式";
        }
        
        if(!isNumber($("#feeDay").val())) {
        	return "账期只能是正整数";
        }

        
	}
  	
  	//正整数校验
  	function isNumber(obj) {
  		var pattern = /^[1-9]*[1-9][0-9]*$/;
  		return patternCheck(obj, pattern);
  	}
  	
  	//手机号校验
  	function isPhoneNumber(obj) {
  		var pattern = /^((1[34578][0-9])\d{8})$/;
  		return patternCheck(obj, pattern);
  	}
  	
  	//邮箱校验
  	function isMallCheck(obj) {
  		var pattern = /^(\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*)$/;
  		return patternCheck(obj, pattern);
  	}
  	
  	//时分校验
  	function hourMinuteCheck(obj) {
 		var pattern = /^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$/;
 		return patternCheck(obj, pattern);
 	}
  	
  	//正则校验
  	function patternCheck(obj, pattern) {
 		if(!isNullOrBlank(obj)){
 			if(!pattern.test(obj)) {
   			return false;
 			}
 		}
 		return true;
  	}
   
  	//为空校验
    function isNullOrBlank(obj){
        return obj == undefined || obj == null || $.trim(obj).length == 0;
    }
  	
  	function handleTime(value) {
  		return value == "" ? "" : value+":00"; 
  	}

</script>
</body>
</html>
