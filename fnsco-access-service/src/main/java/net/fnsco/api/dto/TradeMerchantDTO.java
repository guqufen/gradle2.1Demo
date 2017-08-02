package net.fnsco.api.dto;

import net.fnsco.core.base.DTO;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月1日 下午5:42:04
 */

public class TradeMerchantDTO extends DTO{
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = -712391792550362782L;
    private String innerCode;
    private String merName;
    /**
     * innerCode
     *
     * @return  the innerCode
     * @since   CodingExample Ver 1.0
    */
    
    public String getInnerCode() {
        return innerCode;
    }
    /**
     * innerCode
     *
     * @param   innerCode    the innerCode to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }
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
    
}
