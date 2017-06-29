package net.fnsco.service.modules.merchant;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;

import net.fnsco.api.dto.AppUserDTO;
import net.fnsco.api.dto.SmsCodeDTO;
import net.fnsco.api.merchant.AppUserService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.constants.CoreConstants;
import net.fnsco.core.sms.JavaSmsApi;
import net.fnsco.freamwork.comm.Md5Util;
import net.fnsco.service.dao.master.AppUserDao;
import net.fnsco.service.domain.AppUser;

@Service
public class AppUserServiceImpl  extends BaseService implements AppUserService{
	
	private static final Logger logger = LoggerFactory.getLogger(AppUserServiceImpl.class);
  			
	private static Map<String, SmsCodeDTO> MsgCodeMap = new HashMap<>();//存放验证码的
	
	@Autowired
	private AppUserDao MappUserDao;
	//注册
	@Override
	public ResultDTO<AppUser> insertAppUser(AppUserDTO appUserDTO){
		//数据库实体类
		ResultDTO<AppUser> result=new ResultDTO<AppUser>();
		//非空判断
        if(Strings.isNullOrEmpty(appUserDTO.getMobile())||Strings.isNullOrEmpty(appUserDTO.getPassword())||Strings.isNullOrEmpty(appUserDTO.getCode())){
            return result.setError(CoreConstants.E_COMM_BUSSICSS, "手机号或密码或验证码为空");
        }
		//对比验证码
		ResultDTO<String> res=validateCode(appUserDTO.getDeviceId(),appUserDTO.getCode());
		if(!res.isSuccess()){
		    return result.setError(CoreConstants.E_COMM_BUSSICSS, "验证码错误");
		}
		//判断是否已经注册
		if(MappUserDao.getAppUserByMobile(appUserDTO.getMobile())!=null){
			return result.setError(CoreConstants.E_COMM_BUSSICSS,"该用户已经注册");
		}
		AppUser appUser=new AppUser();
		appUser.setDeviceId(appUserDTO.getDeviceId());
		appUser.setDeviceToken(appUserDTO.getDeviceToken());
		appUser.setMobile(appUserDTO.getMobile());
		appUser.setDeviceType(appUserDTO.getDeviceType());
		String password=Md5Util.getInstance().md5(appUserDTO.getPassword());
		appUser.setPassword(password);
		if(!MappUserDao.insertSelective(appUser)){
		    return result.setError(CoreConstants.E_COMM_BUSSICSS,"注册失败");
		}
		result.setCode("0");
		result.setSuccess("注册成功");
		return result;
	}


