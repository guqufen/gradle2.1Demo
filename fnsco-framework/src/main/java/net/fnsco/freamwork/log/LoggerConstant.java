package net.fnsco.freamwork.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sxfei
 * @version  v 0.1 17/3/6
 */
public class LoggerConstant {

    /** 通用日志 */
    public static Logger COMMON_DEFAULT_LOGGER = LoggerFactory.getLogger("SYS-COMMON-DEFAULT");

    /** 数据层摘要日志  */
    public static Logger DAL_DIGEST_LOGGER     = LoggerFactory.getLogger("SYS-DAL-DIGEST");

    /** 服务引用接口摘要日志  */
    public static Logger SAL_DIGEST_LOGGER     = LoggerFactory.getLogger("SYS-SAL-DIGEST");

    /** 服务发布接口摘要日志  */
    public static Logger SERVICE_DIGEST_LOGGER = LoggerFactory.getLogger("SYS-SERVICE-DIGEST");

    /** web层摘要日志  */
    public static Logger PAGE_DIGEST_LOGGER    = LoggerFactory.getLogger("SYS-PAGE-DIGEST");

    /** web层具体日志 */
    public static Logger WEB_DETAIL_LOGGER     = LoggerFactory.getLogger("SYS-WEB-DETAIL");

    /** 系统消息发送摘要日志 */
    public static Logger MSG_SEND_LOGGER       = LoggerFactory.getLogger("SYS-MSG-SEND-LOG");

    /** 系统消息接受摘要日志 */
    public static Logger MSG_RECEIVE_LOGGER    = LoggerFactory.getLogger("SYS-MSG-RECEIVE-LOG");

    /** 方法调用具体信息日志 */
    public static Logger METHOD_DEBUG_LOGGER   = LoggerFactory.getLogger("SYS-METHOD-DEBUG");

    /** 缓存类操作摘要日志 */
    public static Logger CACHE_DIGEST_LOGGER   = LoggerFactory.getLogger("SYS-CACHE-DIGEST");

    /** JMS类操作摘要日志 */
    public static Logger JMS_DIGEST_LOGGER     = LoggerFactory.getLogger("SYS-JMS-DIGEST");

    /** NOSQL类操作摘要日志 */
    public static Logger NOSQL_DIGEST_LOGGER   = LoggerFactory.getLogger("SYS-NOSQL-DIGEST");

    /** GW类操作摘要日志 */
    public static Logger GW_DIGEST_LOGGER      = LoggerFactory.getLogger("SYS-GW-DIGEST");

}
