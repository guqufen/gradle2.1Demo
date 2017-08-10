package net.fnsco.api.doc.service.user;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;

import net.fnsco.api.doc.comm.AppConstants;
import net.fnsco.api.doc.comm.CfgConstants;
import net.fnsco.api.doc.comm.RegexUtil;
import net.fnsco.api.doc.service.mail.CryptUtil;
import net.fnsco.api.doc.service.mail.MailConstants;
import net.fnsco.api.doc.service.mail.MailUtilService;
import net.fnsco.api.doc.service.user.dao.UserBasicDAO;
import net.fnsco.api.doc.service.user.dao.UserDetailDAO;
import net.fnsco.api.doc.service.user.dao.UserLoginDAO;
import net.fnsco.api.doc.service.user.entity.UserBasicDO;
import net.fnsco.api.doc.service.user.entity.UserDetailDO;
import net.fnsco.api.doc.service.user.entity.UserLoginDO;
import net.fnsco.api.doc.service.vo.RegistParamInfo;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;

/**
 * 
		* <p>Title: 注册相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月8日下午12:54:35</p>
 */
@Service
public class RegistService extends BaseService {
    @Autowired
    private UserBasicService  userBasicService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private UserLoginService  userLoginService;
    @Autowired
    private UserLoginDAO      userLoginDAO;
    //@Autowired
    //private ProjectMemberService projectMemberService;
    @Autowired
    private UserDetailDAO     userDetailDAO;
    @Autowired
    private MailUtilService   mailUtil;
    @Autowired
    private UserBasicDAO      userBasicDAO;
    @Autowired
    private Environment       env;

    @Transactional
    public ResultDTO registByEmail(RegistParamInfo registParamInfo) {
        if ("邮箱" != registParamInfo.getLoginType()) {
            return ResultDTO.fail("登录类型错误");
        }

        UserBasicDO temp = userBasicService.getByEmail(registParamInfo.getEmail());
        //是否验证
        if (temp != null) {
            return ResultDTO.fail("邮箱已存在");
        }
        //保存用户信息
        Long userId = saveUserInfo(registParamInfo);

        //发送邮件
        sendEmail(registParamInfo.getNickName(), registParamInfo.getEmail());
        return ResultDTO.success(userId);
    }

    //处理邮件发送
    private void sendEmail(String nickName, String registEmail) {
        String code = CryptUtil.encryptAES(registEmail, AppConstants.DEFAULT_SECRET_KEY);

        Map<String, Object> model = Maps.newHashMap();
        model.put("nickName", nickName);
        model.put("activeUrl", env.getProperty(CfgConstants.WEB_BASE_URL) + "/web/regist/active.htm?code=" + code);
        String webName = env.getProperty(CfgConstants.WEB_NAME);
        mailUtil.send(registEmail, MailConstants.SUB_REGIST + webName, MailConstants.TMPL_REGIST, model);
    }

    //处理用户信息保存
    private Long saveUserInfo(RegistParamInfo registParamInfo) {
        //处理用户基本信息
        UserBasicDO userBasic = new UserBasicDO();
        userBasic.setEmail(registParamInfo.getEmail());
        userBasic.setLocked(0);
        userBasic.setRegisterIp(registParamInfo.getRegistIp());
        userBasic.setValid(0);
        userBasic.setRole("普通用户");
        userBasic.setCreateDate(new Date());
        userBasic.setModifyDate(new Date());
        userBasicDAO.insert(userBasic);

        Long userId = userBasic.getId();
        //设置密码
        String passwordEncry = userBasicService.encryPasswd(userId, registParamInfo.getPassword());
        userBasic.setPassword(passwordEncry);
        userBasicService.update(userBasic);

        //处理用户详情信息
        UserDetailDO userDetail = new UserDetailDO();
        userDetail.setNickName(registParamInfo.getNickName());
        userDetail.setUserId(userId);
        userDetail.setCreateDate(new Date());
        userDetail.setModifyDate(new Date());
        userDetailDAO.insert(userDetail);

        //处理用户登陆信息
        UserLoginDO userLogin = new UserLoginDO();
        userLogin.setUserId(userId);
        userLogin.setCreateDate(new Date());
        userLogin.setModifyDate(new Date());
        userLogin.setLoginFailureCount(0);
        userLogin.setLoginCount(0);
        userLoginDAO.insert(userLogin);

        return userId;
    }

    public ResultDTO activeByEmail(String code) {
        String registEmail = CryptUtil.decryptAES(code, AppConstants.DEFAULT_SECRET_KEY);
        if (!RegexUtil.isEmail(registEmail)) {
            return ResultDTO.fail("邮箱格式错误");
        }

        UserBasicDO userBasic = userBasicService.getByEmail(registEmail);
        if (userBasic != null) {
            userBasic.setValid(1);
            userBasic.setModifyDate(new Date());
            userBasicService.update(userBasic);
            //加入到demo项目组中
            //			projectMemberService.accept(userBasic.getId(), 1L, Role.viewer);
        }
        return ResultDTO.success();
    }

    public void sendActiveCode(String email) {
        UserBasicDO temp = userBasicService.getByEmail(email);
        if (temp == null) {
            return;
        }
        if (temp.getValid() == 1) {
            return;
        }
        UserDetailDO detail = userDetailService.getByUserId(temp.getId());
        sendEmail(detail.getNickName(), email);
    }

}
