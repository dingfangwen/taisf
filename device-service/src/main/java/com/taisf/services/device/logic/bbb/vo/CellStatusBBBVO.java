package com.taisf.services.device.logic.bbb.vo;

import com.jk.framework.base.entity.BaseEntity;

import java.util.Date;

/**
 * <p></p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2020/4/20 23:05
 * @version 1.0
 */
public class CellStatusBBBVO extends BaseEntity {


    private String door;
    private String warm;
    private String light;
    private String disinfect;

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getWarm() {
        return warm;
    }

    public void setWarm(String warm) {
        this.warm = warm;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public String getDisinfect() {
        return disinfect;
    }

    public void setDisinfect(String disinfect) {
        this.disinfect = disinfect;
    }
}
