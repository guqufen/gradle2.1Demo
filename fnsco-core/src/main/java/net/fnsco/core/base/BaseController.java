package net.fnsco.core.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import net.fnsco.freamwork.spring.SpringUtils;

public class BaseController {
    protected Logger              logger   = LoggerFactory.getLogger(this.getClass());
    protected HttpServletRequest  request;
    protected HttpServletResponse response;
    protected HttpSession         session;
    public static final String    USER_KEY = "SESSION_USER_KEY";

    public Map<String, Integer> copyParamsToInteger(String[] params) {
        Map<String, Integer> map = Maps.newHashMap();
        for (String key : params) {
            String val = request.getParameter(key);
            if (!Strings.isNullOrEmpty(val)) {
                val = val.trim();
            }
            Integer vaule = 0;
            if (!Strings.isNullOrEmpty(val)) {
                vaule = Integer.parseInt(val);
            }
            map.put(key, vaule);
        }
        return map;
    }

    public Map<String, String> copyParams(String[] params) {
        Map<String, String> map = new HashMap<String, String>();
        for (String key : params) {
            String val = request.getParameter(key);
            if (!Strings.isNullOrEmpty(val)) {
                val = val.trim();
            }
            map.put(key, val);
        }
        return map;
    }

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    public Integer getUserId() {
        Integer userId = (Integer) this.session.getAttribute("userId");
        if (null == userId) {
            userId = 0;
        }
        return userId;
    }

    public ResultDTO fail(int msgCode) {
        return this.fail(msgCode, SpringUtils.getMessage(String.valueOf(msgCode), new Object[0]));
    }

    public ResultDTO fail(String message) {
        return this.fail("0000", message);
    }

    public ResultDTO fail(int msgCode, String message) {
        String code = String.valueOf(msgCode);
        String msg = message;

        return this.fail(code, msg);
    }

    private ResultDTO fail(String code, String message) {
        ResultDTO dto = new ResultDTO();
        dto.setCode(code);
        dto.setMessage(message);
        return dto;//.toJsonString();
    }

    public ResultDTO success() {
        return this.success("");
    }

    public ResultDTO success(Object data) {
        return this.success(data, "");
    }

    public ResultDTO success(Object data, String total) {
        return this.success(data, total, "");
    }

    public ResultDTO success(Object data, String total, String pageId) {
        ResultDTO dto = new ResultDTO();
        dto.setSuccess(true);
        dto.setCode("1111");
        dto.setMessage("");
        if (Strings.isNullOrEmpty(total)) {
            dto.setData(data);
        } else {
            List temp = (List) data;
            ResultPageDTO pageDTO = new ResultPageDTO();
            pageDTO.setTotal(Integer.parseInt(total));
            if (!Strings.isNullOrEmpty(pageId)) {
                pageDTO.setCurrentPage(Integer.parseInt(pageId));
            }
            pageDTO.setList(temp);
            dto.setData(pageDTO);
        }
        return dto;//.toJsonString();
    }

    public String getIp() {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String ip1 = "";
        try {
            if (!Strings.isNullOrEmpty(ip)) {// 返回发出请求的IP地址
                String[] ips = ip.split(",");
                for (int i = 0; i < ips.length; i++) {

                    ip1 = ips[i];
                    if (!"unknown".equalsIgnoreCase(ip1)) {
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("获取IP地址出错", ex);
        }
        return ip1;
    }

    public String getUserAgent() {
        String agent = request.getHeader("User-Agent");
        return agent + request.getHeader("Accept");
    }

    public Map<String, String> getCookie() {
        Map<String, String> map = Maps.newHashMap();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if (!Strings.isNullOrEmpty(name)) {
                    map.put(name, cookie.getValue());
                }
            }
        }
        return map;
    }

    public Object getSessionUser() {
        HttpSession session = request.getSession(false);
        if (null == session) {
            return null;
        }
        return session.getAttribute(USER_KEY);
    }

    public void setSessionUser(Object userDO) {
        HttpSession session = request.getSession();
        session.setAttribute(USER_KEY, userDO);
    }
}
