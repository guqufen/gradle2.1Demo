package net.fnsco.web.controller.app.jo;

import net.fnsco.core.base.JO;

/**
 * @desc  设备终端信息接收实体
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年6月30日 下午2:10:57
 */

public class TerminalJO extends JO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = 964813040421444306L;
    
    private Integer terId;
    
    private String termName;
    
    private Integer posId;
    
    private String posName;
    
    /**
     * posId
     *
     * @return  the posId
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getPosId() {
        return posId;
    }

    /**
     * posId
     *
     * @param   posId    the posId to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setPosId(Integer posId) {
        this.posId = posId;
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
     * terId
     *
     * @return  the terId
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getTerId() {
        return terId;
    }

    /**
     * terId
     *
     * @param   terId    the terId to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTerId(Integer terId) {
        this.terId = terId;
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
