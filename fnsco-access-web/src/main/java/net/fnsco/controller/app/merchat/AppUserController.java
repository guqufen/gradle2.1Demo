package net.fnsco.controller.app.merchat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.fnsco.api.merchant.AppUserService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.domain.AppUser;

@RestController
@RequestMapping("/app/user")
public class AppUserController extends BaseController{
	@Autowired
	private AppUserService mAppUserService;
	
	@RequestMapping(value = "/register")
	@ApiOperation(value = "用户注册")
	@ResponseBody
	public ResultDTO register(){
		 String mobile=request.getParameter("mobile");
		 String password=request.getParameter("password");
		 String deviceId=request.getParameter("deviceId");
		 String deviceToken=request.getParameter("deviceToken");
		 int deviceType=Integer.parseInt(request.getParameter("deviceType"));
		 String code=request.getParameter("code");
		//创建实体对象
		 AppUser appUser=new AppUser();
		 appUser.setMobile(mobile);
		 appUser.setPassword(password);
		 appUser.setDeviceId(deviceId);
		 appUser.setDeviceType(deviceType);
		 appUser.setDeviceToken(deviceToken);
		 //对比验证码
		 ResultDTO<String> regResult=mAppUserService.validateCode(mobile, code);
		 //如果错误返回错误信息   如果正确不用返回信息
		 if(!regResult.isSuccess()){
			 return regResult;
		 }
		 //验证码对比正确执行注册逻辑
		 ResultDTO<AppUser> result=mAppUserService.insertAppUser(appUser);
		 return result;
	}
	
	//获取验证码
	@ResponseBody
	@RequestMapping(value = "/getValidateCode", method = RequestMethod.GET)
	@ApiOperation(value = "获取验证码")
	public ResultDTO<String> getValidateCode(@RequestParam(value = "mobile", required = true) String mobile, @RequestParam(value = "deviceId", required = true) String deviceId, @RequestParam(value = "deviceType", required = true) int deviceType){
		 ResultDTO<String> result=mAppUserService.getValidateCode(deviceId, deviceType, mobile);
		 return result;
	}
				
	//修改密码     旧密码和新密码
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "修改密码")
	public ResultDTO<AppUser> modify(@RequestParam(value = "mobile", required = true) String mobile,@RequestParam(value = "oldPassword", required = true) String oldPassword,@RequestParam(value = "password", required = true) String password){
		ResultDTO<AppUser> result = new ResultDTO<>();
		//如果密码为空
		if(StringUtils.isEmpty(oldPassword)){
			 return result.setError("密码不能为空");
		 }
		result=mAppUserService.modify(mobile,password,oldPassword);
		return result;
	}
	
		//根据手机号码找回密码
		@ResponseBody
		@RequestMapping(value = "/findPassword",method = RequestMethod.GET)
		@ApiOperation(value = "找回密码")
		public ResultDTO<String> findPassword(@RequestParam(value = "mobile", required = true) String mobile, @RequestParam(value = "password", required = true) String password, @RequestParam(value = "code", required = true) String code){
			//对比验证码
			ResultDTO<String> regResult=mAppUserService.validateCode(mobile, code);
			//如果错误返回错误信息
			 if(!regResult.isSuccess()){
				 return regResult;
			 }
			ResultDTO<String> result=mAppUserService.findPassword(mobile,code,password);
			return result;
		}
		
		//登录
		@ResponseBody
		@RequestMapping(value = "/login",method = RequestMethod.GET)
		@ApiOperation(value = "用户登录")
		public ResultDTO<String> login(@RequestParam(value = "mobile", required = true) String mobile,@RequestParam(value = "password", required = true) String password){
			ResultDTO<String> result=mAppUserService.loginByMoblie(mobile, password);
			return result;
		}
		
	
}
















