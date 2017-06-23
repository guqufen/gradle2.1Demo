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
//	@ApiOperation(value = "用户注册")
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
		 //如果错误返回错误信息
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
	public ResultDTO getValidateCode(@RequestParam(value = "mobile", required = true) String mobile, @RequestParam(value = "deviceId", required = true) String deviceId){
		 //String mobile=request.getParameter("mobile");
		// String password=request.getParameter("password");
		 //String deviceId=request.getParameter("deviceId");
		 int deviceType= 0 ;//Integer.parseInt(request.getParameter("deviceType"));
		 ResultDTO<String> result=mAppUserService.getValidateCode(deviceId, deviceType, mobile);
		 return result;
	}
				
	//修改密码     旧密码和新密码
	@RequestMapping(value = "/modify")
	@ResponseBody
	public ResultDTO<AppUser> modify(){
		//创建实体对象
		ResultDTO<AppUser> result = new ResultDTO<>();
		//如果密码为空
		String oldPassword=request.getParameter("oldPassword");
		String mobile=request.getParameter("mobile");
		String password=request.getParameter("password");
		if(StringUtils.isEmpty(oldPassword)){
			 return result.setError("密码不能为空");
		 }
		result=mAppUserService.modify(mobile,password,oldPassword);
		return result;
	}
	
	//根据手机号码找回密码
		@ResponseBody
		@RequestMapping(value = "/getValidateCode", method = RequestMethod.GET)
		@ApiOperation(value = "根据手机号码找回密码")
		public ResultDTO findPassword(@RequestParam(value = "mobile", required = true) String mobile, @RequestParam(value = "deviceId", required = true) String deviceId){
			 int deviceType= 0 ;//Integer.parseInt(request.getParameter("deviceType"));
			 ResultDTO<String> result=mAppUserService.findPassword(deviceId,mobile);
			 
			 return result;
		}
	
}
















