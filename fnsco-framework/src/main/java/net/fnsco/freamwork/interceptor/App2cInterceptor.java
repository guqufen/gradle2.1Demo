package net.fnsco.freamwork.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;

import net.fnsco.freamwork.aop.OutWriterUtil;
import net.fnsco.freamwork.business.AppService;
import net.fnsco.freamwork.business.AppUser1DTO;
import net.fnsco.freamwork.comm.FrameworkConstant;
import net.fnsco.freamwork.comm.Md5Util;

/**
 * 检查登录情况
 * 
 * @author Administrator
 *
 */
@Component
public class App2cInterceptor implements HandlerInterceptor {
    private Logger      logger = LoggerFactory.getLogger(App2cInterceptor.class);

    @Autowired
    private Environment env;

    @Autowired(required = false)
    private AppService  userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String isAuthor = env.getProperty(FrameworkConstant.APP_IS_AUTHOR);
        if (FrameworkConstant.IS_AUTHOR.equals(isAuthor)) {
            //byte[] body = HttpHelper.getBodyString(request).getBytes(Charset.forName("UTF-8"));
            //logger.error(new String(body,"utf-8"));
            return true;
        }
        String requestUrl = request.getRequestURL().toString();
        // 从配置文件中获取浙付通接口模块,不需要被拦截
        String appModules = env.getProperty("app.ignore.url");

        if (!Strings.isNullOrEmpty(appModules)) {
            String[] modules = appModules.split(",");
            for (String module : modules) {
                if (requestUrl.indexOf(module) > 0) {
                    return true;
                }
            }
        }
        String tokenId = request.getHeader("tokenId");
        if (Strings.isNullOrEmpty(tokenId)) {
            OutWriterUtil.outJson(response, FrameworkConstant.E_TOKEN_EMPTY);
            return false;
        }
        String identifier = request.getHeader("identifier");
        //String temp = FrameworkConstant.TOKEN_ID + identifier;
        String temp = env.getProperty("app.token.md5") + identifier;
        String trueTokenId = Md5Util.getInstance().md5(temp);
        if (!trueTokenId.equals(tokenId)) {
            logger.error("获取到的TokenId:" + tokenId + " ,identifier=" + identifier + " , temp=" + temp);
            logger.error("TokenId不对" + "传入id为" + tokenId + "生成后的id为" + trueTokenId);
            OutWriterUtil.outJson(response, FrameworkConstant.E_TOKEN_ERROR);
            return false;
        }
        //强制退出
        String userId = request.getHeader("userId");
        String deviceId = request.getHeader("deviceId");
        logger.error("进入强制退出前判断" + userId);
        if (Strings.isNullOrEmpty(userId)) {
            OutWriterUtil.outJson(response, FrameworkConstant.E_FORCED_LOGIN_OUT);
            logger.error("强制退出，用户id为空" + userId);
            return false;
        }
        logger.error("进入强制退出判断" + userId);
        AppUser1DTO userDto = null;
        try {
            userDto = userService.getUserInfo(userId);
        } catch (Exception ex) {
            logger.error("App2cInterceptor登录权限检查出现异常", ex);
        }
        if (userDto == null) {
            OutWriterUtil.outJson(response, FrameworkConstant.E_FORCED_LOGIN_OUT);
            logger.error("强制退出,用户信息不存在" + userId);
            return false;
        }
        if (userDto.getForcedLoginOut() != null && userDto.getForcedLoginOut().compareTo(1) == 0) {
            OutWriterUtil.outJson(response, FrameworkConstant.E_FORCED_LOGIN_OUT);
            logger.error("强制退出" + userId);
            return false;
        } else {
            logger.error("不用强制退出" + userId);
        }
        if (!Strings.isNullOrEmpty(userDto.getDeviceId())) {
            if (!userDto.getDeviceId().equals(deviceId)) {
                OutWriterUtil.outJson(response, FrameworkConstant.E_FORCED_LOGIN_OUT);
                logger.error("强制退出，另一台机器已经登录" + userId);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
