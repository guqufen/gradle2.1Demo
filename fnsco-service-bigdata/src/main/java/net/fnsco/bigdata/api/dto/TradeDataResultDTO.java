package net.fnsco.bigdata.api.dto;

import net.fnsco.core.base.DTO;

public class TradeDataResultDTO extends DTO {

    private String amtTot; //总金额，分
    private String count;  //总笔数

    /**
     * amtTot
     *
     * @return  the amtTot
     * @since   CodingExample Ver 1.0
    */

    public String getAmtTot() {
        return amtTot;
    }

    /**
     * amtTot
     *
     * @param   amtTot    the amtTot to set
     * @since   CodingExample Ver 1.0
     */

    public void setAmtTot(String amtTot) {
        this.amtTot = amtTot;
    }

    /**
     * count
     *
     * @return  the count
     * @since   CodingExample Ver 1.0
    */

    public String getCount() {
        return count;
    }

    /**
     * count
     *
     * @param   count    the count to set
     * @since   CodingExample Ver 1.0
     */

    public void setCount(String count) {
        this.count = count;
    }

}
