package net.fnsco.service.modules.sysappmsg;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.fnsco.api.dto.AppPushMsgInfoDTO;
import net.fnsco.api.push.AppPushService;
import net.fnsco.api.sysappmsg.SysAppMsgService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.PageDTO;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.freamwork.spring.SpringUtils;
import net.fnsco.service.dao.master.SysAppMessageDao;
import net.fnsco.service.domain.SysAppMessage;
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
        message.setContent(record.toString());
        message.setBusType(1);
        message.setMsgType(1);
        String sendTime = sdf.format(date);//定义传递给友盟的时间
        message.setSendTime(date);
        //广播推送
            // ios
        try {
            int iosStatus = appPushService.sendIOSBroadcast(record.getMsgSubtitle(), sendTime);
            if (iosStatus == 200) {
                logger.warn("ios信息推送成功");
                message.setStatus(1);
            } else {
                logger.warn("ios信息推送失败");
                message.setStatus(0);
            }
           message.setPhoneType(1);
           sysAppMessageDao.insertSelective(message);

            int androidStatus = appPushService.sendAndroidBroadcast(record.getMsgSubtitle(), sendTime);
            if (androidStatus == 200) {
                logger.warn("安卓信息推送成功");
                message.setStatus(1);
            } else {
                logger.warn("安卓信息推送失败");
                message.setStatus(0);
            }
            message.setPhoneType(2);
            sysAppMessageDao.insertSelective(message);
        } catch (Exception e) {
            logger.error("广播推送异常", e);
        }
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

}
