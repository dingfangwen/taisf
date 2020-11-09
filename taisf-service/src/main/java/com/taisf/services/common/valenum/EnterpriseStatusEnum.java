package com.taisf.services.common.valenum;

/**
 * <p>企业状态</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2016/10/1.
 * @version 1.0
 * @since 1.0
 */
public enum EnterpriseStatusEnum {


    NO_SUBMIT(0,"未提交","该企业状态未审核通过"),
    SUCCESS(1,"正常",""){
        @Override
        public boolean checkOk() {
            return true;
        }
    },
    TIME_OUT(2,"已过期","该企业合作已过期"),
    STOP(3,"停止合作","该企业已停止合作"),
    ;
    private int code;
    private String name;

    private String des;

    private EnterpriseStatusEnum(int code, String name,String des) {
        this.code = code;
        this.name = name;
        this.des = des;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public static EnterpriseStatusEnum getTypeByCode(int code) {
        EnterpriseStatusEnum[] enums = EnterpriseStatusEnum.values();
        for(EnterpriseStatusEnum enumtype:enums) {
            if(enumtype.getCode() == code) {
                return enumtype;
            }
        }
        return null;
    }


    /**
     * 当前企业状态是否正常
     * @return
     */
    public boolean checkOk(){
        return false;
    }
}
