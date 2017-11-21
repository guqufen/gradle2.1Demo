package net.fnsco.bigdata.api.dto;

import java.util.List;

import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantPos;
import net.fnsco.bigdata.service.domain.MerchantTerminal;
import net.fnsco.core.base.DTO;

/**
 * @desc WEB页面修改或增加POS终端信息接收实体
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月17日 上午11:25:22
 */

public class WebMerchantPosDTO2 extends DTO {

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
    private List<MerchantPos> posInfos;
    /**
     * 终端信息
     */
    private List<MerchantTerminal>  terminals;
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

	public List<MerchantPos> getPosInfos() {
		return posInfos;
	}

	public void setPosInfos(List<MerchantPos> posInfos) {
		this.posInfos = posInfos;
	}

	public List<MerchantTerminal> getTerminals() {
		return terminals;
	}

	public void setTerminals(List<MerchantTerminal> terminals) {
		this.terminals = terminals;
	}

     
    
}
