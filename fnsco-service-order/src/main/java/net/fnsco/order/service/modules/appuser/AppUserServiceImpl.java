package net.fnsco.order.service.modules.appuser;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.fnsco.bigdata.api.merchant.MerchantPosService;
import net.fnsco.bigdata.comm.BigdataSmsUtil;
import net.fnsco.bigdata.service.dao.master.MerchantCoreDao;
import net.fnsco.bigdata.service.dao.master.MerchantEntityDao;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.bigdata.service.domain.MerchantPos;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.OssUtil;
import net.fnsco.core.utils.SmsUtil;
import net.fnsco.freamwork.comm.Md5Util;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.api.appuser.AppUserSettingService;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.order.api.dto.AppOldListDTO;
import net.fnsco.order.api.dto.AppOldPeopleDTO;
import net.fnsco.order.api.dto.AppUserDTO;
import net.fnsco.order.api.dto.AppUserInfoDTO;
import net.fnsco.order.api.dto.AppUserLoginInfoDTO;
import net.fnsco.order.api.dto.AppUserManageDTO;
import net.fnsco.order.api.dto.AppUserMerchantDTO;
import net.fnsco.order.api.dto.AppUserMerchantEntityDTO;
import net.fnsco.order.api.dto.AppUserSettingDTO;
import net.fnsco.order.api.dto.BandDto;
import net.fnsco.order.api.dto.QueryBandDTO;
import net.fnsco.order.api.sysappmsg.SysAppMsgService;
import net.fnsco.order.service.dao.master.AppUserDao;
import net.fnsco.order.service.dao.master.AppUserMerchantDao;
import net.fnsco.order.service.dao.master.SysMsgAppSuccDao;
import net.fnsco.order.service.domain.AppUser;
import net.fnsco.order.service.domain.AppUserMerchant;
import net.fnsco.order.service.domain.SysMsgAppSucc;

@Service
public class AppUserServiceImpl extends BaseService implements AppUserService {

    private static final Logger            logger     = LoggerFactory.getLogger(AppUserServiceImpl.class);

    @Autowired
    private AppUserDao                     appUserDao;
    @Autowired
    private MerchantCoreDao                merchantCoreDao;
    @Autowired
    private AppUserMerchantDao             appUserMerchantDao;
    @Autowired
    private SysMsgAppSuccDao               sysMsgAppSuccDao;
    @Autowired
    private SysAppMsgService               sysAppMsgService;
    @Autowired
    private AppUserSettingService          appUserSettingService;
    @Autowired
    private MerchantPosService             merchantPosService;
    @Autowired
    private MerchantEntityDao         merchantEntityDao;
    @Autowired
   	private Environment env;
    
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;

    //注册
    @Override
    @Transactional
    public ResultDTO insertSelective(AppUserDTO appUserDTO) {
        Map<String, Object> map = new HashMap<>();
        //非空判断
        if (Strings.isNullOrEmpty(appUserDTO.getMobile())) {
            return ResultDTO.fail(ApiConstant.E_APP_PHONE_EMPTY);
        } else if (Strings.isNullOrEmpty(appUserDTO.getPassword())) {
            return ResultDTO.fail(ApiConstant.E_APP_PASSWORD_EMPTY);
        }
        if (Strings.isNullOrEmpty(appUserDTO.getCode())) {
            return ResultDTO.fail(ApiConstant.E_APP_CODE_EMPTY);
        }
        //对比验证码
        ResultDTO<String> res = validateCode(appUserDTO.getDeviceId(), appUserDTO.getCode(), appUserDTO.getMobile());
        if (!res.isSuccess()) {
            return res;
        }
        //根据手机号查询用户实体是否存在
        AppUser user = appUserDao.selectAppUserByMobileAndState(appUserDTO.getMobile(), 1);
        if (user != null) {
            return ResultDTO.fail(ApiConstant.E_ALREADY_LOGIN);
        }
        //根据deviceToken查找记录 如果存在就清空
        List<AppUser> items = appUserDao.queryBydeviceToken(appUserDTO.getDeviceToken());
        if (items.isEmpty()) {
            logger.warn("该设备之前没有注册过账号或该设备之前注册的账号已注销");
        }
        for (AppUser item : items) {
            String deviceToken = null;
            String deviceId = null;
            Integer deviceType = null;
            if (!appUserDao.updateDeviceToken(item.getMobile(), deviceToken, deviceId, deviceType)) {
                return ResultDTO.fail(ApiConstant.E_UPDATEPASSWORD_ERROR);
            }
        }
        AppUser appUser = new AppUser();
        final String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        appUser.setUserName("sqb" + code);
        appUser.setDeviceId(appUserDTO.getDeviceId());
        appUser.setDeviceToken(appUserDTO.getDeviceToken());
        appUser.setMobile(appUserDTO.getMobile());
        appUser.setDeviceType(appUserDTO.getDeviceType());
        appUser.setState(1);
        appUser.setRegTime(new Date());
        if(!Strings.isNullOrEmpty(appUserDTO.getEntityInnerCode())){
            appUser.setInviteEntityInnnerCode(appUserDTO.getEntityInnerCode());
            appUser.setInviteStatus(0);
        }
        //appUser.setLastLoginTime(new Date());
        String password = Md5Util.getInstance().md5(appUserDTO.getPassword());
        appUser.setPassword(password);
        if (!appUserDao.insertSelective(appUser)) {
            return ResultDTO.fail(ApiConstant.E_REGISTER_ERROR);
        }
        appUser = appUserDao.selectAppUserByMobile(appUser.getMobile());
        map.put("appUserId", appUser.getId());
        int merchantNums = 0;
        int Shopkeeper = 2;
        List<QueryBandDTO> list = appUserDao.selectInnercode(appUserDTO.getMobile());
        logger.error("注册sncode："+appUserDTO.getSnCode());
        if (!CollectionUtils.isEmpty(list)) {
            Shopkeeper = 1;
            merchantNums = list.size();
            for (QueryBandDTO li : list) {
                insertRel(li.getInnerCode(), appUser, ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());
            }
            //拉卡拉机器注册用到以下功能
        } else if (!Strings.isNullOrEmpty(appUserDTO.getSnCode())) {
            logger.error("sncode注册");
            String snCode = appUserDTO.getSnCode();
            List<MerchantPos> posList = merchantPosService.selectBySnCode(snCode);
            Map<String, String> innerCodeMap = Maps.newHashMap();
            for (MerchantPos pos : posList) {
                innerCodeMap.put(pos.getInnerCode(), pos.getInnerCode());
            }
            for (Map.Entry<String, String> entry : innerCodeMap.entrySet()) {
                insertRel(entry.getKey(), appUser, ConstantEnum.AuthorTypeEnum.CLERK.getCode());
            }
        }
        map.put("Shopkeeper", Shopkeeper);
        map.put("merchantNums", merchantNums);
        map.put("userName", appUser.getUserName());
        map.put("mobile", appUser.getMobile());
        //返回值增加设置状态
        List<AppUserSettingDTO> settingstatus = appUserSettingService.installSettingStatus(appUser.getId());
        map.put("appSettings", settingstatus);
        return ResultDTO.success(map);
    }

