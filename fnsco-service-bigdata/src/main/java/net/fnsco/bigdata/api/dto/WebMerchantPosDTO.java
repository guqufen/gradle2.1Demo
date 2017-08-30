package net.fnsco.bigdata.api.dto;

import java.util.List;

import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.core.base.DTO;

/**
 * @desc WEB页面修改或增加POS终端信息接收实体
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月17日 上午11:25:22
 */

public class WebMerchantPosDTO extends DTO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = 8952837107289447661L;
    
    /**
     * 渠道信息
     */
    private MerchantChannel merChannel;
    
    /**
     * POS机子信息
     */
    private List<WebMerchantTerminalDTO> posInfos;

    /**
     * merChannel
     *
     * @return  the merChannel
     * @since   CodingExample Ver 1.0
    */
    
    public MerchantChannel getMerChannel() {
        return merChannel;
    }

    /**
     * merChannel
     *
     * @param   merChannel    the merChannel to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setMerChannel(MerchantChannel merChannel) {
        this.merChannel = merChannel;
    }

    /**
     * posInfos
     *
     * @return  the posInfos
     * @since   CodingExample Ver 1.0
    */
    
    public List<WebMerchantTerminalDTO> getPosInfos() {
        return posInfos;
    }

    /**
     * posInfos
     *
     * @param   posInfos    the posInfos to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setPosInfos(List<WebMerchantTerminalDTO> posInfos) {
        this.posInfos = posInfos;
    }
    
}
