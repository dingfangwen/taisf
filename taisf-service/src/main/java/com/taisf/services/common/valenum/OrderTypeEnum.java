package com.taisf.services.common.valenum;

import com.jk.framework.base.constant.YesNoEnum;
import com.jk.framework.base.entity.BaseEle;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.ValueUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>订单类型</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2016/4/1.
 * @version 1.0
 * @since 1.0
 */
public enum OrderTypeEnum {

    LUNCH_COMMON(20,"午餐",true){
        @Override
        public boolean checkSuit(int forLunch, int forDinner) {
            return forLunch == YesNoEnum.YES.getCode();
        }
    },
    LUNCH_EXT(21,"午餐补餐",false){
        @Override
        public boolean isExt() {
            return true;
        }
    },
    DINNER_COMMON(30,"晚餐",true){
        @Override
        public boolean checkSuit(int forLunch, int forDinner) {
            return forDinner == YesNoEnum.YES.getCode();
        }
    },
    DINNER_EXT(31,"晚餐补单",false){
        @Override
        public boolean isExt() {
            return true;
        }
    },

    FACE_FACE(40,"面对面付款",true){
    },

    FACE(41,"现场收款",true){
    },
    ;
    private int code;
    private String name;

    private Boolean show;

    public Boolean getShow() {
        return show;
    }

    private OrderTypeEnum(int code, String name, boolean show) {
        this.code = code;
        this.name = name;
        this.show = show;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    /**
     * 将枚举转化列表
     * @author afi
     * @return
     */
    public static List<BaseEle> trans2List(){
        List<BaseEle> list = new ArrayList<>();
        OrderTypeEnum[] enums = OrderTypeEnum.values();
        for(OrderTypeEnum enumtype:enums) {
            BaseEle ele = new BaseEle() ;
            ele.setKey(ValueUtil.getStrValue(enumtype.getCode()));
            ele.setValue(enumtype.getName());
            if (!enumtype.getShow()){
                continue;
            }
           list.add(ele);
        }
        return list;
    }

    /**
     * 转换当前的订单类型
     * @param code
     * @return
     */
    public static String transCode2Name(Integer code){
        OrderTypeEnum  orderTypeEnum = getTypeByCode(code);
        if (Check.NuNObj(orderTypeEnum)){
            return "未知状态";
        }
        return orderTypeEnum.getName();
    }


    /**
     * 匹配订单类型
     * @param code
     * @return
     */
    public static OrderTypeEnum getTypeByCode(Integer code) {
        if (Check.NuNObj(code)){
            return null;
        }
        OrderTypeEnum[] enums = OrderTypeEnum.values();
        for(OrderTypeEnum enumtype:enums) {
            if(enumtype.getCode() == code) {
                return enumtype;
            }
        }
        return null;
    }

    /**
     * 默认不是补单
     * @return
     */
    public boolean isExt(){
        return false;
    }

    /**
     * 当前是否匹配
     * @param forLunch
     * @param forDinner
     * @return
     */
    public boolean checkSuit(int forLunch,int forDinner){
        return false;
    }




}
