订单相关文档
-----------------------------------  

## 1.提交普通订单

###  地址

    order/initOrder


###  提交方式
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式


### 参数

字段|是否必填|类型|描述
---|---|---|---
businessUid|是|String|供应商code
orderType|是|Integer|订单类型 由首页初始化接口返回
    

返回信息:成功

    {
        "msg": {
            "info": "",
            "code": 0,
            "success": true
        },
        "data": {
            "drawBalance": 0,
            "user": {
                "userUid": "afi",
                "userName": "name",
                "enterpriseCode": "code",
                "enterpriseName": "11",
                "userPhone": "123",
                "userRole": 1,
                "productSource": 1,
                "userCode":"12321"
            },
            "orderMoney": {
                "orderSn": "",
                "sumMoney": 2,
                "couponMoney": 0,
                "discountMoney": 0,
                "redMoney": 0,
                "needPay": 2,
                "payMoney": null,
                "payBalance": 0,
                "carryMoney": 0
            },
            "list": [
                {
                    "id": null,
                    "orderSn": "",
                    "productName": "name1",
                    "productType": 1,
                    "productCode": 1,
                    "productNum": 2,
                    "productPrice": 1
                }
            ]
        }
    }

返回关键字段解释
    
字段|类型|描述
---|---|---
user|对象|用户信息
orderMoney|对象|订单的金额信息
list|list|订单商品
drawBalance|Integer|余额, 当前余额的和orderMoney的needPay做对比判断是否需要微信支付

关键字段描述
````
    //用户信息
    "user": {
        "userUid": "afi", //uid
        "userName": "name",  //名称
        "enterpriseCode": "code", //企业code
        "enterpriseName": "11", //企业名称
        "userPhone": "123", //电话
        "userRole": 1, //角色 1 员工 2老板餐
        "productSource": 1 //无用
        "userCode":"12321" 用户编号
    },
    
    //订单金额信息
    "orderMoney": {
        "orderSn": "", //订单编号
        "sumMoney": 2, //总金额
        "couponMoney": 0, //优惠券金额 
        "discountMoney": 0, //折扣金额
        "redMoney": 0, //红包支付金额
        "needPay": 2, //应付现金
        "payBalance": 0, //余额支付金额
        "carryMoney": 0 //运费
    },
    
    //商品列表
    "list": [
        {
            "id": null,
            "orderSn": "",
            "productName": "name1", //名称
            "productType": 1, //类型
            "productCode": 1, //商品id
            "productNum": 2,  //商品数量
            "productPrice": 1 //单价 分
        }
    ]
    
````
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
            http://localhost:8080/order/initOrder \
            -H 'cache-control: no-cache' \
            -H 'content-type: application/json' \
            -H 'postman-token: 200d8959-c767-0233-3307-5a03f854aeb2' \
            -H 'token: token' \
            -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;versionCode=versionCode;source=1;' \
            -d '{"businessUid":"123","orderType":30}'

````





## 2.提交补单

###  地址

    order/initExtOrder


###  提交方式
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式


### 参数

字段|是否必填|类型|描述
---|---|---|---
businessUid|是|String|供应商code
orderType|是|Integer|订单类型 由首页初始化接口返回
    

返回信息:成功

    {
        "msg": {
            "info": "",
            "code": 0,
            "success": true
        },
        "data": {
            "drawBalance": 0,
            "user": {
                "userUid": "afi",
                "userName": "name",
                "enterpriseCode": "code",
                "enterpriseName": "11",
                "userPhone": "123",
                "userRole": 1,
                "productSource": 1
            },
            "orderMoney": {
                "orderSn": "",
                "sumMoney": 2,
                "couponMoney": 0,
                "discountMoney": 0,
                "redMoney": 0,
                "needPay": 2,
                "payMoney": null,
                "payBalance": 0,
                "carryMoney": 0
            },
            "list": [
                {
                    "id": null,
                    "orderSn": "",
                    "productName": "name1",
                    "productType": 1,
                    "productCode": 1,
                    "productNum": 2,
                    "productPrice": 1
                }
            ]
        }
    }

返回关键字段解释
    
字段|类型|描述
---|---|---
user|对象|用户信息
orderMoney|对象|订单的金额信息
list|list|订单商品
drawBalance|Integer|余额, 当前余额的和orderMoney的needPay做对比判断是否需要微信支付

关键字段描述
````
    //用户信息
    "user": {
        "userUid": "afi", //uid
        "userName": "name",  //名称
        "enterpriseCode": "code", //企业code
        "enterpriseName": "11", //企业名称
        "userPhone": "123", //电话
        "userRole": 1, //角色 1 员工 2老板餐
        "productSource": 1 //无用
    },
    
    //订单金额信息
    "orderMoney": {
        "orderSn": "", //订单编号
        "sumMoney": 2, //总金额
        "couponMoney": 0, //优惠券金额 
        "discountMoney": 0, //折扣金额
        "redMoney": 0, //红包支付金额
        "needPay": 2, //应付现金
        "payBalance": 0, //余额支付金额
        "carryMoney": 0 //运费
    },
    
    //商品列表
    "list": [
        {
            "id": null,
            "orderSn": "",
            "productName": "name1", //名称
            "productType": 1, //类型
            "productCode": 1, //商品id
            "productNum": 2,  //商品数量
            "productPrice": 1 //单价 分
        }
    ]
    
