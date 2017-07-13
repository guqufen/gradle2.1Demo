package net.fnsco.service.modules.sysappmsg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import net.fnsco.api.constant.ApiConstant;
import net.fnsco.api.dto.AppPushMsgInfoDTO;
import net.fnsco.api.push.AppPushService;
import net.fnsco.api.sysappmsg.SysAppMsgService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.JsonPluginsUtil;
import net.fnsco.freamwork.spring.SpringUtils;
import net.fnsco.service.dao.master.AppUserDao;
import net.fnsco.service.dao.master.MerchantCoreDao;
import net.fnsco.service.dao.master.MsgReadDao;
import net.fnsco.service.dao.master.SysAppMessageDao;
import net.fnsco.service.dao.master.SysMsgReceiverDao;
import net.fnsco.service.domain.AppUser;
import net.fnsco.service.domain.MerchantCore;
import net.fnsco.service.domain.MsgRead;
import net.fnsco.service.domain.SysAppMessage;
import net.fnsco.service.domain.SysMsgReceiver;
import net.fnsco.service.domain.SysUser;

/**
 * @desc app推送消息服务类实现
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月12日 上午9:48:03
 */
@Service
public class SysAppMsgServiceImpl extends BaseService implements SysAppMsgService {

    @Autowired
    private SysAppMessageDao sysAppMessageDao;
    
    @Autowired
    private AppPushService appPushService;
    
    @Autowired
    private MsgReadDao msgReadDao;
    
    @Autowired
    private SysMsgReceiverDao sysMsgReceiverDao;
    
    @Autowired
    private AppUserDao    appUserDao;
    
