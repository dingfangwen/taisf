用户相关文档
-----------------------------------  

## 1.用户启动页配置

###  地址

    user/index


###  提交方式
类型|描述
---|---
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式


### 参数
    无

返回信息:成功

    {
        "msg": {
            "info": "",
            "code": 0,
            "success": true
        },
        "data": {
            "userInfo": {
                "userName": "name",
                "userPic":"",
                "userCard":"",
                "enterpriseCode": "code",
                "enterpriseName": "11",
                "userPhone": "123",
                "userType": 1, 
                "userRole": 1,
                "productSource": 1 
            },
            "drawBalance": 0,
            "timeTitle": "点餐（以下今天餐单）",
            "timeMsg": "晚餐订餐倒计时:",
            "timeLast": 5393182,
            "orderFlag": true,
            "orderType":1,
            "supplierCode":"123"
        }
    }

返回信息:失败

    {
    msg: {
        info: "参数异常",
        code: 1,
        success: false
    },
    data: null
    }
    
返回关键字段解释
    
字段|类型|描述
---|---|---
userInfo|对象|用户对象
drawBalance|Integer|用户余额
timeTitle|String|倒计时标题
timeMsg|String|倒计时前缀
timeLast|Long|倒计时 毫秒
orderFlag|boolen| true:可订饭 false:不可订饭
orderType|Integer|订单类型 这个参数需要带到下单作为下单的参数
supplierCode|String|当前用户对应的供应商code


````
userInfo:对象
    "userInfo": {
        "userName": "name", //用户名
        "userPic":"", //头像
        "userCard":"", //二维码
        "enterpriseCode": "code",//企业编号
        "enterpriseName": "11", //企业名称
        "userPhone": "123", //用户电话
        "userType": 1, //用户类型 暂时用不到
        "userRole": 1, //用户角色 1员工 2 老板
        "productSource": 1 // 1 清真 2 西餐 3 普通
    },
           
````  
      
demo:


````
      curl -X POST \
      http://localhost:8080/user/index \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: e5f78a3b-7150-0583-b602-c4a6d37359bb' \
      -H 'token: token' \
      -H 'traceinfo: applicationCode=aa;deviceUuid=deviceUuid;'

````

         
## 2.登录

###  地址

    user/login


###  提交方式
类型|描述
---|---
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式


### 参数信息
字段|类型|描述
---|---|---
userPhone|String|用户电话
pwd|String|用户名密码,不要明文传密码,秘钥需要客户端做md5加密之后再传输

返回信息:成功

   {
       "msg": {
           "info": "",
           "code": 0,
           "success": true
       },
       "data": "ff8080815f0bc096015f0bc096510000" //就是返回的token值
   }

返回信息:失败

    {
        "msg": {
            "info": "异常的头信息",
            "code": 1,
            "success": false
        },
        "data": {}
    }
   
demo:

````
      curl -X POST \
      http://localhost:8080/user/login \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: 2294e873-d761-11b0-01cc-60cfec39e264' \
      -H 'token: token' \
      -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;versionCode=versionCode;source=1;' \
      -d '{"userPhone":"123","pwd":"123"}'

````




         
## 3.验证码登录

###  地址

    user/loginByCode


###  提交方式
类型|描述
---|---
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式


### 参数信息
字段|类型|描述
---|---|---
userPhone|String|用户电话
msgCode|String|验证码

返回信息:成功

   {
       "msg": {
           "info": "",
           "code": 0,
           "success": true
       },
       "data": "ff8080815f0bc096015f0bc096510000" //就是返回的token值
   }

返回信息:失败

    {
        "msg": {
            "info": "异常的头信息",
            "code": 1,
            "success": false
        },
        "data": {}
    }
   
demo:

````
      curl -X POST \
      http://localhost:8080/user/login \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: 2294e873-d761-11b0-01cc-60cfec39e264' \
      -H 'token: token' \
      -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;versionCode=versionCode;source=1;' \
      -d '{"userPhone":"123","pwd":"123"}'

````


           
## 4.退出

###  地址

    user/logout


###  提交方式
类型|描述
---|---
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式


### 参数信息
字段|类型|描述
---|---|---
token|String|token

返回信息:成功

   {
       "msg": {
           "info": "",
           "code": 0,
           "success": true
       },
       "data": {}
   }

返回信息:失败

    {
        "msg": {
            "info": "异常的头信息",
            "code": 1,
            "success": false
        },
        "data": {}
    }

demo:


````
      curl -X POST \
      http://localhost:8080/user/logout \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: 1a044f92-378e-33e7-333d-dcaf57758790' \
      -H 'token: token' \
      -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;versionCode=versionCode;source=1;' \
      -d '{"token":"ff8080815f0bc096015f0bc096510000"}'

````
           
## 5.注册

###  地址

    user/regist


###  提交方式
类型|描述
---|---
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式


### 参数信息
字段|类型|描述
---|---|---
userPhone|String|用户手机号
pwd|String|密码,一定是MD5之后的,不接受明文密码
msgCode|String|手机验证码 原来是code,后来调整msgCode 请注意


