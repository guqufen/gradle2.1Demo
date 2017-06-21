package net.fnsco.freamwork.log.trace;

import java.util.UUID;

import org.slf4j.MDC;

/**
 *
 * @author sxfei
 * @version  
 */
public abstract class Tracer {
    /**
     * traceId复制到log的mdc中
     *
     */
    protected void copyTraceIdToMDC() {
        MDC.remove(TraceContext.TRACE_ID_KEY);
        TraceLogContext traceContext = TraceContext.currentTraceLogContext();
        if (traceContext != null) {
            MDC.put(TraceContext.TRACE_ID_KEY, traceIdForLog(traceContext.getTraceId()));
        }
    }

    /**
     * 组装一个可打印的traceId
     *
     * @param traceId
     * @return
     */
    private String traceIdForLog(String traceId) {
        return "(" + TraceContext.TRACE_ID_KEY + "=" + traceId + ")";
    }

    /**
     * 创建traceId
     *
     * @return
     */
    protected String createTraceId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
