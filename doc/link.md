订单相关文档
-----------------------------------  

## 1.根据订单号获取餐柜信息

###  地址

    buffet/info


###  提交方式
提交方式|get


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
            "id": 7948,
            "deviceId": "221",
            "fid": "111",
            "supplierCode": "jipin",
            "cellNum": 21,
            "address": "221",
            "provinceCode": "330000",
            "cityCode": "330600",
            "countyCode": "330602",
            "status": 1,
            "linkStatus": null,
            "linkStatusDes": null,
            "createTime": 1554877873000,
            "updateTime": 1555914780000,
            "provinceName": "浙江省",
            "cityName": "绍兴市",
            "countyName": "越城区",
            "buffetFid": "111",
            "cellSn": "111",
            "orderSn": null,
            "orderStatus": 7
          }
    }

返回关键字段解释
    
字段|类型|描述
---|---|---
buffetFid|String|餐柜编号
cellSn|String|格子编号
address|String|详细地址,不包括省市县需要拼接省市县
orderStatus|int|订单状态,来确定是否显示取餐按钮

返回信息:失败

    {
    msg: {
        info: "参数异常",
        code: 1,
        success: false
    },
    data: null
    }







## 2.根据fid获取设备信息

###  地址

    buffet/detail


###  提交方式
提交方式|get


### 参数

字段|是否必填|类型|描述
---|---|---|---
buffetFid|是|String|设备编号
    
返回信息:成功

    {
        "msg": {
            "info": "",
            "code": 0,
            "success": true
        },
        "data": {
            "id": 7948,
            "deviceId": "221",
            "fid": "111",
            "supplierCode": "jipin",
            "cellNum": 21,
            "address": "221",
            "provinceCode": "330000",
            "cityCode": "330600",
            "countyCode": "330602",
            "status": 1,
            "linkStatus": null,
            "linkStatusDes": null,
            "createTime": 1554877873000,
            "updateTime": 1555914780000,
            "provinceName": "浙江省",
            "cityName": "绍兴市",
            "countyName": "越城区"
          }
    }

返回关键字段解释
    
字段|类型|描述
---|---|---
status|int| 设备状态  0：未知状态，1：在线，2：已离线，3：未激活，4：已禁用

返回信息:失败

    {
    msg: {
        info: "参数异常",
        code: 1,
        success: false
    },
    data: null
    }
