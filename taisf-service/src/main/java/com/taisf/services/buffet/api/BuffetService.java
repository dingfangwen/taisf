package com.taisf.services.buffet.api;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.taisf.services.buffet.dto.BuffetInfoRequest;
import com.taisf.services.buffet.entity.BuffetEntity;
import com.taisf.services.buffet.vo.BuffetVO;
import com.taisf.services.device.vo.CellModelCDVO;

/**
 * <p>餐柜信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/4/8.
 * @version 1.0
 * @since 1.0
 */
public interface BuffetService {

    /**
     * 获取设备信息
     * @param buffetInfoRequest
     * @return
     */
    DataTransferObject<BuffetVO> getBuffetInfo(BuffetInfoRequest buffetInfoRequest);


    /**
     * 获取设备信息
     * @param buffetInfoRequest
     * @return
     */
    DataTransferObject<BuffetEntity> getBuffetLocal(BuffetInfoRequest buffetInfoRequest);




    BuffetEntity getBuffetByFid(BuffetInfoRequest buffetInfoRequest);

    BuffetEntity getBuffetByDeviceId(BuffetInfoRequest buffetInfoRequest);

    /**
     * @author:zhangzhengguang
     * @date:2019/4/9
     * @description:新增保存
     **/
    DataTransferObject<Void> saveBuffet(BuffetEntity buffetEntity);

    /**
     * @author:zhangzhengguang
     * @date:2019/4/9
     * @description:根据ID修改
     **/
    DataTransferObject<Void> updateById(BuffetEntity buffetEntity);

    /**
     * @author:zhangzhengguang
     * @date:2019/4/9
     * @description:分页查询
     **/
    DataTransferObject<PagingResult<BuffetEntity>> pageListBuffet(BuffetInfoRequest request);

    /**
     * 获取当前设备的格子状态
     * @param deviceId
     * @return
     */
    DataTransferObject<PagingResult<CellModelCDVO>> pageListCell(String configCode,String deviceId);


}
