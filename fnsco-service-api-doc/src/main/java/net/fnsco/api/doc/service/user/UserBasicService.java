package net.fnsco.api.doc.service.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

import net.fnsco.api.doc.comm.AppConstants;
import net.fnsco.api.doc.comm.CfgConstants;
import net.fnsco.api.doc.comm.DateUtil;
import net.fnsco.api.doc.comm.JsonUtils;
import net.fnsco.api.doc.comm.RegexUtil;
import net.fnsco.api.doc.comm.Sha1Utils;
import net.fnsco.api.doc.service.mail.CryptUtil;
import net.fnsco.api.doc.service.mail.MailConstants;
import net.fnsco.api.doc.service.mail.MailUtilService;
import net.fnsco.api.doc.service.user.dao.UserBasicDAO;
import net.fnsco.api.doc.service.user.entity.UserBasicDO;
import net.fnsco.api.doc.service.user.entity.UserDetailDO;
import net.fnsco.core.base.BaseService;

/**
 * 
		* <p>Title: 用户基本信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:40:48</p>
 */
@Service
public class UserBasicService extends BaseService {
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private UserBasicDAO      userBasicDAO;
    @Autowired
    private MailUtilService   mailUtil;

    public UserBasicDO getByEmail(String email) {
        return userBasicDAO.getByEmail(email);
    }

    public String encryPasswd(Long userId, String password) {
        return Sha1Utils.sha1Crypt(password + userId);
    }

    public int countTotalRegist() {
        return userBasicDAO.countTotalRegist();
    }

    public void updatePasswd(Long userId, String oldPasswd, String newPasswd) {
        UserBasicDO basic = userBasicDAO.getById(userId);
        if (encryPasswd(userId, oldPasswd).equals(basic.getPassword())) {//, ErrorCode.LOGIN_008);

        }

        basic.setPassword(encryPasswd(userId, newPasswd));
        update(basic);
    }

    //    public int countDayRegist(Date date) {
    //        return userBasicDAO.countDayRegist(DateUtil.getDayStart(date), DateUtil.getDayEnd(date));
    //    }

    //    public void unlockJob() {
    //        userBasicDAO.unlockJob();
    //    }

    public void sendUpdateEmailCode(Long userId, String passwd, String email) {
        UserBasicDO basic = userBasicDAO.getById(userId);
        if (basic.getPassword().equals(encryPasswd(userId, passwd))) {
            return;//"当前密码错误
        }

        Map<String, Object> info = Maps.newHashMap();
        info.put("userId", userId);
        info.put("email", email);
        String code = CryptUtil.encryptAES(JsonUtils.toJson(info), AppConstants.DEFAULT_SECRET_KEY);

        UserDetailDO detail = userDetailService.getByUserId(userId);

        Map<String, Object> model = Maps.newHashMap();
        model.put("nickName", detail.getNickName());
        model.put("oldEmail", basic.getEmail());
        model.put("newEmail", email);
        model.put("updateEmailUrl", CfgConstants.WEB_BASE_URL + "pass/user/updateEmail.htm?code=" + code);

        mailUtil.send(email, MailConstants.SUB_EMAIL_UPDATE, MailConstants.TMPL_EMAIL_UPDATE, model);
    }

    public void updateEmail(String code) {
        Map<String, Object> updateInfo = JsonUtils.toObject(CryptUtil.decryptAES(code, AppConstants.DEFAULT_SECRET_KEY), HashMap.class);
        Long userId = ((Number) updateInfo.get("userId")).longValue();
        String email = (String) updateInfo.get("email");
        if (RegexUtil.isEmail(email)) {
            return;
        }
        if (!isEmailExist(email, null)) {
            return;
        }

        UserBasicDO basic = userBasicDAO.getById(userId);
        if (basic != null) {
            basic.setEmail(email);
            update(basic);
        }
    }

    public boolean isEmailExist(String email, Integer exceptId) {
        return userBasicDAO.countByEmail(email, exceptId) > 0;
    }

    public void update(UserBasicDO userBasic) {
        userBasicDAO.update(userBasic);
    }

    public UserBasicDO getById(Long userId) {
        return userBasicDAO.getById(userId);
    }
}
