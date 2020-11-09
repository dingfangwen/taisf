package com.taisf.services.order.dao;

import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.Check;
import com.jk.framework.dao.page.PageBounds;
import com.taisf.services.common.dao.BaseDao;
import com.taisf.services.enterprise.dto.EnterpriseListRequest;
import com.taisf.services.enterprise.vo.SupOrderStatsVO;
import com.taisf.services.order.dto.EnterpriseStatsRequest;
import com.taisf.services.order.dto.OrderInfoRequest;
import com.taisf.services.order.dto.SupStatsRequest;
import com.taisf.services.order.entity.OrderEntity;
import com.taisf.services.enterprise.vo.EnterpriseOrderStatsVO;
import com.taisf.services.order.vo.OrderExcelVO;
import com.taisf.services.order.vo.OrderInfoVO;
import com.taisf.services.order.vo.OrderListVo;
import com.taisf.services.order.vo.OrderSendStatsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p> 订单的试图操作 </p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on 2016/3/12.
 * @version 1.0
 * @since 1.0
 */
@Repository("order.orderInfoDao")
public class OrderInfoDao extends BaseDao{
	
	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory.getLogger(OrderInfoDao.class);

	private String SQLID = "order.orderInfoDao.";


	/**
	 * 获取企业订单的统计信息
	 * @author afi
	 * @param request
	 * @return
	 */
	public List<EnterpriseOrderStatsVO> getEnterpriseOrderStats(EnterpriseStatsRequest request){
		return mybatisDaoContext.findAll(SQLID + "getEnterpriseOrderStats", EnterpriseOrderStatsVO.class, request);
	}


	/**
	 * 获取企业订单的统计信息
	 * @author afi
	 * @param request
	 * @return
	 */
	public List<SupOrderStatsVO> getSupOrderStats(SupStatsRequest request){
		return mybatisDaoContext.findAll(SQLID + "getSupOrderStats", SupOrderStatsVO.class, request);
	}







	/**
	 *
	 * 获取当前订单的信息
	 * @author afi
	 * @param orderInfoRequest
	 * @return
	 */
	public List<OrderInfoVO> getOrderInfoPageCurrent(OrderInfoRequest orderInfoRequest){
		PageBounds pageBounds=new PageBounds();
		pageBounds.setLimit(orderInfoRequest.getLimit());
		pageBounds.setPage(orderInfoRequest.getPage());

		if (!Check.NuNStr(orderInfoRequest.getUserUid())){
			return mybatisDaoContext.findCurrentPage(SQLID + "getOrderInfoPageCurrent", OrderInfoVO.class, orderInfoRequest,pageBounds);
		}else {
			return mybatisDaoContext.findCurrentPage(SQLID + "getOrderInfo", OrderInfoVO.class, orderInfoRequest,pageBounds);
		}

	}



    /**
	 *
     * 获取当前订单的信息
     * @author afi
     * @param orderInfoRequest
     * @return
     */
    public PagingResult<OrderInfoVO> getOrderInfoPage(OrderInfoRequest orderInfoRequest){
		PageBounds pageBounds=new PageBounds();
		pageBounds.setLimit(orderInfoRequest.getLimit());
		pageBounds.setPage(orderInfoRequest.getPage());

        return mybatisDaoContext.findForPage(SQLID + "getOrderInfo", OrderInfoVO.class, orderInfoRequest,pageBounds);
    }

	/**
	 * 获取当前用户的待完成订单
	 * @param userUid
	 * @return
	 */
	public List<OrderInfoVO> getOrderInfoWaitingList(String userUid){
		return mybatisDaoContext.findAll(SQLID + "getOrderInfoWaitingList", OrderInfoVO.class, userUid);
	}


	/**
	 * 获取订单详情
	 * @param orderSn
	 * @return
	 */
	public OrderInfoVO getOrderInfoByOrderSn(String orderSn){
		return mybatisDaoContext.findOne(SQLID + "getOrderInfoByOrderSn", OrderInfoVO.class, orderSn);
	}



