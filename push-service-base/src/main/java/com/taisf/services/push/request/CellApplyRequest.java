package com.taisf.services.push.request;

import com.taisf.services.push.core.PushPar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>申请格子</p>
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
public class CellApplyRequest extends SendRequest {


    /**
     * 语音
     */
    private String voiceAlert;


    /**
     * 地址
     */
    private String address;


    /**
     * 餐柜号
     */
    private String buffetFid;

    /**
     * 餐格
     */
    private String cellNum;




    public String getVoiceAlert() {
        return voiceAlert;
    }

    public void setVoiceAlert(String voiceAlert) {
        this.voiceAlert = voiceAlert;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuffetFid() {
        return buffetFid;
    }

    public void setBuffetFid(String buffetFid) {
        this.buffetFid = buffetFid;
    }

    public String getCellNum() {
        return cellNum;
    }

    public void setCellNum(String cellNum) {
        this.cellNum = cellNum;
    }

    @Override
    public PushPar transPushPar() {
        PushPar par = new PushPar();
        par.setTitle("取餐提醒");

        String cxt = "客官您好，您预定的餐食已出炉啦，请您到：{address}{buffetFid}柜{cellNum}格自助取餐。点击查看详情";
        cxt = cxt.replace("{address}",address);
        cxt = cxt.replace("{buffetFid}",buffetFid);
        cxt = cxt.replace("{cellNum}",cellNum);
        par.setContent(cxt);
        List<String> tokenList = new ArrayList<>();
        tokenList.add(getToken());
        par.setToken(tokenList);
        Map<String,String> param = new HashMap<>();
        param.put("messageType","1");
        param.put("voiceAlert", "客官您好，您预定的餐食已出炉啦");
        par.setExtra(param);
        return par;
    }
}
