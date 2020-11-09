package com.taisf.services.device.vo;

import com.jk.framework.base.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2020/2/29 22:13
 * @version 1.0
 */
public class SimpleOp extends BaseEntity {


    private List<SimpleOpEle>  opList;

    /**
     * 设备号
     */
    private String deviceId;


    public SimpleOp(String deviceId) {
        this.deviceId = deviceId;
    }

    public List<SimpleOpEle> getOpList() {
        return opList;
    }

    public void addOp(SimpleOpEle simpleOp) {
        if (opList ==  null){
            opList = new ArrayList<>();
        }
        opList.add(simpleOp);
    }

    public void setOpList(List<SimpleOpEle> opList) {
        this.opList = opList;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
