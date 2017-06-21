package net.fnsco.freamwork.log;

import java.text.MessageFormat;

import org.slf4j.Logger;

/**
 * 日志工具
 *
 * @author sxfei
 * @version v0.1 17/03/06
 */
public class LoggerUtils {

    /**
     * 输出info level的log信息.
     *
     * @param logger    日志记录器
     * @param message   log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params    log格式化参数,数组length与message参数化个数相同, 如:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void info(Logger logger, String message, Object... params) {
        if (logger.isInfoEnabled()) {
            logger.info(format(message, params));
        }
    }

    /**
     * 输出warn level的log信息.
     *
     * @param logger    日志记录器
     * @param message   log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params    log格式化参数,数组length与message参数化个数相同, 如:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void warn(Logger logger, String message, Object... params) {
        if (logger.isWarnEnabled()) {
            logger.warn(format(message, params));
        }
    }

    /**
     * 输出warn level的log信息.
     *
     * @param logger    日志记录器
     * @param throwable 异常对象
     * @param message   log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params    log格式化参数,数组length与message参数化个数相同, 如:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void warn(Logger logger, Throwable throwable, String message, Object... params) {
        if (logger.isWarnEnabled()) {
            logger.warn(format(message, params), throwable);
        }
    }

    /**
     * 输出debug level的log信息.
     *
     * @param logger    日志记录器
     * @param message   log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params    log格式化参数,数组length与message参数化个数相同, 如:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void debug(Logger logger, String message, Object... params) {
        if (logger.isDebugEnabled()) {
            logger.debug(format(message, params));
        }
    }

    /**
     * 输出debug level的log信息.
     *
     * @param logger    日志记录器
     * @param throwable 异常对象
     * @param message   log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params    log格式化参数,数组length与message参数化个数相同, 如:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void debug(Logger logger, Throwable throwable, String message, Object... params) {
        if (logger.isDebugEnabled()) {
            logger.debug(format(message, params), throwable);
        }
    }

    /**
     * 输出error level的log信息.
     *
     * @param logger    日志记录器
     * @param message   log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params    log格式化参数,数组length与message参数化个数相同, 如:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void error(Logger logger, String message, Object... params) {
        if (logger.isErrorEnabled()) {
            logger.error(format(message, params));
        }
    }

    /**
     * 输出error level的log信息.
     *
     * @param logger    日志记录器
     * @param throwable 异常对象
     * @param message   log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params    log格式化参数,数组length与message参数化个数相同, 如:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void error(Logger logger, Throwable throwable, String message, Object... params) {
        if (logger.isErrorEnabled()) {
            logger.error(format(message, params), throwable);
        }
    }

    /**
     * 日志信息参数化格式化
     *
     * @param message   log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params    log格式化参数,数组length与message参数化个数相同, 如:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     * @return          格式化后的日志信息
     */
    private static String format(String message, Object... params) {
        if (message != null && params != null && params.length != 0) {
            return MessageFormat.format(message, params);
        }
        return message;

    }
}
