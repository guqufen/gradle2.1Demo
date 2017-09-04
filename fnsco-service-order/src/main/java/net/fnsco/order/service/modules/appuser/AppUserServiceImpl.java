package net.fnsco.order.service.modules.appuser;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import net.fnsco.bigdata.service.dao.master.MerchantContactDao;
import net.fnsco.bigdata.service.dao.master.MerchantCoreDao;
import net.fnsco.bigdata.service.dao.master.MerchantUserRelDao;
import net.fnsco.bigdata.service.domain.MerchantContact;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.bigdata.service.domain.MerchantUserRel;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.OssUtil;
import net.fnsco.core.utils.SmsUtil;
import net.fnsco.freamwork.comm.Md5Util;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.order.api.dto.AppOldListDTO;
import net.fnsco.order.api.dto.AppOldPeopleDTO;
import net.fnsco.order.api.dto.AppUserDTO;
import net.fnsco.order.api.dto.AppUserInfoDTO;
import net.fnsco.order.api.dto.AppUserManageDTO;
import net.fnsco.order.api.dto.AppUserMerchantDTO;
import net.fnsco.order.api.dto.BandDto;
import net.fnsco.order.api.dto.QueryBandDTO;
import net.fnsco.order.api.dto.SmsCodeDTO;
import net.fnsco.order.service.dao.master.AppUserDao;
import net.fnsco.order.service.dao.master.AppUserMerchantDao;
import net.fnsco.order.service.dao.master.SysMsgAppSuccDao;
import net.fnsco.order.service.domain.AppUser;
import net.fnsco.order.service.domain.AppUserMerchant;
import net.fnsco.order.service.domain.SysMsgAppSucc;

@Service
public class AppUserServiceImpl extends BaseService implements AppUserService {

    private static final Logger            logger     = LoggerFactory.getLogger(AppUserServiceImpl.class);

    private static Map<String, SmsCodeDTO> MsgCodeMap = new HashMap<>();                                  //存放验证码的
    //private static Map<String,Integer> LoginTimeMap=new HashMap<>();//存放登录次数的