````
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
             http://localhost:8080/order/initExtOrder \
             -H 'cache-control: no-cache' \
             -H 'content-type: application/json' \
             -H 'postman-token: 5c64da5f-af41-e3ea-bd17-b2bf53b12f03' \
             -H 'token: token' \
             -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;versionCode=versionCode;source=1;' \
             -d '{"businessUid":"123","orderType":31}'

````




## 3. 普通下单


###  地址

    order/createOrder


###  提交方式
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式

### 参数

字段|是否必填|类型|描述
---|---|---|---
businessUid|是|String|供应商code
orderType|是|Integer|订单类型 由首页初始化接口返回
addressFid|是|String|企业的地址fid
pwd|否|String|只有初始化订单的情况才需要输入密码,不接受明文,需要时md5加密之后
    


返回信息:成功

   {
       "msg": {
           "info": "",
           "code": 0,
           "success": true
       },
       "data": "1710118HV517A9234345"
   }


返回信息解析
1710118HV517A9234345 //订单编号

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
            http://localhost:8080/order/createOrder \
            -H 'cache-control: no-cache' \
            -H 'content-type: application/json' \
            -H 'postman-token: 0c56a128-9d68-88da-c4c8-d4cfd04a7921' \
            -H 'token: token' \
            -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;versionCode=versionCode;source=1;' \
            -d '{"businessUid":"123","orderType":31,"addressFid":"111"}'

````



## 4. 补单


###  地址

    order/createExtOrder


###  提交方式
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式

### 参数

字段|是否必填|类型|描述
---|---|---|---
businessUid|是|String|供应商code
orderType|是|Integer|订单类型 由首页初始化接口返回
addressFid|是|String|企业的地址fid
pwd|否|String|只有初始化订单的情况才需要输入密码,不接受明文,需要时md5加密之后
    


返回信息:成功

   {
       "msg": {
           "info": "",
           "code": 0,
           "success": true
       },
       "data": "1710118HV517A9234345"
   }


返回信息解析
1710118HV517A9234345 //订单编号

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
    http://localhost:8080/order/createExtOrder \
    -H 'cache-control: no-cache' \
    -H 'content-type: application/json' \
    -H 'postman-token: 0c56a128-9d68-88da-c4c8-d4cfd04a7921' \
    -H 'token: token' \
    -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;versionCode=versionCode;source=1;' \
    -d '{"businessUid":"123","orderType":31,"addressFid":"111"}'

````




## 5. 订单列表


###  地址

    order/listPage


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
                   "payBalance": 0,
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
      http://localhost:8080/order/listPage \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: d1615d61-a789-b0f8-ac36-6e1cca1f1903' \
      -H 'token: token' \
      -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;versionCode=versionCode;source=1;' \
      -d '{"page":1,"limit":1}'

````




## 6. 订单详情


###  地址

    order/info


###  提交方式
提交方式|GET
参数|放在url

### 参数

字段|是否必填|类型|描述
---|---|---|---
orderSn|是|String|订单编号


返回信息:成功

   {
       "msg": {
           "info": "",
           "code": 0,
           "success": true
       },
       "data": {
           "orderEntity": {
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
               "mark": null
           },
           "orderMoneyEntity": {
               "orderSn": "1710118HV517A9234345",
               "sumMoney": 2,
               "couponMoney": 0,
               "discountMoney": 0,
               "redMoney": 0,
               "needPay": 2,
               "payMoney": null,
               "payBalance": 0,
               "carryMoney": 0
           },
           "orderPayEntity": null,
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
       }
   }


返回信息解析
//TODO 订单状态稍后提供

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
    'http://localhost:8080/order/info?orderSn=1710118HV517A9234345' \
    -H 'cache-control: no-cache' \
    -H 'content-type: application/json' \
    -H 'postman-token: a4dd2459-89dd-1789-ad83-c7b68ac07dd2' \
    -H 'token: token' \
    -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;versionCode=versionCode;source=1;' \
    -d '{"page":1,"limit":1}'

````


## 7.企业订单统计


###  地址

    order/getEnterpriseOrderStats


###  提交方式
提交方式|get

### 参数

字段|是否必填|类型|描述
---|---|---|---
userName|否|String|用户姓名
userTel|否|String|手机号
orderStatus|否|Integer|状态 1 退款中 2 配送中
openTime|否|String|下单开始时间
tillTime|否|String|下单结束时间
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
                   "payBalance": 0,
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
               }
           ]
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





## 8. 面对面收款


###  地址

    order/faceOrder


###  提交方式
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式

### 参数

字段|是否必填|类型|描述
---|---|---|---
businessUid|是|String|供应商code
price|是|Integer|金额,单位分
pwd|否|String|只有初始化订单的情况才需要输入密码,不接受明文,需要时md5加密之后
    


返回信息:成功

   {
       "msg": {
           "info": "",
           "code": 0,
           "success": true
       },
       "data": 调整了
   }


返回信息解析
1710118HV517A9234345 //订单编号

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
            http://localhost:8080/order/faceOrder \
            -H 'cache-control: no-cache' \
            -H 'content-type: application/json' \
            -H 'postman-token: 0c56a128-9d68-88da-c4c8-d4cfd04a7921' \
            -H 'token: token' \
            -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;versionCode=versionCode;source=1;' \
            -d '{"businessUid":"123","orderType":31,"addressFid":"111"}'

````




## 9. 患者取餐 


###  地址

    order/buffetOut


###  提交方式
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式


### 参数

字段|是否必填|类型|描述
---|---|---|---
orderSn|是|String|订单号
opName|是|String|操作人姓名

无


返回信息:成功

   {
       "msg": {
           "info": "",
           "code": 0,
           "success": true
       },
       "data": null
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



