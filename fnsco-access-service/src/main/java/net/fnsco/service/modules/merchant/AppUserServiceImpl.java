package net.fnsco.service.modules.merchant;
import java.util.HashMap;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;

import net.fnsco.api.constant.ApiConstant;
import net.fnsco.api.dto.AppUserDTO;
import net.fnsco.api.dto.SmsCodeDTO;
import net.fnsco.api.merchant.AppUserService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.sms.JavaSmsApi;
import net.fnsco.freamwork.comm.Md5Util;
import net.fnsco.service.dao.master.AppUserDao;
import net.fnsco.service.domain.AppUser;

@Service
public class AppUserServiceImpl  extends BaseService implements AppUserService{
	
	private static final Logger logger = LoggerFactory.getLogger(AppUserServiceImpl.class);
  			
	private static Map<String, SmsCodeDTO> MsgCodeMap = new HashMap<>();//存放验证码的
	//private static Map<String,Integer> LoginTimeMap=new HashMap<>();//存放登录次数的
	
	@Autowired
	private AppUserDao MappUserDao;
	//注册
	@Override
	@Transactional
	public ResultDTO insertSelective(AppUserDTO appUserDTO){
	    Map<String, Integer> map = new HashMap<>();
		//非空判断
        if(Strings.isNullOrEmpty(appUserDTO.getMobile())){
            return ResultDTO.fail(ApiConstant.E_APP_PHONE_EMPTY);
        }else if(Strings.isNullOrEmpty(appUserDTO.getPassword())){
            return ResultDTO.fail(ApiConstant.E_APP_PASSWORD_EMPTY);
        }if(Strings.isNullOrEmpty(appUserDTO.getCode())){
            return ResultDTO.fail(ApiConstant.E_APP_CODE_EMPTY);
        }
		//对比验证码
		ResultDTO<String> res=validateCode(appUserDTO.getDeviceId(),appUserDTO.getCode(),appUserDTO.getMobile());
		if(!res.isSuccess()){
		    return res;
		}
		//判断是否已经注册
		if(MappUserDao.selectAppUserByMobile(appUserDTO.getMobile())!=null){
		    return ResultDTO.fail(ApiConstant.E_ALREADY_LOGIN);
		}
		AppUser appUser=new AppUser();
		appUser.setDeviceId(appUserDTO.getDeviceId());
		appUser.setDeviceToken(appUserDTO.getDeviceToken());
		appUser.setMobile(appUserDTO.getMobile());
		appUser.setDeviceType(appUserDTO.getDeviceType());
		String password=Md5Util.getInstance().md5(appUserDTO.getPassword());
		appUser.setPassword(password);
		if(!MappUserDao.insertSelective(appUser)){
		    return ResultDTO.fail(ApiConstant.E_REGISTER_ERROR);
		}
		appUser=MappUserDao.selectAppUserByMobile(appUser.getMobile());
		map.put("appUserId",appUser.getId());
		return ResultDTO.success(map);
	}


