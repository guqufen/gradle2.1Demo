package net.fnsco.order.api.dto;

import net.fnsco.core.base.DTO;

/**
 * @desc APP商家列表实体
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年6月29日 下午5:50:19
 */

public class MerChantCoreDTO extends DTO{

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = 2938074403134697431L;
    
    
    private Integer id;
    
    private String merName;
    
    private String legalPerson;
    
    private String registAddress;

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
     * legalPerson
     *
     * @return  the legalPerson
     * @since   CodingExample Ver 1.0
    */
    
    public String getLegalPerson() {
        return legalPerson;
    }

    /**
     * legalPerson
     *
     * @param   legalPerson    the legalPerson to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    /**
     * registAddress
     *
     * @return  the registAddress
     * @since   CodingExample Ver 1.0
    */
    
    public String getRegistAddress() {
        return registAddress;
    }

    /**
     * registAddress
     *
     * @param   registAddress    the registAddress to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setRegistAddress(String registAddress) {
        this.registAddress = registAddress;
    }
    

}
