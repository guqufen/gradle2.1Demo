package net.fnsco.freamwork.aop;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;

import net.fnsco.freamwork.comm.FrameworkConstant;

public class AppAuthorizeFilter implements Filter {
    private static Logger LOGGER         = LoggerFactory.getLogger(AppAuthorizeFilter.class);
    private boolean       authentication = true;
    @Autowired
    private Environment   env;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (Boolean.valueOf(authentication)) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse HttpServletResponse = (HttpServletResponse) response;
            if (RequestMethod.POST.name().equalsIgnoreCase(httpServletRequest.getMethod())) {
                String requestUrl = httpServletRequest.getRequestURL().toString();
                // 防止流读取一次后就没有了, 所以需要将流继续写出去
                ServletRequest requestWrapper = new BodyRequestWrapper(httpServletRequest);
                String body = HttpHelper.getBodyString(requestWrapper);
                if (Strings.isNullOrEmpty(body)) {
                    LOGGER.error("非法请求, 没有APP_KEY, APP_SECRET");
                    OutWriterUtil.outJson(HttpServletResponse, FrameworkConstant.E_TOKEN_EMPTY);
                    return;
                }
                JSONObject parameters = JSONObject.parseObject(body);
                String tokenId = parameters.getString("tokenId");
                if (Strings.isNullOrEmpty(tokenId)) {
                    LOGGER.error("非法请求, tokenId为空" + httpServletRequest.getRemoteHost() + httpServletRequest.getRequestURL());
                    OutWriterUtil.outJson(HttpServletResponse, FrameworkConstant.E_TOKEN_EMPTY);
                    return;
                } else if ("123".equals(tokenId)) {
                    LOGGER.error("非法请求, APP_SECRET为空, user={}");
                    OutWriterUtil.outJson(HttpServletResponse, FrameworkConstant.E_TOKEN_ERROR);
                    return;
                }
                chain.doFilter(requestWrapper, response);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub  

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
