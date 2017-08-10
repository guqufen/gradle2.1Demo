package net.fnsco.api.doc.service.user;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

import net.fnsco.api.doc.comm.AppConstants;
import net.fnsco.api.doc.comm.CfgConstants;
import net.fnsco.api.doc.comm.DateUtil;
import net.fnsco.api.doc.comm.MD5Utils;
import net.fnsco.api.doc.comm.RegexUtil;
import net.fnsco.api.doc.service.mail.CryptUtil;
import net.fnsco.api.doc.service.mail.MailConstants;
import net.fnsco.api.doc.service.mail.MailUtilService;
import net.fnsco.api.doc.service.user.entity.UserBasicDO;
import net.fnsco.api.doc.service.user.entity.UserDetailDO;
import net.fnsco.api.doc.service.user.entity.UserLoginDO;
import net.fnsco.api.doc.service.user.entity.UserTokenDO;
import net.fnsco.api.doc.service.vo.LoginParamInfo;
import net.fnsco.api.doc.service.vo.UserInfo;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;

/**
 * 
		* <p>Title: 登陆相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月8日上午10:29:42</p>
 */
@Service
public class LoginService extends BaseService {
    @Autowired
    private UserBasicService  userBasicService;

    @Autowired
    private UserLoginService  userLoginService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private UserTokenService  userTokenService;
    @Autowired
    private MailUtilService   mailUtil;

    //	@Autowired
    //	private UserMsgService userMsgService;
    //	
    //	@Autowired
    //	private AuthService authService;
    @Autowired
    private Environment       env;

    public ResultDTO loginByEmail(LoginParamInfo loginParamInfo) {
        if (!"邮箱".equals(loginParamInfo.getLoginType())) {
            return ResultDTO.fail("登录类型选择不正确");
        }

        UserBasicDO basic = userBasicService.getByEmail(loginParamInfo.getEmail());
        //登陆验证

        if (null == basic) {
            return ResultDTO.fail("用户不存在");
        }
        if (basic.getLocked() != 0) {
            return ResultDTO.fail("用户被锁定");
        }
        if (basic.getValid() != 1) {
            //, ErrorCode.LOGIN_005);
            return ResultDTO.fail("用户未激活");
        }

        //用户登陆信息
        UserLoginDO userLogin = userLoginService.getByUserId(basic.getId());
        String passwdEncry = userBasicService.encryPasswd(basic.getId(), loginParamInfo.getPassword());
        if (!basic.getPassword().equals(passwdEncry)) {
            dealLoginFail(basic, loginParamInfo, userLogin);
            return ResultDTO.fail("用户名或密码错误");
        }

        return dealLongSuccess(basic, loginParamInfo, userLogin);
    }

    //登陆成功处理
    private ResultDTO dealLongSuccess(UserBasicDO basic, LoginParamInfo loginParamInfo, UserLoginDO userLogin) {
        Long userId = basic.getId();

        //更新用户登陆信息
        userLogin.setLoginFailureCount(0);
        userLogin.setLoginIp(loginParamInfo.getLoginIp());
        userLogin.setLoginType(loginParamInfo.getLoginType());
        userLogin.setAuthCode(loginParamInfo.getSmsCode());
        userLogin.setLoginDate(DateUtil.getNow());
        userLogin.setLoginStatus("在线");
        userLogin.setLoginCount(userLogin.getLoginCount() + 1);
        userLoginService.update(userLogin);

        //设置用户信息
        UserDetailDO detail = userDetailService.getByUserId(userId);
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(basic.getEmail());
        userInfo.setUserId(userId);
        userInfo.setNickName(detail.getNickName());
        userInfo.setHeadUrl(detail.getHeadUrl());
        userInfo.setRole(basic.getRole());

        //加载项目权限信息
        //authService.loadProjAuth(userInfo);

        //插入新发布的系统消息
        //userMsgService.fetchSysMsg(userId, basic.getRole());
        //int newMsgCount = userMsgService.countUnread(userId);
        userInfo.setNewMsgCount(1);//newMsgCount);

        //设置自动登录
        String token = dealAutoLogin(userId, loginParamInfo);
        userInfo.setToken(token);

        return ResultDTO.success(userInfo);
    }

