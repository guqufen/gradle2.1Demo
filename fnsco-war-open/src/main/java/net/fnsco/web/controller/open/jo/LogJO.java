package net.fnsco.web.controller.open.jo;

import net.fnsco.core.base.JO;

public class LogJO extends JO {
    //终端号
    private String termCode;
    //pos号
    private String snCode;
    //log日志
    private String logInfo;

    /**
     * termCode
     *
     * @return  the termCode
     * @since   CodingExample Ver 1.0
    */

    public String getTermCode() {
        return termCode;
    }

    /**
     * termCode
     *
     * @param   termCode    the termCode to set
     * @since   CodingExample Ver 1.0
     */

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    /**
     * snCode
     *
     * @return  the snCode
     * @since   CodingExample Ver 1.0
    */

    public String getSnCode() {
        return snCode;
    }

    /**
     * snCode
     *
     * @param   snCode    the snCode to set
     * @since   CodingExample Ver 1.0
     */

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

}
