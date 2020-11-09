/**
 * jQuery.validate.js 扩展校验
 */
jQuery(function() {
	//扩展经纬度校验 经度:-180.000000~180.000000, 纬度:-90.000000~90.000000
	jQuery.validator.addMethod("geo", function(value, element) {
		value = $.trim(value);
		var regex = /^(-?((180)|(((1[0-7]\d)|([1-9]\d)|([1-9]))(\.\d{1,6})?)))(\,)(-?((90)|((([1-8]\d)|([1-9]))(\.\d{1,6})?)))$/;
		return this.optional(element) || regex.test(value);       
    }, "经纬度格式不正确");
	
	//扩展正整数校验
	jQuery.validator.addMethod("integer", function(value, element) {
		value = $.trim(value);
		var regex = /^[1-9]\d*$/;
 	return this.optional(element) || regex.test(value);       
    }, "请输入正整数");
	
	//扩展非负整数校验
	jQuery.validator.addMethod("nonnegaInteger", function(value, element) {
		value = $.trim(value);
		var regex = /^(0|[1-9]\d*)$/;
		return this.optional(element) || regex.test(value);       
	}, "请输入非负整数");
	
	//扩展经度校验 经度:-180.000000~180.000000
	jQuery.validator.addMethod("longitude", function(value, element) {
		value = $.trim(value);
		var regex = /^(-?((180)|(((1[0-7]\d)|([1-9]\d)|([1-9]))(\.\d{1,6})?)))$/;
		return this.optional(element) || regex.test(value);       
    }, "经度格式不正确");
	
	//扩展纬度校验  纬度:-90.000000~90.000000
	jQuery.validator.addMethod("latitude", function(value, element) {
		value = $.trim(value);
		var regex =/^(-?((90)|((([1-8]\d)|([1-9]))(\.\d{1,6})?)))$/;
		return this.optional(element) || regex.test(value);       
    }, "纬度格式不正确");
	
	// 扩展手机号码校验
	jQuery.validator.addMethod("isMobile", function(value, element) {
		value = $.trim(value);
		var regex = /^1[3|5|7|8|][0-9]{9}$/;
		return this.optional(element) || regex.test(value);
	}, "手机号码不正确");
});