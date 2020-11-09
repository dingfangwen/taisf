
#### 首先
    
    登录相关的和用户端用相同的接口 这里就不做描述,
    后台根据code来判断.骑士的 ApplicationCode : knight




## 1. 历史记录


###  地址

    knight/history


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
      http://localhost:8080/knight/history \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: d1615d61-a789-b0f8-ac36-6e1cca1f1903' \
      -H 'token: token' \
      -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;versionCode=versionCode;source=1;' \
      -d '{"page":1,"limit":1}'

````




## 2.待签收列表


###  地址

    knight/list


###  提交方式
提交方式|get

参数|放在url

### 参数

字段|是否必填|类型|描述
---|---|---|---
userUid|是|String|用户uid

返回信息:成功

   {
       "msg": {
           "info": "",
           "code": 0,
           "success": true
       },
       "data":  [
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
      curl -X get \
      http://localhost:8080/knight/list?userUid=123\
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: d1615d61-a789-b0f8-ac36-6e1cca1f1903' \
      -H 'token: token' \
      -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;versionCode=versionCode;source=1;' \
    

````




## 3. 完成订单


###  地址

    knight/finishOrder


###  提交方式
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式

### 参数

字段|是否必填|类型|描述
---|---|---|---
orderSn|是|String|订单号


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


````
      curl -X POST \
      http://localhost:8080/knight/history \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: d1615d61-a789-b0f8-ac36-6e1cca1f1903' \
      -H 'token: token' \
      -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;versionCode=versionCode;source=1;' \
      -d '{"page":1,"limit":1}'

````







## 4. 获取收款码 


###  地址

    knight/payCode


###  提交方式
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


````
      curl -X POST \
      http://localhost:8080/knight/payCode \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: d1615d61-a789-b0f8-ac36-6e1cca1f1903' \
      -H 'token: token' \
      -H 'traceinfo: applicationCode=knight;deviceUuid=deviceUuid;versionCode=versionCode;source=1;' \

````









## 5. 骑手放餐 


###  地址

    knight/buffetIn


###  提交方式
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式


### 参数

字段|是否必填|类型|描述
---|---|---|---
orderSn|是|String|订单号
buffetFid|是|String|设备编号
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



