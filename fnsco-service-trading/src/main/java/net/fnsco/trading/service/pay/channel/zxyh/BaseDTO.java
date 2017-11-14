package net.fnsco.trading.service.pay.channel.zxyh;

import net.fnsco.core.base.DTO;

public class BaseDTO extends DTO {
    private String signAture;

    /**
     * signAture
     *
     * @return  the signAture
     * @since   CodingExample Ver 1.0
    */

    public String getSignAture() {
        return signAture;
    }

    /**
     * signAture
     *
     * @param   signAture    the signAture to set
     * @since   CodingExample Ver 1.0
     */

    public void setSignAture(String signAture) {
        this.signAture = signAture;
    }

}
