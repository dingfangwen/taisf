package com.taisf.services.supplier.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>菜单分类和商品</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/12.
 * @version 1.0
 * @since 1.0
 */
public class ProductClassifyInfo extends ProductClassifyVO {

    /**
     * 对应的商品信息
     */
    private List<SupplierProductVO> list = new ArrayList<>();


    public List<SupplierProductVO> getList() {
        return list;
    }

    public void setList(List<SupplierProductVO> list) {
        this.list = list;
    }
}
