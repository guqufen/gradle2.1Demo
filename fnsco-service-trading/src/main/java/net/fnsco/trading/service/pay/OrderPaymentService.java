package net.fnsco.trading.service.pay;

import net.fnsco.bigdata.service.domain.trade.MerchantCoreEntityZxyhDTO;

public interface OrderPaymentService {
    /**
     * 
     * trade:(入驻独立商户)
     *   void    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    public void mechAdd(MerchantCoreEntityZxyhDTO core);
    //    public void pay(){
    //        if (Constant.ChannelTypeEnum.PF_PAY.getCode().equals(merchantInfoDO.getChannelType())) {
    //            orderPaymentResult = pFOrderPaymentService.beisaoPaySendPost(orderPayment);
    //        } else if (Constant.ChannelTypeEnum.AN_PAY.getCode().equals(merchantInfoDO.getChannelType())) {
    //            orderPaymentResult = aNOrderPaymentService.beisaoPaySendPost(orderPayment);
    //        }
    //    }
}
