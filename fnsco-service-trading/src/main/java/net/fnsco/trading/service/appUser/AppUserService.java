package net.fnsco.trading.service.appUser;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import net.fnsco.trading.service.appUser.dao.AppUserDAO;
import net.fnsco.trading.service.appUser.entity.AppUserDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.OssLoaclUtil;
import net.fnsco.freamwork.comm.Md5Util;/*
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.dto.AppUserDTO;
import net.fnsco.order.api.dto.AppUserSettingDTO;
import net.fnsco.order.service.domain.AppUser;
import net.fnsco.order.service.domain.AppUserMerchant;
import net.fnsco.order.service.domain.SysMsgAppSucc*/;

@Service
public class AppUserService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private AppUserDAO appUserDAO;

 // 分页
 public ResultPageDTO<AppUserDO> page(AppUserDO appUser, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询AppUserService.page, appUser=" + appUser.toString());
     List<AppUserDO> pageList = this.appUserDAO.pageList(appUser, pageNum, pageSize);
     Integer count = this.appUserDAO.pageListCount(appUser);
   ResultPageDTO<AppUserDO> pager =  new ResultPageDTO<AppUserDO>(count,pageList);
     return pager;
 }

 // 添加
 public AppUserDO doAdd (AppUserDO appUser,int loginUserId) {
     logger.info("开始添加AppUserService.add,appUser=" + appUser.toString());
     this.appUserDAO.insert(appUser);
     return appUser;
 }

 // 修改
 public Integer doUpdate (AppUserDO appUser,Integer loginUserId) {
     logger.info("开始修改AppUserService.update,appUser=" + appUser.toString());
     int rows=this.appUserDAO.update(appUser);
     return rows;
 }

 // 删除
 public Integer doDelete (AppUserDO appUser,Integer loginUserId) {
     logger.info("开始删除AppUserService.delete,appUser=" + appUser.toString());
     int rows=this.appUserDAO.deleteById(appUser.getId());
     return rows;
 }

 // 查询
 public AppUserDO doQueryById (Integer id) {
     AppUserDO obj = this.appUserDAO.getById(id);
     return obj;
 }
 
/*//根据手机号码和密码登录
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
     String headImagePath = appUser.getHeadImagePath();
     if (!Strings.isNullOrEmpty(headImagePath)) {
         String path = headImagePath.substring(headImagePath.indexOf("^") + 1);
         map.put("headImagePath", OssLoaclUtil.getForeverFileUrl(OssLoaclUtil.getHeadBucketName(), path));
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
 */
}