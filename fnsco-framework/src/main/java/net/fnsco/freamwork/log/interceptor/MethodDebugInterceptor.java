package net.fnsco.freamwork.log.interceptor;

import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.MDC;

import com.alibaba.fastjson.JSONObject;

import net.fnsco.freamwork.log.LoggerConstant;
import net.fnsco.freamwork.log.LoggerUtils;
import net.fnsco.freamwork.log.trace.TraceContext;

/**
 * 方法调用debug拦截器
 *
 * @author sxfei
 * @version v0.1 2017/03/07
 */
public class MethodDebugInterceptor extends AbstractLogInterceptor {

    /** 是否开启debug模式 */
    private boolean debugMode = false;

    /** 耗时阈值 默认为1S */
    private int     threshold = 1000;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Class<?> clazz = invocation.getMethod().getDeclaringClass();

        String className = clazz.getSimpleName();
        String methodName = invocation.getMethod().getName();

        long startTime = System.currentTimeMillis();

        Object result = null;
        try {
            result = invocation.proceed();
            return result;
        } finally {
            long elapsed = System.currentTimeMillis() - startTime;
            if (LoggerConstant.METHOD_DEBUG_LOGGER.isInfoEnabled()) {
                if (debugMode || (elapsed > threshold)) {
                    try {
                        String traceId = MDC.get(TraceContext.TRACE_ID_KEY);
                        StringBuilder log = new StringBuilder("[(");
                        log.append(className).append(",").append(methodName).append(",").append(elapsed).append("ms)]");
                        log.append("[").append(getParams(invocation.getArguments())).append("]");
                        log.append("[").append(getResult(result)).append("]").append(traceId);
                        LoggerUtils.info(LoggerConstant.METHOD_DEBUG_LOGGER, log.toString());
                    } catch (Exception e) {
                        LoggerUtils.error(LoggerConstant.METHOD_DEBUG_LOGGER, e, "debug fail");
                    }
                }
            }
        }
    }

    /**
     * 获取入参
     *
     * @param args
     * @return
     */
    private String getParams(Object[] args) {
        StringBuilder sb = new StringBuilder("params=");
        sb.append(objectToString(args));
        return sb.toString();
    }

    /**
     * 获取返回值
     *
     * @param result
     * @return
     */
    private String getResult(Object result) {
        StringBuilder sb = new StringBuilder("result=");
        //返回值大于10就只显示size,不然内容太多
        if (result instanceof List && ((List) result).size() >= 10) {
            sb.append(result.getClass().getSimpleName() + "(size=" + ((List) result).size() + ")");
        } else {
            sb.append(objectToString(result));
        }
        return sb.toString();
    }

    /**
     * 对象转string
     *
     * @param result
     * @return
     */
    private String objectToString(Object result) {
        StringBuffer logStr = new StringBuffer();
        try {
            logStr.append(JSONObject.toJSON(result).toString());
        } catch (Exception ex) {
            logStr.append("参数无法序列化" + result.getClass().getName());
        }

        return logStr.toString();
    }

    /**
     * 对象转string
     *
     * @param result
     * @return
     */
    private String objectToString(Object[] result) {
        StringBuffer logStr = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            logStr.append(objectToString(result[i]));
        }
        return logStr.toString();
    }

    /**
     * Setter for property 'debugMode'.
     *
     * @param debugMode Value to set for property 'debugMode'.
     */
    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    /**
     * Setter for property 'threshold'.
     *
     * @param threshold Value to set for property 'threshold'.
     */
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
