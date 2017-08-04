package net.fnsco.order.api.dto;

import net.fnsco.core.base.DTO;

/**
 * @desc 终端列表实体
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年6月30日 上午11:43:19
 *
 */

public class TerminalsDTO extends DTO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = -2226859972769011486L;
    
    private Integer id;
    
    private String termName;
    
    private String terminalCode;
    

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
     * id
     *
     * @return  the id
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getId() {
        return id;
    }

    /**
     * id
     *
     * @param   id    the id to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setId(Integer id) {
        this.id = id;
    }

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

}
