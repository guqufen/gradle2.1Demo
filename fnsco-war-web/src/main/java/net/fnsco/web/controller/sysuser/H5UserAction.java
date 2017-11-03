/**
 * 
 */
package net.fnsco.web.controller.sysuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.api.appuser.ConmmService;
import net.fnsco.order.api.constant.ConstantEnum.AppTypeEnum;
import net.fnsco.order.api.dto.AppUserDTO;
import net.fnsco.order.api.dto.VersionDTO;
import net.fnsco.order.api.merchant.IntegralRuleLogService;
import net.fnsco.order.service.domain.IntegralRuleLog;

/**@desc 后台管理系统登录控制器
 * @author tangliang
 * @date 2017年6月20日 下午3:11:23
 */
@Controller
@RequestMapping(value = "/h5/user", method = RequestMethod.POST)
public class H5UserAction extends BaseController {

    @Autowired
    private AppUserService         appUserService;

    @Autowired
    private IntegralRuleLogService integralRuleLogService;

    @Autowired
    private ConmmService           conmmService;

    @RequestMapping(value = "/register")
    @ApiOperation(value = "用户注册")
    @ResponseBody
    public ResultDTO register(@RequestBody AppUserDTO appUserDTO) {
        ResultDTO result = appUserService.insertSelective(appUserDTO);
        if (!Strings.isNullOrEmpty(appUserDTO.getEntityInnerCode())) {
            try {
                integralRuleLogService.insert(appUserDTO.getEntityInnerCode(), IntegralRuleLog.IntegralTypeEnum.CODE_YQ02.getCode());
            } catch (Exception ex) {
                logger.error("邀请商户增加积分报错", ex);
            }
        } else {
            logger.info(appUserDTO.getMobile() + "该手机号注册没有实体商户邀请!");
        }
        return result;
    }

    //获取验证码
    @ResponseBody
    @RequestMapping(value = "/getValidateCode")
    @ApiOperation(value = "获取验证码")
    public ResultDTO getValidateCode(@RequestBody AppUserDTO appUserDTO) {
        ResultDTO result = appUserService.getValidateCode(appUserDTO);
        return result;
    }

    /**
     * 
     * checkUpdate:(获取最新版本)
     *
     * @param sysVersionDTO
     * @return   ResultDTO    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping(value = "/getLastVersion", method = RequestMethod.GET)
    @ApiOperation(value = "获取最新版本")
    @ResponseBody
    public ResultDTO getLastVersion() {
        String appCode = AppTypeEnum.LKL.getCode();
        VersionDTO sysVersionDTO = new VersionDTO();
        sysVersionDTO.setAppCode(appCode);
        sysVersionDTO.setAppType(1);
        sysVersionDTO.setVersion("0.0.0");
        ResultDTO result = conmmService.queryLastVersionInfo(sysVersionDTO);
        return result;
    }
}
