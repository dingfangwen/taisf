消息中心相关
-----------------------------------  

## 1.是否有新消息通知

###  地址

    messageInfo/boxRemind


###  请求方式
      提交方式|get
      Content-Type|application/json
      参数|放在body流中,依照json格式
      
      
      ### 参数

字段|是否必填|类型|描述
---|---|---|---
applicationCode  应用 
platform  平台 
subject 主题
lastTime 上次数据获取时间,如果不传 默认7天
    

返回信息:成功

    {
        "msg": {
            "info": "",
            "code": 0,
            "success": true
        },
        "data": {
        "num": "新消息数量,如果为0则没有新消息",
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

## 2.增量拉取消息列表

###  地址

    messageInfo/list


###  请求方式
      提交方式|get
      Content-Type|application/json
      参数|放在body流中,依照json格式
      
      
      ### 参数

字段|是否必填|类型|描述
---|---|---|---
applicationCode  应用 
platform  平台 
subject 主题
lastTime 上次数据获取时间,如果不传 默认7天


返回信息:成功

    {
        "msg": {
            "info": "",
            "code": 0,
            "success": true
        },
        "data": {
        systemTime: "最后一次请的服务器系统时间"
            list:{
                "title": "标题",
                "content": "内容",
                "updateTime": "时间戳"
                
            }
        
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








