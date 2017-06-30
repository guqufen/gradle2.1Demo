package net.fnsco.api.dto;

import net.fnsco.core.base.DTO;

/**
 * @desc APP端终端设备详情实体
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年6月30日 下午1:37:44
 *
 */

public class TerminalDetailDTO extends DTO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = 5176906655454918701L;
    
    private String termName;
    
    private String posFactory;
    
    private String posType;
    
    private String snCode;


    /**
     * termName
     *
     * @return  the termName
     * @since   CodingExample Ver 1.0
    */
    
    public String getTermName() {
        return termName;
    }

    /**
     * termName
     *
     * @param   termName    the termName to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTermName(String termName) {
        this.termName = termName;
    }

    /**
     * posFactory
     *
     * @return  the posFactory
     * @since   CodingExample Ver 1.0
    */
    
    public String getPosFactory() {
        return posFactory;
    }

    /**
     * posFactory
     *
     * @param   posFactory    the posFactory to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setPosFactory(String posFactory) {
        this.posFactory = posFactory;
    }

    /**
     * posType
     *
     * @return  the posType
     * @since   CodingExample Ver 1.0
    */
    
    public String getPosType() {
        return posType;
    }

    /**
     * posType
     *
     * @param   posType    the posType to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setPosType(String posType) {
        this.posType = posType;
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
    
}
