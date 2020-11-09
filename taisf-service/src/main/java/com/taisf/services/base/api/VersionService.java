package com.taisf.services.base.api;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.head.Header;

/**
 * <p>版本信息</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/3/27.
 * @version 1.0
 * @since 1.0
 */
public interface VersionService {

    /**
     * 获取当前的版本信息
     * @author  afi
     * @param header
     * @return
     */
    DataTransferObject getVersion(Header header);
}
