package com.taisf.services.test.message.dao;

import com.taisf.services.message.dao.MessageUserDao;
import com.taisf.services.message.entity.MessageUserEntity;
import com.taisf.services.test.common.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
 * @author afi on on 2017/3/27.
 * @version 1.0
 * @since 1.0
 */
public class MessageUserDaoTest extends BaseTest {


    @Autowired
    private MessageUserDao messageUserDao;

    @Test
    public void saveMessageUserTest() {
        MessageUserEntity entity = new MessageUserEntity();

        entity.setMessageFid("fid");
        entity.setUserId("uid");
        messageUserDao.saveMessageUser(entity);
    }

}
