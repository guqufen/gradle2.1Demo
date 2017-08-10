package net.fnsco.api.doc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.api.doc.comm.AppConstants;
import net.fnsco.api.doc.comm.RegexUtil;
import net.fnsco.api.doc.service.user.LoginService;
import net.fnsco.api.doc.service.user.UserTokenService;
import net.fnsco.api.doc.service.user.entity.UserTokenDO;
import net.fnsco.api.doc.service.vo.LoginParamInfo;
import net.fnsco.api.doc.service.vo.UserInfo;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;

/**
 * 
		* <p>Title: 登陆退出处理</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午2:04:09</p>
 */
@Controller
@RequestMapping(value = "/web")
@Api(value = "/web", tags = { "用户登录" })
public class LoginController extends BaseController {
    @Autowired
    private LoginService     loginService;

    @Autowired
    private UserTokenService userTokenService;

    /**
     * 
    		*@name 登陆处理
    		*@param name		登陆名
    		*@param password	登陆密码
    		*@param code		验证码
    		*@param	smsCode 	手机验证码
    		*@Description  
    		*@CreateDate 2015年7月11日下午2:05:24
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ApiOperation(value = "登陆处理", notes = "登陆处理")
    @ResponseBody
    public ResultDTO login(@RequestParam String loginName, @RequestParam String passwd, @RequestParam String validCode) {
        boolean autoLogin = true;
        if (Strings.isNullOrEmpty(loginName)) {
            //"登陆名不能为空"
        }
        if (Strings.isNullOrEmpty(passwd)) {
            //"登陆密码不能为空");
        }

        //目前默认使用邮箱登陆
        LoginParamInfo paramInfo = new LoginParamInfo();
        paramInfo.setEmail(loginName);
        paramInfo.setPassword(passwd);
        paramInfo.setLoginIp(getIp());
        paramInfo.setLoginType(AppConstants.EMAIL);
        paramInfo.setAutoLogin(autoLogin);

        //if (validCode.equals(getSessionUser())) {
        //   return ResultDTO.fail();//"forward:/forwardLogin.htm";
        //}
        ResultDTO result = loginService.loginByEmail(paramInfo);
        if (!result.isSuccess()) {
            return result;
        }
        UserInfo userInfo = (UserInfo) result.getData();
        //保存登陆用户信息
        setSessionUser(userInfo, userInfo.getUserId());

        //处理token，保存到cookie中
        if (autoLogin) {
            addCookieUser(userInfo.getToken());
        }

        //设置登陆跳转页面
        String redirectUrl = "";
        switch (userInfo.getRole()) {
            case "管理员":
                redirectUrl = "/auth/home/home.htm";
                break;

            case "普通用户":
                redirectUrl = "/admin/home.htm";
                break;

            default:
                break;
        }

        return ResultDTO.success();
    }

    /**
     * 
    		*@name 退出
    		*@Description  
    		*@CreateDate 2015年7月11日下午2:05:24
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ApiOperation(value = "退出", notes = "退出")
    @ResponseBody
    public ResultDTO logout() {
        UserInfo userInfo = (UserInfo) getSessionUser();
        if (userInfo != null) {
            //更新数据库信息
            UserTokenDO userToken = userTokenService.getByUserId(userInfo.getUserId());
            if (userToken != null) {
                //不能设置为空，索引异常
                if (!userToken.getToken().startsWith("invalid:")) {
                    userToken.setToken("invalid:" + userToken.getToken());
                    userTokenService.update(userToken);
                }

                //删除cookie
                removeCookieUser();
            }
        }
        request.getSession().invalidate();

        return ResultDTO.success();//"user/login";
    }

    /**
     * 
    		*@name 发送重置密码授权码
    		*@Description  
    		*@CreateDate 2015年8月22日下午2:58:59
     */
    @RequestMapping(value = "/json/sendResetCode", method = RequestMethod.GET)
    @ApiOperation(value = "发送重置密码授权码", notes = "发送重置密码授权码")
    @ResponseBody
    public ResultDTO sendResetCode(@RequestParam String email) {
        if (RegexUtil.isEmail(email)) {
            //邮箱格式不正确
        }

        loginService.sendResetCode(email);

        return ResultDTO.success();
    }

    /**
     * 
    		*@name 重置密码
    		*@Description  
    		*@CreateDate 2015年8月22日下午2:58:59
     */
    @RequestMapping(value = "/resetPasswd", method = RequestMethod.GET)
    @ApiOperation(value = "重置密码", notes = "重置密码")
    public ResultDTO resetPasswd(@RequestParam String code, @RequestParam String passwd) {
        if (Strings.isNullOrEmpty(code)) {
            //授权码不能为空");
        }

        loginService.resetPasswd(code, passwd);

        return ResultDTO.success();//"/resetSuccess.htm";
    }

}