	//生产验证码
	@Override
	public void getValidateCode(AppUserDTO appUserDTO ) {
		//String deviceId, int deviceType, String mobile
		String deviceId=appUserDTO.getDeviceId();
		String mobile=appUserDTO.getMobile();
		// 生成6位验证码
        final String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        //               key    value
        SmsCodeDTO object=new SmsCodeDTO(code,System.currentTimeMillis());
        MsgCodeMap.put(deviceId,object);
         /**
         * 开启线程发送手机验证码
         */
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    String callback = JavaSmsApi.Code(mobile, code);
                    JSONObject callback_json = (JSONObject) JSONObject.parse(callback);
                    String resultCode = callback_json.getString("code");
                    //String msg = callback_json.getString("msg");
                    if ("0".equals(resultCode)) {
                        logger.warn("验证码" + code + "已经发送至手机号" + mobile + "返回详情" + callback);
                    } else {
                        logger.warn("验证码" + code + "未能够发送至手机" + mobile + "错误code" + resultCode + ";错误详情" + callback);
                    }
                } catch (IOException | URISyntaxException e) {
                    logger.warn("验证码" + code + "未能够发送至手机" + mobile + "错误原因:异常错误");
                    logger.error("短信发送异常 ",e);
                }
            }
        }).start();
	}

	//验证码对比
	private ResultDTO<String> validateCode(String deviceId, String code) {
		ResultDTO<String> result = new ResultDTO<>();
		//从Map中根据手机号取到存入的验证码
		SmsCodeDTO codeDto= MsgCodeMap.get(deviceId);
		//时间
		long newTime = System.currentTimeMillis();
		//验证码超过30分钟
		if((newTime-codeDto.getTime())/1000/60>30){
			MsgCodeMap.remove(deviceId);
			return result.setError(CoreConstants.E_COMM_BUSSICSS, "验证码超过有效时间");
		}
		
		if(!code.equals(codeDto.getCode())){
			return result.setError(CoreConstants.E_COMM_BUSSICSS, "验证码错误");
		}
		//从map从移除验证码
		MsgCodeMap.remove(deviceId);
		result.setCode("0");
		result.setSuccess("成功");
		return result;
	}

	//根据手机号找回登录密码
	@Override
	public ResultDTO<String> findPassword(AppUserDTO appUserDTO) {
		ResultDTO<String> result = new ResultDTO<>();
		//非空判断
        if(Strings.isNullOrEmpty(appUserDTO.getDeviceId())||Strings.isNullOrEmpty(appUserDTO.getPassword())||Strings.isNullOrEmpty(appUserDTO.getCode())){
            return result.setError(CoreConstants.E_COMM_BUSSICSS, "手机号或验证码或密码为空");
        }
		String password=Md5Util.getInstance().md5(appUserDTO.getPassword());
		//对比验证码
		ResultDTO<String> res=validateCode(appUserDTO.getDeviceId(),appUserDTO.getCode());
		if(!res.isSuccess()){
		    return result.setError(CoreConstants.E_COMM_BUSSICSS, "验证码错误");
		}
		//密码更新失败
		if(!MappUserDao.findPasswordByPhone(appUserDTO.getMobile(),password)){
		    return  result.setError(CoreConstants.E_COMM_BUSSICSS, "修改密码失败");
		}
		result.setCode("0");
        result.setSuccess("修改密码成功");
		return result;
	}

	//修改密码
	@Override
	public ResultDTO<String> modifyPassword(AppUserDTO appUserDTO){
	    ResultDTO<String> result=new ResultDTO<String>();
	    Integer id=appUserDTO.getId();
	    //非空判断
	    if(id==null){
	        return result.setError(CoreConstants.E_COMM_BUSSICSS, "id为空");
	    }
        if(Strings.isNullOrEmpty(appUserDTO.getPassword())||Strings.isNullOrEmpty(appUserDTO.getOldPassword())){
            return result.setError(CoreConstants.E_COMM_BUSSICSS, "手机号或密码为空");
        }
		String password=Md5Util.getInstance().md5(appUserDTO.getPassword());
		String oldPassword=Md5Util.getInstance().md5(appUserDTO.getOldPassword());
		AppUser appUser=new AppUser();
		//根据id查询用户是否存在获取原密码
		 AppUser mAppUser=MappUserDao.selectById(id);
		//查到的密码和原密码做比较
		if(!oldPassword.equals(mAppUser.getPassword())){
			return result.setError(CoreConstants.E_COMM_BUSSICSS,"原密码输入错误,请重新输入");
		}
		appUser.setPassword(password);
		appUser.setId(id);
		if(!MappUserDao.updateById(appUser)){
		    return result.setError(CoreConstants.E_COMM_BUSSICSS, "修改密码失败");
		}
		result.setCode("0");
		result.setSuccess("修改密码成功");
		return result;
	}
	
	//根据手机号码和密码登录
	@Override
	public ResultDTO<String> loginByMoblie(AppUserDTO appUserDTO){
		ResultDTO<String> result = new ResultDTO<>();
		//非空判断
        if(Strings.isNullOrEmpty(appUserDTO.getMobile())||Strings.isNullOrEmpty(appUserDTO.getPassword())){
            return result.setError(CoreConstants.E_COMM_BUSSICSS, "手机号或密码为空");
        }
		String password=Md5Util.getInstance().md5(appUserDTO.getPassword());
		AppUser appUser=MappUserDao.getAppUserByMobile(appUserDTO.getMobile());
		if(!password.equals(appUser.getPassword())){
			return result.setError(CoreConstants.E_COMM_BUSSICSS, "登录失败");
		}
		result.setCode("0");
		result.setSuccess("登录成功");
		return result;
	}

}









