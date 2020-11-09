支付相关文档
-----------------------------------  

## 1. 微信支付


###  地址

    create/orderPay


###  提交方式
提交方式|post
Content-Type|application/json
参数|放在body流中,依照json格式

### 参数

字段|是否必填|类型|描述
---|---|---|---
orderSn|是|String|订单编号
payType|是|Integer|支付类型 1:微信支付
productCode|是|Integer| 101


返回信息:成功

   {
       "msg": {
           "info": "",
           "code": 0,
           "success": true
       },
       "data": {}
   }


返回信息解析

返回信息:失败

    {
    msg: {
        info: "参数异常",
        code: 1,
        success: false
    },
    data: null
    }


