package net.fnsco.controller.app.cofig;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import io.swagger.annotations.ApiOperation;
import net.fnsco.api.config.SysConfigService;
import net.fnsco.api.constant.ApiConstant;
import net.fnsco.api.dto.AppConfigDTO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;

/**
 * @desc 获取配置信息的接口
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月2日 下午3:10:08
 */
@RestController
@RequestMapping(value = "/app/config", method = RequestMethod.POST)
public class AppConfigController extends BaseController {
    
    @Autowired
    private SysConfigService sysConfigService;
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
