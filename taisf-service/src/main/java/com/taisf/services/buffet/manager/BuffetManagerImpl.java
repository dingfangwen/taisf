package com.taisf.services.buffet.manager;

import com.jk.framework.base.page.PagingResult;
import com.taisf.services.buffet.dao.BuffetDao;
import com.taisf.services.buffet.dto.BuffetInfoRequest;
import com.taisf.services.buffet.entity.BuffetEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p>购物车先关操作</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/9/23.
 * @version 1.0
 * @since 1.0
 */
@Service("buffet.buffetManagerImpl")
public class BuffetManagerImpl {

	@Resource(name = "buffet.buffetDao")
	private BuffetDao buffetDao;



	/**
	 * 获取当前的设备信息
	 * @param buffetInfoRequest
	 * @return
	 */
	public BuffetEntity getBuffetByFid(BuffetInfoRequest buffetInfoRequest){
		return buffetDao.getBuffetByFid(buffetInfoRequest);
	}

	public BuffetEntity getBuffetByDeviceId(BuffetInfoRequest buffetInfoRequest){
		return buffetDao.getBuffetByDeviceId(buffetInfoRequest);
	}


	/**
	 * 保存设备
	 * @author afi
	 * @param record
	 * @return
	 */
	public int saveBuffet(BuffetEntity record){
		return buffetDao.saveBuffet(record);
	}

	public int updateById(BuffetEntity record){
		return buffetDao.updateById(record);
	}

	public PagingResult<BuffetEntity> pageListBuffet(BuffetInfoRequest request){
		return buffetDao.pageListBuffet(request);
	}


}