返回信息:成功

   {
       "msg": {
           "info": "",
           "code": 0,
           "success": true
       },
       "data": {
           "userPhone": "123",
           "userName": "name",
           "enterpriseCode": "code",
           "enterpriseName": "11",
           "userRoleName": "普通餐",
           "drawBalance": 0,
           "addrList": [
               "add"
           ]
       }
   }

    
返回关键字段解释
    
字段|类型|描述
---|---|---
userPhone|String|用户电话
userName|String|用户名
enterpriseCode|String|企业编号
enterpriseName|String|企业名称
userRoleName|String|餐类型
drawBalance|Integer|余额
addrList|List|地址列表 类型[字符串]


           
返回信息:失败

    {
        "msg": {
            "info": "异常的头信息",
            "code": 1,
            "success": false
        },
        "data": {}
    }

demo:

````
    curl -X POST \
      http://localhost:8080/user/regist \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: 5a1456e4-cf8c-e319-6823-ff83e9d39f18' \
      -H 'token: token' \
      -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;versionCode=versionCode;source=1;' \
      -d '{"userPhone":"123","pwd":"123"}'
````





## 6.发送验证码

###  地址

    send/code?code=103&userTel=12313123123131

###  提交方式
类型|描述
---|---
提交方式|get

参数 放在url



### 参数信息
字段|类型|描述
---|---|---
code|String|修改支付密码传103 用户登录:101  
userTel|String|用户电话
random|String|随机数
sign|String|验签的key:Oj0mUTVY




返回信息:成功

    {
        "msg": {
            "info": "",
            "code": 0,
            "success": true
        },
        "data": {}
    }

返回信息:失败

    {
    msg: {
        info: "参数异常",
        code: 1,
        success: false
    },
    data: null
    }
    

      
demo:


````
    curl -X GET \
      'http://59.110.222.82/send/code?code=101&userTel=18618499887&random=123&sign=E5DA874815ED0B2E7228C64D087B703C' \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: cea12c2c-aeab-8eb5-7c51-e47ed906a6b4' \
      -H 'token: ff8080815f0bc096015f0bc096530001' \
      -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;versionCode=versionCode;source=1;' \
      -d '{"userPhone":"15120095720","pwd":"fcea920f7412b5da7be0cf42b8c93759"}'

````



## 7.修改密码

###  地址

    user/changeUserPwd


###  提交方式
类型|描述
---|---
提交方式|post

参数 放在url

    
### 参数信息
字段|类型|描述
---|---|---
oldPwd|String|原始密码,一定是MD5之后的,不接受明文密码
newPwd|String|新密码,一定是MD5之后的,不接受明文密码
    
    

返回信息:成功

    {
        "msg": {
            "info": "",
            "code": 0,
            "success": true
        },
        "data": {}
    }

返回信息:失败

    {
    msg: {
        info: "参数异常",
        code: 1,
        success: false
    },
    data: null
    }
    

      
demo:


````
   curl -X POST \
     http://localhost:8080/user/changeUserPwd \
     -H 'cache-control: no-cache' \
     -H 'content-type: application/json' \
     -H 'postman-token: 9ef5b6ac-acf3-3b89-f426-a832a55e202d' \
     -H 'token: token' \
     -H 'traceinfo: applicationCode=aa;deviceUuid=deviceUuid;' \
     -d '{
   	"oldPwd":"123",
   	"newPwd":"123456",
   	"msgCode":"123456"
   }'

````




## 8.修改支付密码

###  地址

    user/changeAccountPwd


###  提交方式
类型|描述
---|---
提交方式|post

参数 放在url


### 参数信息
字段|类型|描述
---|---|---
newPwd|String|新密码,一定是MD5之后的,不接受明文密码
msgCode|String|手机验证码-测试统一用123456
isPWd|Integer|1:免密 0或者不填写表示不免密

    

返回信息:成功

    {
        "msg": {
            "info": "",
            "code": 0,
            "success": true
        },
        "data": {}
    }

返回信息:失败

    {
    msg: {
        info: "参数异常",
        code: 1,
        success: false
    },
    data: null
    }
    

      
demo:


````
  curl -X POST \
    http://localhost:8080/user/changeAccountPwd \
    -H 'cache-control: no-cache' \
    -H 'content-type: application/json' \
    -H 'postman-token: 57ef03d1-9702-8107-6317-9c6401116e3b' \
    -H 'token: token' \
    -H 'traceinfo: applicationCode=aa;deviceUuid=deviceUuid;' \
    -d '{
  	"oldPwd":"123",
  	"newPwd":"123456",
  	"msgCode":"123456"
  }'

````




## 9.修改支付密码

###  地址

    user/closePwd


###  提交方式
类型|描述
---|---
提交方式|post

参数 放在url


### 参数信息
无

    

返回信息:成功

    {
        "msg": {
            "info": "",
            "code": 0,
            "success": true
        },
        "data": {}
    }

返回信息:失败

    {
    msg: {
        info: "参数异常",
        code: 1,
        success: false
    },
    data: null
    }
    

      
demo:


````
  curl -X POST \
    http://localhost:8080/user/closePwd \
    -H 'cache-control: no-cache' \
    -H 'content-type: application/json' \
    -H 'postman-token: 57ef03d1-9702-8107-6317-9c6401116e3b' \
    -H 'token: token' \
    -H 'traceinfo: applicationCode=aa;deviceUuid=deviceUuid;' \
  

````

