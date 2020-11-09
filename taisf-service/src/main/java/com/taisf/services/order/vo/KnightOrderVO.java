package com.taisf.services.order.vo;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/12/2.
 * @version 1.0
 * @since 1.0
 */
public class KnightOrderVO extends FaceVO {

    /**
     * 用户id
     */
    private String userUid;


    /**
     * 地址
     */
    private String street;;

    /**
     * 用户id
     */
    private String userName;


    /**
     * 用户电话
     */
    private String userPhone;


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }


}