    /**
     * @author:zhangzhengguang
     * @date:2017/10/16
     * @description:分页查询订单列表
     **/
    public PagingResult<OrderInfoVO> pageListOrder(OrderInfoRequest orderInfoRequest){
		PageBounds pageBounds=new PageBounds();
		pageBounds.setLimit(orderInfoRequest.getLimit());
		pageBounds.setPage(orderInfoRequest.getPage());
        return mybatisDaoContext.findForPage(SQLID + "pageListOrder", OrderInfoVO.class, orderInfoRequest,pageBounds);
    }


	/**
	 * 获取全量的订单列表
	 * @param orderInfoRequest
	 * @return
	 */
	public List<OrderInfoVO> listOrder(OrderInfoRequest orderInfoRequest){
		PageBounds pageBounds=new PageBounds();
		pageBounds.setLimit(orderInfoRequest.getLimit());
		pageBounds.setPage(orderInfoRequest.getPage());
		return mybatisDaoContext.findAll(SQLID + "pageListOrder", OrderInfoVO.class, orderInfoRequest);
	}


   /**
    * @author:zhangzhengguang
    * @date:2018/2/28
    * @description:订单查询导出
    **/
    public List<OrderExcelVO> listOrderExcel(OrderInfoRequest orderInfoRequest){
        return mybatisDaoContext.findAll(SQLID + "listOrderExcel", OrderExcelVO.class, orderInfoRequest);
    }

	/**
     * @author:zhangzhengguang
     * @date:2017/10/18
     * @description:企业订单配送
     **/
    public PagingResult<OrderSendStatsVo> finOrderDistributionList(EnterpriseListRequest enterpriseListRequest){
		PageBounds pageBounds=new PageBounds();
		pageBounds.setLimit(enterpriseListRequest.getLimit());
		pageBounds.setPage(enterpriseListRequest.getPage());
        return mybatisDaoContext.findForPage(SQLID + "enterpriseOrderDistributionList", OrderSendStatsVo.class, enterpriseListRequest,pageBounds);
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/18
     * @description:修改订单状态根据企业编号
     **/
    public int sendByEnterpriseCode(OrderEntity orderEntity){
        return mybatisDaoContext.update(SQLID+"sendByEnterpriseCode",orderEntity);
    }
    /**
     * @author:zhangzhengguang
     * @date:2017/10/19
     * @description:根据企业code查询企业下所有待配送订单
     **/
    public PagingResult<OrderEntity> findListByEnterpriseCode(OrderInfoRequest orderInfoRequest){
    	if(Check.NuNObjs(orderInfoRequest,orderInfoRequest.getEnterpriseCode())){
    		return null;
		}
    	PageBounds pageBounds = new PageBounds();
    	pageBounds.setPage(orderInfoRequest.getPage());
    	pageBounds.setLimit(orderInfoRequest.getLimit());
        return mybatisDaoContext.findForPage(SQLID+"findListByEnterpriseCode",OrderEntity.class,orderInfoRequest,pageBounds);
    }

    /**
     * @author:zhangzhengguang
     * @date:2017/10/20
     * @description:配送记录
     **/
    public PagingResult<OrderListVo> findPageLsit(EnterpriseListRequest enterpriseListRequest){
    	PageBounds pageBounds = new PageBounds();
    	pageBounds.setPage(enterpriseListRequest.getPage());
    	pageBounds.setLimit(enterpriseListRequest.getLimit());
        return mybatisDaoContext.findForPage(SQLID+"findPageLsit",OrderListVo.class,enterpriseListRequest,pageBounds);
    }

	public PagingResult<OrderInfoVO> getOrderListPageByEnterprisCode(OrderInfoRequest orderInfoRequest){
		PageBounds pageBounds=new PageBounds();
		pageBounds.setLimit(orderInfoRequest.getLimit());
		pageBounds.setPage(orderInfoRequest.getPage());
		return mybatisDaoContext.findForPage(SQLID + "getOrderListPageByEnterprisCode", OrderInfoVO.class, orderInfoRequest,pageBounds);
	}
}
