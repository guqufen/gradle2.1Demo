package net.fnsco.freamwork.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;
import com.sun.tools.internal.xjc.generator.bean.ImplStructureStrategy.Result;

/**
 * 检查登录情况
 * 
 * @author Administrator
 *
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private Environment env;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl = request.getRequestURL().toString();
        // 从配置文件中获取浙付通接口模块,不需要被拦截
        String appModules = this.env.getProperty("app.modules");
        if (!Strings.isNullOrEmpty(appModules)) {
            String[] modules = StringUtils.split(appModules, ",");
            for (String module : modules) {
                if (requestUrl.indexOf(module) > 0) {
                    return true;
                }
            }
        }
        Object obj = request.getSession();
        if (obj == null) {
            response.sendRedirect(this.env.getProperty("fns.posp-admin.host") + "/login/login.htm");
            return true;
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
