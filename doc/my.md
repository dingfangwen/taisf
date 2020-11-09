我的
------------------------------------  

## 1.我的地址

###  地址

    my/address


###  提交方式
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式


### 参数

不要参数
    

返回信息:成功

    {
        "msg": {
            "info": "",
            "code": 0,
            "success": true
        },
        "data": [
            {
                "id": 1,
                "fid": "111",
                "enterpriseCode": "code",
                "address": "add",
                "sendNum": 1,
                "conTel": "tel",
                "conName": "name"
            }
        ]
    }
    

返回关键字段解释
    
字段|类型|描述
---|---|---
address|String|地址
fid|String|地址fid

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
  http://localhost:8080/my/address \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: ad7965d1-c4db-5b11-ed8e-13578afc7210' \
  -H 'token: token' \
  -H 'traceinfo: applicationCode=aa;deviceUuid=deviceUuid;'

````





## 2.获取用户信息

###  地址

    my/user


###  提交方式
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式


### 参数

不要参数
    

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
                "enterpriseCode": "code",
                "enterpriseName": "11",
                "userPhone": "123",
                "userType": 1,
                "userRole": 1,
                "productSource": 1
            },
            "drawBalance": 0
        }
    }
    

返回关键字段解释
    
字段|类型|描述
---|---|---
userName|String|姓名
enterpriseCode|String|企业code
enterpriseName|String|企业名称
userPhone|String|电话
drawBalance|Integer|余额

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
    http://localhost:8080/my/user \
    -H 'cache-control: no-cache' \
    -H 'content-type: application/json' \
    -H 'postman-token: 8f5927e4-fa77-9266-bce2-5e0af2b20260' \
    -H 'token: token' \
    -H 'traceinfo: applicationCode=aa;deviceUuid=deviceUuid;'

````



## 3.充值记录

###  地址

    my/rechargeHistory


###  提交方式
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式


### 参数

字段|是否必填|类型|描述
---|---|---|---
limit|否|Integer|默认一页50
page|否|Integer|页码 从1开始
    

返回信息:成功

    {
        "msg": {
            "info": "",
            "code": 0,
            "success": true
        },
        "data": {
            "total": 1,
            "list": [
                {
                    "id": 1,
                    "userId": "afi",
                    "accountType": 4,
                    "bizMoney": 10,
                    "bizStatus": 1,
                    "bizSn": "111",
                    "createTime": 1507793532000,
                    "title": "3月份充值"
                }
            ]
        }
    }
    

返回关键字段解释
    
字段|类型|描述
---|---|---
bizMoney|Integer|充值金额 单位分
createTime|Long|充值时间
title|String|充值标题
accountType|Integer|4 表示充值
bizSn|String|充值编号

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
     http://localhost:8080/my/rechargeHistory \
     -H 'cache-control: no-cache' \
     -H 'content-type: application/json' \
     -H 'postman-token: 81f75bca-336c-ba27-8ffc-14e5e411ae50' \
     -H 'token: token' \
     -H 'traceinfo: applicationCode=aa;deviceUuid=deviceUuid;'

````





## 4.消费记录

###  地址

    my/consumeHistory


###  提交方式
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式


### 参数

字段|是否必填|类型|描述
---|---|---|---
limit|否|Integer|默认一页50
page|否|Integer|页码 从1开始
    

返回信息:成功

    {
        "msg": {
            "info": "",
            "code": 0,
            "success": true
        },
        "data": {
            "total": 2,
            "list": [
                {
                    "id": 2,
                    "orderSn": "1710118HV517A9234345",
                    "title": "补单由供餐商随即分配菜品",
                    "provinceCode": "pcode",
                    "cityCode": "name",
                    "areaCode": "mail",
                    "address": "add",
                    "orderSource": 1,
                    "orderStatus": 10,
                    "evaStatus": 200,
                    "accountsStatus": 0,
                    "payStatus": 0,
                    "orderType": 31,
                    "businessUid": "123",
                    "userUid": "afi",
                    "userTel": "123",
                    "userName": "name",
                    "payTime": null,
                    "sendTime": null,
                    "createTime": 1507736627000,
                    "lastModifyDate": 1507736627000,
                    "mark": null,
                    "sumMoney": 2,
                    "couponMoney": 0,
                    "discountMoney": 0,
                    "redMoney": 0,
                    "needPay": 2,
                    "payMoney": null,
                    "payBalance": 1,
                    "carryMoney": 0,
                    "list": [
                        {
                            "id": 3,
                            "orderSn": "1710118HV517A9234345",
                            "productName": "name1",
                            "productType": 1,
                            "productCode": 1,
                            "productNum": 2,
                            "productPrice": 1
                        }
                    ]
                },
                {
                    "id": 3,
                    "orderSn": "171011TOG61719234554",
                    "title": "补单由供餐商随即分配菜品",
                    "provinceCode": "pcode",
                    "cityCode": "name",
                    "areaCode": "mail",
                    "address": "add",
                    "orderSource": 1,
                    "orderStatus": 10,
                    "evaStatus": 200,
                    "accountsStatus": 0,
                    "payStatus": 0,
                    "orderType": 31,
                    "businessUid": "123",
                    "userUid": "afi",
                    "userTel": "123",
                    "userName": "name",
                    "payTime": null,
                    "sendTime": null,
                    "createTime": 1507736756000,
                    "lastModifyDate": 1507736756000,
                    "mark": null,
                    "sumMoney": 1,
                    "couponMoney": 0,
                    "discountMoney": 0,
                    "redMoney": 0,
                    "needPay": 1,
                    "payMoney": null,
                    "payBalance": 1,
                    "carryMoney": 0,
                    "list": [
                        {
                            "id": 4,
                            "orderSn": "171011TOG61719234554",
                            "productName": "补餐",
                            "productType": 3,
                            "productCode": 0,
                            "productNum": 1,
                            "productPrice": 1
                        }
                    ]
                }
            ]
        }
    }
    

返回信息解析
//TODO 订单状态稍后提供,会对列表字段进行大规模的缩减,需要确定需要哪些值,不需要的不会再返回

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
   http://localhost:8080/my/consumeHistory \
   -H 'cache-control: no-cache' \
   -H 'content-type: application/json' \
   -H 'postman-token: 754438d5-454a-3e9f-5fa4-0501936ec62b' \
   -H 'token: token' \
   -H 'traceinfo: applicationCode=aa;deviceUuid=deviceUuid;'

````





## 5.我的二维码

###  地址

    my/card


###  提交方式
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式


### 参数

不要参数
    

返回信息:成功

    {
        "msg": {
            "info": "",
            "code": 0,
            "success": true
        },
        "data": 
            {
                "aaaa"
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


demo:


````
  curl -X POST \
  http://localhost:8080/my/card \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: ad7965d1-c4db-5b11-ed8e-13578afc7210' \
  -H 'token: token' \
  -H 'traceinfo: applicationCode=aa;deviceUuid=deviceUuid;'

````
