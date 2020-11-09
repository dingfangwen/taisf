package com.taisf.services.order.vo;

import com.jk.framework.base.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>购物车的基本信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/28.
 * @version 1.0
 * @since 1.0
 */
public class CartInfoVO extends BaseEntity {

    private static final long serialVersionUID = 301231231201446703L;


    /**
     * 商家uid
     */
    private String businessUid;

    /**
     * 用户uid
     */
    private String userUid;

    /**
     * 购物车价格
     */
    private Integer price=0;

    /**
     * 购物车中的商品信息
     */
    List<CartVO> list=new ArrayList<>();

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<CartVO> getList() {
        return list;
    }

    public void setList(List<CartVO> list) {
        this.list = list;
    }


    public String getBusinessUid() {
        return businessUid;
    }

    public void setBusinessUid(String businessUid) {
        this.businessUid = businessUid;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }
}