    //处理自动登录
    private String dealAutoLogin(Long userId, LoginParamInfo loginParamInfo) {
        String token = "";
        if (!loginParamInfo.isAutoLogin()) {
            return token;
        }

        //token由用户id和当前时间戳进行md5生成
        token = MD5Utils.Md5("" + userId + "," + DateUtil.getNowTime());
        UserTokenDO userToken = userTokenService.getByUserId(userId);
        if (userToken == null) {//无记录则新建
            userToken = new UserTokenDO();
            userToken.setLoginIp(loginParamInfo.getLoginIp());
            userToken.setUserId(userId);
            userToken.setToken(token);
            userToken.setExpireDate(getTokenExpireDate());
            userTokenService.add(userToken);
        } else {//更新记录信息
            userToken.setToken(token);
            userToken.setExpireDate(getTokenExpireDate());
            userTokenService.update(userToken);
        }

        return token;
    }

    //获取token失效时间
    private Date getTokenExpireDate() {
        int cookieTokenExpire = Integer.parseInt(env.getProperty(CfgConstants.COOKIE_TOKEN_EXPIRE));
        return new Date(DateUtil.getNowTime() + cookieTokenExpire * 1000);
    }

    //登陆失败处理
    private void dealLoginFail(UserBasicDO basic, LoginParamInfo loginParamInfo, UserLoginDO userLogin) {
        int currentFailCount = userLogin.getLoginFailureCount() + 1;
        Date nowDate = DateUtil.getNow();

        //更新用户登陆信息
        userLogin.setLoginFailureCount(currentFailCount);
        userLogin.setLoginIp(loginParamInfo.getLoginIp());
        userLogin.setLoginType(loginParamInfo.getLoginType());
        userLogin.setAuthCode(loginParamInfo.getSmsCode());
        userLogin.setLoginDate(nowDate);
        int limitCurrentFailCount = Integer.parseInt(env.getProperty(CfgConstants.LIMIT_LOGIN_FAIL_COUNT));
        //登陆错误次数超过限制，账号锁定
        if (currentFailCount >= limitCurrentFailCount) {
            basic.setLocked(1);
            basic.setLockedDate(nowDate);
            userBasicService.update(basic);

            userLoginService.update(userLogin);
            //ValidateUtils.isTrue(false, ErrorCode.LOGIN_010);
        } else {
            userLoginService.update(userLogin);
            //ValidateUtils.isTrue(false, ErrorCode.LOGIN_004);
        }
    }

    public void sendResetCode(String email) {
        UserBasicDO basic = userBasicService.getByEmail(email);
        if (null == basic) {
            //, ErrorCode.LOGIN_001);
        }

        UserDetailDO detail = userDetailService.getByUserId(basic.getId());
        String code = CryptUtil.encryptAES(email, AppConstants.DEFAULT_SECRET_KEY);
        Map<String, Object> model = Maps.newHashMap();
        model.put("nickName", detail.getNickName());
        model.put("email", email);
        model.put("resetPasswdUrl", env.getProperty(CfgConstants.WEB_BASE_URL) + "forwardReset.htm?code=" + code);

        mailUtil.send(email, MailConstants.SUB_PASSWD_RESET, MailConstants.TMPL_PASSWD_RESET, model);
    }

    public void resetPasswd(String code, String passwd) {
        String email = CryptUtil.decryptAES(code, AppConstants.DEFAULT_SECRET_KEY);
        if (RegexUtil.isEmail(email)) {
            //, ErrorCode.SYS_001);
        }

        UserBasicDO userBasic = userBasicService.getByEmail(email);
        if (userBasic != null) {
            userBasic.setPassword(userBasicService.encryPasswd(userBasic.getId(), passwd));
            userBasic.setModifyDate(DateUtil.getNow());
            userBasicService.update(userBasic);
        }
    }

    public ResultDTO loginByToken(LoginParamInfo loginParamInfo) {
        UserTokenDO userToken = userTokenService.getByToken(loginParamInfo.getToken());
        if (userToken == null) {
            return null;
        }

        Long userId = userToken.getUserId();
        UserBasicDO basic = userBasicService.getById(userId);
        //用户登陆信息
        UserLoginDO userLogin = userLoginService.getByUserId(userId);
        return dealLongSuccess(basic, loginParamInfo, userLogin);
    }
}
