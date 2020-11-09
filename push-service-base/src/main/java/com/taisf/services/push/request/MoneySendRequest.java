package com.taisf.services.push.request;

import com.taisf.services.push.core.PushPar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2018/4/26.
 * @version 1.0
 * @since 1.0
 */
public class MoneySendRequest extends SendRequest {


    /**
     * 语音
     */
    private String voiceAlert;

    /**
     * 金额
     */
    private String money;

    /**
     * 名称
     */
    private String name;

    /**
     * 电话
     */
    private String phone;


    public String getVoiceAlert() {
        return voiceAlert;
    }

    public void setVoiceAlert(String voiceAlert) {
        this.voiceAlert = voiceAlert;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public PushPar transPushPar() {
        PushPar par = new PushPar();
        par.setTitle("馋滴");
        par.setContent("馋滴支付:馋滴收款" + getMoney() + "元");
        List<String> tokenList = new ArrayList<>();
        tokenList.add(getToken());
        par.setToken(tokenList);
        Map<String,String> param = new HashMap<>();
        param.put("messageType","1");
        param.put("voiceAlert", getName() + "支付" + getMoney() + "元");
        param.put("money",getMoney());
        param.put("name",getName());
        param.put("phone",getPhone());
        par.setExtra(param);

        return par;
    }
}
