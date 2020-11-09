package com.taisf.web.enterprise.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhangzhengguang
 * @create 2018-09-21
 **/
@Component
public class PrinterConstant {


    //*必填*：账号名
    @Value("#{'${user}'}")
    public String USER;

    //*必填*: 注册账号后生成的UKEY
    @Value("#{'${ukey}'}")
    public String UKEY;
}
