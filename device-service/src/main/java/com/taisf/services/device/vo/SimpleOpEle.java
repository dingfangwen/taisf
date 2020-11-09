package com.taisf.services.device.vo;

import com.jk.framework.base.entity.BaseEntity;

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
public class SimpleOpEle  extends BaseEntity {

    /**
     * 设备号
     */
    private String cellNo;

    private List<String> ops;


    public String getCellNo() {
        return cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }

    public List<String> getOps() {
        return ops;
    }

    public void setOps(List<String> ops) {
        this.ops = ops;
    }
}
