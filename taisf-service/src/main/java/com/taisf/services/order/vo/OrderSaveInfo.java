package com.taisf.services.order.vo;

import com.jk.framework.base.entity.BaseEntity;
import com.jk.framework.base.utils.SnUtil;
import com.taisf.services.enterprise.entity.EnterpriseAddressEntity;
import com.taisf.services.order.entity.OrderEntity;
import com.taisf.services.order.entity.OrderMoneyEntity;
import com.taisf.services.order.entity.OrderProductEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>保存订单的vo</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/29.
 * @version 1.0
 * @since 1.0
 */
public class OrderSaveInfo extends BaseEntity{

    private static final long serialVersionUID = 301231231201446703L;

    /**
     * 当前余额
     */
    private  int drawBalance;

    /**
     * 用户信息
     */
    private  UserVO user;

    /**
     * 是否需要密码
     */
    private Boolean needPwd = true;


    /**
     * 订单的金额信息
     */
    private OrderMoneyEntity orderMoney = new OrderMoneyEntity();

    /**
     * 订单的商品信息
     */
    private List<OrderProductEntity> list = new ArrayList<>();


    /**
     * 地址
     */
    List<EnterpriseAddressEntity> addressList = new ArrayList<>();

    public int getDrawBalance() {
        return drawBalance;
    }

    public void setDrawBalance(int drawBalance) {
        this.drawBalance = drawBalance;
    }

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

    public OrderMoneyEntity getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(OrderMoneyEntity orderMoney) {
        this.orderMoney = orderMoney;
    }

    public List<OrderProductEntity> getList() {
        return list;
    }

    public void setList(List<OrderProductEntity> list) {
        this.list = list;
    }

    public List<EnterpriseAddressEntity> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<EnterpriseAddressEntity> addressList) {
        this.addressList = addressList;
    }

    public Boolean getNeedPwd() {
        return needPwd;
    }

    public void setNeedPwd(Boolean needPwd) {
        this.needPwd = needPwd;
    }
}
