package com.taisf.services.device.api.abs;

/**
 * <p></p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2020/4/12 17:00
 * @version 1.0
 */
public abstract class LinkAbstractServoice {




    /**
     * 获取配置编码
     * @return
     */
    protected abstract String getConfigCode();


    protected  String getQueenKey(){
        return "LINK_POP_KEY:";
    }


}
