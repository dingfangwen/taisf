package com.taisf.services.device.logic.aaa.command.client;

import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.common.utils.CloseableHttpUtil;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.device.entity.DeviceConfigEntity;
import com.taisf.services.device.logic.aaa.command.base.LinkApiException;
import com.taisf.services.device.logic.aaa.command.base.LinkRequest;
import com.taisf.services.device.logic.aaa.command.base.LinkResponse;
import com.taisf.services.device.logic.aaa.command.request.LinkBaseRequest;
import com.taisf.services.device.vo.TransVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/4/1.
 * @version 1.0
 * @since 1.0
 */
public class LinkClient {

    private final Logger logger = LoggerFactory.getLogger(LinkClient.class);


    private String serverUrl;
    private String appId;
    private String appSecret;


    public LinkClient(DeviceConfigEntity config) {
        this.serverUrl = config.getServerUrl();
        this.appId = config.getAppId();
        this.appSecret = config.getAppSecret();
    }



//    public LinkClient(String serverUrl, String appId, String appSecret) {
//        this.serverUrl = serverUrl;
//        this.appId = appId;
//        this.appSecret = appSecret;
//    }

    /**
     * 调用三方的请求
     * @param request
     * @param <T>
     * @return
     * @throws LinkApiException
     */
    public <T extends LinkResponse> T execute(LinkRequest<T> request ) throws LinkApiException {
        try {
            //调用请求日志
            String sendPost = CloseableHttpUtil.sendPost(this.serverUrl + request.getUri(), JsonEntityTransform.Object2Json(request));
            LogUtil.info(logger,"LinkClient:par:{},",JsonEntityTransform.Object2Json(request));
            LogUtil.info(logger,"LinkClient:rst:{}",sendPost);
            Class c = request.getResponseClass();
            return  (T)JsonEntityTransform.json2Entity(sendPost, c);
        } catch (Exception var4) {
            throw new LinkApiException(var4.getMessage(), var4);
        }
    }


    /**
     * 处理加密的参数
     * @param baseRequest
     * @return
     */
    public LinkBaseRequest trans2Sign(LinkBaseRequest baseRequest){
        baseRequest.setAppId(this.appId);

        String url =  this.serverUrl  +  "/api/open/digest/get";
        String par = JsonEntityTransform.Object2Json(baseRequest);
        String json = CloseableHttpUtil.sendPost(url, par);
        TransVO transVO = JsonEntityTransform.json2Entity(json, TransVO.class);
        if (Check.NuNObj(transVO)){
            throw  new LinkApiException("获取签名错误");
        }
        if (!transVO.getSuccess()){
            throw  new LinkApiException(transVO.getMsg());
        }
        String Digest = (String) transVO.getData();
        baseRequest.setDigest(Digest);
        return baseRequest;
    }

}
