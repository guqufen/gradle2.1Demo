package net.fnsco.web.controller.open;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import net.fnsco.bigdata.api.trade.TradeDataService;
import net.fnsco.bigdata.comm.ServiceConstant.PaySubTypeAllEnum;
import net.fnsco.bigdata.comm.ServiceConstant.PayTypeEnum;
import net.fnsco.bigdata.comm.ServiceConstant.TradeStateEnum;
import net.fnsco.bigdata.comm.ServiceConstant.TradeTypeEnum;
import net.fnsco.bigdata.service.domain.trade.TradeData;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.DbUtil;
import net.fnsco.web.controller.open.jo.FuiouJO;

@Controller
@RequestMapping(value="/syncData/fuiou", method=RequestMethod.POST)
@Api(value="/syncData/fuiou", tags={"富友交易实时传输交口"})
public class FuiouController extends BaseController{

	@Autowired
	private TradeDataService   tradeDataService;
	@Autowired
	private Environment env;

	@RequestMapping("/transtradeSave")
	@ResponseBody
	public ResultDTO saveTradeFuiou(String req)throws ParseException {

		if(Strings.isNullOrEmpty(req)){
			return null;
		}

		String tradeDataStr = request.getParameter("req");
		logger.info("富友实时交易流水数据" + req);
//		logger.info("富友实时交易流水数据参数req=" + tradeDataStr);

		int len = req.lastIndexOf("key_sign");
		String keySign = req.substring(len);
		String keyMd5 = env.getProperty("channel.fuiou.md5");
		String keySignData = req.substring(0,len-2)+keyMd5;
		System.out.println("获取待进行MD5计算的签名数据："+keySignData);
		try {
			String ccc = md5Encrypt(keySignData);
			System.out.println("计算出来的签名值为："+ccc);
			System.out.println("JSON字串待校验的签名值:"+keySign);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FuiouJO fuiouJO = JSONObject.parseObject(req, FuiouJO.class);//将JSON字符串转换为实体对象

		if(null == fuiouJO){
			return null;
		}

		TradeData tradeData = new TradeData();

		//如果富友退款订单号(原交易订单号)为空，则表示退款或者撤销交易
		if( Strings.isNullOrEmpty(fuiouJO.getOut_refund_no()) ){
			tradeData.setTxnType(TradeTypeEnum.CONSUMER.getCode());//交易类型1-消费；2-撤销
			tradeData.setStatus("1");//交易状态0非正常交易（包括撤销交易和撤销原交易）1正常交易
		}else{
			tradeData.setTxnType(TradeTypeEnum.CANCEL.getCode());
			tradeData.setStatus("0");
		}

		/**
		 * 应答码,交易状态描述：1.支付成功，2退款成功，3撤销成功，4冲正成功
		 */
		if( "1".equals(fuiouJO.getPay_status()) 
				||"2".equals(fuiouJO.getPay_status())
				||"3".equals(fuiouJO.getPay_status())
				||"4".equals(fuiouJO.getPay_status())){
			tradeData.setRespCode(TradeStateEnum.SUCCESS.getCode());
		}else{
			tradeData.setRespCode(TradeStateEnum.FAIL.getCode());
		}
		tradeData.setRespMsg(fuiouJO.getPay_msg());//应答信息
		tradeData.setSettleDate(fuiouJO.getSettle_date());//清算日起
		tradeData.setSuccTime(fuiouJO.getCreatetime());//交易成功时间
		tradeData.setOrgMerOrderId(fuiouJO.getOut_refund_no());//原商户订单号

		/**
		 * 富友交易卡类型：01-借记卡；02-信用卡；03准贷记卡；04-预付费卡
		 */
		if("01".equals(fuiouJO.getCard_type())){
			tradeData.setDcType("00");
		}else if("02".equals(fuiouJO.getCard_type())){
			tradeData.setDcType("01");
		}
		tradeData.setRefAmt(fuiouJO.getRefund_fee());//退款金额

		tradeData.setChannelType("90");//渠道类型，90表示富友
		tradeData.setAmt(fuiouJO.getTotal_fee());//交易金额
		tradeData.setOrderNo(fuiouJO.getOut_trade_no());//订单号
		tradeData.setOrderTime(fuiouJO.getCreatetime());//订单时间

		tradeData.setTimeStamp(fuiouJO.getCreatetime());//订单时间戳
		tradeData.setTradeDetail(req);//交易详情(JSON字串)

		/**
		 * 支付方式：交易类型： 1 微信;2支付宝; 3银行卡; 4现金; 5无卡支付; 6qq钱包; 7百度钱包;8京东钱包 
		 */
		if("3".equals(fuiouJO.getPay_type())){
			tradeData.setPayType(PayTypeEnum.CARD_PAY.getCode());
			tradeData.setPaySubType(PaySubTypeAllEnum.SK_PAY.getCode());
			tradeData.setReferNo(fuiouJO.getOut_trade_no());//参考号
			tradeData.setTraceNo(fuiouJO.getTerminal_trace());//凭证号
			tradeData.setCertifyId(fuiouJO.getCardno());//卡号
			tradeData.setBankId(fuiouJO.getIssuer());//发卡行编号

		}else if("4".equals(fuiouJO.getPay_type()) ||"5".equals(fuiouJO.getPay_type())){
			tradeData.setPaySubType(PaySubTypeAllEnum.QT_PAY.getCode());
		}else{
			tradeData.setPayType(PayTypeEnum.CODE_PAY.getCode());
			if("1".equals(fuiouJO.getPay_type())){
				tradeData.setPaySubType(PaySubTypeAllEnum.WX_PAY.getCode());
			}else if("2".equals(fuiouJO.getPay_type())){
				tradeData.setPaySubType(PaySubTypeAllEnum.ZFB_PAY.getCode());
			}else if("6".equals(fuiouJO.getPay_type())){
				tradeData.setPaySubType(PaySubTypeAllEnum.QQ_PAY.getCode());
			}else if("7".equals(fuiouJO.getPay_type())){
				tradeData.setPaySubType(PaySubTypeAllEnum.BD_PAY.getCode());
			}else if("8".equals(fuiouJO.getPay_type())){
				tradeData.setPaySubType(PaySubTypeAllEnum.JD_PAY.getCode());
			}
			tradeData.setReferNo(fuiouJO.getRetri_ref_no());//参考号
			tradeData.setOrderIdScan(fuiouJO.getRetri_ref_no());//订单号(扫码支付订单号)
		}
		tradeData.setSource("02");//来源00拉卡拉01导入02同步03法奈昇04浦发
		tradeData.setMerId(fuiouJO.getMerchantno_fuiou());//结算商户号(富友商户号)
		tradeData.setCreateTime(DateUtils.StrToDate(fuiouJO.getCreatetime()));//交易创建时间
		tradeData.setPayMedium("00");//支付媒介00pos机01app02台码
		tradeData.setChannelTermCode(fuiouJO.getTerminal_id());//渠道终端号
		tradeData.setMd5(fuiouJO.getKey_sign());//MD5签名
		tradeData.setId(DbUtil.getUuid());//设置主键Id
		tradeData.setRemark(fuiouJO.getReference());//备注

//		String md5 = md5Encrypt();
//		System.out.println("keyMd5:" + keyMd5);
		
		tradeDataService.saveTradeData(tradeData);//数据插表

		return success();
	}
	
	/**
	 * MD5签名算法验证
	 * @param str
	 * @return
	 * @throws Exception
	 */
	private static String md5Encrypt(String str) throws Exception {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes("UTF-8"));
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
