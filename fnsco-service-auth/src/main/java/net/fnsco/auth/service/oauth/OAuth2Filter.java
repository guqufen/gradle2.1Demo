package net.fnsco.auth.service.oauth;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.freamwork.business.WebService;
import net.fnsco.freamwork.business.WebUserDTO;
import net.fnsco.freamwork.comm.FrameworkConstant;
import net.fnsco.freamwork.spring.SpringUtils;

/**
 * oauth2过滤器
 *
 * @author sxfei
 * @email songxianfei@gmail.com
 * @date 2017-05-20 13:00
 */
public class OAuth2Filter extends AuthenticatingFilter {
    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token
        String token = getRequestToken((HttpServletRequest) request);

        if (StringUtils.isBlank(token)) {
            return null;
        }

        return new OAuth2Token(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token，如果token不存在，直接返回401
        String token = getRequestToken((HttpServletRequest) request);
        if (StringUtils.isBlank(token)) {
            logger.error("session不存在或页面未传入token值");
            OAuth2Helper.reLogin(response);
            return false;
        }

        return executeLogin(request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");

        logger.error("登录失败");
        //处理登录失败的异常
        OAuth2Helper.reLogin(response);

        return false;
    }

    /**
     * 获取请求的token
     */
    private String getRequestToken(HttpServletRequest httpRequest) {
        //从header中获取token
        String token = httpRequest.getHeader("token");

        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = httpRequest.getParameter("token");
        }
        if (StringUtils.isBlank(token)) {
            HttpSession session = httpRequest.getSession(false);
            boolean flag = true;
            if (null != session) {
                WebUserDTO obj = (WebUserDTO) session.getAttribute(FrameworkConstant.SESSION_USER_KEY);
                if (obj != null) {
                    token = obj.getTokenId();
                    flag = false;
                }
            }
            if (flag) {
                WebService userService = (WebService) SpringUtils.getBean("webUserServiceImpl");
                WebUserDTO user = userService.getCookieUser(httpRequest);
                if (null != user) {
                    token = user.getTokenId();
                }
            }
        }
        return token;
    }

}
