package net.fnsco.web.controller.e789.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.api.dto.AppUserDTO;
import net.fnsco.order.api.dto.AppUserLoginInfoDTO;
import net.fnsco.web.controller.e789.jo.CommonJO;
import net.fnsco.web.controller.e789.jo.FindPasswordJO;
import net.fnsco.web.controller.e789.jo.GetValidateCodeJO;
import net.fnsco.web.controller.e789.jo.LoginJO;
import net.fnsco.web.controller.e789.jo.RegisterJO;
import net.fnsco.web.controller.e789.vo.LoginVO;

/**
 * @author   hjt
 * @version  
 * @since    Ver 1.1
 * @Date	 2017 2017年12月4日 上午10:04:54
 *
 */
@RestController
@RequestMapping(value = "/app2c/user", method = RequestMethod.POST)
@Api(value = "/app2c/user", tags = { "登陆-App用户管理接口" })
public class AppUserController extends BaseController {
    @Autowired
    private AppUserService        appUserService;

    @RequestMapping(value = "/register")
    @ApiOperation(value = "注册页-用户注册")
    @ResponseBody
    public ResultDTO<LoginVO> register(@RequestBody RegisterJO registerJO) {
    	AppUserDTO appUserDTO = new AppUserDTO();
    	appUserDTO.setCode(registerJO.getCode());
    	appUserDTO.setDeviceId(registerJO.getDeviceId());
    	appUserDTO.setDeviceToken(registerJO.getDeviceToken());
    	appUserDTO.setDeviceType(registerJO.getDeviceType());
    	appUserDTO.setMobile(registerJO.getMobile());
    	appUserDTO.setPassword(registerJO.getPassword());
        ResultDTO result = appUserService.e789InsertSelective(appUserDTO);
        if(!result.isSuccess()) {
        	return result.fail();
        }
        AppUserLoginInfoDTO appUserLoginInfoDTO = appUserService.getLoginInfor(appUserDTO);
        LoginVO loginVO = new LoginVO();
        loginVO.setHeadImagePath(appUserLoginInfoDTO.getHeadImagePath());
        loginVO.setUserId(appUserLoginInfoDTO.getUserId());
        loginVO.setMobile(appUserLoginInfoDTO.getMoblie());
        int num = appUserLoginInfoDTO.getMerchantNums();
        if(num==0) {
        	loginVO.setIsMerchant(false);
        }else {
        	loginVO.setIsMerchant(true);
        }
        if(Strings.isNullOrEmpty(appUserLoginInfoDTO.getPayPassword())) {
        	loginVO.setHasPayPassword(false);
        }else {
        	loginVO.setHasPayPassword(true);
        }
        loginVO.setUnReadMsgIds(appUserLoginInfoDTO.getUnReadMsgIds());
        return ResultDTO.success(loginVO);
    }

    //获取验证码
    @ResponseBody
    @RequestMapping(value = "/getValidateCode")
    @ApiOperation(value = "获取验证码")
    public ResultDTO getValidateCode(@RequestBody GetValidateCodeJO getValidateCodeJO) {
    	AppUserDTO appUserDTO = new AppUserDTO();
    	appUserDTO.setDeviceId(getValidateCodeJO.getDeviceId());
    	appUserDTO.setMobile(getValidateCodeJO.getMobile());
        ResultDTO result = appUserService.getValidateCode(appUserDTO);
        return result;
    }


    //根据手机号码找回密码
    @ResponseBody
    @RequestMapping(value = "/findPassword")
    @ApiOperation(value = "注册页-找回密码")
    public ResultDTO<LoginVO> findPassword(@RequestBody FindPasswordJO findPasswordJO) {
    	AppUserDTO appUserDTO = new AppUserDTO();
    	appUserDTO.setCode(findPasswordJO.getCode());
    	appUserDTO.setDeviceId(findPasswordJO.getDeviceId());
    	appUserDTO.setMobile(findPasswordJO.getMobile());
    	appUserDTO.setPassword(findPasswordJO.getPassword());
        ResultDTO<String> result = appUserService.findPassword(appUserDTO);
        if(!result.isSuccess()) {
        	return result.fail();
        }
        AppUserLoginInfoDTO appUserLoginInfoDTO = appUserService.getLoginInfor(appUserDTO);
        LoginVO loginVO = new LoginVO();
        loginVO.setHeadImagePath(appUserLoginInfoDTO.getHeadImagePath());
        loginVO.setUserId(appUserLoginInfoDTO.getUserId());
        loginVO.setMobile(appUserLoginInfoDTO.getMoblie());
        int num = appUserLoginInfoDTO.getMerchantNums();
        if(num==0) {
        	loginVO.setIsMerchant(false);
        }else {
        	loginVO.setIsMerchant(true);
        }
        if(Strings.isNullOrEmpty(appUserLoginInfoDTO.getPayPassword())) {
        	loginVO.setHasPayPassword(false);
        }else {
        	loginVO.setHasPayPassword(true);
        }
        loginVO.setUnReadMsgIds(appUserLoginInfoDTO.getUnReadMsgIds());
        return ResultDTO.success(loginVO);
    }

    //登录
    @ResponseBody
    @RequestMapping(value = "/login")
    @ApiOperation(value = "用户登录")
    public ResultDTO<LoginVO> login(@RequestBody LoginJO loginJO) {
    	AppUserDTO appUserDTO = new AppUserDTO();
    	appUserDTO.setDeviceId(loginJO.getDeviceId());
    	appUserDTO.setDeviceType(loginJO.getDeviceType());
    	appUserDTO.setDeviceToken(loginJO.getDeviceToken());
    	appUserDTO.setMobile(loginJO.getMobile());
    	appUserDTO.setPassword(loginJO.getPassword());
        ResultDTO<String> result = appUserService.e789LoginByMoblie(appUserDTO);
        if(!result.isSuccess()) {
        	return result.fail();
        }
        AppUserLoginInfoDTO appUserLoginInfoDTO = appUserService.getLoginInfor(appUserDTO);
        LoginVO loginVO = new LoginVO();
        loginVO.setHeadImagePath(appUserLoginInfoDTO.getHeadImagePath());
        loginVO.setUserId(appUserLoginInfoDTO.getUserId());
        loginVO.setUserName(appUserLoginInfoDTO.getUserName());
        loginVO.setMobile(appUserLoginInfoDTO.getMoblie());
        int num = appUserLoginInfoDTO.getMerchantNums();
        if(num==0) {
        	loginVO.setIsMerchant(false);
        }else {
        	loginVO.setIsMerchant(true);
        }
        if(Strings.isNullOrEmpty(appUserLoginInfoDTO.getPayPassword())) {
        	loginVO.setHasPayPassword(false);
        }else {
        	loginVO.setHasPayPassword(true);
        }
        loginVO.setUnReadMsgIds(appUserLoginInfoDTO.getUnReadMsgIds());
        return ResultDTO.success(loginVO);
    }

    //退出登录
    @ResponseBody
    @RequestMapping(value = "/loginOut")
    @ApiOperation(value = "退出登录")
    public ResultDTO<String> loginOut(@RequestBody CommonJO commonJO) {
    	AppUserDTO appUserDTO = new AppUserDTO();
    	appUserDTO.setUserId(commonJO.getUserId());
        ResultDTO<String> result = appUserService.loginOut(appUserDTO);
        return result;
    }

}
