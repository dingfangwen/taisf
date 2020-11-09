/**
 * 行政区域多级联动
 * @param options
 */
function geoCascade(options) {
    var geoJson = options.geoJson,
    	nationCode = options.nationCode,
    	provinceCode = options.provinceCode,
    	cityCode = options.cityCode,
    	areaCode = options.areaCode;
        
    var temp_html,                       
        oNation = $("select[id^=nationCode]"),
        oProvince = $("select[id^=provinceCode]"),
        oCity = $("select[id^=cityCode]"),
        oArea = $("select[id^=areaCode]");
    
    //初始化国家
    var nation = function(){
    	temp_html = "<option value=''>请选择</option>";
        $.each(geoJson,function(i,nation){
            temp_html+="<option value='" + nation.code + "'>" + nation.text + "</option>";
        });
        oNation.html(temp_html);
        province();
    };
    
    //初始化省份
    var province = function(){
    	$.each(oNation,function(i,obj){
    		temp_html = "<option value=''>请选择</option>";
			var n = oNation.get(i).selectedIndex;
			if (typeof (geoJson[n-1]) != "undefined" 
					&& typeof (geoJson[n-1].nodes[0]) != "undefined") {
				$.each(geoJson[n-1].nodes, function(i, province) {
					temp_html += "<option value='" + province.code + "'>" + province.text + "</option>";
				});
			}
			$(oProvince[i]).html(temp_html);
    	});
        city();
    };
    
    //初始化城市
    var city = function(){
    	$.each(oProvince,function(i,obj){
    		temp_html = "<option value=''>请选择</option>";
			var m = oNation.get(i).selectedIndex;
			var n = oProvince.get(i).selectedIndex;
			if (typeof (geoJson[m-1]) != "undefined"
					&& typeof (geoJson[m-1].nodes[n-1]) != "undefined"
					&& typeof (geoJson[m-1].nodes[n-1].nodes[0]) != "undefined") {
				$.each(geoJson[m-1].nodes[n-1].nodes, function(i, city) {
					temp_html += "<option value='" + city.code + "'>" + city.text + "</option>";
				});
			}
			$(oCity[i]).html(temp_html);
    	});
        area();
    };
   
    //初始化区域
    var area = function(){
    	$.each(oCity,function(i,obj){
	        temp_html = "<option value=''>请选择</option>";
			var m = oNation.get(i).selectedIndex;
			var n = oProvince.get(i).selectedIndex;
			var k = oCity.get(i).selectedIndex;
			if (typeof (geoJson[m-1]) != "undefined"
					&&	typeof (geoJson[m-1].nodes[n-1]) != "undefined"
					&& typeof (geoJson[m-1].nodes[n-1].nodes[k-1]) != "undefined"
					&& typeof (geoJson[m-1].nodes[n-1].nodes[k-1].nodes[0]) != "undefined") {
				$.each(geoJson[m-1].nodes[n-1].nodes[k-1].nodes, function(i, area) {
					temp_html += "<option value='" + area.code + "'>" + area.text + "</option>";
				});
			}
			$(oArea[i]).html(temp_html);
    	});
    };
    
    //国家级联省份
    oNation.change(function(){
   	 	province();
    });
    
    //省份级联城市
    oProvince.change(function(){
   	 	city();
    });
    
    //城市级联区域
    oCity.change(function(){
   		area();
    });
    
    nation();
    
    // 设置国家默认值
    if(nationCode != undefined && $.trim(nationCode).length != 0) {
    	oNation.val(nationCode);
    	province();
    }
    
    // 设置省份默认值
    if(provinceCode != undefined && $.trim(provinceCode).length != 0) {
    	oProvince.val(provinceCode);
    	city();
    }
    
    // 设置城市默认值
    if(cityCode != undefined && $.trim(cityCode).length != 0) {
    	oCity.val(cityCode);
    	area();
    }
    
    // 设置区域默认值
    if(areaCode != undefined && $.trim(areaCode).length != 0) oArea.val(areaCode);
};