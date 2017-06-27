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

import net.fnsco.freamwork.comm.Constant;
import net.fnsco.freamwork.comm.Md5Util;

/**
 * 检查登录情况
 * 
 * @author Administrator
 *
 */
@Component
public class OpenInterceptor implements HandlerInterceptor {
    private boolean     authentication = true;
    @Autowired
    private Environment env;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestUrl = request.getRequestURL().toString();
        // 从配置文件中获取浙付通接口模块,不需要被拦截
        String appModules = env.getProperty("app.ignore.url");
        if (!Strings.isNullOrEmpty(appModules)) {
            String[] modules = StringUtils.split(appModules, ",");
            for (String module : modules) {
                if (requestUrl.indexOf(module) > -1) {
                    return true;
                }
            }
        }
        String tokenId = request.getHeader("tokenId");
        if (Strings.isNullOrEmpty(tokenId)) {
            return false;
        }
        String identifier = request.getHeader("identifier");
        String temp = Constant.TOKEN_ID + identifier;
        String trueTokenId = Md5Util.getInstance().md5(temp);
        if (!trueTokenId.equals(tokenId)) {
            return false;
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
