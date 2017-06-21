package net.fnsco.freamwork.log.trace;

/**
 *
 * @author sxfei
 * @version  v 0.1 16/9/1 
 */
public class TracerFactory {

    private static final HttpTracer HTTP_TRACER = new HttpTracer();

    public static HttpTracer getHttpTracer() {
        return HTTP_TRACER;
    }
}
