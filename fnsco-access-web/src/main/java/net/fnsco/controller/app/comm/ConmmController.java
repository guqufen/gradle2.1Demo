package net.fnsco.controller.app.comm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.api.appuser.ConmmService;
import net.fnsco.api.constant.ApiConstant;
import net.fnsco.api.constant.ConstantEnum.AppTypeEnum;
import net.fnsco.api.dto.ProtocolDTO;
import net.fnsco.api.dto.SuggestDTO;
import net.fnsco.api.dto.VersionDTO;

import net.fnsco.controller.app.jo.DiscoveryJO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;

@RestController
@RequestMapping(value = "/app/user", method = RequestMethod.POST)

public class ConmmController extends BaseController {
    @Autowired
    private ConmmService conmmService;
    @Autowired
    private Environment  env;

    @RequestMapping(value = "/checkUpdate")
    @ApiOperation(value = "版本更新")
    @ResponseBody
    public ResultDTO checkUpdate(@RequestBody VersionDTO sysVersionDTO) {
        String appCode = AppTypeEnum.SQB.getCode();
        sysVersionDTO.setAppCode(appCode);
        ResultDTO result = conmmService.queryLastVersionInfo(sysVersionDTO);
        return result;
    }

    @RequestMapping(value = "/getProtocol")
    @ApiOperation(value = "用户协议")
    @ResponseBody
    public ResultDTO getProtocol(@RequestBody ProtocolDTO protocolDTO) {
        ResultDTO result = conmmService.getProtocol(protocolDTO);
        return result;
    }

    @RequestMapping(value = "/discovery")
    @ApiOperation(value = "发现页面")
    @ResponseBody
    public ResultDTO discovery(@RequestBody DiscoveryJO discoveryJO) {
        // 1.安卓  2.IOS            version版本号 1.0.0
        if (discoveryJO.getDeviceType() == 2) {
            return success(env.getProperty(ApiConstant.THIS_IOS_URL));
        }
        return success(env.getProperty(ApiConstant.THIS_ANDROID_URL));  
     }
    
    //反馈
    @RequestMapping(value = "/suggest")
    @ApiOperation(value = "反馈页面")
    @ResponseBody
    public ResultDTO suggest(@RequestBody SuggestDTO suggestDTO) {
        ResultDTO result = conmmService.suggest(suggestDTO);
         return result;  
     }
}









