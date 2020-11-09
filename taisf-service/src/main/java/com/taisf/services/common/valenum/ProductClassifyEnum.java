package com.taisf.services.common.valenum;

/**
 * <p>同步状态</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author zyf on on 2017/6/1.
 * @version 1.0
 * @since 1.0
 */
public enum ProductClassifyEnum {

    SUGGEST("推荐", 100){
        @Override
        public SupplierProductTypeEnum getSupplierProductTypeEnum() {
            //返回商品包的信息
            return SupplierProductTypeEnum.PACKAGE;
        }
    },

    DAHUN("大荤", 1),
    XIAOHUN("小荤", 2),
    SU("素", 3),
    TANG("汤", 4),
    YINPIN("饮品", 5),
    ZHUSHI("主食", 6),
    SHUIGUO("水果", 7),
    FULI("超市", 8)

    ;

    // 成员变量
    private String name;
    private Integer code;

    private SupplierProductTypeEnum supplierProductTypeEnum;

    public String getName() {
        return name;
    }

    public Integer getCode() {
        return code;
    }

    // 构造方法
    private ProductClassifyEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }



    // 普通方法
    public static ProductClassifyEnum getByCode(Integer code) {
        for (ProductClassifyEnum c : ProductClassifyEnum.values()) {
            if (c.code.equals(code)) {
                return c;
            }
        }
        return null;
    }


    public static void main(String[] args) {

        for (ProductClassifyEnum c : ProductClassifyEnum.values()) {
            System.out.println(c.getName());
        }

    }

    /**
     * 获取当前分类的转化
     * @return
     */
    public  SupplierProductTypeEnum getSupplierProductTypeEnum(){
        return SupplierProductTypeEnum.PRODUCT;
    }
}
