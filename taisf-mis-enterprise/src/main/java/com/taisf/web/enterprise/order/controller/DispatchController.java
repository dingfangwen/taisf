package com.taisf.web.enterprise.order.controller;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.enterprise.api.EnterpriseService;
import com.taisf.services.enterprise.dto.EnterpriseDayRequest;
import com.taisf.services.enterprise.dto.EnterprisePageRequest;
import com.taisf.services.enterprise.vo.EnterpriseDispatchVO;
import com.taisf.services.enterprise.vo.EnterpriseListDay;
import com.taisf.services.order.manager.OrderManagerImpl;
import com.taisf.services.ups.entity.EmployeeEntity;
import com.taisf.web.enterprise.common.constant.LoginConstant;
import com.taisf.web.enterprise.common.page.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>配型相关</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/10/19.
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("dispatch/")
public class DispatchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DispatchController.class);

    @Autowired
    private OrderManagerImpl orderManagerImpl;

    @Autowired
    private EnterpriseService enterpriseService;


    /**
     * @author:zhangzhengguang
     * @date:2017/10/17
     * @description:订单配送
     **/
    @RequestMapping("orderDispatching")
    public String orderDispatching(HttpServletRequest request) {
        return "dispatch/orderDispatching";
    }



    /**
     * 修改配送
     * @author afi
     * @param request
     * @param enterpriseDayRequest
     * @return
     */
    @RequestMapping("changeEnterpriseDay")
    @ResponseBody
    public DataTransferObject<Void> changeEnterpriseDay(HttpServletRequest request, EnterpriseDayRequest enterpriseDayRequest) {
        DataTransferObject<Void> dto = new DataTransferObject<>();
        try {
            return enterpriseService.changeEnterpriseDay(enterpriseDayRequest);
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params:{}", JsonEntityTransform.Object2Json(enterpriseDayRequest));
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrorMsg("系统异常");
        }
        return dto;
    }



    /**
     * 分页获取配送信息
     * @author afi
     * @param request
     * @param enterpriseCode
     * @return
     */
    @RequestMapping("getEnterpriseListDay")
    @ResponseBody
    public DataTransferObject<List<EnterpriseListDay>> getEnterpriseListDay(HttpServletRequest request, String enterpriseCode) {
        DataTransferObject<List<EnterpriseListDay>> dto = new DataTransferObject<>();
        try {
            return enterpriseService.getEnterpriseListDay(enterpriseCode);
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params:{}", JsonEntityTransform.Object2Json(enterpriseCode));
            LogUtil.error(LOGGER, "error:{}", e);
            dto.setErrorMsg("系统异常");
        }
        return dto;

    }


    /**
     * 分页获取配送信息
     * @author afi
     * @param request
     * @param enterprisePageRequest
     * @return
     */
    @RequestMapping("orderDispatchPage")
    @ResponseBody
    public PageResult orderDispatchPage(HttpServletRequest request, EnterprisePageRequest enterprisePageRequest) {
        PageResult pageResult = new PageResult();
        try {
            EmployeeEntity emp = (EmployeeEntity)request.getSession().getAttribute(LoginConstant.SESSION_KEY);
            enterprisePageRequest.setSupplierCode(emp.getEmpBiz());
            DataTransferObject<PagingResult<EnterpriseDispatchVO>> dto = enterpriseService.getEnterpriseDispatchByPage(enterprisePageRequest);
            if (dto.checkSuccess()){
                if (!Check.NuNObj(dto.getData())){
                    pageResult.setRows(dto.getData().getList());
                    pageResult.setTotal(dto.getData().getTotal());
                }
            }
        } catch (Exception e) {
            LogUtil.info(LOGGER, "params:{}", JsonEntityTransform.Object2Json(enterprisePageRequest));
            LogUtil.error(LOGGER, "error:{}", e);
            return new PageResult();
        }
        return pageResult;
    }


}
