package net.fnsco.api.doc.service.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.api.doc.service.user.dao.UserBasicDAO;
import net.fnsco.api.doc.service.user.dao.UserDetailDAO;
import net.fnsco.api.doc.service.user.entity.UserDetailDO;
import net.fnsco.api.doc.service.vo.UserInfo;
import net.fnsco.core.base.BaseService;

/**
 * 
		* <p>Title: 用户详细信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:40:48</p>
 */
@Service
public class UserDetailService extends BaseService {
    @Autowired
    private UserDetailDAO userDetailDAO;
    @Autowired
    private UserBasicDAO  userBasicDAO;

    public UserDetailDO getByUserId(Long userId) {
        return userDetailDAO.getByUserId(userId);
    }

    public UserInfo getDetailByUserId(Integer userId) {
        Map<String, Object> info = userBasicDAO.getDetailByUserId(userId);
        return parseDetailInfo(info);
    }

    //组装用户详情
    private UserInfo parseDetailInfo(Map<String, Object> info) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId((Long) info.get("userId"));
        userInfo.setEmail((String) info.get("email"));
        userInfo.setNickName((String) info.get("nickName"));
        userInfo.setHeadUrl((String) info.get("headUrl"));
        userInfo.setRole((String) info.get("role"));
        userInfo.setValid((Boolean) info.get("valid"));
        userInfo.setLocked((Boolean) info.get("locked"));
        userInfo.setRegistDate((Date) info.get("registDate"));

        return userInfo;
    }

}
