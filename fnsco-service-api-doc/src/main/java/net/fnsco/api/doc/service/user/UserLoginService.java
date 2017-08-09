package net.fnsco.api.doc.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.api.doc.service.user.dao.UserLoginDAO;
import net.fnsco.api.doc.service.user.entity.UserLoginDO;
import net.fnsco.core.base.BaseService;

/**
 * 
		* <p>Title: 用户登陆信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:40:48</p>
 */
@Service
public class UserLoginService extends BaseService {
    @Autowired
    private UserLoginDAO userLoginDAO;

    //    public int countDayLogin(Date date) {
    //        return userBasicDAO.countDayLogin(DateUtil.getDayStart(date), DateUtil.getDayEnd(date));
    //    }
    //
    //    public int countDayOldLogin(Date date) {
    //        Date dayStart = DateUtil.getDayStart(date);
    //        return userBasicDAO.countDayOldLogin(dayStart, DateUtil.getDayEnd(date), dayStart);
    //    }
    //
    //    public int countOnline() {
    //        return userBasicDAO.countOnline();
    //    }

    public UserLoginDO getByUserId(Long userId) {
        return userLoginDAO.getByUserId(userId);
    }

    public void resetLoginFailCount() {
        userLoginDAO.resetLoginFailCount();
    }

    public void update(UserLoginDO userLogin) {
        userLoginDAO.update(userLogin);
    }
}
