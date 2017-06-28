package net.fnsco.freamwork.log.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.RequestContextFilter;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import net.fnsco.freamwork.log.LoggerConstant;
import net.fnsco.freamwork.log.LoggerUtils;
import net.fnsco.freamwork.log.trace.TraceContext;
import net.fnsco.freamwork.log.trace.TracerFactory;

/**
 * 请求摘要以及明细过滤器
 *
 * @author sxfei
 * @version v 0.1 17/3/6
 */
public class WebAccessLogFilter extends RequestContextFilter {

    private static final Logger LOGGER                            = LoggerFactory.getLogger(WebAccessLogFilter.class);

    /** 日志线程变量  */
    private static final String MDC_REMOTE_ADDR                   = "remoteAddr";
    private static final String MDC_REMOTE_HOST                   = "remoteHost";
    private static final String MDC_REQUEST_URI                   = "requestURI";
    private static final String MDC_REQUEST_URI_WITH_QUERY_STRING = "requestURIWithQueryString";
    private static final String MDC_REQUEST_URL                   = "requestURL";
    private static final String MDC_REQUEST_URL_WITH_QUERY_STRING = "requestURLWithQueryString";
    private static final String MDC_USER_AGENT                    = "userAgent";
    private static final String MDC_METHOD                        = "method";
    private static final String MDC_REFERRER                      = "referrer";

    private static final String DEFAULT_STRATEGIES_PATH           = "webParameter.properties";
    private static final String DEFAULT_EXCLUDE_PARAM             = "excludePrintParamNames";
    /** 多入参间隔 */
    public static final String  SUB_EXPRESSION                    = "|";

    /** 排除打印参数 */
    private static List<String> excludePrintParamNames            = new ArrayList<String>();
    private static Properties   bizParamNames;
    private static List         IGNORE_URL_LIST                   = Lists.newArrayList();
    static {
        IGNORE_URL_LIST.add("/posp-admin/workOrder/queryUnWork.htm");
        IGNORE_URL_LIST.add("/posp-admin/login/login.htm");
        IGNORE_URL_LIST.add("/workOrder/queryUnWork.htm");
        IGNORE_URL_LIST.add("/login/login.htm");
    }
    /** 开关 */
    private boolean filterApplied = true;

  /*  static {
        try {
            ClassPathResource resource = new ClassPathResource(DEFAULT_STRATEGIES_PATH);
            bizParamNames = PropertiesLoaderUtils.loadProperties(resource);
            String excludePrintParamNameStr = bizParamNames.getProperty(DEFAULT_EXCLUDE_PARAM);
            if (!Strings.isNullOrEmpty(excludePrintParamNameStr)) {
                String[] excludePrintParams = StringUtils.split(excludePrintParamNameStr, SUB_EXPRESSION);
                excludePrintParamNames = Arrays.asList(excludePrintParams);
            }
            bizParamNames.remove(DEFAULT_EXCLUDE_PARAM);
        } catch (IOException ex) {
            LoggerUtils.debug(LOGGER, "Could not load 'webParameter.properties': " + ex.getMessage());
        }
    }*/

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = getRequestURI(request, false);
        if (!filterApplied || requestURI.indexOf(".") > 0) {
            filterChain.doFilter(request, response);
            return;
        }
        boolean hasError = false;

        long startTime = System.currentTimeMillis();
        try {
            //trace id
            TracerFactory.getHttpTracer().start(request, response);
            // 构建日志线程变量
            initMDC(request);

            if (LoggerConstant.WEB_DETAIL_LOGGER.isInfoEnabled()) {
                //LoggerConstant.WEB_DETAIL_LOGGER.info("Receive http request");
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            hasError = true;
            logger.error("系统异常了", e);
            throw e;
        } finally {
            long elapsed = System.currentTimeMillis() - startTime;
            if (LoggerConstant.PAGE_DIGEST_LOGGER.isWarnEnabled() && !IGNORE_URL_LIST.contains(requestURI)) {
                int status = response.getStatus();
                String traceId = MDC.get(TraceContext.TRACE_ID_KEY);
                StringBuilder message = new StringBuilder();
                message.append("[(").append(requestURI).append(",").append(request.getMethod()).append(",").append(status).append(",").append(hasError ? "N" : "Y").append(",").append(elapsed)
                    .append("ms").append(",").append(getIp(request)).append(")]").append(traceId);
                LoggerConstant.PAGE_DIGEST_LOGGER.warn(message.toString());

            }

            // 清理线程变量
            cleanMDC();
            TracerFactory.getHttpTracer().finish(request, response);
        }
    }

