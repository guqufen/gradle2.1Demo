package net.fnsco.freamwork.log.trace;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.freamwork.log.LoggerUtils;

/**
 * web入口
 *
 * @author YuJiaqi
 * @version $Id: HttpTracer.java v 0.1 16/9/1 下午2:05 YuJiaqi Exp $$
 */
public class HttpTracer extends Tracer {

    private static final Logger LOGGER                    = LoggerFactory.getLogger(HttpTracer.class);

    public static final String  EAGLE_REQUEST_TRACER_BEAN = "com.taobao.eagleeye.EagleEyeRequestTracer";

    public static final String  START_TRACE_METHOD        = "startTrace";

    public static final String  END_TRACE_METHOD          = "endTrace";

    private boolean             traceEnabled;

    private Method              startTrace                = null;

    private Method              endTrace                  = null;

    public HttpTracer() {
        try {
            Class<?> eagleTracerClazz = Class.forName(EAGLE_REQUEST_TRACER_BEAN);

            Method startTrace = eagleTracerClazz.getDeclaredMethod(START_TRACE_METHOD, String.class, HttpServletRequest.class, HttpServletResponse.class);
            startTrace.setAccessible(true);

            Method endTrace = eagleTracerClazz.getDeclaredMethod(END_TRACE_METHOD, HttpServletRequest.class, HttpServletResponse.class);
            endTrace.setAccessible(true);

            this.startTrace = startTrace;
            this.endTrace = endTrace;
            this.traceEnabled = true;
        } catch (Throwable t) {
            this.traceEnabled = false;
            LoggerUtils.warn(LOGGER, "初始化eagle tracer失败");
        }
    }

    /**
     * 生成traceId,并放到日志线程上下文
     *
     */
    public void start(HttpServletRequest request, HttpServletResponse response) {
        try {
            TraceLogContext parentContext = TraceContext.currentTraceLogContext();

            String traceId = null;
            if (parentContext == null) {
                traceId = createTraceId();
                //同时初始化eagle eye
                if (traceEnabled) {
                    this.startTrace.invoke(null, new Object[] { traceId, request, response });
                }
            } else {
                traceId = parentContext.getTraceId();
            }

            TraceLogContext currentContext = new TraceLogContext();
            currentContext.setTraceId(traceId);
            currentContext.startTime = System.currentTimeMillis();
            currentContext.parentLogContext = parentContext;
            TraceContext.setTraceLogContext(currentContext);

            //日志线程上下文
            copyTraceIdToMDC();
        } catch (Exception e) {
            LoggerUtils.warn(LOGGER, e, "开始trace context错误");
        }
    }

    /**
     * 结束一次上下文
     *
     */
    public TraceLogContext finish(HttpServletRequest request, HttpServletResponse response) {
        try {
            TraceLogContext traceContext = TraceContext.currentTraceLogContext();
            traceContext.logTime = System.currentTimeMillis();
            TraceContext.setTraceLogContext(traceContext.parentLogContext);

            copyTraceIdToMDC();
            return traceContext;
        } catch (Exception e) {
            LoggerUtils.warn(LOGGER, e, "结束trace context错误");
        } finally {
            if (traceEnabled) {
                try {
                    this.endTrace.invoke((Object) null, request, response);
                } catch (Exception e) {
                    this.traceEnabled = false;
                }
            }
        }
        return null;
    }
}
