package com.taisf.services.order.vo;

import com.taisf.services.order.entity.OrderEntity;

/**
 * @author zhangzhengguang
 * @create 2017-10-18
 **/
public class OrderListVo extends OrderEntity {

    private Integer lunchCommon;

    private Integer lunchExt;

    private Integer dinnerCommon;

    private Integer dinnerExt;

    private String enterpriseName;

    private Integer countNum;

    private Integer successNum;

    public Integer getCountNum() {
        return countNum;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    public Integer getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Integer getLunchCommon() {
        return lunchCommon;
    }

    public void setLunchCommon(Integer lunchCommon) {
        this.lunchCommon = lunchCommon;
    }

    public Integer getLunchExt() {
        return lunchExt;
    }

    public void setLunchExt(Integer lunchExt) {
        this.lunchExt = lunchExt;
    }

    public Integer getDinnerCommon() {
        return dinnerCommon;
    }

    public void setDinnerCommon(Integer dinnerCommon) {
        this.dinnerCommon = dinnerCommon;
    }

    public Integer getDinnerExt() {
        return dinnerExt;
    }

    public void setDinnerExt(Integer dinnerExt) {
        this.dinnerExt = dinnerExt;
    }
}