    @Autowired
    private AppUserDao                     appUserDao;
    @Autowired
    private MerchantCoreDao                merchantCoreDao;
    @Autowired
    private MerchantUserRelDao             merchantUserRelDao;
    @Autowired
    private MerchantContactDao             merchantContactDao;
    @Autowired
    private AppUserMerchantDao             appUserMerchantDao;
    @Autowired
    private SysMsgAppSuccDao               sysMsgAppSuccDao;
    //注册
    @Override
    @Transactional
    public ResultDTO insertSelective(AppUserDTO appUserDTO) {
        Map<String, Integer> map = new HashMap<>();
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
        List<AppUser> items=appUserDao.queryBydeviceToken(appUserDTO.getDeviceToken());
        if(items.isEmpty()){
            logger.warn("该设备之前没有注册过账号或该设备之前注册的账号已注销");
        }
        for(AppUser item:items){
            String deviceToken=null;
            String deviceId=null;
            Integer deviceType=null;
            if (!appUserDao.updateDeviceToken(item.getMobile(),deviceToken,deviceId,deviceType)) {
                return ResultDTO.fail(ApiConstant.E_UPDATEPASSWORD_ERROR);
            }
        }
        AppUser appUser = new AppUser();
        final String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        if(Strings.isNullOrEmpty(appUserDTO.getUserName())){
            appUser.setUserName("sqb"+code);
        }
        appUser.setDeviceId(appUserDTO.getDeviceId());
        appUser.setDeviceToken(appUserDTO.getDeviceToken());
        appUser.setMobile(appUserDTO.getMobile());
        appUser.setDeviceType(appUserDTO.getDeviceType());
        appUser.setState(1);
        appUser.setRegTime(new Date());
        appUser.setLastLoginTime(new Date());
        String password = Md5Util.getInstance().md5(appUserDTO.getPassword());
        appUser.setPassword(password);
        if (!appUserDao.insertSelective(appUser)) {
            return ResultDTO.fail(ApiConstant.E_REGISTER_ERROR);
        }
        appUser = appUserDao.selectAppUserByMobile(appUser.getMobile());
        map.put("appUserId", appUser.getId());
        //注册成功后关联商户
        int merchantNums1 = 0;
        int merchantNums2 = 0;

        //MerchantContact表
        MerchantContact merchantContact = new MerchantContact();
        merchantContact.setContactMobile(appUserDTO.getMobile());
        //条件查询符合手机号的记录返回list
        List<MerchantContact> li = merchantContactDao.queryListByCondition(merchantContact);
        if (!CollectionUtils.isEmpty(li)) {
            merchantNums1 = li.size();
            //返回商户数
            for (MerchantContact object : li) {
                MerchantUserRel rel = merchantUserRelDao.selectByUserIdInnerCode(appUser.getId(), object.getInnerCode());
                if (rel == null) {
                    rel = new MerchantUserRel();
                    rel.setAppUserId(appUser.getId());
                    rel.setInnerCode(object.getInnerCode());
                    rel.setModefyTime(new Date());
                    merchantUserRelDao.insertSelective(rel);
                    //用户管理表新增记录
                    AppUserMerchant dto=new AppUserMerchant();
                    dto.setAppUserId(appUser.getId());
                    dto.setInnerCode(object.getInnerCode());
                    dto.setModefyTime(new Date());
                    dto.setRoleId(ConstantEnum.AuthorTypeEnum.CLERK.getCode());
                    appUserMerchantDao.insertSelective(dto);
                }
            }
        }

        //MerchantCore表
        MerchantCore merchantCore = new MerchantCore();
        merchantCore.setLegalPersonMobile(appUserDTO.getMobile());
        merchantCore.setStatus(1);
        List<MerchantCore> list = merchantCoreDao.queryListByCondition(merchantCore);
        if (!CollectionUtils.isEmpty(list)) {
            merchantNums2 = list.size();
            //返回商户数
            for (MerchantCore object : list) {
                MerchantUserRel rel = merchantUserRelDao.selectByUserIdInnerCode(appUser.getId(), object.getInnerCode());
                if (rel == null) {
                    rel = new MerchantUserRel();
                    rel.setAppUserId(appUser.getId());
                    rel.setInnerCode(object.getInnerCode());
                    rel.setModefyTime(new Date());
                    merchantUserRelDao.insertSelective(rel);
                  //用户管理表新增记录
                    AppUserMerchant dto=new AppUserMerchant();
                    dto.setAppUserId(appUser.getId());
                    dto.setInnerCode(object.getInnerCode());
                    dto.setModefyTime(new Date());
                    dto.setRoleId(ConstantEnum.AuthorTypeEnum.CLERK.getCode());
                    appUserMerchantDao.insertSelective(dto);
                }
            }
        }
        map.put("merchantNums", merchantNums1 + merchantNums2);
        return ResultDTO.success(map);
    }

