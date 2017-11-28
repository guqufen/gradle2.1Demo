package net.fnsco.web.controller.app.comm;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import io.swagger.annotations.ApiOperation;
import net.fnsco.bigdata.api.constant.BigdataConstant;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.appuser.ConmmService;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.constant.ConstantEnum.AppTypeEnum;
import net.fnsco.order.api.dto.ProtocolDTO;
import net.fnsco.order.api.dto.SuggestDTO;
import net.fnsco.order.api.dto.VersionDTO;
import net.fnsco.order.api.merchant.IntegralLogService;
import net.fnsco.order.service.domain.IntegralLog;
import net.fnsco.web.controller.app.jo.DiscoveryJO;

@RestController
@RequestMapping(value = "/app/user", method = RequestMethod.POST)
public class ConmmController extends BaseController {
    @Autowired
    private ConmmService           conmmService;
    @Autowired
    private Environment            env;
    @Autowired
    private IntegralLogService integralRuleLogService;

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

    //邀新
    @RequestMapping(value = "/getInviteUrl", method = RequestMethod.GET)
    @ApiOperation(value = "返回邀新链接地址")
    @ResponseBody
    public ResultDTO getInviteUrl(@RequestParam String entityInnerCode) {
        
        if(Strings.isNullOrEmpty(entityInnerCode)) {
        	 return ResultDTO.fail(BigdataConstant.APP_MER_ENTITY_INNERCODE_NULL);
        }
      
        String url = env.getProperty("web.base.url") + "/acti/register.html?entityId=" + entityInnerCode;
        Map<String, Object> resultMap = Maps.newHashMap();
        List<IntegralLog> list = integralRuleLogService.queryListByEntityInnerCode(entityInnerCode);
        Integer integral = 0;// 积分值 
        int count = 0;
        if (list != null) {
            count = list.size();
            for (IntegralLog log : list) {
                integral += log.getIntegral();
            }
        }
        resultMap.put("url", url);
        resultMap.put("integral", integral);
        resultMap.put("count", count);
        return ResultDTO.success(resultMap);
    }

}
