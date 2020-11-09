package com.taisf.services.test.buffet.proxy;

import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.page.PagingResult;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.taisf.services.buffet.proxy.BuffetServiceProxy;
import com.taisf.services.device.vo.CellModelCDVO;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/4/11.
 * @version 1.0
 * @since 1.0
 */
public class BuffetServiceProxyTest  extends BaseTest {


    @Resource(name = "buffet.buffetServiceProxy")
    private BuffetServiceProxy buffetServiceProxy;


    @Test
    public void pageListCellTest() {

        DataTransferObject<PagingResult<CellModelCDVO>> page = buffetServiceProxy.pageListCell("aaa","f0e6b85a39ac3a3d28d18f6cbd3cb89d");
        System.out.println(JsonEntityTransform.Object2Json(page));

    }

}
