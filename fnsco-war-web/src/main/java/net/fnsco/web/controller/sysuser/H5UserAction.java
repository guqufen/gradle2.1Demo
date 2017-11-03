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

import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.api.dto.AppUserDTO;

/**@desc 后台管理系统登录控制器
 * @author tangliang
 * @date 2017年6月20日 下午3:11:23
 */
@Controller
@RequestMapping(value = "/h5/user", method = RequestMethod.POST)
public class H5UserAction extends BaseController {

    @Autowired
    private AppUserService appUserService;

    @RequestMapping(value = "/register")
    @ApiOperation(value = "用户注册")
    @ResponseBody
    public ResultDTO register(@RequestBody AppUserDTO appUserDTO) {
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

}