    protected void initMDC(HttpServletRequest request) {

        //http method
        putMDC(MDC_METHOD, request.getMethod());

        //完整URL,不包含参数
        putMDC(MDC_REQUEST_URL, getRequestURL(request, false));

        //完整URL,包含参数
        putMDC(MDC_REQUEST_URL_WITH_QUERY_STRING, getRequestURL(request, true));

        //URI(不包含host),不包含参数
        putMDC(MDC_REQUEST_URI, getRequestURI(request, false));

        //URI(不包含host),不包含参数
        putMDC(MDC_REQUEST_URI_WITH_QUERY_STRING, getRequestURI(request, true));

        //远程host
        putMDC(MDC_REMOTE_HOST, request.getRemoteHost());

        //远程ip地址
        String remoteAddr = request.getHeader("X-Real-IP");
        if (remoteAddr == null || remoteAddr.equals("")) {
            remoteAddr = request.getRemoteAddr();
        }
        putMDC(MDC_REMOTE_ADDR, remoteAddr);

        putMDC(MDC_USER_AGENT, request.getHeader("User-Agent"));
        putMDC(MDC_REFERRER, request.getHeader("Referer"));

    }

    public static String getIp(HttpServletRequest request) {

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
            ex.printStackTrace();
        }
        int port = request.getRemotePort();// 返回发出请求的客户机的端口号。
        //ip);// 保存所有IP
        return ip1;

    }

    /**
     * 清理日志线程变量
     */
    protected void cleanMDC() {
        removeMDC(MDC_METHOD);
        removeMDC(MDC_REQUEST_URL);
        removeMDC(MDC_REQUEST_URL_WITH_QUERY_STRING);
        removeMDC(MDC_REQUEST_URI);
        removeMDC(MDC_REQUEST_URI_WITH_QUERY_STRING);
        removeMDC(MDC_REMOTE_HOST);
        removeMDC(MDC_REMOTE_ADDR);
        removeMDC(MDC_USER_AGENT);
        removeMDC(MDC_REFERRER);
    }

    /**
     * 存放线程变量
     *
     * @param key
     * @param value
     */
    protected void putMDC(final String key, final String value) {
        if (key == null || value == null) {
            return;
        }
        MDC.put(key, value);
    }

    /**
     * 删除线程变量
     *
     * @param key
     */
    protected void removeMDC(final String key) {
        if (key == null) {
            return;
        }
        MDC.remove(key);
    }

    /**
     * 取得当前的request URI，包括query string。
     *
     * @param withQueryString 是否包含query string
     *
     * @return 当前请求的request URI
     */
    private String getRequestURI(HttpServletRequest request, boolean withQueryString) {
        StringBuffer buffer = new StringBuffer(request.getRequestURI());

        if (withQueryString) {
            String queryString = queryString(request);

            if (queryString != null) {
                buffer.append('?').append(queryString);
            }
        }
        return buffer.toString();
    }

    /**
     * 取得当前的request URL，包括query string。
     *
     * @param withQueryString 是否包含query string
     *
     * @return 当前请求的request URL
     */
    private String getRequestURL(HttpServletRequest request, boolean withQueryString) {
        StringBuffer buffer = request.getRequestURL();

        if (withQueryString) {
            String queryString = queryString(request);

            if (queryString != null) {
                buffer.append('?').append(queryString);
            }
        }
        return buffer.toString();
    }

    /**
     * 获取查询参数字符串(支持post参数)
     *
     * @param request
     * @return
     */
    private String queryString(HttpServletRequest request) {
        String queryString = request.getQueryString();
        if (Strings.isNullOrEmpty(request.getCharacterEncoding())) {
            return queryString;
        }
        StringBuilder builder = new StringBuilder();
        Map<String, ?> parametMaps = request.getParameterMap();
        if (parametMaps == null || parametMaps.isEmpty()) {
            return builder.toString();
        }

        for (String key : parametMaps.keySet()) {
            //需要被排除的参数
            if (!excludePrintParamNames.contains(key)) {
                builder.append(key).append("=").append(parametMaps.get(key).toString()).append("&");
            }
        }
        if (builder.length() > 0) {
            queryString = builder.substring(0, builder.length() - 1);
        }
        return queryString;
    }

}
