package net.fnsco.freamwork.log.trace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 追踪上下文,兼容DUBBO和HSF
 *
 * @author sxfei
 * @version  
 */
public class TraceContext {

    private static final Logger                       LOGGER           = LoggerFactory.getLogger(TraceContext.class);
    /** RpcId 分隔符 */
    public static final String                        RPC_ID_SEPARATOR = ".";

    /** RpcId 放在透传上下文中的 key */
    public static final String                        RPC_ID_KEY       = "rpcId";

    /** TraceId 放在透传上下文中的 key */
    public static final String                        TRACE_ID_KEY     = "traceId";

    /** 线程变量 */
    private static final ThreadLocal<TraceLogContext> CONTEXT_HOLDER   = new ThreadLocal<TraceLogContext>();

    /**
     * 获取当前线程的上下文
     *
     * @return
     */
    public static TraceLogContext currentTraceLogContext() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 设置当前线程上下文
     *
     * @param traceLogContext
     */
    public static void setTraceLogContext(TraceLogContext traceLogContext) {
        CONTEXT_HOLDER.set(traceLogContext);
    }

}
