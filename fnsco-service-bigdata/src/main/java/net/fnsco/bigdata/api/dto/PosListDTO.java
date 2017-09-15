package net.fnsco.bigdata.api.dto;

import java.util.List;

import net.fnsco.core.base.DTO;

/**
 * @desc POS列表
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月16日 上午11:46:32
 */

public class PosListDTO extends DTO {


    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = -1312077621470328903L;
    
    private String merName;
    
    private String innerCode;
    /**
     * POS机名称列表
     */
    private List<PosInfosDTO> posInfo;
    
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

    /**
     * posInfo
     *
     * @return  the posInfo
     * @since   CodingExample Ver 1.0
    */
    
    public List<PosInfosDTO> getPosInfo() {
        return posInfo;
    }

    /**
     * posInfo
     *
     * @param   posInfo    the posInfo to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setPosInfo(List<PosInfosDTO> posInfo) {
        this.posInfo = posInfo;
    }

}
