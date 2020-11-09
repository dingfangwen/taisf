退款相关文档
-----------------------------------  

## 1. 退款


###  地址

    refund/create


###  提交方式
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式

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
       "data": "TK180102Q4L28OA5172527"
   }


返回信息解析
1710118HV517A9234345 //退款单编号

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
    http://localhost:8080/refund/create \
    -H 'cache-control: no-cache' \
    -H 'content-type: application/json' \
    -H 'postman-token: 0c56a128-9d68-88da-c4c8-d4cfd04a7921' \
    -H 'token: token' \
    -H 'traceinfo: applicationCode=user;deviceUuid=deviceUuid;versionCode=versionCode;source=1;' \
    -d '{"orderSn":"123"}'

````
