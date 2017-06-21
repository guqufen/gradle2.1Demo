package net.fnsco.freamwork.log.interceptor;

import org.aopalliance.intercept.MethodInterceptor;

/**
 *
 * @author sxfei
 * @version v0.1 2017/03/07
 */
public abstract class AbstractLogInterceptor implements MethodInterceptor {

    protected boolean shouldSkip(String className, String methodName) {
        if ("java.lang.Object".equals(className)) {
            return true;
        }
        return false;
    }

}
