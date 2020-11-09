购物车相关文档
-----------------------------------  

## 1.添加购物车

###  地址

    cart/addCart


###  提交方式
      提交方式|post
      Content-Type|application/json
      参数|放在body流中,依照json格式
      
      
      ### 参数

字段|是否必填|类型|描述
---|---|---|---
businessUid|是|String|供应商code
productCode|是|Integer|商品code
supplierProductType|是|Integer|商品分类 注意是 supplierProductType 不是productType
productNum|否|Integer|商品数量,默认1
    

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
          http://localhost:8080/cart/addCart \
          -H 'cache-control: no-cache' \
          -H 'content-type: application/json' \
          -H 'postman-token: 21f0fca3-c613-cd35-c94f-b513af9a91df' \
          -H 'token: token' \
          -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;versionCode=versionCode;source=1;' \
          -d '{"businessUid":"123","productCode":1,"supplierProductType":1}'

````


## 2.删除

###  地址

    cart/delCart


###  提交方式
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式


### 参数

字段|是否必填|类型|描述
---|---|---|---
businessUid|是|String|供应商code
productCode|是|Integer|商品code
supplierProductType|是|Integer|商品分类
    

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
          http://localhost:8080/cart/delCart \
          -H 'cache-control: no-cache' \
          -H 'content-type: application/json' \
          -H 'postman-token: 21f0fca3-c613-cd35-c94f-b513af9a91df' \
          -H 'token: token' \
          -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;versionCode=versionCode;source=1;' \
          -d '{"businessUid":"123","productCode":1,"supplierProductType":1}'

````


            
## 3.清空购物车

###  地址

    cart/cartClean


###  提交方式
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式


### 参数

字段|是否必填|类型|描述
---|---|---|---
businessUid|是|String|供应商code
    

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
          http://localhost:8080/cart/delCart \
          -H 'cache-control: no-cache' \
          -H 'content-type: application/json' \
          -H 'postman-token: 21f0fca3-c613-cd35-c94f-b513af9a91df' \
          -H 'token: token' \
          -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;versionCode=versionCode;source=1;' \
          -d '{"businessUid":"123"}'

````



           
## 4.查看购物车

###  地址

    cart/info


###  提交方式
提交方式|GET
参数|放在url


### 参数

字段|是否必填|类型|描述
---|---|---|---
businessUid|是|String|供应商code
    

返回信息:成功

   {
       "msg": {
           "info": "",
           "code": 0,
           "success": true
       },
       "data": {
           "businessUid": "123",
           "userUid": "afi",
           "price": 2,
           "list": [
               {
                   "id": null,
                   "businessUid": "123",
                   "userUid": "afi",
                   "supplierProductType": 1,
                   "productCode": 1,
                   "productNum": 2,
                   "createTime": null,
                   "productName": "name1",
                   "productPrice": 1
               }
           ]
       }
   }
   
返回关键字段解释
    
字段|类型|描述
---|---|---
businessUid|String|商家code
userUid|String|用户uid
supplierProductType|Integer|菜单类型
productCode|Integer|菜单code
productName|String|菜案名称
productNum|Integer|菜数量
productPrice|Integer|菜价格 分



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
       'http://localhost:8080/cart/info?businessUid=123' \
       -H 'cache-control: no-cache' \
       -H 'content-type: application/json' \
       -H 'postman-token: ff341a85-7963-d249-ca9c-731997d29890' \
       -H 'token: token' \
       -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;versionCode=versionCode;source=1;' \
       -d '{"businessUid":"123","productCode":1,"supplierProductType":1}'

````