    //生产验证码
    @Override
    public ResultDTO getValidateCode(AppUserDTO appUserDTO) {
        String deviceId = appUserDTO.getDeviceId();
        final String mobile = appUserDTO.getMobile();
        //注册需要判断
        if (appUserDTO.getOprationType() == 0) {
            AppUser user = appUserDao.selectAppUserByMobileAndState(appUserDTO.getMobile(), 1);
            if (user != null) {
                return ResultDTO.fail(ApiConstant.E_ALREADY_LOGIN);
            }
        }

        // 生成6位验证码
        final String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        SmsCodeDTO object = new SmsCodeDTO(code, System.currentTimeMillis());
        MsgCodeMap.put(mobile + deviceId, object);
        /**
        * 开启线程发送手机验证码
        */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String callback = SmsUtil.Code(mobile, code);
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
    private ResultDTO validateCode(String deviceId, String code, String mobile) {
        //非空判断
        if (Strings.isNullOrEmpty(deviceId)) {
            return ResultDTO.fail(ApiConstant.E_APP_DEVICETYPE_EMPTY);
        } else if (Strings.isNullOrEmpty(code)) {
            return ResultDTO.fail(ApiConstant.E_APP_CODE_EMPTY);
        }
        if (Strings.isNullOrEmpty(mobile)) {
            return ResultDTO.fail(ApiConstant.E_APP_PHONE_EMPTY);
        }
        if (MsgCodeMap.get(mobile + deviceId) == null) {
            return ResultDTO.fail(ApiConstant.E_APP_CODE_ERROR);
        }
        //从Map中根据手机号取到存入的验证码
        SmsCodeDTO codeDto = MsgCodeMap.get(mobile + deviceId);
        if (null == codeDto) {
            return ResultDTO.fail(ApiConstant.E_APP_CODE_ERROR);
        }
        //时间
        long newTime = System.currentTimeMillis();
        //验证码超过30分钟
        if ((newTime - codeDto.getTime()) / 1000 / 60 > 30) {
            MsgCodeMap.remove(mobile + deviceId);
            return ResultDTO.fail(ApiConstant.E_CODEOVERTIME_ERROR);
        }
        //验证码正确
        if (code.equals(codeDto.getCode())) {
            MsgCodeMap.remove(mobile + deviceId);
            return ResultDTO.success();
        }
        return ResultDTO.fail(ApiConstant.E_APP_CODE_ERROR);
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
        String deviceToken=null;
        String deviceId=null;
        Integer deviceType=null;
        if (!appUserDao.updateDeviceTokenById(id,deviceToken,deviceId,deviceType)){
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
        List<AppUser> items=appUserDao.queryBydeviceToken(appUserDTO.getDeviceToken());
        if(items.isEmpty()){
            logger.warn("该设备之前没有注册过账号或该设备之前注册的账号已注销");
        }
        for(AppUser item:items){
            String deviceToken=null;
            String deviceId=null;
            Integer deviceType=null;
            if (!appUserDao.updateDeviceToken(item.getMobile(),deviceToken,deviceId,deviceType)) {
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
         if(!Strings.isNullOrEmpty(headImagePath)){
            String path = headImagePath.substring(headImagePath.indexOf("^")+1);
            map.put("headImagePath",OssUtil.getForeverFileUrl(OssUtil.getHeadBucketName(), path));
         }else{
            map.put("headImagePath",""); 
         }
        map.put("userName",appUser.getUserName()); 
        map.put("mobile", appUser.getMobile());
        map.put("sex",appUser.getSex());
        map.put("appUserId", appUser.getId());
        //查询用户绑定商户数量 根据用户id查询数量
        int merchantNums = 0;
        List<MerchantUserRel> rel = merchantUserRelDao.selectByUserId(appUser.getId());
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
        List<SysMsgAppSucc> unReadInfo =  sysMsgAppSuccDao.selectTotalUnread(appUserId);
        List<Integer> unReadMsgIds = Lists.newArrayList();
        for (SysMsgAppSucc sysMsgAppSucc : unReadInfo) {
            unReadMsgIds.add(sysMsgAppSucc.getMsgId());
        }
        map.put("unReadMsgIds", unReadMsgIds);
        map.put("Shopkeeper", Shopkeeper);
        return ResultDTO.success(map);
    }

    //退出登录
    @Override
    public ResultDTO<String> loginOut(AppUserDTO appUserDTO) {
        //更新到实体
        Integer id=appUserDTO.getUserId();
        String deviceToken=null;
        String deviceId=null;
        Integer deviceType=null;
        if (!appUserDao.updateDeviceTokenById(id,deviceToken,deviceId,deviceType)){
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
               if(appUserMerchant == null){
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
                if(appUserMerchant==null){
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
              if(appUserMerchant!=null){
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
    public void addAppUserMerchantRole(AppUserMerchant dto){
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
        if(!flag){
            return ResultDTO.fail(ApiConstant.E_UPDATE_FAIL); 
        }
        return ResultDTO.success();
        
    }

    //获取个人信息
    @Override
    public ResultDTO<String> getUserInfo(AppUserDTO appUserDTO) {
        if(appUserDTO.getUserId()==null){
           return ResultDTO.fail(ApiConstant. E_USER_ID_NULL);
        }
        AppUser appUser=appUserDao.selectAppUserById(appUserDTO.getUserId());
        AppUserInfoDTO dto=new AppUserInfoDTO();
        dto.setSex(appUser.getSex());
        dto.setUserName(appUser.getUserName());
        String headImagePath = appUser.getHeadImagePath();
        if(!Strings.isNullOrEmpty(headImagePath)){
            String path = headImagePath.substring(headImagePath.indexOf("^")+1);
            dto.setHeadImagePath(OssUtil.getForeverFileUrl(OssUtil.getHeadBucketName(), path));
        }
        dto.setMoblie(appUser.getMobile());
        dto.setRealName(appUser.getRealName());
        return ResultDTO.success(dto);
    }
}
