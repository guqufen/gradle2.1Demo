package net.fnsco.freamwork.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;

import ch.qos.logback.core.CoreConstants;
import net.fnsco.freamwork.aop.OutWriterUtil;
import net.fnsco.freamwork.business.AppUserDTO;
import net.fnsco.freamwork.business.UserService;
import net.fnsco.freamwork.comm.FrameworkConstant;

/**
 * 检查登录情况
 * 
 * @author Administrator
 *
 */
@Component
public class WebInterceptor implements HandlerInterceptor {
    private Logger             logger   = LoggerFactory.getLogger(WebInterceptor.class);
    @Autowired
    private Environment        env;
    @Autowired
    private UserService        userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String isAuthor = env.getProperty(FrameworkConstant.WEB_IS_AUTHOR);
        if (FrameworkConstant.IS_AUTHOR.equals(isAuthor)) {
            return true;
        }
        String requestUrl = request.getRequestURL().toString();
        // 从配置文件中获取浙付通接口模块,不需要被拦截
        String appModules = env.getProperty("web.ignore.url");
        if (!Strings.isNullOrEmpty(appModules)) {
            String[] modules = appModules.split(",");
            for (String module : modules) {
                if (requestUrl.indexOf(module) > 0) {
                    return true;
                }
            }
        }
        HttpSession session = request.getSession(false);
        boolean flag = true;
        if (null != session) {
            Object obj = session.getAttribute(FrameworkConstant.SESSION_USER_KEY);
            if (obj != null) {
                flag = false;
            }
        }
        if (flag) {
            logger.warn("未登录转入登录页面");
            String requestType = request.getHeader("X-Requested-With");
            if (Strings.isNullOrEmpty(requestType)) {
                response.sendRedirect("/idx");
                return false;
            }
            AppUserDTO user = userService.getCookieUser(request);
            if (null == user) {
                OutWriterUtil.outJson(response, FrameworkConstant.E_NOT_LOGIN);
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
