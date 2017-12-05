package net.fnsco.web.controller.merchant.pos;

import java.util.List;

import net.fnsco.bigdata.service.domain.MerchantPos;
import net.fnsco.bigdata.service.domain.MerchantTerminal;

/**
 * 
 * @desc   pos信息和终端信息
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月17日 上午11:33:04
 */
public class WebMerchantTerminalDTO2 {
    
    /**
     * POS机子信息
     */
    private MerchantPos merchantPos;
  
	/**
     * 终端信息
     */
    private List<MerchantTerminal>  terminals;

    /**
     * merchantPos
     *
     * @return  the merchantPos
     * @since   CodingExample Ver 1.0
    */
    
    public MerchantPos getMerchantPos() {
        return merchantPos;
    }

    /**
     * merchantPos
     *
     * @param   merchantPos    the merchantPos to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setMerchantPos(MerchantPos merchantPos) {
        this.merchantPos = merchantPos;
    }

    /**
     * terminals
     *
     * @return  the terminals
     * @since   CodingExample Ver 1.0
    */
    public List<MerchantTerminal> getTerminals() {
        return terminals;
    }

    /**
     * terminals
     *
     * @param   terminals    the terminals to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTerminals(List<MerchantTerminal> terminals) {
        this.terminals = terminals;
    }
    
}
