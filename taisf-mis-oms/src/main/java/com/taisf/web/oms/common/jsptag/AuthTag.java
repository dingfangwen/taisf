package com.taisf.web.oms.common.jsptag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.taisf.services.ups.api.ResourceService;
import com.taisf.services.ups.entity.EmployeeEntity;
import com.taisf.services.ups.entity.ResourceEntity;
import com.taisf.web.oms.common.constant.LoginConstant;

/**
 * <p>自定义标签</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/3/22.
 * @version 1.0
 * @since 1.0
 */
@Component("authTag")
public class AuthTag extends TagSupport {

	/*@Resource(name = "ups.resourceServiceProxy")
	private ResourceService resourceService;*/
	
    /**
     * 序列化id
     */
    private static final long serialVersionUID = -3234634512908407214L;
    /**
     * 权限url
     */
    private String authUrl;

    @Override
    public int doAfterBody() throws JspException {
        return super.doAfterBody();
    }

    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }
    
    @Override
    public int doStartTag() throws JspException {
    	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
    	ResourceService resourceService = (ResourceService)ctx.getBean("ups.resourceServiceProxy");
    	HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
    	EmployeeEntity entity = (EmployeeEntity) request.getSession().getAttribute(LoginConstant.SESSION_KEY);
    	String userId = entity.getUserId();
    	String resultJson = resourceService.selectByUserId(userId);
    	List<ResourceEntity> resourceList = JSON.parseArray(resultJson, ResourceEntity.class);
    	if(resourceList!=null&&resourceList.size()>0){
    		for (ResourceEntity resource : resourceList) {
    			if(resource.getResUrl().equals(authUrl)){
    				return TagSupport.EVAL_BODY_INCLUDE;
    			}
    		}
    	}
    	return TagSupport.SKIP_BODY;
    }
    /**
     * @return the authUrl
     */
    public String getAuthUrl() {
        return authUrl;
    }

    /**
     * @param authUrl the authUrl to set
     */
    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }


}
