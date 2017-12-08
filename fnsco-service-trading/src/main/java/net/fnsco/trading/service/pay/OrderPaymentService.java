package net.fnsco.trading.service.pay;


import java.util.Map;

import net.fnsco.bigdata.api.dto.MerchantCoreEntityZxyhDTO;
import net.fnsco.core.base.ResultDTO;


public interface OrderPaymentService {
    /**
     * 
     * trade:(入驻独立商户)
     *   void    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
	public ResultDTO<Object> aliCallBack(String resultStr);
	
	public ResultDTO<Map<String, Object>> generateQRCodeWeiXin(Integer userId,String txnAmt);
	
	public ResultDTO<Map<String, Object>> generateQRCodeAliPay(Integer userId,String ip,String txnAmt);
	
    public Map<String, Object> mechAdd(MerchantCoreEntityZxyhDTO core);
    
    //    public void pay(){
    //        if (Constant.ChannelTypeEnum.PF_PAY.getCode().equals(merchantInfoDO.getChannelType())) {
    //            orderPaymentResult = pFOrderPaymentService.beisaoPaySendPost(orderPayment);
    //        } else if (Constant.ChannelTypeEnum.AN_PAY.getCode().equals(merchantInfoDO.getChannelType())) {
    //            orderPaymentResult = aNOrderPaymentService.beisaoPaySendPost(orderPayment);
    //        }
    //    }
}
