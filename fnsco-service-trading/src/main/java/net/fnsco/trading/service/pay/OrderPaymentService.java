package net.fnsco.trading.service.pay;


import java.util.Map;

import org.springframework.cache.annotation.EnableCaching;

import net.fnsco.bigdata.api.dto.MerchantCoreEntityZxyhDTO;
import net.fnsco.core.base.ResultDTO;

@EnableCaching(proxyTargetClass=true)
public interface OrderPaymentService {
    /**
     * 
     * trade:(入驻独立商户)
     *   void    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
	public ResultDTO<Object> aliCallBack(String resultStr);
	
	public ResultDTO<Map<String, Object>> generateQRCodeWeiXin(Integer userId,String txnAmt,String entityInnerCode,String innerCode);
	
	public ResultDTO<Map<String, Object>> generateQRCodeAliPay(Integer userId,String ip,String txnAmt,String entityInnerCode,String innerCode);
	
    public Map<String, Object> mechAdd(MerchantCoreEntityZxyhDTO core);
    
    //    public void pay(){
    //        if (Constant.ChannelTypeEnum.PF_PAY.getCode().equals(merchantInfoDO.getChannelType())) {
    //            orderPaymentResult = pFOrderPaymentService.beisaoPaySendPost(orderPayment);
    //        } else if (Constant.ChannelTypeEnum.AN_PAY.getCode().equals(merchantInfoDO.getChannelType())) {
    //            orderPaymentResult = aNOrderPaymentService.beisaoPaySendPost(orderPayment);
    //        }
    //    }
}
