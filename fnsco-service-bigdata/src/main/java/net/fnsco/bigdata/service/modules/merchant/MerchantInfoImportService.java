package net.fnsco.bigdata.service.modules.merchant;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import net.fnsco.bigdata.api.dto.WebMerchantTerminalDTO;
import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.bigdata.api.merchant.MerchantPosService;
import net.fnsco.bigdata.service.domain.MerchantBank;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantContact;
import net.fnsco.bigdata.service.domain.MerchantCore;
import net.fnsco.bigdata.service.domain.MerchantPos;
import net.fnsco.bigdata.service.domain.MerchantTerminal;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.CodeUtil;
import net.fnsco.core.utils.ReadExcel;

/**
 * @desc excel上传实现类
 * @author hjt
 * @date 2017年6月21日 上午10:15:40
 */
@Service
public class MerchantInfoImportService {
	@Autowired
	private MerchantCoreService merchantCoreService;
	@Autowired
	private MerchantPosService merchantPosService;

	// 批量导入客户
	@Transactional
	public boolean batchImport(String name, MultipartFile file, List<Object[]> customerList) throws ParseException {
		boolean b = false;
		if (customerList.size() != 0) {
			// 迭代添加信息（注：实际上这里也可以直接将customerList集合作为参数，在Mybatis的相应映射文件中使用foreach标签进行批量添加。）
			for (Object[] objs : customerList) {
				for(int i=0;i<objs.length;i++) {
					if(objs[i]==null) {
						objs[i]="";
					}
				}
				//工商注册名称（协议签约一致）
				String mername = String.valueOf(objs[0]);
				//营业执照注册号
				String businesslicensenum = String.valueOf(objs[1]);
				//法人身份证号码
				String cardnum = String.valueOf(objs[2]);
				//法人姓名
				String legalperson = String.valueOf(objs[3]);
				//商户入网注册名称
				String abbreviation = String.valueOf(objs[4]);
				//签购单名称
				String mercrefername = String.valueOf(objs[5]);
				//押金
				//String postype = String.valueOf(objs[6]);
				//机器型号
				String postype = String.valueOf(objs[7]);
				//备注/  机具SN
				String sncode = String.valueOf(objs[8]);
				//商户联系人
				String contactname = String.valueOf(objs[9]);
				//联系电话
				String contactmobile = String.valueOf(objs[10]);
				//装机地址
				String posaddr = String.valueOf(objs[11]);
				//入账人
				String accountname = String.valueOf(objs[12]);
				//入账账号
				String accountno = String.valueOf(objs[13]);
				//开户行
				String subbankname = String.valueOf(objs[14]);
				//邮箱
				String contactemail = String.valueOf(objs[15]);
				//刷卡扣率-借记卡
				String jjk0 = String.valueOf(objs[16]);
				//刷卡扣率-贷记卡
				String creditcardrate = String.valueOf(objs[17]);
				//扫码扣率
				String xx = String.valueOf(objs[18]);
				//法人手机号
				String legalpersonmobile = String.valueOf(objs[19]);
				//证件有效期
				String cardvalidtimeStr = String.valueOf(objs[20]);
				//营业执照有效期
				String businesslicensevalidtimeStr = String.valueOf(objs[21]);
				//商户注册地址
				String registaddress = String.valueOf(objs[22]);
				//商户标签
				String mercflag = String.valueOf(objs[23]);
				//代理商
				Integer agentid =Integer.parseInt(String.valueOf(objs[24]));
				//渠道商户号
				String channelmerid = String.valueOf(objs[25]);
				//终端编号刷卡
				String terminalcode1 = String.valueOf(objs[26]);
				//终端编号扫码
				String terminalcode2 = String.valueOf(objs[27]);
				
				String innerCode = merchantCoreService.getInnerCode();
				/**
				 * 商户基本信息
				 */
				MerchantCore merchantCore = new MerchantCore();
				merchantCore.setInnerCode(innerCode);
				merchantCore.setMerName(mername);
				merchantCore.setBusinessLicenseNum(businesslicensenum);
				merchantCore.setLegalValidCardType("0");
				merchantCore.setCardNum(cardnum);
				merchantCore.setLegalPerson(legalperson);
				merchantCore.setAbbreviation(abbreviation);
				merchantCore.setLegalPersonMobile(legalpersonmobile);
				//excel中导出的时间是“EEE MMM dd HH:mm:ss z yyyy”类型的String类，将他转换成"yyyy/MM/dd"
				SimpleDateFormat sdf1 =  new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.US);
		        Date date1 = sdf1.parse(cardvalidtimeStr);
		        sdf1 = new SimpleDateFormat("yyyy/MM/dd");
		        String cardvalidtime=sdf1.format(date1);
		        SimpleDateFormat sdf2 =  new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.US);
		        Date date2 = sdf2.parse(businesslicensevalidtimeStr);
		        sdf2 = new SimpleDateFormat("yyyy/MM/dd");
		        String businesslicensevalidtime=sdf2.format(date2);
				merchantCore.setCardValidTime(cardvalidtime);
				merchantCore.setBusinessLicenseValidTime(businesslicensevalidtime);
				merchantCore.setRegistAddress(registaddress);
				merchantCore.setMercFlag(mercflag);
				merchantCore.setAgentId(agentid);
				// 商户基本信息保存
				merchantCoreService.doAddMerCore(merchantCore);
				/**
				 * 商户联系人信息
				 */
				MerchantContact merchantContact=new MerchantContact();
				merchantContact.setInnerCode(innerCode);
				merchantContact.setContactName(contactname);
				merchantContact.setContactMobile(contactmobile);
				merchantContact.setContactEmail(contactemail);
				List<MerchantContact> contcactList=new ArrayList<MerchantContact>();
				contcactList.add(merchantContact);
				//商户联系人信息保存
				merchantCoreService.doAddMerContact(contcactList);
				
				/**
				 * 渠道信息
				 */
				MerchantChannel merchantChannel=new MerchantChannel();
				merchantChannel.setInnerCode(innerCode);
				merchantChannel.setChannelMerId(channelmerid);
				merchantChannel.setChannelType("00");
//				List<MerchantChannel> channelList=new ArrayList<MerchantChannel>();
//				channelList.add(merchantChannel);
				//渠道信息保存
				Integer channelId=merchantCoreService.doAddChannel(merchantChannel);
				/**
				 * 商户银行卡信息
				 */
				MerchantBank merchantBank = new MerchantBank();
				merchantBank.setInnerCode(innerCode);
				merchantBank.setAccountName(accountname);
				merchantBank.setAccountNo(accountno);
				merchantBank.setSubBankName(subbankname);
				// 银行卡信息保存
				Integer bankId = merchantCoreService.doAddBanks(merchantBank);
				/**
				 * 商戶pos机信息
				 */
				MerchantPos merchantPos = new MerchantPos();
				merchantPos.setInnerCode(innerCode);
				merchantPos.setMercReferName(mercrefername);
				merchantPos.setPosType(postype);
				merchantPos.setSnCode(sncode);
				merchantPos.setPosAddr(posaddr);
				merchantPos.setStatus("1");
				// 获取银行卡ID
				if(bankId==0) {
					return b;
				}
				merchantPos.setBankId(bankId);
				if(channelId==0) {
					return b;
				}
				merchantPos.setChannelId(channelId);
				// pos机信息保存
				Integer posId = merchantPosService.insertPos(merchantPos);

				/**
				 * 商户终端信息
				 */
				//创建一个merchantTerminal1来接受刷卡的终端信息
				MerchantTerminal merchantTerminal1 = new MerchantTerminal();
				merchantTerminal1.setInnerCode(innerCode);
				// 借记卡字符串分割
				// 借记卡转小数
				if ("null".equals(jjk0)) {
					merchantTerminal1.setDebitCardRate(null);
					merchantTerminal1.setDebitCardMaxFee(null);
				} else {
					String jjk1 = jjk0.substring(0, jjk0.indexOf("%"));
					BigDecimal bigDecimal0 = new BigDecimal(jjk1);
					BigDecimal jjk = bigDecimal0.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
					merchantTerminal1.setDebitCardRate(String.valueOf(jjk.doubleValue()));
					// 借记卡费率封顶值
					String max1 = jjk0.substring(jjk0.indexOf("+"));
					Integer max = Integer.valueOf(max1);
					merchantTerminal1.setDebitCardMaxFee(max);
				}
				merchantTerminal1.setCreditCardRate(creditcardrate);
				merchantTerminal1.setPosId(posId);
				merchantTerminal1.setTermName("刷卡");
				merchantTerminal1.setTerminalType("00");
				merchantTerminal1.setTerminalCode(terminalcode1);
				
				//创建一个merchantTerminal2来接受扫码的终端信息
				MerchantTerminal merchantTerminal2 = new MerchantTerminal();
				merchantTerminal2.setInnerCode(innerCode);
				// 支付宝微信费率分割
				//String xx = String.valueOf(objs[23]);
				if ("null".equals(xx)) {
					merchantTerminal2.setAlipayFee(null);
					merchantTerminal2.setWechatFee(null);
				} else {
					// 支付宝费率转换
					String zfb1 = xx.substring(xx.indexOf("支付宝") + 3, xx.indexOf("%"));
					BigDecimal bigDecimal1 = new BigDecimal(zfb1);
					BigDecimal zfb = bigDecimal1.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
					merchantTerminal2.setAlipayFee(String.valueOf(zfb.doubleValue()));
					// 微信费率转换
					String wx1 = xx.substring(xx.indexOf("微信") + 2, xx.lastIndexOf("%"));
					BigDecimal bigDecimal2 = new BigDecimal(wx1);
					BigDecimal wx = bigDecimal2.divide(new BigDecimal("100")).setScale(6, BigDecimal.ROUND_HALF_UP);
					merchantTerminal2.setWechatFee(String.valueOf(wx.doubleValue()));
					merchantTerminal2.setPosId(posId);
					merchantTerminal2.setTermName("扫码");
					merchantTerminal2.setTerminalType("01");
				}
				merchantTerminal2.setTerminalCode(terminalcode2);
				//把2个终端信息打包成List
				List<MerchantTerminal> terminalList = new ArrayList<MerchantTerminal>();
				terminalList.add(merchantTerminal1);
				terminalList.add(merchantTerminal2);
				// 终端信息保存
				merchantCoreService.doAddMerTerminal(terminalList);
			}
			b = true;
		}
		return b;
	}
}
