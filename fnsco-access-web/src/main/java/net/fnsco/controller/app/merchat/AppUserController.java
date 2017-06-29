package net.fnsco.controller.app.merchat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.api.dto.AppUserDTO;
import net.fnsco.api.merchant.AppUserService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.domain.AppUser;
/**
 * @author   zhoujincheng
 * @version  
 * @since    Ver 1.1
 * @Date	 2017 2017年6月28日 下午3:44:54
 *
 */
@RestController
@RequestMapping(value="/app/user",method = RequestMethod.POST)
public class AppUserController extends BaseController{
	@Autowired
	private AppUserService appUserService;
	
	@RequestMapping(value = "/register")
	@ApiOperation(value = "用户注册")
	@ResponseBody
	public ResultDTO<AppUser> register(@RequestBody AppUserDTO appUserDTO){
		 ResultDTO<AppUser> result=appUserService.insertAppUser(appUserDTO);
		 return result;
	}
	
	//获取验证码
	@ResponseBody
	@RequestMapping(value = "/getValidateCode")
	@ApiOperation(value = "获取验证码")
	public ResultDTO getValidateCode(@RequestBody AppUserDTO appUserDTO){
		 appUserService.getValidateCode(appUserDTO);
		 return success();
	}
				
	//修改密码     旧密码和新密码
	@RequestMapping(value = "/modifyPassword")
	@ResponseBody
	@ApiOperation(value = "修改密码")
	public ResultDTO<String> modifyPassword(@RequestBody AppUserDTO appUserDTO){
		ResultDTO<String> result = new ResultDTO<>();
		result=appUserService.modifyPassword(appUserDTO);
		return result;
	}
	
	//根据手机号码找回密码
		@ResponseBody
		@RequestMapping(value = "/findPasswordByPhone")
		@ApiOperation(value = "找回密码")
		public ResultDTO<String> findPasswordByPhone(@RequestBody AppUserDTO appUserDTO){
			ResultDTO<String> result=appUserService.findPassword(appUserDTO);
			return result;
		}
		
		//登录
		@ResponseBody
		@RequestMapping(value = "/login")
		@ApiOperation(value = "用户登录")
		public ResultDTO<String> login(@RequestBody AppUserDTO appUserDTO){
			ResultDTO<String> result=appUserService.loginByMoblie(appUserDTO);
			return result;
		}
		
	
}
















