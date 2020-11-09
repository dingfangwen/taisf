package com.taisf.services.base.proxy;

import com.jk.framework.base.constant.YesNoEnum;
import com.jk.framework.base.entity.DataTransferObject;
import com.jk.framework.base.head.Header;
import com.jk.framework.base.utils.Check;
import com.jk.framework.base.utils.ValueUtil;
import com.jk.framework.log.utils.LogUtil;
import com.taisf.services.base.entity.UpgradeEntity;
import com.taisf.services.base.manager.VersionManagerImpl;
import com.taisf.services.base.api.VersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>获取版本更新信息</p>
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
@Component("basedata.versionServiceProxy")
public class VersionServiceProxy implements VersionService {


    private static final Logger LOGGER = LoggerFactory.getLogger(VersionServiceProxy.class);

    @Resource(name = "basedata.versionManagerImpl")
    private VersionManagerImpl versionManager;


    /**
     * 获取当前的版本信息
     * @author  afi
     * @param header
     * @return
     */
    @Override
    public DataTransferObject<UpgradeEntity> getVersion(Header header) {
        DataTransferObject dto = new DataTransferObject();
        try {
            UpgradeEntity upgrade = versionManager.getVersion(header);
            if (Check.NuNObj(upgrade)) {
                return dto;
            }
            //指定升级.
            if (upgrade.getSpecificEdition() == 0) {
                if (ValueUtil.getintValue(header.getVersionCode()) < upgrade.getMinVersion()) {
                    upgrade.setFourceUpGradeFlag(upgrade.getIsForce() == YesNoEnum.YES.getCode());
                }
            } else if (upgrade.getSpecificEdition().equals(upgrade.getVersionCode())) {
                upgrade.setFourceUpGradeFlag(upgrade.getIsForce() == YesNoEnum.YES.getCode());
            }
            //判断强弱提醒
            if (upgrade.getFourceUpGradeFlag()) {
                upgrade.setPromtUpGradeFlag(false);
            } else if (ValueUtil.getintValue(header.getVersionCode()) < upgrade.getVersionCode()) {
                upgrade.setPromtUpGradeFlag(true);
            }
            dto.setData(upgrade);
        } catch (Exception e) {
            LogUtil.error(LOGGER, "【获取版本信息】error:{}", e);
            dto.setErrCode(DataTransferObject.ERROR);
            dto.setMsg("获取版本信息失败");
            return dto;
        }
        return dto;
    }
}
