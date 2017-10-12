package net.fnsco.order.service.act;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.utils.SmsUtil;
import net.fnsco.order.service.act.dao.ApplyUserDAO;
import net.fnsco.order.service.act.entity.ApplyUserDO;

@Service
public class ApplyUserService extends BaseService {

    private Logger       logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ApplyUserDAO applyUserDAO;
    @Autowired
    private Environment  env;

    // 添加
    public ApplyUserDO doAdd(ApplyUserDO applyUser) {
        logger.info("开始添加ApplyUserService.add,applyUser=" + applyUser.toString());
        this.applyUserDAO.insert(applyUser);
        //发送短信
        String mobile = env.getProperty("act.customer.mobile");
        
        try {
            String callback = SmsUtil.applyUser(mobile, applyUser.getUserName(), applyUser.getContactNum(), applyUser.getMercName());
        } catch (IOException e) {
            logger.error("邀请申请用户报名发送短信失败",e);
        }
        return applyUser;
    }

    // 修改
    public Integer doUpdate(ApplyUserDO applyUser, Integer loginUserId) {
        logger.info("开始修改ApplyUserService.update,applyUser=" + applyUser.toString());
        int rows = this.applyUserDAO.update(applyUser);
        return rows;
    }

    // 删除
    public Integer doDelete(ApplyUserDO applyUser, Integer loginUserId) {
        logger.info("开始删除ApplyUserService.delete,applyUser=" + applyUser.toString());
        int rows = this.applyUserDAO.deleteById(applyUser.getId());
        return rows;
    }

    // 查询
    public ApplyUserDO doQueryById(Integer id) {
        ApplyUserDO obj = this.applyUserDAO.getById(id);
        return obj;
    }
}