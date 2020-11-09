$(document).ready(function () {
	//验证用户名
	var userName = document.getElementById('inputEmail3');
	var aler = document.getElementById('aler');
	userName.onblur = function(){
		userName.style.border = '1px solid #ccc';
		aler.innerHTML = '';
		if(!/^[A-Za-z0-9]{2,20}$/.test(userName.value)){
			userName.style.border = '1px solid red';
			aler.innerHTML = '请输入正确的用户名!';
		}else{
			userName.style.border = '1px solid #ccc';
			aler.innerHTML = '';
		}
	}
	var pass = document.getElementById('inputPassword3');
	pass.onblur = function(){
		pass.style.border = '1px solid #ccc';
		aler.innerHTML = '';
		if(!/^(\w){6,20}$/.test(pass.value)){
			pass.style.border = '1px solid red';
			aler.innerHTML = '请输入正确的密码!';
		}else{
			pass.style.border = '1px solid #ccc';
			aler.innerHTML = '';
		}
	}
	//记住用户名密码

    if ($.cookie("rmbUser") == "true") {
        $("#remPass").attr("checked", true);
        $("#inputEmail3").val($.cookie("username"));
        $("#inputPassword3").val($.cookie("password"));
    }

    function Save() {
        if ( $("#remPass")[0].checked ) {
            var str_username = $("#inputEmail3").val();
            var str_password = $("#inputPassword3").val();
            $.cookie("rmbUser", "true", { expires: 7 }); //存储一个带7天期限的cookie
            $.cookie("username", str_username, { expires: 7 });
            $.cookie("password", str_password, { expires: 7 });
        }
        else {
            $.cookie("rmbUser", "false", { expire: -1 });
            $.cookie("username", "", { expires: -1 });
            $.cookie("password", "", { expires: -1 });
        }
    };
    document.getElementById('login').onclick = function fn(){
    	userName.style.border = '1px solid #ccc';
		pass.style.border = '1px solid #ccc';
		aler.innerHTML = '';
    	if(!/^[A-Za-z0-9]{2,20}$/.test(userName.value)){
    		userName.style.border = '1px solid red';
			aler.innerHTML = '请输入正确的用户名!';
			return false;
		}
		if(!(/^(\w){6,20}$/.test(pass.value))){
			pass.style.border = '1px solid red';
			aler.innerHTML ='请输入正确的密码!';
			return false;
		}
		if(/^[A-Za-z0-9]{2,20}$/.test(userName.value) && /^(\w){6,20}$/.test(pass.value)){
			var requestUrl = document.getElementById('requestUrl');
			//存储用户名
			$.ajax({
				type: "get",
				url: "dealLogin?empMail="+userName.value +"&password="+pass.value+"&t="+new Date().getTime(),
				dataType:"json",
				async:false,
				data: {},
				success: function (result) {
					if(result.code == 0){
						Save();
						window.location.href = 'index';
					}else {
						$("#aler").text(result.msg);
					}
				},
				error: function(result) {
					// layer.alert("", {icon: 5,time: 2000, title:'提示'});
					$("#aler").text("未知错误");
				}
			});


		}
    }
});