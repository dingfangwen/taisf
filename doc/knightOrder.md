
#### 首先
    
    登录相关的和用户端用相同的接口 这里就不做描述,
    后台根据code来判断.骑士的 ApplicationCode : knight




## 1. 扫码获取用户信息


###  地址

    knightOrder/userInfo?userUid=xxx


###  提交方式
提交方式|get


### 参数

字段|是否必填|类型|描述
---|---|---|---
userUid|是|String|用户uid

返回信息:成功

```

{
    "msg": {
        "info": "",
        "code": 0,
        "success": true
    },
    "data": {
        "userInfo": {
            "userName": "李四3",
            "userPic": "",
            "userCard": "",
            "enterpriseCode": "qpg001",
            "enterpriseName": "青苹果餐厅",
            "supplierCode": "jipin",
            "supplierName": "上海极品餐饮管理有限公司",
            "userPhone": "15910000003",
            "isAdmin": 0,
            "isPwd": 0,
            "isAccount": 0,
            "userType": 1,
            "userRole": 1,
            "productSource": 3,
            "payCode": ""
        },
        "drawBalance": 0
    }
}
```

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
        'http://localhost:8080/knightOrder/userInfo?userUid=2c91cb366eb1787f016eb19b50730220' \
        -H 'cache-control: no-cache' \
        -H 'content-type: application/json' \
        -H 'postman-token: 83f015c0-294f-6067-8b82-a073095b650e' \
        -H 'token: ff8080816ec54a2b016ec54a2bf60000' \
        -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;'

````





## 2. 获取扫码收款的信息


###  地址

    knightOrder/knightOrderInfo?userUid=xxx


###  提交方式
提交方式|get


### 参数

字段|是否必填|类型|描述
---|---|---|---
userUid|是|String|用户uid

返回信息:成功

```

{
    "msg": {
        "info": "",
        "code": 30001,
        "success": false
    },
    "data": {}
}



{
    "msg": {
        "info": "",
        "code": 0,
        "success": true
    },
    "data": {
        //如果有,直接调用支付/没有直接调用下单
        "orderSn": null,
        "payTime": 1575268527402,
        "supplierName": "上海极品餐饮管理有限公司",
        "supplierCode": "jipin",
        "price": 1,
        "orderStatus": 1, //1 未付款 
        "userUid": "2c91cb366eb1787f016eb19b50730220",
        "street": "地址",
        "userName": "姓名",
        "userPhone": "电话"

    }
}

```

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
        'http://localhost:8080/knightOrder/userInfo?userUid=2c91cb366eb1787f016eb19b50730220' \
        -H 'cache-control: no-cache' \
        -H 'content-type: application/json' \
        -H 'postman-token: 83f015c0-294f-6067-8b82-a073095b650e' \
        -H 'token: ff8080816ec54a2b016ec54a2bf60000' \
        -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;'

````




## 3. 扫码枪/创建指定金额的订单


###  地址

    knightOrder/createKnightOrder


###  提交方式
提交方式|post
application/json


### 参数

字段|是否必填|类型|描述
---|---|---|---
userUid|是|String|用户uid
price|是|int|金额(分)

返回信息:成功

```
{
    "msg": {
        "info": "",
        "code": 0,
        "success": true
    },
    "data": {
       //如果有,直接调用支付/没有直接调用下单
       "orderSn": null,
       "payTime": 1575268527402,
       "supplierName": "上海极品餐饮管理有限公司",
       "supplierCode": "jipin",
       "price": 1,
       "orderStatus": 1, //1 未付款 
       "userUid": "2c91cb366eb1787f016eb19b50730220",
       "street": "地址",
       "userName": "姓名",
       "userPhone": "电话"
    }
}
}

```

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
      http://localhost:8080/knightOrder/createKnightOrder \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: ef3ff41d-581f-359c-e538-f9645ccf8dfe' \
      -H 'token: ff8080816ec54a2b016ec54a2bf60000' \
      -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;' \
      -d '{
    	
    	"userUid":"2c91cb366eb1787f016eb19b50730220",
    	"price":1
    }'
````



