package com.taisf.services.device.vo;

import com.jk.framework.base.entity.BaseEntity;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * <p>单元格对象</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/4/1.
 * @version 1.0
 * @since 1.0
 */
public class CellModelCDVO extends BaseEntity {


    private String cellNo;
    private String outOrderId;
    private Integer cellStatus;
    private Integer useStatus;
    private Integer frontDoor;
    private Integer backDoor;
    private Integer frontLight;
    private Integer backLight;
    private Integer sterilamp;
    private Integer freeze;
    private Integer warm;
    private Integer infrared;

    public CellModelCDVO() {
    }


    public String getCellNo() {
        return cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public Integer getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(Integer cellStatus) {
        this.cellStatus = cellStatus;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public Integer getFrontDoor() {
        return frontDoor;
    }

    public void setFrontDoor(Integer frontDoor) {
        this.frontDoor = frontDoor;
    }

    public Integer getBackDoor() {
        return backDoor;
    }

    public void setBackDoor(Integer backDoor) {
        this.backDoor = backDoor;
    }

    public Integer getFrontLight() {
        return frontLight;
    }

    public void setFrontLight(Integer frontLight) {
        this.frontLight = frontLight;
    }

    public Integer getBackLight() {
        return backLight;
    }

    public void setBackLight(Integer backLight) {
        this.backLight = backLight;
    }

    public Integer getSterilamp() {
        return sterilamp;
    }

    public void setSterilamp(Integer sterilamp) {
        this.sterilamp = sterilamp;
    }

    public Integer getFreeze() {
        return freeze;
    }

    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
    }

    public Integer getWarm() {
        return warm;
    }

    public void setWarm(Integer warm) {
        this.warm = warm;
    }

    public Integer getInfrared() {
        return infrared;
    }

    public void setInfrared(Integer infrared) {
        this.infrared = infrared;
    }
}
