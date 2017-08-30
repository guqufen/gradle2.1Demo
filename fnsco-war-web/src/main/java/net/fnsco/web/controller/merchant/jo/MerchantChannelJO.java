package net.fnsco.web.controller.merchant.jo;

import java.util.List;

import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.core.base.JO;

public class MerchantChannelJO extends JO {
    /**
     * 渠道信息
     */
    private MerchantChannel     merChannel;

    /**
     * POS机子信息
     */
    private List<MerchantPosJO> posInfos;

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

    public List<MerchantPosJO> getPosInfos() {
        return posInfos;
    }

    /**
     * posInfos
     *
     * @param   posInfos    the posInfos to set
     * @since   CodingExample Ver 1.0
     */

    public void setPosInfos(List<MerchantPosJO> posInfos) {
        this.posInfos = posInfos;
    }

}
