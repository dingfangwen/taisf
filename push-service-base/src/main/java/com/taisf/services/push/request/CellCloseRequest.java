package com.taisf.services.push.request;

import com.taisf.services.push.core.PushPar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>关闭格子</p>
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
public class CellCloseRequest extends SendRequest {


    /**
     * 语音
     */
    private String voiceAlert;





    public String getVoiceAlert() {
        return voiceAlert;
    }

    public void setVoiceAlert(String voiceAlert) {
        this.voiceAlert = voiceAlert;
    }




    @Override
    public PushPar transPushPar() {
        PushPar par = new PushPar();
        par.setTitle("取餐成功提醒");

        String cxt = "客官您好，您的餐品已成功取出，祝您用餐愉快！";
        par.setContent(cxt);
        List<String> tokenList = new ArrayList<>();
        tokenList.add(getToken());
        par.setToken(tokenList);
        Map<String,String> param = new HashMap<>();
        param.put("messageType","1");
        param.put("voiceAlert", cxt);
        par.setExtra(param);
        return par;
    }
}