    private void insertRel(String innerCode, AppUser appUser, String roleId) {
        AppUserMerchant dto = new AppUserMerchant();
        dto.setAppUserId(appUser.getId());
        dto.setInnerCode(innerCode);
        dto.setModefyTime(new Date());
        dto.setRoleId(roleId);
        appUserMerchantDao.insertSelective(dto);
        //推送消息
        try {
        	if(!Strings.isNullOrEmpty(innerCode)) {
        		sysAppMsgService.pushMerChantMsg(innerCode, appUser.getId());
        	}
            
        } catch (Exception ex) {
            logger.error("绑定商户发送消息失败", ex);
        }
    }

    //生产验证码 
    @Override
    public ResultDTO getValidateCode(AppUserDTO appUserDTO) {
        String deviceId = appUserDTO.getDeviceId();
        final String mobile = appUserDTO.getMobile();
        //注册需要判断    0表示通过注册流程来获取验证码  1表示通过忘记密码流程来获取验证码
        if (appUserDTO.getOprationType() != null && appUserDTO.getOprationType() == 0) {
            AppUser user = appUserDao.selectAppUserByMobileAndState(appUserDTO.getMobile(), 1);
            if (user != null) {
                return ResultDTO.fail(ApiConstant.E_ALREADY_LOGIN);
            }
        }

        // 生成6位验证码
        final String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        valOpsStr.set(mobile + deviceId,code,30,TimeUnit.MINUTES);
        /**
        * 开启线程发送手机验证码
        */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String callback = BigdataSmsUtil.Code(mobile, code);
                    JSONObject callbackJson = (JSONObject) JSONObject.parse(callback);
                    String resultCode = callbackJson.getString("code");
                    if ("0".equals(resultCode)) {
                        logger.warn("验证码" + code + "已经发送至手机号" + mobile + "返回详情" + callback);
                    } else {
                        logger.warn("验证码" + code + "未能够发送至手机" + mobile + "错误code" + resultCode + ";错误详情" + callback);
                    }
                } catch (Exception e) {
                    logger.warn("验证码" + code + "未能够发送至手机" + mobile + "错误原因:异常错误");
                    logger.error("短信发送异常 ", e);
                }
            }
        }).start();
        return ResultDTO.success();
    }
    
  //生产验证码 
    @Override
    public ResultDTO getYZFValidateCode(AppUserDTO appUserDTO) {
        String deviceId = appUserDTO.getDeviceId();
        final String mobile = appUserDTO.getMobile();
        //注册需要判断    0表示通过注册流程来获取验证码  1表示通过忘记密码流程来获取验证码
        if (appUserDTO.getOprationType() != null && appUserDTO.getOprationType() == 0) {
            AppUser user = appUserDao.selectAppUserByMobileAndState(appUserDTO.getMobile(), 1);
            if (user != null) {
                return ResultDTO.fail(ApiConstant.E_ALREADY_LOGIN);
            }
        }

        // 生成6位验证码
        final String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        valOpsStr.set(mobile + deviceId,code,30,TimeUnit.MINUTES);
        /**
        * 开启线程发送手机验证码
        */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String callback = BigdataSmsUtil.bigDataCode(mobile, code);
                    JSONObject callbackJson = (JSONObject) JSONObject.parse(callback);
                    String resultCode = callbackJson.getString("code");
                    if ("0".equals(resultCode)) {
                        logger.warn("验证码" + code + "已经发送至手机号" + mobile + "返回详情" + callback);
                    } else {
                        logger.warn("验证码" + code + "未能够发送至手机" + mobile + "错误code" + resultCode + ";错误详情" + callback);
                    }
                } catch (Exception e) {
                    logger.warn("验证码" + code + "未能够发送至手机" + mobile + "错误原因:异常错误");
                    logger.error("短信发送异常 ", e);
                }
            }
        }).start();
        return ResultDTO.success();
    }
    
  //验证码对比
    public ResultDTO validateCode(Integer type,String deviceId, String code, String mobile) {
    	//非空判断
        if (Strings.isNullOrEmpty(deviceId)) {
            return ResultDTO.fail(ApiConstant.E_APP_DEVICETYPE_EMPTY);
        } else if (Strings.isNullOrEmpty(code)) {
            return ResultDTO.fail(ApiConstant.E_APP_CODE_EMPTY);
        }
        if (Strings.isNullOrEmpty(mobile)) {
            return ResultDTO.fail(ApiConstant.E_APP_PHONE_EMPTY);
        }
        
        String value = valOpsStr.get(type + mobile + deviceId);
        if(Strings.isNullOrEmpty(value)){
        	return ResultDTO.fail(ApiConstant.E_CODEOVERTIME_ERROR);//已超时
        }
        if(code.equals(value)){
        	stringRedisTemplate.delete(type + mobile + deviceId);//匹配
        	return ResultDTO.success();
        }else{
        	return ResultDTO.fail(ApiConstant.E_APP_CODE_ERROR);//不匹配
        }
    }
    //验证码对比
    public ResultDTO validateCode(String deviceId, String code, String mobile) {
    	//非空判断
        if (Strings.isNullOrEmpty(deviceId)) {
            return ResultDTO.fail(ApiConstant.E_APP_DEVICETYPE_EMPTY);
        } else if (Strings.isNullOrEmpty(code)) {
            return ResultDTO.fail(ApiConstant.E_APP_CODE_EMPTY);
        }
        if (Strings.isNullOrEmpty(mobile)) {
            return ResultDTO.fail(ApiConstant.E_APP_PHONE_EMPTY);
        }
        
        String value = valOpsStr.get(mobile + deviceId);
        if(Strings.isNullOrEmpty(value)){
        	return ResultDTO.fail(ApiConstant.E_CODEOVERTIME_ERROR);//已超时
        }
        if(code.equals(value)){
        	stringRedisTemplate.delete(mobile + deviceId);//匹配
        	return ResultDTO.success();
        }else{
        	return ResultDTO.fail(ApiConstant.E_APP_CODE_ERROR);//不匹配
        }
    }

    //根据手机号找回登录密码
    @Override
    public ResultDTO findPassword(AppUserDTO appUserDTO) {
        //非空判断
        if (Strings.isNullOrEmpty(appUserDTO.getDeviceId())) {
            return ResultDTO.fail(ApiConstant.E_APP_DEVICEIDNULL);
        } else if (Strings.isNullOrEmpty(appUserDTO.getPassword())) {
            return ResultDTO.fail(ApiConstant.E_APP_PASSWORD_EMPTY);
        } else if (Strings.isNullOrEmpty(appUserDTO.getCode())) {
            return ResultDTO.fail(ApiConstant.E_APP_CODE_EMPTY);
        } else if (Strings.isNullOrEmpty(appUserDTO.getMobile())) {
            return ResultDTO.fail(ApiConstant.E_APP_PHONE_EMPTY);
        }

        //判断手机号是否已经注册
        if (appUserDao.selectAppUserByMobile(appUserDTO.getMobile()) == null) {
            return ResultDTO.fail(ApiConstant.E_APP_PHONE_ERROR);
        }
        String password = Md5Util.getInstance().md5(appUserDTO.getPassword());
        //对比验证码
        ResultDTO res = validateCode(appUserDTO.getDeviceId(), appUserDTO.getCode(), appUserDTO.getMobile());
        if (!res.isSuccess()) {
            return res;
        }
        //密码更新失败
        if (!appUserDao.updatePasswordByPhone(appUserDTO.getMobile(), password)) {
            return ResultDTO.fail(ApiConstant.E_UPDATEPASSWORD_ERROR);
        }
        return ResultDTO.success();
    }

    //修改密码
    @Override
    public ResultDTO<String> modifyPassword(AppUserDTO appUserDTO) {
        Integer id = appUserDTO.getUserId();
        //非空判断
        if (id == null) {
            return ResultDTO.fail(ApiConstant.E_APP_ID_EMPTY);
        }
        if (Strings.isNullOrEmpty(appUserDTO.getPassword())) {
            return ResultDTO.fail(ApiConstant.E_APP_PASSWORD_EMPTY);
        } else if (Strings.isNullOrEmpty(appUserDTO.getOldPassword())) {
            return ResultDTO.fail(ApiConstant.E_APP_PASSWORD_EMPTY);
        }
        String password = Md5Util.getInstance().md5(appUserDTO.getPassword());
        String oldPassword = Md5Util.getInstance().md5(appUserDTO.getOldPassword());
        AppUser appUser = new AppUser();
        //根据id查询用户是否存在获取原密码
        AppUser mAppUser = appUserDao.selectAppUserById(id);
        if (null == mAppUser) {
            return ResultDTO.fail(ApiConstant.E_NOREGISTER_LOGIN);
        }
        //查到的密码和原密码做比较
        if (!oldPassword.equals(mAppUser.getPassword())) {
            return ResultDTO.fail(ApiConstant.E_OLDPASSWORD_ERROR);
        }
        appUser.setPassword(password);
        appUser.setId(id);
        if (!appUserDao.updateByPrimaryKeySelective(appUser)) {
            return ResultDTO.fail(ApiConstant.E_UPDATEPASSWORD_ERROR);
        }
        //更新到deviceToken
        String deviceToken = null;
        String deviceId = null;
        Integer deviceType = null;
        if (!appUserDao.updateDeviceTokenById(id, deviceToken, deviceId, deviceType)) {
            return ResultDTO.fail(ApiConstant.E_LOGINOUT_ERROR);
        }
        return ResultDTO.success();
    }

    //根据手机号码和密码登录
    @Override
    public ResultDTO loginByMoblie(AppUserDTO appUserDTO) {
        Map<String, Object> map = new HashMap<>();
        int Shopkeeper = 2;
        //非空判断
        if (Strings.isNullOrEmpty(appUserDTO.getMobile())) {
            return ResultDTO.fail(ApiConstant.E_APP_PHONE_EMPTY);
        } else if (Strings.isNullOrEmpty(appUserDTO.getPassword())) {
            return ResultDTO.fail(ApiConstant.E_APP_PASSWORD_EMPTY);
        }
        String password = Md5Util.getInstance().md5(appUserDTO.getPassword());
        //根据手机号查询用户实体是否存在
        AppUser appUser = appUserDao.selectAppUserByMobileAndState(appUserDTO.getMobile(), 1);
        if (appUser == null) {
            return ResultDTO.fail(ApiConstant.E_NOREGISTER_LOGIN);
        }
        //密码错误
        if (!password.equals(appUser.getPassword())) {
            return ResultDTO.fail(ApiConstant.E_LOGINMSG_ERROR);
        }
        //把原先的deviceToken清空
        //根据deviceToken查找记录 如果存在就清空
        List<AppUser> items = appUserDao.queryBydeviceToken(appUserDTO.getDeviceToken());
        if (items.isEmpty()) {
            logger.warn("该设备之前没有注册过账号或该设备之前注册的账号已注销");
        }
        for (AppUser item : items) {
            String deviceToken = null;
            String deviceId = null;
            Integer deviceType = null;
            if (!appUserDao.updateDeviceToken(item.getMobile(), deviceToken, deviceId, deviceType)) {
                return ResultDTO.fail(ApiConstant.E_UPDATEPASSWORD_ERROR);
            }
        }
        AppUser adminUser = new AppUser();
        adminUser.setDeviceId(appUserDTO.getDeviceId());
        adminUser.setLastLoginTime(new Date());
        adminUser.setDeviceType(appUserDTO.getDeviceType());
        adminUser.setDeviceToken(appUserDTO.getDeviceToken());
        adminUser.setId(appUser.getId());
        adminUser.setForcedLoginOut(0);
        //更新到实体
        if (!appUserDao.updateByPrimaryKeySelective(adminUser)) {
            return ResultDTO.fail(ApiConstant.E_LOGIN_ERROR);
        }
        String headImagePath = appUser.getHeadImagePath();
        if (!Strings.isNullOrEmpty(headImagePath)) {
            String path = headImagePath.substring(headImagePath.indexOf("^") + 1);
            map.put("headImagePath", OssUtil.getForeverFileUrl(OssUtil.getHeadBucketName(), path));
        } else {
            map.put("headImagePath", "");
        }
        map.put("userName", appUser.getUserName());
        map.put("mobile", appUser.getMobile());
        map.put("sex", appUser.getSex());
        map.put("appUserId", appUser.getId());
        //查询用户绑定商户数量 根据用户id查询数量
        int merchantNums = 0;
        List<AppUserMerchant> rel = appUserMerchantDao.selectByUserId(appUser.getId());
        if (!CollectionUtils.isEmpty(rel)) {
            merchantNums = rel.size();
        }
        map.put("merchantNums", merchantNums);
        //登录判断是否是店主
        Integer appUserId = appUser.getId();
        //返回多个店铺的店主
        List<AppUserMerchant> merchantList = appUserMerchantDao.selectByPrimaryKey(appUserId, "1");
        if (!CollectionUtils.isEmpty(merchantList)) {
            Shopkeeper = 1;
        }
        //登录获取未读消息信息
        List<SysMsgAppSucc> unReadInfo = sysMsgAppSuccDao.selectTotalUnread(appUserId);
        List<Integer> unReadMsgIds = Lists.newArrayList();
        for (SysMsgAppSucc sysMsgAppSucc : unReadInfo) {
            unReadMsgIds.add(sysMsgAppSucc.getMsgId());
        }
        map.put("unReadMsgIds", unReadMsgIds);
        map.put("Shopkeeper", Shopkeeper);
        //返回值增加设置状态
        List<AppUserSettingDTO> settingstatus = appUserSettingService.installSettingStatus(appUserId);
        map.put("appSettings", settingstatus);
        return ResultDTO.success(map);
    }
    //e789生产验证码 前手机号判断
	@Override
	public AppUser selectAppUserByMobile(String mobile) {
		return appUserDao.selectAppUserByMobileAndState(mobile, 1);
	}
  //e789生产验证码 
    @Override
    public ResultDTO getE789ValidateCode(AppUserDTO appUserDTO) {
        String deviceId = appUserDTO.getDeviceId();
        final String mobile = appUserDTO.getMobile();
        final Integer type = appUserDTO.getOprationType();
        // 生成6位验证码
        final String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        valOpsStr.set(type+mobile + deviceId,code,30,TimeUnit.MINUTES);
        /**
        * 开启线程发送手机验证码
        */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String callback = SmsUtil.E789Code(mobile, code);
                    JSONObject callbackJson = (JSONObject) JSONObject.parse(callback);
                    String resultCode = callbackJson.getString("code");
                    if ("0".equals(resultCode)) {
                        logger.warn("验证码" + code + "已经发送至手机号" + mobile + "返回详情" + callback);
                    } else {
                        logger.warn("验证码" + code + "未能够发送至手机" + mobile + "错误code" + resultCode + ";错误详情" + callback);
                    }
                } catch (Exception e) {
                    logger.warn("验证码" + code + "未能够发送至手机" + mobile + "错误原因:异常错误");
                    logger.error("短信发送异常 ", e);
                }
            }
        }).start();
        return ResultDTO.success();
    }
    //根据手机号找回登录密码
    @Override
    public ResultDTO e789FindPassword(AppUserDTO appUserDTO) {
        //非空判断
        if (Strings.isNullOrEmpty(appUserDTO.getDeviceId())) {
            return ResultDTO.fail(ApiConstant.E_APP_DEVICEIDNULL);
        } else if (Strings.isNullOrEmpty(appUserDTO.getPassword())) {
            return ResultDTO.fail(ApiConstant.E_APP_PASSWORD_EMPTY);
        } else if (Strings.isNullOrEmpty(appUserDTO.getCode())) {
            return ResultDTO.fail(ApiConstant.E_APP_CODE_EMPTY);
        } else if (Strings.isNullOrEmpty(appUserDTO.getMobile())) {
            return ResultDTO.fail(ApiConstant.E_APP_PHONE_EMPTY);
        }

        //判断手机号是否已经注册
        if (appUserDao.selectAppUserByMobile(appUserDTO.getMobile()) == null) {
            return ResultDTO.fail(ApiConstant.E_APP_PHONE_ERROR);
        }
        String password = Md5Util.getInstance().md5(appUserDTO.getPassword());
        //对比验证码
        ResultDTO res = validateCode(1, appUserDTO.getDeviceId(), appUserDTO.getCode(), appUserDTO.getMobile());
        if (!res.isSuccess()) {
            return res;
        }
        //密码更新失败
        if (!appUserDao.updatePasswordByPhone(appUserDTO.getMobile(), password)) {
            return ResultDTO.fail(ApiConstant.E_UPDATEPASSWORD_ERROR);
        }
        return ResultDTO.success();
    }
    //根据手机号找回支付密码
    @Override
    public ResultDTO e789FindPayPassword(AppUserDTO appUserDTO) {
        //非空判断
        if (Strings.isNullOrEmpty(appUserDTO.getDeviceId())) {
            return ResultDTO.fail(ApiConstant.E_APP_DEVICEIDNULL);
        } else if (Strings.isNullOrEmpty(appUserDTO.getPassword())) {
            return ResultDTO.fail(ApiConstant.E_APP_PASSWORD_EMPTY);
        } else if (Strings.isNullOrEmpty(appUserDTO.getCode())) {
            return ResultDTO.fail(ApiConstant.E_APP_CODE_EMPTY);
        } else if (Strings.isNullOrEmpty(appUserDTO.getMobile())) {
            return ResultDTO.fail(ApiConstant.E_APP_PHONE_EMPTY);
        }
        String password = Md5Util.getInstance().md5(appUserDTO.getPassword());
        //对比验证码
        ResultDTO res = validateCode(3 ,appUserDTO.getDeviceId(), appUserDTO.getCode(), appUserDTO.getMobile());
        if (!res.isSuccess()) {
            return res;
        }
        //密码更新失败
        if (!appUserDao.updatePayPasswordByPhone(appUserDTO.getMobile(), password)) {
            return ResultDTO.fail(ApiConstant.E_UPDATEPASSWORD_ERROR);
        }
        return ResultDTO.success();
    }
    //e789注册
    @Override
    @Transactional
    public ResultDTO e789InsertSelective(AppUserDTO appUserDTO) {
        //根据deviceToken查找记录 如果存在就清空
        List<AppUser> items = appUserDao.queryBydeviceToken(appUserDTO.getDeviceToken());
        if (items.isEmpty()) {
            logger.warn("该设备之前没有注册过账号或该设备之前注册的账号已注销");
        }
        for (AppUser item : items) {
            String deviceToken = null;
            String deviceId = null;
            Integer deviceType = null;
            if (!appUserDao.updateDeviceToken(item.getMobile(), deviceToken, deviceId, deviceType)) {
                return ResultDTO.fail(ApiConstant.E_UPDATEPASSWORD_ERROR);
            }
        }
        AppUser appUser = new AppUser();
        final String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        appUser.setUserName("e789" + code);
        appUser.setDeviceId(appUserDTO.getDeviceId());
        appUser.setDeviceToken(appUserDTO.getDeviceToken());
        appUser.setMobile(appUserDTO.getMobile());
        appUser.setDeviceType(appUserDTO.getDeviceType());
        appUser.setHeadImagePath(appUserDTO.getHeadImagePath());
        String payPassword = Md5Util.getInstance().md5(appUserDTO.getPayPassword());
        appUser.setPayPassword(payPassword);
        appUser.setState(1);
        appUser.setRegTime(new Date());
        if(!Strings.isNullOrEmpty(appUserDTO.getEntityInnerCode())){
            appUser.setInviteEntityInnnerCode(appUserDTO.getEntityInnerCode());
            appUser.setInviteStatus(0);
        }
        //appUser.setLastLoginTime(new Date());
        String password = Md5Util.getInstance().md5(appUserDTO.getPassword());
        appUser.setPassword(password);
        if (!appUserDao.insertSelective(appUser)) {
            return ResultDTO.fail(ApiConstant.E_REGISTER_ERROR);
        }
        return ResultDTO.success();
    }
  //e789根据手机号码和密码登录
    @Override
    public ResultDTO e789LoginByMoblie(AppUserDTO appUserDTO) {
        //非空判断
        if (Strings.isNullOrEmpty(appUserDTO.getMobile())) {
            return ResultDTO.fail(ApiConstant.E_APP_PHONE_EMPTY);
        } else if (Strings.isNullOrEmpty(appUserDTO.getPassword())) {
            return ResultDTO.fail(ApiConstant.E_APP_PASSWORD_EMPTY);
        }
        String password = Md5Util.getInstance().md5(appUserDTO.getPassword());
        //根据手机号查询用户实体是否存在
        AppUser appUser = appUserDao.selectAppUserByMobileAndState(appUserDTO.getMobile(), 1);
        if (appUser == null) {
            return ResultDTO.fail(ApiConstant.E_NOREGISTER_LOGIN);
        }
        //密码错误
        if (!password.equals(appUser.getPassword())) {
            return ResultDTO.fail(ApiConstant.E_LOGINMSG_ERROR);
        }
        //把原先的deviceToken清空
        //根据deviceToken查找记录 如果存在就清空
        List<AppUser> items = appUserDao.queryBydeviceToken(appUserDTO.getDeviceToken());
        if (items.isEmpty()) {
            logger.warn("该设备之前没有注册过账号或该设备之前注册的账号已注销");
        }
        for (AppUser item : items) {
            String deviceToken = null;
            String deviceId = null;
            Integer deviceType = null;
            if (!appUserDao.updateDeviceToken(item.getMobile(), deviceToken, deviceId, deviceType)) {
                return ResultDTO.fail(ApiConstant.E_UPDATEPASSWORD_ERROR);
            }
        }
        AppUser adminUser = new AppUser();
        adminUser.setDeviceId(appUserDTO.getDeviceId());
        adminUser.setLastLoginTime(new Date());
        adminUser.setDeviceType(appUserDTO.getDeviceType());
        adminUser.setDeviceToken(appUserDTO.getDeviceToken());
        adminUser.setId(appUser.getId());
        adminUser.setForcedLoginOut(0);
        //更新到实体
        if (!appUserDao.updateByPrimaryKeySelective(adminUser)) {
            return ResultDTO.fail(ApiConstant.E_LOGIN_ERROR);
        }
        return ResultDTO.success();
    }
    //e789获取登录信息
    @Override
    public AppUserLoginInfoDTO getLoginInfor(AppUserDTO appUserDTO) {
    	AppUserLoginInfoDTO appUserLoginInfoDTO = new AppUserLoginInfoDTO();
    	AppUser appUser = appUserDao.selectAppUserByMobileAndState(appUserDTO.getMobile(), 1);
    	String headImagePath = appUser.getHeadImagePath();
        if (!Strings.isNullOrEmpty(headImagePath)) {
        	if(headImagePath.indexOf("^")!=-1) {
        		String path = headImagePath.substring(headImagePath.indexOf("^") + 1);
                appUserLoginInfoDTO.setHeadImagePath(OssUtil.getForeverFileUrl(OssUtil.getHeadBucketName(), path));
        	}else {
        		String prefix = env.getProperty("app.base.url");
        		appUserLoginInfoDTO.setHeadImagePath(prefix+headImagePath);
        	}
        } else {
        	appUserLoginInfoDTO.setHeadImagePath(null);
        }
        appUserLoginInfoDTO.setUserName(appUser.getUserName());
        appUserLoginInfoDTO.setMoblie(appUser.getMobile());
        appUserLoginInfoDTO.setUserId(appUser.getId());
        appUserLoginInfoDTO.setPayPassword(appUser.getPayPassword());
        appUserLoginInfoDTO.setRealName(appUser.getRealName());
        Integer appUserId = appUser.getId();
        //登录获取未读消息信息
        List<SysMsgAppSucc> unReadInfo = sysMsgAppSuccDao.selectTotalUnread(appUserId);
        List<Integer> unReadMsgIds = Lists.newArrayList();
        for (SysMsgAppSucc sysMsgAppSucc : unReadInfo) {
            unReadMsgIds.add(sysMsgAppSucc.getMsgId());
        }
        appUserLoginInfoDTO.setUnReadMsgIds(unReadMsgIds);
        /*//返回值增加设置状态
        List<AppUserSettingDTO> settingstatus = appUserSettingService.installSettingStatus(appUserId);
        map.put("appSettings", settingstatus);*/
        return appUserLoginInfoDTO;
    }
    //e789修改支付密码
    @Override
    public ResultDTO<String> modifyPayPassword(AppUserDTO appUserDTO) {
        Integer id = appUserDTO.getUserId();
        //非空判断
        if (id == null) {
            return ResultDTO.fail(ApiConstant.E_APP_ID_EMPTY);
        }
        if (Strings.isNullOrEmpty(appUserDTO.getPassword())) {
            return ResultDTO.fail(ApiConstant.E_APP_PASSWORD_EMPTY);
        } else if (Strings.isNullOrEmpty(appUserDTO.getOldPassword())) {
            return ResultDTO.fail(ApiConstant.E_APP_PASSWORD_EMPTY);
        }
        String password = Md5Util.getInstance().md5(appUserDTO.getPassword());
        String oldPassword = Md5Util.getInstance().md5(appUserDTO.getOldPassword());
        AppUser appUser = new AppUser();
        //根据id查询用户是否存在获取原密码
        AppUser mAppUser = appUserDao.selectAppUserById(id);
        if (null == mAppUser) {
            return ResultDTO.fail(ApiConstant.E_NOREGISTER_LOGIN);
        }
        //查到的密码和原密码做比较
        if (!oldPassword.equals(mAppUser.getPayPassword())) {
            return ResultDTO.fail(ApiConstant.E_OLDPASSWORD_ERROR);
        }
        appUser.setPayPassword(password);
        appUser.setId(id);
        if (!appUserDao.updateByPrimaryKeySelective(appUser)) {
            return ResultDTO.fail(ApiConstant.E_UPDATEPASSWORD_ERROR);
        }
        /*//更新到deviceToken
        String deviceToken = null;
        String deviceId = null;
        Integer deviceType = null;
        if (!appUserDao.updateDeviceTokenById(id, deviceToken, deviceId, deviceType)) {
            return ResultDTO.fail(ApiConstant.E_LOGINOUT_ERROR);
        }*/
        return ResultDTO.success();
    }
  //e789获取个人信息
    @Override
    public AppUserInfoDTO getMyselfInfo(Integer id) {
        AppUser appUser = appUserDao.selectAppUserById(id);
        if(appUser==null) {
        	return null;
        }
        AppUserInfoDTO dto = new AppUserInfoDTO();
        dto.setSex(appUser.getSex());
        dto.setUserName(appUser.getUserName());
        String headImagePath = appUser.getHeadImagePath();
        if (!Strings.isNullOrEmpty(headImagePath)) {
        	if(headImagePath.indexOf("^")!=-1) {
        		String path = headImagePath.substring(headImagePath.indexOf("^") + 1);
        		dto.setHeadImagePath(OssUtil.getForeverFileUrl(OssUtil.getHeadBucketName(), path));
        	}else {
        		String prefix = env.getProperty("app.base.url");
        		dto.setHeadImagePath(prefix+headImagePath);
        	}
        } else {
        	dto.setHeadImagePath(null);
        }
        dto.setMoblie(appUser.getMobile());
        dto.setRealName(appUser.getRealName());
        return dto;
    }


    //退出登录
    @Override
    public ResultDTO<String> loginOut(AppUserDTO appUserDTO) {
        //更新到实体
        Integer id = appUserDTO.getUserId();
        String deviceToken = null;
        String deviceId = null;
        Integer deviceType = null;
        if (!appUserDao.updateDeviceTokenById(id, deviceToken, deviceId, deviceType)) {
            return ResultDTO.fail(ApiConstant.E_LOGINOUT_ERROR);
        }
        return ResultDTO.success();
    }

    /**
     * web端分页查询
     * (non-Javadoc)
     * @see net.fnsco.order.api.appuser.AppUserService#queryPageList(net.fnsco.order.api.dto.AppUserManageDTO, int, int)
     * @auth tangliang
     * @date 2017年7月13日 下午1:53:56
     */
    @Override
    public ResultPageDTO<AppUserManageDTO> queryPageList(AppUserManageDTO record, int currentPageNum, int perPageSize) {
        //分页器实例化     实例化当前页和每条显示的记录数 还有传过来的参数  手机号和店铺名等条件称封装到一个实体里面
        PageDTO<AppUserManageDTO> params = new PageDTO<AppUserManageDTO>(currentPageNum, perPageSize, record);
        //调用Dao方法时可以使用上面封装的实体         AppUserManageDTO即使用户返回给用户的实体 也用来传递参数
        List<AppUserManageDTO> data = appUserDao.queryPageList(params);
        //返回根据条件查询的所有记录条数
        int totalNum = appUserDao.queryTotalByCondition(record);
        //返回给页面总条数和每页查询的数据
        ResultPageDTO<AppUserManageDTO> result = new ResultPageDTO<AppUserManageDTO>(totalNum, data);
        return result;
    }

    @Override
    public List<AppUserManageDTO> queryAppPageList(AppUserManageDTO record) {
        //分页器实例化     实例化当前页和每条显示的记录数 还有传过来的参数  手机号和店铺名等条件称封装到一个实体里面
        PageDTO<AppUserManageDTO> params = new PageDTO<AppUserManageDTO>(1, 10000, record);
        //调用Dao方法时可以使用上面封装的实体         AppUserManageDTO即使用户返回给用户的实体 也用来传递参数
        List<AppUserManageDTO> data = appUserDao.queryPageList(params);
        return data;
    }

    @Override
    public ResultDTO modifyRole(BandDto bandDto) {
        List<QueryBandDTO> list = appUserDao.selectBandPeopleByMobile(bandDto.getMobile());
        if (list.size() == 0) {
            return ResultDTO.fail(ApiConstant.E_NOBAND_ERROR);
        }
        //一条商铺都没有绑定
        for (QueryBandDTO li : list) {
            MerchantCore core = merchantCoreDao.selectByInnerCode(li.getInnerCode());
            li.setMerName(core.getMerName());
        }
        return ResultDTO.success(list);
    }

    /**
     * (non-Javadoc)推送消息接收者查询
     * @see net.fnsco.order.api.appuser.AppUserService#queryAllPushUser()
     * @auth tangliang
     * @date 2017年7月17日 上午9:35:26
     */
    @Override
    public List<AppUser> queryAllPushUser() {
        return appUserDao.queryAllPushUser();
    }

    //判断角色修改
    @Override
    public ResultDTO<String> judgeRoles(List<AppUserMerchantDTO> params) {
        AppOldPeopleDTO datas = new AppOldPeopleDTO();
        List<AppOldListDTO> listDto = new ArrayList<AppOldListDTO>();
        //想成为店主
        List<AppOldListDTO> clerk = new ArrayList<AppOldListDTO>();
        for (AppUserMerchantDTO li : params) {
            //如果自己更新自己则不提示 成为店主 1
            //AppOldPeopleDTO appOldPeopleDTO=new AppOldPeopleDTO();
            if (li.getRoleId().equals(ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode())) {
                //判断用户是否原先是这个店的店主
                AppUserMerchant appUserMerchant = appUserMerchantDao.selectByall(li.getInnerCode(), li.getAppUserId(), ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());
                if (appUserMerchant == null) {
                    //如果不是店主
                    AppOldListDTO dto = new AppOldListDTO();
                    MerchantCore merchantCore = merchantCoreDao.selectByInnerCode(li.getInnerCode());
                    dto.setMerName(merchantCore.getMerName());
                    listDto.add(dto);
                }
            }
            //想成为店员 2
            if (li.getRoleId().equals(ConstantEnum.AuthorTypeEnum.CLERK.getCode())) {
                //查询这个店员原来是不是店主
                AppUserMerchant appUserMerchant = appUserMerchantDao.selectByall(li.getInnerCode(), li.getAppUserId(), ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());
                if (appUserMerchant != null) {
                    AppOldListDTO dto = new AppOldListDTO();
                    AppUser appUser = appUserDao.selectAppUserById(li.getAppUserId());
                    dto.setMobile(appUser.getMobile());
                    MerchantCore merchantCore = merchantCoreDao.selectByInnerCode(li.getInnerCode());
                    dto.setMerName(merchantCore.getMerName());
                    clerk.add(dto);
                }
            }

        }
        datas.setList(listDto);
        datas.setClerk(clerk);
        return ResultDTO.success(datas);
    }

    //角色修改
    @Override
    @Transactional
    public ResultDTO<String> changeRole(List<AppUserMerchantDTO> params) {
        for (AppUserMerchantDTO li : params) {
            //如果修改成店主
            if (li.getRoleId().equals(ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode())) {
                //判断自己是不是这个店原来的店长
                AppUserMerchant appUserMerchant = appUserMerchantDao.selectByall(li.getInnerCode(), li.getAppUserId(), ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());
                if (appUserMerchant == null) {
                    AppUser user = new AppUser();
                    user.setId(li.getAppUserId());
                    user.setForcedLoginOut(1);
                    if (!appUserDao.updateByPrimaryKeySelective(user)) {
                        return ResultDTO.fail(ApiConstant.E_FORCEDLOGINOUT_ERROR);
                    }
                }
            }
            //如果成为店员 
            if (li.getRoleId().equals(ConstantEnum.AuthorTypeEnum.CLERK.getCode())) {
                //判断自己原来是不是店长
                AppUserMerchant appUserMerchant = appUserMerchantDao.selectByall(li.getInnerCode(), li.getAppUserId(), ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());
                if (appUserMerchant != null) {
                    AppUser user = new AppUser();
                    user.setId(li.getAppUserId());
                    user.setForcedLoginOut(1);
                    if (!appUserDao.updateByPrimaryKeySelective(user)) {
                        return ResultDTO.fail(ApiConstant.E_FORCEDLOGINOUT_ERROR);
                    }
                }
            }
            li.setModefyTime(new Date());
            int num = appUserMerchantDao.updateByPrimaryKeySelective(li);
            if (num == 0) {
                return ResultDTO.fail(ApiConstant.E_CHANGEROLE_ERROR);
            }
        }
        return ResultDTO.success();
    }

    @Override
    public AppUser selectAppUserById(Integer id) {

        // TODO Auto-generated method stub
        return appUserDao.selectAppUserById(id);

    }

    @Override
    public void addAppUserMerchantRole(AppUserMerchant dto) {
        appUserMerchantDao.insertSelective(dto);
    }

    /**
     * (non-Javadoc)修改用户个人信息
     * @see net.fnsco.order.api.appuser.AppUserService#modifyInfo(net.fnsco.order.api.dto.AppUserDTO)
     * @author tangliang
     * @date 2017年7月31日 下午3:01:35
     */
    @Override
    public ResultDTO modifyInfo(AppUserDTO appUserDto) {
        AppUser appUser = new AppUser();
        BeanUtils.copyProperties(appUserDto, appUser);
        appUser.setId(appUserDto.getUserId());
        boolean flag = appUserDao.updateByPrimaryKeySelective(appUser);
        if (!flag) {
            return ResultDTO.fail(ApiConstant.E_UPDATE_FAIL);
        }
        return ResultDTO.success();

    }

    //获取个人信息
    @Override
    public ResultDTO<String> getUserInfo(AppUserDTO appUserDTO) {
        if (appUserDTO.getUserId() == null) {
            return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
        }
        AppUser appUser = appUserDao.selectAppUserById(appUserDTO.getUserId());
        AppUserInfoDTO dto = new AppUserInfoDTO();
        dto.setSex(appUser.getSex());
        dto.setUserName(appUser.getUserName());
        String headImagePath = appUser.getHeadImagePath();
        if (!Strings.isNullOrEmpty(headImagePath)) {
            String path = headImagePath.substring(headImagePath.indexOf("^") + 1);
            dto.setHeadImagePath(OssUtil.getForeverFileUrl(OssUtil.getHeadBucketName(), path));
        }
        dto.setMoblie(appUser.getMobile());
        dto.setRealName(appUser.getRealName());
        return ResultDTO.success(dto);
    }

    @Override
    public List<AppUserMerchant> getAppUserMerchantByInnerCode(String innerCode) {
        return appUserMerchantDao.selectAllByInnerCode(innerCode);
    }
    @Override
    public List<AppUser> selectAllInviteAppUser(){
        return appUserDao.selectAllInviteAppUser();
    }
    @Override
    public boolean updateAppUser(AppUser appUser){
        return appUserDao.updateByPrimaryKeySelective(appUser);
    }

	@Override
	public String getIdAuth(Integer userId) {
		AppUser appUser = appUserDao.selectIdAuth(userId);
		if(appUser != null){
			return appUser.getIdCardNumber();
		}else{
			return "";
		}
	}

	@Override
	public ResultPageDTO<AppUserMerchantEntityDTO> queryPageList(AppUserMerchantEntityDTO record, int currentPageNum,
			int perPageSize) {
		
		//分页器实例化     实例化当前页和每条显示的记录数 还有传过来的参数  手机号和店铺名等条件称封装到一个实体里面
        PageDTO<AppUserMerchantEntityDTO> params = new PageDTO<AppUserMerchantEntityDTO>(currentPageNum, perPageSize, record);
        //调用Dao方法时可以使用上面封装的实体         AppUserManageDTO即使用户返回给用户的实体 也用来传递参数
        List<AppUserMerchantEntityDTO> data = appUserDao.queryPageListE789(params);
        //返回根据条件查询的所有记录条数
        int totalNum = appUserDao.queryTotalByConditionE789(record);
        //返回给页面总条数和每页查询的数据
        ResultPageDTO<AppUserMerchantEntityDTO> result = new ResultPageDTO<AppUserMerchantEntityDTO>(totalNum, data);
        return result;
		
	}
}
