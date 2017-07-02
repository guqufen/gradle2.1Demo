package net.fnsco.service.modules.merchant;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.api.constant.ApiConstant;
import net.fnsco.api.dto.VersionDTO;
import net.fnsco.api.dto.VersionResultDTO;
import net.fnsco.api.merchant.VersionService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.dao.master.VersionDao;
import net.fnsco.service.domain.Version;

@Service
public class VersionServiceImpl extends BaseService implements VersionService {

    @Autowired
    private VersionDao sysVersionDao;

    @Override
    public ResultDTO checkUpdate(VersionDTO sysVersionDTO) {
        //
        String version = sysVersionDTO.getVersion();
        Integer appType = Integer.valueOf(sysVersionDTO.getAppType());
        //非空判断
        if (Strings.isNullOrEmpty(version)) {
            return ResultDTO.fail(ApiConstant.E_APP_PHONE_EMPTY);
        } else if (appType == null) {
            return ResultDTO.fail(ApiConstant.E_APP_PASSWORD_EMPTY);
        } else if (Strings.isNullOrEmpty(sysVersionDTO.getDeviceId())) {
            return ResultDTO.fail(ApiConstant.E_APP_CODE_EMPTY);
        }

        //客户端版本号
        String[] versionArr = StringUtils.split(version, ".");
        if (versionArr == null || versionArr.length != 3) {
            logger.warn("版本号格式错误,version=" + version);
            return ResultDTO.fail();
        }

        String appCode = "sqb";
        List<Version> list = sysVersionDao.selectByPrimaryKey(appCode, appType, version);
        //便利list集合
        int flag = 0;
        Version ver = new Version();
        for (Version temp : list) {
            if (temp.getForceUpdate() == 1) {
                flag = 1;
            }
            ver = temp;
        }
        ver.setForceUpdate(flag);
        return ResultDTO.success(ver);
    }

    @Override
    public VersionResultDTO queryVersionInfo(VersionDTO sysVersionDTO) {
        List<Version> list = sysVersionDao.selectByPrimaryKey(sysVersionDTO.getAppCode(), sysVersionDTO.getAppType(), sysVersionDTO.getVersion());
        //便利list集合
        int flag = 0;
        Version ver = new Version();
        for (Version temp : list) {
            if (temp.getForceUpdate() == 1) {
                flag = 1;
            }
            ver = temp;
        }

        ver.setForceUpdate(flag);
        VersionResultDTO result = new VersionResultDTO();
        result.setDownloadUrl1(ver.getDownloadUrl1());
        result.setForceUpdate(String.valueOf(flag));
        Boolean isUpdate = false;
        if (sysVersionDTO.getVersion().equals(ver.getVersion())) {
            isUpdate = false;
        } else {
            isUpdate = true;
        }
        result.setIsUpdate(isUpdate);
        result.setVersion(ver.getVersion());
        return result;
    }
}
