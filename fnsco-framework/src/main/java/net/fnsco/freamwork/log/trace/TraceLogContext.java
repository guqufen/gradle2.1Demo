package net.fnsco.freamwork.log.trace;

import java.util.HashMap;
import java.util.Map;

public class TraceLogContext {

    /** 上下文 */
    Map<String, String> tracerContext = new HashMap<String, String>();

    /** 父上下文 */
    TraceLogContext     parentLogContext;

    /** 上下文起始时间 */
    long                startTime     = 0L;

    /** 记录时间 */
    long                logTime       = 0L;

    public TraceLogContext() {
    }

    public TraceLogContext(String traceId, String rpcId) {
        tracerContext.put(TraceContext.TRACE_ID_KEY, traceId);
        tracerContext.put(TraceContext.RPC_ID_KEY, rpcId);
    }

    /**
     * 获取traceId
     *
     * @return
     */
    public String getTraceId() {
        return tracerContext.get(TraceContext.TRACE_ID_KEY);
    }

    /**
     * 设置traceId
     *
     * @param traceId
     */
    public void setTraceId(String traceId) {
        tracerContext.put(TraceContext.TRACE_ID_KEY, traceId);
    }

    /**
     * 获取这次上下文调用花费的时间
     *
     * @return
     */
    public long getElapsedTime() {
        return logTime - startTime;
    }
}
