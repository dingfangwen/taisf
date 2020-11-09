/**
 * 公用js使用，能提出来的js大家都提出来哈
 * @param $
 */

//获取当前网址，如： http://localhost:8080/Tmall/index.jsp 
var curWwwPath=window.document.location.href; 

//获取主机地址之后的目录如：/Tmall/index.jsp 
var pathName=window.document.location.pathname; 
var pos=curWwwPath.indexOf(pathName); 

//获取主机地址，如： http://localhost:8080 
var localhostPaht=curWwwPath.substring(0,pos); 

//获取带"/"的项目名，如：/Tmall 
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1); 
// var SERVER_CONTEXT = localhostPaht+"/";

var SERVER_CONTEXT = "http://localhost:8080/jk-mis-oms/";
if(localhostPaht.indexOf("localhost")>0){
	SERVER_CONTEXT = localhostPaht+projectName+"/";
}
(function ($) {
	
	var commonUtils = {};
	
	/**
	 *  所有的ajax调用  可以使用此方法提交 url:要提交的路径 data:提交的数据 binary:提交成功的回调函数
	 *  @param url 请求的url
	 *  @param data 请求数据
	 *  @param callback 回调函数
	 */
	commonUtils.ajaxPostSubmit =  function (url,data,callback){
		$.ajax({
	    type: "POST",
	    url: url,
	    dataType:"json",
	    data: data,
	    success: function (result) {
	    	callback(result);
	    },
	    error: function(result) {
	       alert("error:"+result);
	        }
	     });
	}

	/**
	 * 同意查询接口
	 */
	commonUtils.query = function(){
		$('#listTable').bootstrapTable('selectPage', 1);
	}
	//分转化成元
	commonUtils.getMoney = function (value, row, index){
		
		if(value==null||value==""||value==undefined) {
			return "*";
		}
    	return parseFloat(value)/100;
		
	}
    //分转化成元
    commonUtils.getMoneyStr = function (value, row, index){

        if(value==null||value==""||value==undefined ||   typeof(value)!="undefined" ) {
            return "0 元";
        }
        return parseFloat(value)/100 + "元";

    }
	// 格式化时间
	commonUtils.formateDate =  function(value, row, index) {
		
		  if(value==null||value == ""||value==undefined){
			  return "";
		  }
		  var date = new Date(value);
		  var month = date.getMonth()+1;
		  var day = date.getDate();
		  var hours = date.getHours();
		  var minutes = date.getMinutes();
		  var seconds = date.getSeconds();
		  month = month<10?'0'+month:month;
		  day = day<10?'0'+day:day;
		  hours = hours<10?'0'+hours:hours;
		  minutes = minutes<10?'0'+minutes:minutes;
		  seconds = seconds<10?'0'+seconds:seconds;
		  return date.getFullYear()+"-"+month+"-"+day+"  "+hours+":"+minutes+":"+seconds;
	}
	// 格式化时间(隔1个空格)
	commonUtils.formateDateTime =  function(value, row, index) {
		  if(value==null||value == ""||value==undefined){
			  return "";
		  }
		  var date = new Date(value);
		  var month = date.getMonth()+1;
		  var day = date.getDate();
		  var hours = date.getHours();
		  var minutes = date.getMinutes();
		  var seconds = date.getSeconds();
		  month = month<10?'0'+month:month;
		  day = day<10?'0'+day:day;
		  hours = hours<10?'0'+hours:hours;
		  minutes = minutes<10?'0'+minutes:minutes;
		  seconds = seconds<10?'0'+seconds:seconds;
		  return date.getFullYear()+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
	}
	// 格式化日期
	commonUtils.formateDay =  function(value, row, index) {
		
		  if(value==null||value == ""||value==undefined){
			  return "-";
		  }
		  var date = new Date(value);
		  var month = date.getMonth()+1;
		  var day = date.getDate();
		  var date = new Date(value);
		  month = month<10?'0'+month:month;
		  day = day<10?'0'+day:day;
		  return date.getFullYear()+"-"+month+"-"+day;
	}
	
	/**
	 * 获取常量1
	 */
	commonUtils.getConstant = function(value, row, index){
		return 1;
	}

	/* 加载树  公共方法封装  domId:id信息  obj:树的数据  binary：点击回调函数*/
	commonUtils.treeViewCommon =  function (domId,obj,binary){
		//加载左侧树	
	    $('#'+domId).treeview({
	        color: " inherit",
	        data: obj,
	        onNodeSelected: function (event, node) {
	        binary(event, node);
	     }
	  })
	}
	/* layer方法confirm  mes:展示的信息 iconNum：layer的icon binary：confirm方法回调  */
	 commonUtils.layerConfirm = function(mes,iconNum,binary){
		layer.confirm(mes, {icon: iconNum, title:'提示'},function(index){
			binary(index);
		});
	}
	/* 笑脸图标，显示2000ms 自动消失，不需要用户确认*/
	 commonUtils.msgLayer =  function (mesg){
		layer.msg(mesg, {icon: 6,time: 2000, title:'提示'});
	}

	 /* 日历插件 */
	 commonUtils.datePicker = function(elemId){
		 laydate({
		      elem: '#'+elemId, //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
		      event: 'focus' //响应事件。如果没有传入event，则按照默认的click
		  });
	 }
	 commonUtils.datePickerFormat = function(elemId,formatStr){
	 	if(formatStr==undefined)	formatStr = 'YYYY-MM-DD';
		 laydate({
		    elem: '#' + elemId,
		    format: formatStr,
		    istime: false,
		    istoday: true
		 });
	 }

    commonUtils.initDateFormat = function(elemId){
        laydate({
            elem: '#' + elemId,
            format: 'YYYY-MM-DD hh:mm:ss',
            istime: true,
            istoday: true
        });
    }

	 commonUtils.datePickerFormatClass = function(elemClass){
		 laydate({
		    elem: '.' + elemClass,
		    format: 'YYYY-MM-DD ',
		    istime: true,
		    istoday: true
		 });
	 }

	commonUtils.datePickerMonth = function(elemId){
		laydate({
			elem: '#' + elemId,
			format: 'YYYY-MM'
		});
	}
	
	commonUtils.datePickerMonthChoose = function(elemId,checkDate){
		laydate({
			elem: '#' + elemId,
			format: 'YYYY-MM',
			choose:checkDate
		});
	}
	
	commonUtils.datePickerTimeChoose = function(elemId){
		laydate({
			elem: '#' + elemId,
			format: 'YYYY/MM/DD hh:mm:ss',
			min: laydate.now(), //设定最小日期为当前日期
		    max: '2099-06-16 23:59:59', //最大日期
		    istime: true,
		    istoday: false
		});
	}


	 
	 /*paramObj示例 {isWeightS:$('#test1').val(),zoName:$('#zoName').val()}*/
	 /* 分页  tableName:table名称,paramObj:参数对象,params:table内部对象     */
	 commonUtils.paginationCommon = function(tableName,paramObj,params){
		    var result = paramObj;
			if(typeof(paramObj) != undefined && typeof(paramObj) == "object"){
				result.limit = params.limit; //页数限制
				result.page = $('#'+tableName).bootstrapTable('getOptions').pageNumber;
				
				/*var resultName = '';
				for(var param in paramObj){
					resultName = param;
					result.resultName = paramObj[param];
				}*/
			}
			return result;
	}
	 /**
	  * 获取可点击值
	  */
	 commonUtils.nameShow = function(value){
		 return "<a href='javascript:void(0);'>"+value+"</a>";

	 }



    // 格式化时间
    commonUtils.formateTimeStr = function formateDate(value, row, index) {
        if (value != null) {
            var vDate = new Date(value);
            return vDate.format("yyyy-MM-dd HH:mm:ss");
        } else {
            return "";
        }
    }

    // 格式化时间
    commonUtils.formateDateStr = function formateDate(value, row, index) {
        if (value != null) {
            var vDate = new Date(value);
            return vDate.format("yyyy-MM-dd");
        } else {
            return "";
        }
    }
    
    /** 设置select选择框 为value的值选中*/
    commonUtils.selectedDefault = function(selectName,value,showName){
    	if(typeof(selectName) != undefined && typeof(selectName) == "string"){
    		$("#"+selectName+" option[value='"+value+"']").attr("selected","selected");	
    	}
    	
    	if(typeof(showName) != undefined && typeof(showName) == "string"){
    		$("#"+showName).val($("#"+selectName+" option[value='"+value+"']").html());	
    	}else if(typeof(showName) != undefined && typeof(showName) == "function"){
    		showName(value);
    	}
	}
    /** 参数对象 构造函数方式实现*/
    commonUtils.Param = function(name,value){
    	this.name = name; 
    	this.value = value; 
    	return this;
    }
    //jumpPageOrExportFile(new Param("url","www.baidu.com"),new Param('rentWay','1'),new Param('beginTime','hello'));
    /** 页面跳转、导出excel都可使用 1 需要传入路径名称为'url' 2 传入任意个参数 */
    commonUtils.jumpPageOrExportFile = function(){
    	   var url = "";
    	   var param = "";
    	   var args = arguments;
    	   for(var i in args){
    		   if(args[i].name == 'url'){
    			   url = args[i].value;
    			}else{
    			   if(!!param){
    				   param += "&"+args[i].name+"="+args[i].value;   
    			   }else{
    				   param += args[i].name+"="+args[i].value;
    			   }
    			   
    		   }
    	   }
    	   if(!!param){
    		   url += "?" + param
    	   }
    	  window.location.href = url;
     } 
    /** 动态生成bootstrapTable*/
    commonUtils.strapTable=function (tableId,dataUrl,queryParam,columnsData) { 
    	  $('#'+tableId).bootstrapTable('destroy'); 
    	  $('#'+tableId).bootstrapTable({ 
    	   url: dataUrl,   //请求后台的URL（*） 
    	   contentType :"application/x-www-form-urlencoded",
    	   method: 'post',      //请求方式（*） 
    	   cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*） 
    	   pagination: true,     //是否显示分页（*） 
    	   queryParams: queryParam,//传递参数（*） 
    	   sidePagination: "server",   //分页方式：client客户端分页，server服务端分页（*） 
    	   pageSize: 10,      //每页的记录行数（*） 
    	   pageList: [10, 20, 50],  //可供选择的每页的行数（*） 
    	   columns: columnsData,
    	   toggle:'table',
    	   paginationFirstText:'首页',
    	   paginationPreText:'上一页',
    	   paginationNextText:'下一页',
    	   paginationLastText:'末页',
    	   singleSelect:true,
    	   height:520,
    	   clickToSelect:true
    	  });
    }
   window.CommonUtils = commonUtils;
}(jQuery));