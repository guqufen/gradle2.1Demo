package net.fnsco.order.api.dto;

import net.fnsco.core.base.DTO;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月16日 下午4:52:36
 */

public class TerminalInfoDTO extends DTO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = -4113983287234907135L;
    
    /**
     * 终端名称
     */
    private String terName;
    
    /**
     * 通道终端号
     */
    private String terminalCode;
    
    /**
     * APP显示标题
     */
    private String terTitle;
    
    /**
     * terTitle
     *
     * @return  the terTitle
     * @since   CodingExample Ver 1.0
    */
    
    public String getTerTitle() {
        return terTitle;
    }

    /**
     * terTitle
     *
     * @param   terTitle    the terTitle to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTerTitle(String terTitle) {
        this.terTitle = terTitle;
    }

    /**
     * terminalCode
     *
     * @return  the terminalCode
     * @since   CodingExample Ver 1.0
    */
    
    public String getTerminalCode() {
        return terminalCode;
    }

    /**
     * terminalCode
     *
     * @param   terminalCode    the terminalCode to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    /**
     * terName
     *
     * @return  the terName
     * @since   CodingExample Ver 1.0
    */
    
    public String getTerName() {
        return terName;
    }

    /**
     * terName
     *
     * @param   terName    the terName to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTerName(String terName) {
        this.terName = terName;
    }
    
}
