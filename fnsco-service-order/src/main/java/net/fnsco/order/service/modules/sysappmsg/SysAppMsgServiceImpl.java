package net.fnsco.order.service.modules.sysappmsg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import net.fnsco.bigdata.service.dao.master.MerchantCoreDao;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.constants.CoreConstants;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.OssLoaclUtil;
import net.fnsco.freamwork.spring.SpringUtils;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.dto.AppPushMsgInfoDTO;
import net.fnsco.order.api.dto.PushMsgInfoDTO;
import net.fnsco.order.api.sysappmsg.SysAppMsgService;
import net.fnsco.order.service.dao.master.AppUserDao;
import net.fnsco.order.service.dao.master.AppUserMerchantDao;
import net.fnsco.order.service.dao.master.SysAppMessageDao;
import net.fnsco.order.service.dao.master.SysMsgAppSuccDao;
import net.fnsco.order.service.domain.AppUser;
import net.fnsco.order.service.domain.AppUserMerchant;
import net.fnsco.order.service.domain.SysAppMessage;
import net.fnsco.order.service.domain.SysUser;
import net.fnsco.order.service.modules.push.AppPushHelper;

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
    private AppUserDao    appUserDao;
    
    @Autowired
    private MerchantCoreDao merchantCoreDao;
    
    @Autowired
    private SysMsgAppSuccDao sysMsgAppSuccDao;
    
    @Autowired
    private AppUserMerchantDao appUserMerchantDao;
    
    @Autowired
    private AppPushHelper appPushHelper;

    /**
     * (non-Javadoc)
     * @see net.fnsco.order.api.sysappmsg.SysAppMsgService#deleteByPrimaryKey(java.lang.Integer)
     * @auth tangliang
     * @date 2017年7月12日 上午9:48:03
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {

        return sysAppMessageDao.deleteByPrimaryKey(id);

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.order.api.sysappmsg.SysAppMsgService#insert(net.fnsco.order.service.domain.SysAppMessage)
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
     * @see net.fnsco.order.api.sysappmsg.SysAppMsgService#insertSelective(net.fnsco.order.service.domain.SysAppMessage)
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
     * @see net.fnsco.order.api.sysappmsg.SysAppMsgService#selectByPrimaryKey(java.lang.Integer)
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
     * @see net.fnsco.order.api.sysappmsg.SysAppMsgService#updateByPrimaryKeySelective(net.fnsco.order.service.domain.SysAppMessage)
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
     * @see net.fnsco.order.api.sysappmsg.SysAppMsgService#updateByPrimaryKey(net.fnsco.order.service.domain.SysAppMessage)
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
     * @see net.fnsco.order.api.sysappmsg.SysAppMsgService#queryListByCondition(net.fnsco.order.service.domain.SysAppMessage)
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
     * @see net.fnsco.order.api.sysappmsg.SysAppMsgService#queryExecuteData()
     * @auth tangliang
     * @date 2017年7月12日 下午4:38:35
     */
    @Override
    public List<SysAppMessage> queryExecuteData() {
        
        return sysAppMessageDao.queryExecuteData();
        
    }
    /**
     * 查询待未过期的活动列表
     * (non-Javadoc)
     * @see net.fnsco.order.api.sysappmsg.SysAppMsgService#queryExecuteData()
     * @auth tangliang
     * @date 2017年7月12日 下午4:38:35
     */
    @Override
    public List<SysAppMessage> queryActivityIng(String msgType) {
        
        return sysAppMessageDao.queryActivityIng(msgType);
        
    }
    /**
     * (non-Javadoc) 条件分页查询
     * @see net.fnsco.order.api.sysappmsg.SysAppMsgService#queryPageList(net.fnsco.order.service.domain.SysAppMessage, int, int)
     * @auth tangliang
     * @date 2017年7月12日 上午9:48:03
     */
    @Override
    public ResultPageDTO<SysAppMessage> queryPageList(SysAppMessage record, int currentPageNum, int perPageSize) {
        record.setBusType(1);
        if(StringUtils.isNoneEmpty(record.getStartSendTime())){
            record.setStartSendTime(record.getStartSendTime()+" 00:00:00");
        }
        if(StringUtils.isNoneEmpty(record.getEndSendTime())){
            record.setEndSendTime(record.getEndSendTime()+" 23:59:59");
        }
        PageDTO<SysAppMessage> params = new PageDTO<SysAppMessage>(currentPageNum, perPageSize, record);
        List<SysAppMessage> data = sysAppMessageDao.queryPageList(params);
        int totalNum = sysAppMessageDao.queryTotalByCondition(record);
        ResultPageDTO<SysAppMessage> result = new ResultPageDTO<SysAppMessage>(totalNum, data);
        return result;
    }

    /**
     * (non-Javadoc) 新增加消息推送处理实现
     * @see net.fnsco.order.api.sysappmsg.SysAppMsgService#doAddMsg(net.fnsco.order.service.domain.SysAppMessage)
     * @auth tangliang
     * @date 2017年7月12日 上午9:59:32
     */
    @Transactional
    @Override
    public ResultDTO<String> doAddMsg(AppPushMsgInfoDTO record) {
        
        SysAppMessage message = new SysAppMessage();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (StringUtils.isEmpty(record.getSendTimeStr())) {
            return ResultDTO.fail(CoreConstants.WEB_PUSH_DATE_ERROR);
        }
        record.setSendTimeStr(record.getSendTimeStr()+":00");
        try {
            record.setSendTime(sdf.parse(record.getSendTimeStr()));
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        String currTime = sdf.format(new Date());
        int timeStatus = DateUtils.compare_date(record.getSendTimeStr(), currTime);
        if (timeStatus == 2 || timeStatus == -1) {
            return ResultDTO.fail(CoreConstants.WEB_PUSH_DATE_ERROR);
        }
        message.setSendType(2);
        message.setPushType(2);
        Date date = new Date();
        SysUser sysUser = (SysUser) SpringUtils.getRequest().getSession().getAttribute("SESSION_USER_KEY");
        message.setModifyUserId(sysUser.getId());
        message.setModifyTime(date); //创建时间
        message.setBusType(1);
        message.setMsgType(1);
        try {
            message.setSendTime(sdf.parse(record.getSendTimeStr()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        message.setDetailUrl(record.getDetailURL());
        message.setImageUrl(record.getImageURL());
        message.setMsgSubject(record.getMsgSubject());
        message.setMsgSubTitle(record.getMsgSubtitle());
        message.setStatus(0); //待发送
        sysAppMessageDao.insertSelective(message);
        return ResultDTO.successForSave(null);

    }

    /**
     * (non-Javadoc) 更新单条数据
     * @see net.fnsco.order.api.sysappmsg.SysAppMsgService#deleteMsg(java.lang.Integer)
     * @auth tangliang
     * @date 2017年7月12日 上午10:12:33
     */
    @Override
    public ResultDTO<String> deleteMsg(Integer id) {

        if (null == id) {
            return ResultDTO.fail();
        }
        SysAppMessage record = new SysAppMessage();
        record.setId(id);
        record.setStatus(2);
        int res = sysAppMessageDao.updateStatusByIdAndStatus(record);
        if (res != 0) {
            return ResultDTO.success();
        }
        return ResultDTO.fail();

    }
    
    /**
     * (non-Javadoc) 获取消息列表
     * @see net.fnsco.order.api.sysappmsg.SysAppMsgService#queryMsgList(java.lang.Integer, boolean)
     * @auth tangliang
     * @date 2017年7月12日 下午1:45:55
     */
    @Transactional
    @Override
    public ResultDTO<ResultPageDTO<AppPushMsgInfoDTO>> queryMsgList(Integer userId, boolean hasRead,Integer phoneType,Integer currentPageNum,Integer perPageSize) {
        if(null == userId){
            return ResultDTO.fail(ApiConstant.E_USERID_NULL);
        }
        /**
         * 根据记录时间查询出未读数据
         */
//        SysAppMessage condition = new SysAppMessage();
//        SysMsgReceiver condition1 = new SysMsgReceiver();
////        condition.setPhoneType(phoneType);
//        condition.setStatus(1);
//        condition1.setAppUserId(userId);
//        List<SysAppMessage> datas = sysAppMessageDao.queryListByCondition(condition);
//        List<SysMsgReceiver> datas1 =  sysMsgReceiverDao.queryListByCondition(condition1);
//        List<AppPushMsgInfoDTO> result = Lists.newArrayList();
////        for (SysAppMessage sysAppMessage : datas) {
////            result.add(JsonPluginsUtil.jsonToBean(sysAppMessage.getContentJson(), AppPushMsgInfoDTO.class));
////        }
//        for (SysMsgReceiver sysMsgReceiver : datas1) {
//            result.add(JsonPluginsUtil.jsonToBean(sysMsgReceiver.getMsgContent(), AppPushMsgInfoDTO.class));
//        }
        ResultPageDTO<AppPushMsgInfoDTO> resultPage = new ResultPageDTO<AppPushMsgInfoDTO>(perPageSize, null);
//        //按照时间排序
//        Collections.sort(result);
//        resultPage.setTotal(result.size());
//        resultPage.setCurrentPage(currentPageNum);
//        if(result.size() >= currentPageNum*perPageSize){
//            result = result.subList((currentPageNum-1)*perPageSize, currentPageNum*perPageSize);
//        }else if(result.size() < currentPageNum*perPageSize && currentPageNum != 1){
//            result.clear();
//        }
//        List<AppPushMsgInfoDTO> resultDate = Lists.newArrayList();
//        //重新获取图片路径
//        for (AppPushMsgInfoDTO appPushMsgInfoDTO : result) {
//            if(StringUtils.isNotEmpty(appPushMsgInfoDTO.getImageURL())){
//                String path = appPushMsgInfoDTO.getImageURL().substring(appPushMsgInfoDTO.getImageURL().indexOf("^")+1);
//                String imageURL =  OssUtil.getFileUrl(OssUtil.getHeadBucketName(), path);
//                appPushMsgInfoDTO.setImageURL(imageURL);
//            }
//            resultDate.add(appPushMsgInfoDTO);
//        }
//        resultPage.setList(resultDate);
//        
//        /**
//         * 再次记录读取时间
//         */
//        if(hasRead){
//            MsgRead reader = msgReadDao.selectByUserId(userId);
//            Date readTime = new Date();
//            if(reader == null){
//                reader = new MsgRead();
//                reader.setAppUserId(userId);
//                reader.setReadTime(readTime);
//                msgReadDao.insertSelective(reader);
//            }else{
//                reader.setReadTime(readTime);
//                msgReadDao.updateByPrimaryKeySelective(reader);
//            }
//        }
        
        return ResultDTO.success(resultPage);
        
    }
    /**
     * (non-Javadoc) 店员加入商家通知
     * @see net.fnsco.order.api.sysappmsg.SysAppMsgService#pushMerChantMsg(java.lang.String, java.lang.Integer)
     * @auth tangliang
     * @date 2017年7月13日 上午9:15:18
     */
    @Override
    public void pushMerChantMsg(String innerCode, Integer userId) {
        //推送给该商户的其他所有成员
        List<AppUserMerchant> merInfos = appUserMerchantDao.selectByInnerCode(innerCode,null);
        if(CollectionUtils.isEmpty(merInfos)){
            logger.warn(innerCode+"该店没有成员!");
            return;
        }
       //发送推送
        String newPhone = "";
        MerchantCore merchantCore = merchantCoreDao.selectByInnerCode(innerCode);
        AppUser appUser = appUserDao.selectAppUserById(userId);
        newPhone = appUser.getMobile();
        String msgContent = "新店员"+newPhone+"加入了"+merchantCore.getMerName()+"店";
        SysAppMessage message = appPushHelper.insertIntoDBSysAppMessage(msgContent, "新店员加入",1);
        
        if(message.getId() != null){
            //组装推送信息
            for (AppUserMerchant merInfo : merInfos) {
                AppUser user = appUserDao.selectAppUserById(merInfo.getAppUserId());
                Integer deviceType = user.getDeviceType();
                if(null == deviceType ||deviceType == 0||user.getId().equals(userId)){
                    continue;
                }
                //推送IOS和android消息
                appPushHelper.pushNewMessage(user, message,false);
            }
        }
        
    }
    /**
     * (non-Javadoc)查询新消息条数
     * @see net.fnsco.order.api.sysappmsg.SysAppMsgService#queryNewsCount(java.lang.Integer, java.lang.Integer)
     * @auth tangliang
     * @date 2017年7月13日 下午4:49:57
     */
    @Override
    public ResultDTO<PushMsgInfoDTO> queryNewsCount(Integer userId, boolean hasRead,Integer phoneType) {
        
        if(null == userId){
            return ResultDTO.fail(ApiConstant.E_USERID_NULL);
        }
        if(null == phoneType || (phoneType != 1 && phoneType!=2)){
            return ResultDTO.fail(ApiConstant.E_PHONETYPE_ERROR);
        }
        PushMsgInfoDTO msgInfoDTO = new PushMsgInfoDTO();
        msgInfoDTO.setUnReadCount(sysMsgAppSuccDao.selectTotalUnreadCount(userId));
        return ResultDTO.success(msgInfoDTO);
    }
    
    /**
     * (non-Javadoc)根据ID查询列表
     * @see net.fnsco.order.api.sysappmsg.SysAppMsgService#queryList(java.util.List)
     * @auth tangliang
     * @date 2017年7月19日 下午4:08:17
     */
    @Transactional
    @Override
    public ResultDTO<List<AppPushMsgInfoDTO>> queryListByIds(List<Integer> ids) {
        List<SysAppMessage> datas = sysAppMessageDao.queryListByIds(ids);
        List<AppPushMsgInfoDTO> data = Lists.newArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (SysAppMessage sysAppMessage : datas) {
            AppPushMsgInfoDTO dtoInfo = new AppPushMsgInfoDTO();
            dtoInfo.setId(sysAppMessage.getId());
            dtoInfo.setDetailURL(sysAppMessage.getDetailUrl());
            dtoInfo.setMsgType(sysAppMessage.getMsgType().toString());
            String pathUrl  = sysAppMessage.getImageUrl();
            if(!Strings.isNullOrEmpty(pathUrl)){
                String path = pathUrl.substring(pathUrl.indexOf("^")+1);
                dtoInfo.setImageURL(OssLoaclUtil.getForeverFileUrl(OssLoaclUtil.getHeadBucketName(), path));
            }
            
            dtoInfo.setMsgSubtitle(sysAppMessage.getMsgSubTitle());
            dtoInfo.setMsgSubject(sysAppMessage.getMsgSubject());
            dtoInfo.setSendTimeStr(sdf.format(sysAppMessage.getSendTime()));
          //推算周报时间
            String weekDateStr = dtoInfo.getSendTimeStr().substring(0, 10);
            dtoInfo.setWeeklyStartDate(DateUtils.getMonDateStr(weekDateStr));
            dtoInfo.setWeeklyEndDate(DateUtils.getSunDateStr(weekDateStr));
            data.add(dtoInfo);
        }
        //更新成功消息状态
        sysMsgAppSuccDao.updateByMsgIds(ids);
        return ResultDTO.success(data);
        
    }
}
