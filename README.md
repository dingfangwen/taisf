
##  1.服务器地址

http://59.110.222.82:8080

## 2.接口请求参数
 head
 ````
 Content-Type:application/json
 traceInfo:applicationCode=aa;deviceUuid=deviceUuid;
 token:用户的token信息
 ````
 traceInfo:对象
   ````
     private String versionName;
     private String versionCode;
     private String buildVersion;
     private String osVersion;
     private String model;
     private String appName;
     private String applicationCode;
     private String clientName;
     private String channelId;
     private String idfa;
     private String deviceUuid;
     private int loginSource;
     private String userId;
     private String userToken;
     float cityLang;
     float cityLat;
     private int source;
     private int deviceType;
 ````
##  接口文档
 
排序|描述|链接
---|---|---
 1|首页和用户| [首页用户相关](doc/user.md)
 2|菜单相关接口|[菜单相关文档](doc/product.md)
 3|购物车相关接口|[购物车相关文档](doc/cart.md)
 4|订单相关接口|[订单相关文档](doc/order.md)
 5|我的相关接口|[我的相关文档](doc/my.md)
 6|骑手相关接口|[骑手相关文档](doc/knight.md)
 7|支付相关|[支付相关文档](doc/pay.md)
 8|退款相关|[支付相关文档](doc/refund.md)
 9|验证码|[发送短信相关文档](doc/send.md)
