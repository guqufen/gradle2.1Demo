package net.fnsco.api.dto;

import java.util.List;

import net.fnsco.core.base.DTO;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月1日 下午5:50:20
 */

public class TradeTurnoverDTO extends DTO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = 1544559212500669503L;
    
    private List<TurnoverDTO> turnovers;
    
//    private List<TradeMerchantDTO> tradeMerchant;

    /**
     * turnovers
     *
     * @return  the turnovers
     * @since   CodingExample Ver 1.0
    */
    
    public List<TurnoverDTO> getTurnovers() {
        return turnovers;
    }

    /**
     * turnovers
     *
     * @param   turnovers    the turnovers to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTurnovers(List<TurnoverDTO> turnovers) {
        this.turnovers = turnovers;
    }

//    /**
//     * tradeMerchant
//     *
//     * @return  the tradeMerchant
//     * @since   CodingExample Ver 1.0
//    */
//    
//    public List<TradeMerchantDTO> getTradeMerchant() {
//        return tradeMerchant;
//    }
//
//    /**
//     * tradeMerchant
//     *
//     * @param   tradeMerchant    the tradeMerchant to set
//     * @since   CodingExample Ver 1.0
//     */
//    
//    public void setTradeMerchant(List<TradeMerchantDTO> tradeMerchant) {
//        this.tradeMerchant = tradeMerchant;
//    }
//    
}
