package com.taisf.services.device.logic.aaa.command.model;

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
public class LinkCellModel extends BaseEntity {


    @JsonProperty("CellNo")
    private String cellNo;
    @JsonProperty("OutOrderId")
    private String outOrderId;
    @JsonProperty("CellStatus")
    private Integer cellStatus;
    @JsonProperty("UseStatus")
    private Integer useStatus;
    @JsonProperty("FrontDoor")
    private Integer frontDoor;
    @JsonProperty("BackDoor")
    private Integer backDoor;
    @JsonProperty("FrontLight")
    private Integer frontLight;
    @JsonProperty("BackLight")
    private Integer backLight;
    @JsonProperty("Sterilamp")
    private Integer sterilamp;
    @JsonProperty("Freeze")
    private Integer freeze;
    @JsonProperty("Warm")
    private Integer warm;
    @JsonProperty("Infrared")
    private Integer infrared;

    public LinkCellModel() {
    }

    public String getCellNo() {
        return this.cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }

    public String getOutOrderId() {
        return this.outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public Integer getCellStatus() {
        return this.cellStatus;
    }

    public void setCellStatus(Integer cellStatus) {
        this.cellStatus = cellStatus;
    }

    public Integer getUseStatus() {
        return this.useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public Integer getFrontDoor() {
        return this.frontDoor;
    }

    public void setFrontDoor(Integer frontDoor) {
        this.frontDoor = frontDoor;
    }

    public Integer getBackDoor() {
        return this.backDoor;
    }

    public void setBackDoor(Integer backDoor) {
        this.backDoor = backDoor;
    }

    public Integer getFrontLight() {
        return this.frontLight;
    }

    public void setFrontLight(Integer frontLight) {
        this.frontLight = frontLight;
    }

    public Integer getBackLight() {
        return this.backLight;
    }

    public void setBackLight(Integer backLight) {
        this.backLight = backLight;
    }

    public Integer getSterilamp() {
        return this.sterilamp;
    }

    public void setSterilamp(Integer sterilamp) {
        this.sterilamp = sterilamp;
    }

    public Integer getFreeze() {
        return this.freeze;
    }

    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
    }

    public Integer getWarm() {
        return this.warm;
    }

    public void setWarm(Integer warm) {
        this.warm = warm;
    }

    public Integer getInfrared() {
        return this.infrared;
    }

    public void setInfrared(Integer infrared) {
        this.infrared = infrared;
    }
}
