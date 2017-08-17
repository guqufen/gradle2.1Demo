package net.fnsco.order.api.dto;

import net.fnsco.core.base.DTO;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月16日 下午1:45:16
 */

public class PosInfoDTO extends DTO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = 7085083346173299618L;
    
    /**
     * POS机ID
     */
    private Integer posId;
    
    /**
     * POS名称
     */
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
    
}
