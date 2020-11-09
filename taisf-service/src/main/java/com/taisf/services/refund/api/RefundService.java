package com.taisf.services.refund.api;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.taisf.services.refund.dto.RefundQueryRequest;
import com.taisf.services.refund.dto.RefundRequest;
import com.taisf.services.refund.entity.RefundEntity;
import com.taisf.services.refund.vo.RefundVo;

/**
 * @author:zhangzhengguang
 * @date:2017/12/21
 * @description:退款
 **/
public interface RefundService {
    /**
     * @author:zhangzhengguang
     * @date:2017/12/21
     * @description:分页查询退款列表
     **/
    DataTransferObject<PagingResult<RefundVo>> refundPageList(RefundQueryRequest request);

    /**
     * @author:zhangzhengguang
     * @date:2017/12/21
     * @description:根据ID查询
     **/
    DataTransferObject<RefundEntity> findRefundById(Integer id);

    /**
     * @author:zhangzhengguang
     * @date:2017/10/12
     * @description:根据ID修改
     **/
    DataTransferObject<Void> updateRefund(RefundEntity refundEntity);

    /**
     * 处理退款逻辑
     * @author afi
     * @param refundRequest
     * @return
     */
    DataTransferObject<Void> dealRefundBack(RefundRequest refundRequest);
}
