package net.fnsco.web.controller.e789.common;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.appuser.ConmmService;
import net.fnsco.order.api.config.SysConfigService;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.constant.ConstantEnum.AppTypeEnum;
import net.fnsco.order.api.dto.AppConfigDTO;
import net.fnsco.order.api.dto.ProtocolDTO;
import net.fnsco.order.api.dto.SuggestDTO;
import net.fnsco.order.api.dto.VersionDTO;
import net.fnsco.order.api.dto.VersionResultDTO;
import net.fnsco.web.controller.e789.jo.DiscoveryJO;

@RestController
@RequestMapping(value = "/app2c/user", method = RequestMethod.POST)
@Api(value = "/app2c/user", tags = { "我的-APP常用信息管理接口" })
public class CommonController extends BaseController {
    @Autowired
    private ConmmService           conmmService;
    @Autowired
    private Environment            env;
    @Autowired
    private SysConfigService sysConfigService;

    @RequestMapping(value = "/checkUpdate")
    @ApiOperation(value = "我的-设置-关于我们-版本更新")
    @ResponseBody
    public ResultDTO<VersionResultDTO> checkUpdate(@RequestBody VersionDTO sysVersionDTO) {
        String appCode = AppTypeEnum.E789.getCode();
        sysVersionDTO.setAppCode(appCode);
        ResultDTO<VersionResultDTO> result = conmmService.queryLastVersionInfo(sysVersionDTO);
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
    @ApiOperation(value = "我的-帮助与反馈-反馈页面")
    @ResponseBody
    public ResultDTO<String> suggest(@RequestBody SuggestDTO suggestDTO) {
        ResultDTO result = conmmService.suggest(suggestDTO);
        return result;
    }
    
    /**
     * getValueByName:(这里用一句话描述这个方法的作用)获取配置地址信息
     *
     * @return    设定文件
     * @return ResultDTO<String>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping("/getConfigValue")
    @ApiOperation(value = "获取配置地址链接信息")
    public ResultDTO<String> getValueByName(@RequestBody AppConfigDTO appConfigDTO){
        if(null == appConfigDTO.getUserId()){
            return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
        }
        if(Strings.isNullOrEmpty(appConfigDTO.getType())){
            return ResultDTO.fail(ApiConstant.E_CONFIG_NAME_NULL);
        }
        String url = sysConfigService.getValueUrl(appConfigDTO);
        Map<String,String> data = Maps.newHashMap();
        data.put("configUrl", url);
        return ResultDTO.success(data);
    }

}
