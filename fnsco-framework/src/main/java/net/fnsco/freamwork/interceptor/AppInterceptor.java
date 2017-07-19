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
import net.fnsco.freamwork.business.AppUserDTO;
import net.fnsco.freamwork.business.UserService;
import net.fnsco.freamwork.comm.FrameworkConstant;
import net.fnsco.freamwork.comm.Md5Util;

/**
 * 检查登录情况
 * 
 * @author Administrator
 *
 */
@Component
public class AppInterceptor implements HandlerInterceptor {
    private Logger      logger = LoggerFactory.getLogger(AppInterceptor.class);

    @Autowired
    private Environment env;

    @Autowired
    private UserService userService;

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
        String temp = FrameworkConstant.TOKEN_ID + identifier;
        String trueTokenId = Md5Util.getInstance().md5(temp);
        if (!trueTokenId.equals(tokenId)) {
            logger.error("TokenId不对", "传入id为" + tokenId + "生成后的id为" + trueTokenId);
            OutWriterUtil.outJson(response, FrameworkConstant.E_TOKEN_ERROR);
            return false;
        }
        //强制退出
        String userId = request.getHeader("userId");
        if (!Strings.isNullOrEmpty(userId)) {
            AppUserDTO userDto = userService.getUserInfo(userId);
            if (userDto != null) {
                if (userDto.getForcedLoginOut() != null && userDto.getForcedLoginOut().compareTo(1) == 0) {
                    OutWriterUtil.outJson(response, FrameworkConstant.E_FORCED_LOGIN_OUT);
                    return false;
                }
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