	//生产验证码
	@Override
	public void getValidateCode(AppUserDTO appUserDTO ) {
		String deviceId=appUserDTO.getDeviceId();
		final String mobile=appUserDTO.getMobile();
		// 生成6位验证码
        final String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        SmsCodeDTO object=new SmsCodeDTO(code,System.currentTimeMillis());
        MsgCodeMap.put(mobile+deviceId,object);
         /**
         * 开启线程发送手机验证码
         */
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    String callback = JavaSmsApi.Code(mobile, code);
                    JSONObject callbackJson = (JSONObject) JSONObject.parse(callback);
                    String resultCode = callbackJson.getString("code");
                    if ("0".equals(resultCode)) {
                        logger.warn("验证码" + code + "已经发送至手机号" + mobile + "返回详情" + callback);
                    } else {
                        logger.warn("验证码" + code + "未能够发送至手机" + mobile + "错误code" + resultCode + ";错误详情" + callback);
                    }
                } catch (Exception e) {
                    logger.warn("验证码" + code + "未能够发送至手机" + mobile + "错误原因:异常错误");
                    logger.error("短信发送异常 ",e);
                }
            }
        }).start();
	}

	//验证码对比
	private ResultDTO validateCode(String deviceId, String code,String mobile) {
	   //非空判断
        if(Strings.isNullOrEmpty(deviceId)){
            return ResultDTO.fail(ApiConstant.E_APP_DEVICETYPE_EMPTY);
        }else if(Strings.isNullOrEmpty(code)){
            return ResultDTO.fail(ApiConstant.E_APP_CODE_EMPTY);
        }if(Strings.isNullOrEmpty(mobile)){
            return ResultDTO.fail(ApiConstant.E_APP_PHONE_EMPTY);
        }
		//从Map中根据手机号取到存入的验证码
		SmsCodeDTO codeDto= MsgCodeMap.get(mobile+deviceId);
		//时间
		long newTime = System.currentTimeMillis();
		//验证码超过30分钟
		if((newTime-codeDto.getTime())/1000/60>30){
			MsgCodeMap.remove(mobile+deviceId);
			return ResultDTO.fail(ApiConstant.E_CODEOVERTIME_ERROR);
		}
		
		if(!code.equals(codeDto.getCode())){
			return ResultDTO.fail(ApiConstant.E_APP_CODE_ERROR);
		}
		//从map从移除验证码
		MsgCodeMap.remove(mobile+deviceId);
		return ResultDTO.success();
	}

	//根据手机号找回登录密码
	@Override
	@Transactional
	public ResultDTO findPassword(AppUserDTO appUserDTO) {
		//非空判断
        if(Strings.isNullOrEmpty(appUserDTO.getDeviceId())){
            return ResultDTO.fail(ApiConstant.E_APP_DEVICEIDNULL);
        }else if(Strings.isNullOrEmpty(appUserDTO.getPassword())){
            return ResultDTO.fail(ApiConstant.E_APP_PASSWORD_EMPTY);
        }if(Strings.isNullOrEmpty(appUserDTO.getCode())){
            return ResultDTO.fail(ApiConstant.E_APP_CODE_EMPTY);
        }
		String password=Md5Util.getInstance().md5(appUserDTO.getPassword());
		//对比验证码
		ResultDTO res=validateCode(appUserDTO.getDeviceId(),appUserDTO.getCode(),appUserDTO.getMobile());
		if(!res.isSuccess()){
		    return res;
		}
		//密码更新失败
		if(!MappUserDao.updatePasswordByPhone(appUserDTO.getMobile(),password)){
		    return ResultDTO.fail(ApiConstant.E_UPDATEPASSWORD_ERROR);
		}
		return ResultDTO.success();
	}

	//修改密码
	@Override
	@Transactional
	public ResultDTO<String> modifyPassword(AppUserDTO appUserDTO){
	    Integer id=appUserDTO.getId();
	    //非空判断
	    if(id==null){
	        return ResultDTO.fail(ApiConstant.E_APP_ID_EMPTY);
	    }
        if(Strings.isNullOrEmpty(appUserDTO.getPassword())){
            return ResultDTO.fail(ApiConstant.E_APP_PASSWORD_EMPTY);
        }else if(Strings.isNullOrEmpty(appUserDTO.getOldPassword())){
            return ResultDTO.fail(ApiConstant.E_APP_PASSWORD_EMPTY);
        }
		String password=Md5Util.getInstance().md5(appUserDTO.getPassword());
		String oldPassword=Md5Util.getInstance().md5(appUserDTO.getOldPassword());
		AppUser appUser=new AppUser();
		//根据id查询用户是否存在获取原密码
		 AppUser mAppUser=MappUserDao.selectAppUserById(id);
		//查到的密码和原密码做比较
		if(!oldPassword.equals(mAppUser.getPassword())){
		    return ResultDTO.fail(ApiConstant.E_OLDPASSWORD_ERROR);
		}
		appUser.setPassword(password);
		appUser.setId(id);
		if(!MappUserDao.updateByPrimaryKeySelective(appUser)){
		    return ResultDTO.fail(ApiConstant.E_UPDATEPASSWORD_ERROR);
		}
		return ResultDTO.success();
	}
	
	//根据手机号码和密码登录
	@Override
	public ResultDTO loginByMoblie(AppUserDTO appUserDTO){
	    Map<String, Integer> map = new HashMap<>();
		//非空判断
        if(Strings.isNullOrEmpty(appUserDTO.getMobile())){
            return ResultDTO.fail(ApiConstant.E_APP_PHONE_EMPTY);
        }else if(Strings.isNullOrEmpty(appUserDTO.getPassword())){
            return ResultDTO.fail(ApiConstant.E_APP_PASSWORD_EMPTY);
        }
		String password=Md5Util.getInstance().md5(appUserDTO.getPassword());
		AppUser appUser=MappUserDao.selectAppUserByMobile(appUserDTO.getMobile());
		if(!password.equals(appUser.getPassword())){
			return ResultDTO.fail(ApiConstant.E_APP_PASSWORD_ERROR);
		}
		map.put("appUserId",appUser.getId());
		return ResultDTO.success(map);
	}

}









