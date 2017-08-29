package net.fnsco.bigdata.api.dto;

import java.util.List;

import net.fnsco.core.base.DTO;

/**
 * @desc APP商家终端列表实体
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年6月30日 上午11:38:57
 *
 */

public class MerTerminalsDTO extends DTO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = 7716998928797164939L;
    
    private String merName;
    
    private List<TerminalsDTO> terminals;

    /**
     * merName
     *
     * @return  the merName
     * @since   CodingExample Ver 1.0
    */
    
    public String getMerName() {
        return merName;
    }

    /**
     * merName
     *
     * @param   merName    the merName to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setMerName(String merName) {
        this.merName = merName;
    }

    /**
     * terminals
     *
     * @return  the terminals
     * @since   CodingExample Ver 1.0
    */
    
    public List<TerminalsDTO> getTerminals() {
        return terminals;
    }

    /**
     * terminals
     *
     * @param   terminals    the terminals to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTerminals(List<TerminalsDTO> terminals) {
        this.terminals = terminals;
    }
    
}
