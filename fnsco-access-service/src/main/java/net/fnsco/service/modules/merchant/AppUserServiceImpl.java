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

import net.fnsco.api.merchant.AppUserService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.sms.JavaSmsApi;
import net.fnsco.service.dao.master.AppUserDao;
import net.fnsco.service.domain.AppUser;

@Service
public class AppUserServiceImpl  extends BaseService implements AppUserService{
	
	private static final Logger logger = LoggerFactory.getLogger(AppUserServiceImpl.class);
	
	private static Map<String, String> MsgCodeMap = new HashMap<>();//存放验证码的
	
	@Autowired
	private AppUserDao MappUserDao;
	//注册
	@Override
	public ResultDTO<AppUser> insertAppUser(AppUser appUser) {
		ResultDTO<AppUser> result=new ResultDTO<AppUser>();
		int num=MappUserDao.selectByPrimaryKey(appUser.getMobile());
		if(num!=0){
		      return result.setError("1","该用户已经注册");
		}
		if(MappUserDao.insertSelective(appUser)){
			result.setData(appUser);
			return result.setSuccess("注册成功");
		}
		return result;
	}

	//修改密码
	@Override
	public ResultDTO<AppUser> modify(String mobile,String password,String oldPassword){
		ResultDTO<AppUser> result=new ResultDTO<AppUser>();
		AppUser mAppUser=new AppUser();
		//根据手机号查询用户是否存在获取原密码
		mAppUser=MappUserDao.selectByMobile(mobile);
		//查到的密码和原密码做比较
		if(!oldPassword.equals(mAppUser.getPassword())){
			return result.setError("原密码输入错误,请重新输入");
		}
		mAppUser.setPassword(password);
		mAppUser.setMobile(mobile);
		if(MappUserDao.updateByPrimaryKeySelective(mAppUser)){
			result.setSuccess("修改密码成功");
		}
		return result;
	}

	//生产验证码
	@Override
	public ResultDTO<String> getValidateCode(String deviceId, int deviceType, String mobile) {
		ResultDTO<String> result = new ResultDTO<>();
		// 生成6位验证码
        final String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        //                key    value
        MsgCodeMap.put(mobile, code);
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
                    result.setCode("7");
                    result.setMessage("发送短信异常！");
                    result.setError();
                }
            }
        }).start();
        result.setData(code);
        result.setSuccess("验证码生产成功");
		return result;
	}

	//验证码对比
	@Override
	public ResultDTO<String> validateCode(String mobile, String code) {
		ResultDTO<String> result = new ResultDTO<>();
		//从Map中根据手机号取到存入的验证码
		String sendCode=MsgCodeMap.get(mobile);
		if(code==null){
			result.setError(1, "失效的验证码");
		}
		if(code.equals(sendCode)){
			MsgCodeMap.remove(mobile);
			result.setSuccess("成功");
		}else{
			result.setError(1, "验证码错误");
		}
		return result;
	}

	//根据手机号找回登录密码
	@Override
	public ResultDTO<String> findPassword(String mobile, String code) {
		
		return null;
	}
}









