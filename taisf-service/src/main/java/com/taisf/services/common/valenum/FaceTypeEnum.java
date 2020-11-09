package com.taisf.services.common.valenum;

import com.jk.framework.base.utils.Check;

/**
 * <p>商品类型</p>
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
public enum FaceTypeEnum {


    NULL(0,"不是"),
    FACE(1,"面对面收款"),
    KNIGHT(2,"骑手扫码"),
    SACN_GUN(3,"扫码枪收款"),
    ;
    private int code;
    private String name;

    private FaceTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
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

    public static FaceTypeEnum getTypeByCode(Integer code) {
        if (Check.NuNObj(code)){
            return  null;
        }
        FaceTypeEnum[] enums = FaceTypeEnum.values();
        for(FaceTypeEnum enumtype:enums) {
            if(enumtype.getCode() == code) {
                return enumtype;
            }
        }
        return null;
    }

}
