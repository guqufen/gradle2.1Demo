package net.fnsco.controller.app.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.api.constant.ApiConstant;
import net.fnsco.api.dto.VersionDTO;
import net.fnsco.api.dto.VersionResultDTO;
import net.fnsco.api.merchant.VersionService;
import net.fnsco.controller.app.jo.CommJO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;

/**
 * 开放接口公共处理类
 * @author sxf
 *
 */
@RestController
@RequestMapping(value = "/open/comm", method = RequestMethod.POST)
public class CommonController extends BaseController {
    @Autowired
    private Environment    env;
    @Autowired
    private VersionService versionService;

    /**
     * 获取APP下载地址
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/appDownUrl")
    @ApiOperation(value = "获取APP下载地址")
    public ResultDTO getMerCode() {
        return success(env.getProperty(ApiConstant.THIS_PROGREM_URL));
    }

    @RequestMapping(value = "/checkUpdate")
    @ApiOperation(value = "检查是否有新版本")
    public ResultDTO checkUpdate(@RequestBody CommJO commJO) {
        String version = commJO.getVersion();
        Integer type = commJO.getType();
        String[] versionArr = version.split("\\.");
        if (versionArr == null || versionArr.length != 3) {
            logger.warn("版本号格式错误,version=" + version);
            return ResultDTO.fail();
        }
        String appCode = "lkl";
        VersionDTO sysVersionDTO = new VersionDTO();
        sysVersionDTO.setAppType(type);
        sysVersionDTO.setVersion(version);
        sysVersionDTO.setAppCode(appCode);
        VersionResultDTO resultDTO = versionService.queryVersionInfo(sysVersionDTO);
        return ResultDTO.success(resultDTO);
    }
}
