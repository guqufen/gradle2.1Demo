/**
 * 
 */
package net.fnsco.web.controller.sysuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beust.jcommander.Strings;

import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.freamwork.comm.Md5Util;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.api.appuser.ConmmService;
import net.fnsco.order.api.constant.ConstantEnum.AppTypeEnum;
import net.fnsco.order.api.dto.AppUserDTO;
import net.fnsco.order.api.dto.VersionDTO;

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
    private ConmmService           conmmService;

    @RequestMapping(value = "/register")
    @ApiOperation(value = "用户注册")
    @ResponseBody
    public ResultDTO register(@RequestBody AppUserDTO appUserDTO) {
        String password = Md5Util.getInstance().pwdEncrypt(appUserDTO.getPassword());
        appUserDTO.setPassword(password);
        ResultDTO result = appUserService.insertSelective(appUserDTO);
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
	String appCode = AppTypeEnum.SQB.getCode();
        VersionDTO sysVersionDTO = new VersionDTO();
        sysVersionDTO.setAppCode(appCode);
        sysVersionDTO.setAppType(1);
        sysVersionDTO.setVersion("0.0.0");
        ResultDTO result = conmmService.queryLastVersionInfo(sysVersionDTO);
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
    @RequestMapping(value = "/getLklLastVersion", method = RequestMethod.GET)
    @ApiOperation(value = "获取拉卡拉最新版本")
    @ResponseBody
    public ResultDTO getLklLastVersion() {
	String appCode = AppTypeEnum.YZF.getCode();
        VersionDTO sysVersionDTO = new VersionDTO();
        sysVersionDTO.setAppCode(appCode);
        sysVersionDTO.setAppType(1);
        sysVersionDTO.setVersion("0.0.0");
        ResultDTO result = conmmService.queryLastVersionInfo(sysVersionDTO);
        return result;
    }
}
