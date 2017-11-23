package net.fnsco.bigdata.api.dto;

import java.util.List;
import net.fnsco.core.base.DTO;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月16日 下午1:59:59
 */

public class PosDetailDTO extends DTO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = -1246884277721829088L;
    
    private String posName;
    
    private String snCode;

    private String posFactory;

    private String posType;
    
    private String terminalCode;
    private String qrChannelTerminalCode;
    /**
     * 终端名称
     */
    private List<TerminalInfoDTO> terNames;
    
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
	 * qrChannelTerminalCode
	 *
	 * @return  the qrChannelTerminalCode
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getQrChannelTerminalCode() {
		return qrChannelTerminalCode;
	}
	/**
	 * qrChannelTerminalCode
	 *
	 * @param   qrChannelTerminalCode    the qrChannelTerminalCode to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setQrChannelTerminalCode(String qrChannelTerminalCode) {
		this.qrChannelTerminalCode = qrChannelTerminalCode;
	}
	/**
     * posName
     *
     * @return  the posName
     * @since   CodingExample Ver 1.0
    */
    
    public String getPosName() {
        return posName;
    }
    /**
     * posName
     *
     * @param   posName    the posName to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setPosName(String posName) {
        this.posName = posName;
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
     * terNames
     *
     * @return  the terNames
     * @since   CodingExample Ver 1.0
    */
    
    public List<TerminalInfoDTO> getTerNames() {
        return terNames;
    }
    /**
     * terNames
     *
     * @param   terNames    the terNames to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTerNames(List<TerminalInfoDTO> terNames) {
        this.terNames = terNames;
    }
}
