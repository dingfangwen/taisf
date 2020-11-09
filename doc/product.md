菜单相关文档
-----------------------------------  

## 1.商品分类

###  地址

    supplier/classify


###  提交方式
类型|描述
---|---
提交方式|get
参数|放在url


### 参数
    
supplierCode:企业code

返回信息:成功

    {
        "msg": {
            "info": "",
            "code": 0,
            "success": true
        },
        "data": [
            {
                "productClassify": "100",
                "productClassifyName": "推荐"
            },
            {
                "productClassify": "1",
                "productClassifyName": "大荤"
            },
            {
                "productClassify": "2",
                "productClassifyName": "小荤"
            },
            {
                "productClassify": "3",
                "productClassifyName": "素"
            },
            {
                "productClassify": "4",
                "productClassifyName": "汤"
            },
            {
                "productClassify": "5",
                "productClassifyName": "饮品"
            },
            {
                "productClassify": "6",
                "productClassifyName": "主食"
            },
            {
                "productClassify": "7",
                "productClassifyName": "水果"
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
    
返回关键字段解释
    
字段|类型|描述
---|---|---
productClassify|Integer|类型code,又有的菜单的分类都需要传这个参数
productClassifyName|String|展示名称

demo:


````
        curl -X GET \
        'http://localhost:8080/supplier/classify?supplierCode=code' \
        -H 'cache-control: no-cache' \
        -H 'postman-token: 106c9cc2-e98a-d445-a5e9-b9e05a569564'

````

            
## 2.获取商品分类

###  地址

    supplier/productList


###  提交方式
类型|描述
---|---
提交方式|GET
参数|放在url中


### 参数信息
字段|类型|描述
---|---|---
supplierCode|String|供应商code
productClassify|Integer|上一个接口返回的菜单分类

返回信息:成功
{
    "msg": {
        "info": "",
        "code": 0,
        "success": true
    },
    "data": []
}


返回关键字段解释
    
字段|类型|描述
---|---|---
productName|String|商品名称
productClassify|Integer|商品分类
productType|Integer|商品类型
productSource|Integer|商品属性
productDes|String|描述信息
productPic|String|图片信息
priceSale|Integer|销售价 单位:分


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
        curl -X GET \
        'http://localhost:8080/supplier/productList?supplierCode=code&productClassify=100' \
        -H 'cache-control: no-cache' \
        -H 'postman-token: 5480b730-adb5-a831-d7c3-1fe60aebef8d'

````



## 3.商品分类二级商品列表

###  地址

    supplier/classifyProduct


###  提交方式
类型|描述
---|---
提交方式|get
参数|放在url


### 参数
    
supplierCode:企业code

返回信息:成功

    {
        "msg": {
            "info": "",
            "code": 0,
            "success": true
        },
        "data": [
            {
                "productClassify": "100",
                "productClassifyName": "推荐",
                "list": [
                    {
                        "id": 1,
                        "productName": "套餐",
                        "productClassify": null,
                        "productType": null,
                        "productSource": null,
                        "productDes": null,
                        "productPic": "pic",
                        "priceSale": 1,
                        "createTime": null,
                        "updateTime": null,
                        "isDel": null,
                        "supplierProductType": 2,
                        "supplierProductDes": null
                    }
                ]
            },
            {
                "productClassify": "1",
                "productClassifyName": "大荤",
                "list": [
                    {
                        "id": 1,
                        "productName": "name1",
                        "productClassify": 1,
                        "productType": 1,
                        "productSource": 1,
                        "productDes": "des",
                        "productPic": null,
                        "priceSale": 1,
                        "createTime": 1505312802000,
                        "updateTime": 1507789291000,
                        "isDel": 0,
                        "supplierProductType": 1,
                        "supplierProductDes": null
                    }
                ]
            },
            {
                "productClassify": "2",
                "productClassifyName": "小荤",
                "list": [
                    {
                        "id": 2,
                        "productName": "name2",
                        "productClassify": 2,
                        "productType": 2,
                        "productSource": 1,
                        "productDes": "des",
                        "productPic": null,
                        "priceSale": 1,
                        "createTime": 1505312802000,
                        "updateTime": 1507789775000,
                        "isDel": 0,
                        "supplierProductType": 1,
                        "supplierProductDes": null
                    },
                    {
                        "id": 3,
                        "productName": "name3",
                        "productClassify": 2,
                        "productType": 3,
                        "productSource": 1,
                        "productDes": "des",
                        "productPic": null,
                        "priceSale": 1,
                        "createTime": 1505312802000,
                        "updateTime": 1507789779000,
                        "isDel": 0,
                        "supplierProductType": 1,
                        "supplierProductDes": null
                    }
                ]
            },
            {
                "productClassify": "3",
                "productClassifyName": "素",
                "list": [
                    {
                        "id": 4,
                        "productName": "name4",
                        "productClassify": 3,
                        "productType": 3,
                        "productSource": 1,
                        "productDes": "des",
                        "productPic": null,
                        "priceSale": 1,
                        "createTime": 1505312802000,
                        "updateTime": 1507789784000,
                        "isDel": 0,
                        "supplierProductType": 1,
                        "supplierProductDes": null
                    },
                    {
                        "id": 5,
                        "productName": "name5",
                        "productClassify": 3,
                        "productType": 3,
                        "productSource": 1,
                        "productDes": "des",
                        "productPic": null,
                        "priceSale": 1,
                        "createTime": 1505312802000,
                        "updateTime": 1507789788000,
                        "isDel": 0,
                        "supplierProductType": 1,
                        "supplierProductDes": null
                    },
                    {
                        "id": 6,
                        "productName": "name6",
                        "productClassify": 3,
                        "productType": 3,
                        "productSource": 3,
                        "productDes": "des",
                        "productPic": null,
                        "priceSale": 1,
                        "createTime": 1505312802000,
                        "updateTime": 1507790705000,
                        "isDel": 0,
                        "supplierProductType": 1,
                        "supplierProductDes": null
                    },
                    {
                        "id": 7,
                        "productName": "name7",
                        "productClassify": 3,
                        "productType": 3,
                        "productSource": 1,
                        "productDes": "des",
                        "productPic": null,
                        "priceSale": 1,
                        "createTime": 1505312802000,
                        "updateTime": 1507789796000,
                        "isDel": 0,
                        "supplierProductType": 1,
                        "supplierProductDes": null
                    }
                ]
            },
            {
                "productClassify": "4",
                "productClassifyName": "汤",
                "list": []
            },
            {
                "productClassify": "5",
                "productClassifyName": "饮品",
                "list": []
            },
            {
                "productClassify": "6",
                "productClassifyName": "主食",
                "list": []
            },
            {
                "productClassify": "7",
                "productClassifyName": "水果",
                "list": []
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
    
返回关键字段解释
    
字段|类型|描述
---|---|---
productClassify|Integer|类型code,又有的菜单的分类都需要传这个参数
productClassifyName|String|展示名称
list|list|商品列表

demo:


````
        curl -X GET \
        'http://localhost:8080/supplier/classify?supplierCode=code' \
        -H 'cache-control: no-cache' \
        -H 'postman-token: 106c9cc2-e98a-d445-a5e9-b9e05a569564'

````





## 3.商品分类二级商品列表和每天的订饭时间配置

###  地址

    supplier/classifyProductWeek


###  提交方式
类型|描述
---|---
提交方式|get
参数|放在url


### 参数
    
字段|类型|描述
---|---|---
supplierCode|String|商企业code品名称
week|Integer|周几


返回信息:成功

    {
      "code": 0,
      "msg": "",
      "data": {
        "productClassifyList": [
          {
            "productClassify": "1",
            "productClassifyName": "大荤",
            "list": [
              {
                "id": 30,
                "productName": "蒜泥白肉",
                "productClassify": 1,
                "productType": 1,
                "productSource": 3,
                "productDes": "微辣",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-25/2c91340c6128075301612be942140021",
                "priceSale": 900,
                "priceMarket": 1000,
                "createTime": 1511428484000,
                "updateTime": 1517230672000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 0,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  }
                ]
              },
              {
                "id": 44,
                "productName": "蒜叶蹄髈",
                "productClassify": 1,
                "productType": 1,
                "productSource": 3,
                "productDes": "微辣",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-25/2c91340c6128075301612becc512002f",
                "priceSale": 810,
                "priceMarket": 900,
                "createTime": 1511428456000,
                "updateTime": 1517230594000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 0,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  }
                ]
              },
              {
                "id": 59,
                "productName": "香酥鸭",
                "productClassify": 1,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-25/2c91340c6128075301612bf04364003d",
                "priceSale": 900,
                "priceMarket": 1000,
                "createTime": 1511428425000,
                "updateTime": 1517230717000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 0,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  }
                ]
              },
              {
                "id": 133,
                "productName": "手工蛋饺",
                "productClassify": 1,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-25/2c91340c6128075301612c32183d0084",
                "priceSale": 720,
                "priceMarket": 800,
                "createTime": 1516864939000,
                "updateTime": 1517230642000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 0,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  }
                ]
              },
              {
                "id": 135,
                "productName": "口水鸡",
                "productClassify": 1,
                "productType": 1,
                "productSource": 3,
                "productDes": "中辣",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-25/2c91340c6128075301612c338d390086",
                "priceSale": 720,
                "priceMarket": 800,
                "createTime": 1516865040000,
                "updateTime": 1517230748000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 0,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  }
                ]
              },
              {
                "id": 239,
                "productName": "清蒸多宝鱼",
                "productClassify": 1,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-29/2c91340c61280753016141e7c54200f2",
                "priceSale": 900,
                "priceMarket": 1000,
                "createTime": 1517229167000,
                "updateTime": 1517229167000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 0,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  }
                ]
              },
              {
                "id": 240,
                "productName": "糟带鱼",
                "productClassify": 1,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-29/2c91340c61280753016141e8579000f3",
                "priceSale": 720,
                "priceMarket": 800,
                "createTime": 1517229204000,
                "updateTime": 1517229204000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 0,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  }
                ]
              },
              {
                "id": 241,
                "productName": "红烧猪手",
                "productClassify": 1,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-29/2c91340c61280753016141e8eea200f4",
                "priceSale": 810,
                "priceMarket": 900,
                "createTime": 1517229243000,
                "updateTime": 1517229242000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 0,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  }
                ]
              },
              {
                "id": 242,
                "productName": "酱爆猪肝",
                "productClassify": 1,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-29/2c91340c61280753016141e9834100f5",
                "priceSale": 720,
                "priceMarket": 800,
                "createTime": 1517229281000,
                "updateTime": 1517229281000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 0,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  }
                ]
              },
              {
                "id": 243,
                "productName": "红烧肉圆",
                "productClassify": 1,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-29/2c91340c61280753016141ea12f000f6",
                "priceSale": 720,
                "priceMarket": 800,
                "createTime": 1517229318000,
                "updateTime": 1517229317000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 0,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  }
                ]
              },
              {
                "id": 244,
                "productName": "香菜鸡胗",
                "productClassify": 1,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-29/2c91340c61280753016141eaf1db00f7",
                "priceSale": 810,
                "priceMarket": 900,
                "createTime": 1517229374000,
                "updateTime": 1517229374000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 0,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  }
                ]
              }
            ]
          },
          {
            "productClassify": "2",
            "productClassifyName": "小荤",
            "list": [
              {
                "id": 79,
                "productName": "韭黄炒蛋",
                "productClassify": 2,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-25/2c91340c6128075301612bf565f90051",
                "priceSale": 450,
                "priceMarket": 500,
                "createTime": 1511428383000,
                "updateTime": 1517138238000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 0,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  }
                ]
              },
              {
                "id": 195,
                "productName": "西兰花培根",
                "productClassify": 2,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-27/2c91340c61280753016135c20a3100c4",
                "priceSale": 450,
                "priceMarket": 500,
                "createTime": 1517025367000,
                "updateTime": 1517025640000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 0,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  }
                ]
              },
              {
                "id": 245,
                "productName": "毛豆香干肉丝",
                "productClassify": 2,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-29/2c91340c61280753016141ec2ebe00f8",
                "priceSale": 450,
                "priceMarket": 500,
                "createTime": 1517229456000,
                "updateTime": 1517229455000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 0,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  }
                ]
              },
              {
                "id": 246,
                "productName": "芹菜茶树菇肉丝",
                "productClassify": 2,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-29/2c91340c61280753016141ecbf0200f9",
                "priceSale": 450,
                "priceMarket": 500,
                "createTime": 1517229493000,
                "updateTime": 1517229493000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 0,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  }
                ]
              },
              {
                "id": 247,
                "productName": "日本豆腐",
                "productClassify": 2,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-29/2c91340c61280753016141ed778300fa",
                "priceSale": 450,
                "priceMarket": 500,
                "createTime": 1517229540000,
                "updateTime": 1517229539000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 0,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  }
                ]
              },
              {
                "id": 248,
                "productName": "笋丝培根炒蛋",
                "productClassify": 2,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-29/2c91340c61280753016141edfc1b00fb",
                "priceSale": 450,
                "priceMarket": 500,
                "createTime": 1517229577000,
                "updateTime": 1517229576000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 0,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  }
                ]
              },
              {
                "id": 249,
                "productName": "虎皮青椒",
                "productClassify": 2,
                "productType": 1,
                "productSource": 3,
                "productDes": "微辣",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-29/2c91340c61280753016141ee9a0f00fc",
                "priceSale": 450,
                "priceMarket": 500,
                "createTime": 1517229614000,
                "updateTime": 1517229614000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 1,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  },
                  {
                    "orderType": 30,
                    "orderTypeName": "晚餐"
                  }
                ]
              },
              {
                "id": 250,
                "productName": "鸡腿菇炒腊肉",
                "productClassify": 2,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-29/2c91340c61280753016141ef0bc000fd",
                "priceSale": 450,
                "priceMarket": 500,
                "createTime": 1517229643000,
                "updateTime": 1517229643000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 1,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  },
                  {
                    "orderType": 30,
                    "orderTypeName": "晚餐"
                  }
                ]
              },
              {
                "id": 251,
                "productName": "荠菜蘑菇肉丝",
                "productClassify": 2,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-29/2c91340c61280753016141ef905000fe",
                "priceSale": 450,
                "priceMarket": 500,
                "createTime": 1517229680000,
                "updateTime": 1517229679000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 1,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  },
                  {
                    "orderType": 30,
                    "orderTypeName": "晚餐"
                  }
                ]
              },
              {
                "id": 252,
                "productName": "香菜小素鸡",
                "productClassify": 2,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-29/2c91340c61280753016141f0161400ff",
                "priceSale": 450,
                "priceMarket": 500,
                "createTime": 1517229712000,
                "updateTime": 1517229711000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 1,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  },
                  {
                    "orderType": 30,
                    "orderTypeName": "晚餐"
                  }
                ]
              },
              {
                "id": 253,
                "productName": "刀豆外婆菜肉丝",
                "productClassify": 2,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-29/2c91340c61280753016141f098a40100",
                "priceSale": 450,
                "priceMarket": 500,
                "createTime": 1517229745000,
                "updateTime": 1517229745000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 1,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  },
                  {
                    "orderType": 30,
                    "orderTypeName": "晚餐"
                  }
                ]
              }
            ]
          },
          {
            "productClassify": "3",
            "productClassifyName": "素",
            "list": [
              {
                "id": 90,
                "productName": "酸辣土豆丝",
                "productClassify": 3,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-25/2c91340c6128075301612bf7f60c005c",
                "priceSale": 180,
                "priceMarket": 200,
                "createTime": 1511428361000,
                "updateTime": 1517230870000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 1,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  },
                  {
                    "orderType": 30,
                    "orderTypeName": "晚餐"
                  }
                ]
              },
              {
                "id": 93,
                "productName": "香菇青菜",
                "productClassify": 3,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-25/2c91340c6128075301612bf8713f005f",
                "priceSale": 180,
                "priceMarket": 200,
                "createTime": 1511428355000,
                "updateTime": 1517230904000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 0,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  }
                ]
              },
              {
                "id": 103,
                "productName": "地三鲜",
                "productClassify": 3,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-25/2c91340c6128075301612bfad0ef0069",
                "priceSale": 270,
                "priceMarket": 300,
                "createTime": 1511428335000,
                "updateTime": 1517230791000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 0,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  }
                ]
              },
              {
                "id": 254,
                "productName": "油焖茄子",
                "productClassify": 3,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-29/2c91340c61280753016141f14beb0101",
                "priceSale": 270,
                "priceMarket": 300,
                "createTime": 1517229791000,
                "updateTime": 1517229790000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 0,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  }
                ]
              },
              {
                "id": 255,
                "productName": "黄瓜拌老油条",
                "productClassify": 3,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-29/2c91340c61280753016141f1cf3b0102",
                "priceSale": 270,
                "priceMarket": 300,
                "createTime": 1517229825000,
                "updateTime": 1517229824000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 0,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  }
                ]
              },
              {
                "id": 256,
                "productName": "西葫芦香干",
                "productClassify": 3,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-29/2c91340c61280753016141f248930103",
                "priceSale": 270,
                "priceMarket": 300,
                "createTime": 1517229857000,
                "updateTime": 1517229856000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 1,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  },
                  {
                    "orderType": 30,
                    "orderTypeName": "晚餐"
                  }
                ]
              },
              {
                "id": 257,
                "productName": "青椒绿豆芽",
                "productClassify": 3,
                "productType": 1,
                "productSource": 3,
                "productDes": "本帮",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-29/2c91340c61280753016141f2ca120104",
                "priceSale": 180,
                "priceMarket": 200,
                "createTime": 1517229889000,
                "updateTime": 1517229888000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 1,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  },
                  {
                    "orderType": 30,
                    "orderTypeName": "晚餐"
                  }
                ]
              },
              {
                "id": 258,
                "productName": "酸辣海带丝",
                "productClassify": 3,
                "productType": 1,
                "productSource": 3,
                "productDes": "酸辣",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-29/2c91340c61280753016141f33d740105",
                "priceSale": 180,
                "priceMarket": 200,
                "createTime": 1517229924000,
                "updateTime": 1517229924000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 1,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  },
                  {
                    "orderType": 30,
                    "orderTypeName": "晚餐"
                  }
                ]
              }
            ]
          },
          {
            "productClassify": "6",
            "productClassifyName": "主食",
            "list": [
              {
                "id": 110,
                "productName": "米饭",
                "productClassify": 6,
                "productType": 1,
                "productSource": 3,
                "productDes": "原味",
                "productPic": "http://img.dev.chdwang.com/common/2018-01-25/2c91340c6128075301612bfc50a30070",
                "priceSale": 90,
                "priceMarket": 100,
                "createTime": 1511491004000,
                "updateTime": 1517231201000,
                "isDel": 0,
                "forLunch": 1,
                "forDinner": 1,
                "supplierProductType": 1,
                "supplierProductDes": null,
                "orderList": [
                  {
                    "orderType": 20,
                    "orderTypeName": "午餐"
                  },
                  {
                    "orderType": 30,
                    "orderTypeName": "晚餐"
                  }
                ]
              }
            ]
          }
        ],
        "timeList": [
          {
            "name": "午餐",
            "start": "9:30:00",
            "end": "20:00:00"
          },
          {
            "name": "晚餐",
            "start": "20:30:00",
            "end": "21:30:00"
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
    
返回关键字段解释
 

demo:


````
        curl -X GET \
        'http://localhost:8080/supplier/classify?supplierCode=code' \
        -H 'cache-control: no-cache' \
        -H 'postman-token: 106c9cc2-e98a-d445-a5e9-b9e05a569564'

````