    @Autowired
    private MerchantCoreDao merchantCoreDao;

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.sysappmsg.SysAppMsgService#deleteByPrimaryKey(java.lang.Integer)
     * @auth tangliang
     * @date 2017年7月12日 上午9:48:03
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {

        return sysAppMessageDao.deleteByPrimaryKey(id);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.sysappmsg.SysAppMsgService#insert(net.fnsco.service.domain.SysAppMessage)
     * @auth tangliang
     * @date 2017年7月12日 上午9:48:03
     */
    @Override
    public int insert(SysAppMessage record) {

        // TODO Auto-generated method stub
        return sysAppMessageDao.insert(record);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.sysappmsg.SysAppMsgService#insertSelective(net.fnsco.service.domain.SysAppMessage)
     * @auth tangliang
     * @date 2017年7月12日 上午9:48:03
     */
    @Override
    public int insertSelective(SysAppMessage record) {

        // TODO Auto-generated method stub
        return sysAppMessageDao.insertSelective(record);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.sysappmsg.SysAppMsgService#selectByPrimaryKey(java.lang.Integer)
     * @auth tangliang
     * @date 2017年7月12日 上午9:48:03
     */
    @Override
    public SysAppMessage selectByPrimaryKey(Integer id) {

        // TODO Auto-generated method stub
        return sysAppMessageDao.selectByPrimaryKey(id);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.sysappmsg.SysAppMsgService#updateByPrimaryKeySelective(net.fnsco.service.domain.SysAppMessage)
     * @auth tangliang
     * @date 2017年7月12日 上午9:48:03
     */
    @Override
    public int updateByPrimaryKeySelective(SysAppMessage record) {

        // TODO Auto-generated method stub
        return sysAppMessageDao.updateByPrimaryKeySelective(record);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.sysappmsg.SysAppMsgService#updateByPrimaryKey(net.fnsco.service.domain.SysAppMessage)
     * @auth tangliang
     * @date 2017年7月12日 上午9:48:03
     */
    @Override
    public int updateByPrimaryKey(SysAppMessage record) {

        // TODO Auto-generated method stub
        return sysAppMessageDao.updateByPrimaryKey(record);

    }
    
    /**
     * 条件查询
     * (non-Javadoc)
     * @see net.fnsco.api.sysappmsg.SysAppMsgService#queryListByCondition(net.fnsco.service.domain.SysAppMessage)
     * @auth tangliang
     * @date 2017年7月12日 下午4:27:36
     */
    @Override
    public List<SysAppMessage> queryListByCondition(SysAppMessage record) {
        
        // TODO Auto-generated method stub
        return sysAppMessageDao.queryListByCondition(record);
        
    }
    /**
     * 查询待执行推送任务的数据
     * (non-Javadoc)
     * @see net.fnsco.api.sysappmsg.SysAppMsgService#queryExecuteData()
     * @auth tangliang
     * @date 2017年7月12日 下午4:38:35
     */
    @Override
    public List<SysAppMessage> queryExecuteData() {
        
        return sysAppMessageDao.queryExecuteData();
        
    }
    /**
     * (non-Javadoc) 条件分页查询
     * @see net.fnsco.api.sysappmsg.SysAppMsgService#queryPageList(net.fnsco.service.domain.SysAppMessage, int, int)
     * @auth tangliang
     * @date 2017年7月12日 上午9:48:03
     */
    @Override
    public ResultPageDTO<SysAppMessage> queryPageList(SysAppMessage record, int currentPageNum, int perPageSize) {

        PageDTO<SysAppMessage> params = new PageDTO<SysAppMessage>(currentPageNum, perPageSize, record);
        List<SysAppMessage> data = sysAppMessageDao.queryPageList(params);
        int totalNum = sysAppMessageDao.queryTotalByCondition(record);
        ResultPageDTO<SysAppMessage> result = new ResultPageDTO<SysAppMessage>(totalNum, data);
        return result;
    }

    /**
     * (non-Javadoc) 新增加消息推送处理实现
     * @see net.fnsco.api.sysappmsg.SysAppMsgService#doAddMsg(net.fnsco.service.domain.SysAppMessage)
     * @auth tangliang
     * @date 2017年7月12日 上午9:59:32
     */
    @Transactional
    @Override
    public ResultDTO<String> doAddMsg(AppPushMsgInfoDTO record) {
        
        SysAppMessage message = new SysAppMessage();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (null == record.getSendTime()) {
            return ResultDTO.fail();
        }
        String currTime = sdf.format(new Date());
        String getPage = sdf.format(record.getSendTime());
        int timeStatus = DateUtils.compare_date(getPage, currTime);
        if (timeStatus == 2 || timeStatus == -1) {
            return ResultDTO.fail();
        }
        message.setSendType(2);
        message.setPushType(2);
        Date date = new Date();
        SysUser sysUser = (SysUser) SpringUtils.getRequest().getSession().getAttribute("SESSION_USER_KEY");
        message.setModifyUserId(sysUser.getId());
        message.setModifyTime(date); //创建时间
        message.setContent(record.getMsgSubtitle());
        message.setContentJson(record.toString());
        message.setBusType(1);
        message.setMsgType(1);
        message.setSendTime(date);
        message.setStatus(2); 
        message.setPhoneType(1);
        sysAppMessageDao.insertSelective(message);
        message.setPhoneType(2);
        sysAppMessageDao.insertSelective(message);
        return ResultDTO.successForSave(null);

    }

    /**
     * (non-Javadoc) 删除单条数据
     * @see net.fnsco.api.sysappmsg.SysAppMsgService#deleteMsg(java.lang.Integer)
     * @auth tangliang
     * @date 2017年7月12日 上午10:12:33
     */
    @Override
    public ResultDTO<String> deleteMsg(Integer id) {

        if (null == id) {
            return ResultDTO.fail();
        }
        int res = sysAppMessageDao.deleteByPrimaryKey(id);
        if (res != 0) {
            ResultDTO.success();
        }
        return ResultDTO.fail();

    }
    
    /**
     * (non-Javadoc) 获取消息列表
     * @see net.fnsco.api.sysappmsg.SysAppMsgService#queryMsgList(java.lang.Integer, boolean)
     * @auth tangliang
     * @date 2017年7月12日 下午1:45:55
     */
    @Transactional
    @Override
    public ResultDTO<List<AppPushMsgInfoDTO>> queryMsgList(Integer userId, boolean hasRead) {
        if(null == userId){
            return ResultDTO.fail(ApiConstant.E_USERID_NULL);
        }
        MsgRead reader = msgReadDao.selectByUserId(userId);
        Date date = null;
        if(null != reader){
            date = reader.getReadTime();
        }
        /**
         * 根据记录时间查询出未读数据
         */
        SysAppMessage condition = new SysAppMessage();
        SysMsgReceiver condition1 = new SysMsgReceiver();
        condition.setSendTime(date);
        condition1.setSendTime(date);
        condition1.setAppUserId(userId);
        List<SysAppMessage> datas = sysAppMessageDao.queryListByCondition(condition);
        List<SysMsgReceiver> datas1 =  sysMsgReceiverDao.queryListByCondition(condition1);
        List<AppPushMsgInfoDTO> result = Lists.newArrayList();
        for (SysAppMessage sysAppMessage : datas) {
            result.add(JsonPluginsUtil.jsonToBean(sysAppMessage.getContentJson(), AppPushMsgInfoDTO.class));
        }
        for (SysMsgReceiver sysMsgReceiver : datas1) {
            result.add(JsonPluginsUtil.jsonToBean(sysMsgReceiver.getMsgContent(), AppPushMsgInfoDTO.class));
        }
        
        /**
         * 再次记录读取时间
         */
        if(hasRead){
            Date readTime = new Date();
            if(reader == null){
                reader = new MsgRead();
                reader.setAppUserId(userId);
                reader.setReadTime(readTime);
                msgReadDao.insertSelective(reader);
            }else{
                reader.setReadTime(readTime);
                msgReadDao.updateByPrimaryKeySelective(reader);
            }
        }
        
        return ResultDTO.success(result);
        
    }
    /**
     * (non-Javadoc) 店员加入商家通知
     * @see net.fnsco.api.sysappmsg.SysAppMsgService#pushMerChantMsg(java.lang.String, java.lang.Integer)
     * @auth tangliang
     * @date 2017年7月13日 上午9:15:18
     */
    @Override
    public void pushMerChantMsg(String innerCode, Integer userId) {
        
      //发送推送
        List<AppUser> users = appUserDao.selectByInnerCode(innerCode);
        String param_and ="";
        String param_ios ="";
        String newPhone = "";
        Set<String> relatelds_and = new HashSet<String>();
        Set<String> relatelds_ios = new HashSet<String>();
        for (AppUser user : users) {
            int deviceType = user.getDeviceType();
            if(String.valueOf(deviceType)==null||"0".equals(deviceType)||user.getId() == userId){
                continue;
            }else if(deviceType==1){//安卓
                if(StringUtils.isNotBlank(user.getDeviceToken())){
                    relatelds_and.add(user.getDeviceToken());
                }
            }else if(deviceType==2){//ios
                if(StringUtils.isNotBlank(user.getDeviceToken())){
                    relatelds_ios.add(user.getDeviceToken());
                }
            }
            if(user.getId() == userId){
                newPhone =user.getMobile();
            }
            
        }
        param_and = relatelds_and.toString().substring(1, param_and.length()-1);
        param_and = param_and.replaceAll(" ", "");//安卓去重后token
        
        param_ios = relatelds_ios.toString().substring(1, param_ios.length()-1);
        param_ios = param_ios.replaceAll(" ", "");//ios去重后的token
        MerchantCore merchantCore = merchantCoreDao.selectByInnerCode(innerCode);
        String msgContent = "【新店员加入】新店员"+newPhone+"加入"+merchantCore.getMerName()+"店";
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sendTime = sdf.format(new Date());
        /**
         * IOS
         */
        try {
            int status = appPushService.sendIOSListcast(param_ios, msgContent,sendTime);
            if (status == 200) {
                logger.info("ios信息推送成功");
            } else {
                logger.info("ios信息推送失败");
            }
            
        } catch (Exception e) {
            logger.error("ios列播推送"+e);
        }
        /**
         * android
         */
        try {
            int status = appPushService.sendAndroidListcast(param_and, msgContent,sendTime);
            if (status == 200) {
                logger.info("安卓信息推送成功");
            } else {
                logger.info("安卓信息推送失败");
            }
        } catch (Exception e) {
            logger.error("android列播推送："+e);
        } 
       
        try {
            SysMsgReceiver sysMsgReceiver = new SysMsgReceiver();
            sysMsgReceiver.setAppUserId(userId);
            sysMsgReceiver.setMsgContent(msgContent);
            sysMsgReceiver.setSendTime(sdf.parse(sendTime));
            sysMsgReceiverDao.insertSelective(sysMsgReceiver);
        } catch (ParseException e) {
            logger.error("列播推送消息日期转换出错："+e);
        }
        
    }

}
